package com.alura.loja.service;

import com.alura.loja.client.FornecedorClient;
import com.alura.loja.client.TransportadorClient;
import com.alura.loja.dto.*;

import com.alura.loja.model.Compra;
import com.alura.loja.model.CompraState;
import com.alura.loja.repository.CompraRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class CompraService {

    private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);

    @Autowired
    private FornecedorClient fornecedorClient;

    @Autowired
    private TransportadorClient transportadorClient;

    @Autowired
    private CompraRepository compraRepository;

    @HystrixCommand(threadPoolKey = "buscaCompraPorIdThreadPoolKey")
    public Compra buscaCompraPorId(Long id) {
        return compraRepository.findById(id).orElse(new Compra());
    }

    @HystrixCommand(fallbackMethod = "realizaCompraFallback", threadPoolKey = "realizaCompraThreadPoolKey")
    public Compra realizaCompra(CompraDTO compra) {

        Compra compraSalva = new Compra();
        compraSalva.setState(CompraState.RECEBIDO);
        compraSalva.setEnderecoDestino(compra.getEndereco().toString());
        compraRepository.save(compraSalva);
        compra.setCompraId(compraSalva.getId());

        LOG.info("buscando informações do fornecedor de {}", compra.getEndereco().getEstado());
        InfoFornecedorDTO info = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());
        LOG.info("realizando um pedido");
        InfoPedidoDTO pedido = fornecedorClient.realizaPedido(compra.getItens());
        compraSalva.setPedidoId(pedido.getId());
        compraSalva.setTempoDePreparo(pedido.getTempoDePreparo());
        compraSalva.setState(CompraState.PEDIDO_REALIZADO);
        compraRepository.save(compraSalva);

        InfoEntregaDTO entregaDTO = new InfoEntregaDTO();
        entregaDTO.setPedidoId(pedido.getId());
        entregaDTO.setDataParaEntrega(LocalDate.now().plusDays(pedido.getTempoDePreparo()));
        entregaDTO.setEnderecoOrigem(info.getEndereco());
        entregaDTO.setEnderecoDestino(compra.getEndereco().toString());
        VoucherDTO voucher = transportadorClient.reservaEntrega(entregaDTO);
        compraSalva.setState(CompraState.RESERVA_ENTERGA_REALIZADA);
        compraSalva.setDataPraEntrega(voucher.getPrevisaoParaEntrega());
        compraSalva.setVoucher(voucher.getNumero());
        compraRepository.save(compraSalva);

        compraRepository.save(compraSalva);

        return compraSalva;
    }

    public Compra realizaCompraFallback(CompraDTO compra) {
        if(compra.getCompraId() != null) {
            return compraRepository.findById(compra.getCompraId()).get();
        }

        Compra compraFallback = new Compra();
        compraFallback.setEnderecoDestino(compra.getEndereco().toString());
        return compraFallback;
    }
}

package com.alura.loja.client;

import com.alura.loja.dto.InfoEntregaDTO;
import com.alura.loja.dto.VoucherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("transportador")
public interface TransportadorClient {

    @PostMapping("/entrega")
    VoucherDTO reservaEntrega(@RequestBody InfoEntregaDTO entregaDTO);

}

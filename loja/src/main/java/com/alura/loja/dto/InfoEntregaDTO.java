package com.alura.loja.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class InfoEntregaDTO {
    private Long pedidoId;

    private LocalDate dataParaEntrega;

    private String enderecoOrigem;

    private String enderecoDestino;
}

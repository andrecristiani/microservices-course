package com.alura.loja.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class VoucherDTO {
    private Long numero;

    private LocalDate previsaoParaEntrega;
}

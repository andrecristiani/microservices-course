package com.alura.loja.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pedidoId;

    private Integer tempoDePreparo;

    private String enderecoDestino;

    private LocalDate dataPraEntrega;

    private Long voucher;

    @Enumerated(EnumType.STRING)
    private CompraState state;
}

package com.alura.loja.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompraDTO {

    @JsonIgnore
    private Long compraId;

    private List<ItemDaCompraDTO> itens;

    private EnderecoDTO endereco;
}

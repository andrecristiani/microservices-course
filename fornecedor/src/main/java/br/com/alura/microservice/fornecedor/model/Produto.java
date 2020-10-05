package br.com.alura.microservice.fornecedor.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String estado;
	
	private String descricao;
	
	private BigDecimal preco;
}

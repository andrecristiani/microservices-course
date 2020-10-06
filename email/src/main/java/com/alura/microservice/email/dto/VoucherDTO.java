package com.alura.microservice.email.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VoucherDTO {

	private Long numero;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate previsaoParaEntrega;
}

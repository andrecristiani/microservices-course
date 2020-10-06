package br.com.alura.microservice.transportador.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VoucherDTO {

	private Long numero;

	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate previsaoParaEntrega;

}

package io.github.cursodsouza.msavaliadorcredito.domain.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CartaoCliente {
	// representa os cartoes que o cliente tem
	
	private String nome;
	private String bandeira;
	private BigDecimal limiteLiberado;
	
}

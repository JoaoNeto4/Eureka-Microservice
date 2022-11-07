package io.github.cursodsouza.msavaliadorcredito.application.ex;

public class DadosClienteNotFoundException extends Exception{

	public DadosClienteNotFoundException() {
		super("Dados do Cliente não encontrado para o CPF informado.");
	}
}

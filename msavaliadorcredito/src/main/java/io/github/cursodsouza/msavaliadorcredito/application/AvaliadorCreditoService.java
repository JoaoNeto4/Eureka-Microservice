package io.github.cursodsouza.msavaliadorcredito.application;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.github.cursodsouza.msavaliadorcredito.ClienteResourceClient;
import io.github.cursodsouza.msavaliadorcredito.domain.model.DadosCliente;
import io.github.cursodsouza.msavaliadorcredito.domain.model.SituacaoCliente;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {
	
	private final ClienteResourceClient clienteClient;

	public SituacaoCliente obterSituacaoCliente(String cpf) {
		//obter dados cliente - MSCLIENTES
		//obter cartoes do cliente - MSCARTOES
		
		ResponseEntity<DadosCliente> dadosClienteResponse = clienteClient.dadosCliente(cpf);
		
		return SituacaoCliente
				.builder()
				.cliente(dadosClienteResponse.getBody())
				.build();
	}
}

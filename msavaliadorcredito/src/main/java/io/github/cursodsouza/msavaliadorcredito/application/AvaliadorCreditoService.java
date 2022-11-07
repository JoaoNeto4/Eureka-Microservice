package io.github.cursodsouza.msavaliadorcredito.application;

import java.util.List;

import feign.FeignException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.github.cursodsouza.msavaliadorcredito.application.ex.DadosClienteNotFoundException;
import io.github.cursodsouza.msavaliadorcredito.application.ex.ErroCominicacaoMicroservicesException;
import io.github.cursodsouza.msavaliadorcredito.domain.model.CartaoCliente;
import io.github.cursodsouza.msavaliadorcredito.domain.model.DadosCliente;
import io.github.cursodsouza.msavaliadorcredito.domain.model.SituacaoCliente;
import io.github.cursodsouza.msavaliadorcredito.infra.clients.CartoesResourceClient;
import io.github.cursodsouza.msavaliadorcredito.infra.clients.ClienteResourceClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {
	
	private final ClienteResourceClient clienteClient;
	private final CartoesResourceClient cartoesClient;

	public SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ErroCominicacaoMicroservicesException{
		//obter dados cliente - MSCLIENTES
		//obter cartoes do cliente - MSCARTOES
		
		try {
			ResponseEntity<DadosCliente> dadosClienteResponse = clienteClient.dadosCliente(cpf);
			ResponseEntity<List<CartaoCliente>> cartoesResponse = cartoesClient.getCartoesByCliente(cpf);
			
			return SituacaoCliente
					.builder()
					.cliente(dadosClienteResponse.getBody())
					.cartoes(cartoesResponse.getBody())
					.build();
			
		} catch (FeignException.FeignClientException e) {
			int status = e.status();
			if(HttpStatus.NOT_FOUND.value() == status) {
				throw new DadosClienteNotFoundException();
			}
			throw new ErroCominicacaoMicroservicesException(e.getMessage(), status);
		}
	}
}

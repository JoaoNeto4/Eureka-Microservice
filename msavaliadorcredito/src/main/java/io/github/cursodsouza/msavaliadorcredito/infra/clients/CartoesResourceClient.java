package io.github.cursodsouza.msavaliadorcredito.infra.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.cursodsouza.msavaliadorcredito.domain.model.CartaoCliente;

@FeignClient(value = "mscartoes", path = "/cartoes")
public interface CartoesResourceClient {

	@GetMapping(params = "cpf")
	ResponseEntity<List<CartaoCliente>> getCartoesByCliente(@RequestParam("cpf") String cpf);
}

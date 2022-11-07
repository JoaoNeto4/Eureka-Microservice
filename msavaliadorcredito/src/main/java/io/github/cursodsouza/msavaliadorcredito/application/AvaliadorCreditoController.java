package io.github.cursodsouza.msavaliadorcredito.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.cursodsouza.msavaliadorcredito.application.ex.DadosClienteNotFoundException;
import io.github.cursodsouza.msavaliadorcredito.application.ex.ErroCominicacaoMicroservicesException;
import io.github.cursodsouza.msavaliadorcredito.domain.model.SituacaoCliente;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/avaliacoes-credito")
@RequiredArgsConstructor
public class AvaliadorCreditoController {
	
	private final AvaliadorCreditoService avaliadorCreditoService;

	@GetMapping
	public String status() {
		return "ok";
	}
	
	//localhost:8080/avaliacoes-credito/situacao-clente?cpf=55555555555
	@RequestMapping(value="situacao-cliente", params = "cpf")
	public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf){
		try {
			SituacaoCliente situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
			return ResponseEntity.ok(situacaoCliente);
		} catch (DadosClienteNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (ErroCominicacaoMicroservicesException e) {
			return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
		}
		
		
	}
}

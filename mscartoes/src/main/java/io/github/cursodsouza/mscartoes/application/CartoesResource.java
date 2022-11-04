package io.github.cursodsouza.mscartoes.application;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.cursodsouza.mscartoes.application.representation.CartaoSaveRequest;

@RestController
@RequestMapping("cartoes")
public class CartoesResource {

	@GetMapping
	public String status() {
		return "ok";
	}
	
	public ResponseEntity cadastrar(@RequestBody CartaoSaveRequest request) {
		
	}
	
}

package io.github.cursodsouza.msavaliadorcredito.application.ex;

import lombok.Getter;

public class ErroCominicacaoMicroservicesException extends Exception{

	@Getter
	private Integer status;
	
	public ErroCominicacaoMicroservicesException(String msg, Integer status) {
		super(msg);
		this.status = status;
	}
}

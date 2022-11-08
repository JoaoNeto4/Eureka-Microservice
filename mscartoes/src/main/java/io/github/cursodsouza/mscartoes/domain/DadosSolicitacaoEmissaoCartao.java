package io.github.cursodsouza.mscartoes.domain;

import java.math.BigDecimal;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DadosSolicitacaoEmissaoCartao {

	private Long idCartao;
	private String cpf;
	private String endereco;
	private BigDecimal limiteLiberado;
}
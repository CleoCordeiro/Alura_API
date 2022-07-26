package br.com.cleo.AluraAPi.config.validacao;

import io.swagger.v3.oas.annotations.media.Schema;

public class LanguageErroDetails {

	@Schema(example = "Linguagem não encontrada")
	private final String erro;

	@Schema(example = "Não foi possível encontrar a linguagem, verifique o ID informado")
	private final String message;

	public LanguageErroDetails(String erro, String message) {
		this.erro = erro;
		this.message = message;
	}

	public String getErro() {
		return erro;
	}

	public String getMessage() {
		return message;
	}

}

package br.com.cleo.AluraAPi.config.validacao;

import io.swagger.v3.oas.annotations.media.Schema;

public class ErroDetails {

	@Schema(example = "Tipo de erro")
	private final String erro;

	@Schema(example = "Descrição do erro")
	private final String message;

	public ErroDetails(String erro, String message) {
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

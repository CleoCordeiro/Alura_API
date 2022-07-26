package br.com.cleo.AluraAPi.config.validacao;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ErroHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDetails> handle(MethodArgumentNotValidException exception) {
		List<ErroDetails> details = new ArrayList<>();

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			details.add(
					new ErroDetails(e.getField(),
							messageSource.getMessage(e, LocaleContextHolder.getLocale())));
		});

		return details;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ErroDetails handle(HttpMessageNotReadableException exception) {
		return new ErroDetails(exception.getClass().getName(), exception.getMessage());
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(FileNotFoundException.class)
	public ErroDetails handle(FileNotFoundException exception) {
		return new ErroDetails("Imagem não encontrada", "Verifique se a url da imagem está correta");
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(HttpClientErrorException.class)
	public ErroDetails handle(HttpClientErrorException exception) {
		return new ErroDetails(exception.getStatusText(), exception.getMessage());
	}
}

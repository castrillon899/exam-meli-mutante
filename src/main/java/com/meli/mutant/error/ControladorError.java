package com.meli.mutant.error;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.meli.mutant.exception.DNALengthInvalideException;
import com.meli.mutant.exception.DNAPatternInvalideBaseException;

@ControllerAdvice
public class ControladorError extends ResponseEntityExceptionHandler {

	private static final String ERROR_NO_CONTROLADO = "Error no controlado en tiempo de ejecucion";

	private static final ConcurrentHashMap<String, Integer> CODIGOS_DE_ERROR = new ConcurrentHashMap<>();



	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Error> handleAllExceptions(Exception exception) {
		ResponseEntity<Error> resultado;

		String excepcionNombre = exception.getClass().getSimpleName();
		String mensaje = exception.getMessage();
		Integer codigo = CODIGOS_DE_ERROR.get(excepcionNombre);

		if (codigo != null) {
			Error error = new Error(mensaje);
			resultado = new ResponseEntity<>(error, HttpStatus.valueOf(codigo));
		} else {
			Error error = new Error(ERROR_NO_CONTROLADO);
			resultado = new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resultado;
	}

	public ControladorError() {
		CODIGOS_DE_ERROR.put(DNALengthInvalideException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
		CODIGOS_DE_ERROR.put(DNAPatternInvalideBaseException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
	}
	
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleExceptionInternal(ex, creteRespondeError(ex.getBindingResult()), headers, HttpStatus.BAD_REQUEST,
				request);
	}

	private List<Error> creteRespondeError(BindingResult bindingResult) {
		List<Error> errors = new ArrayList<>();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			Error error = new Error(fieldError.toString());
			errors.add(error);
		}
		return errors;
	}
	
	

}

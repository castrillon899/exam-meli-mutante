package com.meli.mutant.exception.handler.builder;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;

import com.meli.mutant.exception.DNAException;
import com.meli.mutant.exception.handler.model.ResponseError;
import com.meli.mutant.util.BundleMessage;

public class ResponseErrorBuilder {

	/**
	 * Messaging
	 */
	private final BundleMessage bungleMessage;

	/**
	 * default code error messsage
	 */
	private static final String UNEXPECTED = "unexpected";

	/**
	 * Response Error
	 */
	private ResponseError responseError;

	/**
	 * Contructor no args
	 */
	public ResponseErrorBuilder(BundleMessage bungleMessage) {
		this.bungleMessage = bungleMessage;
		responseError = new ResponseError();
	}

	public ResponseError build() {
		loadMessage(this.responseError.getMessage(), responseError.getArgsMessage());
		return responseError;
	}

	/**
	 * si el mensaje es vacio, intenta incontrar el mensaje apartir de codigo de
	 * error en el archivo message.proertoes
	 * 
	 * @param messageCode
	 * @param argsMessage
	 */
	private void loadMessage(String messageCode, Object... argsMessage) {
		if (StringUtils.isEmpty(messageCode)) {
			try {
				String message = bungleMessage.getMessageProperties(this.responseError.getErrorCode(), argsMessage);
				this.responseError.setMessage(message);
			} catch (NoSuchMessageException e) {
				this.responseError.setMessage("No found message error: " + messageCode);
			}
		}
	}

	/**
	 * define la estructura del error
	 * 
	 * @param e BusinessException
	 * @return builder
	 */
	public ResponseErrorBuilder witchBusinesException(DNAException e) {
		withErrorCode(e.getCode());
		withMessage(e.getMessage());
		withErrorMessage(StringUtils.defaultIfBlank(e.getErroMessage(), ExceptionUtils.getRootCauseMessage(e)));
		withStatusCode(e.getStatusCode());
		withArgsMessage(e.getArgsMessage());
		return this;
	}

	/**
	 * define el codigo de respuesta
	 * 
	 * @param code
	 * @return builder
	 */
	public ResponseErrorBuilder withErrorCode(String code) {
		responseError.setErrorCode(StringUtils.defaultIfBlank(code, UNEXPECTED));
		return this;
	}

	/**
	 * define un mensaje de error
	 * 
	 * @param message message
	 * @return builder
	 */
	public ResponseErrorBuilder withMessage(String message) {
		responseError.setMessage(message);
		return this;
	}

	/**
	 * define un mensaje de error apartir de codigo
	 * 
	 * @param messageCode code of message
	 * @param argsMessage arguments of message
	 * @return builder
	 */
	public ResponseErrorBuilder withMessageByCode(String messageCode, Object... argsMessage) {
		loadMessage(messageCode, argsMessage);
		return this;
	}

	/**
	 * define el mensaje de error para ser retornado en la respuesta de error
	 * 
	 * @param errorMessage error message
	 * @return builder
	 */
	public ResponseErrorBuilder withErrorMessage(String errorMessage) {
		responseError.setErrorMessage(StringUtils.defaultIfBlank(errorMessage, UNEXPECTED));
		return this;
	}

	/**
	 * define el codidgo de error http para ser retornado en la respuesta
	 * 
	 * @param httpStatus {@link HttpStatus}
	 * @return builder
	 */
	public ResponseErrorBuilder withStatusCode(HttpStatus httpStatus) {
		httpStatus = ObjectUtils.defaultIfNull(httpStatus, HttpStatus.INTERNAL_SERVER_ERROR);
		responseError.setStatus(httpStatus.value());
		return this;
	}

	/**
	 * message args
	 * 
	 * @param argsMessage arguments of message
	 * @return builder
	 */
	public ResponseErrorBuilder withArgsMessage(Object... argsMessage) {
		this.responseError.setArgsMessage(argsMessage);
		return this;
	}

}

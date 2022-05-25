package com.meli.mutant.exception;

import org.springframework.http.HttpStatus;


public class DNAInvalideLengthException extends DNAException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1701103574391064913L;

	/**
	 * error code
	 * <p>
	 * The length of the DNA sequences must be the same size
	 */
	private static final String error = "dna.sequence.inconsistent.length";

	/**
	 * default args
	 * 
	 * @param expected
	 */
	public DNAInvalideLengthException(int expected, int found) {
		super(error, HttpStatus.BAD_REQUEST);
		String msgError = "The length of the DNA sequences must be the same size. Expected " + expected + ", found "
				+ found;
		super.setErroMessage(msgError);
	}

}

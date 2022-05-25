package com.meli.mutant.exception;

import org.springframework.http.HttpStatus;


public class DNALengthInvalideException extends DNAException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1701103574391064913L;

	/**
	 * error code
	 * <p>
	 * The length of the DNA sequences must be the same size
	 */
	private static final String ERROR = "dna.sequence.inconsistent.length";

	/**
	 * default args
	 * 
	 * @param expected
	 */
	public DNALengthInvalideException(int expected, int found) {
		super(ERROR, HttpStatus.BAD_REQUEST);
		String msgError = "The length of the DNA sequences must be the same size. Expected " + expected + ", found "
				+ found;
		super.setErroMessage(msgError);
	}

}

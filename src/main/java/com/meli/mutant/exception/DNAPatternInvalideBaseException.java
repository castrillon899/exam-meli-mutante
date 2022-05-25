package com.meli.mutant.exception;

import org.springframework.http.HttpStatus;


public class DNAPatternInvalideBaseException extends DNAException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1701103574391064913L;

	/**
	 * error code
	 * <p>
	 * The only valid characters are A, T, C e G.
	 */
	private static final String ERROR = "dna.invalid.nitrogenous.base";

	/**
	 * default args
	 * 
	 * @param dnaRow
	 */
	public DNAPatternInvalideBaseException(String dnaRow) {
		super(ERROR, HttpStatus.BAD_REQUEST,
				"The only valid characters are A, T, C e G. Found invalida char in " + dnaRow);
	}

}
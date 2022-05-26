package com.meli.mutant.exception;


public class DNAPatternInvalideBaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DNAPatternInvalideBaseException(String dnaRow) {
		super("The only valid characters are A, T, C e G. Found invalida char in " + dnaRow);
	}
}

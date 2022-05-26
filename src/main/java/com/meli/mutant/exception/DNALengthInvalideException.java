package com.meli.mutant.exception;


public class DNALengthInvalideException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DNALengthInvalideException(int expected, int found) {
		super("The length of the DNA sequences must be the same size. Expected " + expected + ", found " + found);
	}
}

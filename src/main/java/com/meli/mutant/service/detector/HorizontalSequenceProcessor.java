package com.meli.mutant.service.detector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HorizontalSequenceProcessor extends MutantDetectorProcessor {

	private static final Logger log = LoggerFactory.getLogger(HorizontalSequenceProcessor.class);
	private static final DirectionMatrix DIRECTION = DirectionMatrix.HORIZONTAL;

	public HorizontalSequenceProcessor(DetectorContext context) {
		super(context);
	}

	
	
	
	@Override
	protected void searchMutanteSequences() {
		log.debug("validacion secuencias dna matriz en dirreccion {}: ", DIRECTION);
		char[][] dna = context.getDna();
		
		//El objetivo es recorrer fila por fila para encontrar secuencias DNA
		for (int row = 0; row < dna.length; row++) {
			boolean match = findMutantSequence(Coordinate.at(dna, row, 0));
			if (match) {
				break;
			}
		}
	}

	/**
	 * Realiza el siguiente movimiento en la secencia configurada
	 */
	@Override
	protected void moveNext(Coordinate coordinate) {
		coordinate.column++;
		coordinate.subIndex++;
		coordinate.lastChar = coordinate.curruntChar;
		coordinate.curruntChar = coordinate.dna[coordinate.row][coordinate.column];
	}

	
	/**
	 * valida si el flujo puede realizar un proximo movimiento
	 */
	@Override
	protected boolean hasNext(Coordinate coordinate, int actualSequence) {
		return coordinate.column + 1 <= coordinate.safeIndex;
	}

}

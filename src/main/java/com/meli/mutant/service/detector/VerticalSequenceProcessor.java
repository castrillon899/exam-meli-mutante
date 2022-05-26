package com.meli.mutant.service.detector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerticalSequenceProcessor extends MutantDetectorProcessor {

	private static final Logger log = LoggerFactory.getLogger(VerticalSequenceProcessor.class);
	private static final DirectionMatrix DIRECTION = DirectionMatrix.VERTICAL;

	public VerticalSequenceProcessor(DetectorContext context) {
		super(context);
	}

	@Override
	protected void searchMutanteSequences() {
		log.debug("validacion secuencias dna matriz en dirreccion {}: ", DIRECTION);
		char[][] dna = context.getDna();

		// El objetivo es recorrer columna por columna para encontrar secuencias DNA
		for (int column = 0; column < dna.length; column++) {
			boolean match = findMutantSequence(Coordinate.at(dna, 0, column));
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
		coordinate.row++;
		coordinate.subIndex++;
		coordinate.lastChar = coordinate.curruntChar;
		coordinate.curruntChar = coordinate.dna[coordinate.row][coordinate.column];
	}


	/**
	 * valida si el flujo puede realizar un proximo movimiento
	 */
	@Override
	protected boolean hasNext(Coordinate coordinate, int actualSequence) {
		return coordinate.row + 1 <= coordinate.safeIndex;
	}

}

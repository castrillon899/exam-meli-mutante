package com.meli.mutant.service.detector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiagonalSequenceProcessor extends MutantDetectorProcessor {

	private static final Logger log = LoggerFactory.getLogger(DiagonalSequenceProcessor.class);
	private static final DirectionMatrix DIRECTION = DirectionMatrix.DIAGONAL_DOWN;

	public DiagonalSequenceProcessor(DetectorContext context) {
		super(context);
	}

	@Override
	public void searchMutanteSequences() {
		log.debug("validacion secuencias dna matriz en dirreccion {}: ", DIRECTION);
		char[][] dna = context.getDna();

		// nos ubicamos en la primera fila, primera columna y empezamos patrones de dna
		boolean match = findMutantSequence(Coordinate.at(dna, 0, 0));
		if (match) {
			return;
		}

		// @formatter:off
			//Recorremos apartir de la segunda fila hasta que se cumpla la condicion dna.length - context.getSequenceToMudant()
			//No tiene sentido recorrer todas la filas por que no cumplirian la secuencia de dna requeridas		
		// @formatter:on
		for (int row = 1; row <= dna.length - context.getSequenceToMudant(); row++) {
			match = findMutantSequence(Coordinate.at(dna, row, 0, row));
			// || findMutantSequence(Coordinate.at(dna, 0, row, row));
			if (match) {
				break;
			}
		}

	}

	@Override
	protected void moveNext(Coordinate coordinate) {
		coordinate.row++;
		coordinate.column++;
		coordinate.subIndex++;
		coordinate.lastChar = coordinate.curruntChar;
		coordinate.curruntChar = coordinate.dna[coordinate.row][coordinate.column];
	}

	@Override
	protected boolean hasNext(Coordinate coordinate, int actualSequence) {
		int subIndex = coordinate.subIndex;
		int available = coordinate.size - subIndex;
		return available + actualSequence >= context.getSequenceToMudant()
				&& Math.max(coordinate.column, coordinate.row) + 1 < coordinate.size;
	}

}

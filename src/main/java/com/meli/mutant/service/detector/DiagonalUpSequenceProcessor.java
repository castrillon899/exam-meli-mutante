package com.meli.mutant.service.detector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiagonalUpSequenceProcessor extends DiagonalSequenceProcessor {

	private static final Logger log = LoggerFactory.getLogger(DiagonalUpSequenceProcessor.class);
	private static final DirectionMatrix DIRECTION = DirectionMatrix.DIAGONAL_UP;

	public DiagonalUpSequenceProcessor(DetectorContext context) {
		super(context);
	}

	@Override
	public void searchMutanteSequences() {
		log.debug("validacion secuencias dna matriz en dirreccion {}: ", DIRECTION);
		char[][] dna = context.getDna();

		// Nos ubicamos en la ultima fila para recorrer apartir de ahi
		int fistIndexRow = dna.length - 1;

		// Nos ubicamos en la primera columna
		int fistIndexColumn = 0;
		boolean match = findMutantSequence(Coordinate.at(dna, fistIndexRow, fistIndexColumn));
		if (match) {
			return;
		}
		for (int row = 1; row <= dna.length - context.getSequenceToMudant(); row++) {
			match = findMutantSequence(Coordinate.at(dna, fistIndexRow - row, 0, row))
					|| findMutantSequence(Coordinate.at(dna, fistIndexRow, row, row));
			if (match) {
				break;
			}
		}

	}

	@Override
	protected void moveNext(Coordinate coordinate) {
		coordinate.row--;
		coordinate.column++;
		coordinate.subIndex++;
		coordinate.lastChar = coordinate.curruntChar;
		coordinate.curruntChar = coordinate.dna[coordinate.row][coordinate.column];
	}

	@Override
	protected boolean hasNext(Coordinate coordinate, int actualSequence) {
		int index = coordinate.subIndex;
		int available = coordinate.size - index;
		return available + actualSequence >= context.getSequenceToMudant() && coordinate.row - 1 >= 0
				&& coordinate.column + 1 < coordinate.size;
	}

}

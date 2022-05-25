package com.meli.mutant.service.detector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class MutantDetectorProcessor {

	private static final Logger log = LoggerFactory.getLogger(MutantDetectorProcessor.class);
	protected final DetectorContext context;

	protected MutantDetectorProcessor(DetectorContext context) {
		this.context = context;
	}

	protected abstract void searchMutanteSequences();

	protected abstract void moveNext(Coordinate coordinate);

	protected abstract boolean hasNext(Coordinate coordinate, int actualSequence);

	protected boolean findMutantSequence(Coordinate coordidate) {
		char currentChar = coordidate.dna[coordidate.row][coordidate.column];
		int sequence = 1;
		while (hasNext(coordidate, sequence)) {
			moveNext(coordidate);

			if (currentChar != coordidate.curruntChar) {
				sequence = 1;
				currentChar = coordidate.curruntChar;
			} else {
				sequence++;
				if (sequence >= context.getSequenceToMudant()) {
					log.debug("Se encontro una sencuencia valida mutante valida: {}", coordidate);
					this.newSequenceMatch();

					if (hasMatchSequencesMutant()) {
						return Boolean.TRUE;
					}
				}

			}
		}
		return Boolean.FALSE;
	}

	protected void newSequenceMatch() {
		context.setMatchs(context.getMatchs() + 1);

	}

	public boolean hasMatchSequencesMutant() {
		log.debug("secuencias dna mutantes esperadas {} esperadas. actual: {} ",
				context.getCountSequencesMatchMutant() - 1, context.getMatchs());
		int count = context.getCountSequencesMatchMutant();
		return context.getMatchs() >= count;
	}
}

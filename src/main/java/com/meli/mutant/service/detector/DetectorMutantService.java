package com.meli.mutant.service.detector;

import java.util.LinkedList;
import java.util.List;

public class DetectorMutantService {

	private DetectorContext context;
	List<MutantDetectorProcessor> processors = new LinkedList<>();

	public DetectorMutantService(char[][] dna, int countSequencesMatchMutant, int sequenceToMudant) {
		context = DetectorContext.builder().dna(dna).countSequencesMatchMutant(countSequencesMatchMutant)
				.sequenceToMudant(sequenceToMudant).build();
		loadProcessors();
	}

	private void registerProcessor(MutantDetectorProcessor processor) {
		processors.add(processor);
	}

	private void loadProcessors() {
		MutantDetectorProcessor horizontal = new HorizontalSequenceProcessor(context);
		registerProcessor(horizontal);
		MutantDetectorProcessor vertical = new VerticalSequenceProcessor(context);
		registerProcessor(vertical);
		MutantDetectorProcessor diagnonal = new DiagonalSequenceProcessor(context);
		registerProcessor(diagnonal);
		MutantDetectorProcessor diagnonalUP = new DiagonalUpSequenceProcessor(context);
		registerProcessor(diagnonalUP);
	}

	public boolean isMutante() {
		for (MutantDetectorProcessor processor : processors) {
			processor.searchMutanteSequences();
			if (processor.hasMatchSequencesMutant()) {
				break;
			}
		}

		return context.getMatchs() >= context.getCountSequencesMatchMutant();
	}

}

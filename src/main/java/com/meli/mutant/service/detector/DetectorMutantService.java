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
		MutantDetectorProcessor diagnonal = new DianonalSequenceProcessor(context);
		registerProcessor(diagnonal);
		MutantDetectorProcessor diagnonalUP = new DianonalUpSequenceProcessor(context);
		registerProcessor(diagnonalUP);
	}

	/**
	 * @return true if find the count DNA sequences needed to consider a Mutant
	 */
	public boolean isMutante() {
		for (MutantDetectorProcessor processor : processors) {
			// TODO Evoluir para processamento assincrono e adicionar observable para
			// interroper os processos
			processor.searchMutanteSequences();
			if (processor.hasMatchSequencesMutant()) {
				break;
			}
		}

		// TODO - implementar controller observable para esperar o retorno dos processos
		// TODO - remover os breaks espealhados
		return context.getMatchs() >= context.getCountSequencesMatchMutant();
	}

}

package com.meli.mutant.service.detector;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class DetectorContext {

	char[][] dna;
	private int sequenceToMudant;
	private int countSequencesMatchMutant;
	private int matchs;
	
	public void setMatchs(int matchs) {
		this.matchs = matchs;
	}
	
	

}

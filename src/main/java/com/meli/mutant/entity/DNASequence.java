package com.meli.mutant.entity;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public class DNASequence {

	@NotNull
	private List<String> dna;
}

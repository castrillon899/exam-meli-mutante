package com.meli.mutant.entity;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class DNASequence {

	private List<String> dna;
}

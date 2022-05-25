package com.meli.mutant.domain;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class DNASequence {

	@NotNull
	private List<String> dna;
}

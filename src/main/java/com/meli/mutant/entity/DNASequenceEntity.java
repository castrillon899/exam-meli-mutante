package com.meli.mutant.entity;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class DNASequenceEntity {

	@NotNull
	private List<String> dna;
}

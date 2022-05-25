package com.meli.mutant.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@Document(collection = "dna_result")
public class DNAResult {

	@Id
	private DNASequenceEntity dna;

	@Indexed(name = "is_mutant")
	private boolean isMutant;

}

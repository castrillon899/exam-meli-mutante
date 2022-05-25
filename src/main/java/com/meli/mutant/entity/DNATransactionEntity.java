package com.meli.mutant.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
@Document(collection = "dna_transaction")
public class DNATransactionEntity {

	@Id
	private DNASequence dna;

	@Indexed(name = "is_mutant")
	private boolean isMutant;

}

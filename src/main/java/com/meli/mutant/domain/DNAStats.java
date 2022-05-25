package com.meli.mutant.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class DNAStats {
	@JsonProperty("count_mutant_dna")
	private Long mutantCount;
	@JsonProperty("count_human_dna")
	private Long humanCount;
	private BigDecimal ratio;
	@JsonIgnore
	private Long total;

}

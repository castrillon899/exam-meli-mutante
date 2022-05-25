package com.meli.mutant.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DNAStatus {


	@Builder.Default
	@JsonProperty("count_mutant_dna")
	private Long mutantCount = 0L;

	@Builder.Default
	@JsonProperty("count_human_dna")
	private Long humanCount = 0L;

	private BigDecimal ratio;

	@Builder.Default
	@JsonIgnore
	private Long total = 0L;

}

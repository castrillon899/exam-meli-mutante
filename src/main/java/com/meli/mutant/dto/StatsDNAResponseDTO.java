package com.meli.mutant.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StatsDNAResponseDTO {
	
	@JsonProperty("count_mutant_dna")
	private Long mutantCount;
	@JsonProperty("count_human_dna")
	private Long humanCount;
	private BigDecimal ratio;
}

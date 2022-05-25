package com.meli.mutant.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DNAStatus {

	@Builder.Default
	private Long mutantCount = 0L;

	@Builder.Default
	private Long humanCount = 0L;

	private BigDecimal ratio;

	@Builder.Default
	private Long total = 0L;

	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}

}

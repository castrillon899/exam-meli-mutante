package com.meli.mutant.repository.impl;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ComparisonOperators;
import org.springframework.data.mongodb.core.aggregation.ConditionalOperators;
import org.springframework.stereotype.Repository;

import com.meli.mutant.entity.DNAResult;
import com.meli.mutant.entity.DNAStatus;
import com.meli.mutant.repository.DNAStatusRepository;

@Repository
public class DNAStatusRepositoryImpl implements DNAStatusRepository {

	private static final Logger log = LoggerFactory.getLogger(DNAStatusRepositoryImpl.class);

	/**
	 * {@link MongoTemplate}
	 */
	@Autowired
	private transient MongoTemplate template;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mercadolibre.exam.mutant.repository.DNAStatusRepository#getSummaryStatus(
	 * )
	 */
	@Override
	public DNAStatus getSummaryStatus() {
		log.debug("Repository, find summary status");

		DNAStatus status = findSumResult();
		BigDecimal ratio = calcRatio(status);
		status.setRatio(ratio);

		return status;

	}

	/**
	 * Calculate the ratio between mutant / human<br>
	 * R - ratio<br>
	 * M - mutant count<br>
	 * H - human count
	 * 
	 * R = M / H
	 * 
	 * @param status
	 * @return
	 */
	private BigDecimal calcRatio(DNAStatus status) {
		BigDecimal ratio = BigDecimal.ZERO;

		if (status.getMutantCount() != 0) {
			if (status.getHumanCount() == 0) {
				ratio = BigDecimal.ONE;
			} else {
				BigDecimal mutant = BigDecimal.valueOf(status.getMutantCount());
				BigDecimal human = BigDecimal.valueOf(status.getHumanCount());
				ratio = mutant.divide(human, 2, RoundingMode.HALF_UP);
			}
		}
		return ratio;
	}

	/**
	 * @return result summarized
	 */
	private DNAStatus findSumResult() {
		// @formatter:off
		Aggregation aggregation = Aggregation.newAggregation(
		group()
			.sum(ConditionalOperators
					.when(ComparisonOperators.valueOf("isMutant").equalToValue(true)).then(1).otherwise(0)).as("mutantCount")
			.sum(ConditionalOperators
					.when(ComparisonOperators.valueOf("isMutant").equalToValue(false)).then(1).otherwise(0)).as("humanCount")
			.count().as("total")
		);

		AggregationResults<DNAStatus> result = this.template.aggregate(aggregation, DNAResult.class, DNAStatus.class);
		// @formatter:on

		DNAStatus status = result.getUniqueMappedResult();
		return status == null ? new DNAStatus() : status;
	}

}

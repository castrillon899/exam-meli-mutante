package com.meli.mutant.repository;


public interface DNAStatsRepository {
	/**
	 * Statistics of verifications
	 * 
	 * @return DNAStatus
	 */
	com.meli.mutant.entity.DNAStatus getSummaryStatus();
}

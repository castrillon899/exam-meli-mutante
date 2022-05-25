package com.meli.mutant.repository;


public interface DNAStatusRepository {
	/**
	 * Statistics of verifications
	 * 
	 * @return DNAStatus
	 */
	com.meli.mutant.entity.DNAStatus getSummaryStatus();
}

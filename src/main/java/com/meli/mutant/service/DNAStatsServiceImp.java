package com.meli.mutant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.meli.mutant.dto.StatsDNAResponseDTO;
import com.meli.mutant.repository.DNAStatusRepository;

@Service
public class DNAStatsServiceImp implements DNAStatsService {
	/**
	 * logger
	 */
	private static final Logger log = LoggerFactory.getLogger(DNAStatsServiceImp.class);

	@Autowired
	private DNAStatusRepository dnaStatusRepository;

	public StatsDNAResponseDTO getStats() {
		log.debug("Find statistics of verifications the exposed method (humans, mutants and ratio)");
		com.meli.mutant.entity.DNAStatus getStatistics = dnaStatusRepository.getSummaryStatus();
		return StatsDNAResponseDTO.builder().humanCount(getStatistics.getHumanCount())
				.mutantCount(getStatistics.getMutantCount()).ratio(getStatistics.getRatio()).build();

	}

}
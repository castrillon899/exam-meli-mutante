package com.meli.mutant.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meli.mutant.dto.StatsDNAResponseDTO;
import com.meli.mutant.service.DNAStatsService;

/**
 * API que permite cuantificar las consultas realizadas a la API DNA
 * 
 * @author angel marin
 */
@RestController
@RequestMapping("/stats")
public class MutantStatsController {

	private static final Logger log = LoggerFactory.getLogger(MutantStatsController.class);

	@Autowired
	private DNAStatsService dnaStatusService;

	@GetMapping("/")
	public ResponseEntity<StatsDNAResponseDTO> getStatsMutant() {
		log.debug("Request para obtener estadisticas.");
		StatsDNAResponseDTO status = dnaStatusService.getStats();
		return ResponseEntity.ok(status);
	}

}

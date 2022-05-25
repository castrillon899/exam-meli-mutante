package com.meli.mutant.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meli.mutant.domain.DNASequence;
import com.meli.mutant.dto.SequenceDNARequestDTO;
import com.meli.mutant.service.MutantService;

/**
 * API que permite identificar si un humano es mutante
 * 
 * @author angel marin
 */
@RestController
@RequestMapping("/mutant")
public class MutantController {

	private static final Logger log = LoggerFactory.getLogger(MutantController.class);

	@Autowired
	private MutantService mutantService;

	/**
	 * Valida si es un humano mutante dado un DNA
	 * 
	 * @param sequenceDNARequestDTO
	 * @return void
	 */
	@PostMapping("/")
	public ResponseEntity<Void> isMutant(@RequestBody @Valid SequenceDNARequestDTO sequenceDNARequestDTO) {
		log.debug("REQUEST validando humano si es mutante: {}", sequenceDNARequestDTO);
		DNASequence dnaSequencee = DNASequence.builder().dna(sequenceDNARequestDTO.getDna()).build();
		boolean isMutant = mutantService.isMutant(dnaSequencee);
		if (isMutant) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}

}

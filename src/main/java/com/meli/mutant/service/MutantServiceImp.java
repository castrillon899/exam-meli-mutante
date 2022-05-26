package com.meli.mutant.service;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.meli.mutant.entity.DNATransactionEntity;
import com.meli.mutant.entity.DNASequence;
import com.meli.mutant.dto.SequenceDNARequestDTO;
import com.meli.mutant.exception.DNALengthInvalideException;
import com.meli.mutant.exception.DNAPatternInvalideBaseException;
import com.meli.mutant.repository.DNAResultRepository;
import com.meli.mutant.service.detector.DetectorMutantService;

@Service
public class MutantServiceImp implements MutantService {

	private static final Logger log = LoggerFactory.getLogger(MutantServiceImp.class);
	private static final Pattern DNA_PATTERN = Pattern.compile("[atcg]+", Pattern.CASE_INSENSITIVE);
	private static final int SIZE_MINIMO_DNA_MUTANTE = 4;
	private static final int DEFAULT_COUNT_SEQUENCES_MATCH_MUTANT = 2;
	private static final int DEFAULT_SEQUENCE_SIZE_MUTANT = 4;

	@Autowired
	private DNAResultRepository dnaResultRepository;

	public boolean isMutant(SequenceDNARequestDTO dna) {
		log.debug("Iniciando proceso de validacion si es humano mutante dada una secuencia '{}'", dna);
		boolean isMutant = isDNAMutant(dna);
		saveQuery(dna, isMutant);
		return isMutant;

	}

	private void saveQuery(SequenceDNARequestDTO dna, boolean isMutante) {

		DNASequence dnaSequenceEntity = DNASequence.builder().dna(dna.getDna()).build();
		DNATransactionEntity result = DNATransactionEntity.builder().dna(dnaSequenceEntity).isMutant(isMutante).build();
		this.dnaResultRepository.save(result);
	}

	private boolean isDNAMutant(SequenceDNARequestDTO dnaSequence) {

		if (dnaSequence.getDna() == null || dnaSequence.getDna().size() <= SIZE_MINIMO_DNA_MUTANTE) {
			log.debug(
					"Los dnas enviados no son los minimos requeridos para determinar si es humano mutante. ACTUAL:{}, se espera que los dnas sea >= {}, se retornara que no es humano mutante.",
					dnaSequence.getDna().size(), SIZE_MINIMO_DNA_MUTANTE);
			return false;
		}

		char[][] dnas = loadDNAToMatrix(dnaSequence);
		DetectorMutantService mutantDetector = new DetectorMutantService(dnas, DEFAULT_COUNT_SEQUENCES_MATCH_MUTANT,
				DEFAULT_SEQUENCE_SIZE_MUTANT);
		log.debug("Validando si es humano mutante la secuencia '{}' is a mutant' DNA", dnaSequence);
		return mutantDetector.isMutante();

	}

	private char[][] loadDNAToMatrix(SequenceDNARequestDTO dnaSequence) {
		log.debug("Iniciando carga de DNA a la matriz dna temporal");
		int vectorLength = dnaSequence.getDna().size();
		char[][] dnaMatrix = new char[vectorLength][vectorLength];

		for (int i = 0; i < vectorLength; i++) {
			String dnaItem = dnaSequence.getDna().get(i);
			validateDNA(vectorLength, dnaItem);
			dnaMatrix[i] = dnaItem.toUpperCase().toCharArray();
		}
		return dnaMatrix;
	}

	private void validateDNA(int dnaLength, String dna) {
		if (dna.length() != dnaLength) {
			log.error("El DNA que se intenta cargar no es valido. experado {}, recibido {}:  DNA{} ", dnaLength,
					dna.length(), dna);
			throw new DNALengthInvalideException(dnaLength, dna.length());
		} else if (!DNA_PATTERN.matcher(dna).matches()) {
			log.error("La secuencia de DNA son invalidados, solo son permitidos A, T, C E G. RECIBIDO {}", dna);
			throw new DNAPatternInvalideBaseException(dna);
		}
	}

}

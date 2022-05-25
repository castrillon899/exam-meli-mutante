package com.meli.mutant.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.meli.mutant.entity.DNAResult;

@Repository
public interface DNAResultRepository extends MongoRepository<DNAResult, DNAResult> {

}

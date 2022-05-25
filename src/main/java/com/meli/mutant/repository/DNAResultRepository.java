package com.meli.mutant.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.meli.mutant.entity.DNATransactionEntity;

@Repository
public interface DNAResultRepository extends MongoRepository<DNATransactionEntity, DNATransactionEntity> {

}

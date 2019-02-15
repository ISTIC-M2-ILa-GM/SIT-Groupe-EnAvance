package SIT.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import SIT.backend.entity.CustomSequences;

public interface CustomSequencesRepository extends MongoRepository<CustomSequences, String> {

}

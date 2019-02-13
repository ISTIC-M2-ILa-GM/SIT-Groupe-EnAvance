package SIT.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import SIT.backend.entity.MissionResult;

public interface MissionResultRepository extends MongoRepository<MissionResult, Integer> {

}

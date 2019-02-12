package SIT.backend.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import SIT.backend.entity.Mission;



public interface MissionRepository extends MongoRepository<Mission, Integer>{

}

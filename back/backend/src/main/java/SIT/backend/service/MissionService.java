package SIT.backend.service;

import org.springframework.http.ResponseEntity;

import SIT.backend.entity.Mission;

public interface MissionService {
	
	ResponseEntity<?> creerMission(Mission mission);
	
	ResponseEntity<?> findMissionById(Integer id);
}

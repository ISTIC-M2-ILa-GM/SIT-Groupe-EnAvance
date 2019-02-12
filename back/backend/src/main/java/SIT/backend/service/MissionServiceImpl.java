package SIT.backend.service;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import SIT.backend.entity.Mission;
import SIT.backend.repository.MissionRepository;

@RestController
@RequestMapping("/api/mission")
public class MissionServiceImpl implements MissionService{
	
	@Resource
	private MissionRepository mRepository;
	
	@RequestMapping(value="/local/create", method=RequestMethod.POST)
	@Override
	public ResponseEntity<?> creerMission(@RequestBody(required=true) Mission mission) {
		mission = mRepository.save(mission);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@Override
	@RequestMapping(value="/{missionId}", method=RequestMethod.GET)
	public ResponseEntity<?> findMissionById(Integer id) {
		Mission mission = mRepository.findById(id).get();
		return  new ResponseEntity<>(mission, HttpStatus.OK);
	}

}

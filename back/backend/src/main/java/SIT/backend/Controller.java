package SIT.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import SIT.backend.dto.Points;
import SIT.backend.entity.Mission;
import SIT.backend.repository.MissionRepository;
import SIT.backend.service.NextSequenceService;

@RestController
@RequestMapping("/api/mission")
public class Controller {

	// https://www.djamware.com/post/59be51e780aca768e4d2b140/tutorial-of-building-java-rest-api-using-spring-boot-and-mongodb
	@Autowired
	MissionRepository missionRepository;
	@Autowired
	NextSequenceService nextSequenceService;
	/**
	 * Renvoyer la liste des points d'une mission
	 */
	@PostMapping("")
	@ResponseBody
	public Integer addPlace(@RequestBody Points points) {
		// intialisé l'objet Mission avant de le persister et retourner son id
		//*******************ENCORE DU TRAITEMENT*****************//
		Mission mission = new Mission();
		//générer l'id
		int id = nextSequenceService.getNextSequence("customSequences");
		mission.setId(id);
		//persister l'objet dans mongodb
		missionRepository.save(mission);
		return id;
	}
}

package SIT.backend;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import SIT.backend.dto.PointDto;
import SIT.backend.dto.Points;
import SIT.backend.entity.Mission;
import SIT.backend.repository.MissionRepository;
import SIT.backend.service.NextSequenceService;
import SIT.backend.entity.Point;

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
	public Integer addMission(@RequestBody Points pointsRecus) {
		// intialisé l'objet Mission avant de le persister et retourner son id
		Mission mission = new Mission();
		//traiter les points reçus
		List<Point> points = new ArrayList<Point>();
		int i =0;
		for(PointDto ptd : pointsRecus.getPoints()) {
			//créer notre objet point qui contient toutes les infos
			Point pt = new Point(ptd.getX(),ptd.getY(),ptd.getZ(),null,i);
			//rajouter le dans la liste
			points.add(pt);
			//indexer le point pour identifier l'image
			i++;
		}
		//générer l'id automatiquement
		int id = nextSequenceService.getNextSequence("customSequences");
		mission.setId(id);
		mission.setPoints(points);
		//persister l'objet dans mongodb
		missionRepository.save(mission);
		return id;
	}
}

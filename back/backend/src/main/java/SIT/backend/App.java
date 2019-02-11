package SIT.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SIT.backend.repository.MissionRepository;

@RestController
@RequestMapping("/api/mission")

public class App {

	@Autowired
	MissionRepository missionRepository;
}

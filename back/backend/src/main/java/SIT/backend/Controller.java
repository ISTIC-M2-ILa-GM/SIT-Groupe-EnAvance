package SIT.backend;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import SIT.backend.dto.PointAndroidDTO;
import SIT.backend.dto.PointDeBase;
import SIT.backend.dto.PointsListDTO;
import SIT.backend.dto.ResultDTO;
import SIT.backend.entity.CustomSequences;
import SIT.backend.entity.Mission;
import SIT.backend.repository.CustomSequencesRepository;
import SIT.backend.repository.MissionRepository;
import SIT.backend.service.NextSequenceService;
import SIT.backend.entity.PointMission;
import SIT.backend.entity.PointResult;
import SIT.backend.entity.Result;

@RestController
@RequestMapping("/api/mission")
public class Controller {

	// https://www.djamware.com/post/59be51e780aca768e4d2b140/tutorial-of-building-java-rest-api-using-spring-boot-and-mongodb
	@Autowired
	MissionRepository missionRepository;
	@Autowired
	CustomSequencesRepository customSequencesRepository;
	@Autowired
	NextSequenceService nextSequenceService;

	/**
	 * Renvoyer la liste des points d'une mission
	 */
	@PostMapping("")
	@ResponseBody
	public Integer addMission(@RequestBody PointsListDTO pointsRecus) {
		// intialisé l'objet Mission avant de le persister et retourner son id
		Mission mission = new Mission();
		// traiter les points reçus
		List<PointMission> points = new ArrayList<PointMission>();
		for (PointAndroidDTO ptd : pointsRecus.getPoints()) {
			// créer notre objet point qui contient toutes les infos
			PointMission pt = new PointMission(ptd.getX(), ptd.getY(), ptd.getZ(), ptd.getIndex());
			// rajouter le dans la liste
			points.add(pt);
			// indexer le point pour identifier l'image
		}
		// générer l'id automatiquement
		int id = nextSequenceService.getNextSequence("customSequences");
		mission.setId(id);
		mission.setPoints(points);
		// persister l'objet dans mongodb
		missionRepository.save(mission);
		return id;
	}

	/**
	 * Renvoyer la dernière mission
	 */
	@GetMapping("/last")
	@ResponseBody
	public Mission getLastMission() {
		CustomSequences customSeq = customSequencesRepository.findAll().get(0);
		Integer idLastMission = customSeq.getSeq();
		Optional<Mission> mission = missionRepository.findById(idLastMission);
		if (mission.isPresent()) {
			return mission.get();
		} else {
			return null;
		}
	}

	/**
	 * Recevoir une photo par point
	 * 
	 * @throws IOException
	 */
	@PostMapping("/result/{mission_id}")
	@ResponseBody
	public void sendPhoto(@PathVariable("mission_id") String mission_id, @RequestBody ResultDTO resultDTO)
			throws IOException {

		String repPath = "./pictures/";

		BufferedImage image = null;
		byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(resultDTO.getPicture());
		// write the image to a file
		String path = repPath + "imageM" + mission_id + "_" + System.currentTimeMillis() + ".png";
		File outputFile = new File(path);
		try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile))) {
			outputStream.write(imageBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Result result = new Result();
		PointDeBase ptbase = resultDTO.getPointDroneDTO();
		PointResult ptr = new PointResult(ptbase.getX(), ptbase.getY(), ptbase.getZ());
		result.setPointResult(ptr);
		result.setPathToImage(path);
		
		result.setId(UUID.randomUUID()+""+System.currentTimeMillis());

		Optional<Mission> missionOpt = missionRepository.findById(Integer.parseInt(mission_id));
		if (missionOpt.isPresent()) {
			Mission mission = missionOpt.get();
			List<Result> results = mission.getResults();
			if(results == null) {
				results =new ArrayList<Result>();
			}
			results.add(result);
			mission.setResults(results);
			missionRepository.save(mission);
		}
	}

	/**
	 * 
	 * @throws IOException
	 */
	@GetMapping("/{mission_id}/result/{result_id}")
	@ResponseBody
	public ResultDTO getPhoto(@PathVariable("mission_id") String mission_id,
			@PathVariable("result_id") String result_id) throws IOException {
		Optional<Mission> missionOpt = missionRepository.findById(Integer.parseInt(mission_id));
		ResultDTO toReturn = new ResultDTO();

		if (missionOpt.isPresent()) {
			Mission mission = missionOpt.get();
			List<Result> results = mission.getResults();
			for (Result result : results) {
				if (result.getId().equals(result_id)) {
					String imageFilePath = result.getPathToImage();
					// Encode the image file into base64
					byte[] fileContent = FileUtils.readFileToByteArray(new File(imageFilePath));
					String encodedImage = Base64.getEncoder().encodeToString(fileContent);
					toReturn.setPicture(encodedImage);
					PointDeBase ptbase = new PointDeBase(result.getPointResult().getX(), result.getPointResult().getY(),
							result.getPointResult().getZ());
					toReturn.setPointDroneDTO(ptbase);
					return toReturn;
				}
			}
		}
		return null;

	}
}

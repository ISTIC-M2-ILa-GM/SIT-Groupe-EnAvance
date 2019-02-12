package SIT.backend.dto;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class ImageDTO {
	
	private Integer index;
	
	//le droe doit nous communiquer l'id de la mission
	//pour qu'on puisse stocker le chemin vers l'image dans
	//les bons points dans la bonne mission
	private Integer idMission;

	//l'image ??
	//https://stackoverflow.com/questions/18301524/image-upload-with-spring-rest
	private MultipartFile image;


}

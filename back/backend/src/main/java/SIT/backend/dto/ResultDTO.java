package SIT.backend.dto;

public class ResultDTO {

	private String picture;
	private PointDeBase pointDroneDTO;

	public ResultDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResultDTO(String picture, PointDeBase pointDroneDTO) {
		super();
		this.picture = picture;
		this.pointDroneDTO = pointDroneDTO;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public PointDeBase getPointDroneDTO() {
		return pointDroneDTO;
	}

	public void setPointDroneDTO(PointDeBase pointDroneDTO) {
		this.pointDroneDTO = pointDroneDTO;
	}

}

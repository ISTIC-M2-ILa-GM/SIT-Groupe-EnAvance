package SIT.backend.dto;

public class ResultDTO {

	private String picture;
	private PointDroneDTO pointDroneDTO;

	public ResultDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResultDTO(String picture, PointDroneDTO pointDroneDTO) {
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

	public PointDroneDTO getPointDroneDTO() {
		return pointDroneDTO;
	}

	public void setPointDroneDTO(PointDroneDTO pointDroneDTO) {
		this.pointDroneDTO = pointDroneDTO;
	}

}

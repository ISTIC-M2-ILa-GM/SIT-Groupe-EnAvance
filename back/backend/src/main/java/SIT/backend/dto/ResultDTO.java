package SIT.backend.dto;

public class ResultDTO {

	private String picture;
	private PointDeBase point;

	public ResultDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResultDTO(String picture, PointDeBase point) {
		super();
		this.picture = picture;
		this.point = point;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public PointDeBase getPointDroneDTO() {
		return point;
	}

	public void setPointDroneDTO(PointDeBase point) {
		this.point = point;
	}

}

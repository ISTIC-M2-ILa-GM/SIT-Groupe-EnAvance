package SIT.backend.dto;

import java.util.List;

public class PointsListDTO {

	List<PointAndroidDTO> points;

	public PointsListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PointsListDTO(List<PointAndroidDTO> points) {
		super();
		this.points = points;
	}

	public List<PointAndroidDTO> getPoints() {
		return points;
	}

	public void setPoints(List<PointAndroidDTO> points) {
		this.points = points;
	}

}

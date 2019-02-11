package SIT.backend.dto;

import java.util.List;

public class PointsListDTO {

	List<PointDTO> points;

	public PointsListDTO(List<PointDTO> points) {
		super();
		this.points = points;
	}

	public List<PointDTO> getPoints() {
		return points;
	}

	public void setPoints(List<PointDTO> points) {
		this.points = points;
	}

}

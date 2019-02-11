package SIT.backend.dto;

import java.util.List;

public class Points {

	List<PointDto> points;

	public Points(List<PointDto> points) {
		super();
		this.points = points;
	}

	public List<PointDto> getPoints() {
		return points;
	}

	public void setPoints(List<PointDto> points) {
		this.points = points;
	}

}

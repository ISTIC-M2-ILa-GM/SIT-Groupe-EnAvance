package SIT.backend.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "missionsresult")
public class MissionResult {

	@Id
	private Integer id;
	private Point point;

	public MissionResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MissionResult(Integer id, Point point) {
		super();
		this.id = id;
		this.point = point;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

}

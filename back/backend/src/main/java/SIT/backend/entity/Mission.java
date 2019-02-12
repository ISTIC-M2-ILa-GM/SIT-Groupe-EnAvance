package SIT.backend.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "missions")
public class Mission {

	@Id
	private Integer id;
	private List<Point> points;
	
	

	public Mission() {
	}

	public Mission(Integer id, List<Point> points) {
		super();
		this.id = id;
		this.points = points;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

}

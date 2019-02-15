package SIT.backend.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "missions")
public class Mission {

	@Id
	private Integer id;
	private List<PointMission> points;
	private List<Result> results;

	public Mission() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mission(Integer id, List<PointMission> points, List<Result> results) {
		super();
		this.id = id;
		this.points = points;
		this.results = results;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<PointMission> getPoints() {
		return points;
	}

	public void setPoints(List<PointMission> points) {
		this.points = points;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

}

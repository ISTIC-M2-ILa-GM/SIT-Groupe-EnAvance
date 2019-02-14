package SIT.backend.entity;

import org.springframework.data.annotation.Id;

public class Result {

	@Id
	private Integer id;
	private PointResult pointResult;
	private String pathToImage;

	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Result(Integer id, PointResult pointResult, String pathToImage) {
		super();
		this.id = id;
		this.pointResult = pointResult;
		this.pathToImage = pathToImage;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PointResult getPointResult() {
		return pointResult;
	}

	public void setPointResult(PointResult pointResult) {
		this.pointResult = pointResult;
	}

	public String getPathToImage() {
		return pathToImage;
	}

	public void setPathToImage(String pathToImage) {
		this.pathToImage = pathToImage;
	}

}

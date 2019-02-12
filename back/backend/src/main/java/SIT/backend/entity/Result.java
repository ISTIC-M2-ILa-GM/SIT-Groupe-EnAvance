package SIT.backend.entity;

public class Result {

	private String pathToImage;

	public Result(String pathToImaage) {
		super();
		this.pathToImage = pathToImaage;
	}

	public String getPathToImaage() {
		return pathToImage;
	}

	public void setPathToImaage(String pathToImaage) {
		this.pathToImage = pathToImaage;
	}

}

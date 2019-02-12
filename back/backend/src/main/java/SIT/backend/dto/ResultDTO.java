package SIT.backend.dto;

public class ResultDTO {

	private String picture;

	public ResultDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResultDTO(String imageB64) {
		super();
		this.picture = imageB64;
	}

	public String getImageB64() {
		return picture;
	}

	public void setImageB64(String imageB64) {
		this.picture = imageB64;
	}

}

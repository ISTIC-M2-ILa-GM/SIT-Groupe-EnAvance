package SIT.backend.dto;

public class PointAndroidDTO {

	double x, y, z;
	Integer index;

	public PointAndroidDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PointAndroidDTO(double x, double y, double z, Integer index) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.index = index;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

}

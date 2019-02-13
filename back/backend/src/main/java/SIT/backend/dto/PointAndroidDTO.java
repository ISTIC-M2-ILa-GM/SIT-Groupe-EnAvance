package SIT.backend.dto;

public class PointAndroidDTO {

	long x, y, z;
	Integer index;

	public PointAndroidDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PointAndroidDTO(long x, long y, long z, Integer index) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.index = index;
	}

	public long getX() {
		return x;
	}

	public void setX(long x) {
		this.x = x;
	}

	public long getY() {
		return y;
	}

	public void setY(long y) {
		this.y = y;
	}

	public long getZ() {
		return z;
	}

	public void setZ(long z) {
		this.z = z;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

}

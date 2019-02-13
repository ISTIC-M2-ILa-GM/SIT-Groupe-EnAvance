package SIT.backend.dto;

public class PointDroneDTO {

	long x, y, z;

	public PointDroneDTO() {
			super();
			// TODO Auto-generated constructor stub
		}

	public PointDroneDTO(long x, long y, long z) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
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

}

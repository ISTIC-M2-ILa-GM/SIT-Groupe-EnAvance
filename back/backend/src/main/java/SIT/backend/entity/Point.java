package SIT.backend.entity;

public class Point {

	private long x, y, z;
	// contient le path vers l'image
	private Result result;
	// index pour pour faire la liaison image+point
	// on va recevoir l'index dans la requete (param) avec l'image dans le body
	private Integer index;

	public Point(long x, long y, long z, Result result, Integer index) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.result = result;
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

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

}

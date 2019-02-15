package SIT.backend.entity;

public class PointMission extends PointResult {

	private Integer index;

	public PointMission() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PointMission(double x, double y, double z) {
		super(x, y, z);
		// TODO Auto-generated constructor stub
	}
	
	public PointMission(double x, double y, double z,Integer index) {
		super(x, y, z);
		this.index = index;
		// TODO Auto-generated constructor stub
	}

	public PointMission(Integer index) {
		super();
		this.index = index;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

}

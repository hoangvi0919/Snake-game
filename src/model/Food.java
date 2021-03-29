package model;

import java.awt.Point;

public abstract class Food {
	// loại thức ăn, Vị trí hiển thị, độ dinh dưỡng//
	private String type;
	private Point location;
	private int nutrition;

	public Food() {
		this.location = null;
	}
	// phương thức get
	public String getType() {
		return type;
	}

	public Point getLocation() {
		return location;
	}

	public int getNutrition() {
		return nutrition;
	}
	
	//phương thức set
	public void setType(String type) {
		this.type = type;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public void setNutrition(int nutrition) {
		this.nutrition = nutrition;
	}
	
	
}

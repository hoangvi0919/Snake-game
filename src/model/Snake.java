package model;

import java.awt.Point;
import java.util.ArrayList;

public class Snake {
	private ArrayList<Point> points;
	private String defaultDirection;// hướng di chuyển mặc định ban đầu

	private int life;
	private int length;
	private int level;
	private int firstSpeed;
	private int lastLevel;
	private int lastLength;
	private int speed;

	public Snake() {
		this.points = new ArrayList<Point>();
		this.defaultDirection = "right";
		this.life = 3;
		this.length = 0;
		this.level = 0;
		this.firstSpeed = 250;
		this.lastLevel = 5;
		this.lastLength = 8;
		this.speed = firstSpeed+level*3/4; // tốc độ tăng theo level
	}

	// get method
	public int getLife() {
		return life;
	}

	public int getLevel() {
		return level;
	}

	public int getLastLevel() {
		return lastLevel;
	}

	public int getLength() {
		return length;
	}

	public int getLastLength() {
		return lastLength;
	}
	
	// phuong thuc set
	public void setLife(int life) {
		this.life = life;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setFirstSpeed(int firstSpeed) {
		this.firstSpeed = firstSpeed;
	}

	public void setLastLevel(int lastLevel) {
		this.lastLevel = lastLevel;
	}

	public void setLastLength(int lastLength) {
		this.lastLength = lastLength;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}

	public String getDefaultDirection() {
		return defaultDirection;
	}
	public void setDefaultDirection(String	newDirectionMove) {
		this.defaultDirection= newDirectionMove;
	}

	public int getFirstSpeed() {
		return firstSpeed;
	}

	public int getSpeed() {
		return speed;
	}
	
	// tăng chiều dài
	public void increaseLength() {
		length = length + 1;
	}
	// tăng mạng sống
	public void increaseLife() {
		life = life + 1;
	}
	// giảm mạng sống
	public void decreaseLife() {
		if (life == 0) {

		} else {
			life = life - 1;
		}
	}
	// tăng mức độ
	public void increaseLevel() {
		level = level + 1;
	}

	// kiểm tra xem rắn còn sống hay không
	public boolean isSnakeAlive() {
		return life != 0;

	}
	// kiá»ƒm tra level Ä‘Ã£ cao nháº¥t chÆ°a
	public boolean isTheHighestLevel() {
		if (level == lastLevel) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isLengthTheLongest() {
		if (length == lastLength) {
			return true;
		}
		return false;
	}
	
	public void revive() {
		points= new ArrayList<Point>();
		defaultDirection="right";
		length=0;
		speed= firstSpeed-level*(firstSpeed/lastLevel);
		
	}
	public void speedUp() {
		points= new ArrayList<Point>();
		defaultDirection= "right";
		length=0;
		this.speed= firstSpeed-level*(firstSpeed/lastLevel);
	}
	
	public void reset() {
		this.points= new ArrayList<Point>();
		this.defaultDirection= "right";
		this.life= 3;
		this.length= 0;
		this.level=0;
		this.firstSpeed=300;
		this.lastLevel=5;
		this.lastLength=8;
		this.speed=firstSpeed+level*3/4;
		
	}

}

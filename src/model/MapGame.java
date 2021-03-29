package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class MapGame {
	private int height, width, leftLimit, rightLimit, topLimit, bottomLimit, heightOfPoint, widthOfPoint;
	private ArrayList<Point> points;
	private AlterPoint alterPoint;

	public MapGame(int width, int height, int leftLimit, int rightLimit, int topLimit, int bottomLimit,
			int widthOfPoint, int heightOfPoint) {
		this.width = width;
		this.height = height;
		this.leftLimit = leftLimit;
		this.rightLimit = rightLimit;
		this.topLimit = topLimit;
		this.bottomLimit = bottomLimit;
		this.widthOfPoint = widthOfPoint;
		this.heightOfPoint = heightOfPoint;
		this.points = new ArrayList<Point>();
		this.alterPoint = new MapBasic();
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getLeftLimit() {
		return leftLimit;
	}

	public void setLeftLimit(int leftLimit) {
		this.leftLimit = leftLimit;
	}

	public int getRightLimit() {
		return rightLimit;
	}

	public void setRightLimit(int rightLimit) {
		this.rightLimit = rightLimit;
	}

	public int getTopLimit() {
		return topLimit;
	}

	public void setTopLimit(int topLimit) {
		this.topLimit = topLimit;
	}

	public int getBottomLimit() {
		return bottomLimit;
	}

	public void setBottomLimit(int bottomLimit) {
		this.bottomLimit = bottomLimit;
	}

	public int getHeightOfPoint() {
		return heightOfPoint;
	}

	public void setHeightOfPoint(int heightOfPoint) {
		this.heightOfPoint = heightOfPoint;
	}

	public int getWidthOfPoint() {
		return widthOfPoint;
	}

	public void setWidthOfPoint(int widthOfPoint) {
		this.widthOfPoint = widthOfPoint;
	}

	public AlterPoint getAlterPoint() {
		return alterPoint;
	}

	// lấy ra danh sách điểm
	public ArrayList<Point> getPoints() {
		return points;
	}
	// cập nhật danh sách point mới
	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}
	public void setAlterPoint(AlterPoint alterPoint) {
		this.alterPoint = alterPoint;

	}

	public void alterPoint() {
		alterPoint.alterPoint(this);
	}
	// vẽ chướng ngại vật
	public void draw(Graphics g) {
		for (int i = 0; i < points.size(); i++) {
			Point point = points.get(i);
			g.setColor(Color.black);
			g.fillRect(point.x, point.y, widthOfPoint - 1, heightOfPoint - 1);
			g.setColor(Color.BLACK);
			g.drawRect(point.x, point.y, widthOfPoint, heightOfPoint);
		}
	}

}

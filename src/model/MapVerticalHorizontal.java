package model;

import java.awt.Point;

import java.util.ArrayList;
import java.util.Random;

public class MapVerticalHorizontal implements AlterPoint {

	@Override
	public void alterPoint(MapGame map) {
		// TODO Auto-generated method stub
		int numberOfPointOfWall = 5;
		int numberOfWall = 20;
		map.setPoints(new ArrayList<Point>());
		ArrayList<Point> temp = new ArrayList<Point>();
		for (int i = 0; i < numberOfWall;) {
			Point point1 = RandomPoint.createRandomPoint(map, numberOfPointOfWall);
			temp.add(point1);
			Point point2 = RandomPoint.createRandomPoint(map, numberOfPointOfWall);
			temp.add(point2);
			for (int j = 0; j < numberOfPointOfWall - 1; j++) {
				point1 = new Point(point1.x + map.getWidthOfPoint(), point1.y);
				point2 = new Point(point2.x, point2.y + map.getHeightOfPoint());
				temp.add(point1);
				temp.add(point2);
			}
			boolean isContained = false;
			for (int k = 0; k < numberOfPointOfWall; k++) {
				if (map.getPoints().contains(temp.get(k))) {
					isContained = true;
					break;
				}
			}
			if (!isContained) {
				for (int h = 0; h < numberOfPointOfWall; h++) {
					map.getPoints().add(temp.get(h));
				}
				i++;
			}
			temp = new ArrayList<Point>();
		}
	}
}

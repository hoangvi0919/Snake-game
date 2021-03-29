package model;
import java.awt.Point;

import java.util.ArrayList;
import java.util.Random;
public class MapHorizontal implements AlterPoint {

	@Override
	public void alterPoint(MapGame map) {
		// TODO Auto-generated method stub
		int numberOfPointOfWall = 3;
		int numberOfWall = 15;
		map.setPoints(new ArrayList<Point>());
		ArrayList<Point> temp = new ArrayList<Point>();
		for (int i = 0; i < numberOfWall;i++) {
			Point point1 = RandomPoint.createRandomPoint(map, numberOfPointOfWall);
			temp.add(point1);
			for (int j = 0; j < numberOfPointOfWall - 1; j++) {
				point1 = new Point(point1.x + map.getWidthOfPoint(), point1.y);
				temp.add(point1);
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

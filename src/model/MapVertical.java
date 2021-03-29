package model;

import java.awt.Point;
import java.util.ArrayList;

public class MapVertical implements AlterPoint{
	
	@Override
	public void alterPoint(MapGame map) {
		int numberOfPointOfWall = 5;
		int numberOfWall = 15;
		map.setPoints(new ArrayList<Point>());
		ArrayList<Point> temp = new ArrayList<Point>();
		for (int i = 0; i < numberOfWall;) {
			Point point1 = RandomPoint.createRandomPoint(map,
					numberOfPointOfWall);
			temp.add(point1);
			for (int j = 0; j < numberOfPointOfWall - 1; j++) {
				point1 = new Point(point1.x, point1.y + map.getHeightOfPoint());
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

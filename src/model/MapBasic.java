package model;

import java.awt.Point;
import java.util.ArrayList;

public class MapBasic implements AlterPoint {

	@Override
	public void alterPoint(MapGame m) {
		m.setPoints(new ArrayList<Point>());
	}

}

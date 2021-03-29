package model;

import java.awt.Point;

import java.util.Random;

public class RandomPoint {
	public static Point createRandomPoint(MapGame room, int numberOfPointOfWall) {
		int gioiHanBenTraiNgoai = room.getLeftLimit() + room.getWidthOfPoint()
				* numberOfPointOfWall;
		int gioiHanBenTraiTrong = (room.getWidth() / 2) - room.getWidthOfPoint()
				* numberOfPointOfWall;
		int gioiHanBenPhaiNgoai = room.getRightLimit() - room.getWidthOfPoint()
				* numberOfPointOfWall;
		int gioiHanBenPhaiTrong = (room.getWidth() / 2) + (room.getWidthOfPoint() * 3)
				+ (room.getWidthOfPoint() * numberOfPointOfWall);
		int gioiHanBenTrenNgoai = room.getTopLimit() + room.getHeightOfPoint()
				* numberOfPointOfWall;
		int gioiHanBenTrenTrong = (room.getHeight() / 2) - room.getHeightOfPoint()
				* numberOfPointOfWall;
		int gioiHanBenDuoiNgoai = room.getBottomLimit() - room.getHeightOfPoint()
				* numberOfPointOfWall;
		int gioiHanBenDuoiTrong = (room.getHeight() / 2) + room.getHeightOfPoint()
				* numberOfPointOfWall;
		Point result = null;
		Random random = new Random();
		int width = 0;
		width = random.nextInt(room.getRightLimit());
		while (true) {
			if ((gioiHanBenTraiNgoai < width && width < gioiHanBenTraiTrong && width
					% room.getWidthOfPoint() == 0)
					|| (gioiHanBenPhaiTrong < width
							&& width < gioiHanBenPhaiNgoai && width
							% room.getWidthOfPoint() == 0)) {
				break;
			}
			width = random.nextInt(room.getRightLimit());

		}
		int height = 0;
		height = random.nextInt(room.getBottomLimit());
		while (true) {
			if ((gioiHanBenTrenNgoai < height && height < gioiHanBenTrenTrong && height
					% room.getHeightOfPoint() == 0)
					|| (gioiHanBenDuoiTrong < height
							&& height < gioiHanBenDuoiNgoai && height
							% room.getHeightOfPoint() == 0)) {
				break;
			}
			height = random.nextInt(room.getBottomLimit());
		}
		result = new Point(width, height);
		return result;
	}
}
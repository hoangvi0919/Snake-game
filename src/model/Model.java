package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import com.sun.java.accessibility.util.TopLevelWindowListener;

public class Model implements ModelInterface {
	private boolean flag;
	private boolean wasViewCanceled;
	private boolean didSnakeMove;
	private int widthOfPanel, heightOfPanel;
	private int widthOfRoom, heightOfRoom;
	private int leftLimit, rightLimit, topLimit, bottomLimit;
	private int widthOfPoint, heightOfPoint;
	private int score, numberOfSpecialFoodAteIn5s, numberOfAddLife, limitOfAddLife;

	private Point leftTopCornerOfRoom;
	private Food food;
	private Snake snake;
	private MapGame map;
	private ArrayList<ScoreObserver> scoreObservers ;
	private ArrayList<LifeObserver> lifeObservers ;
	private ArrayList<LengthObserver> lengthObservers;
	private ArrayList<LevelObserver> levelObservers ;

	public Model() {
		this.flag = false;
		this.wasViewCanceled = false;
		this.didSnakeMove = false;
		this.leftTopCornerOfRoom = new Point(8, 8);
		this.widthOfPanel = 424;
		this.heightOfPanel = 550;
		this.widthOfRoom = 400;
		this.heightOfRoom = 400;
		this.leftLimit = 8;
		this.rightLimit = 408;
		this.topLimit = 8;
		this.bottomLimit = 408;
		setFood(new NormalFood());
		this.numberOfSpecialFoodAteIn5s = 0;
		this.widthOfPoint = 10;
		this.heightOfPoint = 10;
		this.snake = new Snake();
		this.score = 0;
		this.numberOfAddLife = 0;
		this.limitOfAddLife = 50;
		this.scoreObservers = new ArrayList<ScoreObserver>();
		this.lifeObservers = new ArrayList<LifeObserver>();
		this.levelObservers = new ArrayList<LevelObserver>();
		this.lengthObservers = new ArrayList<LengthObserver>();
		map = new MapGame(widthOfRoom, heightOfRoom, leftLimit, rightLimit, topLimit, bottomLimit, widthOfPoint,
				heightOfPoint);
		TypeMap();

	}
	
	// score
	@Override
	public void registerScoreObserver(ScoreObserver score) {
		this.scoreObservers.add(score);

	}

	@Override
	public void removeScoreObserver(ScoreObserver score) {
		this.scoreObservers.remove(score);

	}

	@Override
	public void notifyScoreObserver(int score) {
		for (int i = 0; i < scoreObservers.size(); i++) {
			ScoreObserver scoreObserver = scoreObservers.get(i);
			scoreObserver.updateScore(score);
		}

	}
	// length
	@Override
	public void registerLengthObserver(LengthObserver length) {
		this.lengthObservers.add(length);

	}

	@Override
	public void removeLengthObserver(LengthObserver length) {
		this.lengthObservers.remove(length);

	}

	@Override
	public void notifyLengthObserver(int lengthOfSnake) {
		for (int i = 0; i < lengthObservers.size(); i++) {
			LengthObserver lengthObserver = lengthObservers.get(i);
			lengthObserver.updateLength(lengthOfSnake);
		}

	}
	// life
	@Override
	public void registerLifeObserver(LifeObserver life) {
		this.lifeObservers.add(life);

	}

	@Override
	public void removeLifeObserver(LifeObserver life) {
		this.lifeObservers.remove(life);

	}

	@Override
	public void notifyLifeObserver(int lifeOfSnake) {
		for (int i = 0; i < lifeObservers.size(); i++) {
			LifeObserver lifeObserver = lifeObservers.get(i);
			lifeObserver.updateLife(lifeOfSnake);
		}

	}
	// level
	@Override
	public void registerLevelObserver(LevelObserver level) {
		this.levelObservers.add(level);

	}

	@Override
	public void removeLevelObserver(LevelObserver level) {
		this.levelObservers.remove(level);

	}

	@Override
	public void notifyLevelObserver(int levelOfSnake) {
		for (int i = 0; i < levelObservers.size(); i++) {
			LevelObserver levelObserver = levelObservers.get(i);
			levelObserver.updateLevel(levelOfSnake);
		}
	}

	@Override
	public void setFlag(Boolean flag) {
		this.flag = flag;

	}

	@Override
	public void setFirstStateForSnake() {
		int width = widthOfRoom / 2;
		int height = heightOfRoom / 2;
		Point p1 = new Point(width, height);
		snake.getPoints().add(p1);
		snake.increaseLength();
		for (int i = 0; i < 2; i++) {
			Point p2 = new Point(p1.x + widthOfPoint, p1.y);
			snake.getPoints().add(p2);
			snake.increaseLength();
			p1 = p2;
		}
	}
	// thay doi ran khi di chuyen
	@Override
	public void alterPointsOfSnake() {
		String moveDirection = snake.getDefaultDirection();
		switch (moveDirection) {
		case "right":
			for (int j = 0; j < snake.getPoints().size(); j++) {
				if (j == snake.getPoints().size() - 1) {
					Point temp = snake.getPoints().get(j);
					snake.getPoints().remove(j);
					Point p = new Point(temp.x + widthOfPoint, temp.y);
					snake.getPoints().add(j, p);
				} else {
					Point temp = snake.getPoints().get(j + 1);
					snake.getPoints().remove(j);
					snake.getPoints().add(j, temp);
				}
			}
			break;
		case "left":
			for (int j = 0; j < snake.getPoints().size(); j++) {
				if (j == snake.getPoints().size() - 1) {
					Point temp = snake.getPoints().get(j);
					snake.getPoints().remove(j);
					Point p = new Point(temp.x - widthOfPoint, temp.y);
					snake.getPoints().add(j, p);
				} else {
					Point temp = snake.getPoints().get(j + 1);
					snake.getPoints().remove(j);
					snake.getPoints().add(j, temp);
				}
			}
			break;
		case "up":
			for (int j = 0; j < snake.getPoints().size(); j++) {
				if (j == snake.getPoints().size() - 1) {
					Point temp = snake.getPoints().get(j);
					snake.getPoints().remove(j);
					Point p = new Point(temp.x, temp.y - heightOfPoint);
					snake.getPoints().add(j, p);
				} else {
					Point temp = snake.getPoints().get(j + 1);
					snake.getPoints().remove(j);
					snake.getPoints().add(j, temp);
				}
			}
			break;
		case "down":
			for (int j = 0; j < snake.getPoints().size(); j++) {
				if (j == snake.getPoints().size() - 1) {
					Point temp = snake.getPoints().get(j);
					snake.getPoints().remove(j);
					Point p = new Point(temp.x, temp.y + heightOfPoint);
					snake.getPoints().add(j, p);
				} else {
					Point temp = snake.getPoints().get(j + 1);
					snake.getPoints().remove(j);
					snake.getPoints().add(j, temp);
				}
			}
			break;
		default:
			break;
		}

	}
	// kiem tra ran co dung chuong ngai vat
	@Override
	public boolean snakeHitWall() {
		if (snake.getPoints().size() == 0) {

		} else {
			Point headOfSnake = snake.getPoints().get(snake.getPoints().size() - 1);
			// kiem tra ran dung tuong bao quanh phong
			if (headOfSnake.x < leftLimit || headOfSnake.x >= rightLimit || headOfSnake.y < topLimit
					|| headOfSnake.y >= bottomLimit) {
				return true;
			}
			// kiem tra ran dung tuong trong phong
			for (int i = 0; i < map.getPoints().size(); i++) {
				Point point = map.getPoints().get(i);
				if (headOfSnake.x == point.x && headOfSnake.y == point.y) {
					return true;
				}
			}
		}
		return false;

	}
	// kiem tra ran co can than
	@Override
	public boolean snakeAteItself() {
		boolean result = false;
		if (snake.getPoints().size() == 0) {

		} else {
			Point headOfSnake = snake.getPoints().get(snake.getPoints().size() - 1);
			for (int i = 0; i < snake.getPoints().size() - 1; i++) {
				Point temp = snake.getPoints().get(i);
				if ((temp.x == headOfSnake.x) & (temp.y == headOfSnake.y)) {
					result = true;
					break;
				}
			}
		}
		return result;

	}

	@Override
	public void start() {
		this.flag = true;

	}

	@Override
	public void cancel() {
		this.wasViewCanceled = true;

	}

	@Override
	public void pause() {
		this.flag = false;
	}

	@Override
	public void resume() {

		this.flag = true;
	}

	@Override
	public boolean isSnakeReady() {

		return this.flag;
	}

	@Override
	public void paintPanel(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(leftTopCornerOfRoom.x, leftTopCornerOfRoom.y, widthOfRoom, heightOfRoom);
		g.setColor(Color.WHITE);
		g.fillRect(leftTopCornerOfRoom.x, leftTopCornerOfRoom.y, widthOfRoom, heightOfRoom);
		// draw room
		map.draw(g);
		// draw snake
		drawSnake(g);
		// draw food
		drawFood(g);
	}

	
	
	
	@Override
	public boolean notChangeDirectionMove(String newDirectionMove) {
		boolean result = false;
		if (snake.getDefaultDirection().equals("up") && newDirectionMove.equals("down")) {
			result = true;
		} else if (snake.getDefaultDirection().equals("down") && newDirectionMove.equals("up")) {
			result = true;
		} else if (snake.getDefaultDirection().equals("left") && newDirectionMove.equals("right")) {
			result = true;
		} else if (snake.getDefaultDirection().equals("right") && newDirectionMove.equals("left")) {
			result = true;
		} else {

		}
		return result;
	}

	@Override
	public void setIsMoved(Boolean b) {
		this.didSnakeMove = b;

	}

	@Override
	public void moveDirectionSettings(String newDirectionMove) {
		if (notChangeDirectionMove(newDirectionMove) || (!didSnakeMove)) {

		} else {
			snake.setDefaultDirection(newDirectionMove);
			didSnakeMove = false;
		}

	}

	
	public void setFood(Food food) {
		this.food = food;

	}

	@Override
	public void randomFood() {
		Random random = new Random();
		int i = random.nextInt(20);
		if ((i % 5) == 0) {
			setFood(new SpecialFood());
		} else {
			setFood(new NormalFood());
		}

	}

	@Override
	public void changeSpecialFoodToNormalFood() {
		if (food instanceof NormalFood) {

		} else if (food instanceof SpecialFood) {
			Runnable run = new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (numberOfSpecialFoodAteIn5s != 0) {
						numberOfSpecialFoodAteIn5s -= 1;
					} else if (food instanceof NormalFood) {

					} else if (food instanceof SpecialFood) {
						Point locationOfSpecialFood = food.getLocation();
						setFood(new NormalFood());
						food.setLocation(locationOfSpecialFood);
					}
				}
			};
			Thread t = new Thread(run);
			t.start();
		}

	}
	// tao vi tri thuc an
	@Override
	public void createLocationOfFood() {
		// neu food.getLocation == null thi food da bi snake an do do phai tao lai
		// food.location khac de tao food o vi tri moi
		while (food.getLocation() == null) {
			int width = 0;
			int height = 0;
			Random random = new Random();
			width = random.nextInt(rightLimit);
			while (true) {
				if ((leftLimit <= width) && (width % widthOfPoint == 0)) {
					break;
				}
				width = random.nextInt(rightLimit);
			}
			height = random.nextInt(bottomLimit);
			while (true) {
				if ((topLimit <= height) && (height % heightOfPoint == 0)) {
					break;
				}
				height = random.nextInt(bottomLimit);
			}
			Point temp = new Point(width, height);
			food.setLocation(temp);
			;
			// kiem tra food.location co trung voi point cua ran khong
			for (int i = 0; i < snake.getPoints().size(); i++) {
				Point pointOfSnake = snake.getPoints().get(i);
				if (temp.x == pointOfSnake.x && temp.y == pointOfSnake.y) {
					food.setLocation(null);
					break;
				}
			}
			// kiem tra food.location co trung voi point cua phong khong
			for (int i = 0; i < map.getPoints().size(); i++) {
				Point pointOfWall = map.getPoints().get(i);
				if (temp.x == pointOfWall.x && temp.y == pointOfWall.y) {
					food.setLocation(null);
					break;
				}
			}
		}

	}

	@Override
	public boolean canSnakeEat() {
		boolean result = false;
		if (snake.getPoints().size() == 0) {

		} else {
			Point headOfSnake = snake.getPoints().get(snake.getPoints().size() - 1);
			if (headOfSnake.x == food.getLocation().x && headOfSnake.y == food.getLocation().y) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public void snakeEatFood() {
		if (food.getType().equals("Normal")) {
			snakeEatNormalFood();
		} else if (food.getType().equals("Special")) {
			snakeEatSpecialFood();
		} else {

		}

	}

	@Override
	public void snakeEatNormalFood() {
		Point firstPointOfSnake = snake.getPoints().get(0);
		Point extraPoint = null;
		if (snake.getDefaultDirection().equals("right")) {
			extraPoint = new Point(firstPointOfSnake.x - widthOfPoint, firstPointOfSnake.y);
		} else if (snake.getDefaultDirection().equals("left")) {
			extraPoint = new Point(firstPointOfSnake.x + widthOfPoint, firstPointOfSnake.y);
		} else if (snake.getDefaultDirection().equals("up")) {
			extraPoint = new Point(firstPointOfSnake.x, firstPointOfSnake.y + heightOfPoint);
		} else if (snake.getDefaultDirection().equals("down")) {
			extraPoint = new Point(firstPointOfSnake.x, firstPointOfSnake.y - heightOfPoint);
		}
		snake.getPoints().add(0, extraPoint);
		increaseLengthOfSnake();
		notifyLengthObserver(getLengthOfSnake());

	}

	@Override
	public void snakeEatSpecialFood() {
		this.numberOfSpecialFoodAteIn5s += 1;

	}

	public int getScore() {
		return this.score;
	}
	public void setScore(int score) {
		this.score=score;
	}
	public void increaseScore() {
		score += food.getNutrition();

	}

	@Override
	public void decreaseLifeOfSnake() {
		snake.decreaseLife();

	}

	@Override
	public int getSpeedOfSnake() {
		return this.snake.getSpeed();
	}

	@Override
	public void increaseLengthOfSnake() {
		this.snake.increaseLength();

	}

	@Override
	public void snakeRevive() {
		this.snake.revive();
		randomFood();
		changeSpecialFoodToNormalFood();
	}

	@Override
	public boolean isSnakeAlive() {
		return this.snake.isSnakeAlive();
	}

	// thưởng mạng cho rắn
	@Override
	public boolean addLifeForSnake() {
		if (score >= ((numberOfAddLife + 1) * 50)) {
			numberOfAddLife += 1;
			return true;
		}
		return false;
	}

	@Override
	public void increaseLifeOfSnake() {
		this.snake.increaseLife();

	}

	@Override
	public boolean isLengthOfSnakeTheLongest() {
		return snake.isLengthTheLongest();
	}

	@Override
	public void increaseLevelOfSnake() {
		this.snake.increaseLevel();

	}

	@Override
	public void snakeSpeedUp() {
		this.snake.speedUp();

	}

	@Override
	public int getLifeOfSnake() {
		return snake.getLife();
	}
	
	public void setLife(int life) {
		this.snake.setLife(life);
	}

	@Override
	public int getLevelOfSnake() {
		return snake.getLevel();
	}
	public void setLevel(int level) {
		this.snake.setLevel(level);
	}

	
	@Override
	public int getLastLevelOfSnake() {
		return snake.getLastLevel();
	}

	@Override
	public int getLengthOfSnake() {
		return snake.getLength();
	}
	public void setLength(int length) {
		this.snake.setLength(length);
	}
	

	@Override
	public int getLastLengthOfSnake() {
		return snake.getLastLength();
	}

	@Override
	public boolean isSnakeTheOldest() {
		return snake.isTheHighestLevel();
	}

	@Override
	public void reset() {
		snake.reset();
		flag = false;
		didSnakeMove = false;
		setFood(new NormalFood());
		numberOfSpecialFoodAteIn5s = 0;
		score = 0;
		numberOfAddLife = 0;
		TypeMap();

	}

	@Override
	public void drawFood(Graphics g) {
		Point locationOfFood = food.getLocation();
		if (food.getType().equals("Normal")) {
			g.setColor(Color.green);
		} else if (food.getType().equals("Special")) {
			g.setColor(Color.GRAY.darker());
		}
		int widthOfFood = widthOfPoint;
		int heightOfFood = heightOfPoint;
		if (food.getLocation() == null) {
			
		} else {
			g.drawOval(food.getLocation().x, food.getLocation().y, widthOfFood, heightOfFood);
			g.fillOval(food.getLocation().x, food.getLocation().y, widthOfFood, heightOfFood);
		}

	}

	@Override
	public boolean pointOfSnakeIsWall(Point point) {
		for (int i = 0; i < map.getPoints().size(); i++) {
			Point temp = map.getPoints().get(i);
			if (point.x == temp.x && point.y == temp.y) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean pointOfSnakeIsOutsideWall(Point point) {
		boolean result = false;
		if (snake.getDefaultDirection().equals("right") || snake.getDefaultDirection().equals("down")) {
			if (point.x >= rightLimit || point.y >= bottomLimit) {
				result = true;
			} else {

			}
		} else {
			if (point.x < leftLimit || point.y < topLimit) {
				result = true;
			} else {

			}
		}
		return result;
	}

	@Override
	public void drawSnake(Graphics g) {
		for (int j = 0; j < snake.getPoints().size(); j++) {
			Point point = snake.getPoints().get(j);
			if (pointOfSnakeIsOutsideWall(point)) {
				// not paint
			} else if (pointOfSnakeIsWall(point)) {
				// not paint
			} else {
				if(j==snake.getPoints().size()-1) {
					g.setColor(Color.RED);
					g.fillOval(point.x, point.y, widthOfPoint, heightOfPoint);
					g.setColor(Color.BLACK);
					g.drawOval(point.x, point.y, widthOfPoint, heightOfPoint);
				}
				else {
				g.setColor(Color.GREEN);
				g.fillOval(point.x, point.y, widthOfPoint, heightOfPoint);
				g.setColor(Color.BLACK);
				g.drawOval(point.x, point.y, widthOfPoint, heightOfPoint);
			}}
		}
	}

	@Override
	public void TypeMap() {
		switch (snake.getLevel()) {
		case 0:
			// map khong tuong
			map.setAlterPoint(new MapBasic());
			map.alterPoint();
			break;
		case 1:
			// map co chuong ngay vat nam ngang
			map.setAlterPoint(new MapVertical());
			map.alterPoint();
			break;
		case 2:
			// map co chuong ngai vat nam doc
			map.setAlterPoint(new MapHorizontal());
			map.alterPoint();
			break;
		case 3:
			// map co chuong ngai vat ngang, doc
			map.setAlterPoint(new MapVerticalHorizontal());
			map.alterPoint();
			break;
		case 4:
			// map co day du cac loai chuong ngai vat
			map.setAlterPoint(new MapFull());
			map.alterPoint();
			break;
		default:
			break;
		}
		
	}

	
	public boolean getIsViewCanceled() {
		return wasViewCanceled;
	}

	
	public int getWidthOfPanel() {
		return widthOfPanel;
	}


	public int getHeightOfPanel() {
		return heightOfPanel;
	}
	
	
	
	
	
	
	

	


}

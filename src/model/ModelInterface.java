package model;

import java.awt.Graphics;
import java.awt.Point;

public interface ModelInterface {
	// score
	public void registerScoreObserver(ScoreObserver score);
	public void removeScoreObserver(ScoreObserver score);
	public void notifyScoreObserver(int score);
	
	//length
	public void registerLengthObserver(LengthObserver length);
	public void removeLengthObserver(LengthObserver length);
	public void notifyLengthObserver(int lengthOfSnake);
	
	//Life
	public void registerLifeObserver(LifeObserver life);
	public void removeLifeObserver(LifeObserver life);
	public void notifyLifeObserver(int lifeOfSnake);
	
	//Level
	public void registerLevelObserver(LevelObserver level);
	public void removeLevelObserver(LevelObserver level);
	public void notifyLevelObserver(int levelOfSnake);
	
	public void setFlag(Boolean flag);
	
	public void setFirstStateForSnake();
	
	public void alterPointsOfSnake();
	
	public boolean snakeHitWall();

	public boolean snakeAteItself();

	public void start();
	public void cancel();
	public void pause();
	public void resume();

	public boolean isSnakeReady();

	public void paintPanel(Graphics g);

	public boolean notChangeDirectionMove(String newDirectionMove);

	public void setIsMoved(Boolean b);

	public void moveDirectionSettings(String huongDiChuyenMoi);

	public void setFood(Food food);

	public void randomFood();

	public void changeSpecialFoodToNormalFood();

	public void createLocationOfFood();

	public boolean canSnakeEat();

	public void snakeEatFood();

	public void snakeEatNormalFood();

	public void snakeEatSpecialFood();

	public int getScore();

	public void increaseScore();

	public void decreaseLifeOfSnake();

	public int getSpeedOfSnake();

	public void increaseLengthOfSnake();

	public void snakeRevive();

	public boolean isSnakeAlive();

	public boolean addLifeForSnake();

	public void increaseLifeOfSnake();

	public boolean isLengthOfSnakeTheLongest();

	public void increaseLevelOfSnake();

	public void snakeSpeedUp();

	public int getLifeOfSnake();

	public int getLevelOfSnake();

	public int getLastLevelOfSnake();

	public int getLengthOfSnake();

	public int getLastLengthOfSnake();

	public boolean isSnakeTheOldest();

	public void reset();

	public void drawFood(Graphics g);

	public boolean pointOfSnakeIsWall(Point point);

	public boolean pointOfSnakeIsOutsideWall(Point point);

	public void drawSnake(Graphics g);

	public void TypeMap();

	public boolean getIsViewCanceled();

	public int getWidthOfPanel();

	public int getHeightOfPanel();
}


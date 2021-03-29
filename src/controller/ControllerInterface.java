package controller;

import java.awt.Graphics;

import model.Model;

public interface ControllerInterface {
	public Model getModel();

	public void setFirstState();

	public void paintPanel(Graphics g);

	public void startOrCancel();

	public void pauseOrResume();

	public boolean wasViewCancled();

	public boolean didSnakeDie();

	public boolean isSnakeTheOldest();

	public void moveDirectionSettings(String newDirectionMove);

	public void snakeMove();

	public void snakeEatFood();

	public void snakeRevive();

	public void snakeSpeedUp();

	public void actionSnake();

	public void lose();

	public void win();

	public void rules();

	public void closeRulesView();

	public void reset();
}

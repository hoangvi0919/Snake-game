package controller;

import java.awt.Graphics;


import model.Model;
import view.ChangeMap;
import view.GameRulesView;
import view.LoserView;
import view.SnakeView;
import view.VictoryView;



public class Controller implements ControllerInterface{
	private SnakeView snakeView;
	private Model model;
	private VictoryView victoryView;
	private LoserView loserView;
	private GameRulesView gameRulesView;
	private ChangeMap changeMapView;

	
	public Controller(Model model) {
		this.model = model;
		this.victoryView = new VictoryView();
		this.loserView = new LoserView();
		this.gameRulesView = new GameRulesView(this);
		this.changeMapView = new ChangeMap(this);
		this.snakeView= new SnakeView(this	, model);
		this.snakeView.createView();
	}

	@Override
	public Model getModel() {
		return model;
	}

	@Override
	public void setFirstState() {
		model.setFirstStateForSnake();
		model.notifyScoreObserver(model.getScore());
		model.notifyLifeObserver(model.getLifeOfSnake());
		model.notifyLevelObserver(model.getLevelOfSnake());
		model.notifyLengthObserver(model.getLengthOfSnake());
		model.createLocationOfFood();
		snakeView.repaintPanel();
	}
	

	@Override
	public void paintPanel(Graphics g) {
		model.paintPanel(g);
		
	}

	@Override
	public void startOrCancel() {
		if (snakeView.getTextOfButtonToStartOrCancel().equals("Cancel")) {
			snakeView.cancel();
			model.cancel();
		} else {
			snakeView.start();
			model.start();
			// khong the de phuong thuc ranHoatDong() ngay trong phuong thuc
			// startOrCancle vi khi click start se dan den dung do nut start van
			// dang tiep dien
		}

		
	}

	@Override
	public void pauseOrResume() {
		if (snakeView.getTextOfButtonToPauseOrResume().equals("Pause")) {
			snakeView.pause();
			model.pause();
		} else {
			snakeView.resume();
			model.resume();
		}

		
	}

	@Override
	public boolean wasViewCancled() {
		return model.getIsViewCanceled();
	}

	@Override
	public boolean didSnakeDie() {
		boolean result = model.snakeHitWall() || model.snakeAteItself();
		return result;
	}

	@Override
	public boolean isSnakeTheOldest() {
		return model.isSnakeTheOldest();
	}

	@Override
	public void moveDirectionSettings(String newDirectionMove) {
		model.moveDirectionSettings(newDirectionMove);
		
	}

	@Override
	public void snakeMove() {
		if (model.isSnakeReady()) {
			model.alterPointsOfSnake();
			model.createLocationOfFood();
			snakeView.repaintPanel();
			model.setIsMoved(true);
		}
		
	}

	@Override
	public void snakeEatFood() {
		if (model.canSnakeEat()) {
			model.snakeEatFood();
			model.increaseScore();
			model.notifyScoreObserver(model.getScore());
			// model.randomFood() phai nam sau model.increaseScore neu ko se co
			// the tang lon diem
			model.randomFood();
			model.changeSpecialFoodToNormalFood();
			if (model.addLifeForSnake()) {
				model.increaseLifeOfSnake();
				model.notifyLifeObserver(model.getLifeOfSnake());
			}
			snakeSpeedUp();
			model.createLocationOfFood();
			snakeView.repaintPanel();
	}
	}
	@Override
	public void snakeRevive() {
		if (model.isSnakeAlive()) {
			model.snakeRevive();
			setFirstState();
			actionSnake();
		}
		
	}

	@Override
	public void snakeSpeedUp() {
		if (model.isLengthOfSnakeTheLongest()) {
			model.increaseLevelOfSnake();
			model.notifyLevelObserver(model.getLevelOfSnake());
			if (isSnakeTheOldest()) {
				// do nothing
			} else {
				model.snakeSpeedUp();
				model.TypeMap();
				setFirstState();
				changeMapView.createView();
			}
		}
	}

	@Override
	public void actionSnake() {
		while (true) {
			if (didSnakeDie() || wasViewCancled()) {
				model.decreaseLifeOfSnake();
				model.notifyLifeObserver(model.getLifeOfSnake());
				if (wasViewCancled()) {

				} else {
					snakeRevive();
					// phai bat dieu kien isSnakeTheOldest() vi khi chet n lan
					// va hoi sinh, sau do choi het level luc nay se xay
					// ra truong hop lose va win cung duoc ve
					// khong viet: snakeRevive(); lose();
					if (isSnakeTheOldest()) {
						// do nothing
					} else {
						lose();
					}
				}
				snakeView.disablePause();
				System.out.println("end");
				break;
			}
			if (isSnakeTheOldest()) {
				win();
				snakeView.disablePause();
				break;
			}
			try {
				Thread.sleep(model.getSpeedOfSnake());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			snakeMove();
			snakeEatFood();
		}
		
	}

	@Override
	public void lose() {
		if (loserView.isShowing()) {

		} else {
			loserView.createView();
			loserView.close();
		}
		
	}

	@Override
	public void win() {
		if (victoryView.isShowing()) {

		} else {
			victoryView.createView();
			victoryView.close();
		}
		
	}

	@Override
	public void rules() {
		gameRulesView.createView();
		
	}
	

	@Override
	public void closeRulesView() {
		gameRulesView.close();
		
	}

	@Override
	public void reset() {
		snakeView.reset();
		model.reset();
		setFirstState();
		
	}

}

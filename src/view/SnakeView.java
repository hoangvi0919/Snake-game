package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import controller.Controller;
import model.LengthObserver;
import model.LevelObserver;
import model.LifeObserver;
import model.Model;
import model.ScoreObserver;

public class SnakeView implements ScoreObserver, LifeObserver, LevelObserver, LengthObserver {
	private JFrame frame;
	private JPanel panRight;
	private JMenuBar memuBar;
	private JMenu menuForOption;
	private JMenuItem menuItemForNew, menuItemForHelp;
	private JButton buttonToStartOrCancel, buttonToPauseOrResume;
	private JLabel lbBangTK,labelForScore, labelForLife, labelForLevel, labelForLength;

	private SnakePanel panel;
	private Controller controller;
	private Model model;

	public SnakeView(Controller controller, Model model) {
		super();
		this.controller = controller;
		this.model = model;
		model.registerScoreObserver(this);
		model.registerLengthObserver(this);
		model.registerLevelObserver(this);
		model.registerLifeObserver(this);
	}

	public void createView() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Snake");
		frame.setResizable(false);

		// THANH MENU
		memuBar = new JMenuBar();
		memuBar.setBackground(Color.WHITE);
		menuForOption = new JMenu( "MENU" );
		memuBar.add(menuForOption);
		
		//  New game
		menuItemForNew = new JMenuItem( "New" );
		menuForOption.add(menuItemForNew);
		menuItemForNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (controller.isSnakeTheOldest() || controller.didSnakeDie()) {
					controller.reset();
					Runnable run = new Runnable() {
						@Override
						public void run() {
							controller.actionSnake();
							;
						}
					};
					Thread t = new Thread(run);
					t.start();
				} else {
					controller.reset();
				}
			}
		});

		// Rules
		menuItemForHelp = new JMenuItem( "Rules" );
		menuForOption.add(menuItemForHelp);
		menuItemForHelp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.rules();
			}
		});
		frame.getContentPane().add(memuBar, BorderLayout.NORTH);

		// Panel right
		panRight = new JPanel();
		panRight.setLayout(new GridLayout(7, 1));
		
		

		// Labels
		lbBangTK= new JLabel( );
		lbBangTK.setText("<html>" + "<span style='font-size:15.0pt;color:blue'>"
				+ " Statistical Table:   " + "</html>");
		panRight.add(lbBangTK);
		labelForScore = new JLabel();
		panRight.add(labelForScore);
		labelForLife = new JLabel();
		panRight.add(labelForLife);
		labelForLength = new JLabel();
		panRight.add(labelForLength);
		labelForLevel = new JLabel();
		panRight.add(labelForLevel);
		
		// Cancel button- start button
		buttonToStartOrCancel = new JButton(
				 "Start" );
		buttonToStartOrCancel.setBackground(Color.YELLOW.brighter().brighter());
		buttonToStartOrCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.startOrCancel();
			}

		});
		panRight.add(buttonToStartOrCancel);

		//Pause, Resum button
		buttonToPauseOrResume = new JButton( "Pause" );
		buttonToPauseOrResume.setBackground(Color.YELLOW.brighter().brighter());
		buttonToPauseOrResume.setEnabled(false);
		buttonToPauseOrResume.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.pauseOrResume();
			}

		});
		panRight.add(buttonToPauseOrResume);

		frame.getContentPane().add(panRight, BorderLayout.EAST);
		
		
		
		// snake panel
		panel = new SnakePanel();
		panel.setBackground(Color.darkGray.darker());
		controller.setFirstState();
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		
		frame.setSize(controller.getModel().getWidthOfPanel() + 135, controller.getModel().getHeightOfPanel() - 70);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		// cho 1 tien trinh actionSnake(); chay, luc nay flag = false nen ran
		// chua chay, cho nguoi choi an start
		controller.actionSnake();

	}

	public String getTextOfButtonToStartOrCancel() {
		return buttonToStartOrCancel.getText();
	}
	public String getTextOfButtonToPauseOrResume() {
		return buttonToPauseOrResume.getText();
	}
	
	public void start() {
		panel.setFocusable(true);
		buttonToStartOrCancel.setText( "Cancel" );
		buttonToStartOrCancel.setFocusable(false);
		buttonToPauseOrResume.setEnabled(true);
	}

	public void cancel() {
		frame.dispose();
	}

	public void pause() {
		panel.setFocusable(true);
		buttonToPauseOrResume.setText( "Resume" );
		buttonToPauseOrResume.setFocusable(false);
	}

	public void resume() {
		panel.setFocusable(true);
		buttonToPauseOrResume.setText( "Pause" );
		buttonToPauseOrResume.setFocusable(false);
	}

	public void reset() {
		buttonToStartOrCancel.setText("Start");
		buttonToPauseOrResume.setText( "Pause" );
		buttonToPauseOrResume.setEnabled(false);
	}

	public void disablePause() {
		if (buttonToPauseOrResume.isEnabled()) {
			buttonToPauseOrResume.setEnabled(false);
		} else {

		}
	}

	public void repaintPanel() {
		panel.repaint();
	}

	@Override
	public void updateScore(int score) {
		String s = "Score: ";
		model.setScore(score);
		s+=model.getScore()+"";
		labelForScore.setText(s);
	}

	@Override
	public void updateLife(int lifeOfSnake) {
		String s = "Life: ";
		model.setLife(lifeOfSnake);;
		s+=model.getLifeOfSnake()+"";
		labelForLife.setText(s);

	}

	@Override
	public void updateLevel(int levelOfSnake) {
		String s = "Level: ";
		model.setLevel(levelOfSnake);
		s+=model.getLevelOfSnake()+"";
		s += "/";
		s += controller.getModel().getLastLevelOfSnake();
		labelForLevel.setText(s);

	}

	@Override
	public void updateLength(int lengthOfSnake) {
		String s = "Length: ";
		model.setLength(lengthOfSnake);
		s+=model.getLengthOfSnake()+"";
		s += "/";
		s += controller.getModel().getLastLengthOfSnake();
		labelForLength.setText(s);
	}

	
	private class SnakePanel extends JPanel {

		public SnakePanel() {
			super();
			addKeyListener(new keyHandler());
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			controller.paintPanel(g);
		}

		
		private class keyHandler implements KeyListener {
			public void keyPressed(KeyEvent k) {
				int keyCode = k.getKeyCode();
				if (keyCode == KeyEvent.VK_LEFT) {
					// System.out.println("l");
					if (buttonToPauseOrResume.getText().equals("Resume")
							|| buttonToStartOrCancel.getText().equals("Start")) {

					} else {
						controller.moveDirectionSettings("left");
					}
				} else if (keyCode == KeyEvent.VK_RIGHT) {
					// System.out.println("r");
					if (buttonToPauseOrResume.getText().equals("Resume")
							|| buttonToStartOrCancel.getText().equals("Start")) {

					} else {
						controller.moveDirectionSettings("right");
					}
				} else if (keyCode == KeyEvent.VK_UP) {
					// System.out.println("u");
					if (buttonToPauseOrResume.getText().equals("Resume")
							|| buttonToStartOrCancel.getText().equals("Start")) {

					} else {
						controller.moveDirectionSettings("up");
					}
				} else if (keyCode == KeyEvent.VK_DOWN) {
					// System.out.println("d");
					if (buttonToPauseOrResume.getText().equals("Resume")
							|| buttonToStartOrCancel.getText().equals("Start")) {

					} else {
						controller.moveDirectionSettings("down");
					}
				}
			}

			public void keyReleased(KeyEvent arg0) {
			}
			public void keyTyped(KeyEvent arg0) {
			}

		}
	}

}

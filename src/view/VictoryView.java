package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VictoryView implements IView {
	private JFrame frame;
	private JPanel panel;
	private JLabel lb;

	public VictoryView() {
		super();
	}

	@Override
	public void createView() {
	
		lb = new JLabel();
		lb.setText("<html>" + "<span style='font-size:25.0pt;color:red'>" + "VICTORY" + "</html>");
		
		panel= new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.add(lb);
		
		frame= new JFrame();
		frame.getContentPane().add(panel,BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(210,100);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	public boolean isShowing() {
		if (frame == null) {
			return false;
		} else {
			return frame.isShowing();
		}
	}

	public void close() {
		Runnable run = new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
	
					e.printStackTrace();
				}
				frame.dispose();
			}

		};
		Thread t = new Thread(run);
		t.start();

	}
}

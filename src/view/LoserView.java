package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class LoserView implements IView{
	private JFrame frame;
	private JPanel panel;
	private JLabel label;
	
	public LoserView() {
		super();
	}

	@Override
	public void createView() {
		label = new JLabel();
		label.setText("<html>" + "<span style='font-size:25.0pt;color:red'>"
				+ "YOU LOSED" + "</html>");
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.add(label);
		
		frame= new JFrame();
		frame.getContentPane().add(panel,BorderLayout.CENTER);
		frame.setSize(210, 100);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
					Thread.sleep(1000);
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

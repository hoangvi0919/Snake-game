package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;

public class ChangeMap implements IView {
	private JFrame frame;
	private JLabel label;
	private JPanel panel;

	private Controller controller;

	public ChangeMap(Controller controller) {
		this.controller = controller;
	}
	// tạo view
	@Override
	public void createView() {
		
		label= new JLabel();
		label.setText("<html>" + "<span style='font-size:25.0pt;color:blue'>"
				+ " CHANGE LEVEL ..." + "</html>");
		
		panel= new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.setBackground(Color.WHITE);
		panel.add(label);
		
		
		frame= new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(300, 200);
		frame.getContentPane().add(panel);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		waitAndClose();
		
	}
	//Hiệu ứng chuyển map
	public void waitAndClose() {
		Runnable run = new Runnable() {

			public void run() {
				try {
					controller.getModel().setFlag(false);
					for (int i = 3; i > 0; i--) {
						String a;
						if(i==3) a="READY";
						else a="GO";
						Thread.sleep(1000);
						label.setText("<html>"
								+ "<span style='font-size:80.0pt;color:blue'>"
								+ a + "</html>");
					}
					Thread.sleep(1000);
					frame.dispose();
					controller.getModel().setFlag(true);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		};
		Thread t = new Thread(run);
		t.start();

	}

	
}

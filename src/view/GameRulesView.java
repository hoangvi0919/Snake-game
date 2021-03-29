package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.Controller;

public class GameRulesView implements IView {
	private JFrame frame;
	private JPanel panel;
	private JTextArea textAreaForRules;
	private JButton closeButton;
	private Controller controller;
	public GameRulesView(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void createView() {

		textAreaForRules = new JTextArea();
		textAreaForRules.setText(text());
		textAreaForRules.setEnabled(false);
		textAreaForRules.setBackground(Color.WHITE);
		textAreaForRules.setDisabledTextColor(Color.BLACK);

		closeButton = new JButton("Close");
		closeButton.addActionListener(
				new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent e) {
							controller.closeRulesView();
						}
					}
			);
	
		panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.add(closeButton);
		
		frame= new JFrame("Game Rules");
		frame.getContentPane().add(textAreaForRules, BorderLayout.CENTER);
		frame.getContentPane().add(panel,BorderLayout.SOUTH);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);

	}

	
	public String text() {
		StringBuffer result = new StringBuffer();
		result.append("                                    RULES \n\n");
		result.append("  - Ấn các phím mũi tên để rắn di chuyển \n");
		result.append("sang phải, sang trái hoặc di chuyển lên , xuống.\n");
		result.append("  - Ăn thức ăn màu xanh rắn tăng 1 chiều dài , cộng thêm 5đ\n");
		result.append("  - Ăn thức ăn màu đen được cộng thêm 10 điểm.\n");
		result.append("  - Độ dài rắn bằng 8 thì tăng 1 level.\n");
		result.append("  - Đạt 50đ  được tăng thêm một mạng.\n");
		result.append("  - Tốc độ di tăng dần theo level.\n");
		result.append("  - Chạm tường hoặc  chính mình  sẽ bị mất một mạng.\n");
		result.append("  - Thua cuộc nếu mạng sống bằng 0 \n\n\n\n");
		return result.toString();

	}
	public void close() {
		frame.dispose();
	}

}

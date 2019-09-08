package util;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class checkUI extends JFrame {




	public checkUI(String text) {
		initialize(text);
	}//设置该窗口文本

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String text) {
		setBounds(100, 100, 300, 239);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
		setTitle("提示");
		setVisible(true);
		
		JLabel lblNewLabel = new JLabel(text);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 62, 264, 43);
		getContentPane().add(lblNewLabel);
		
		JButton button = new JButton("确定");
		button.setBounds(95, 127, 93, 23);
		getContentPane().add(button);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}//关闭窗口
		});
	}

}

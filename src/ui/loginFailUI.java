package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JButton;

public class loginFailUI extends JFrame{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginFailUI window = new loginFailUI();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public loginFailUI() {
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 264, 220);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JTextArea txtrr = new JTextArea();
		txtrr.setEditable(false);
		txtrr.setLineWrap(true);
		txtrr.setText("登录失败！");
		txtrr.setForeground(Color.BLACK);
		txtrr.setBounds(81, 56, 70, 24);
		getContentPane().add(txtrr);
		
		JButton button = new JButton("确定");
		button.setBounds(81, 105, 70, 24);
		getContentPane().add(button);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		setVisible(true);
	}

}

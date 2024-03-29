package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import util.*;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TrueUI{

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	//private login lg=new login();
	private SqlConnect sConnect=new SqlConnect();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrueUI window = new TrueUI();
					window.frame.setVisible(true);
					
					//window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}//唯一main函数

	/**
	 * Create the application.
	 */
	public TrueUI() {
		initialize();
		/*new JFrame();
		setBounds(100, 100, 581, 437);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(201, 129, 177, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("用户名");
		label.setBounds(137, 132, 54, 15);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("密  码");
		label_1.setBounds(137, 179, 54, 21);
		getContentPane().add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(201, 179, 177, 21);
		getContentPane().add(passwordField);
		
		JButton button = new JButton("登录");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lg.log_in(textField, passwordField)) {
					new UserUI();
					dispose();
				}else {
					new loginFailUI();
				}
			}
		});
		button.setBounds(232, 242, 93, 23);
		getContentPane().add(button);*/
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(583, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setTitle("考勤系统登录界面");
		
		textField = new JTextField();
		textField.setBounds(201, 129, 177, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("用户名");
		label.setBounds(137, 132, 54, 15);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("密  码");
		label_1.setBounds(137, 179, 54, 21);
		frame.getContentPane().add(label_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(201, 179, 177, 21);
		frame.getContentPane().add(passwordField);
		
		JButton button = new JButton("登录");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
				/*new UserUI();
				frame.dispose();*/
			}
		});
		button.setBounds(232, 242, 93, 23);
		frame.getContentPane().add(button);
		
	}
	
	private void login() {
		if (sConnect.adminLogin(textField, passwordField)) {
			new UserUI();
			frame.dispose();
		}else {
			checkUI ui1=new checkUI("登录失败，请检查用户名与密码");
			ui1.setLocationRelativeTo(frame);
		}
	}
}

package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import util.SqlConnect;
import util.checkUI;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
//该ui为用户创建界面
public class createUserUI {

	/**
	 * 
	 */
	private JFrame frame;
	private SqlConnect sqlConnect=null;
	private JTextField textField;
	private JLabel label_1;
	private JPasswordField passwordField;//界面组件



	/**
	 * Create the application.
	 */
	public createUserUI() {
		sqlConnect=new SqlConnect();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame=new JFrame();
		frame.setResizable(false);
		frame.setTitle("添加员工");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);//主frame
		
		textField = new JTextField();
		textField.setBounds(145, 86, 184, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);//输入用户姓名
		
		JLabel label = new JLabel("姓名");
		label.setBounds(87, 89, 40, 15);
		frame.getContentPane().add(label);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		label_1 = new JLabel("密码");
		label_1.setBounds(87, 141, 40, 15);
		frame.getContentPane().add(label_1);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(145, 138, 184, 21);
		passwordField.setColumns(16);
		frame.getContentPane().add(passwordField);//输入账户密码
		
		JButton button = new JButton("添加");
		button.setBounds(160, 190, 93, 23);
		frame.getContentPane().add(button);
		button.addActionListener(new ActionListener() {//button实现新用户创建/添加
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField tf=passwordField;
				if (sqlConnect.createUser(textField.getText(), tf.getText())) {
					checkUI ui1=new checkUI("添加成功！");
					ui1.setLocationRelativeTo(frame);
				}else {
					checkUI ui1=new checkUI("添加失败！");
					ui1.setLocationRelativeTo(frame);
				}
			}
		});
		
		frame.setVisible(true);
	}
	public void setlocation(JFrame fr) {
		frame.setLocationRelativeTo(fr);
	}//居中设定
}

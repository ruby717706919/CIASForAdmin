package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import util.*;
import java.awt.GridLayout;

public class UserUI {

	private JFrame frame;
	private TimeSynchro timeSynchro=new TimeSynchro();

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserUI window = new UserUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public UserUI() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(135, 206, 250));
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(100, 100, 1024, 604);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 255, 255));
		panel.setForeground(Color.WHITE);
		panel.setBounds(30, 20, 214, 256);
		frame.getContentPane().add(panel);
		
		/*JTextArea textArea = new JTextArea();//显示日期
		textArea.setBounds(30, 46, 140, 24);
		panel.add(textArea);*/
		panel.setLayout(null);
		JLabel label = new JLabel();
		label.setBounds(30, 55, 140, 21);
		panel.add(label);
		
		JLabel label_1 = new JLabel();
		label_1.setBounds(30, 86, 67, 24);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel();
		label_2.setBounds(107, 86, 63, 24);
		panel.add(label_2);
		
		timeSynchro.timeSync(label, label_1, label_2);
		
		JLabel label_3 = new JLabel("当前出勤：");
		label_3.setBounds(0, 0, 0, 0);
		panel.add(label_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(58, 176, 83, 52);
		panel.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(30, 326, 214, 170);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton = new JButton("打卡设定");//该button实现修改打卡规则等的功能
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(10, 39, 93, 23);
		panel_2.add(btnNewButton);
		
		JButton button = new JButton("请假审批");//该button实现关于员工请假的审批功能
		button.setBounds(10, 74, 93, 24);
		panel_2.add(button);
		
		JButton button_1 = new JButton("更多设置");//该button点击后会出现新界面，有更多功能button
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBounds(10, 111, 93, 23);
		panel_2.add(button_1);
		
		JButton button_2 = new JButton("添加员工");
		button_2.setBounds(111, 39, 93, 23);
		panel_2.add(button_2);
		
		JPanel panel_3 = new JPanel();//暂定为显示员工的当日打卡情况，暂不需要实现
		panel_3.setBounds(254, 20, 534, 477);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel = new JLabel("什么都队考勤管理系统");//预定使用图片替换，
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(589, 508, 397, 47);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("考勤总体日报");//该button实现查看本日员工考勤情况功能的实现
		btnNewButton_1.setBounds(798, 20, 188, 74);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("考勤总体周报");//该button实现查看本月员工考勤情况功能的实现，与上button同考虑使用第三方插件，暂无需实现
		btnNewButton_2.setBounds(800, 118, 186, 71);
		frame.getContentPane().add(btnNewButton_2);
		
		JPanel panel_4 = new JPanel();//备用功能button区，暂时无用，无需处理
		panel_4.setBounds(798, 199, 188, 297);
		frame.getContentPane().add(panel_4);
		
		frame.setVisible(true);
	}
}

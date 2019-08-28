package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import util.*;
import java.awt.GridLayout;

import java.sql.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class UserUI {

	private JFrame frame;
	private TimeSynchro timeSynchro=new TimeSynchro();
	private SqlConnect sqlConnect=null;

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
		sqlConnect=new SqlConnect();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(135, 206, 250));
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(100, 100, 1000, 551);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 21, 209, 188);
		panel.setBackground(Color.CYAN);
		panel.setForeground(Color.WHITE);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		JLabel label = new JLabel();
		label.setBackground(Color.WHITE);
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
		panel_1.setBounds(59, 118, 83, 52);
		panel.add(panel_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.CYAN);
		panel_3.setBounds(229, 21, 529, 464);
		frame.getContentPane().add(panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0};
		gbl_panel_3.rowHeights = new int[]{0};
		gbl_panel_3.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{Double.MIN_VALUE};
		panel_3.setLayout(new GridLayout(5, 4, 10, 10));
		
		JButton btnNewButton_1 = new JButton("考勤总体日报");
		btnNewButton_1.setBounds(778, 21, 183, 66);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("考勤总体周报");
		btnNewButton_2.setBounds(778, 109, 183, 72);
		frame.getContentPane().add(btnNewButton_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 224, 209, 261);
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
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(778, 201, 183, 284);
		frame.getContentPane().add(panel_4);
		
		JLabel lblNewLabel = new JLabel("什么都队考勤管理系统");//预定使用图片替换，
		lblNewLabel.setBounds(841, 495, 120, 15);
		lblNewLabel.setForeground(Color.WHITE);
		frame.getContentPane().add(lblNewLabel);
		
	
		ArrayList<JPanel>PanelList=new ArrayList<JPanel>();
		ArrayList<JLabel>userStateList=new ArrayList<JLabel>();
		ArrayList<Users>users=new ArrayList<>();
		users=sqlConnect.getUsers();
		for (int i = 0; i < 20; i++) {
			userStateList.add(new JLabel());
			PanelList.add(new JPanel());
		}
		for (int i=0;i<users.size();i++) {
			userStateList.get(i).setVisible(false);
			userStateList.get(i).setOpaque(true);
			PanelList.add(new JPanel());
			PanelList.get(i).setVisible(false);
			PanelList.get(i).add(userStateList.get(i));
			userStateList.get(i).setHorizontalAlignment(SwingConstants.CENTER);
			userStateList.get(i).setText(String.format("%d%s", users.get(i).getID(), users.get(i).getName()));
			System.out.println(users.get(i).getNowState());
			switch (users.get(i).getNowState()){
				case "0000":
					PanelList.get(i).setBackground(new Color(18, 253, 27));
					break;
				case "1000":
					PanelList.get(i).setBackground(new Color(9, 253, 250));
					break;
				case "0100":
					PanelList.get(i).setBackground(new Color(25, 53, 253));
					break;
				case "0010":
					PanelList.get(i).setBackground(new Color(253, 30, 33));
					break;
				case "0001":
					PanelList.get(i).setBackground(new Color(253, 237, 33));
					break;
				default:
					PanelList.get(i).setBackground(new Color(255, 255, 255));
					break;
			}
			
			panel_3.add(PanelList.get(i));
			PanelList.get(i).setOpaque(true);
			PanelList.get(i).setVisible(true);
			userStateList.get(i).setVisible(true);
		
			PanelList.get(i).setVisible(true);
			userStateList.get(i).setVisible(true);
		}
	
		frame.setVisible(true);
	}
}

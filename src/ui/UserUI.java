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
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;

import util.*;
import java.awt.GridLayout;



public class UserUI {

	private JFrame frame;
	private TimeSynchro timeSynchro=new TimeSynchro();
	private SqlConnect sqlConnect=null;
	private ArrayList<JLabel>userStateList=new ArrayList<JLabel>();
	private ArrayList<Users>users=new ArrayList<>();
	//private ArrayList<SqlConnect>sqlList=new ArrayList<SqlConnect>();
	
	private String attTime;
	private String leaveTime;

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
		/*for (int i = 0; i < 20; i++) {
			sqlList.add(new SqlConnect());
		}*/
		users=sqlConnect.getUsers();
		attTime="0900";
		leaveTime="1700";
		initialize();
	}
	private void setUserStateList(JPanel p) {
		for (int i = 0; i < 20; i++) {
			userStateList.add(new JLabel());
			userStateList.get(i).setVisible(false);
			userStateList.get(i).setVisible(false);
			userStateList.get(i).setOpaque(true);
			userStateList.get(i).setHorizontalAlignment(SwingConstants.CENTER);
			userStateList.get(i).setVerticalAlignment(SwingConstants.CENTER);
			p.add(userStateList.get(i));
		}
	}
	
	private void refreshUserStateList() {
		for (int i=0;i<users.size();i++) {
			//System.out.println("????");
			userStateList.get(i).setText(String.format("%d%s", users.get(i).getID(), users.get(i).getName()));
			//panel_3.add(PanelList.get(i));
			switch (users.get(i).getNowState()){
				case "0000"://正常出勤
					//PanelList.get(i).setBackground(new Color(18, 253, 27));
					userStateList.get(i).setBackground(new Color(18, 253, 27));
					break;
				case "1000"://请假
					//PanelList.get(i).setBackground(new Color(9, 253, 250));
					userStateList.get(i).setBackground(new Color(9, 253, 250));
					break;
				case "0100"://出差
					//PanelList.get(i).setBackground(new Color(25, 53, 253));
					userStateList.get(i).setBackground(new Color(25, 53, 253));
					break;
				case "0010"://旷工
					//PanelList.get(i).setBackground(new Color(253, 30, 33));
					userStateList.get(i).setBackground(new Color(253, 30, 33));
					break;
				case "0001"://迟到或早退
					//PanelList.get(i).setBackground(new Color(253, 237, 33));
					userStateList.get(i).setBackground(new Color(253, 237, 33));
					break;
				default:
					//PanelList.get(i).setBackground(new Color(255, 255, 255));
					userStateList.get(i).setBackground(new Color(255, 255, 255));
					break;
			}
			userStateList.get(i).setVisible(true);

		}
		
	}
	
	private void setAtdRate(JLabel lbl) {
		int atd=0;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getNowState().equals("0000")||users.get(i).getNowState().equals("0001")) 
				atd++;
			}
		lbl.setText("当前出勤："+atd+"/"+users.size());
	}

	
	public void setAtt(String attTime,String leaveTime) {
		this.attTime=attTime;
		this.leaveTime=leaveTime;
	}
	
	private void newSATU() {
		SetAttTimeUI satu=new SetAttTimeUI(this);
		satu.setLocationRelativeTo(frame);
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
		frame.setTitle("什么都队考勤系统");
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 21, 209, 188);
		panel.setBackground(Color.CYAN);
		panel.setForeground(Color.WHITE);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblDate = new JLabel();
		lblDate.setText("Date");
		lblDate.setBounds(30, 55, 140, 21);
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblDate);
		
		JLabel lblTime = new JLabel();
		lblTime.setText("Time");
		lblTime.setBounds(30, 86, 67, 24);
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblTime);
		
		JLabel lblWeekday = new JLabel();
		lblWeekday.setText("Weekday");
		lblWeekday.setBounds(107, 86, 63, 24);
		lblWeekday.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblWeekday);
		
		timeSynchro.timeSync(lblDate, lblTime, lblWeekday);
		
		JLabel label_3 = new JLabel("当前出勤：");
		label_3.setBounds(0, 0, 0, 0);
		panel.add(label_3);
		
		JLabel lblNewLabel_1 = new JLabel("atdRate");
		lblNewLabel_1.setBounds(30, 136, 140, 15);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.CYAN);
		panel_3.setBounds(229, 21, 529, 464);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(new GridLayout(5, 4, 50, 50));
		
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
				newSATU();
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
		button_1.setBounds(111, 108, 93, 23);
		panel_2.add(button_1);
		
		JButton button_2 = new JButton("添加员工");
		button_2.setBounds(111, 39, 93, 23);
		panel_2.add(button_2);
		
		button_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createUserUI ui=new createUserUI();
				ui.setlocation(frame);
			}
		});
		
		JButton button_3 = new JButton("删除员工");
		button_3.setBounds(111, 75, 93, 23);
		panel_2.add(button_3);
		button_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteUserUI ui=new DeleteUserUI();
				ui.setlocation(frame);
			}
		});
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(778, 201, 183, 284);
		frame.getContentPane().add(panel_4);
		
		JLabel lblNewLabel = new JLabel("什么都队考勤管理系统");
		lblNewLabel.setBounds(841, 495, 120, 15);
		lblNewLabel.setForeground(Color.WHITE);
		frame.getContentPane().add(lblNewLabel);
		
	
		//ArrayList<JPanel>PanelList=new ArrayList<JPanel>();
		setUserStateList(panel_3);
		Timer timer=new Timer();
		TimerTask timerTask=new TimerTask() {
			
			@Override
			public void run() {
				users=sqlConnect.getUsers();
				refreshUserStateList();
				setAtdRate(lblNewLabel_1);
				
			}
		};
		timer.schedule(timerTask,0,10000);
		frame.setVisible(true);
	}
}

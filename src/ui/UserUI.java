package ui;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.awt.event.ActionEvent;

import util.*;
import java.awt.GridLayout;
import java.util.Timer;


public class UserUI {

	private JFrame frame;
	private TimeSynchro timeSynchro=new TimeSynchro();//时间同步
	private SqlConnect sqlConnect=null;
	//private ArrayList<JLabel>userStateList=new ArrayList<JLabel>();//员工状态的图形化
	private ArrayList<Users>users;//员工链表
	//private ArrayList<SqlConnect>sqlList=new ArrayList<SqlConnect>();
	
	private String attTime;
	private String leaveTime;//打卡时间

	private JTable table;
	private String [][]tableData;
	private final static String[]columnNames=new String[] {"ID","姓名","考勤状态","上班时间","下班时间"};
	private JScrollPane panel_3;
	private SimpleDateFormat sdf;
	private Map<String,String>mapState=new HashMap<>();

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
		sqlConnect=new SqlConnect();//进行sql连接
		/*for (int i = 0; i < 20; i++) {
			sqlList.add(new SqlConnect());
		}*/
		users=sqlConnect.getUsers();//获取员工数据
		attTime="0900";
		leaveTime="1700";//默认上下班时间
		sdf=new SimpleDateFormat("yyyyMMdd");
		setStateMap();
		initialize();//ui实现
	}

	private void setStateMap(){
		/*mapColor.put("0000",new Color(18, 253, 27));
		mapColor.put("1000",new Color(129, 137, 253));
		mapColor.put("0100",new Color(93, 255, 248));
		mapColor.put("0010",new Color(254, 61, 66));
		mapColor.put("0001",new Color(249, 246, 89));*/
		mapState.put("0000","<html><font color='green'>正常出勤</font></html>");
		mapState.put("1000","<html><font color='blue'>请假</font></html>");
		mapState.put("0100","<html><font color='cyan'>出差</font></html>");
		mapState.put("0010","<html><font color='red'>旷工</font></html>");
		mapState.put("0001","<html><font color='yellow'>迟到/早退</font></html>");
	}//设置员工状态显示的字体颜色

	private void setTableData(){
		panel_3=new JScrollPane();
		//JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(192, 195, 255));
		panel_3.setBounds(229, 21, 529, 464);
		frame.getContentPane().add(panel_3);
		tableData=new String[users.size()][5];
		for (int i=0;i<users.size();i++){
			tableData[i][0]=String.valueOf(i+1);
			tableData[i][1]=users.get(i).getName();
			if (mapState.containsKey(users.get(i).getNowState()))
				tableData[i][2]=mapState.get(users.get(i).getNowState());
			else tableData[i][2]="<html><font color='black'>状态不明</font></html>";
			tableData[i][3]=users.get(i).getmAttTime();
			tableData[i][4]=users.get(i).getmLeaveTime();
		}
		table=new JTable(tableData,columnNames);
		table.setBackground(Color.WHITE);
		panel_3.setViewportView(table);
		table.setVisible(true);
		panel_3.setVisible(true);
	}//设定table数据

	private void reset() {
		frame.remove(panel_3);
		users=new ArrayList<>();
		users=sqlConnect.getUsers();
		setTableData();
	}//刷新table数据
	

	
	private void setAtdRate(JLabel lbl) {
		int atd=0;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getNowState().equals("0000")||users.get(i).getNowState().equals("0001")) 
				atd++;
			}
		lbl.setText("当前出勤："+atd+"/"+users.size());
	}//显示出勤人数比

	
	public void setAtt(String attTime,String leaveTime) {
		this.attTime=attTime;
		this.leaveTime=leaveTime;
	}//设置打卡时间
	
	private void newSATU() {
		SetAttTimeUI satu=new SetAttTimeUI(this);
		satu.setLocationRelativeTo(frame);
	}//打卡时间设置界面打开
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		DateCheck dc=new DateCheck();
		dc.Check();
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(135, 206, 250));
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setBounds(100, 100, 1000, 551);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setTitle("什么都队考勤系统");//主界面信息设置
		
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
		
		timeSynchro.timeSync(lblDate, lblTime, lblWeekday);//以上label与该函数共同实现时间同步刷新
		
		JLabel label_3 = new JLabel("当前出勤：");
		label_3.setBounds(0, 0, 0, 0);
		panel.add(label_3);
		
		JLabel lblNewLabel_1 = new JLabel("atdRate");
		lblNewLabel_1.setBounds(30, 136, 140, 15);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);//出勤人数比显示

		setTableData();
		
		JButton btnNewButton_1 = new JButton("考勤总体日报");
		btnNewButton_1.setBounds(778, 21, 183, 66);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(e -> {
			DayUI dayUI=new DayUI(sqlConnect);
			dayUI.setLocation(frame);
		});
		
		JButton btnNewButton_2 = new JButton("<html>"+"考勤总"+"<br>"+"体月报"+"</html>");//该button实现显示考勤月报功能
		btnNewButton_2.setBounds(778, 109, 98, 82);
		frame.getContentPane().add(btnNewButton_2);
		btnNewButton_2.addActionListener(e -> {
			MonthUI mUi=new MonthUI(8);
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 224, 209, 261);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton = new JButton("<html>"+"打卡"+"<br>"+"设定"+"</html>");//该button实现修改打卡规则等的功能
		btnNewButton.addActionListener(e -> newSATU());
		btnNewButton.setBounds(10, 24, 81, 93);
		panel_2.add(btnNewButton);
		
		JButton button = new JButton("<html>"+"请假"+"<br>"+"审批"+"</html>");//该button实现关于员工请假的审批功能，目前未实现
		button.setBounds(10, 138, 81, 93);
		panel_2.add(button);
		
		JButton button_2 = new JButton("<html>"+"添加"+"<br>"+"员工"+"</html>");//添加员工界面
		button_2.setBounds(111, 24, 88, 93);
		panel_2.add(button_2);
		
		button_2.addActionListener(e -> {
			createUserUI ui=new createUserUI();
			ui.setlocation(frame);
		});
		
		JButton button_3 = new JButton("<html>"+"删除"+"<br>"+"员工"+"</html>");//删除员工界面
		button_3.setBounds(111, 138, 88, 93);
		panel_2.add(button_3);
		button_3.addActionListener(e -> {
			DeleteUserUI ui=new DeleteUserUI();
			ui.setlocation(frame);
		});
		
		JPanel panel_4 = new JPanel();//不知道现在有啥用，可能剔除
		panel_4.setBounds(778, 201, 183, 284);
		frame.getContentPane().add(panel_4);
		
		JLabel lblNewLabel = new JLabel("什么都队考勤管理系统");
		lblNewLabel.setBounds(841, 495, 120, 15);
		lblNewLabel.setForeground(Color.WHITE);
		frame.getContentPane().add(lblNewLabel);
		

		Timer timer=new Timer();
		TimerTask timerTask=new TimerTask() {
			
			@Override
			public void run() {
				reset();
				setAtdRate(lblNewLabel_1);
				
			}
		};
		timer.schedule(timerTask,0,10000);//每10秒一次刷新员工状态
		frame.setVisible(true);
	}
}

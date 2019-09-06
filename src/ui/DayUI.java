package ui;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.event.ListDataListener;

import util.SqlConnect;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Array;
import java.sql.ResultSet;

import java.awt.FlowLayout;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.*;
import java.util.Date;
import java.util.Calendar;

import util.checkUI;

import org.jdesktop.swingx.JXDatePicker;



public class DayUI {

	private JFrame frame;
	private JTable table;
	private SqlConnect sConnect;
	private String sql;
	private ResultSet res;
	private String[][] tableData;
	private Date date;
	private SimpleDateFormat sdf;
	private String dateStr;
	private ArrayList<String> users;
	private String[] columnNames;
	private JScrollPane scrollPane;




	/**
	 * Create the application.
	 */
	public DayUI(SqlConnect sqlConnect) {
		sConnect=sqlConnect;
		sdf=new SimpleDateFormat("yyyyMMdd");
		columnNames=new String[] {"ID","姓名","考勤状态","上班时间","下班时间"};
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 655, 440);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("考勤日报");

		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 38, 624, 353);
		frame.getContentPane().add(scrollPane);
		
		JXDatePicker datePicker=new JXDatePicker();
		datePicker.setBounds(5, 11, 150, 24);
		frame.getContentPane().add(datePicker);
		



		JButton button = new JButton("查询");//按钮功能实现 显示对应combobox组成日期的考勤情况
		button.setBounds(193, 13, 57, 19);
		frame.getContentPane().add(button);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					date=datePicker.getDate();
					dateStr=sdf.format(date);
					sql="select * from employee";
					res=sConnect.getSqlRunningRes(sql);
					users=new ArrayList<>();
					while (res.next()){
						users.add(res.getString("name"));
					}
					tableData=new String[users.size()][5];
					date=datePicker.getDate();
					dateStr=sdf.format(date);
					sql=String.format("select * from %sattendance where date='%s'",dateStr.substring(0,6),dateStr);
					res=sConnect.getSqlRunningRes(sql);
					String state;
					while (res.next()){
						for (int i=0;i<users.size();i++){
							tableData[i][0]=String.valueOf(i+1);
							tableData[i][1]=users.get(i);
							state=res.getString(users.get(i));
							tableData[i][2]=state.substring(8,12);
							tableData[i][3]=state.substring(0,4);
							tableData[i][4]=state.substring(4,8);
						}
					}
					reset();

					/*Date datecheck=datePicker.getDate();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
					String d=sdf.format(datecheck);
					checkUI cUi=new checkUI(d);*/
				} catch (Exception ex) {
					checkUI cUI=new checkUI("查找失败！！！");
					cUI.setLocationRelativeTo(frame);
				}
			}
		});

		frame.setVisible(true);
		frame.setResizable(false);
	}

	public void setLocation(JFrame fr){
		frame.setLocationRelativeTo(fr);
	}

	public void reset(){
		frame.remove(scrollPane);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 38, 624, 353);
		frame.getContentPane().add(scrollPane);
		table = new JTable(tableData,columnNames);
		table.setVisible(true);
		scrollPane.setViewportView(table);
		scrollPane.setVisible(true);
	}



}

package ui;

import util.SqlConnect;
import util.Users;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MonthUI extends JFrame {

	private JFrame frame;
	private SqlConnect sqlConnect;

	//默认表格模型
	private DefaultTableModel model;
	private JTable table =null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonthUI window = new MonthUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MonthUI() {

		super("monthUI");
		sqlConnect = new SqlConnect();
		String[][] datas ={};
		String[] titles = {"姓名","班次","01","02","03","04","05","06","07","08","09","10","11","12","13"
				,"14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29"
				,"30","31","请假","出差","旷工","迟/早"};
		model = new DefaultTableModel(datas,titles);
		table = new JTable(model);
		add(new JScrollPane(table));
		setSize(1400,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//8月表格填充
		fillInTable(8);
		setVisible(true);
	}

	//填充表格
	public void fillInTable(int month){
		ArrayList<Users> list = sqlConnect.getUsers();

		//四种状态计数数组，12个月，list.size()个人
		int[][] vacateCount = new int[list.size()+1][12];//请假
		int[][] outCount = new int[list.size()+1][12];//出差
		int[][] absentCount = new int[list.size()+1][12];//旷工
		int[][] lateOrEarlyCount = new int[list.size()+1][12];//迟到/早退

		//数组初始化
		for(int i = 0;i<list.size();i++){
			for(int j = 0;j<12;j++){
				vacateCount[i][j] = 0;
				outCount[i][j] = 0;
				absentCount[i][j] = 0;
				lateOrEarlyCount[i][j] = 0;
			}
		}

		Object[] o = {""};
		model.addRow(o);
		model.addColumn("",o);

		//遍历userList
		for(int i = 0;i<list.size();i++) {

			for (int row = 0; row < table.getRowCount(); row++) {

				//该姓名已被创建
				if (table.getValueAt(row, 0).toString() == list.get(i).getName()) {
					//获得上班行号，填充表格
					int flag = table.getRowCount();
					setCell(list.get(i), table.getRowCount() -1 );
				}
				//该姓名未被创建
				if (row == table.getRowCount()-1  && table.getValueAt(row, 0).toString() != list.get(i).getName()) {
					//新建两行并获得下班行号，填充表格
					Object[] objects_1 = {list.get(i).getName(), "上班时间"};
					model.addRow(objects_1);
					Object[] objects_2 = {list.get(i).getName(), "下班时间"};
					model.addRow(objects_2);
					setCell(list.get(i), table.getRowCount()-1);
				}
			}

			//统计状态数
			{
				int[] arr = new int[4];
				//把String型状态转换为状态数组
				for (int a = 0; a < 4; a++) {
					arr[a] = Integer.parseInt(list.get(i).getNowState().substring(a, a + 1));//substring是找出包含起始位置，不包含结束位置，到结束位置的前一位的子串
				}

				//i:数组下标，user的ID.w
				if (arr[0] == 1) {
					vacateCount[i][Integer.valueOf(list.get(i).getMonth()) - 1]++;
				}
				if (arr[1] == 1) {
					outCount[i][Integer.valueOf(list.get(i).getMonth()) - 1]++;
				}
				if (arr[2] == 1) {
					absentCount[i][Integer.valueOf(list.get(i).getMonth()) - 1]++;
				}
				if (arr[3] == 1) {
					lateOrEarlyCount[i][Integer.valueOf(list.get(i).getMonth()) - 1]++;
				}
			}
		}

		//将状态填充到表格中
		//遍历userList
		for(int i = 0;i<list.size();i++){

			//遍历数组
			for(int arrayCount = 0;arrayCount<list.size()+1;arrayCount++){

				//用户ID等于数组下标
				if(list.get(i).getID()==arrayCount){
					//检索表格行名

					for(int j = 0;j<table.getRowCount();j++){

						//如果数组中id对应的姓名，就是userList对应的姓名，与表格姓名相同
						if(list.get(i).getName()==table.getValueAt(j,0)){

							//将数组中数值填进表格
							model.setValueAt(vacateCount[arrayCount][month],j,33);
							model.setValueAt(outCount[arrayCount][month],j,34);
							model.setValueAt(absentCount[arrayCount][month],j,35);
							model.setValueAt(lateOrEarlyCount[arrayCount][month],j,36);

						}
					}//结束表格遍历

				}
			}//结束数组遍历

		}//结束userList遍历

	}

	//根据user的name和day,填充上班时间下班时间，参数为用户信息与下班行号
	public void setCell(Users user,int row){
		//填充上班时间
		model.setValueAt(user.getmAttTime(),row-1,Integer.parseInt(user.getDate())+1);
		//填充下班时间
		model.setValueAt(user.getmLeaveTime(),row,Integer.parseInt(user.getDate())+1);
}
}

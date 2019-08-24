package util;

import javax.swing.JLabel;
import java.util.*;

public class employeeState {
	
	public void getEmployeeState(int empID,JLabel label) {
		
	}//获取员工状态

	public void refreshEmployeeState(int empID,JLabel label) {
		Timer timer=new Timer();
		TimerTask resTask=new TimerTask() {
			
			@Override
			public void run() {
				
				
			}
		};
		timer.schedule(resTask, 0, 1000);
	}//刷新员工状态
}

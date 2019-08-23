package util;
import java.util.*;
import java.util.Timer;

import javax.swing.*; 

public class TimeSynchro {
	
	public void setDate(JTextArea ta) {
		Calendar cal=Calendar.getInstance();
		int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        month+=1;
        int day=cal.get(Calendar.DATE);
        String Date = year + "年" + (month<10?"0"+month:month) + "月" + (day<10?"0"+day:day) + "日";
        ta.setText(Date);
	}
	
	public void setTime(JTextArea ta) {
		Calendar cal=Calendar.getInstance();
        int hour=cal.get(Calendar.HOUR_OF_DAY);
        int minute=cal.get(Calendar.MINUTE);
        int second=cal.get(Calendar.SECOND);
        String Time=(hour<10?"0"+hour:hour)+":"+(minute<10?"0"+minute:minute)+":"+(second<10?"0"+second:second);
        ta.setText(Time);
	}
	
	public void setDayOfWeek(JTextArea ta) {
		Calendar cal=Calendar.getInstance();
		int DOW=cal.get(Calendar.DAY_OF_WEEK);
		String dayOfWeek = null;
		switch (DOW) {
		case 1:
			dayOfWeek="星期日";
			break ;
		case 2:
			dayOfWeek="星期一";
			break ;
		case 3:
			dayOfWeek="星期二";
			break ;
		case 4:
			dayOfWeek="星期三";
			break ;
		case 5:
			dayOfWeek="星期四";
			break ;
		case 6:
			dayOfWeek="星期五";
			break ;
		case 7:
			dayOfWeek="星期六";
			break ;

		default:
		}
		ta.setText(dayOfWeek);
	}
	
	public void timeSync(JTextArea ta1,JTextArea ta2,JTextArea ta3) {
		Timer timer=new Timer();
		TimerTask timeSyncTask=new TimerTask() {
			
			@Override
			public void run() {
				setDate(ta1);
				setTime(ta2);
				setDayOfWeek(ta3);
				
			}
		};
		timer.schedule(timeSyncTask, 0, 1000);
	}
	
	
}

package util;
import java.util.*;
import java.util.Timer;

import javax.swing.*; 

public class TimeSynchro {
	
	public void setDate(JLabel l) {
		Calendar cal=Calendar.getInstance();
		int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        month+=1;
        int day=cal.get(Calendar.DATE);
        String Date = year + "年" + (month<10?"0"+month:month) + "月" + (day<10?"0"+day:day) + "日";
        l.setText(Date);//设置日期
	}
	
	public void setTime(JLabel l) {
		Calendar cal=Calendar.getInstance();
        int hour=cal.get(Calendar.HOUR_OF_DAY);
        int minute=cal.get(Calendar.MINUTE);
        int second=cal.get(Calendar.SECOND);
        String Time=(hour<10?"0"+hour:hour)+":"+(minute<10?"0"+minute:minute)+":"+(second<10?"0"+second:second);
        l.setText(Time);//设置时间
	}
	
	public void setDayOfWeek(JLabel l) {
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
		l.setText(dayOfWeek);//设置星期
	}
	
	public void timeSync(JLabel l1,JLabel l2,JLabel l3) {
		Timer timer=new Timer();
		TimerTask timeSyncTask=new TimerTask() {
			
			@Override
			public void run() {
				setDate(l1);
				setTime(l2);
				setDayOfWeek(l3);
				
			}
		};
		timer.schedule(timeSyncTask, 0, 1000);//每秒同步更新时间
	}
	
	
}

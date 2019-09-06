package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static java.util.Calendar.*;

public class DateCheck {
    private SqlConnect sConnect=null;
    private int Yesterday=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    private int Today=0;
    private ArrayList<Users> uList=new ArrayList<Users>();
    public DateCheck(){
        sConnect=new SqlConnect();
        uList=sConnect.getUsers();
    }

    public void Check(){
        Today=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        //Timer timer1=new Timer();
        Timer timer2=new Timer();
        /*TimerTask timerTask1=new TimerTask() {
			
			@Override
			public void run() {
				if (NowTime!=LastTime&&NowTime==0) {
					setNewState();
				}
				LastTime=NowTime;
				NowTime=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			}
		};*/
		
		TimerTask timertask2=new TimerTask() {
			
			@Override
			public void run() {
				if (Today!=Yesterday&&Today==1) {
					createNewTable();
					Yesterday=Today;
					Today=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
				}
			}
		};
		//timer1.schedule(timerTask1,0,10000);
		timer2.schedule(timertask2,0,86400000);

    }
    
    /*public void setNewState() {
		int year= getInstance().get(YEAR);
		int month= getInstance().get(MONTH)+1;
		int date= getInstance().get(DAY_OF_MONTH);
		String sql="insert into "+year+(month<10?"0"+month:month)+"attendance values("+year+(month<10?"0"+month:month)+",";
		for (int i = 0; i < uList.size(); i++) {
			sql=sql+"'999999999999',";
		}
		sql=sql+")";
		sConnect.runSqlStmt(sql);
	}*/
    
    public void createNewTable() {
    	int year= getInstance().get(YEAR);
		int month= getInstance().get(MONTH)+1;
		String monthOfYear=String.valueOf(year)+(month<10?"0"+month:month);
		uList=sConnect.getUsers();
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.DATE,1);
		calendar.roll(Calendar.DATE, -1);
		int daysOfMonth=calendar.get(Calendar.DATE);
		String sql=String.format("create table if not exist %sattendance (date varchar(8)",monthOfYear);
		
		
		for (int i = 0; i < uList.size(); i++) {
			sql=sql+","+uList.get(i).getName()+" varchar(12) not null default '99999999'";
		}
		
		sql=sql+")default charset=utf8";
		sConnect.runSqlStmt(sql);
		for (int i = 1; i <= daysOfMonth; i++) {
			sql=String.format("insert into %sattentance(date) values (%s)",monthOfYear,monthOfYear+(i<10?"0"+i:i));
			sConnect.runSqlStmt(sql);
		}
	}
}

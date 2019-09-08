package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static java.util.Calendar.*;
//实现对每月考勤表在数据库中的建立
public class DateCheck {
    private SqlConnect sConnect=null;
    private int Today=0;
    private ArrayList<Users> uList=new ArrayList<Users>();
    public DateCheck(){
        sConnect=new SqlConnect();
        uList=sConnect.getUsers();
    }

    public void Check(){
        Today=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);//获取今天
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
				if (Today==1) {//如果今天是月初则新建立这个月的表
					createNewTable();
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
    
    public void createNewTable() {//建立表
    	int year= getInstance().get(YEAR);
		int month= getInstance().get(MONTH)+1;
		String monthOfYear=String.valueOf(year)+(month<10?"0"+month:month);
		uList=sConnect.getUsers();
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.DATE,1);
		calendar.roll(Calendar.DATE, -1);
		int daysOfMonth=calendar.get(Calendar.DATE);
		String sql=String.format("create table if not exist %sattendance (date varchar(8)",monthOfYear);//若无则创建
		
		
		for (int i = 0; i < uList.size(); i++) {
			sql=sql+","+uList.get(i).getName()+" varchar(12) not null default '99999999'";
		}
		sql=sql+")default charset=utf8";
		sConnect.runSqlStmt(sql);//以上用于设定新表

		for (int i = 1; i <= daysOfMonth; i++) {
			sql=String.format("insert into %sattentance(date) values (%s)",monthOfYear,monthOfYear+(i<10?"0"+i:i));
			sConnect.runSqlStmt(sql);
		}//对新表建立每天的数据
	}
}

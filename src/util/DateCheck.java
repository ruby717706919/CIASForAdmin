package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class DateCheck {
    private SqlConnect sConnect=null;
    private int LastTime=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    private int NowTime=0;
    private int Yesterday=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    private int Today=0;
    public DateCheck(){
        sConnect=new SqlConnect();
    }

    public void Check(){
        NowTime=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        Today=Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        Timer timer1=new Timer();
        Timer timer2=new Timer();
        TimerTask timerTask1=new TimerTask() {
			
			@Override
			public void run() {
				if (NowTime!=LastTime&&NowTime==0) {
					setNewState();
				}
				LastTime=NowTime;
				NowTime=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			}
		};
		
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
		timer1.schedule(timerTask1,0,10000);
		timer2.schedule(timertask2,0,86400000);
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdf.format(date);

    }
    
    public void setNewState() {
		ArrayList<Users> uList=sConnect.getUsers();
		Date date=new Date();
		String sql="insert into employee values("+date+",";
		for (int i = 0; i < uList.size(); i++) {
			sql=sql+uList.get(i).getName()+",";
		}
		sql=sql+")";
		sConnect.runSqlStmt(sql);
	}
    
    public void createNewTable() {
		String sql="create table "+Calendar.getInstance().get(Calendar.YEAR)+
				(Calendar.getInstance().get(Calendar.MONTH)+1)+"(Date date";
		ArrayList<Users> uList=sConnect.getUsers();
		for (int i = 0; i < uList.size(); i++) {
			sql=sql+","+uList.get(i).getName()+" varchar(12)";
		}
		sql=sql+")default charset=utf8";
		sConnect.runSqlStmt(sql);
	}
}

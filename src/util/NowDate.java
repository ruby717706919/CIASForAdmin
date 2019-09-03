package util;

import java.util.Calendar;

public class NowDate {
	public String  getNowDate() {
		Calendar calendar=Calendar.getInstance();
		int year=calendar.get(Calendar.YEAR);
		int month=calendar.get(Calendar.MONTH)+1;
		int date=calendar.get(Calendar.DAY_OF_MONTH);
		String day=String.format("%s",year);
		day=day+(month<10?"0"+month:month)+(date<10?"0"+date:date);
		return day;
	}

	public String NowMonth(){
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;
        String M=String.format("%s",year);
        M=M+(month<10?"0"+month:month);
        return M;
    }
}

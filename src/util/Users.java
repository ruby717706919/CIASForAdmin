package util;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import static java.util.Calendar.getInstance;

public class Users {
	private String name;
	private String password;
	private int id;
	private SqlConnect sConnect;
	private String nowState;

	private String mAttTime;
	private String mLeaveTime;
	private String today;
	public Users(String Name,String Password,SqlConnect sConnect) {
		this.sConnect=sConnect;
		name=Name;
		password=Password;
		this.sConnect.createUser(name,password);
		id=sConnect.getID(name);
	}

	public Users(int ID,SqlConnect sConnect) {
		int year= getInstance().get(YEAR);
		int month= getInstance().get(MONTH)+1;
		int date= getInstance().get(DAY_OF_MONTH);
		today=String.valueOf(year)+(month<10?"0"+month:month)+(date<10?"0"+date:date);
		this.sConnect=sConnect;
		id=ID;
		name=this.sConnect.getName(id);
		password=this.sConnect.getPassword(ID);
		nowState=this.sConnect.getNowState(name);
		mAttTime=this.sConnect.getAttTime(name);
		mLeaveTime=this.sConnect.getLeaveTime(name);
	}

	public void setName(String Name) {
		name=Name;
	}

	public void setPassword(String Password) {
		password=Password;
	}

	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}

	public int getID() {
		return id;
	}

	public String getNowState(){
		return nowState;
	}

	public String getmAttTime(){
		return mAttTime;
	}

	public String getmLeaveTime(){
		return mLeaveTime;
	}

	public void setmAttTime(String attTime){
		sConnect.setAttTime(attTime,name);
	}

	public void setmLeaveTime(String leaveTime){
		sConnect.setLeaveTime(leaveTime,name);
	}

	public String getSomedayAttTime(String someday){
		return sConnect.getAttTime(name,someday);
	}

	public String getSomedayLeaveTime(String someday){
		return sConnect.getAttTime(name,someday);
	}

	public String getDate() {
		int[] arr = new int[today.length()];
		for (int i = 0; i < today.length(); i++) {
			arr[i] = Integer.parseInt(today.substring(i, i + 1));//substring是找出包含起始位置，不包含结束位置，到结束位置的前一位的子串
		}
		return String.valueOf(arr[6])+String.valueOf(arr[7]);
	}

	public String getMonth(){
		int[] arr = new int[today.length()];
		for (int i = 0; i < today.length(); i++) {
			arr[i] = Integer.parseInt(today.substring(i, i + 1));//substring是找出包含起始位置，不包含结束位置，到结束位置的前一位的子串
		}
		return String.valueOf(arr[4])+String.valueOf(arr[5]);
	}
}

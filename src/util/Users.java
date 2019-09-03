package util;

public class Users {
	private String name;
	private String password;
	private int id;
	private SqlConnect sConnect=null;
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

}

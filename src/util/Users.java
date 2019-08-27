package util;

public class Users {
	private String name;
	private String password;
	private int id;
	private String comeTime;
	private String leaveTime;
	private String state;
	SqlConnect sConnect=null;
	public Users(String Name,String Password) {
		sConnect=new SqlConnect();
		name=Name;
		password=Password;
		sConnect.createUser(name,password);
		id=sConnect.getID();
	}
	
	public Users() {
		sConnect=new SqlConnect();
		name=getName();
		password=getPassword();
		id=sConnect.getID();
		comeTime=getCT();
		leaveTime=getLT();
		state=getState();
	}
	
	public boolean setName(String Name) {
		name=Name;
		return sConnect.setName(id,Name);
	}
	
	public boolean setPassword(String Password) {
		password=Password;
		return sConnect.setPassword(id,Password);
	}
	
	public void setCT(String ct) {
		comeTime=ct;
		sConnect.setCT(name,ct);
	}
	
	public void setLT(String lt) {
		leaveTime=lt;
		sConnect.setLT(name,lt);
	}
	
	public void setState(String state) {
		this.state=state;
		sConnect.setState(name,state);
	}
	
	public String getName() {
		return sConnect.getName(id);
	}
	public String getPassword() {
		return sConnect.getPassword(id);
	}
	public int getID() {
		return sConnect.getID();
	}
	public String getCT() {
		return sConnect.getCT();
	}
	public String getLT() {
		return sConnect.getLT();
	}
	public String getState() {
		return sConnect.getState();
	}

}

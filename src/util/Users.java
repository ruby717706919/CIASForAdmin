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
		sConnect.setID();
		sConnect.setName(Name);
		sConnect.setPassword(Password);
	}
	
	public Users() {
		sConnect=new SqlConnect();
		
	}
	
	public void setName(String Name) {
		name=Name;
		sConnect.setName(Name);
	}
	
	public void setPassword(String Password) {
		password=Password;
		sConnect.setPassword(Password);
	}
	
	public void setCT(String ct) {
		comeTime=ct;
		sConnect.setCT(ct);
	}
	
	public void setLT(String lt) {
		leaveTime=lt;
		sConnect.setLT(lt);
	}
	
	public void setState(String state) {
		this.state=state;
		sConnect.setState(state);
	}
	
	public String getName() {
		return sConnect.getName();
	}
	public String getPassword() {
		return sConnect.getPassword();
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

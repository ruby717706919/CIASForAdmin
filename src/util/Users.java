package util;

public class Users {
	private String name;
	private String password;
	private int id;
	//private SqlConnect sConnect=null;
	private String nowState;
	public Users(String Name,String Password,SqlConnect sConnect) {
		//sConnect=new SqlConnect();
		name=Name;
		password=Password;
		sConnect.createUser(name,password);
		id=sConnect.getID(name);
		
		
	}
	
	public Users(int ID,SqlConnect sConnect) {
		//sConnect=new SqlConnect();
		id=ID;
		name=sConnect.getName(id);
		password=sConnect.getPassword(ID);
		nowState=sConnect.getNowState(name);
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

}

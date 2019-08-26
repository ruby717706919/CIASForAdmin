package util;


import java.sql.*;

//该类实现与数据库连接，暂未完成
public class SqlConnect {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://citeam.chinaeast.cloudapp.chinacloudapi.cn:3306/userdata?useSSL=false&serverTimezone=UTC";
    static final String USERNAME = "citeam";
    static final String PASSWORD = "SEUshixunCIteam";
    private Connection conn = null;
	private Statement stmt =null;
	public SqlConnect() {
		try {
    		Class.forName(JDBC_DRIVER);
    		//System.out.println("connecting");
    		
    		conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
    		//System.out.println("实例化Statement");
    		
    	} catch(Exception e) {
    		System.out.println("连接失败！");
    		e.printStackTrace();
    	}
    	System.out.println("SUCCESS!");
	}
	
	public void setID() {
		
	}
	
	public void setID(int id) {
		
	}
	
	public void setName(String name) {
		
	}
	
	public void setPassword(String password) {
		
	}
	
	public void setCT(String ct) {
		
	}
	
	public void setLT(String lt) {
		
	}
	
	public void setState(String state) {
		
	}
	//以下功能均需要从数据库中读取数据返回，但未完成，先用""和0代替
	public String getName() {
		return "";
	}
	
	public String getPassword() {
		return "";
	}
	
	public int getID() {
		return 0;
	}
	
	public String getCT() {
		return "";
	}
	
	public String getLT() {
		return "";
	}
	
	public String getState() {
		return "";
	}
	
	
}

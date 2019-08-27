package util;


import javax.swing.*;
import java.sql.*;

//该类实现与数据库连接，暂未完成
public class SqlConnect {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://citeam.chinaeast.cloudapp.chinacloudapi.cn:3306/userdata?useSSL=false&serverTimezone=UTC";
    static final String USERNAME = "citeam";
    static final String PASSWORD = "SEUshixunCIteam";
    private Connection conn = null;
	private Statement stmt =null;
	private String sql=null;
	private ResultSet resultSet=null;
	public SqlConnect() {
		try {
    		Class.forName(JDBC_DRIVER);
    		//System.out.println("connecting");
    		
    		conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
    		//System.out.println("实例化Statement");
    		stmt=conn.createStatement();
    	} catch(Exception e) {
    		System.out.println("连接失败！");
    		e.printStackTrace();
    	}
    	System.out.println("SUCCESS!");
	}

	public void runSqlStmt(String sql){
		this.sql=sql;
		try {
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean adminLogin(JTextField tf1, JTextField tf2) {
	    String admin=tf1.getText();
	    String adminPassword=tf2.getText();
        try {
        	sql= "select password from admin where name='"+admin+"'";
            resultSet=stmt.executeQuery(sql);
            while (resultSet.next()) {
            	if (resultSet.getString("password").equals("0")||!resultSet.getString("password").equals(adminPassword))
                    return false;
            	else {
					return true;
				}
			}
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
	
	public boolean createUser(String name,String password) {
		sql="select id from emploee";
		int count = 0;
		try {
			stmt=conn.createStatement();
			resultSet=stmt.executeQuery(sql);
			while (resultSet.next()) {
				count=resultSet.getInt("id");
			}
			count++;
			sql="create into emploee value(count,name,password)";
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		
	}

	
	public boolean setName(int id,String name) {
		sql= String.format("update employee set name=%s where id=%d", name, id);
		try {
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean setPassword(int id,String password) {
		sql= String.format("update employee set password=%s where id=%d", password, id);
		try {
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void setCT(String name,String ct) {
		
	}
	
	public void setLT(String name,String lt) {
		
	}
	
	public void setState(String name,String state) {
		
	}
	//以下功能均需要从数据库中读取数据返回，但未完成，先用""和0代替
	public String getName(int id) {
		sql= String.format("select name from employee where id=%d", id);
		String name=null;
		try {
			resultSet=stmt.executeQuery(sql);
			name=resultSet.getString("name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	public String getPassword(int id) {
		sql= String.format("select name from employee where id=%d", id);
		String password=null;
		try {
			resultSet=stmt.executeQuery(sql);
			password=resultSet.getString("name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return password;
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

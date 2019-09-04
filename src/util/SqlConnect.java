package util;


import javax.swing.*;
import java.util.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.util.Calendar.*;

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
	
	private ArrayList<SqlConnect>sqllist=new ArrayList<SqlConnect>();
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
    	//System.out.println("SUCCESS!");
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
		sql="select id from employee";
		int count = 0;
		if (!name.equals(null)&&!password.equals(null)&&!name.equals("")&&!password.equals("")) {
			try {
				resultSet=stmt.executeQuery(sql);
				while (resultSet.next()) {
					count=resultSet.getInt("id");
				}
				count++;
				sql=String.format("insert into employee(id,name,password) values(%d,'%s','%s')",count,name,password);
				stmt.execute(sql);
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}else 
			System.out.print("???");
			return false;
		
	}
	
	
	public boolean deleteUser(String id) {
		if (id.equals(null)||id.equals("")) {
			return false;
		}
		int ID=Integer.parseInt(id);
		sql="delete from employee where id="+ID;
		try {
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<Users> getUsers() {
		ArrayList<Users> userslist=new ArrayList<Users>();
		
		try {
		    sql="select id from employee";
			resultSet=stmt.executeQuery(sql);
			int i=0;
			while (resultSet.next()) {
				//userslist.add(new Users(resultSet.getInt("id"),sqlConnect));
				sqllist.add(new SqlConnect());
			    userslist.add(new Users(resultSet.getInt("id"),sqllist.get(i)));
			    i++;
			    } 
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return userslist;
	}

	
	public boolean setName(int id,String name,Users user) {
		sql= String.format("update employee set name='%s' where id=%d", name, id);
		try {
			stmt.execute(sql);
			user.setName(name);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	
	public boolean setPassword(int id,String password,Users user) {
		sql= String.format("update employee set password=%s where id=%d", password, id);
		try {
			stmt.execute(sql);
			user.setPassword(password);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void setState(String name,String state) {
		
	}
	//以下功能均需要从数据库中读取数据返回，但未完成，先用""和0代替
	public String getName(int id) {
		sql= String.format("select name from employee where id=%d", id);
		String name=null;
		try {
			resultSet=stmt.executeQuery(sql);
			if (resultSet.next())
				name=resultSet.getString("name");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	public String getName(Users user) {
		return user.getName();
	}
	
	public String getPassword(int id) {
		sql= String.format("select password from employee where id=%d", id);
		String password=null;
		try {
			resultSet=stmt.executeQuery(sql);
			if (resultSet.next())
				password=resultSet.getString("password");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return password;
	}
	
	public String getaPassword(Users user) {
		return user.getPassword();
	}
	
	public int getID(String name) {
		sql="select id from emloyee where name='"+name+"'";
		try {
			resultSet=stmt.executeQuery(sql);
			while (resultSet.next())
				return resultSet.getInt("id");	

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}
	
	public int getID(Users user) {
		return user.getID();
	}

	public String getNowState(String name){
		int year= getInstance().get(YEAR);
		int month= getInstance().get(MONTH)+1;
		int date= getInstance().get(DAY_OF_MONTH);
		String monthOfYear=String.valueOf(year)+(month<10?"0"+month:month);
		String state=null;
		sql="select * from "+monthOfYear+"attendance where Date='"+monthOfYear+"'";
		/*sql="select * from "+year+"08"+
				(month<10?"0"+month:month)+
				"attendance where Date='20190828'";*/
		try {
			resultSet=stmt.executeQuery(sql);
			if (resultSet.next()) {
				state=resultSet.getString(name);
				return state.substring(8,12);
			}else {
				return "9999";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "9999";
	}
	
	public String getNowState(Users user) {
		return user.getNowState();
	}

	public String getAttTime(String name){
		int year= getInstance().get(YEAR);
		int month= getInstance().get(MONTH)+1;
		int date= getInstance().get(DAY_OF_MONTH);
		String monthOfYear=String.valueOf(year)+(month<10?"0"+month:month);
		sql="select * from "+monthOfYear+"attendance where Date='"+monthOfYear+"'";
		/*sql="select * from "+year+"08"+
				//(month<10?"0"+month:month)+
				"attendance where Date='20190828'";*/
		String state=null;
		try {
			resultSet=stmt.executeQuery(sql);
			if (resultSet.next()) {
				state=resultSet.getString(name);
				return state.substring(0,4);
			}else {
				return "9999";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "9999";
		}
	}

	public String getAttTime(String name,String someday){
	    sql=String.format("select '%s' from %s where date='%s'",name,someday.substring(0,6),someday);
	    String state=null;
        try {
            if (resultSet.next()){
                resultSet=stmt.executeQuery(sql);
                state=resultSet.getString(name);
                return state.substring(0,4);
            }else
                return  "9999";

        } catch (SQLException e) {
            e.printStackTrace();
            return "9999";
        }
    }

	public String getLeaveTime(String name){
		int year= getInstance().get(YEAR);
		int month= getInstance().get(MONTH)+1;
		int date= getInstance().get(DAY_OF_MONTH);
		String monthOfYear=String.valueOf(year)+(month<10?"0"+month:month);
		sql="select * from "+monthOfYear+"attendance where Date='"+monthOfYear+"'";
		/*sql="select * from "+year+"08"+
				//(month<10?"0"+month:month)+
				"attendance where Date='20190828'";*/
		String state=null;
		try {
			resultSet=stmt.executeQuery(sql);
			if (resultSet.next()) {
				state=resultSet.getString(name);
				return state.substring(4,8);
			}else {
				return "9999";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "9999";
	}

	public String getLeaveTime(String name,String someday){
        sql=String.format("select '%s' from %s where date='%s'",name,someday.substring(0,6),someday);
        String state=null;
        try {
            if (resultSet.next()){
                resultSet=stmt.executeQuery(sql);
                state=resultSet.getString(name);
                return state.substring(4,8);
            }else
                return  "9999";

        } catch (SQLException e) {
            e.printStackTrace();
            return "9999";
        }
    }
	//只有当天，指定任意时间的获取未完成

	public void setAttTime(String name,String attTime){
		int year= getInstance().get(YEAR);
		int month= getInstance().get(MONTH)+1;
		int date= getInstance().get(DAY_OF_MONTH);
		String monthOfYear=String.valueOf(year)+(month<10?"0"+month:month);
		sql="select * from "+monthOfYear+"attendance where Date='"+monthOfYear+"'";
		/*sql="select * from "+year+"08"+
				//(month<10?"0"+month:month)+
				"attendance where Date='20190828'";*/
		String state=null;
		try {
			resultSet=stmt.executeQuery(sql);
			if (resultSet.next()) {
				state = resultSet.getString(name);
				state = state.substring(4, 12);
				state = attTime+state;
				sql="update "+year+"08"+
						//(month<10?"0"+month:month)+
						"attendance set where name='"+name+"' where Date='20190828'";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setLeaveTime(String name,String leaveTime){
		int year= getInstance().get(YEAR);
		int month= getInstance().get(MONTH)+1;
		int date= getInstance().get(DAY_OF_MONTH);
		String monthOfYear=String.valueOf(year)+(month<10?"0"+month:month);
		sql="select * from "+monthOfYear+"attendance where Date='"+monthOfYear+"'";
		/*sql="select * from "+year+"08"+
				//(month<10?"0"+month:month)+
				"attendance where Date='20190828'";*/
		String state=null;
		try {
			resultSet=stmt.executeQuery(sql);
			if (resultSet.next()) {
				state = resultSet.getString(name);
				state = state.substring(0, 4)+leaveTime+state.substring(8,12);
				sql="update "+year+"08"+
						//(month<10?"0"+month:month)+
						"attendance set where name='"+name+"' where Date='20190828'";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//同样只有当天

	public void setAttTime(String name,String attTime,String someday){

	}
	public void setLeaveTime(String name,String leaveTime,String someday){

	}
}

package util;

import java.util.HashMap;
import java.util.Map;

//该类实现与数据库连接，暂未完成
public class SqlConnect {

	private Map<String, String> map=new HashMap<String, String>();//存储账号与密码的配对
	
	public static void main(String[] args) {
		

	}//进行数据库连接
	
	public Map<String, String> getMap(){
		return map;
	}

}

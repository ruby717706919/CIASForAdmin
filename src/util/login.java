package util;

import java.util.HashMap;
import java.util.Map;

import javax.swing.*;


public class login {
	private Map<String, String> mp=new HashMap<String, String>();
	public login() {
		mp.put("管理员", "20190824");
	}
	public boolean log_in(JTextField tf,JPasswordField pf) {
		/*String username=tf.getText();
		String password=String.valueOf(pf.getPassword());
		if (mp.get(username)!=null&&mp.get(username).equals(password)) {
			return true;
		} else {
			return false;
		}*/
		return true;
		//此处应有查阅数据库对比用户名与密码的功能实现代码
		//暂未能码出来
		//查阅完成会返回一个boolean值，匹配为true，不匹配为false
		//boolean isMatch=true;//声明，此时默认为true保证不报错，用于存储登录与否,此值直接作为该函数返回值
		//return map.get(username).equals(password);
	}

}

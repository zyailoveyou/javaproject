package user;

import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import ojdbc.DataBaseOperation;

public class user {
	
	
	String checkname;
	String usernameString;
	String passwordString;
	String departmentString;
	String id;
	
		
	public String getCheckname() {
		return checkname;
	}


	public String getUsernameString() {
		return usernameString;
	}


	public String getPasswordString() {
		return passwordString;
	}


	public String getDepartmentString() {
		return departmentString;
	}
	
	
	public String getId() {
		return id;
	}


	private user(String checkname,String usernameString,String passwordString,String id,String departmentString)
	{		
		this.checkname = checkname;
		this.usernameString = usernameString;
		this.passwordString = passwordString;
		this.departmentString = departmentString;
		this.id = id;
	}
	
	
	public static user GetUserData(String usernameString,String passwordString)	
	{
			return null;
	
	}
	
		
	public static boolean RegisterUser(String checkname,String usernameString,String passwordString,String departmentString) throws ClassNotFoundException, SQLException
	{
		
		DataBaseOperation testBaseOperation = new DataBaseOperation();
			
		int id = testBaseOperation.GetID_from_Nanme(checkname);		
		return testBaseOperation.InsertIntoOneLine_DATA_ACCOUNT(usernameString, passwordString, checkname, id);
				
	}
	
	
	
	public static Map<user, String> LoginInUser (String usernameString,String passwordString) throws ClassNotFoundException, SQLException 	
	{
		
		
		if (usernameString.equals("")||passwordString.equals("")) {
			user returnUser = new user("-1", "-1", "-1", "-1","-1");
			Map<user, String> resultmap = new HashMap<user, String>();		
			resultmap.put(returnUser, "帐号或者密码不能为空");
			return resultmap;
		}
		
		DataBaseOperation testBaseOperation = new DataBaseOperation();		
		Map<ArrayList<String>, String> resultString = testBaseOperation.SelectOneLine_DATA_ACCOUNT(usernameString, passwordString);
				
		Set<Map.Entry<ArrayList<String>, String>> set = resultString.entrySet();					
		Iterator<Map.Entry<ArrayList<String>, String>> iterator = set.iterator();
				
		String logininreturnString = null;
		ArrayList<String> resultArrayList = null; 
		
		while (iterator.hasNext()) {
			
			Map.Entry<ArrayList<String>, String> returnresult= (Map.Entry<ArrayList<String>, String>) iterator.next();
            resultArrayList = returnresult.getKey();
            logininreturnString = returnresult.getValue();
					
		}
						
		if (logininreturnString.equals("成功登录")) {
			
			user returnUser = new user(resultArrayList.get(0), resultArrayList.get(1), resultArrayList.get(2), resultArrayList.get(3),resultArrayList.get(4));
			Map<user, String> resultmap = new HashMap<user, String>();			
			resultmap.put(returnUser, "成功登录");
			return resultmap;
		}
		
		else if (logininreturnString.equals("数据库中没有读取到任何帐号数据，请联系管理员")) {
			
			user returnUser = new user("-1", "-1", "-1", "-1","-1");
			Map<user, String> resultmap = new HashMap<user, String>();			
			resultmap.put(returnUser, "数据库中没有读取到任何帐号数据，请联系管理员");			
			return resultmap;
		}
		
		else if (logininreturnString.equals("帐号或者密码错误")) {
			
			user returnUser = new user("-1", "-1", "-1", "-1","-1");
			Map<user, String> resultmap = new HashMap<user, String>();			
			resultmap.put(returnUser, "帐号或者密码错误");			
			return resultmap;
		}
		
		else if (logininreturnString.equals("数据库连接错误")) {
			
			user returnUser = new user("-1", "-1", "-1", "-1","-1");
			Map<user, String> resultmap = new HashMap<user, String>();		
			resultmap.put(returnUser, "数据库连接错误");			
			return resultmap;
		}
		
		return null;
								
	}
	
	
	private static void ShowDialog(String word) {
		
		JOptionPane.showMessageDialog(null,word, "错误提示", JOptionPane.ERROR_MESSAGE); 
				
	}
	

}

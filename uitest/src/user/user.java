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


	private user(String checkname,String usernameString,String passwordString,String departmentString)
	{		
		this.checkname = checkname;
		this.usernameString = usernameString;
		this.passwordString = passwordString;
		this.departmentString = departmentString;		
	}
	
	
	public static user GetUserData(String usernameString,String passwordString)	
	{
			return null;
	
	}
	
		
	public static boolean RegisterUser(String checkname,String usernameString,String passwordString,String departmentString) throws ClassNotFoundException, SQLException
	{
		
		DataBaseOperation testBaseOperation = new DataBaseOperation();
		
		int usernamelength = usernameString.length();
		int passwordlength = passwordString.length();
		
		if (checkname.equals("")) {
			ShowDialog("Ա����������Ϊ��");
			return false;
		}
		
		if (usernamelength<6) {
			
			ShowDialog("�û�������С��6λ");
			return false;
		}
		
		if (passwordlength<6) {
			ShowDialog("���볤��С��6λ");
			return false;
			
		}
		
		int id = testBaseOperation.GetID_from_Nanme(checkname);
		
		return testBaseOperation.InsertIntoOneLine_DATA_ACCOUNT(usernameString, passwordString, checkname, id);
				
	}
	
	
	
	public static Map<user, String> LoginInUser (String usernameString,String passwordString) throws ClassNotFoundException, SQLException 	
	{
		
		
		if (usernameString.equals("")||passwordString.equals("")) {
			user returnUser = new user("-1", "-1", "-1", "-1");
			Map<user, String> resultmap = new HashMap<user, String>();		
			resultmap.put(returnUser, "�ʺŻ������벻��Ϊ��");
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
						
		if (logininreturnString.equals("�ɹ���¼")) {
			
			user returnUser = new user(resultArrayList.get(0), resultArrayList.get(1), resultArrayList.get(2), resultArrayList.get(3));
			Map<user, String> resultmap = new HashMap<user, String>();			
			resultmap.put(returnUser, "�ɹ���¼");
			return resultmap;
		}
		
		else if (logininreturnString.equals("���ݿ���û�ж�ȡ���κ��ʺ����ݣ�����ϵ����Ա")) {
			
			user returnUser = new user("-1", "-1", "-1", "-1");
			Map<user, String> resultmap = new HashMap<user, String>();			
			resultmap.put(returnUser, "���ݿ���û�ж�ȡ���κ��ʺ����ݣ�����ϵ����Ա");			
			return resultmap;
		}
		
		else if (logininreturnString.equals("�ʺŻ����������")) {
			
			user returnUser = new user("-1", "-1", "-1", "-1");
			Map<user, String> resultmap = new HashMap<user, String>();			
			resultmap.put(returnUser, "�ʺŻ����������");			
			return resultmap;
		}
		
		else if (logininreturnString.equals("���ݿ����Ӵ���")) {
			
			user returnUser = new user("-1", "-1", "-1", "-1");
			Map<user, String> resultmap = new HashMap<user, String>();		
			resultmap.put(returnUser, "���ݿ����Ӵ���");			
			return resultmap;
		}
		
		return null;
								
	}
	
	
	private static void ShowDialog(String word) {
		
		JOptionPane.showMessageDialog(null,word, "������ʾ", JOptionPane.ERROR_MESSAGE); 
				
	}
	

}
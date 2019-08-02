package ojdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;

import excel.wrtieExcel;
import jxl.write.WriteException;
import vacation_extrawork.Dayinformation;
import vacation_extrawork.OneManData;

public class DataBaseOperation {
	
	Connection connect = null;
    Statement statement = null;
    ResultSet resultSet = null;
	
	
	public Connection LinkToDataBase() throws ClassNotFoundException, SQLException {
			
		connect = DriverManager.getConnection("jdbc:oracle:thin:@111.230.138.68:1523:VAChecking", "C##MY", "z456788002");		
        System.out.println(connect);         
        return connect;
               						
	}
	
	public void DisposeDataBaseLink() throws SQLException {
		
		if (statement!=null) {statement.close();}
		if (resultSet!=null) {resultSet.close();}
		if (connect!=null) {connect.close();}
		
	}
	
	public boolean InsertIntoOneLine_DATA_VACATIONANDOVERWORK(String name,int id,Date time,
			                                                  String ACTUALTIMENOCLEAR,String REASONS,
			                                                  String REASONS_DETAILS,String REASONS_EXPLANATION,String HANDLEOVERTIMEWORK) throws SQLException, ClassNotFoundException {
		
		LinkToDataBase();
		
		if (connect!=null) {
			
			String sql = "INSERT INTO DATA_VACATIONANDOVERWORK (name,id,time,ACTUALTIMENOCLEAR,REASONS,REASONS_DETAILS,REASONS_EXPLANATION,HANDLEOVERTIMEWORK)VALUES(?,?,?,?,?,?,?,?)";
			
			PreparedStatement pre = connect.prepareStatement(sql);
			
			pre.setString(1,name);
			pre.setString(2,String.valueOf(id));
			pre.setDate(3, time);
			pre.setString(4,ACTUALTIMENOCLEAR);
			pre.setString(5,REASONS);
			pre.setString(6,REASONS_DETAILS);
			pre.setString(7,REASONS_EXPLANATION);
			pre.setString(8,HANDLEOVERTIMEWORK);
			
			pre.execute();			
			DisposeDataBaseLink();									
			return true;
						
		}
		else {
			System.out.println("����û�н����ɹ�");
			DisposeDataBaseLink();
			return false;
		}
			
	}
	
	public boolean DeleteOneLine_DATA_VACATIONANDOVERWORK(String name,int id,Date time,
            String ACTUALTIMENOCLEAR) throws SQLException, ClassNotFoundException {
		
		LinkToDataBase();
		if (connect!=null) {
			
		String sql = "DELETE FROM DATA_VACATIONANDOVERWORK WHERE name = ?and ID =? and TIME = ? and ACTUALTIMENOCLEAR = ?";		
		PreparedStatement pre = connect.prepareStatement(sql);
		
		pre.setString(1, name);
		pre.setInt(2, id);
		pre.setDate(3,time);
		pre.setString(4, ACTUALTIMENOCLEAR);		
		int indexcount = pre.executeUpdate();	
		System.out.println(indexcount);
		
		 if (indexcount>0) {
			 DisposeDataBaseLink();	
			return true;
				
		 }
		 else {
			 DisposeDataBaseLink();	
			return false;
		 }
		}
		else {
			DisposeDataBaseLink();	
			return false;
		}
			
	}
	
	public boolean InsertIntoOneLine_DATA_WOKERNAMELIST(int id,String name) throws SQLException, ClassNotFoundException {
		
		LinkToDataBase();
		if (connect!=null) {
			
			String sql = "INSERT INTO WOKERNAMELIST (ID,NAME) VALUES (?,?)";		
			PreparedStatement pre = connect.prepareStatement(sql);			
			pre.setInt(1, id);
			pre.setString(2,name);				
			pre.execute();		
			DisposeDataBaseLink();	
			return true;
									
		}
		else {
			System.out.println("����û�н����ɹ�");
			DisposeDataBaseLink();	
			return false;
		}
		
	}
	
	public int GetID_from_Nanme(String name) throws ClassNotFoundException, SQLException {
		
		LinkToDataBase();
		int id = -1;
		if (connect!=null) {
			
			String sql = "SELECT * FROM WOKERNAMELIST WHERE NAME = ?";
			PreparedStatement pre = connect.prepareStatement(sql);			
			pre.setString(1, name);		
			resultSet = pre.executeQuery();
			
			
			while (resultSet.next()) {
				
				id = resultSet.getInt("ID");
				
			}
						
		}
		DisposeDataBaseLink();
		return id;
		
	}
	
	
	public void InsertInto_DATA_VACATIONANDOVERWORK_ONEMANDATAGROUP(ArrayList<OneManData> data) throws ClassNotFoundException, SQLException {
		
		LinkToDataBase();
		
		if (connect!=null) {
			
			connect.setAutoCommit(false);
			String onelineSQL = "INSERT INTO DATA_VACATIONANDOVERWORK (name,id,time,ACTUALTIMENOCLEAR,REASONS,REASONS_DETAILS,REASONS_EXPLANATION,HANDLEOVERTIMEWORK)VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement pre = connect.prepareStatement(onelineSQL);					
			Iterator<OneManData> onemaniIterator = data.iterator();
			
			while (onemaniIterator.hasNext()) {
				OneManData dataforoneman = (OneManData)onemaniIterator.next();
				
				Iterator<Dayinformation> dayinformationIterator = dataforoneman.getDayinformation().iterator();
				
				while (dayinformationIterator.hasNext()) {
					Dayinformation datafordayinformaiton = (Dayinformation)dayinformationIterator.next();

					pre.setString(1,dataforoneman.getName());
					pre.setString(2,String.valueOf(dataforoneman.getId()));
					pre.setDate(3, datafordayinformaiton.getTime());
					pre.setString(4,datafordayinformaiton.getActualtimenoclear());
					pre.setString(5,datafordayinformaiton.getreasons());
					pre.setString(6,datafordayinformaiton.getreasons_details());
					pre.setString(7,datafordayinformaiton.getreasons_explanation());
					pre.setString(8,datafordayinformaiton.gethandleovertimework());					
					pre.addBatch();
										
				}
				
			}
			int[] result = pre.executeBatch();
											
		}
		
		DisposeDataBaseLink();
		
				
	}
	
	
	public void selectfrom_DATA_VACATIONANDOVERWORK_downloadchoose(String Rangename,String rangetype,Date[] period) throws ClassNotFoundException, SQLException, WriteException, IOException {
		
        LinkToDataBase();
        
		if (connect!=null) {
			
			String alltypesqlString ="select * from DATA_VACATIONANDOVERWORK where Time>= ? and Time<= ?and name = ? and reasons = ?";
			String allmansqlString ="select * from DATA_VACATIONANDOVERWORK where Time>= ? and Time<= ? and reasons = ?";	
			String allreasonsqlString ="select * from DATA_VACATIONANDOVERWORK where Time>= ? and Time<= ? and name = ?";	
			String all ="select * from DATA_VACATIONANDOVERWORK where Time>= ? and Time<= ? ";	
					
			if (Rangename.equals("������")&&rangetype.equals("ȫ������")) {
				
				PreparedStatement pre = connect.prepareStatement(all);
				pre.setDate(1, GetTheSmallerTimeInperiod(period));
				pre.setDate(2, GetTheBiggerTimeInperiod(period));
				ResultSet myresultSet = pre.executeQuery();
				if (!myresultSet.isBeforeFirst()) {
					
					ShowDialog("ѡ���ķ�Χ��û������");
					return;
				}
				wrtieExcel wrtieExcel = new wrtieExcel("�쳣���ڵ�������.xls");
				while (myresultSet.next()) {
					String NAME = myresultSet.getString("NAME");
					int IDint = myresultSet.getInt("ID");
					String ID= String.valueOf(IDint);
					Date TIMEdate = myresultSet.getDate("TIME");
					String Time = String.valueOf(TIMEdate);
					String ACTUALTIMENOCLEAR = myresultSet.getString("ACTUALTIMENOCLEAR");
					String REASONS = myresultSet.getString("REASONS");
					String REASONS_DETAILS = myresultSet.getString("REASONS_DETAILS");
					String REASONS_EXPLANATION = myresultSet.getString("REASONS_EXPLANATION");
					String HANDLEOVERTIMEWORK = myresultSet.getString("HANDLEOVERTIMEWORK");					
					ArrayList<String> datagroup = new ArrayList<String>();
					datagroup.add(NAME);
					datagroup.add(ID);
					datagroup.add(Time);
					datagroup.add(ACTUALTIMENOCLEAR);
					datagroup.add(REASONS);
					datagroup.add(REASONS_DETAILS);
					datagroup.add(REASONS_EXPLANATION);
					datagroup.add(HANDLEOVERTIMEWORK);
					
					wrtieExcel.writeline(datagroup);															
				}
				wrtieExcel.writedone();
			}
			
			
            else if (!Rangename.equals("������")&&rangetype.equals("ȫ������")) {
				
				PreparedStatement pre = connect.prepareStatement(allreasonsqlString);
				pre.setDate(1, GetTheSmallerTimeInperiod(period));
				pre.setDate(2, GetTheBiggerTimeInperiod(period));
				pre.setString(3, Rangename);
				ResultSet myresultSet = pre.executeQuery();
				if (!myresultSet.isBeforeFirst()) {
					
					ShowDialog("ѡ���ķ�Χ��û������");
					return;
				}
				wrtieExcel wrtieExcel = new wrtieExcel("�쳣���ڵ�������.xls");
				while (myresultSet.next()) {
					String NAME = myresultSet.getString("NAME");
					int IDint = myresultSet.getInt("ID");
					String ID= String.valueOf(IDint);
					Date TIMEdate = myresultSet.getDate("TIME");
					String Time = String.valueOf(TIMEdate);
					String ACTUALTIMENOCLEAR = myresultSet.getString("ACTUALTIMENOCLEAR");
					String REASONS = myresultSet.getString("REASONS");
					String REASONS_DETAILS = myresultSet.getString("REASONS_DETAILS");
					String REASONS_EXPLANATION = myresultSet.getString("REASONS_EXPLANATION");
					String HANDLEOVERTIMEWORK = myresultSet.getString("HANDLEOVERTIMEWORK");					
					ArrayList<String> datagroup = new ArrayList<String>();
					datagroup.add(NAME);
					datagroup.add(ID);
					datagroup.add(Time);
					datagroup.add(ACTUALTIMENOCLEAR);
					datagroup.add(REASONS);
					datagroup.add(REASONS_DETAILS);
					datagroup.add(REASONS_EXPLANATION);
					datagroup.add(HANDLEOVERTIMEWORK);
					
					wrtieExcel.writeline(datagroup);															
				}
				wrtieExcel.writedone();
			}
			
			
           else if (Rangename.equals("������")&&!rangetype.equals("ȫ������")) {
				
				PreparedStatement pre = connect.prepareStatement(allmansqlString);
				pre.setDate(1, GetTheSmallerTimeInperiod(period));
				pre.setDate(2, GetTheBiggerTimeInperiod(period));
				pre.setString(3, rangetype);
				ResultSet myresultSet = pre.executeQuery();
				if (!myresultSet.isBeforeFirst()) {
					
					ShowDialog("ѡ���ķ�Χ��û������");
					return;
				}
				wrtieExcel wrtieExcel = new wrtieExcel("�쳣���ڵ�������.xls");
				while (myresultSet.next()) {
					String NAME = myresultSet.getString("NAME");
					int IDint = myresultSet.getInt("ID");
					String ID= String.valueOf(IDint);
					Date TIMEdate = myresultSet.getDate("TIME");
					String Time = String.valueOf(TIMEdate);
					String ACTUALTIMENOCLEAR = myresultSet.getString("ACTUALTIMENOCLEAR");
					String REASONS = myresultSet.getString("REASONS");
					String REASONS_DETAILS = myresultSet.getString("REASONS_DETAILS");
					String REASONS_EXPLANATION = myresultSet.getString("REASONS_EXPLANATION");
					String HANDLEOVERTIMEWORK = myresultSet.getString("HANDLEOVERTIMEWORK");					
					ArrayList<String> datagroup = new ArrayList<String>();
					datagroup.add(NAME);
					datagroup.add(ID);
					datagroup.add(Time);
					datagroup.add(ACTUALTIMENOCLEAR);
					datagroup.add(REASONS);
					datagroup.add(REASONS_DETAILS);
					datagroup.add(REASONS_EXPLANATION);
					datagroup.add(HANDLEOVERTIMEWORK);					
					wrtieExcel.writeline(datagroup);															
				}
				wrtieExcel.writedone();
			}
						
			else {
				
				PreparedStatement pre = connect.prepareStatement(alltypesqlString);
				pre.setDate(1, GetTheSmallerTimeInperiod(period));
				pre.setDate(2, GetTheBiggerTimeInperiod(period));
				pre.setString(3, Rangename);
				pre.setString(4, rangetype);
				ResultSet myresultSet = pre.executeQuery();
				
				if (!myresultSet.isBeforeFirst()) {
					
					ShowDialog("ѡ���ķ�Χ��û������");
					return;
				}
				wrtieExcel wrtieExcel = new wrtieExcel("�쳣���ڵ�������.xls");
				while (myresultSet.next()) {
					String NAME = myresultSet.getString("NAME");
					int IDint = myresultSet.getInt("ID");
					String ID= String.valueOf(IDint);
					Date TIMEdate = myresultSet.getDate("TIME");
					String Time = String.valueOf(TIMEdate);
					String ACTUALTIMENOCLEAR = myresultSet.getString("ACTUALTIMENOCLEAR");
					String REASONS = myresultSet.getString("REASONS");
					String REASONS_DETAILS = myresultSet.getString("REASONS_DETAILS");
					String REASONS_EXPLANATION = myresultSet.getString("REASONS_EXPLANATION");
					String HANDLEOVERTIMEWORK = myresultSet.getString("HANDLEOVERTIMEWORK");					
					ArrayList<String> datagroup = new ArrayList<String>();
					datagroup.add(NAME);
					datagroup.add(ID);
					datagroup.add(Time);
					datagroup.add(ACTUALTIMENOCLEAR);
					datagroup.add(REASONS);
					datagroup.add(REASONS_DETAILS);
					datagroup.add(REASONS_EXPLANATION);
					datagroup.add(HANDLEOVERTIMEWORK);					
					wrtieExcel.writeline(datagroup);															
				}
				wrtieExcel.writedone();								
			}														
		}
		DisposeDataBaseLink();
				
	}
	
	public boolean InsertIntoOneLine_DATA_ACCOUNT(String username,String password,String workname,int id) throws ClassNotFoundException, SQLException {
		
	       LinkToDataBase();
	        
			if (connect!=null) {
				
				String onelineSQL = "INSERT INTO ACCOUNT (username,password,workername,id) VALUES (?,?,?,?)";
				PreparedStatement pre = connect.prepareStatement(onelineSQL);
				
				pre.setString(1, username);
				pre.setString(2, password);
				pre.setString(3, workname);
				pre.setInt(4, id);				
				int result = pre.executeUpdate();
				
				
				if (result == 0) {
					
					return false;
					
				}
				
				if (result == 1) {
					
					return true;
					
				}
							
			}
			
			DisposeDataBaseLink();			
			return false;		
	}
	
	public Map<ArrayList<String>, String> SelectOneLine_DATA_ACCOUNT(String username,String password) throws ClassNotFoundException, SQLException
	{
		LinkToDataBase();
		
		if (connect!=null) {
			
			String SelectSQL = "SELECT * FROM account";
			PreparedStatement pre = connect.prepareStatement(SelectSQL);
			ResultSet myresultSet = pre.executeQuery();
			
			if (!myresultSet.isBeforeFirst()) {
				
				Map<ArrayList<String>, String> resutMap = new HashMap<ArrayList<String>, String>();
				resutMap.put(new ArrayList<String>(), "���ݿ���û�ж�ȡ���κ��ʺ����ݣ�����ϵ����Ա");	
				DisposeDataBaseLink();
				return resutMap;
				
			}
			
			while (myresultSet.next()) {
				
				String usenameString= myresultSet.getString("Username");				
				String passwordString= myresultSet.getString("password");
				String wokernameString= myresultSet.getString("WOKERNAME");
				String idString= myresultSet.getString("id");
												
				if (usenameString.equals(username)) {
					
					if (passwordString.equals(password)) {
						
						Map<ArrayList<String>, String> resutMap = new HashMap<ArrayList<String>, String>();
						ArrayList<String> userinformationArrayList  = new ArrayList<String>();
						userinformationArrayList.add(wokernameString);
						userinformationArrayList.add(usenameString);
						userinformationArrayList.add(passwordString);
						userinformationArrayList.add(idString);
						resutMap.put(userinformationArrayList, "�ɹ���¼");						
						DisposeDataBaseLink();
						return resutMap;
					}
				}
				
			}
			
			Map<ArrayList<String>, String> resutMap = new HashMap<ArrayList<String>, String>();
			resutMap.put(new ArrayList<String>(), "�ʺŻ����������");
			DisposeDataBaseLink();
			return resutMap;
									
		}
		
		Map<ArrayList<String>, String> resutMap = new HashMap<ArrayList<String>, String>();
		resutMap.put(new ArrayList<String>(), "���ݿ����Ӵ���");		
		DisposeDataBaseLink();
		return resutMap;
		
	}
	
	
		
	public Date GetTheBiggerTimeInperiod(Date[] period) {
		
		if (period[0] !=null &&period[1] !=null ) {
			
			int result = period[0].compareTo(period[1]);
			
			if (result<0) {
				
				return period[1];
				
			}
			
			else if (result>0) {
				
				return period[0];
			}
			else {

                 Date error = Date.valueOf("9999-12-12");
                 return error;
			}
			
			
		}
		
		Date error = Date.valueOf("9999-12-12");
        return error;
		
	}

    
	public Date GetTheSmallerTimeInperiod(Date[] period) {
		
		
          if (period[0] !=null &&period[1] !=null ) {
			
			int result = period[0].compareTo(period[1]);
			
			if (result<0) {
				
				return period[0];
				
			}
			
			else if (result>0) {
				
				return period[1];
			}
			else {

                 Date error = Date.valueOf("9999-12-12");
                 return error;
			}
			
			
		}
		
		Date error = Date.valueOf("9999-12-12");
        return error;
		
	}
	
	private void ShowDialog(String word) {
		
		JOptionPane.showMessageDialog(null,word, "������ʾ", JOptionPane.ERROR_MESSAGE); 
				
	}

}
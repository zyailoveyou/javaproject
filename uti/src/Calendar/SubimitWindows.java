package Calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import ojdbc.DataBaseOperation;
import ojdbc.LocaltestDataBaseOperation;
import user.User;
import vacation_extrawork.Dayinformation;
import vacation_extrawork.OneManData;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;

public class SubimitWindows extends Windows {
	
	private String[] yearlistdata;
	private String[] monthlistdata;
	private ArrayList<Mylabel> daylabeList = new ArrayList<Mylabel>();
	private ArrayList<OneManData> submitdatagroup = new ArrayList<OneManData>();
	private JComboBox<String> year;
	private JComboBox<String> month;
	private JComboBox<String> vacationorextrawork;
	private JLabel worknameinformation;

	
	public ArrayList<OneManData> getSubmitdatagroup() {
		return submitdatagroup;
	}
	
	

	public SubimitWindows(User user) {
		
		setUser(user);
		setWindowsMode("提交模式");
		yearlistdata = new String[] {"2019年", "2020年", "2021年", "2022年", "2023年", "2024年", "2025年", "2026年", "2027年", "2028年", "2029年", "2030年", "2031年", "2032年", "2033年", "2034年", "2035年", "2036年", "2037年", "2038年", "2039年", "2040年", "2041年", "2042年", "2043年", "2044年", "2045年", "2046年", "2047年", "2048年", "2049年", "2050年"};
		monthlistdata = new String[] {"1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"};
						
		initialize();
	}


	private void initialize() {

		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\u8BF7\u5047\u63D0\u4EA4");
		frame.setBounds(100, 100, 635, 480);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setLocationRelativeTo(null);
		ImageIcon icon55 = new ImageIcon("src/image/汇景图标.png");		
		frame.setIconImage(icon55.getImage());
		JPanel top = new JPanel();
		frame.getContentPane().add(top, BorderLayout.NORTH);
		
				
		year = new JComboBox<String>();
		year.setToolTipText("\u9009\u62E9\u5E74\u4EFD");
		year.setModel(new DefaultComboBoxModel<String>(yearlistdata));
		year.setFont(new Font("幼圆", Font.BOLD, 16));
		year.setForeground(Color.DARK_GRAY);
		month = new JComboBox<String>();
		month.setToolTipText("\u9009\u62E9\u6708\u4EFD");
		month.setModel(new DefaultComboBoxModel<String>(monthlistdata));
		month.setFont(new Font("幼圆", Font.BOLD, 16));
		month.setForeground(Color.DARK_GRAY);
		vacationorextrawork = new JComboBox<String>();
		vacationorextrawork.setToolTipText("\u9009\u62E9\u672A\u6253\u5361\u539F\u56E0");
		vacationorextrawork.setModel(new DefaultComboBoxModel<String>(new String[] {"请假","加班","正常休假","未打卡"}));
		vacationorextrawork.setFont(new Font("幼圆", Font.BOLD, 16));
		vacationorextrawork.setForeground(Color.DARK_GRAY);
		worknameinformation = new JLabel("New label");
		worknameinformation.setFont(new Font("幼圆", Font.BOLD, 16));		
		worknameinformation.setText("欢迎你登录，"+user.getCheckname()+"!");
		worknameinformation.setForeground(Color.DARK_GRAY);
		GroupLayout gl_top = new GroupLayout(top);
		gl_top.setHorizontalGroup(
			gl_top.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(gl_top.createSequentialGroup()
					.addContainerGap()
					.addComponent(worknameinformation)
					.addGap(20)
					.addComponent(year, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addComponent(month, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addComponent(vacationorextrawork, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_top.setVerticalGroup(
			gl_top.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(gl_top.createSequentialGroup()
					.addGap(9)
					.addGroup(gl_top.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(vacationorextrawork, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(month, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(year, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(worknameinformation))
					.addGap(9))
		);
		
		top.setLayout(gl_top);
		
		JPanel middle = new JPanel();
		middle.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		frame.getContentPane().add(middle, BorderLayout.CENTER);
		middle.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		middle.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 7, 4, 2));
		
		JLabel label = new JLabel("\u661F\u671F\u4E00");
		label.setForeground(Color.DARK_GRAY);
		label.setBackground(Color.BLUE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		panel.add(label);
		
		JLabel label_2 = new JLabel("\u661F\u671F\u4E8C");
		label_2.setForeground(Color.DARK_GRAY);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("宋体", Font.PLAIN, 16));
		panel.add(label_2);
		
		JLabel label_1 = new JLabel("\u661F\u671F\u4E09");
		label_1.setForeground(Color.DARK_GRAY);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		panel.add(label_1);
		
		JLabel label_3 = new JLabel("\u661F\u671F\u56DB");
		label_3.setForeground(Color.DARK_GRAY);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("宋体", Font.PLAIN, 16));
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("\u661F\u671F\u4E94");
		label_4.setForeground(Color.DARK_GRAY);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("宋体", Font.PLAIN, 16));
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("\u661F\u671F\u516D");
		label_5.setForeground(Color.DARK_GRAY);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("宋体", Font.PLAIN, 16));
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("\u661F\u671F\u65E5");
		label_6.setForeground(Color.DARK_GRAY);
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("宋体", Font.PLAIN, 16));
		panel.add(label_6);
		
		JPanel dayzoompJPanel = new JPanel();
		middle.add(dayzoompJPanel, BorderLayout.CENTER);
		dayzoompJPanel.setLayout(new GridLayout(6, 7, 4, 4));
		
		JPanel list = new JPanel();
		middle.add(list, BorderLayout.SOUTH);
		list.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton submit = new JButton("\u63D0\u4EA4\u4FE1\u606F");
		submit.setForeground(Color.DARK_GRAY);
		submit.setFont(new Font("幼圆", Font.BOLD, 16));
		list.add(submit);
		
		
		
		
        submit.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
					
				
//				LocaltestDataBaseOperation localtestDataBaseOperation = new LocaltestDataBaseOperation();
				DataBaseOperation test = new DataBaseOperation();
				if (submitdatagroup.isEmpty()) {
					ShowDialog("没有设置任何数据");
					return;
				}
				try {
//					test.InsertInto_DATA_VACATIONANDOVERWORK_ONEMANDATAGROUP(submitdatagroup);
					for (OneManData i : submitdatagroup) {
                      //执行3天以上假期的拆分
							i.SeparateSequential_ThreeDay_Dayinformation();						 
						
					}
										
//					localtestDataBaseOperation.InsertInto_DATA_VACATIONANDOVERWORK_ONEMANDATAGROUP(submitdatagroup);
					test.InsertInto_DATA_VACATIONANDOVERWORK_ONEMANDATAGROUP(submitdatagroup);
				} catch (ClassNotFoundException e1) {

					e1.printStackTrace();
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
				catch (CloneNotSupportedException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				
					ShowDialog("提交成功");
					submitdatagroup.clear();
					flashdata();
					getFrame().dispose();
																					
			}
			
		});
        
        
	    for (int i = 0; i < 42; i++) {
			
			Mylabel mylabel = new Mylabel(this);
			mylabel.setText("");
			mylabel.setOpaque(true);
			mylabel.setFont(new Font("黑体", Font.BOLD, 20));
			mylabel.setHorizontalAlignment(SwingConstants.CENTER);
			mylabel.setVerticalAlignment(SwingConstants.CENTER);
			mylabel.addMouseListener(mylabel);
			mylabel.setBorder(BorderFactory.createLineBorder(Color.gray));
			daylabeList.add(mylabel);
			dayzoompJPanel.add(mylabel);
		}
		
								
 		flashdata();
						
		year.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {

				
				if (e.getStateChange() == ItemEvent.SELECTED) {
					flashdata();
				}

			}
		});
		
		
		month.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {
					flashdata();
				}
				
			}
		});
        
        
	
	}


	private void ShowDialog(String word) {
		
		JOptionPane.showMessageDialog(null,word, "提示ʾ", JOptionPane.ERROR_MESSAGE); 
				
	}
	
	
	public int getyear() {
		
		String cachesStringyear = (String)year.getSelectedItem();		
		String yearsString = cachesStringyear.substring(0,cachesStringyear.lastIndexOf("年"));		
		int year = Integer.parseInt(yearsString);
		return year;
	}
	
	public int getmonth() {
		String cachesStringmonth = (String)month.getSelectedItem();		
		String monthString = cachesStringmonth.substring(0,cachesStringmonth.lastIndexOf("月"));		
		int month = Integer.parseInt(monthString);
		return month;
		
	}
	
	public String getVacationorExtrawork() {
		
		String indexinformation = (String)vacationorextrawork.getSelectedItem();		
		return indexinformation;
		
	}
	

	private void flashdata() {
		
		int year = getyear();			
		int month = getmonth();
				
		Mycalendar myMycalendar = new Mycalendar();		
	    myMycalendar.setYear(year);
	    myMycalendar.setMonth(month);
	    
	    String[] daynumbergroup = myMycalendar.getCalendar();
	    

	    for (int i = 0; i < 42; i++) {
			
	    	daylabeList.get(i).setText(daynumbergroup[i]);
	    	daylabeList.get(i).SetNoChooseState();
	    				
		}
	    

	    if (submitdatagroup.isEmpty()) {
			
		    for (int i = 0; i < 42; i++) {
				
		    	daylabeList.get(i).SetNoChooseState();
		    				
			}
		}
	    
	    else {
	    		    	
	    	for (OneManData data:submitdatagroup) {
				
		    	if (data.getName().equals(user.getCheckname()))     	
		    	{
		    		
	    			for (Dayinformation in:data.getN_dayinformation()) {
	    				
	    				Calendar calendar = Calendar.getInstance();
	    				calendar.setTime(in.getTime());
	    				int restore_year = calendar.get(Calendar.YEAR);
	    				int restore_month= calendar.get(Calendar.MONTH)+1;
	    										    				    		        		    	
	    				if (getyear() == restore_year && getmonth() == restore_month) {
							
	    	    		    for (int i = 0; i < 42; i++) {
					    		    	
			    		    	if (in.getLabelday().equals(daylabeList.get(i).getText())) {
			    		    				    
			    		    		
			    		    		if (in.getreasons().equals("正常休假")) {
			    		    			
			    		    			daylabeList.get(i).SetChooseStateForNormalRestDay();
									}
			    		    		
			    		    		else if (in.getreasons().equals("加班")) {
			    		    			daylabeList.get(i).SetChooseForExtrawork();
									}
			    		    		else if (in.getreasons().equals("请假")) {
			    		    			daylabeList.get(i).SetChooseForVacation();
									}
			    		    		else {
			    		    			daylabeList.get(i).SetChooseStateForMisscheck();
									}
			    
									break;
								 }	    		    	
					         }
	    					
						}
	    						    		   
	    			 }
	    			 break;
	    			 
		    	 }
		    	
		    	else {
		    		
				    for (int i = 0; i < 42; i++) {
						
				    	daylabeList.get(i).SetNoChooseState();
				    				
					}
				    
				}
		    	
	    	}
	    }
	}
		
}
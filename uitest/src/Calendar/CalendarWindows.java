package Calendar;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridLayout;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import com.jgoodies.forms.layout.CellConstraints.Alignment;

import excel.readExcel;
import excel.wrtieExcel;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import ojdbc.DataBaseOperation;
import user.user;
import vacation_extrawork.Dayinformation;
import vacation_extrawork.OneManData;

import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class CalendarWindows {

	private JFrame frame;
	private String[] namelistdata;
	private String[] yearlistdata;
	private String[] monthlistdata;
	private String[] vacationorextraworklistdata;
	private ArrayList<Mylabel> daylabeList = new ArrayList<Mylabel>();
	private ArrayList<OneManData> submitdatagroup = new ArrayList<OneManData>();
	private Date[] period = new Date[2];
		
	JComboBox<String> year;
	JComboBox<String> month;
	JComboBox<String> vacationorextrawork;
	JComboBox<String> namelist;
	private JComboBox downloadchoose;
	private user loginuser;
	

	public CalendarWindows() {
		
		namelistdata = new String[] {"陈诚","张雪","钟静鸿","汪怡雯","吴友兰","彭小波","谢金豆","杨易","廖龙"};
		yearlistdata = new String[] {"2019年", "2020年", "2021年", "2022年", "2023年", "2024年", "2025年", "2026年", "2027年", "2028年", "2029年", "2030年", "2031年", "2032年", "2033年", "2034年", "2035年", "2036年", "2037年", "2038年", "2039年", "2040年", "2041年", "2042年", "2043年", "2044年", "2045年", "2046年", "2047年", "2048年", "2049年", "2050年"};
		monthlistdata = new String[] {"1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"};
		vacationorextraworklistdata = new String[] {"未打卡说明","换休","病假","年假","事假","产假","丧假"};
		
		
		initialize();
		
				
	}
	
	
	
	
	public ArrayList<Mylabel> getDaylabeList() {
		return daylabeList;
	}




	public Date[] getPeriod() {
		return period;
	}


	public ArrayList<OneManData> getSubmitdatagroup() {
		return submitdatagroup;
	}


	public void setSubmitdatagroup(ArrayList<OneManData> submitdatagroup) {
		this.submitdatagroup = submitdatagroup;
	}


	public String getVacationorExtrawork() {
				
		String indexinformation = (String)vacationorextrawork.getSelectedItem();
		
		return indexinformation;
		
	}

	public JFrame getFrame() {
		return frame;
	}
	
	public ArrayList<OneManData> getsubmitdatagroup() {
		
		return submitdatagroup;
		
	}

	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u8BF7\u5047\u8BBE\u7F6E");
		frame.setBounds(100, 100, 520, 384);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setLocationRelativeTo(null);
		JPanel top = new JPanel();
		frame.getContentPane().add(top, BorderLayout.NORTH);
		
		
		top.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		
		namelist = new JComboBox<String>();
		namelist.setModel(new DefaultComboBoxModel(new String[] {"\u6768\u9053\u7434", "\u9648\u9053\u9896", "\u5362\u71D5", "\u5218\u840D", "\u6BB5\u4ECE\u52C7", "\u66F9\u627F\u6615", "\u6240\u6709\u4EBA"}));
		namelist.setToolTipText("\u9009\u62E9\u4EBA\u5458");
		namelist.setFont(new Font("Adobe 黑体 Std R", Font.PLAIN, 16));
		top.add(namelist);
		
		
		namelist.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				if (e.getStateChange() == ItemEvent.SELECTED) {
					flashdata();
				}
								
			}
		});
		
		
		
		year = new JComboBox<String>();
		year.setToolTipText("\u9009\u62E9\u5E74\u4EFD");
		year.setModel(new DefaultComboBoxModel<String>(yearlistdata));
		year.setFont(new Font("Adobe 黑体 Std R", Font.PLAIN, 16));
		top.add(year);
		
		month = new JComboBox<String>();
		month.setToolTipText("\u9009\u62E9\u6708\u4EFD");
		month.setModel(new DefaultComboBoxModel<String>(monthlistdata));
		month.setFont(new Font("Adobe 黑体 Std R", Font.PLAIN, 16));
		top.add(month);
		
		vacationorextrawork = new JComboBox<String>();
		vacationorextrawork.setToolTipText("\u9009\u62E9\u672A\u6253\u5361\u539F\u56E0");
		vacationorextrawork.setModel(new DefaultComboBoxModel(new String[] {"\u8BF7\u5047", "\u52A0\u73ED", "\u6B63\u5E38\u4F11\u5047", "\u5168\u90E8\u533A\u95F4"}));
		vacationorextrawork.setFont(new Font("Adobe 黑体 Std R", Font.PLAIN, 16));
		top.add(vacationorextrawork);
		
		downloadchoose = new JComboBox();
		downloadchoose.setModel(new DefaultComboBoxModel(new String[] {"\u63D0\u4EA4\u6A21\u5F0F", "\u4E0B\u8F7D\u6A21\u5F0F"}));
		downloadchoose.setFont(new Font("黑体", Font.BOLD, 16));
		top.add(downloadchoose);
		
		JPanel middle = new JPanel();
		middle.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		frame.getContentPane().add(middle, BorderLayout.CENTER);
		middle.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		middle.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 7, 4, 2));
		
		JLabel label = new JLabel("\u661F\u671F\u4E00");
		label.setForeground(Color.BLACK);
		label.setBackground(Color.BLUE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		panel.add(label);
		
		JLabel label_2 = new JLabel("\u661F\u671F\u4E8C");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("宋体", Font.PLAIN, 16));
		panel.add(label_2);
		
		JLabel label_1 = new JLabel("\u661F\u671F\u4E09");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		panel.add(label_1);
		
		JLabel label_3 = new JLabel("\u661F\u671F\u56DB");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("宋体", Font.PLAIN, 16));
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("\u661F\u671F\u4E94");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("宋体", Font.PLAIN, 16));
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("\u661F\u671F\u516D");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("宋体", Font.PLAIN, 16));
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("\u661F\u671F\u65E5");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("宋体", Font.PLAIN, 16));
		panel.add(label_6);
		
		JPanel dayzoompJPanel = new JPanel();
		middle.add(dayzoompJPanel, BorderLayout.CENTER);
		dayzoompJPanel.setLayout(new GridLayout(6, 7, 4, 4));
		
		JPanel list = new JPanel();
		middle.add(list, BorderLayout.SOUTH);
		
		JButton submit = new JButton("\u63D0\u4EA4");
		submit.setFont(new Font("宋体", Font.PLAIN, 16));
		
		JButton download = new JButton("\u4E0B\u8F7D");
		download.setFont(new Font("宋体", Font.PLAIN, 16));
		list.setLayout(new GridLayout(0, 2, 0, 0));
		list.add(submit);
		list.add(download);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu modegroup = new JMenu("\u6A21\u5F0F\u9009\u62E9");
		modegroup.setFont(new Font("微软雅黑", Font.BOLD, 14));
		menuBar.add(modegroup);
		
		JMenuItem sumbitmode = new JMenuItem("\u63D0\u4EA4\u6A21\u5F0F");
		modegroup.add(sumbitmode);
		
		JMenuItem downloadmode = new JMenuItem("\u4E0B\u8F7D\u6A21\u5F0F");
		modegroup.add(downloadmode);
		
		JMenuItem approvalmode = new JMenuItem("\u5BA1\u6279\u6A21\u5F0F");
		modegroup.add(approvalmode);
		
		JMenu aboutinformation = new JMenu("\u5173\u4E8E");
		aboutinformation.setFont(new Font("微软雅黑", Font.BOLD, 14));
		menuBar.add(aboutinformation);
				
		submit.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
							
				DataBaseOperation test = new DataBaseOperation();
				if (submitdatagroup.isEmpty()) {
					ShowDialog("没有任何数据，请重新设置数据");
					return;
				}
				try {
					test.InsertInto_DATA_VACATIONANDOVERWORK_ONEMANDATAGROUP(submitdatagroup);
				} catch (ClassNotFoundException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				
					ShowDialog("数据发送成功");
					submitdatagroup.clear();
					flashdata();
																					
			}
			
		});
		
		download.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				DataBaseOperation test= new DataBaseOperation();
				
				try {
					test.selectfrom_DATA_VACATIONANDOVERWORK_downloadchoose((String)getNamelist().getSelectedItem(), getVacationorExtrawork(), period);
				} catch (ClassNotFoundException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				} catch (WriteException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
											

			}
			
		});
		
		
		
//		for (int i = 0; i < 42; i++) {
//			
//			mylabel mylabel = new mylabel(this);
//			mylabel.setText("");
//			mylabel.setOpaque(true);
//			mylabel.setFont(new Font("宋体", Font.BOLD, 20));
//			mylabel.setHorizontalAlignment(SwingConstants.CENTER);
//			mylabel.setVerticalAlignment(SwingConstants.CENTER);
//			mylabel.addMouseListener(mylabel);
//			daylabeList.add(mylabel);
//			
//		}
		
		
		for (Mylabel i : daylabeList) {
			
			dayzoompJPanel.add(i);
		}
								
 		flashdata();
						
		year.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO 自动生成的方法存根
				
				if (e.getStateChange() == ItemEvent.SELECTED) {
					flashdata();
				}

			}
		});
		
		
		month.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO 自动生成的方法存根
				if (e.getStateChange() == ItemEvent.SELECTED) {
					flashdata();
				}
				
			}
		});
		
	
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
	
	public JComboBox<String> getYear() {
		return year;
	}


	public void setYear(JComboBox<String> year) {
		this.year = year;
	}
	
	public JComboBox<String> getmode() {
		return downloadchoose;
	}


	public JComboBox<String> getMonth() {
		return month;
	}


	public JComboBox<String> getNamelist() {
		return namelist;
	}


	public JComboBox<String> getVacationorextrawork() {
		return vacationorextrawork;
	}
	
	
	//刷新数据
	private void flashdata() {
			
		if (((String)downloadchoose.getSelectedItem()).equals("提交模式")) {
			
			ModeSubmitFlash();
			
		}
		
		if (((String)downloadchoose.getSelectedItem()).equals("下载模式")) {
			
			ModeDownloadFlash();
			
		}
			    		
	}
	
	private void ModeDownloadFlash() {
		// TODO 自动生成的方法存根
		
		int year = getyear();			
		int month = getmonth();
				
		Mycalendar myMycalendar = new Mycalendar();		
	    myMycalendar.setYear(year);
	    myMycalendar.setMonth(month);
	    
	    String[] daynumbergroup = myMycalendar.getCalendar();
	    
	    //重新刷新日历表格
	    for (int i = 0; i < 42; i++) {
			
	    	daylabeList.get(i).setText(daynumbergroup[i]);
	    	daylabeList.get(i).SetNoChooseState();
	    				
		}
		
		for (int i = 0; i <2; i++) {
			
			Calendar myCalendar = Calendar.getInstance();		
			if (period[i] !=null) {							
			 myCalendar.setTime(period[i]);
			
			 if (getyear() == myCalendar.get(Calendar.YEAR) && getmonth() == myCalendar.get(Calendar.MONTH)+1) {
				
				 for (int k = 0; k < 42; k++)  {
					 
					 int saveday  = -1;
					 if (daylabeList.get(k).getText()!=null) {
						 saveday = (Integer.valueOf(daylabeList.get(k).getText()));
						 
						 if (saveday ==myCalendar.get(Calendar.DAY_OF_MONTH)) {
								
							 daylabeList.get(k).SetChooseStatefornormalrestdayColor();
							 
						  } 
						 
					  }
					 					 				 				 										 					
				}
												
			 }
			
		  }
						
		}
		
	}




	private void ModeSubmitFlash() {
		
		int year = getyear();			
		int month = getmonth();
				
		Mycalendar myMycalendar = new Mycalendar();		
	    myMycalendar.setYear(year);
	    myMycalendar.setMonth(month);
	    
	    String[] daynumbergroup = myMycalendar.getCalendar();
	    
	    //重新刷新日历表格
	    for (int i = 0; i < 42; i++) {
			
	    	daylabeList.get(i).setText(daynumbergroup[i]);
	    	daylabeList.get(i).SetNoChooseState();
	    				
		}
	    
	    //开始刷新检查
	    if (submitdatagroup.isEmpty()) {
			
		    for (int i = 0; i < 42; i++) {
				
		    	daylabeList.get(i).SetNoChooseState();
		    				
			}
		}
	    
	    else {
	    		    	
	    	for (OneManData data:submitdatagroup) {
				
		    	if (data.getName().equals((String)namelist.getSelectedItem()))     	
		    	{
		    		
	    			for (Dayinformation in:data.getDayinformation()) {
	    				
	    				Calendar calendar = Calendar.getInstance();
	    				calendar.setTime(in.getTime());
	    				int restore_year = calendar.get(Calendar.YEAR);
	    				int restore_month= calendar.get(Calendar.MONTH)+1;
	    										    				    		        		    	
	    				if (getyear() == restore_year && getmonth() == restore_month) {
							
	    	    		    for (int i = 0; i < 42; i++) {
					    		    	
			    		    	if (in.getLabelday().equals(daylabeList.get(i).getText())) {
			    		    				    
			    		    		
			    		    		if (in.getreasons_details().equals("正常休假")) {
			    		    			
			    		    			daylabeList.get(i).SetChooseStatefornormalrestdayColor();
									}
			    		    		else {
			    		    			daylabeList.get(i).SetChooseState();
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
	

			
	private void ShowDialog(String word) {
		
		JOptionPane.showMessageDialog(null,word, "错误提示", JOptionPane.ERROR_MESSAGE); 
				
	}
	

	
	public Date GetTheBiggerTimeInperiod() {
		
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

    
	public Date GetTheSmallerTimeInperiod() {
		
		
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
}

package vacation_extrawork;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import java.awt.Component;
import javax.swing.SwingConstants;

import Calendar.Mylabel;
import ojdbc.DataBaseOperation;

import javax.swing.JList;

public class ExtraWorkWindows {

	
	private boolean noinformationsubmit = true;
	private JFrame frame;
	private Mylabel label;
	private JComboBox extraworktype;
	private JCheckBox morningextrawork;
	private JCheckBox afternoonextrawork;
	private JCheckBox wholedayextrawork;
	private JComboBox howtodealwithoverwork;
		
	public boolean isNoinformationsubmit() {
		return noinformationsubmit;
	}

	public Mylabel getlabel() {
		return label;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JFrame getFrame() {
		return frame;
	}

	public ExtraWorkWindows(Mylabel label) {
		this.label = label;
		initialize();
	}
	
	public boolean getnoinformationsubmit() {
		
		return noinformationsubmit;
		
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		extraworktype = new JComboBox();
		extraworktype.setPreferredSize(new Dimension(38, 40));
		extraworktype.setFont(new Font("黑体", Font.BOLD, 18));
		extraworktype.setModel(new DefaultComboBoxModel(new String[] {"\u4F11\u606F\u65E5\u52A0\u73ED", "\u6CD5\u5B9A\u8282\u5047\u65E5\u52A0\u73ED", "\u8D85\u65F6\u52A0\u73ED", "\u7EDF\u4E00\u52A0\u73ED"}));
		frame.getContentPane().add(extraworktype, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		morningextrawork = new JCheckBox("\u4E0A\u5348\u52A0\u73ED");
		morningextrawork.setFont(new Font("黑体", Font.PLAIN, 16));
		morningextrawork.setHorizontalAlignment(SwingConstants.LEFT);
		morningextrawork.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(morningextrawork);
		
		afternoonextrawork = new JCheckBox("\u4E0B\u5348\u52A0\u73ED");
		afternoonextrawork.setFont(new Font("黑体", Font.PLAIN, 16));
		afternoonextrawork.setHorizontalAlignment(SwingConstants.LEFT);
		afternoonextrawork.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(afternoonextrawork);
		
		wholedayextrawork = new JCheckBox("\u5168\u5929\u52A0\u73ED");
		wholedayextrawork.setFont(new Font("黑体", Font.PLAIN, 16));
		wholedayextrawork.setHorizontalAlignment(SwingConstants.LEFT);
		wholedayextrawork.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(wholedayextrawork);
		
		howtodealwithoverwork = new JComboBox();
		howtodealwithoverwork.setFont(new Font("黑体", Font.BOLD, 16));
		howtodealwithoverwork.setModel(new DefaultComboBoxModel(new String[] {"\u8BA1\u5DE5\u8D44", "\u8BA1\u6362\u4F11"}));
		panel.add(howtodealwithoverwork);
		
		ButtonGroup mygourpButtonGroup = new ButtonGroup();
		mygourpButtonGroup.add(morningextrawork);
		mygourpButtonGroup.add(afternoonextrawork);
		mygourpButtonGroup.add(wholedayextrawork);
		
		JButton submitextrawork = new JButton("\u63D0\u4EA4");
		submitextrawork.setFont(new Font("黑体", Font.BOLD, 16));
		submitextrawork.setPreferredSize(new Dimension(93, 40));
		
		frame.getContentPane().add(submitextrawork, BorderLayout.SOUTH);
		
		submitextrawork.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (morningextrawork.isSelected()&&!afternoonextrawork.isSelected()) 
				{					
				 
					   setdata("上午未打",null);
					   noinformationsubmit = false;
					 						 						 
				}
					
				else if (!morningextrawork.isSelected()&&afternoonextrawork.isSelected()) 
				{					
						setdata("下午未打",null);
						noinformationsubmit = false;

				}
					
				else if(wholedayextrawork.isSelected())
				{					
					    setdata("全天未打",null);
					    noinformationsubmit = false;

				}
					
				else 
				{							
				     noinformationsubmit = true;
				}
			
						
			frame.dispose();
			
				
			}
			
		});
		
		
	}
	
	
	public void setdata(String Actualtimenoclear,String Explainreason) {
		 
		 String nameString = getlabel().getCal().getUser().getCheckname();	 		 		 
		 Dayinformation information = new Dayinformation();
		 String catogoryString = getlabel().getNewSubimitWindows().getVacationorExtrawork();
		 String vacationreasonString = (String)extraworktype.getSelectedItem();
		 String timeString = String.valueOf(getlabel().getNewSubimitWindows().getyear())+"-"+
				 String.valueOf(getlabel().getNewSubimitWindows().getmonth())+"-"+getlabel().getText();
		 Date datetime = Date.valueOf(timeString);
		 String howtodealwithovertimework  = (String)howtodealwithoverwork.getSelectedItem();
		 
		 information.setreasons(catogoryString);
		 information.setreasons_details(vacationreasonString);
		 information.setTime(datetime);
		 information.setLabelday(getlabel().getText());
		 information.setActualtimenoclear(Actualtimenoclear);
		 information.setreasons_explanation(Explainreason);
		 information.sethandleovertimework(howtodealwithovertimework);
		 
		 CheckTheManExist(nameString);
		 
		 for (int i=0;i<getlabel().getNewSubimitWindows().getSubmitdatagroup().size();i++) {
			if (nameString.equals(getlabel().getNewSubimitWindows().getSubmitdatagroup().get(i).getName())) {				
				getlabel().getNewSubimitWindows().getSubmitdatagroup().get(i).getDayinformation().add(information);				
			}

		}
		 
		 
		 System.out.println(nameString+"写入加班信息完成");
		
	}


	private void CheckTheManExist(String name) {
		
		 boolean exist = false;
		 for (int i=0;i<getlabel().getNewSubimitWindows().getSubmitdatagroup().size();i++) {
			if (name.equals(getlabel().getNewSubimitWindows().getSubmitdatagroup().get(i).getName())) {				
				exist = true;		
			}
		 }
		 
		 if (!exist) {
			
			 CreateOneManData(name);
			 
		 }
		 
		
		
	}
	
	
	private void CreateOneManData(String name) {
		
		OneManData OnePersondata = new OneManData(new ArrayList<Dayinformation>());
		OnePersondata.setName(name);
		DataBaseOperation getidBaseOperation = new DataBaseOperation();
		int id = -1;
		try {
			id = getidBaseOperation.GetID_from_Nanme(name);
		} catch (ClassNotFoundException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		if (id != -1) {
			
			OnePersondata.setId(id);
			getlabel().getNewSubimitWindows().getSubmitdatagroup().add(OnePersondata);
			
		}
		else {
			System.out.println("id查询错误，放弃添加人员信息");
		}
		
		
	}
	
	
		

}

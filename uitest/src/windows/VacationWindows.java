package windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import data.persondayinformation;
import uitest1.CalendarWindows;
import uitest1.mylabel;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;

public class VacationWindows {

	private JFrame frame;
	JCheckBox morningnocheck;
	private boolean nondaychoose = false;
	private JTextField morningreason;
	private JTextField afternoonreason;
	private JTextField wholedayreason;
	private String[] reasonsliStrings = new String[] {"换休","年休","事假","丧假","产假","陪护假","未打卡说明"};
	private Color activeColor = new Color(255, 255, 255);
	private Color nagativecColor =  new Color(150,150,150);
	private persondayinformation information = new persondayinformation();
	private mylabel label;
	
	private JComboBox<String> vacationreasons;

	
	public boolean getnodaychoose() {
		
		return nondaychoose;
		
	}
	
	public mylabel getlabel() {
		return label;
	}
	
	public persondayinformation getdaypersoninformation() {
				
       return information;
		
	}
		
	public JFrame getFrame() {
		return frame;
	}

	public VacationWindows(mylabel label) {

		this.label = label;
		initialize();
	}
	
	public void activeTextfield() {
		morningreason.setEnabled(true);
		afternoonreason.setEnabled(true);
		wholedayreason.setEnabled(true);
		morningreason.setBackground(activeColor);
		afternoonreason.setBackground(activeColor);
		wholedayreason.setBackground(activeColor);
		
	}
	
	public void nagativeTextfield() {
		
		morningreason.setEnabled(false);
		afternoonreason.setEnabled(false);
		wholedayreason.setEnabled(false);		
		morningreason.setBackground(nagativecColor);
		afternoonreason.setBackground(nagativecColor);
		wholedayreason.setBackground(nagativecColor);
		
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 317, 303);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		vacationreasons = new JComboBox<String>();
		vacationreasons.setPreferredSize(new Dimension(32, 40));
		vacationreasons.setFont(new Font("黑体", Font.BOLD, 18));
		vacationreasons.setModel(new DefaultComboBoxModel<String>(reasonsliStrings));
		frame.getContentPane().add(vacationreasons, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 2, 0, 10));
		
		JCheckBox morningnocheck = new JCheckBox("上午未打");
		morningnocheck.setBackground(new Color(245, 245, 245));
		panel.add(morningnocheck);
		morningnocheck.setFont(new Font("黑体", Font.PLAIN, 16));
		morningnocheck.setHorizontalAlignment(SwingConstants.CENTER);
		
		morningreason = new JTextField();
		panel.add(morningreason);
		morningreason.setColumns(10);
				
		JCheckBox afternoonnocheck = new JCheckBox("下午未打");
		afternoonnocheck.setBackground(new Color(245, 245, 245));
		panel.add(afternoonnocheck);
		afternoonnocheck.setFont(new Font("黑体", Font.PLAIN, 16));
		afternoonnocheck.setHorizontalAlignment(SwingConstants.CENTER);
		
		afternoonreason = new JTextField();
		afternoonreason.setColumns(10);
		panel.add(afternoonreason);
		
		JCheckBox wholedaynoclear = new JCheckBox("全天未打");
		wholedaynoclear.setHorizontalAlignment(SwingConstants.CENTER);
		wholedaynoclear.setFont(new Font("黑体", Font.PLAIN, 16));
		wholedaynoclear.setBackground(new Color(245, 245, 245));
		panel.add(wholedaynoclear);
		
		wholedayreason = new JTextField();
		wholedayreason.setColumns(10);
		panel.add(wholedayreason);
		
		JButton submitvacation = new JButton("提交");
		submitvacation.setPreferredSize(new Dimension(61, 40));
		frame.getContentPane().add(submitvacation, BorderLayout.SOUTH);
		
		nagativeTextfield();
				
		vacationreasons.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				String reasonsString = (String)vacationreasons.getSelectedItem();
				
				if (reasonsString.equals("未打卡说明")) {
					
                    activeTextfield();
					
					
				}
				else {
					
					nagativeTextfield();

					
				}				
			}
		});
			
		
		
		
		
		
		submitvacation.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
													
					if (morningnocheck.isSelected()&&!afternoonnocheck.isSelected()) 
					{					
					 
						   setdata();
						   information.setActualtimenoclear("上午未打");
						   information.setPostilinformation(morningreason.getText());
						   
						 						 
					}
						
					else if (!morningnocheck.isSelected()&&afternoonnocheck.isSelected()) 
					{					
							setdata();
							information.setActualtimenoclear("下午未打");
							information.setPostilinformation(afternoonreason.getText());
					}
						
					else if(wholedaynoclear.isSelected())
					{					
						   setdata();
						   information.setActualtimenoclear("全天未打");
						   information.setPostilinformation(wholedayreason.getText());
					}
						
					else 
					{							
					     nondaychoose = true;
					}
				
							
				frame.dispose();
											
			}
		});
			
	}
	
	
	public void setdata() {
		
		 String nameString = (String)getlabel().getCal().getNamelist().getSelectedItem();
		 information.setName(nameString);
		 String catogoryString = getlabel().getCal().getVacationorExtrawork();
		 information.setCatogorys(catogoryString);
		 String vacationreasonString = (String)vacationreasons.getSelectedItem();
		 information.setSubcatogory(vacationreasonString);
		 String timeString = String.valueOf(getlabel().getCal().getyear())+"-"+
				 String.valueOf(getlabel().getCal().getmonth())+"-"+getlabel().getText();								 
		 information.setTime(timeString);
		 
		
	}

}

package approvel;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Calendar.Windows;
import mylayout.MyVFlowLayout;
import ojdbc.DataBaseOperation;
import ojdbc.LocaltestDataBaseOperation;
import tcp.ListInformation;
import user.User;
import java.awt.Color;
public class ApprovalWindows extends Windows {

	ListInformation informationgroup;

	ArrayList<VacationPanel> vacationPanels =  new ArrayList<VacationPanel>();
	public ApprovalWindows(User user,ListInformation information) throws ClassNotFoundException, SQLException {
		this.setUser(user);
		this.informationgroup=information;
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private void initialize() throws ClassNotFoundException, SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 525, 527);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setTitle("请假审批");
		
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		
				
		JPanel panelcontainer = new JPanel();
		scrollPane.setViewportView(panelcontainer);
		MyVFlowLayout mvfl_panelcontainer = new MyVFlowLayout(MyVFlowLayout.TOP,20,20,false,false);
		panelcontainer.setLayout(mvfl_panelcontainer);
		
		if (informationgroup!= null) {
			
			for (ArrayList<String> list : informationgroup.getLineinformationgroup()) {
				VacationPanel vacationPanel = new VacationPanel(list.get(0),list.get(1),list.get(2),list.get(3),list.get(4),list.get(5));
				panelcontainer.add(vacationPanel);
				vacationPanels.add(vacationPanel);
			}
			
		}
		
		else {
			return;
		}
		
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton quicklyapproval = new JButton("\u4E00\u952E\u540C\u610F");
		quicklyapproval.setForeground(Color.DARK_GRAY);
		quicklyapproval.setFont(new Font("幼圆", Font.BOLD, 16));
		panel.add(quicklyapproval);
		
		JButton submitapproval = new JButton("\u63D0\u4EA4\u5BA1\u6279");
		submitapproval.setForeground(Color.DARK_GRAY);
		submitapproval.setFont(new Font("幼圆", Font.BOLD, 16));
		panel.add(submitapproval);
		
		
		
		if (user!=null) {
			
			String userlevel_shape = user.getLevel_shape();
			
			
		}
		
		quicklyapproval.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				for (VacationPanel v:vacationPanels) {
					
					v.Setquickapprovelcheckedstate();;
					System.out.println(v.getWidth());
					System.out.println(v.getHeight());
				}
				
			}
		});
		
		
		submitapproval.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {

				ListInformation datagroupadd = new ListInformation();
				ListInformation datagroupdelete = new ListInformation();
				for (VacationPanel v:vacationPanels) {
					
					if (v.getapprovelcheckedstate() == true) {
												
						ArrayList<String> dataadd = new ArrayList<String>();
						dataadd.add(v.getWokername());
						dataadd.add(v.getWokervacationtime());
						dataadd.add(v.getWokervacationdate());
						datagroupadd.getLineinformationgroup().add(dataadd);																							
					}
					
					
					if (v.getdenycheckedstate() == true) {
						
						ArrayList<String> datadelete = new ArrayList<String>();
						datadelete.add(v.getWokername());
						datadelete.add(v.getWokervacationtime());
						datadelete.add(v.getWokervacationdate());
						datagroupdelete.getLineinformationgroup().add(datadelete);																							
					}
				}
				
//				DataBaseOperation baseOperation = new DataBaseOperation();
				LocaltestDataBaseOperation localtestDataBaseOperation = new LocaltestDataBaseOperation();
				
				try {
					
					if (!datagroupadd.getLineinformationgroup().isEmpty()) {
						localtestDataBaseOperation.Update_VACATION_WORK_NORMOL_APPROVAL_state(datagroupadd);
						frame.dispose();
					}
					
					if (!datagroupdelete.getLineinformationgroup().isEmpty()) {
						localtestDataBaseOperation.Delete_VACATION_WORK_NORMOL_APPROVAL(datagroupdelete);
						frame.dispose();
					}
					
				} catch (ClassNotFoundException e1) {
					ShowDialog("无法连接数据库");
					e1.printStackTrace();
				} catch (SQLException e1) {					
					ShowDialog("无法更新数据，有可能你的数据中含有重复提交的项目");
					e1.printStackTrace();
				}

			}
		});
		
		
		
	}
	
	private void ShowDialog(String word) {
		
		JOptionPane.showMessageDialog(null,word, "提示ʾ", JOptionPane.ERROR_MESSAGE); 
				
	}

}

package approvel;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
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

	ArrayList<ListInformation> informationgroup;

	ArrayList<VacationPanel> vacationPanels =  new ArrayList<VacationPanel>();
	public ApprovalWindows(User user,ArrayList<ListInformation> information) throws ClassNotFoundException, SQLException {
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
		frame.setBounds(100, 100, 530, 527);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setTitle("请假审批");
		
		ImageIcon icon55 = new ImageIcon("src/image/汇景图标.png");		
		frame.setIconImage(icon55.getImage());
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		
				
		JPanel panelcontainer = new JPanel();
		scrollPane.setViewportView(panelcontainer);
		MyVFlowLayout mvfl_panelcontainer = new MyVFlowLayout(MyVFlowLayout.TOP,20,20,false,false);
		panelcontainer.setLayout(mvfl_panelcontainer);
		
		if (informationgroup!= null) {
			
			for (ArrayList<String> list : informationgroup.get(0).getLineinformationgroup()) {
				VacationPanel vacationPanel = new VacationPanel(list.get(0),list.get(1),list.get(2),list.get(3),list.get(4),list.get(5));
				vacationPanel.setMode("单普通审批");
				panelcontainer.add(vacationPanel);
				vacationPanels.add(vacationPanel);
			}
			
			for (ArrayList<String> list : informationgroup.get(1).getLineinformationgroup()) {
				VacationPanel vacationPanel = new VacationPanel(list.get(0),list.get(1),list.get(2),list.get(3),list.get(4),list.get(5));
				vacationPanel.setMode("单高级审批");
				panelcontainer.add(vacationPanel);
				vacationPanels.add(vacationPanel);
			}
			
			for (ArrayList<String> list : informationgroup.get(2).getLineinformationgroup()) {
				VacationPanel vacationPanel = new VacationPanel(list.get(0),list.get(1),list.get(2),list.get(3),list.get(4),list.get(5));
				vacationPanel.setMode("双权限审批");
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

				ListInformation datagroup_singlenormal = new ListInformation();
				ListInformation datagroup_singlespecial = new ListInformation();
				ListInformation datagroup_doubleright = new ListInformation();
				ListInformation datagroup_delete = new ListInformation();
				for (VacationPanel v:vacationPanels) {
					
					if (v.getapprovelcheckedstate() == true) {
						
						
						if (v.getMode() == "单普通审批") {
													
							ArrayList<String> dataadd = new ArrayList<String>();
							dataadd.add(v.getWokername());
							dataadd.add(v.getWokervacationtime());
							dataadd.add(v.getWokervacationdate());
							datagroup_singlenormal.getLineinformationgroup().add(dataadd);
							
							
						}
						
						if (v.getMode() == "单高级审批") {
							
							ArrayList<String> dataadd = new ArrayList<String>();
							dataadd.add(v.getWokername());
							dataadd.add(v.getWokervacationtime());
							dataadd.add(v.getWokervacationdate());
							datagroup_singlespecial.getLineinformationgroup().add(dataadd);
													
						}
					
					    if (v.getMode() == "双权限审批") {
					    	
					    	
							ArrayList<String> dataadd = new ArrayList<String>();
							dataadd.add(v.getWokername());
							dataadd.add(v.getWokervacationtime());
							dataadd.add(v.getWokervacationdate());
							datagroup_doubleright.getLineinformationgroup().add(dataadd);					    	
					   		
					    }
												
					}
					
					
					if (v.getdenycheckedstate() == true) {
						
			
						ArrayList<String> dataadd = new ArrayList<String>();
						dataadd.add(v.getWokername());
						dataadd.add(v.getWokervacationtime());
						dataadd.add(v.getWokervacationdate());
						datagroup_delete.getLineinformationgroup().add(dataadd);	
								
					}
					
				}
				
				DataBaseOperation baseOperation = new DataBaseOperation();
//				LocaltestDataBaseOperation localtestDataBaseOperation = new LocaltestDataBaseOperation();
				
				try {
					
					if ((!datagroup_singlenormal.getLineinformationgroup().isEmpty())||
							(!datagroup_singlespecial.getLineinformationgroup().isEmpty())||
							   (!datagroup_doubleright.getLineinformationgroup().isEmpty()
						)) {
//						localtestDataBaseOperation.Update_VACATION_WORK_NORMOL_APPROVAL_state(datagroup_singlenormal,datagroup_singlespecial,datagroup_doubleright);
						baseOperation.Update_VACATION_WORK_NORMOL_APPROVAL_state(datagroup_singlenormal,datagroup_singlespecial,datagroup_doubleright);
						frame.dispose();
					}
					
					if (!datagroup_delete.getLineinformationgroup().isEmpty()) {
//						localtestDataBaseOperation.Delete_VACATION_WORK_NORMOL_APPROVAL(datagroup_delete);
						baseOperation.Delete_VACATION_WORK_NORMOL_APPROVAL(datagroup_delete);
						ShowDialog("操作成功");
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
package Calendar;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

import MyThread.MyThread;
import approvel.ApprovalWindows;
import backup.BackupDownWindows;
import manager_operation.Manager_download_windows;
import ojdbc.DataBaseOperation;
import ojdbc.LocaltestDataBaseOperation;
import tcp.ListInformation;
import user.LoginInWindows;
import user.User;
import version_information.VersionWindows;

import javax.swing.SwingConstants;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Rectangle;
import javax.swing.border.MatteBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.Color;
import javax.swing.border.EmptyBorder;

public class MainWindows {

	private JFrame frame;
	private User user;

	
	public User getUser() {
		return user;
	}

	public JFrame getFrame() {
		return frame;
	}

	public MainWindows(User user) {
		this.user = user;
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setFont(new Font("微软雅黑", Font.BOLD, 18));
		frame.setTitle("\u6B22\u8FCE\u767B\u5F55\uFF0C"+user.getCheckname());
		frame.setBounds(100, 100, 600, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		ImageIcon icon55 = new ImageIcon("src/image/汇景图标.png");		
		frame.setIconImage(icon55.getImage());
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("黑体", Font.PLAIN, 14));
		frame.setJMenuBar(menuBar);
		
		
		ImageIcon icon11 = new ImageIcon("src/image/考勤功能.png");
		JMenu assessmode = new JMenu("\u8003\u52E4\u529F\u80FD");
		assessmode.setBorder(new EmptyBorder(2, 2, 2, 2));
		assessmode.setIconTextGap(6);
		assessmode.setFont(new Font("幼圆", Font.BOLD, 14));
		assessmode.setIcon(icon11);
		menuBar.add(assessmode);
		
		
		ImageIcon icon1 = new ImageIcon("src/image/提交考勤.png");
		
		JMenuItem submitmode = new JMenuItem("\u63D0\u4EA4\u8003\u52E4");
		submitmode.setIconTextGap(10);
		submitmode.setIcon(icon1);
		submitmode.setHorizontalAlignment(SwingConstants.CENTER);
		submitmode.setFont(new Font("宋体", Font.PLAIN, 12));
		assessmode.add(submitmode);
		
		ImageIcon icon2 = new ImageIcon("src/image/下载.png");
		ImageIcon icon23 = new ImageIcon("src/image/请假审批.png");
		JMenuItem vacationapproval = new JMenuItem("考勤审批");
		assessmode.add(vacationapproval);
		vacationapproval.setIconTextGap(10);
		vacationapproval.setHorizontalAlignment(SwingConstants.CENTER);
		vacationapproval.setFont(new Font("宋体", Font.PLAIN, 12));
		vacationapproval.setIcon(icon23);
		
			
		
		
		vacationapproval.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				DataBaseOperation dataBaseOperation = new DataBaseOperation();
//				LocaltestDataBaseOperation localtestDataBaseOperation = new LocaltestDataBaseOperation();
				ArrayList<ListInformation> informationgroup = null;
				try {
					informationgroup = dataBaseOperation.Selectfrom_DATA_VACATION_WORK_APPROVAL_ForUser(user);
//					informationgroup = localtestDataBaseOperation.Selectfrom_DATA_VACATION_WORK_APPROVAL_ForUser(user);
				} catch (ClassNotFoundException e2) {
					e2.printStackTrace();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				
								
				if (informationgroup!=null) {
					
					ApprovalWindows testwindows;
					try {
						testwindows = new ApprovalWindows(user,informationgroup);
						testwindows.getFrame().setVisible(true);
					} catch (ClassNotFoundException e1) {

						e1.printStackTrace();
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
					
				}
								
			}
		});
		JMenuItem downloadmode = new JMenuItem("\u4E0B\u8F7D\u8003\u52E4");
		downloadmode.setIconTextGap(10);
		downloadmode.setIcon(icon2);
		downloadmode.setHorizontalAlignment(SwingConstants.CENTER);
		downloadmode.setFont(new Font("宋体", Font.PLAIN, 12));
		assessmode.add(downloadmode);
		
		
		ImageIcon icon22 = new ImageIcon("src/image/审批功能.png");
		JMenu checkmode = new JMenu("考核功能");
		checkmode.setBorder(new EmptyBorder(2, 2, 2, 2));
		checkmode.setIconTextGap(6);
		checkmode.setFont(new Font("幼圆", Font.BOLD, 14));
		checkmode.setIcon(icon22);
		menuBar.add(checkmode);
		


		
		ImageIcon icon24 = new ImageIcon("src/image/考勤审批.png");
		
		JMenuItem checksubmit = new JMenuItem("考核提交");
		checksubmit.setIconTextGap(10);
		checksubmit.setHorizontalAlignment(SwingConstants.CENTER);
		checksubmit.setFont(new Font("宋体", Font.PLAIN, 12));
		checksubmit.setEnabled(false);
		checkmode.add(checksubmit);
		
		ImageIcon icon27 = new ImageIcon("src/image/管理功能.png");
		JMenu administration = new JMenu("公司管理");
		administration.setIconTextGap(6);

		administration.setFont(new Font("幼圆", Font.BOLD, 14));
		administration.setBorder(new EmptyBorder(2, 2, 2, 2));
		administration.setIcon(icon27);
		menuBar.add(administration);
		
		ImageIcon icon28 = new ImageIcon("src/image/人员录入.png");
		ImageIcon icon29 = new ImageIcon("src/image/下载.png");
		JMenuItem groupdownload = new JMenuItem("记录下载");
		groupdownload.setIconTextGap(10);
		groupdownload.setHorizontalAlignment(SwingConstants.CENTER);
		groupdownload.setFont(new Font("宋体", Font.PLAIN, 12));
		groupdownload.setIcon(icon29);
		administration.add(groupdownload);
		
			
		
		JMenuItem wokerinput = new JMenuItem("人员录入");
		wokerinput.setIconTextGap(10);
		wokerinput.setHorizontalAlignment(SwingConstants.CENTER);
		wokerinput.setFont(new Font("宋体", Font.PLAIN, 12));
		wokerinput.setEnabled(true);
		wokerinput.setIcon(icon28);
		administration.add(wokerinput);
		
		
		
		ImageIcon icon30 = new ImageIcon("src/image/公司管理.png");
		JMenuItem managerRelation = new JMenuItem("公司管理");
		managerRelation.setIconTextGap(10);
		managerRelation.setHorizontalAlignment(SwingConstants.CENTER);
		managerRelation.setFont(new Font("宋体", Font.PLAIN, 12));
		managerRelation.setIcon(icon30);
		administration.add(managerRelation);
		
		ImageIcon icon25 = new ImageIcon("src/image/关于.png");
		JMenu aboutinformation = new JMenu("\u5173\u4E8E");
		aboutinformation.setBorder(new EmptyBorder(2, 2, 2, 2));
		aboutinformation.setIconTextGap(6);
		aboutinformation.setFont(new Font("幼圆", Font.BOLD, 14));
		aboutinformation.setIcon(icon25);
		menuBar.add(aboutinformation);
		
		ImageIcon icon26 = new ImageIcon("src/image/版本信息.png");
		JMenuItem versioninfo = new JMenuItem("版本信息");
		versioninfo.setIconTextGap(10);
		versioninfo.setHorizontalAlignment(SwingConstants.CENTER);
		versioninfo.setFont(new Font("宋体", Font.PLAIN, 12));
		versioninfo.setIcon(icon26);
		aboutinformation.add(versioninfo);
		
		SubimitWindows SnewSubimitWindows = new SubimitWindows(getUser());
		DownloadWindows DnewSubimitWindows = new DownloadWindows(getUser());

        //设置高级功能是否开启
		
		if (Integer.valueOf(getUser().getPower_level()) == 99) {
			
			administration.setEnabled(true);
		}
		
		
		//设置审批功能是否开启		
					
			checkmode.setEnabled(false);
	
				
        //设置请假审批功能是否开启		
		if (Integer.valueOf(getUser().getVACATION_APPROVAL_NORMAL_RIGHT())!=1&&Integer.valueOf(getUser().getVACATION_APPROVAL_HIGHER_RIGHT())!=1) {
			vacationapproval.setEnabled(false);
		}
			
			JMenu checkgrade = new JMenu("考核打分");
			checkgrade.setIconTextGap(6);
			checkgrade.setFont(new Font("宋体", Font.PLAIN, 12));
			checkgrade.setBorder(new EmptyBorder(2, 2, 2, 2));
			checkmode.add(checkgrade);
			
			JMenuItem nomalcheck = new JMenuItem("普通考核");
			nomalcheck.setIconTextGap(10);
			nomalcheck.setHorizontalAlignment(SwingConstants.CENTER);
			nomalcheck.setFont(new Font("宋体", Font.PLAIN, 12));
			nomalcheck.setEnabled(false);
			checkgrade.add(nomalcheck);
			
			JMenuItem arroundcheck = new JMenuItem("周边绩效");
			arroundcheck.setIconTextGap(10);
			arroundcheck.setHorizontalAlignment(SwingConstants.CENTER);
			arroundcheck.setFont(new Font("宋体", Font.PLAIN, 12));
			arroundcheck.setEnabled(false);
			checkgrade.add(arroundcheck);
//		}
			

		
		
		groupdownload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				//筛选下载范围
				DataBaseOperation DataBaseOperation = new DataBaseOperation();
				Manager_download_windows manager_download_windows;
				ArrayList<String> namelist = null;
				try {
					namelist = DataBaseOperation.SelectDownLoadlist_From_DataBase(getUser());
					if(namelist!=null) {
						String[] stringArray = new String[namelist.size()];
						namelist.toArray(stringArray);
						manager_download_windows = new Manager_download_windows(getUser(),stringArray);
						manager_download_windows.getFrame().setVisible(true);
					}
					
				} catch (ClassNotFoundException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}				
								
			}
		});
		
		
		
		submitmode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				SnewSubimitWindows.getFrame().setVisible(true);
			}
		});
		
		downloadmode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				DnewSubimitWindows.getFrame().setVisible(true);
				
			}
		});
		
		versioninfo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				VersionWindows versionWindows = new VersionWindows();
				versionWindows.getFrame().setVisible(true);
				
			}
		});
		
		
	
	}
	
	private void ShowDialog(String word) {
		
		JOptionPane.showMessageDialog(null,word, "提示", JOptionPane.INFORMATION_MESSAGE); 
				
	}

}
package Calendar;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import manager_operation.Manager_download_windows;
import ojdbc.DataBaseOperation;
import vacation_extrawork.Approvel_N_Dayinformation;
import vacation_extrawork.Dayinformation;
import vacation_extrawork.ExtraWorkWindows;
import vacation_extrawork.MissCheckWindows;
import vacation_extrawork.OneManData;
import vacation_extrawork.VacationWindows;

public class Mylabel extends JLabel implements MouseListener {
	
	private Color choosecolorforvacation;
	private Color choosecolorforextrawork;
	private Color choosecolorfornormalrestday;
	private Color choosecolorformisscheck;
	private Color choosecolorfordownloaddrange;
	private Color nochoosecolor;

	private boolean ischoose = false;
	private String dayclearinformationString;
	private Windows cal;
	private Dayinformation information = new Dayinformation();
	
	public void SetChooseForVacation() {
		setBackground(choosecolorforvacation);
		this.setForeground(Color.white);
		ischoose = true;	
	}
	
	public void SetChooseForExtrawork() {
		setBackground(choosecolorforextrawork);
		this.setForeground(Color.white);
		ischoose = true;	
	}
	
	
	public void SetChooseStateForNormalRestDay()
	{
		setBackground(choosecolorfornormalrestday);
		this.setForeground(Color.white);
		ischoose =true;
	}
	
	public void SetChooseStateForMisscheck()
	{
		setBackground(choosecolorformisscheck);
		this.setForeground(Color.white);
		ischoose =true;
	}
	
	public void SetChooseStateForDownLoaddRange()
	{
		setBackground(choosecolorfordownloaddrange);
		ischoose =true;
	}
	
	public void SetNoChooseState() {
		setBackground(nochoosecolor);
		this.setForeground(Color.black);
		ischoose = false;	
	}
	


	
	public void Removedata(String name) {
			
		ArrayList<OneManData> data= (getNewSubimitWindows().getSubmitdatagroup());		
		Iterator<OneManData> l = data.iterator();
		
		while(l.hasNext()) {
			
			OneManData oneManData = (OneManData)l.next();
			
			if (oneManData.getName().equals(name)) {
				
				ArrayList<Approvel_N_Dayinformation> dayinformations = oneManData.getN_dayinformation();			
				Iterator<Approvel_N_Dayinformation> l2 = dayinformations.iterator();				
				while (l2.hasNext()) {
					
					Dayinformation dayinformation = (Dayinformation)l2.next();
					if (dayinformation.getLabelday().equals(getText())) {
						
						l2.remove();
					}										
				}
				
				if (dayinformations.isEmpty()) {					
					l.remove();					
				}
								
			}
						
		}
				
	}
		
	public Dayinformation getinformation() {
		
		return information;
		
	}
	


	public Color getNochoosecolor() {
		return nochoosecolor;

	}

	public boolean getIschoose() {
		return ischoose;
	}
		
		
	public String getDayclearinformationString() {
		return dayclearinformationString;
	}

	
	public Windows getCal() {		
		return cal;		
		
	}
	
	public SubimitWindows getNewSubimitWindows() {
		return (SubimitWindows)cal;	
	}
	
	public DownloadWindows getDownloadWindows() {
		
		return (DownloadWindows)cal;	
	}
	
	public Manager_download_windows getManager_download_windows() {
		
		return(Manager_download_windows)cal;
	}


	public Mylabel(Windows cal) {
		
		super();
		
		choosecolorforvacation = new Color(217,65,63);
		choosecolorforextrawork = new Color(151,40,41);
		choosecolorfornormalrestday = new Color(140,140,140);
		choosecolorformisscheck = new Color(64,64,64);
		choosecolorfordownloaddrange = new Color(59,204,105);
		nochoosecolor = new Color(255,255,255);
		this.setBackground(nochoosecolor);
		this.cal = cal;
								
	}
	
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {

		if ((getCal().getWindowsMode()).equals("提交模式")) {
			
		    ExecuteSubmitMode();
		}

		else if ((getCal().getWindowsMode()).equals("下载模式")) {
								
			ExecuteDownloadMode();
		}
		
		else if ((getCal().getWindowsMode()).equals("管理员下载模式")) {
			
			ExecuteManager_download_windowsDownloadMode();
		}
		
	}
	
	



	private void ShowDialog(String word) {
		
		JOptionPane.showMessageDialog(null,word, "提示ʾ", JOptionPane.ERROR_MESSAGE); 
				
	}

	
	
	public void setdata(String Actualtimenoclear,String Explainreason) {
		 
		 String nameString = getCal().getUser().getCheckname();	 
		 Approvel_N_Dayinformation information = new Approvel_N_Dayinformation();
		 String catogoryString = (getNewSubimitWindows().getVacationorExtrawork());
		 
		 String timeString = String.valueOf(getNewSubimitWindows().getyear())+"-"+
				 String.valueOf(getNewSubimitWindows().getmonth())+"-"+getText();
		 Date datetime = Date.valueOf(timeString);
		 
		 information.setreasons(catogoryString);
		 information.setreasons_details("正常休假");
		 information.setTime(datetime);
		 information.setLabelday(getText());		 
		 information.setActualtimenoclear(Actualtimenoclear);
		 information.setreasons_explanation(null);
		 information.sethandleovertimework(null);
		 
		 
		 information.setVACATION_NORMAL_PASSED(0);
		 information.setVACATION_SPECIAL_PASSED(0);
		 String level_shape = getCal().getUser().getLevel_shape();
		 information.setLEVEL_SHAPE(level_shape);
		 information.setDEPARTMENT(getCal().getUser().getDepartmentString());
		 information.setVACATION_APPROVAL_NORMAL_UPPER(getCal().getUser().getVACATION_APPROVAL_NORMAL_UPPER());
		 information.setVACATION_APPROVAL_HIGHER_UPPER(getCal().getUser().getVACATION_APPROVAL_HIGHER_UPPER());
		 
		 
		 CheckTheManExist(nameString);
		 
		 for (int i=0;i<(getNewSubimitWindows().getSubmitdatagroup().size());i++) {
			if (nameString.equals(getNewSubimitWindows().getSubmitdatagroup().get(i).getName())) {				
				(getNewSubimitWindows().getSubmitdatagroup().get(i).getN_dayinformation()).add(information);				
			}

		}
		 
		 
		 System.out.println(nameString+"写入假期信息完成");
		
	}
	
	
	private void CheckTheManExist(String name) {
		
		 boolean exist = false;
		 for (int i=0;i<(getNewSubimitWindows().getSubmitdatagroup()).size();i++) {
			if (name.equals((getNewSubimitWindows().getSubmitdatagroup()).get(i).getName())) {				
				exist = true;		
			}
		 }
		 
		 if (!exist) {
			
			 CreateOneManData(name);
			 
		 }		
	}
	
	
	
	private void CreateOneManData(String name) {
		
		OneManData OnePersondata = new OneManData(new ArrayList<Approvel_N_Dayinformation>());
		OnePersondata.setName(name);

		int id = -1;	
		id = Integer.valueOf(getNewSubimitWindows().getUser().getId());
		
		if (id != -1) {
			
			OnePersondata.setId(id);
			(getNewSubimitWindows().getSubmitdatagroup()).add(OnePersondata);
			
		}
		else {
			System.out.println("id查询错误，放弃添加人员信息");
		}
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {

		
	}

	@Override
	public void mouseExited(MouseEvent e) {

		
	}
	
	public void ExecuteSubmitMode() {
		
		  if (!ischoose) {
				
				ischoose = true;						
				String optionchoose = (getNewSubimitWindows().getVacationorExtrawork());
				
				if (optionchoose.equals("请假")) {
					
					SetChooseForVacation();
								
					VacationWindows Vwindow = new VacationWindows(this);
					Vwindow.getFrame().setVisible(true);		
					Vwindow.getFrame().addWindowListener(new WindowAdapter() {					
						@Override
						public void windowClosed(WindowEvent e) {
							
							if (Vwindow.getnoinformationsubmit()) {
								
								SetNoChooseState();
								ShowDialog("没有提交或者设置任何信息");
								return;
								
							}
							
							else {
								
								System.out.println("成功提交信息");
								
							}
							
						}
						
						@Override
						public void windowClosing(WindowEvent e) {
							
							SetNoChooseState();
							return;
						}
						
					});
				
			      }
				
				else if (optionchoose.equals("加班")) {
					
					SetChooseForExtrawork();			
				    ExtraWorkWindows ewindoWindow = new ExtraWorkWindows(this);
				    ewindoWindow.getFrame().setVisible(true);
				    ewindoWindow.getFrame().addWindowListener(new WindowAdapter() {
				    	
				    	@Override
				    	public void windowClosed(WindowEvent e) {
				    				
							if (ewindoWindow.getnoinformationsubmit()) {
								
								SetNoChooseState();
								ShowDialog("没有提交或者设置任何信息");
								return;
								
							}
							
							else {
								
								System.out.println("成功提交信息");
								
							}
				    		    		

				    	}
				    	
					});
							
			      }
				
					
			    else if (optionchoose.equals("未打卡")) {
					
			    	SetChooseStateForMisscheck();			
				    MissCheckWindows missCheckWindows = new MissCheckWindows(this);
				    missCheckWindows.getFrame().setVisible(true);
				    missCheckWindows.getFrame().addWindowListener(new WindowAdapter() {
				    	
				    	@Override
				    	public void windowClosed(WindowEvent e) {
				    				
							if (missCheckWindows.getnoinformationsubmit()) {
								
								SetNoChooseState();
								ShowDialog("没有提交或者设置任何信息");
								return;
								
							}
							
							else {
								
								System.out.println("成功提交信息");
								
							}
				    		    		

				    	}
				    	
					});
			    	
				}
				
			   else if (optionchoose.equals("正常休假")) {
					
					SetChooseStateForNormalRestDay();					
					setdata("全天未打", "正常休假");
													
				}
				
						
			    else {
				
				     ShowDialog("提交信息错误，联系管理员");
			    }
				
			  }
													
			   else {
				
				Removedata(getCal().getUser().getCheckname());		
				SetNoChooseState();
				
			   }
		
	}
	
	public void ExecuteDownloadMode() {
		
		
		if (!ischoose) {
			
			String nowchoosedateString = String.valueOf(getDownloadWindows().getyear())+"-"+String.valueOf(getDownloadWindows().getmonth())+"-"+getText();			
			Date nowDate = Date.valueOf(nowchoosedateString);
			
			  if ((getDownloadWindows().getPeriod())[0] == null ) {
				
				(getDownloadWindows().getPeriod())[0] = nowDate;
				SetChooseStateForDownLoaddRange();

				
			  }
			
			  else {
				
				if ((getDownloadWindows().getPeriod())[1] == null) {
										
					(getDownloadWindows().getPeriod())[1] = nowDate;
					SetChooseStateForDownLoaddRange();
					
				}
				
				else {
					
					ShowDialog("日期设置满了必须先删除一个，再次点击设置好的日期可以删除");
										
				 }
											
			   }
						   			
			 }
			
			
			else {
				
				String nowchoosedateString = String.valueOf(getDownloadWindows().getyear())+"-"+String.valueOf(getDownloadWindows().getmonth())+"-"+getText();			
				Date nowDate = Date.valueOf(nowchoosedateString);
				for (int i = 0; i < 2;i++) {					
										
					if ((getDownloadWindows().getPeriod())[i] != null) {
												
						if ((getDownloadWindows().getPeriod())[i].equals(nowDate)) {
							(getDownloadWindows().getPeriod())[i] = null;
							SetNoChooseState();
							break;													
						}
												
					}
					
					
				}
				
			}
	
	}
	
	private void ExecuteManager_download_windowsDownloadMode() {
		
		if (!ischoose) {
			
			String nowchoosedateString = String.valueOf(getManager_download_windows().getyear())+"-"+String.valueOf(getManager_download_windows().getmonth())+"-"+getText();			
			Date nowDate = Date.valueOf(nowchoosedateString);
			
			  if ((getManager_download_windows().getPeriod())[0] == null ) {
				
				(getManager_download_windows().getPeriod())[0] = nowDate;
				SetChooseStateForDownLoaddRange();

				
			  }
			
			  else {
				
				if ((getManager_download_windows().getPeriod())[1] == null) {
										
					(getManager_download_windows().getPeriod())[1] = nowDate;
					SetChooseStateForDownLoaddRange();
					
				}
				
				else {
					
					ShowDialog("日期设置满了必须先删除一个，再次点击设置好的日期可以删除");
										
				 }
											
			   }
						   			
			 }
			
			
			else {
				
				String nowchoosedateString = String.valueOf(getManager_download_windows().getyear())+"-"+String.valueOf(getManager_download_windows().getmonth())+"-"+getText();			
				Date nowDate = Date.valueOf(nowchoosedateString);
				for (int i = 0; i < 2;i++) {					
										
					if ((getManager_download_windows().getPeriod())[i] != null) {
												
						if ((getManager_download_windows().getPeriod())[i].equals(nowDate)) {
							(getManager_download_windows().getPeriod())[i] = null;
							SetNoChooseState();
							break;													
						}
												
					}
					
					
				}
				
			}
		
	}
		

}
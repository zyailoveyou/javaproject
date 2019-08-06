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

import ojdbc.DataBaseOperation;
import vacation_extrawork.Dayinformation;
import vacation_extrawork.ExtraWorkWindows;
import vacation_extrawork.OneManData;
import vacation_extrawork.VacationWindows;

public class Mylabel extends JLabel implements MouseListener {
	
	private Color choosecolor;
	private Color choosecolorfornormalrestdayColor;
	private Color nochoosecolor;
	private boolean ischoose = false;
	private String dayclearinformationString;
	private Windows cal;
	private Dayinformation information = new Dayinformation();
	
	public void SetChooseState() {
		setBackground(choosecolor);
		ischoose = true;	
	}
	
	public void SetNoChooseState() {
		setBackground(nochoosecolor);
		ischoose = false;	
	}
	
	public void SetChooseStatefornormalrestdayColor()
	{
		setBackground(choosecolorfornormalrestdayColor);
		ischoose =true;
	}

	
	public void Removedata(String name) {
			
		ArrayList<OneManData> data= (getNewSubimitWindows().getSubmitdatagroup());		
		Iterator<OneManData> l = data.iterator();
		
		while(l.hasNext()) {
			
			OneManData oneManData = (OneManData)l.next();
			
			if (oneManData.getName().equals(name)) {
				
				ArrayList<Dayinformation> dayinformations = oneManData.getDayinformation();			
				Iterator<Dayinformation> l2 = dayinformations.iterator();				
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
	
	public Color getChoosecolor() {
		return choosecolor;
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


	public Mylabel(Windows cal) {
		
		super();
		choosecolor = new Color(255,206, 82);
		nochoosecolor = new Color(255,255,255);
		choosecolorfornormalrestdayColor = new Color(0,204,255);
		this.setBackground(nochoosecolor);
		this.cal = cal;
								
	}
	
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//提交模式执行代码
		if ((getCal().getWindowsMode()).equals("提交模式")) {
			
		  if (!ischoose) {
						
			ischoose = true;						
			String optionchoose = (getNewSubimitWindows().getVacationorExtrawork());
			
			if (optionchoose.equals("请假")) {
				
				setBackground(choosecolor);
							
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
				
				setBackground(choosecolor);				
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
			
			else if (optionchoose.equals("正常休假")) {
				
				SetChooseStatefornormalrestdayColor();							
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
		//提交模式执行代码完毕
		
		//下载模式执行代码
//		else if ((getCal().getWindowsMode()).equals("下载模式")) {
//			
//			
//			if (!ischoose) {
//										
//			String nowchoosedateString = String.valueOf(getCal().getyear())+"-"+String.valueOf(getCal().getmonth())+"-"+getText();			
//			Date nowDate = Date.valueOf(nowchoosedateString);
//			
//			  if ((getCal().getPeriod())[0] == null ) {
//				
//				(getCal().getPeriod())[0] = nowDate;
//				SetChooseStatefornormalrestdayColor();
//
//				
//			  }
//			
//			  else {
//				
//				if ((getCal().getPeriod())[1] == null) {
//										
//					(getCal().getPeriod())[1] = nowDate;
//					SetChooseStatefornormalrestdayColor();
//					
//				}
//				
//				else {
//					
//					ShowDialog("日期设置满了必须先删除一个，再次点击设置好的日期可以删除");
//					
//					
//				 }
//											
//			   }
//						   			
//			 }
//			
//			
//			else {
//				
//				String nowchoosedateString = String.valueOf(getCal().getyear())+"-"+String.valueOf(getCal().getmonth())+"-"+getText();			
//				Date nowDate = Date.valueOf(nowchoosedateString);
//				for (int i = 0; i < 2;i++) {					
//										
//					if ((getCal().getPeriod())[i] != null) {
//												
//						if ((getCal().getPeriod())[i].equals(nowDate)) {
//							(getCal().getPeriod())[i] = null;
//							SetNoChooseState();
//							break;													
//						}
//												
//					}
//					
//					
//				}
//				
//			}
//			
//		
//			
//		}
		
	}
	
	

	private void ShowDialog(String word) {
		
		JOptionPane.showMessageDialog(null,word, "错误提示", JOptionPane.ERROR_MESSAGE); 
				
	}

	
	
	public void setdata(String Actualtimenoclear,String Explainreason) {
		 
		 String nameString = getCal().getUser().getCheckname();	 
		 Dayinformation information = new Dayinformation();
		 String catogoryString = (getNewSubimitWindows().getVacationorExtrawork());


		 String timeString = String.valueOf(getNewSubimitWindows().getyear())+"-"+
				 String.valueOf(getNewSubimitWindows().getmonth())+"-"+getText();
		 Date datetime = Date.valueOf(timeString);
		 

		 information.setreasons(catogoryString);
		 information.setreasons_details("正常休假");
		 information.setTime(datetime);
		 information.setLabelday(getText());		 
		 information.setActualtimenoclear(Actualtimenoclear);
		 information.setreasons_explanation(Explainreason);
		 information.sethandleovertimework(null);
		 
		 
		 CheckTheManExist(nameString);
		 
		 for (int i=0;i<(getNewSubimitWindows().getSubmitdatagroup().size());i++) {
			if (nameString.equals(getNewSubimitWindows().getSubmitdatagroup().get(i).getName())) {				
				(getNewSubimitWindows().getSubmitdatagroup().get(i).getDayinformation()).add(information);				
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
		
		OneManData OnePersondata = new OneManData(new ArrayList<Dayinformation>());
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
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
		

}

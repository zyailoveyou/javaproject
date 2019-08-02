package Calendar;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.ListIterator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import ojdbc.DataBaseOperation;
import vacation_extrawork.Dayinformation;
import vacation_extrawork.ExtraWorkWindows;
import vacation_extrawork.OneManData;
import vacation_extrawork.VacationWindows;

public class mylabel extends JLabel implements MouseListener {
	
	private Color choosecolor;
	private Color choosecolorfornormalrestdayColor;
	private Color nochoosecolor;
	private boolean ischoose = false;
	private String dayclearinformationString;
	private CalendarWindows cal;
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
			
		ArrayList<OneManData> data= getCal().getsubmitdatagroup();		
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

	
	public CalendarWindows getCal() {
		return cal;
	}

	public mylabel(CalendarWindows cal) {
		
		super();
		choosecolor = new Color(255,206, 82);
		nochoosecolor = new Color(255,255,255);
		choosecolorfornormalrestdayColor = new Color(0,204,255);
		this.setBackground(nochoosecolor);
		this.cal = cal;
								
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//�ύģʽִ�д���
		if (((String)getCal().getmode().getSelectedItem()).equals("�ύģʽ")) {
			
		  if (!ischoose) {
						
			ischoose = true;						
			String optionchoose = getCal().getVacationorExtrawork();
			
			if (optionchoose.equals("���")) {
				
				setBackground(choosecolor);
							
				VacationWindows Vwindow = new VacationWindows(this);
				Vwindow.getFrame().setVisible(true);		
				Vwindow.getFrame().addWindowListener(new WindowAdapter() {					
					@Override
					public void windowClosed(WindowEvent e) {
						
						if (Vwindow.getnoinformationsubmit()) {
							
							SetNoChooseState();
							ShowDialog("û���ύ���������κ���Ϣ");
							return;
							
						}
						
						else {
							
							System.out.println("�ɹ��ύ��Ϣ");
							
						}
						
					}
					
					@Override
					public void windowClosing(WindowEvent e) {
						
						SetNoChooseState();
						return;
					}
					
				});
			
		      }
			
			else if (optionchoose.equals("�Ӱ�")) {
				
				setBackground(choosecolor);				
			    ExtraWorkWindows ewindoWindow = new ExtraWorkWindows(this);
			    ewindoWindow.getFrame().setVisible(true);
			    ewindoWindow.getFrame().addWindowListener(new WindowAdapter() {
			    	
			    	@Override
			    	public void windowClosed(WindowEvent e) {
			    				
						if (ewindoWindow.getnoinformationsubmit()) {
							
							SetNoChooseState();
							ShowDialog("û���ύ���������κ���Ϣ");
							return;
							
						}
						
						else {
							
							System.out.println("�ɹ��ύ��Ϣ");
							
						}
			    		    		

			    	}
			    	
				});
						
		      }
			
			else if (optionchoose.equals("�����ݼ�")) {
				
				SetChooseStatefornormalrestdayColor();							
				setdata("ȫ��δ��", "�����ݼ�");
												
			}
			
					
		    else {
			
			     ShowDialog("�ύ��Ϣ������ϵ����Ա");
		    }
			
		  }
												
		   else {
			
			Removedata((String)getCal().getNamelist().getSelectedItem());		
			SetNoChooseState();
			
		   }
		  
		}
		//�ύģʽִ�д������
		
		//����ģʽִ�д���
		else if (((String)getCal().getmode().getSelectedItem()).equals("����ģʽ")) {
			
			
			if (!ischoose) {
										
			String nowchoosedateString = String.valueOf(getCal().getyear())+"-"+String.valueOf(getCal().getmonth())+"-"+getText();			
			Date nowDate = Date.valueOf(nowchoosedateString);
			
			  if ((getCal().getPeriod())[0] == null ) {
				
				(getCal().getPeriod())[0] = nowDate;
				SetChooseStatefornormalrestdayColor();

				
			  }
			
			  else {
				
				if ((getCal().getPeriod())[1] == null) {
										
					(getCal().getPeriod())[1] = nowDate;
					SetChooseStatefornormalrestdayColor();
					
				}
				
				else {
					
					ShowDialog("�����������˱�����ɾ��һ�����ٴε�����úõ����ڿ���ɾ��");
					
					
				 }
											
			   }
						   			
			 }
			
			
			else {
				
				String nowchoosedateString = String.valueOf(getCal().getyear())+"-"+String.valueOf(getCal().getmonth())+"-"+getText();			
				Date nowDate = Date.valueOf(nowchoosedateString);
				for (int i = 0; i < 2;i++) {					
										
					if ((getCal().getPeriod())[i] != null) {
												
						if ((getCal().getPeriod())[i].equals(nowDate)) {
							(getCal().getPeriod())[i] = null;
							SetNoChooseState();
							break;													
						}
												
					}
					
					
				}
				
			}
			
			
//		    FillDateBetweenTheSetTime();
			
		}
		
	}
	
	
//	private void FillDateBetweenTheSetTime() {
//		
//		
//		if ((getCal().getPeriod())[0]!=null&& (getCal().getPeriod())[1]!=null) {
//			
//			
//			
//			
//			
//		}
//		
//		
//		
//	}

	private void ShowDialog(String word) {
		
		JOptionPane.showMessageDialog(null,word, "������ʾ", JOptionPane.ERROR_MESSAGE); 
				
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}
	
	
	private void ChecingToCreateTheManInfo(String name) {
		
		
		
	}
	
	
	public void setdata(String Actualtimenoclear,String Explainreason) {
		 
		 String nameString = (String)getCal().getNamelist().getSelectedItem();	 
		 Dayinformation information = new Dayinformation();
		 String catogoryString = getCal().getVacationorExtrawork();


		 String timeString = String.valueOf(getCal().getyear())+"-"+
				 String.valueOf(getCal().getmonth())+"-"+getText();
		 Date datetime = Date.valueOf(timeString);
		 

		 information.setreasons(catogoryString);
		 information.setreasons_details("�����ݼ�");
		 information.setTime(datetime);
		 information.setLabelday(getText());		 
		 information.setActualtimenoclear(Actualtimenoclear);
		 information.setreasons_explanation(Explainreason);
		 information.sethandleovertimework(null);
		 
		 
		 CheckTheManExist(nameString);
		 
		 for (int i=0;i<getCal().getsubmitdatagroup().size();i++) {
			if (nameString.equals(getCal().getsubmitdatagroup().get(i).getName())) {				
				getCal().getsubmitdatagroup().get(i).getDayinformation().add(information);				
			}

		}
		 
		 
		 System.out.println(nameString+"д�������Ϣ���");
		
	}
	
	
	private void CheckTheManExist(String name) {
		
		 boolean exist = false;
		 for (int i=0;i<getCal().getsubmitdatagroup().size();i++) {
			if (name.equals(getCal().getsubmitdatagroup().get(i).getName())) {				
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
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		
		if (id != -1) {
			
			OnePersondata.setId(id);
			getCal().getsubmitdatagroup().add(OnePersondata);
			
		}
		else {
			System.out.println("id��ѯ���󣬷���������Ա��Ϣ");
		}
		
		
	}
		

}
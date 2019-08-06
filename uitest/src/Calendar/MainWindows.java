package Calendar;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import MyThread.MyThread;
import user.LoginInWindows;
import user.user;

public class MainWindows {

	private JFrame frame;
	private user user;

	
	public user getUser() {
		return user;
	}

	public JFrame getFrame() {
		return frame;
	}

	public MainWindows(user user) {
		this.user = user;
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setFont(new Font("΢���ź�", Font.BOLD, 15));
		frame.setTitle("\u6B22\u8FCE\u767B\u5F55\uFF0C"+user.getCheckname());
		frame.setBounds(100, 100, 479, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("����", Font.PLAIN, 14));
		frame.setJMenuBar(menuBar);
		
		JMenu modeselect = new JMenu("\u6A21\u5F0F\u9009\u62E9");
		modeselect.setFont(new Font("΢���ź�", Font.BOLD, 14));
		menuBar.add(modeselect);
		
		JMenuItem submitmode = new JMenuItem("\u63D0\u4EA4\u6A21\u5F0F");
		modeselect.add(submitmode);
		
		JMenuItem downloadmode = new JMenuItem("\u4E0B\u8F7D\u6A21\u5F0F");
		modeselect.add(downloadmode);
		
		JMenuItem approvalmode = new JMenuItem("\u5BA1\u6279\u6A21\u5F0F");
		modeselect.add(approvalmode);
		
		JMenu aboutinformation = new JMenu("\u5173\u4E8E");
		aboutinformation.setFont(new Font("΢���ź�", Font.BOLD, 14));
		menuBar.add(aboutinformation);
		
		submitmode.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SubimitWindows newSubimitWindows = new SubimitWindows(getUser());
				MyThread myThread = new MyThread(newSubimitWindows);				
				Thread starThread = new Thread(myThread);
				starThread.start();
			}
		});
	
	}

}

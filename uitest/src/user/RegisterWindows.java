package user;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextPane;
import java.awt.GridBagConstraints;
import java.awt.TextField;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import ojdbc.DataBaseOperation;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RegisterWindows {

	private JFrame frame;
	private JTextField usenametextfiled;
	private JTextField checknamefield;
	private JPasswordField passwordtextfiled;
	private JPasswordField CetificatePasswordField;

	
	public JFrame getFrame() {
		return frame;
	}
	
	public RegisterWindows() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\u6CE8\u518C\u7A97\u53E3");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setLocationRelativeTo(null);
		JPanel northfillin = new JPanel();
		FlowLayout fl_northfillin = (FlowLayout) northfillin.getLayout();
		fl_northfillin.setVgap(20);
		fl_northfillin.setHgap(10);
		frame.getContentPane().add(northfillin, BorderLayout.NORTH);
		
		JPanel centerfillin = new JPanel();
		frame.getContentPane().add(centerfillin, BorderLayout.CENTER);
		
		Box ChecknameBox = Box.createHorizontalBox();
		
		JLabel label_1 = new JLabel("\u5458\u5DE5\u59D3\u540D\uFF1A");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 16));
		ChecknameBox.add(label_1);
		
		checknamefield = new JTextField();
		checknamefield.setColumns(10);
		ChecknameBox.add(checknamefield);
		
		Box UsenameBox = Box.createHorizontalBox();
		
		JLabel usernamelabel = new JLabel("\u6CE8\u518C\u5E10\u53F7\uFF1A");
		usernamelabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
		UsenameBox.add(usernamelabel);
		
		usenametextfiled = new JTextField();
		UsenameBox.add(usenametextfiled);
		usenametextfiled.setColumns(10);
		
		Box PasswordBox = Box.createHorizontalBox();
		
		JLabel passwordlable = new JLabel("\u6CE8\u518C\u5BC6\u7801\uFF1A");
		passwordlable.setFont(new Font("微软雅黑", Font.BOLD, 16));
		PasswordBox.add(passwordlable);
		
		passwordtextfiled = new JPasswordField();
		PasswordBox.add(passwordtextfiled);
		
		Box CetificatePasswordBox = Box.createHorizontalBox();
		
		JLabel CetificatePasswordlable = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		CetificatePasswordlable.setFont(new Font("微软雅黑", Font.BOLD, 16));
		CetificatePasswordBox.add(CetificatePasswordlable);
		
		CetificatePasswordField = new JPasswordField();
		CetificatePasswordBox.add(CetificatePasswordField);
		
		GroupLayout gl_centerfillin = new GroupLayout(centerfillin);
		gl_centerfillin.setHorizontalGroup(
			gl_centerfillin.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_centerfillin.createSequentialGroup()
					.addGroup(gl_centerfillin.createParallelGroup(Alignment.LEADING)
						.addComponent(ChecknameBox, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
						.addComponent(UsenameBox, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
						.addComponent(PasswordBox, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
						.addComponent(CetificatePasswordBox, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_centerfillin.setVerticalGroup(
			gl_centerfillin.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_centerfillin.createSequentialGroup()
					.addGap(10)
					.addComponent(ChecknameBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(UsenameBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(PasswordBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(CetificatePasswordBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(15);
		ChecknameBox.add(panel);
		
		JLabel label = new JLabel("\u6240\u5C5E\u90E8\u95E8\uFF1A");
		ChecknameBox.add(label);
		label.setFont(new Font("微软雅黑", Font.BOLD, 16));
		
		JComboBox departmentfield = new JComboBox();
		departmentfield.setModel(new DefaultComboBoxModel(new String[] {"\u5DE5\u7A0B\u90E8", "\u529E\u516C\u5BA4", "\u8D22\u52A1\u90E8", "\u9500\u552E\u90E8"}));
		departmentfield.setFont(new Font("微软雅黑", Font.BOLD, 16));
		ChecknameBox.add(departmentfield);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_1.getLayout();
		flowLayout_3.setHgap(1);
		ChecknameBox.add(panel_1);
		centerfillin.setLayout(gl_centerfillin);
		
		JPanel southfillin = new JPanel();
		frame.getContentPane().add(southfillin, BorderLayout.SOUTH);
		
		JButton register = new JButton("\u6CE8\u518C");
		register.setFont(new Font("微软雅黑", Font.BOLD, 16));
		GroupLayout gl_southfillin = new GroupLayout(southfillin);
		gl_southfillin.setHorizontalGroup(
			gl_southfillin.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_southfillin.createSequentialGroup()
					.addGap(188)
					.addComponent(register)
					.addContainerGap(191, Short.MAX_VALUE))
		);
		gl_southfillin.setVerticalGroup(
			gl_southfillin.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_southfillin.createSequentialGroup()
					.addComponent(register)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		southfillin.setLayout(gl_southfillin);
		
		register.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO 自动生成的方法存根
				
			}
		});
		
		JPanel westfillin = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) westfillin.getLayout();
		flowLayout_1.setHgap(20);
		frame.getContentPane().add(westfillin, BorderLayout.WEST);
		
		JPanel eastfillin = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) eastfillin.getLayout();
		flowLayout_2.setHgap(20);
		frame.getContentPane().add(eastfillin, BorderLayout.EAST);
		
		
		register.addMouseListener(new MouseAdapter() {
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				String usernameString = usenametextfiled.getText();
				String checknameString = checknamefield.getText();
				String passwordString = passwordtextfiled.getPassword().toString();
				String cetificationString = CetificatePasswordField.getPassword().toString();

				String departmentString = (String)departmentfield.getSelectedItem();
				
				int usernamelength = usernameString.length();
				int passwordlength = passwordString.length();
				
				if (checknameString.equals("")) {
					ShowDialog("员工姓名不能为空");
					return;
				}
				
				if (usernamelength<6) {
					
					ShowDialog("用户名长度小于6位");
					return;
				}
				
				if (passwordlength<6) {
					ShowDialog("密码长度小于6位");
					return;
					
				}
				
				if (!passwordString.equals(cetificationString)) {
					ShowDialog("两次输入的密码不一致");
					return;
					
				}
				
				
				try {
					boolean result = user.RegisterUser(checknameString, usernameString, passwordString, departmentString);
					if (result == true) {
						ShowDialog("注册成功");
					}
				} catch (ClassNotFoundException e1) {
					ShowDialog("数据库连接错误");
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					ShowDialog("注册失败,该员工已经注册帐号，或者注册人员姓名不是员工姓名");
					e1.printStackTrace();
				}
				
				
			}
		});
		
	}
	
	
	
	
	
    private void ShowDialog(String word) {

         JOptionPane.showMessageDialog(null,word, "错误提示", JOptionPane.ERROR_MESSAGE); 
		
    }
	
	

}

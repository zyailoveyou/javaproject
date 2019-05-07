package windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import java.awt.Component;
import javax.swing.SwingConstants;

public class ExtraWorkWindows {

	private JFrame frame;
	
	


	public JFrame getFrame() {
		return frame;
	}

	public ExtraWorkWindows() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JComboBox extraworktype = new JComboBox();
		extraworktype.setPreferredSize(new Dimension(38, 40));
		extraworktype.setFont(new Font("黑体", Font.BOLD, 18));
		extraworktype.setModel(new DefaultComboBoxModel(new String[] {"\u4F11\u606F\u65E5\u52A0\u73ED", "\u6CD5\u5B9A\u8282\u5047\u65E5\u52A0\u73ED", "\u8D85\u65F6\u52A0\u73ED", "\u7EDF\u4E00\u52A0\u73ED"}));
		frame.getContentPane().add(extraworktype, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JCheckBox morningextrawork = new JCheckBox("\u4E0A\u5348\u52A0\u73ED");
		morningextrawork.setFont(new Font("黑体", Font.PLAIN, 16));
		morningextrawork.setHorizontalAlignment(SwingConstants.CENTER);
		morningextrawork.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(morningextrawork);
		
		JCheckBox afternoonextrawork = new JCheckBox("\u4E0B\u5348\u52A0\u73ED");
		afternoonextrawork.setFont(new Font("黑体", Font.PLAIN, 16));
		afternoonextrawork.setHorizontalAlignment(SwingConstants.CENTER);
		afternoonextrawork.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(afternoonextrawork);
		
		JCheckBox wholedayextrawork = new JCheckBox("\u5168\u5929\u52A0\u73ED");
		wholedayextrawork.setFont(new Font("黑体", Font.PLAIN, 16));
		wholedayextrawork.setHorizontalAlignment(SwingConstants.CENTER);
		wholedayextrawork.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(wholedayextrawork);
		
		JButton submitextrawork = new JButton("\u63D0\u4EA4");
		submitextrawork.setFont(new Font("黑体", Font.BOLD, 16));
		submitextrawork.setPreferredSize(new Dimension(93, 40));
		frame.getContentPane().add(submitextrawork, BorderLayout.SOUTH);
	}

}

package register;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import register.Program.State;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField TID;
	private JTextField PASS;

	public login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 221, 320);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmAddTeacher = new JMenuItem("Add Teacher");
		mntmAddTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame parent = new JFrame();
			    JOptionPane.showMessageDialog(parent, "Ha Ha you can not do this!!!!");
			}
		});
		mnFile.add(mntmAddTeacher);
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mntmClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmClose);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmInstruction = new JMenuItem("Instruction");
		mntmInstruction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame parent = new JFrame();

			    JOptionPane.showMessageDialog(parent, "Enter your 'Username' and 'Password' then hit the 'Attempt' button");
			}
		});
		mnHelp.add(mntmInstruction);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, contentPane);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 5, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel, 429, SpringLayout.WEST, contentPane);
		contentPane.add(lblNewLabel);
		
		TID = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, TID, 24, SpringLayout.SOUTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, TID, 73, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, TID, -15, SpringLayout.EAST, contentPane);
		contentPane.add(TID);
		TID.setColumns(10);
		
		JButton LOGINBTN = new JButton("Attempt");
		sl_contentPane.putConstraint(SpringLayout.NORTH, LOGINBTN, 139, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, LOGINBTN, 20, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, LOGINBTN, -15, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, LOGINBTN, 0, SpringLayout.EAST, TID);
		LOGINBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(TID.getText().toString().length() > 0  && PASS.getText().toString().length() > 0)
				{
					if(Program.createTeacher(TID.getText().toString().toLowerCase(), PASS.getText().toString().toLowerCase()))
					{
						Program.changeState(State.Class);	
					}else{
						JFrame parent = new JFrame();

					    JOptionPane.showMessageDialog(parent, "Incorrect login details!");
					}
				}
						
			}
		});
		contentPane.add(LOGINBTN);
		
		PASS = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.WEST, PASS, 73, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, PASS, -25, SpringLayout.NORTH, LOGINBTN);
		sl_contentPane.putConstraint(SpringLayout.EAST, PASS, -18, SpringLayout.EAST, contentPane);
		contentPane.add(PASS);
		PASS.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblUsername, 0, SpringLayout.NORTH, TID);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblUsername, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblPassword, 0, SpringLayout.NORTH, PASS);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblPassword, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblPassword);
	}
}

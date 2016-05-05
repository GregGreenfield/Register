package register;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import register.Program.State;

import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField TID;
	private JTextField PASS;

	/**
	 * Create the frame.
	 */
	public login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 221, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 5, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel, 429, SpringLayout.WEST, contentPane);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username :");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 19, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_1, 5, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, 109, SpringLayout.NORTH, contentPane);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 80, SpringLayout.SOUTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_2, 5, SpringLayout.WEST, contentPane);
		contentPane.add(lblNewLabel_2);
		
		TID = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, TID, 24, SpringLayout.SOUTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, TID, 13, SpringLayout.EAST, lblNewLabel_1);
		contentPane.add(TID);
		TID.setColumns(10);
		
		JButton LOGINBTN = new JButton("Attempt");
		LOGINBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(TID.getText().toString().length() > 0  && PASS.getText().toString().length() > 0)
					if(Program.createTeacher(TID.getText().toString().toLowerCase(), PASS.getText().toString().toLowerCase()))
						Program.changeState(State.Register);	
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, LOGINBTN, 164, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, LOGINBTN, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, LOGINBTN, -55, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, LOGINBTN, -35, SpringLayout.EAST, contentPane);
		contentPane.add(LOGINBTN);
		
		PASS = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.EAST, TID, 0, SpringLayout.EAST, PASS);
		sl_contentPane.putConstraint(SpringLayout.WEST, PASS, -84, SpringLayout.EAST, LOGINBTN);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, PASS, 0, SpringLayout.SOUTH, lblNewLabel_2);
		sl_contentPane.putConstraint(SpringLayout.EAST, PASS, 20, SpringLayout.EAST, LOGINBTN);
		contentPane.add(PASS);
		PASS.setColumns(10);
	}
}
package register;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import register.Program.State;

import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class enrolStudents extends JFrame {

	private JPanel contentPane;
	private DefaultListModel listModel = new DefaultListModel();
	private DefaultListModel listModel_2 = new DefaultListModel();

	public enrolStudents() {
		setTitle("Students");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 388, 570);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Program.changeState(State.Logout);
			}
		});
		mnFile.add(mntmLogout);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmInstructions = new JMenuItem("Instructions");
		mntmInstructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame parent = new JFrame();
			    JOptionPane.showMessageDialog(parent, "Move studends from unenrolled to enrolled by selecing them and pressing the add button"
			    		+ '\n'+ "same for the remove Select and press the button. Once finish select continue. ");					
			}
		});
		mnHelp.add(mntmInstructions);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 60, 112, 380);
		contentPane.add(scrollPane);
		
		JList list = new JList(listModel);
		list.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		scrollPane.setViewportView(list);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(199, 60, 112, 380);
		contentPane.add(scrollPane_1);
		
		JList list_1 = new JList(listModel_2);
		list_1.setFont(new Font("Sitka Text", Font.PLAIN, 11));
		scrollPane_1.setViewportView(list_1);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setFont(new Font("Tw Cen MT", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enrolStudent(list_1.getSelectedIndex());
			}
		});
		btnNewButton.setBounds(209, 451, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Remove");
		btnNewButton_1.setFont(new Font("Tw Cen MT", Font.PLAIN, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				unenrolStudent(list.getSelectedIndex());
			}
		});
		btnNewButton_1.setBounds(55, 451, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblEnrolled = new JLabel("Enrolled");
		lblEnrolled.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblEnrolled.setBounds(68, 22, 76, 14);
		contentPane.add(lblEnrolled);
		
		JLabel lblStudents = new JLabel("Students");
		lblStudents.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		lblStudents.setBounds(224, 22, 74, 14);
		contentPane.add(lblStudents);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.setFont(new Font("Tw Cen MT", Font.PLAIN, 11));
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<String> ls = new ArrayList<String>();
				
				for(int i = 0; i < listModel.size(); i++ )
					ls.add(listModel.get(i).toString());
				
				Program.enrolStudent(ls);
				Program.changeState(State.Class);
			}
		});
		btnContinue.setBounds(133, 485, 89, 23);
		contentPane.add(btnContinue);
	}
	
	public void listAllStudents(List ls){
		for(int i = 0; i < ls.size(); i++)
			listModel_2.addElement(ls.get(i));
	}
	
	public void enrolStudent(int index){
		listModel.addElement(listModel_2.getElementAt(index));
		listModel_2.remove(index);
	}
	
	public void unenrolStudent(int index){
		listModel_2.addElement(listModel.getElementAt(index));
		listModel.remove(index);
	}
}

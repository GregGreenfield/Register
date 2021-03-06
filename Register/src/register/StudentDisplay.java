package register;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import register.Program.State;
import javax.swing.SpringLayout;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import java.awt.Font;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class StudentDisplay extends JFrame {

	private JPanel contentPane;
	private JList list, list_1, list_2;
	private DefaultListModel here = new DefaultListModel();
	private DefaultListModel absent = new DefaultListModel();
	private DefaultListModel more = new DefaultListModel();
	private JTextField filename = new JTextField(), dir = new JTextField();

	
	public StudentDisplay() {
		setTitle("Students");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 401, 516);
		
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
		
		JMenuItem SaveMenu = new JMenuItem("Save");
		SaveMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser c = new JFileChooser();
			      
				int rVal = c.showSaveDialog(null);
			    if (rVal == JFileChooser.APPROVE_OPTION) {
			    	filename.setText(c.getSelectedFile().getName());
			        dir.setText(c.getCurrentDirectory().toString());
			        Program.saveFile(filename.getText(), dir.getText());
			    }
			    if (rVal == JFileChooser.CANCEL_OPTION) {
			        filename.setText("You pressed cancel");
			        dir.setText("");
			    }
			}
		});
		mnFile.add(SaveMenu);
		mnFile.add(mntmLogout);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmInstruction = new JMenuItem("Instruction");
		mntmInstruction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame parent = new JFrame();
			    JOptionPane.showMessageDialog(parent, "Follow button prompts!!!");				
			}
		});
		mnHelp.add(mntmInstruction);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JButton AbsentBtn = new JButton("Change to absent");
		sl_contentPane.putConstraint(SpringLayout.WEST, AbsentBtn, 36, SpringLayout.WEST, contentPane);
		AbsentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selected = "";
				try{
					selected = list.getSelectedValue().toString();
				}catch(Exception e){
					System.out.println("Nothing selected!");
				}
				
				if(!selected.isEmpty())
				{
					absent.addElement(list.getSelectedValue());
					here.remove(list.getSelectedIndex());
					Program.changeStudentState(selected);
				}
			}
		});
		contentPane.add(AbsentBtn);
		
		JButton AttendBtn = new JButton("Change to attened");
		sl_contentPane.putConstraint(SpringLayout.NORTH, AttendBtn, 0, SpringLayout.NORTH, AbsentBtn);
		sl_contentPane.putConstraint(SpringLayout.EAST, AttendBtn, -42, SpringLayout.EAST, contentPane);
		AttendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selected = "";
				try{
					selected = list_1.getSelectedValue().toString();
				}catch(Exception e){
					System.out.println("Nothing selected!");
				}
				
				if(!selected.isEmpty())
				{
					here.addElement(list_1.getSelectedValue());
					absent.remove(list_1.getSelectedIndex());
					Program.changeStudentState(selected);
				}
			}
		});
		contentPane.add(AttendBtn);
		
		JButton EnrolAndHere = new JButton("Add to enrolled & here");
		sl_contentPane.putConstraint(SpringLayout.WEST, EnrolAndHere, 114, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, EnrolAndHere, -10, SpringLayout.SOUTH, contentPane);
		EnrolAndHere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selected = "";
				
				try{
					selected = list_2.getSelectedValue().toString();
				}catch(Exception e){
					System.out.println("Nothing selected!");
				}
				
				if(!selected.isEmpty())
				{
					here.addElement(list_2.getSelectedValue());
					more.remove(list_2.getSelectedIndex());
					Program.enrolStudent(selected);
				}
			}
		});
		contentPane.add(EnrolAndHere);
		
		JLabel lblScannedIn = new JLabel("Scanned in");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblScannedIn, 55, SpringLayout.WEST, contentPane);
		lblScannedIn.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		contentPane.add(lblScannedIn);
		
		JLabel lblAbsent = new JLabel("Absent");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblScannedIn, 0, SpringLayout.NORTH, lblAbsent);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblAbsent, -87, SpringLayout.EAST, contentPane);
		lblAbsent.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		contentPane.add(lblAbsent);
		
		JLabel lblScannedInAnd = new JLabel("Scanned in and not enrolled");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, AbsentBtn, -17, SpringLayout.NORTH, lblScannedInAnd);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblScannedInAnd, 80, SpringLayout.WEST, contentPane);
		lblScannedInAnd.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		contentPane.add(lblScannedInAnd);
		
		JScrollPane scrollPane = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane, 72, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane, 21, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane, -6, SpringLayout.NORTH, AbsentBtn);
		contentPane.add(scrollPane);
		
		list = new JList(here);
		list.setFont(new Font("Calibri", Font.PLAIN, 11));
		list.setBackground(new Color(224, 255, 255));
		scrollPane.setViewportView(list);
		sl_contentPane.putConstraint(SpringLayout.WEST, list, 165, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, list, -31, SpringLayout.NORTH, AbsentBtn);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane, -39, SpringLayout.WEST, scrollPane_1);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblAbsent, -17, SpringLayout.NORTH, scrollPane_1);
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane_1, 0, SpringLayout.NORTH, scrollPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane_1, -6, SpringLayout.NORTH, AttendBtn);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane_1, 201, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane_1, 0, SpringLayout.EAST, AttendBtn);
		contentPane.add(scrollPane_1);
		
		list_1 = new JList(absent);
		list_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		list_1.setBackground(new Color(224, 255, 255));
		scrollPane_1.setViewportView(list_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, list_1, 278, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, list_1, 0, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, list_1, -31, SpringLayout.NORTH, AttendBtn);
		sl_contentPane.putConstraint(SpringLayout.NORTH, list_1, 17, SpringLayout.SOUTH, lblAbsent);
		sl_contentPane.putConstraint(SpringLayout.NORTH, list, 0, SpringLayout.NORTH, list_1);
		sl_contentPane.putConstraint(SpringLayout.EAST, list, -6, SpringLayout.WEST, list_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblScannedInAnd, -6, SpringLayout.NORTH, scrollPane_2);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane_2, 40, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane_2, -57, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane_2, 332, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane_2, -6, SpringLayout.NORTH, EnrolAndHere);
		contentPane.add(scrollPane_2);
		
		list_2 = new JList(more);
		list_2.setFont(new Font("Calibri", Font.PLAIN, 11));
		list_2.setBackground(new Color(224, 255, 255));
		scrollPane_2.setViewportView(list_2);
		sl_contentPane.putConstraint(SpringLayout.NORTH, list_2, 349, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, list_2, 58, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, list_2, -69, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, list_2, -44, SpringLayout.EAST, contentPane);
	}
	
	public void addToList(List ls){		
		for(int i = 0; i < ls.size(); i++){
			if(((Student) ls.get(i)).isAttend())
				here.addElement(((Student) ls.get(i)).getStudentID() + " " +((Student) ls.get(i)).getName());
			else if (((Student) ls.get(i)).isEnrolled())
				absent.addElement(((Student) ls.get(i)).getStudentID() + " " +((Student) ls.get(i)).getName());
			else
				more.addElement(((Student) ls.get(i)).getStudentID() + " " +((Student) ls.get(i)).getName());
			
		}
	}
}

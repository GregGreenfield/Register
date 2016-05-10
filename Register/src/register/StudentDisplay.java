package register;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import register.Program.State;

import javax.swing.SpringLayout;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentDisplay extends JFrame {

	private JPanel contentPane;
	private JList list, list_1, list_2;
	private DefaultListModel here = new DefaultListModel();
	private DefaultListModel absent = new DefaultListModel();
	private DefaultListModel more = new DefaultListModel();
	/**
	 * Create the frame.
	 */
	public StudentDisplay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 416, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		list = new JList(here);
		sl_contentPane.putConstraint(SpringLayout.WEST, list, 54, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, list, -229, SpringLayout.EAST, contentPane);
		contentPane.add(list);
		
		list_1 = new JList(absent);
		sl_contentPane.putConstraint(SpringLayout.WEST, list_1, 51, SpringLayout.EAST, list);
		sl_contentPane.putConstraint(SpringLayout.EAST, list_1, -66, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, list, 173, SpringLayout.NORTH, list_1);
		sl_contentPane.putConstraint(SpringLayout.NORTH, list, 5, SpringLayout.NORTH, list_1);
		sl_contentPane.putConstraint(SpringLayout.NORTH, list_1, 56, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, list_1, -272, SpringLayout.SOUTH, contentPane);
		contentPane.add(list_1);
		
		list_2 = new JList(more);
		sl_contentPane.putConstraint(SpringLayout.NORTH, list_2, 349, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, list_2, 58, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, list_2, -69, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, list_2, -44, SpringLayout.EAST, contentPane);
		contentPane.add(list_2);
		
		JButton AbsentBtn = new JButton("Change to absent");
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
		sl_contentPane.putConstraint(SpringLayout.NORTH, AbsentBtn, 260, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, AbsentBtn, 51, SpringLayout.WEST, contentPane);
		contentPane.add(AbsentBtn);
		
		JButton AttendBtn = new JButton("Change to attened");
		sl_contentPane.putConstraint(SpringLayout.NORTH, AttendBtn, 0, SpringLayout.NORTH, AbsentBtn);
		sl_contentPane.putConstraint(SpringLayout.EAST, AttendBtn, -56, SpringLayout.EAST, contentPane);
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
		sl_contentPane.putConstraint(SpringLayout.NORTH, EnrolAndHere, 6, SpringLayout.SOUTH, list_2);
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
		sl_contentPane.putConstraint(SpringLayout.WEST, lblScannedIn, 74, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblScannedIn, -17, SpringLayout.NORTH, list);
		contentPane.add(lblScannedIn);
		
		JLabel lblAbsent = new JLabel("Absent");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblAbsent, 0, SpringLayout.NORTH, lblScannedIn);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblAbsent, -96, SpringLayout.EAST, contentPane);
		contentPane.add(lblAbsent);
		
		JLabel lblScannedInAnd = new JLabel("Scanned in and not enrolled");
		sl_contentPane.putConstraint(SpringLayout.EAST, EnrolAndHere, 0, SpringLayout.EAST, lblScannedInAnd);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblScannedInAnd, -10, SpringLayout.NORTH, list_2);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblScannedInAnd, -127, SpringLayout.EAST, contentPane);
		contentPane.add(lblScannedInAnd);
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

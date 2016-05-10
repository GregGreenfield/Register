package register;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import register.Program.State;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class selectRegister extends JFrame {
	private DefaultListModel listModel = new DefaultListModel();
	private JPanel contentPane;
	private JList list;

	/**
	 * Create the frame.
	 */
	public selectRegister() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JButton AddBtn = new JButton("Add Register");
		AddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				Object result = JOptionPane.showInputDialog(frame, "Enter the date and time YYYY-MM-DD TTTT "
						+ "E.G. 2016-03-20 1400 :");
				
				String date = result.toString().substring(0, result.toString().indexOf(" "));
				String time = result.toString().substring(result.toString().indexOf(" "), result.toString().length());
			
				if(!result.toString().isEmpty()){
					System.out.println(date + " " + time);
					Program.addRegister(date, time);
				}else{
					System.out.println("EMPTY!");
				}
				
			}
		});
		contentPane.add(AddBtn);
		
		JButton ContinueBTN = new JButton("Continue");
		sl_contentPane.putConstraint(SpringLayout.EAST, AddBtn, -6, SpringLayout.WEST, ContinueBTN);
		sl_contentPane.putConstraint(SpringLayout.NORTH, ContinueBTN, 0, SpringLayout.NORTH, AddBtn);
		sl_contentPane.putConstraint(SpringLayout.WEST, ContinueBTN, 299, SpringLayout.WEST, contentPane);
		ContinueBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String selected = list.getSelectedValue().toString();
				
					if(!selected.isEmpty())
					{
						Program.addStudents(selected);
						Program.changeState(State.Student);
					}
				}catch(NullPointerException e){
					e.printStackTrace();
				}
			}
		});
		contentPane.add(ContinueBTN);
		
		JButton DeleteBtn = new JButton("Delete Register");
		DeleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selected = list.getSelectedValue().toString();
				
				if(!selected.isEmpty())
				{
					Program.deleteRegister(selected); 
					removerRegister(list.getSelectedIndex());
				}else{
					System.out.println("EMPTY!");
				}
			}
		});
		sl_contentPane.putConstraint(SpringLayout.EAST, DeleteBtn, -337, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, AddBtn, 1, SpringLayout.EAST, DeleteBtn);
		sl_contentPane.putConstraint(SpringLayout.NORTH, DeleteBtn, 0, SpringLayout.NORTH, AddBtn);
		contentPane.add(DeleteBtn);
		
		JLabel lblNewLabel = new JLabel("New label");
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel, -246, SpringLayout.EAST, contentPane);
		contentPane.add(lblNewLabel);
		
		list = new JList(listModel);
		sl_contentPane.putConstraint(SpringLayout.EAST, ContinueBTN, 0, SpringLayout.EAST, list);
		sl_contentPane.putConstraint(SpringLayout.WEST, DeleteBtn, 0, SpringLayout.WEST, list);
		sl_contentPane.putConstraint(SpringLayout.NORTH, AddBtn, 26, SpringLayout.SOUTH, list);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel, -14, SpringLayout.NORTH, list);
		sl_contentPane.putConstraint(SpringLayout.NORTH, list, 33, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, list, 15, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, list, -75, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, list, -26, SpringLayout.EAST, contentPane);
		contentPane.add(list);
	}
	
	public void addToList(List ls){		
		for(int i = 0; i < ls.size(); i++)
			listModel.addElement(ls.get(i));
	}
	
	public void addRegister(String reg){
		listModel.addElement(reg);
	}
	
	public void removerRegister(Integer num){
		listModel.remove(num);
	}
}

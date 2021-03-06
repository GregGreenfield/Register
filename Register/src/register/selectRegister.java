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
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class selectRegister extends JFrame {
	private DefaultListModel listModel = new DefaultListModel();
	private JPanel contentPane;
	private JList list;

	public selectRegister() {
		setTitle("Register");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 419);
		
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
		
		JMenuItem mntmInstruction = new JMenuItem("Instruction");
		mntmInstruction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame parent = new JFrame();
			    JOptionPane.showMessageDialog(parent, "Select the Register that you would like and click the continue button."
			    		+ '\n' + "Alternativly you can add or remove by selecting the correct button!");			
			}
		});
		mnHelp.add(mntmInstruction);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JButton AddBtn = new JButton("Add Register");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, AddBtn, -28, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, AddBtn, -201, SpringLayout.EAST, contentPane);
		AddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				Object result = JOptionPane.showInputDialog(frame, "Enter the date and time YYYY-MM-DD TTTT "
						+ "E.G. 2016-03-20 1400 :");
				
				String date = result.toString().substring(0, result.toString().indexOf(" "));
				String time = result.toString().substring(result.toString().indexOf(" "), result.toString().length());
			
				if(!result.toString().isEmpty()){
					Program.addRegister(date, time);
				}else{
					System.out.println("EMPTY!");
				}
			}
		});
		contentPane.add(AddBtn);
		
		JButton ContinueBTN = new JButton("Continue");
		sl_contentPane.putConstraint(SpringLayout.WEST, ContinueBTN, 7, SpringLayout.EAST, AddBtn);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, ContinueBTN, -28, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, ContinueBTN, -31, SpringLayout.EAST, contentPane);
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
		sl_contentPane.putConstraint(SpringLayout.WEST, DeleteBtn, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, DeleteBtn, -28, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, DeleteBtn, -342, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, AddBtn, 0, SpringLayout.EAST, DeleteBtn);
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
		contentPane.add(DeleteBtn);
		
		JLabel lblNewLabel = new JLabel("Register's");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 190, SpringLayout.WEST, contentPane);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane, 22, SpringLayout.SOUTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane, 25, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane, -1, SpringLayout.NORTH, AddBtn);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane, -43, SpringLayout.EAST, contentPane);
		contentPane.add(scrollPane);
		
		list = new JList(listModel);
		list.setBackground(new Color(224, 255, 255));
		scrollPane.setViewportView(list);
		sl_contentPane.putConstraint(SpringLayout.NORTH, list, 196, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, list, 186, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, list, -75, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, list, -26, SpringLayout.EAST, contentPane);
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

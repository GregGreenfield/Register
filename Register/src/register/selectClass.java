package register;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import register.Program.State;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class selectClass extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JList list;
	private DefaultListModel listModel = new DefaultListModel();
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmLogout;
	private JMenu mnHelp;
	private JMenuItem mntmInstructions;
	
	public selectClass() {
		setTitle("Classes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 335, 331);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Program.changeState(State.Logout);
			}
		});
		mnFile.add(mntmLogout);
		
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		mntmInstructions = new JMenuItem("Instructions");
		mntmInstructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame parent = new JFrame();
			    JOptionPane.showMessageDialog(parent, "To continue select a class and then the continue button"
			    		+ '\n' + "or add/remove a class by selecting the buttons!");			
			}
		});
		mnHelp.add(mntmInstructions);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(173, 216, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		lblNewLabel = new JLabel("Class");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 40, SpringLayout.WEST, contentPane);
		contentPane.add(lblNewLabel);
		
		JButton ContinueBtn = new JButton("Continue");
		sl_contentPane.putConstraint(SpringLayout.EAST, ContinueBtn, 0, SpringLayout.EAST, contentPane);
		ContinueBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selected = list.getSelectedValue().toString();
				
				if(!selected.isEmpty())
				{
					Program.createRegister(selected);
					Program.changeState(State.Register);
				}
				
			}
		});
		contentPane.add(ContinueBtn);
		
		JButton btnNewButton_1 = new JButton("Add");
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton_1, -34, SpringLayout.WEST, ContinueBtn);
		sl_contentPane.putConstraint(SpringLayout.NORTH, ContinueBtn, 0, SpringLayout.NORTH, btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame = new JFrame();
				Object result = JOptionPane.showInputDialog(frame, "Enter the class Number and name: ");
				
				if(!result.toString().isEmpty()){
					String num = result.toString().substring(0, result.toString().indexOf(" "));
					String name = result.toString().substring(result.toString().indexOf(" "), result.toString().length());
					Program.addClass(num, name);
					Program.changeState(State.Enroll);
				}else{
					System.out.println("EMPTY!");
				}
			}
		});
		contentPane.add(btnNewButton_1);
		
		JButton btnRemove = new JButton("Remove");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton_1, 0, SpringLayout.NORTH, btnRemove);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton_1, 24, SpringLayout.EAST, btnRemove);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnRemove, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnRemove, -10, SpringLayout.SOUTH, contentPane);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selected = list.getSelectedValue().toString();
				
				if(!selected.isEmpty())
				{
					Program.removeClass(selected);
					removeRegister(list.getSelectedIndex());
				}else{
					System.out.println("EMPTY!");
				}
			}
		});
		contentPane.add(btnRemove);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane_1, 11, SpringLayout.SOUTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane_1, 30, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane_1, -32, SpringLayout.NORTH, ContinueBtn);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane_1, -42, SpringLayout.EAST, contentPane);
		contentPane.add(scrollPane_1);
		
		list = new JList(listModel);
		scrollPane_1.setViewportView(list);
		sl_contentPane.putConstraint(SpringLayout.NORTH, list, 163, SpringLayout.SOUTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, list, 20, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, list, 287, SpringLayout.WEST, contentPane);
		list.setBackground(new Color(224, 255, 255));
		list.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		sl_contentPane.putConstraint(SpringLayout.SOUTH, list, -15, SpringLayout.NORTH, ContinueBtn);
	}
	
	public void changeText(String text){
		lblNewLabel.setText(text);
	} 
	
	public void addToList(List ls){		
		for(int i = 0; i < ls.size(); i++)
			listModel.addElement(ls.get(i));
	}
	public void addClass(String cla){
		listModel.addElement(cla);
	}
	
	public void removeRegister(Integer num){
		listModel.remove(num);
	}
}

package register;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class selectClass extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JList list;
	DefaultListModel listModel = new DefaultListModel();
	/**
	 * Create the frame.
	 */
	public selectClass() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 280, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		lblNewLabel = new JLabel("New label");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 40, SpringLayout.WEST, contentPane);
		contentPane.add(lblNewLabel);
		
		list = new JList(listModel);
		sl_contentPane.putConstraint(SpringLayout.NORTH, list, 16, SpringLayout.SOUTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, list, 15, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, list, 176, SpringLayout.SOUTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.EAST, list, 236, SpringLayout.WEST, contentPane);
		contentPane.add(list);
		
		JButton btnNewButton = new JButton("New button");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton, 6, SpringLayout.SOUTH, list);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton, 0, SpringLayout.EAST, list);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnNewButton_1, 0, SpringLayout.SOUTH, btnNewButton);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton_1, -6, SpringLayout.WEST, btnNewButton);
		contentPane.add(btnNewButton_1);
	}
	
	public void changeText(String text){
		lblNewLabel.setText(text);
	} 
	
	public void addToList(List ls){		
		for(int i = 0; i < ls.size(); i++)
			listModel.addElement(ls.get(i));
	}
}
package register;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import register.Program.State;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class selectRegister extends JFrame {
	private DefaultListModel listModel = new DefaultListModel();
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public selectRegister() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 312, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblNewLabel = new JLabel("New label");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 45, SpringLayout.WEST, contentPane);
		contentPane.add(lblNewLabel);
		
		JList list = new JList(listModel);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel, -14, SpringLayout.NORTH, list);
		sl_contentPane.putConstraint(SpringLayout.NORTH, list, 28, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, list, -31, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, list, 279, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, list, 10, SpringLayout.WEST, contentPane);
		contentPane.add(list);
		
		JButton btnNewButton = new JButton("New button");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton, 1, SpringLayout.SOUTH, list);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton, 0, SpringLayout.WEST, contentPane);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton_1, 1, SpringLayout.SOUTH, list);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton_1, 6, SpringLayout.EAST, btnNewButton);
		contentPane.add(btnNewButton_1);
		
		JButton ContinueBTN = new JButton("Continue");
		ContinueBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selected = list.getSelectedValue().toString();
				
				if(!selected.isEmpty())
				{
					Program.createRegister(selected);
					System.out.println("Selected: " + selected);
					Program.changeState(State.Register);
				}
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, ContinueBTN, 0, SpringLayout.NORTH, btnNewButton);
		sl_contentPane.putConstraint(SpringLayout.WEST, ContinueBTN, 6, SpringLayout.EAST, btnNewButton_1);
		contentPane.add(ContinueBTN);
	}
	
	public void addToList(List ls){		
		for(int i = 0; i < ls.size(); i++)
			listModel.addElement(ls.get(i));
	}
}

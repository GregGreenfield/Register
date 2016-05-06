package register;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;

public class StudentDisplay extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentDisplay frame = new StudentDisplay();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		
		JList list = new JList();
		sl_contentPane.putConstraint(SpringLayout.WEST, list, 54, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, list, -229, SpringLayout.EAST, contentPane);
		contentPane.add(list);
		
		JList list_1 = new JList();
		sl_contentPane.putConstraint(SpringLayout.WEST, list_1, 51, SpringLayout.EAST, list);
		sl_contentPane.putConstraint(SpringLayout.EAST, list_1, -66, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, list, 173, SpringLayout.NORTH, list_1);
		sl_contentPane.putConstraint(SpringLayout.NORTH, list, 5, SpringLayout.NORTH, list_1);
		sl_contentPane.putConstraint(SpringLayout.NORTH, list_1, 56, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, list_1, -272, SpringLayout.SOUTH, contentPane);
		contentPane.add(list_1);
		
		JList list_2 = new JList();
		sl_contentPane.putConstraint(SpringLayout.NORTH, list_2, 349, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, list_2, 58, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, list_2, -69, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, list_2, -44, SpringLayout.EAST, contentPane);
		contentPane.add(list_2);
		
		JButton btnNewButton = new JButton("New button");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton, 260, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton, 51, SpringLayout.WEST, contentPane);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton_1, 0, SpringLayout.NORTH, btnNewButton);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton_1, -73, SpringLayout.EAST, contentPane);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton_2, 137, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnNewButton_2, -10, SpringLayout.SOUTH, contentPane);
		contentPane.add(btnNewButton_2);
		
		JLabel lblScannedIn = new JLabel("Scanned in");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblScannedIn, 74, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblScannedIn, -17, SpringLayout.NORTH, list);
		contentPane.add(lblScannedIn);
		
		JLabel lblAbsent = new JLabel("Absent");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblAbsent, 0, SpringLayout.NORTH, lblScannedIn);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblAbsent, -96, SpringLayout.EAST, contentPane);
		contentPane.add(lblAbsent);
		
		JLabel lblScannedInAnd = new JLabel("Scanned in and not enrolled");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblScannedInAnd, -10, SpringLayout.NORTH, list_2);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblScannedInAnd, -127, SpringLayout.EAST, contentPane);
		contentPane.add(lblScannedInAnd);
	}
}

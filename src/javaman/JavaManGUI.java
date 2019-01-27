package javaman;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
public class JavaManGUI {

	public JFrame frame;
	private JTextField txtFieldUserID;



	/**
	 * Create the application.
	 */
	public JavaManGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 401, 237);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUserID = new JLabel("User ID:");
		lblUserID.setBounds(43, 55, 48, 43);
		lblUserID.setFont(new Font("Malgun Gothic Semilight", Font.PLAIN, 14));
		frame.getContentPane().add(lblUserID);
		
		txtFieldUserID = new JTextField();
		txtFieldUserID.setBounds(103, 55, 226, 43);
		frame.getContentPane().add(txtFieldUserID);
		txtFieldUserID.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(103, 130, 89, 23);
		frame.getContentPane().add(btnDelete);
		
		JButton Cancel = new JButton("Cancel");
		Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Cancel.setBounds(240, 130, 89, 23);
		frame.getContentPane().add(Cancel);
	}
}

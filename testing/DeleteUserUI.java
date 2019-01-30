

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.SwingConstants;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteUserUI {

	public JFrame delJFrame;
	
	String[] strArray;
	ArrayList<String> list; 
	
	//JComboBox
	JComboBox idComboBox;
	
	
	/**
	 * Launch the application.
	 */
	public void ShowDeleteUserUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteUserUI window = new DeleteUserUI();
					window.delJFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DeleteUserUI() {
		delJFrame = new JFrame();
		delJFrame.setBounds(100, 100, 402, 209);
		delJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		delJFrame.getContentPane().setLayout(null);
		
		JLabel lblInputUser = new JLabel("Select Id");
		lblInputUser.setBounds(21, 73, 75, 14);
		delJFrame.getContentPane().add(lblInputUser);
		
		//populates the JComboBox
		PopulateList();
		idComboBox.setBounds(113, 71, 256, 17);
		delJFrame.getContentPane().add(idComboBox);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginTracker tracker = new LoginTracker();
				tracker.DeleteAccount((String)idComboBox.getSelectedItem());
				delJFrame.dispose();
			}
		});
		btnOk.setBounds(101, 117, 91, 23);
		delJFrame.getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				delJFrame.dispose();
			}
		});
		btnCancel.setBounds(216, 117, 91, 23);
		delJFrame.getContentPane().add(btnCancel);
		
		JLabel lblDeleteUsers = new JLabel("Delete User");
		lblDeleteUsers.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDeleteUsers.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteUsers.setBounds(144, 27, 98, 14);
		delJFrame.getContentPane().add(lblDeleteUsers);
	}
  //note: create user, dispose() and the ArrayList mismatch
	public void PopulateList (){
		LoginTracker tracker = new LoginTracker();
		
		list = new ArrayList<String>(1);
		list = tracker.GetIdList();
		strArray = list.toArray(new String [list.size()]);
		idComboBox = new JComboBox<>(strArray);
	}
}

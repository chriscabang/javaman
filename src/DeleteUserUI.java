

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.SwingConstants;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteUserUI {

	public JFrame delJFrame;
	JFrame adminUIJFrame;
	
	String[] strArray;
	ArrayList<String> list; 
	
	//JComboBox
	JComboBox idComboBox;
	
	
	/**
	 * Launch the application.
	 */
	public void ShowDeleteUserUI(JFrame frame) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteUserUI window = new DeleteUserUI(frame);
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
	public DeleteUserUI(JFrame sourceFrame) {
		
		//frame of source window (admin)
		adminUIJFrame = sourceFrame;
		
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
				boolean actionSuccess = false;
				boolean deletePursued = false;
		
				
				 if (JOptionPane.showConfirmDialog(null, "Do you wish to delete?", "Delete User?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
					actionSuccess = tracker.DeleteAccount((String)idComboBox.getSelectedItem());
					deletePursued = true;
				}
				
				
				if (deletePursued == true){
					
					if (actionSuccess == true) {
						JOptionPane.showMessageDialog(null,"Successfully deleted!");
						adminUIJFrame.setVisible(true);
						delJFrame.dispose();
					}
					else{
						JOptionPane.showMessageDialog(null, "Delete Failed! Error on Db!", "Error", JOptionPane.ERROR_MESSAGE);
					}	
				}
			}
		});
		btnOk.setBounds(101, 117, 91, 23);
		delJFrame.getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				adminUIJFrame.setVisible(true);
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
		
		//window listener if user clicks the x button
		delJFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				//recall LoginAdminUI (since it was hidden)
				adminUIJFrame.setVisible(true);
			}
		});
	}
	
	//overloaded constructor
	public DeleteUserUI(){
		
	}
	
	public void PopulateList (){
		LoginTracker tracker = new LoginTracker();
		
		list = new ArrayList<String>(1);
		list = tracker.GetIdList();
		strArray = list.toArray(new String [list.size()]);
		idComboBox = new JComboBox<>(strArray);
	}
}

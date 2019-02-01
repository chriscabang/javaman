
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JComponent;

public class CreateUserUI extends JFrame implements ActionListener {

	public JFrame createUserJFrame;
	public JFrame adminUIJFrame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	
	//JPasswordFields
	private JPasswordField passField;
	private JPasswordField passField_1;
	
	//JButton
	JButton btnCreateAccount;

	 
	 //JComboBox
	 JComboBox comboBox;
	
	/**
	 * Launch the application.
	 */
	 
	public void ShowCreateUserUI(JFrame frame) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateUserUI window = new CreateUserUI(frame);
					window.createUserJFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CreateUserUI(JFrame frame) {
		
		adminUIJFrame = frame;
		
		createUserJFrame = new JFrame();
		createUserJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		createUserJFrame.setBounds(100, 100, 477, 460);
		createUserJFrame.getContentPane().setLayout(null);
		
		JLabel lblCreateUserId = new JLabel("Create User ID");
		lblCreateUserId.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
		lblCreateUserId.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateUserId.setBounds(188, 11, 100, 14);
		createUserJFrame.getContentPane().add(lblCreateUserId);
		
		JLabel lblNewLabel = new JLabel("New User ID*");
		lblNewLabel.setBounds(26, 69, 100, 14);
		createUserJFrame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(149, 66, 279, 20);
		createUserJFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewPassword = new JLabel("New password*");
		lblNewPassword.setBounds(26, 99, 100, 14);
		createUserJFrame.getContentPane().add(lblNewPassword);
		
		//confirm pass
		passField_1 = new JPasswordField();
		passField_1.setColumns(10);
		passField_1.setBounds(149, 127, 279, 20);
		createUserJFrame.getContentPane().add(passField_1);
		
		//new pass
		passField = new JPasswordField();
		passField.setColumns(10);
		passField.setBounds(149, 96, 279, 20);
		createUserJFrame.getContentPane().add(passField);
		
		//first name
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(149, 159, 279, 20);
		createUserJFrame.getContentPane().add(textField_3);
		
		//middle name
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(149, 190, 279, 20);
		createUserJFrame.getContentPane().add(textField_4);
		
		JLabel lblConfirmPassword = new JLabel("Confirm password*");
		lblConfirmPassword.setBounds(26, 130, 124, 14);
		createUserJFrame.getContentPane().add(lblConfirmPassword);
		
		JLabel lblFirstName = new JLabel("First Name*");
		lblFirstName.setBounds(26, 161, 100, 14);
		createUserJFrame.getContentPane().add(lblFirstName);
		
		JLabel lblMiddleName = new JLabel("Middle Name");
		lblMiddleName.setBounds(26, 193, 100, 14);
		createUserJFrame.getContentPane().add(lblMiddleName);
		
		JLabel lblLastName = new JLabel("Last Name*");
		lblLastName.setBounds(26, 224, 100, 14);
		createUserJFrame.getContentPane().add(lblLastName);
		
		//Last name
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(149, 221, 279, 20);
		createUserJFrame.getContentPane().add(textField_5);
			
		//age
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(149, 249, 53, 20);
		createUserJFrame.getContentPane().add(textField_6);
		
		JLabel lblAge = new JLabel("Age*");
		lblAge.setBounds(26, 252, 100, 14);
		createUserJFrame.getContentPane().add(lblAge);
		
		//address
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(149, 280, 279, 20);
		createUserJFrame.getContentPane().add(textField_7);
		
		JLabel lblAddress = new JLabel("Address*");
		lblAddress.setBounds(26, 283, 100, 14);
		createUserJFrame.getContentPane().add(lblAddress);
		
		JLabel label = new JLabel("Gender");
		label.setBounds(26, 314, 100, 14);
		createUserJFrame.getContentPane().add(label);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"M", "F"}));
		comboBox.setBounds(149, 310, 68, 22);
		createUserJFrame.getContentPane().add(comboBox);
		
		btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(this);
		btnCreateAccount.setBounds(171, 364, 132, 23);
		createUserJFrame.getContentPane().add(btnCreateAccount);
		
		//window listener if user clicks the x button
		createUserJFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				//recall LoginAdminUI (since it was hidden)
				adminUIJFrame.setVisible(true);
			}
		});
	}
	
	//overloaded constructor
	public CreateUserUI(){
		
	}
	
	
	public boolean FieldChecker (String IdString, String inputPass, String inputCfrmPass, String fname, String lname, String ageString, String addr) {
		
		boolean good = false;
		
		//check if important text fields are empty
		if (IdString.trim().length() <= 0){
			JOptionPane.showMessageDialog(null, "Id field can't be empty!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (inputPass.trim().length() <= 0 ){
			JOptionPane.showMessageDialog(null, "Password field can't be empty!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (inputCfrmPass.trim().length() <= 0){
			JOptionPane.showMessageDialog(null, "Confirm password field can't be empty!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (ageString.trim().length() <= 0 ) {
			JOptionPane.showMessageDialog(null, "Age field can't be empty!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (fname.trim().length() <= 0 ) {
			JOptionPane.showMessageDialog(null, "First name field can't be empty!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (lname.trim().length() <= 0 ){
			JOptionPane.showMessageDialog(null, "Last name field can't be empty!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (addr.trim().length() <= 0 ){
			JOptionPane.showMessageDialog(null, "Address field can't be empty!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		//if all fields are okay set good to true
		else {
			good = true;
		}
		
		return good;
	}
	
	//event
	public void actionPerformed(ActionEvent event){
		//only one listener since only one button
		LoginTracker tracker = new LoginTracker();
		
		//Create user obj to represent newly created user
		User newUser = new User();
		
		//Variables
		boolean areInputsValid = true;		
		String IdString = textField.getText();
		char [] pass = passField.getPassword();
		char [] cfrmPass = passField_1.getPassword();
		String ageString = textField_6.getText();
		
		//parse the char into string
		String inputPass = new String(pass);
		String inputCfrmPass = new String (cfrmPass);
		
		//Assign strings to capture data inputed for other fields
		String fname = textField_3.getText();
		String lname = textField_5.getText();
		String mname = textField_4.getText();
		String addr = textField_7.getText();
		
		//check if fields are empty
		areInputsValid = FieldChecker(IdString,  inputPass,  inputCfrmPass,  fname,  lname,  ageString,  addr);
		
		//checks if user name is not too identical with existing records
		boolean noDuplicateRecord = tracker.CheckExistingNames(fname, lname, mname);
		
		if (noDuplicateRecord == false){
			JOptionPane.showMessageDialog(null, "Name of User too identical with existing record! Possible duplicate!", "Error", JOptionPane.ERROR_MESSAGE);
			areInputsValid = false;
			
		}
		
		
		//if all important fields have value, push through
		if (areInputsValid == true){
			
			//parse age and id into int
			//Parse String to int (handles exceptions)
			try {
				newUser.SetId(Integer.parseInt(IdString));
			}
			catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Id must be numerical!", "Error", JOptionPane.ERROR_MESSAGE);
				areInputsValid = false;
			}
			
			try {
				newUser.SetAge(Integer.parseInt(ageString));
			}
			catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Age must be numerical!", "Error", JOptionPane.ERROR_MESSAGE);
				areInputsValid = false;
				
			}
		
			//set the other fields of the user obj
			newUser.SetFirstName(textField_3.getText());
			newUser.SetLastName(textField_5.getText());
			newUser.SetMiddleName(textField_4.getText());
			newUser.SetGender((String)comboBox.getSelectedItem());
			newUser.SetAddress(textField_7.getText());
		}
		
		//if all inputs are valid call AddAccount method from LoginTracker class and checks if success
		/*
			Legend:
			 0 - success 
			 1 - passwords don't match
			 2 - user Id already taken
			 -1 - sql exception occurred
		*/
		if (areInputsValid == true){
			int status = tracker.AddAccount (newUser, inputPass, inputCfrmPass);
			
			switch (status) {
				case 0:
					JOptionPane.showMessageDialog(null,"Account creation successful!");
					//close the create frame
					createUserJFrame.dispose();
					adminUIJFrame.setVisible(true);
					break;
				case 1:
					JOptionPane.showMessageDialog(null, "Passwords fail to match!", "Error", JOptionPane.ERROR_MESSAGE);
					break;
				case 2:
					JOptionPane.showMessageDialog(null, "Id already taken! Retry!", "Error", JOptionPane.ERROR_MESSAGE);
					break;
				case -1:
					JOptionPane.showMessageDialog(null, "Problem connecting to db!", "Error", JOptionPane.ERROR_MESSAGE);
					break;
			}
		}
		
	}
	
}


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

public class CreateUserUI extends JFrame implements ActionListener {

	public JFrame createUserJFrame;
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
	 
	public void ShowCreateUserUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateUserUI window = new CreateUserUI();
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
	public CreateUserUI() {
		createUserJFrame = new JFrame();
		createUserJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		createUserJFrame.setBounds(100, 100, 477, 460);
		createUserJFrame.getContentPane().setLayout(null);
		
		JLabel lblCreateUserId = new JLabel("Create User ID");
		lblCreateUserId.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
		lblCreateUserId.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateUserId.setBounds(188, 11, 100, 14);
		createUserJFrame.getContentPane().add(lblCreateUserId);
		
		JLabel lblNewLabel = new JLabel("New User ID");
		lblNewLabel.setBounds(26, 69, 100, 14);
		createUserJFrame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(149, 66, 279, 20);
		createUserJFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewPassword = new JLabel("New password");
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
		
		JLabel lblConfirmPassword = new JLabel("Confirm password");
		lblConfirmPassword.setBounds(26, 130, 124, 14);
		createUserJFrame.getContentPane().add(lblConfirmPassword);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(26, 161, 100, 14);
		createUserJFrame.getContentPane().add(lblFirstName);
		
		JLabel lblMiddleName = new JLabel("Middle Name");
		lblMiddleName.setBounds(26, 193, 100, 14);
		createUserJFrame.getContentPane().add(lblMiddleName);
		
		JLabel lblLastName = new JLabel("Last Name");
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
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(26, 252, 100, 14);
		createUserJFrame.getContentPane().add(lblAge);
		
		//address
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(149, 280, 279, 20);
		createUserJFrame.getContentPane().add(textField_7);
		
		JLabel lblAddress = new JLabel("Address");
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
	}
	
	//event
	public void actionPerformed(ActionEvent event){
		//only one listener since only one button
		LoginTracker tracker = new LoginTracker();
		
		//Create user obj to represent newly created user
		User newUser = new User();
		
		String IdString = textField.getText();
		char [] pass = passField.getPassword();
		char [] cfrmPass = passField_1.getPassword();
		String ageString = textField_6.getText();
		
		//parse the char into string
		String inputPass = new String(pass);
		String inputCfrmPass = new String (cfrmPass);
		
		//parse age and id into int
		newUser.SetId(Integer.parseInt(IdString));
		newUser.SetAge(Integer.parseInt(ageString));
		
		//set the other fields of the user obj
		newUser.SetFirstName(textField_3.getText());
		newUser.SetLastName(textField_5.getText());
		newUser.SetMiddleName(textField_4.getText());
		newUser.SetGender((String)comboBox.getSelectedItem());
		newUser.SetAddress(textField_7.getText());
		
		//call AddAccount method from LoginTracker class
		tracker.AddAccount (newUser, inputPass, inputCfrmPass);
		
		//close the create frame
		createUserJFrame.dispose();
	}
	
}

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
public class LoggedInAsAdminUI extends JFrame implements ActionListener {

	public JFrame frame;
	
	//User id for reference:
	int Id;
	//User time-in for reference:
	String timeIn;
	
	//flag
	boolean changeView = false;
	boolean atLog = false;
	
	//Model
	DefaultListModel<String> listModel;
	
	//JList
	JList list;
	
	//JButtons
	JButton btnViewLog;
	JButton btnDelUser;
	JButton btnCreateUser;
	JButton btnShowEmployeeLog;
	JButton btnDelLog;
	JButton btnLogOut;
	
	
	//ScrollPane
	JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public void ShowLogAsAdminUI(User user, String timeIn) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoggedInAsAdminUI window = new LoggedInAsAdminUI();
					window.SetTimeIn(timeIn);
					System.out.println("Show events at main triggered!");
					window.DrawPanel(user);
					window.frame.setVisible(true);
					window.frame.setTitle("JavaMan: Admin");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public void DrawPanel(User user) {
		
		//set the Id
		Id = user.GetId();
		frame.setBounds(100, 100, 608, 552);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Admin");
		lblNewLabel.setBounds(10, 11, 46, 14);
		lblNewLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 14));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name: " + user.GetFirstName() +" "+ user.GetMiddleName() +" "+ user.GetLastName());
		lblNewLabel_1.setBounds(26, 47, 500, 30);
		lblNewLabel_1.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblId = new JLabel("ID: " + user.GetId());
		lblId.setBounds(26, 72, 500, 30);
		lblId.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
		frame.getContentPane().add(lblId);
		
		JLabel lblAge = new JLabel("Age: " + user.GetAge());
		lblAge.setBounds(26, 94, 100, 30);
		lblAge.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
		frame.getContentPane().add(lblAge);
		
		JLabel lblGender = new JLabel("Gender: " + user.GetGender());
		lblGender.setBounds(26, 122, 100, 30);
		lblGender.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
		frame.getContentPane().add(lblGender);
		
		JLabel lblAddress = new JLabel("Address: "  + user.GetAddress());
		lblAddress.setBounds(26, 147, 600, 30);
		lblAddress.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
		frame.getContentPane().add(lblAddress);
		
		btnViewLog = new JButton("View Logged Users");
		btnViewLog.setFont(new Font("Arial", Font.PLAIN, 11));
		btnViewLog.addActionListener(this);
		btnViewLog.setBounds(428, 201, 138, 23);
		frame.getContentPane().add(btnViewLog);
		
		btnDelUser = new JButton("Delete User");
		btnDelUser.setFont(new Font("Arial", Font.PLAIN, 11));
		btnDelUser.addActionListener(this);
		btnDelUser.setBounds(222, 201, 116, 23);
		frame.getContentPane().add(btnDelUser);
		
		btnCreateUser = new JButton("Create User");
		btnCreateUser.setBounds(26, 201, 98, 23);
		btnCreateUser.setFont(new Font("Arial", Font.PLAIN, 11));
		btnCreateUser.addActionListener(this);
		frame.getContentPane().add(btnCreateUser);
		
		//Draw the scrollpane with the list
		DrawList(false);
		
		// //Scroll pane
		// frame.getContentPane().add(DrawList());
		
		// //tracker object
		// LoginTracker tracker = new LoginTracker();	
		
		// listModel = new DefaultListModel<>();
		// listModel = tracker.DisplayLogRecords(user.GetId(),false);
		// list = new JList<>(listModel);
		// scrollPane.setViewportView(list);
		
		
		btnShowEmployeeLog = new JButton("Show All Employee Logs");
		btnShowEmployeeLog.setFont(new Font("Arial", Font.PLAIN, 11));
		btnShowEmployeeLog.addActionListener(this);
		btnShowEmployeeLog.setBounds(24, 456, 197, 23);
		frame.getContentPane().add(btnShowEmployeeLog);
		
		btnDelLog = new JButton("Delete Log");
		btnDelLog.setFont(new Font("Arial", Font.PLAIN, 11));
		btnDelLog.addActionListener(this);
		btnDelLog.setBounds(458, 456, 108, 23);
		frame.getContentPane().add(btnDelLog);
		
		JLabel timeLabel = new JLabel("Date and Time");
		timeLabel.setFont(new Font("Malgun Gothic", Font.PLAIN, 12));
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setBounds(195, 11, 197, 14);
		frame.getContentPane().add(timeLabel);
		
		JLabel txtName = new JLabel();
		txtName.setFont(new Font("Malgun Gothic", Font.PLAIN, 11));
		txtName.setBounds(92, 44, 273, 17);
		frame.getContentPane().add(txtName);
		
		JLabel txtID = new JLabel();
		txtID.setFont(new Font("Malgun Gothic", Font.PLAIN, 11));
		txtID.setBounds(92, 69, 273, 17);
		frame.getContentPane().add(txtID);
		
		JLabel txtAge = new JLabel();
		txtAge.setFont(new Font("Malgun Gothic", Font.PLAIN, 11));
		txtAge.setBounds(92, 92, 56, 19);
		frame.getContentPane().add(txtAge);
		
		JLabel txtGender = new JLabel();
		txtGender.setFont(new Font("Malgun Gothic", Font.PLAIN, 11));
		txtGender.setBounds(92, 119, 98, 19);
		frame.getContentPane().add(txtGender);
		
		JLabel txtAddress = new JLabel();
		txtAddress.setFont(new Font("Malgun Gothic", Font.PLAIN, 11));
		txtAddress.setBounds(92, 144, 360, 19);
		frame.getContentPane().add(txtAddress);
		
		btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(this);
		btnLogOut.setBounds(475, 9, 91, 23);
		frame.getContentPane().add(btnLogOut);
		
		final SimpleDateFormat timeFormat = new SimpleDateFormat("MMMM/dd/YYYY  HH:mm:ss a");
        ActionListener timerListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
                String time = timeFormat.format(date);
                timeLabel.setText(time);
            }
        };
        Timer timer = new Timer(1000, timerListener);
        // to make sure it doesn't wait one second at the start
        timer.setInitialDelay(0);
        timer.start();
	}
	
	//contructor
	public LoggedInAsAdminUI() {
		//set the list model
		listModel = new DefaultListModel<>();
		list = new JList<>(listModel);
		frame = new JFrame();
		scrollPane = new JScrollPane();
	}

	public void DrawList (boolean toggle){
		//tracker object
		LoginTracker tracker = new LoginTracker();
		scrollPane.setBounds(26, 235, 541, 202);
		
	
		listModel = tracker.DisplayLogRecords(this.Id,toggle);
		list = new JList<>(listModel);
		scrollPane.setViewportView(list);
		
		//Scroll pane
		frame.getContentPane().add(scrollPane);
		
	}
	
	//overloaded DrawList func
	public void DrawList(){
		//tracker object
		LoginTracker tracker = new LoginTracker();
		scrollPane.setBounds(26, 235, 541, 202);
		
		list = new JList<>(listModel);
		scrollPane.setViewportView(list);
		
		//Scroll pane
		frame.getContentPane().add(scrollPane);
	}
	
	
	
	//actionPerformed
	public void actionPerformed(ActionEvent event) {
		LoginTracker tracker = new LoginTracker();
		JButton button = (JButton) event.getSource();
		
		if (button == btnShowEmployeeLog) {
			atLog = true;
			if (changeView == false) {
				changeView = true;
				btnShowEmployeeLog.setText("Show Only Admin Logs");
			}
			else {
				changeView = false;
				btnShowEmployeeLog.setText("Show All Employee Logs");
			}

			//redraws the list DrawList method
			DrawList(changeView);
		}
		else if (button == btnDelLog) {
			int index = 0;
			String logIdString;
			index = list.getSelectedIndex();
			
			if (atLog == true){
				if (index > 1 ) {
					logIdString = (String)list.getSelectedValue();
					logIdString = logIdString.split("\\|")[0];
					logIdString = logIdString.trim();
					System.out.println("i: " + index + ": " + logIdString);
					
					//call to db
					tracker.DeleteLog(this.Id, logIdString);
					listModel.removeElementAt(index);
			
				}
			}
			
		}
		else if (button == btnViewLog) {
			atLog = false;
			listModel = tracker.ViewLoggedUsers();
			DrawList();
		}
		else if (button == btnCreateUser) {
			CreateUserUI create = new CreateUserUI();
			
			//draw the create user UI
			create.ShowCreateUserUI();			
		}
		else if (button == btnLogOut) {
			tracker.SignOut(this.Id, this.timeIn);
			//close frame
			frame.dispose();
		}
		else if (button == btnDelUser) {
			DeleteUserUI del = new DeleteUserUI();
			del.ShowDeleteUserUI();
		}
		
	}
	
	//mutator
	public void SetTimeIn (String timeIn) {
		this.timeIn = timeIn;
	}
		
}


import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
public class LoggedInAsUserUI extends JFrame implements ActionListener{
	
	private JFrame frame;
	
	//User id for reference:
	int Id;
	//User time-in for reference:
	String timeIn;
	
	//Model
	DefaultListModel<String> recordLogModel;
	
	//JList
	JList recordLogList;
	
	//JButtons
	JButton btnViewLog;
	JButton btnDelLog;
	
	//Scrollpane
	JScrollPane scrollPane;
	LoggedInAsUserUI window;
	
	/**
	 * Launch the application.
	 */
	public void ShowLogAsUserUI(User user, String timeIn) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new LoggedInAsUserUI();
					window.SetTimeIn(timeIn);
					window.DrawPanel(user);
					window.frame.setVisible(true);
					window.frame.setTitle("JavaMan: Logged");
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
		frame = new JFrame();
		frame.setBounds(100, 100, 608, 552);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
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
		
		JLabel lblGender = new JLabel("Gender: "  + user.GetGender());
		lblGender.setBounds(26, 122, 100, 30);
		lblGender.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
		frame.getContentPane().add(lblGender);
		
		JLabel lblAddress = new JLabel("Address: " + user.GetAddress());
		lblAddress.setBounds(26, 147, 600, 30);
		lblAddress.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
		frame.getContentPane().add(lblAddress);
		
		btnViewLog = new JButton("Sign Out");
		btnViewLog.addActionListener(this);
		btnViewLog.setFont(new Font("Arial", Font.PLAIN, 11));
		btnViewLog.setBounds(458, 9, 109, 23);
		frame.getContentPane().add(btnViewLog);
		
		//tracker object
		LoginTracker tracker = new LoginTracker();	
		
		recordLogModel = new DefaultListModel<>();
		recordLogModel = tracker.DisplayLogRecords(user.GetId(),false);
		recordLogList = new JList<>(recordLogModel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 235, 541, 202);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(recordLogList);
		
		btnDelLog = new JButton("Delete Log");
		btnDelLog.addActionListener(this);
		btnDelLog.setFont(new Font("Arial", Font.PLAIN, 11));
		btnDelLog.setBounds(458, 456, 108, 23);
		frame.getContentPane().add(btnDelLog);
		
		JLabel timeLabel = new JLabel("Date and Time");
		timeLabel.setFont(new Font("Malgun Gothic", Font.PLAIN, 12));
		timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		timeLabel.setBounds(183, 11, 197, 14);
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
		
		final SimpleDateFormat timeFormat = new SimpleDateFormat("MMMM/dd/YYYY  HH:mm:ss ");
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
	
	//constructor
    public LoggedInAsUserUI(){
		
	}
	
	
	public void actionPerformed(ActionEvent event) {
		
	 LoginTracker tracker = new LoginTracker();
	 JButton button = (JButton) event.getSource();
	 
		if (button == btnViewLog) {
			tracker.SignOut(this.Id, this.timeIn);
		}
		else if (button == btnDelLog) {
			int index = 0;
			String logIdString;
			index = recordLogList.getSelectedIndex();
			
			if (index > -1 ){
				logIdString = (String)recordLogList.getSelectedValue();
				logIdString = logIdString.split("\\|")[0];
				logIdString = logIdString.trim();
				System.out.println("i: " + index + ": " + logIdString);
				
				//call to db
				recordLogModel.removeElementAt(index);
				tracker.DeleteLog(this.Id, logIdString, false);
			
			}
		}
	}
	
	//mutator
	public void SetTimeIn (String timeIn) {
		this.timeIn = timeIn;
	}

}


import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
public class JavaManGUI3 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaManGUI3 window = new JavaManGUI3();
					window.frame.setVisible(true);
					window.frame.setTitle("JavaMan");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.	
	 */
	public JavaManGUI3() {

        initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 608, 552);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 46, 14);
		lblNewLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 14));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setBounds(26, 47, 46, 14);
		lblNewLabel_1.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(26, 72, 46, 14);
		lblId.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
		frame.getContentPane().add(lblId);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(26, 94, 46, 17);
		lblAge.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
		frame.getContentPane().add(lblAge);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(26, 122, 56, 14);
		lblGender.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
		frame.getContentPane().add(lblGender);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(26, 147, 56, 14);
		lblAddress.setFont(new Font("Malgun Gothic", Font.PLAIN, 14));
		frame.getContentPane().add(lblAddress);
		
		JButton btnViewLog = new JButton("Sign Out");
		btnViewLog.setFont(new Font("Arial", Font.PLAIN, 11));
		btnViewLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnViewLog.setBounds(458, 9, 109, 23);
		frame.getContentPane().add(btnViewLog);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 235, 541, 202);
		frame.getContentPane().add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JButton btnDelLog = new JButton("Delete Log");
		btnDelLog.setFont(new Font("Arial", Font.PLAIN, 11));
		btnDelLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
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
}

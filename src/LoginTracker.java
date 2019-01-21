
import java.sql.*;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////
// Program Proper
///////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////

public class LoginTracker {
	
	Connection con=null;
	Statement stmt=null;
	ResultSet rs=null;
	LocalDate date;
	LocalTime time;
	public static void main (String args[]){
		//Variables
		int adminId = 900000;
		int loggedId = 0;
		boolean isAdmin = false;
		boolean isLogged = false;
		String latestTimeIn;
	   
		//Instatiate object
		LoginTracker track = new LoginTracker();
		
		//Calls login function and checks if logged user is valid
		Login login = new Login();
		
		//excute login
		login.EmpAccount();
		
		//retrieve the input id
		loggedId = login.Id();
		
		//retrieve the time-in
		latestTimeIn = login.GetLatestTime();
		
		System.out.println("User "+ loggedId);
		if (loggedId > 0){
			//valid logged user
			//checks if user logged is admin
            if (loggedId == adminId){
				System.out.println("Welcome Admin!");
				track.TrackingAsAdmin(loggedId, latestTimeIn);
			}
			else {
				track.TrackingAsClient(loggedId, latestTimeIn);
			}
			
				
		}
		
	}
	
	public void TrackingAsClient(int Id, String timeIn){
		/* This is the tracker view of the employee (client) */
		
		//variables
		boolean isSignedOut = false;
		int input = 0;
		date = LocalDate.now();
		
		
		//scanner object for capturing input in console
		Scanner in = new Scanner (System.in);
		
		//The con object
		MySqlConnection newCon = new MySqlConnection();
		
		//Instantiate user object
		User user = new User();
	    
		
		//establish conn to fetch data
		con = newCon.ConnectToDB();
		
		try {
			//Sql statement object
			stmt = con.createStatement();
			
			//resultset object
			rs = stmt.executeQuery("select * from personal_info where Id=" + Id);
			while (rs.next()){
				int id = rs.getInt("Id");
				String fname = rs.getString("FirstName");
				String mname = rs.getString("MiddleName");
				String lname = rs.getString("LastName");
				String gender = rs.getString("Gender");
				int age = rs.getInt("Age");
				String address = rs.getString("Address");
				
				
				user = new User(id,fname,lname,mname,age,gender,address);
			}
			
			//Display data:
			
			
			
			while (!isSignedOut){
				DisplayLogRecords(user,false);
				//--------------------------------------------------------------------
				System.out.println("Input 1: delete item/2: sign-out"); 
				input = in.nextInt();	
				in.nextLine();
				
				switch (input) {
					case 1:
						DeleteLog(user.GetId(),false);
						break;
					case 2:
						isSignedOut = true;
						//retrieve latest time for accurate time out
						time = LocalTime.now();
						stmt.executeUpdate("update accounts set Logged='F' where ID="+ user.GetId());
						System.out.println(timeIn);
						stmt.executeUpdate("update employee_log set TimeOut='"+ time +"'where Emp_Id="+ user.GetId()+ " and TimeIn='" + timeIn+"'");
						break;
					default:
						System.out.println("\nInvalid!");
				}
			}
			
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally {
			try {
				con.close();
				rs.close();
				stmt.close();
			}
			catch (Exception e){
				System.out.print("Error relating to db!");
			}
			System.out.println("\nThanks for using!\n");
		}
		
	}
	
	
	public void TrackingAsAdmin(int Id, String logInTime){
		/* This is the tracker view of the employee (admin) */
		
		//variables
		boolean isSignedOut = false;
		String timeIn = logInTime;
		int input = 0;
		date = LocalDate.now();
		
		
		//scanner object for capturing input in console
		Scanner in = new Scanner (System.in);
		
		//The con object
		MySqlConnection newCon = new MySqlConnection();
		
		//Instantiate user object
		User user = new User();
	    
		
		//establish conn to fetch data
		con = newCon.ConnectToDB();
		
		try {
			//Sql statement object
			stmt = con.createStatement();
			
			//resultset object
			rs = stmt.executeQuery("select * from personal_info where Id=" + Id);
			while (rs.next()){
				int id = rs.getInt("Id");
				String fname = rs.getString("FirstName");
				String mname = rs.getString("MiddleName");
				String lname = rs.getString("LastName");
				String gender = rs.getString("Gender");
				int age = rs.getInt("Age");
				String address = rs.getString("Address");
				
				
				user = new User(id,fname,lname,mname,age,gender,address);
			}
			
			//Display data:
			while (!isSignedOut){
				DisplayLogRecords(user,false);
				//--------------------------------------------------------------------
				System.out.println("Input 1: delete item/2: view logged-in employees/3: view logs of employees/4: sign-out"); 
				input = in.nextInt();	
				in.nextLine();
				
				switch (input) {
					case 1:
						DeleteLog(user.GetId(), false);
						break;
					case 2:
						ViewLoggedUsers();
						break;
					case 3:
						DisplayLogRecords(user,true);
						break;
					case 4:
						isSignedOut = true;
						//retrieve latest time for accurate time out
						time = LocalTime.now();
						stmt.executeUpdate("update accounts set Logged='F' where ID="+ user.GetId());
						stmt.executeUpdate("update employee_log set TimeOut='"+ time +"'where Emp_Id="+ user.GetId()+ " and TimeIn='" + timeIn+"'");
						break;
					default:
						System.out.println("\nInvalid!");
				}
			}
			
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally {
			try {
				con.close();
				rs.close();
				stmt.close();
			}
			catch (Exception e){
				System.out.print("Error relating to db!");
			}
			System.out.println("\nThanks for using!\n");
		}
	}
	
	public void ViewLoggedUsers(){
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select accounts.ID, LastName, FirstName from accounts JOIN personal_info ON accounts.ID = personal_info.Id WHERE logged='T'");
			while (rs.next()){
				int emp_id = rs.getInt("ID");
				String fname = rs.getString("FirstName");
				String lname = rs.getString("LastName");
				System.out.println("--");
				System.out.println(emp_id +" || " + lname + ", " + fname);
			}
			Scanner in = new Scanner (System.in);
			//this is just to pause the screen a bit
			int inputLogId = in.nextInt();	
			in.nextLine();
		}
		catch (Exception e){
			System.out.println(e);
		}
		
	}
	
	public void DisplayLogRecords (User user, boolean allEmployees){
		
		//scanner object for capturing input in console
		Scanner in = new Scanner (System.in);
		
		try {
			stmt = con.createStatement();
			
			if (allEmployees == true){
				System.out.println("++ Admin ++");
				rs = stmt.executeQuery("select * from employee_log");
				while (rs.next()){
				   int id = rs.getInt("LogId");
				   int emp_id = rs.getInt("Emp_Id");
				   String date = rs.getString("Date");
				   String TimeIn = rs.getString("TimeIn");
				   String TimeOut = rs.getString("TimeOut");
				   System.out.println("--");
				   System.out.println(id +" || " + emp_id + " || " + date + " || " + TimeIn + " || " + TimeOut);
				}
				
				//Prompt on whether to delete a log
				System.out.println("Action 1: delete item/2:return"); 
				int input = in.nextInt();	
				in.nextLine();
				
				switch(input) {
					case 1: 
						DeleteLog (user.GetId(), true);
						break;
					case 2:
						break;
					default:
						System.out.println("Invalid!");
					
				}
		
				
			}
			else {
				//console prompts (note: in a JFrame note to refresh the screen instead
				System.out.println("---Personal Info----");
				System.out.println("Id:" + user.GetId());
				System.out.println("Name:" + user.GetFirstName() + " " + user.GetMiddleName() + " " + user.GetLastName());
				System.out.println("Gender:" + user.GetGender());
			//--------------------------------------------------------------------
				rs = stmt.executeQuery("select * from employee_log where Emp_Id=" +  user.GetId());
				System.out.println("---Record info----");
			
				while (rs.next()){
				   int id = rs.getInt("LogId");
				   String date = rs.getString("Date");
				   String TimeIn = rs.getString("TimeIn");
				   String TimeOut = rs.getString("TimeOut");
				   System.out.println("--");
				   System.out.println(id +" ||" + date + " || " + TimeIn + " || " + TimeOut);
				}
			}
			
			
		}
		catch (Exception e){
			System.out.println(e);
		}
		
		
	}
	
	public void DeleteLog(int Id, boolean allEmployees){
		
		//Variables
		Scanner in = new Scanner (System.in);
		int inputLogId=0;
		boolean validRecord = false;
		
		System.out.println("==== Delete Record ====");
		System.out.println("Acting as ID: " + Id);
		try {
			
			System.out.println("Input record id to delete");
			//wait for data
			inputLogId = in.nextInt();	
			in.nextLine();
			
			if (allEmployees == true){
				rs = stmt.executeQuery("select * from employee_log where LogId=" + inputLogId );
			}
			else {
				rs = stmt.executeQuery("select * from employee_log where LogId=" + inputLogId + " and Emp_Id=" + Id);
			}
			
			//checks if there is such a log record
			if (rs.next() == false) {
				System.out.println("No such record!");
			}else { 
				stmt.executeUpdate("delete from employee_log where LogId=" + inputLogId);
			}
						
		}
		catch(Exception e){
			System.out.println(e);
		}
		
	}
	
	
}
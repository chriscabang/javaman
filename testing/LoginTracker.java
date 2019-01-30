
import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.UnsupportedEncodingException; 
import java.security.NoSuchAlgorithmException; 

import javax.swing.*;
import java.awt.event.*;

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
	
	
	public static void main (String args[]) {
		//instantiate object
		LoginUI log = new LoginUI();
		
		try {
			log.LoginHomeUI();
		}
		catch(Exception e){
			System.out.println(e);			
		}
		
		
	}
	
	
	public void IdentifyUser(int loggedId, String latestTimeIn){
		//Variables
		int adminId = 900000;
		boolean isAdmin = false;
		boolean isLogged = false;
		
		///////////////////////////////////////
		if (loggedId > 0){
			//valid logged user
			//checks if user logged is admin
            if (loggedId == adminId){
				System.out.println("Welcome Admin!");
				TrackingAsAdmin(loggedId, latestTimeIn);
			}
			else {
				TrackingAsClient(loggedId, latestTimeIn);
			}
		}
		else {
			System.out.println("Invalid! Re-try!");
		}
		
	}
	
	public void TrackingAsClient(int Id, String latestTimeIn){
		/* This is the tracker view of the employee (client) */
		Scanner in = new Scanner (System.in);
		
		//variables
		boolean isSignedOut = false;
		int input = 0;
		date = LocalDate.now();
		
		//The con object
		MySqlConnection newCon = new MySqlConnection();
		
		//Instantiate user object
		User user = new User();
		
		//UI object (for client)
		LoggedInAsUserUI userUI;
		
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
			
			//Prep for UI screen
			userUI = new LoggedInAsUserUI();
			try {
				userUI.ShowLogAsUserUI(user, latestTimeIn);
			}
			catch(Exception e){
				System.out.println(e);
			}
			
			
			// while (!isSignedOut){
				// //DisplayLogRecords(user,false);
				// //--------------------------------------------------------------------
				
				// input = in.nextInt();	
				// in.nextLine();
				
				// switch (input) {
					// case 1:
						// DeleteLog(user.GetId(),false);
						// break;
					// case 2:
						// isSignedOut = true;
						// //retrieve latest time for accurate time out
						// time = LocalTime.now();
						// stmt.executeUpdate("update accounts set Logged='F' where ID="+ user.GetId());
						// System.out.println(timeIn);
						// stmt.executeUpdate("update employee_log set TimeOut='"+ time +"'where Emp_Id="+ user.GetId()+ " and TimeIn='" + timeIn+"'");
						// break;
					// default:
						// System.out.println("\nInvalid!");
				// }
			// }
			
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
	
	
	
	public void TrackingAsAdmin(int Id, String latestTimeIn){
		/* This is the tracker view of the employee (admin) */
		
		//variables
		boolean isSignedOut = false;
		String timeIn = latestTimeIn;
		int input = 0;
		date = LocalDate.now();
		
		
		//scanner object for capturing input in console
		Scanner in = new Scanner (System.in);
		
		//The con object
		MySqlConnection newCon = new MySqlConnection();
		
		//Instantiate user object
		User user = new User();
	    
		//UI object (for admin)
		LoggedInAsAdminUI adminUI;
		
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
			
			//Prep for UI screen
			adminUI = new LoggedInAsAdminUI();

			try {
				adminUI.ShowLogAsAdminUI(user, latestTimeIn);
			}
			catch(Exception e){
				System.out.println(e);
			}
			
			// //Display data:
			// while (!isSignedOut){
			
				// switch (input) {
					// case 1:
					// //	DeleteLog(user.GetId(), false);
						// break;
					// case 2:
						// ViewLoggedUsers();
						// break;
					// case 3:
						// //DisplayLogRecords(user,true);
						// break;
					// case 4:
						// isSignedOut = true;
						// //retrieve latest time for accurate time out
						// time = LocalTime.now();
						// stmt.executeUpdate("update accounts set Logged='F' where ID="+ user.GetId());
						// stmt.executeUpdate("update employee_log set TimeOut='"+ time +"'where Emp_Id="+ user.GetId()+ " and TimeIn='" + timeIn+"'");
						// break;
					// case 5:
						// AddAccount();
						// break;
					// case 6:
						// DeleteAccount();
						// break;
					// default:
						// System.out.println("\nInvalid!");
				// }
			// }
			
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
	
	public DefaultListModel<String> ViewLoggedUsers(){
		
		//The con object
		MySqlConnection newCon = new MySqlConnection();
		
		//Model to pass to the JFrame
		DefaultListModel<String> loggedUsersModel = new DefaultListModel<>();
		
		//establish conn to fetch data
		con = newCon.ConnectToDB();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select accounts.ID, LastName, FirstName from accounts JOIN personal_info ON accounts.ID = personal_info.Id WHERE logged='T'");
			loggedUsersModel.addElement("Employee Id | Employee Name");
			loggedUsersModel.addElement("***************************");
			while (rs.next()){
				int emp_id = rs.getInt("ID");
				String fname = rs.getString("FirstName");
				String lname = rs.getString("LastName");
				loggedUsersModel.addElement(emp_id +" | " + lname + ", " + fname);
			}
		}
		catch (Exception e){
			System.out.println(e);
		}
		finally {
			try {
				con.close();
				stmt.close();
				rs.close();
			}
			catch (Exception e){
				System.out.print(e);
			}
		}
		
		return loggedUsersModel;
	}
	
	public void SignOut(int Id, String timeIn) {
		//The con object
		MySqlConnection newCon = new MySqlConnection();
		//establish conn to fetch data
		
		con = newCon.ConnectToDB();
		
		try {
			stmt = con.createStatement();
			
			time = LocalTime.now();
			stmt.executeUpdate("update accounts set Logged='F' where ID="+ Id);
			System.out.println(timeIn);
			stmt.executeUpdate("update employee_log set TimeOut='"+ time +"'where Emp_Id="+ Id + " and TimeIn='" + timeIn+"'");
			System.out.println("Log out successful!");
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally {
			
			try {
				con.close();
				stmt.close();
			}
			catch (Exception e){
				System.out.print(e);
			}
		}
		
	}
	
	public ArrayList GetIdList(){
		ArrayList<String> list = new ArrayList<String>(1);
		
		//The con object
		MySqlConnection newCon = new MySqlConnection();
		
		//establish conn to fetch data
		con = newCon.ConnectToDB();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select ID from accounts where ID <> 900000");
			
			while (rs.next()){
				//retrieves the data from Id column as java string types
					list.add(rs.getString("ID"));
			}
			
		}
		catch (Exception e){
			System.out.println("Exception at Display logs" + e);
		}
		finally {
			
			try {
				con.close();
				stmt.close();
				rs.close();
			}
			catch (Exception e){
				System.out.print(e);
			}
		}
		
		//return
		return list;	
	}
	
	public DefaultListModel<String> DisplayLogRecords (int Id, boolean allEmployees){
		
		//scanner object for capturing input in console
		Scanner in = new Scanner (System.in);
		
		//The con object
		MySqlConnection newCon = new MySqlConnection();
		
		//establish conn to fetch data
		con = newCon.ConnectToDB();
		
		//Model to pass to the JFrame
		DefaultListModel<String> recordLogModel = new DefaultListModel<>();
		
		
		try {
			stmt = con.createStatement();
			
			if (allEmployees == true){
				//adds "Label to the JList"
				recordLogModel.addElement("LogID | Employee Id | Date | Time-In | Time-Out");
				recordLogModel.addElement("***********************************************");
				
				rs = stmt.executeQuery("select * from employee_log");
				while (rs.next()){
				   int id = rs.getInt("LogId");
				   int emp_id = rs.getInt("Emp_Id");
				   String date = rs.getString("Date");
				   String TimeIn = rs.getString("TimeIn");
				   String TimeOut = rs.getString("TimeOut");
				   recordLogModel.addElement(id +" | " + emp_id + " | " + date + " | " + TimeIn + " | " + TimeOut);
				}
				
				// //Prompt on whether to delete a log
				// System.out.println("Action 1: delete item/2:return"); 
				// int input = in.nextInt();	
				// in.nextLine();
				
				// switch(input) {
					// case 1: 
				// //		DeleteLog (user.GetId(), true);
						// break;
					// case 2:
						// break;
					// default:
						// System.out.println("Invalid!");
					
				// }
			}
			else {
				System.out.println("---Personal Info----");
				System.out.println("Id:" + Id);
			//--------------------------------------------------------------------
				rs = stmt.executeQuery("select * from employee_log where Emp_Id=" +  Id);
				System.out.println("---Record info----");
				
				//adds "Label to the JList"
				recordLogModel.addElement("LogID | Date | Time-In | Time-Out");
				recordLogModel.addElement("**********************************");
				
				while (rs.next()){
				   int id = rs.getInt("LogId");
				   String date = rs.getString("Date");
				   String TimeIn = rs.getString("TimeIn");
				   String TimeOut = rs.getString("TimeOut");
				   recordLogModel.addElement(id +" | " + date + " | " + TimeIn + " | " + TimeOut);
				}
			}
			
		}
		catch (Exception e){
			System.out.println("Exception at Display logs" + e);
		}
		finally {
			
			try {
				con.close();
				stmt.close();
				rs.close();
			}
			catch (Exception e){
				System.out.print(e);
			}
		}
		
		//Return model for the UI view
			return recordLogModel;	
	}
	
	public void DeleteLog(int Id, String inputLogId){
		
		System.out.println("==== Delete Record ====");
		System.out.println("Acting as ID: " + Id);
		
		//The con object
		MySqlConnection newCon = new MySqlConnection();
		
		//establish conn to fetch data
		con = newCon.ConnectToDB();	
			
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("delete from employee_log where LogId=" + inputLogId);			
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
			try {
				con.close();
				stmt.close();
			}
			catch (Exception e){
				System.out.print(e);
			}
		}
		
	}
	
	
	// Add User account
	public void AddAccount(User user, String inputUserPass, String confirmPass){
		Scanner in = new Scanner (System.in);
		
		System.out.println("==== Add User====");
		System.out.println("Acting as Admin");
		
		//The con object
		MySqlConnection newCon = new MySqlConnection();
		
		//establish conn to fetch data
		con = newCon.ConnectToDB();	
		
		try {
			stmt = con.createStatement();
			//scans db if Id number already taken
			rs = stmt.executeQuery("select * from accounts where ID=" + user.GetId());
			
			if (rs.next() == false){
				//if new id input is available
				//proceed to compare passwords
				//Hash the inputted password using the algo and store it to itself
				
				inputUserPass = AeSimpleSHA1.SHA1(inputUserPass);
				confirmPass = AeSimpleSHA1.SHA1(confirmPass);
				
				//compare passwords if match
				if (inputUserPass.equals(confirmPass)){
					//if true push to db
					stmt.executeUpdate("insert into accounts (ID, password) values("+ user.GetId() + ",'" + inputUserPass + "')");
					System.out.println("User creation success!");
					CreateUser(user);
				}
				else {
					System.out.println("Passwords dont match! Try again!");
				}
			}
			else {
				System.out.println("Id already taken! Try again!");
			}
		}
		catch (NoSuchAlgorithmException e){
			System.out.println("Error with algo!");
		}
		catch (UnsupportedEncodingException e) {
			System.out.println("Error with encoding!");
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
			try {
				con.close();
				stmt.close();
				rs.close();
			}
			catch (Exception e){
				System.out.print(e);
			}
		}
	}
	
	// Add Create user data
	public void CreateUser(User newUser){
		
		//Variables
		String query;
		
		//The con object
		MySqlConnection newCon = new MySqlConnection();
		
		//establish conn to fetch data
		con = newCon.ConnectToDB();	
		
		//Push new data into the personal_info table
		try {
			stmt = con.createStatement();
			query = "insert into personal_info (LastName, FirstName, MiddleName, Age, Gender, Address, Id)";
			query += "values('" + newUser.GetLastName() + "','" + newUser.GetFirstName() + "','" + newUser.GetMiddleName() + "'," + newUser.GetAge() + ",'" + newUser.GetGender() + "','" + newUser.GetAddress() + "'," + newUser.GetId() + ")";
			stmt.executeUpdate(query);
			System.out.println("Create User successful!");
		}
		catch (Exception e){
			System.out.println(e);
		}
		finally{
			try {
				con.close();
				stmt.close();
			}
			catch (Exception e){
				System.out.print(e);
			}
		}
	}
	
	//Delete User data
	public void DeleteAccount(String IdString) {
		System.out.println("==== Delete User====");
		System.out.println("Acting as Admin");
		
		//The con object
		MySqlConnection newCon = new MySqlConnection();
		
		//establish conn to fetch data
		con = newCon.ConnectToDB();	
		
		//Asks for User id to delete
		try {
			//runs only if inputted id is not admin's
			//double safety
			if (Integer.parseInt(IdString) != 900000){
				stmt = con.createStatement();
				//deletes the employee_logs then personal_info,  then accounts table
				stmt.executeUpdate("delete from employee_log where Emp_Id=" + IdString);
				stmt.executeUpdate("delete from personal_info where Id=" + IdString);
				stmt.executeUpdate("delete from accounts where ID=" + IdString);
				
				System.out.println("Delete Successful!");
			}
		}
		catch (Exception e){
			System.out.println(e);
		}
		
		
	}
	
	
}
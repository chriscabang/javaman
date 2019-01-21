
//Login 
import java.sql.*;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.lang.String;
import java.io.UnsupportedEncodingException; 
import java.security.NoSuchAlgorithmException; 

public class Login {
	Connection con=null;
	Statement stmt=null;
	ResultSet rs=null;
	LocalDate date;
	LocalTime time;
	int inputID;
	public void EmpAccount(){
		
		//Variables:
		Scanner in = new Scanner (System.in);
		date = LocalDate.now();
		String inputPass;
		boolean validID = false;
		boolean validPass = false;
				
		//Prompts		
		System.out.println("Simple login Program\nMade by:Robert Hyatt");
		
		//Ask for credentials:
		System.out.print("Input user ID: ");
		this.inputID = in.nextInt();	
		in.nextLine();
		System.out.print("Input password: ");
		inputPass = in.nextLine();
		
		//Establish connection.
		MySqlConnection newCon = new MySqlConnection();
		con = newCon.ConnectToDB();
			
		//Hash pass and query DB for data
		
		try {	

			//Hash the inputted password using the algo and store it to itself
			inputPass = AeSimpleSHA1.SHA1(inputPass);
	
	    
			//Sql statement object
			stmt = con.createStatement();
			
			//Verifies if inputted ID is valid 
			//Query to DB
			rs = stmt.executeQuery("select ID from accounts where ID=" + inputID);
			
			if (rs.next() == false) {
				validID = false;
			}else { 
				validID = true;
			}
			
			if (validID == true){
				//Query to DB for the password
				rs = stmt.executeQuery("select password from accounts where ID=" + this.inputID);
				
				while (rs.next()){
					if (inputPass.equals(rs.getString("password"))){
						validPass = true;
					}
					else {
						validPass = false;
					}
				}
				
			}
			
			//Verifies creditials for final say
			if (validID == true && validPass == true){
				//----alters logged-in flag
				stmt.executeUpdate("update accounts set Logged='T' where ID="+ this.inputID);
				
				//----pushes data into logs
				   //recapture current time
				time = LocalTime.now();
				//convert the time into string and split and miniseconds:
				String timeString = new StringBuffer().append(this.time).toString();  
				timeString = timeString.split("\\.")[0];
				//sql stmt
				stmt.executeUpdate("insert into employee_log(Emp_Id, Date, TimeIn ) VALUES (" + this.inputID + ",'" + date + "', '" + timeString + "')");
				System.out.println("Welcome, USER " + this.inputID);
			}
			else {
				System.out.println("Invalid input!");
				this.inputID = -1;
			}
			
		}catch (NoSuchAlgorithmException e){
			System.out.println("Error with algo!");
		}
		catch (UnsupportedEncodingException e) {
			System.out.println("Error with encoding!");
		}catch (SQLException e) {
			throw new IllegalStateException(e);
		
		}catch (Exception e){
			System.out.println("An error occured!");
		}
		finally {
			try {
				con.close();
				rs.close();
				stmt.close();
			}
			catch (SQLException e){
				throw new IllegalStateException(e);
			}
		}
	}
	
	//getter for inputID
	public int Id() {return this.inputID;}
	
	public String GetLatestTime() {
		String timeString = new StringBuffer().append(this.time).toString();  
		return timeString.split("\\.")[0];
	}

}	
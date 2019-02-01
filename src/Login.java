
//Login Logic
import java.sql.*;
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
	
	
	public int ConnectAccount(int inputID, String inputPass) {
		
		//Variables:
		date = LocalDate.now();
		int status = 0;
		this.inputID = inputID;
		boolean validID = false;
		boolean validPass = false;
		
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
			rs = stmt.executeQuery("select ID from accounts where ID=" + this.inputID);
			
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
				
				//checks if user is already logged-in
				boolean isAlreadyLogged = false;
				
				rs = stmt.executeQuery("select Logged from accounts where ID=" + this.inputID + " AND Logged='T'");
				
				if (rs.next() == false){
					isAlreadyLogged = false;
				}
				else {
					isAlreadyLogged = true;
				}
				
				
				if (isAlreadyLogged == false) {
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
					status = 0;
				}
				else {
					status = 2;
				}
			}
			else {
				System.out.println("Invalid input!");
				status = 1;
			}
			
		}catch (NoSuchAlgorithmException e){
			System.out.println("Error with algo!");
			status = 5;
		}
		catch (UnsupportedEncodingException e) {
			System.out.println("Error with encoding!");
			status = 5;
		}catch (SQLException e) {
			System.out.println(e);
			status = -1;
		
		}catch (Exception e){
			System.out.println("An error occured!");
			status = 6;
		}
		finally {
			try {
				con.close();
				rs.close();
				stmt.close();
			}
			catch (SQLException e){
				System.out.println(e);
				status = -1;
			}
		}
		
		return status;
	}
	
	
	//getter for inputID
	public int GetId() {return this.inputID;}
	
	public String GetLatestTime() {
		String timeString = new StringBuffer().append(this.time).toString();  
		return timeString.split("\\.")[0];
	}

}	
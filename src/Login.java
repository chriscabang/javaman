
//Login 
import java.sql.*;
import java.util.Scanner;
import java.io.UnsupportedEncodingException; 
import java.security.NoSuchAlgorithmException; 

public class Login {
	
	public static void main(String args[]){
		
		//Variables:
		Scanner in = new Scanner (System.in);
		int inputID;
		String inputPass;
		boolean validID = false;
		boolean validPass = false;
		
		
		//note: change the pass of user 902310
		
		
		///////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////
		// Program Proper
		///////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////
				
		System.out.println("Simple login Program\nMade by:Robert Hyatt");
		
		//Ask for credentials:
		System.out.print("Input user ID: ");
		inputID = in.nextInt();	
		in.nextLine();
		System.out.print("Input password: ");
		inputPass = in.nextLine();
		
		
			
		//Hash pass and establish connection to DB
		
		try {	

			//Hash the inputted password using the algo and store it to itself
			inputPass = AeSimpleSHA1.SHA1(inputPass);
	
		    //Establish connection. Details below:
			
			/*Connection string to db:
			  In my case;
			  DB name: records_db
			  username (for server): java
			  password (for server): qwerty  

			*/
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/records_db?useTimezone=true&serverTimezone=UTC","java","qwerty");
			Statement stmt = con.createStatement();  
			//Verifies if inputted ID is valid b4 it continues
			//Query to DB
			ResultSet rs = stmt.executeQuery("select ID from accounts where ID=" + inputID);
			
			if (rs.next() == false) {
				validID = false;
			}else { 
				validID = true;
			}
			
			if (validID == true){
				//Query to DB for the password
				rs = stmt.executeQuery("select password from accounts where ID=" + inputID);
				
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
				System.out.println("Welcome, USER " + inputID);
			}
			else {
				System.out.println("Invalid input!");
			}
			
			con.close();
			rs.close();
			stmt.close();
			
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
			System.out.println("Program exiting!");
		}
	}
	
}	
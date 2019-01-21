//Establishes the connection to db

import java.sql.*;

public class MySqlConnection{
Connection con = null;

	public Connection ConnectToDB(){
		/*Connection string to db:
		  In my case;
		  DB name: records_db
		  username (for server): java
		  password (for server): qwerty  

		*/
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/records_db?useTimezone=true&serverTimezone=UTC","java","qwerty");
			return con;
		}
		catch(Exception e){
			System.out.println("An error has occurred!");
			System.out.println(e);
			return null;
		}
	
	}
	
}


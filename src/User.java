//User class that will represent the logged-in user

public class User {
	private int id;
	private String fname;
	private String lname;
	private String mname;
	private int age;
	private String gender; 
	private String address;
	
	//Constructor
	User (int id, String fname, String lname, String mname, int age, String gender, String address){
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.mname = mname;
		this.age = age;
		this.gender = gender; 
		this.address = address; 
		
	}
	//Overloaded empty constructor
	User(){};
	
	//Methods (getters)
	public int GetId() {return this.id;}
	public String GetFirstName() {return this.fname;}
	public String GetLastName() {return this.lname;}
	public String GetMiddleName() {return this.mname;}
	public String GetGender() {return this.gender;}
	public int GetAge() {return this.age;}
	public String GetAddress() {return this.address;}
	
	
	//Methods mutators
	public void SetId(int id){this.id = id;}
	public void SetFirstName(String fname){this.fname = fname;}
	public void SetLastName(String lname) {this.lname = lname;}
	public void SetMiddleName(String mname) {this.mname = mname;}
	public void SetGender(String gender) {this.gender = gender;}
	public void SetAge(int age) {this.age = age;}
	public void SetAddress(String address) {this.address = address;}
	
}
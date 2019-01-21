This is my branch.


Note: @src 
  -contains the SHA hash algorithm I used for hashing the password,
   name of file is AeSimpleSHA1.java

  -contains the LoginTracker.java and other classes for the Login App.
  -the LoginTracker.java contains the main() method.

     @sql script (project_sql)
  -contains the script used in creating the dummy database.
  -simply copy paste the script in the mysql command line/shell.
   and press enter.
  
  -then to verify if successful, copy paste these two statements:
  SELECT * FROM Accounts;
  SELECT * FROM Personal_Info;

  (note: include the semicolons ';').
  -then press enter.
  -the Accounts table and Personal_Info table should be shown/displayed. 

     @sql script (create_user)
  -contains the script to create another user (which is 'java')
   This is to practice good habits in db management, since connecting
   an app to a db using the root/admin user directly is bad practice.

  -Run this after creating the db. Just copy paste the script in the command    line and press enter.

   -to verify, run this:
   SELECT User FROM mysql.user;

     -- there should be 'java' in the returned query table.

 Links: 
  MySQL installer: 
     https://dev.mysql.com/downloads/installer/

  .jar file link:
   https://drive.google.com/open?id=1GgRToRryGG7jeBg9lAcc-VtJwrPisAo0

** For any other questions/inquiries etc. dont hesitate to 
   contact/msg me hehehehe
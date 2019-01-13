/*
 Additional sql script
 
 Purpose: Create user on server and grant it privilages like root.
 Reason: Connecting app to db using root directly is simply bad practice.
 
 Info:
        -- User name: java 
		-- Password:  qwerty
*/

USE records_db;
CREATE USER 'java'@'localhost' IDENTIFIED BY 'qwerty';
GRANT ALL ON records_db.* TO 'java'@'localhost'

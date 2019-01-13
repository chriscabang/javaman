/*
  Dummy Database of Clients Database
  Script by: Robert S. Hyatt
  Group: JavaMan
*/

-- Create the database (w/ checker)

DROP DATABASE IF EXISTS  Records_DB;

CREATE DATABASE Records_DB;
USE Records_DB;
-- Create tables:
-- The Account table (contains the ID number and the password (hashed)

/* 
   -- Notes: --
 List of passwords: 
	For ID: 902310, unhashed pass: Password
    For ID: 902311, unhashed pass: steadfast
    For ID: 902322, unhashed pass: master123
*/

 
 CREATE TABLE Accounts 
 (
	ID INT PRIMARY KEY NOT NULL 
    ,password VARCHAR(50) NOT NULL 
 );
 
 -- Insert dummy data into table
INSERT INTO Accounts (ID, password) 
VALUES 
(902310,'8be3c943b1609fffbfc51aad666d0a04adf83c9d')
,(902311,'efe27c4ef866f91d1ce6f1a240e8137b368070f3')
,(902322,'b84689b769ab3d929f7cc14ee35e77c4ae6427c8');


-- Create Personal_Info table
-- Contains name, age, gender etc.

CREATE TABLE Personal_Info 
(
   LastName VARCHAR (20) NOT NULL,
   FirstName VARCHAR (20) NOT NULL,
   MiddleName VARCHAR (20) NULL,
   Age INT NULL,
   Gender CHAR(1) NULL,
   Address VARCHAR(50) NOT NULL,
   Id INT,
   FOREIGN KEY (Id) REFERENCES Accounts(ID) 
);

INSERT INTO Personal_Info (LastName, FirstName, MiddleName, Age, Gender, Address, Id) 
VALUES 
('Hyatt', 'Robert', 'Sibla', 22, 'M', 'Somewhere out there, Philippines', 902310),
('Fuk', 'Ho', 'Lee', 21, null, 'Ching Chiong', 902311),
('Maldita', 'Lolita', 'Very', 20, 'F', 'Someplace, Cebu, Philippines', 902322);
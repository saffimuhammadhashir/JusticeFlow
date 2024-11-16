-- Use the database
USE sda_project;
SHOW TABLES;


-- Drop Statements
DROP TABLE IF EXISTS CaseFiles;
DROP TABLE IF EXISTS Cases;
DROP TABLE IF EXISTS Lawyers;
DROP TABLE IF EXISTS Prosecutors;
DROP TABLE IF EXISTS BarAssociations;
DROP TABLE IF EXISTS Judges;
DROP TABLE IF EXISTS Clerks;
DROP TABLE IF EXISTS CourtAdministrators;
DROP TABLE IF EXISTS Plaintiffs;
DROP TABLE IF EXISTS Defendants;
DROP TABLE IF EXISTS Bailiffs;
DROP TABLE IF EXISTS CourtReporters;
DROP TABLE IF EXISTS ProbationOfficers;
DROP TABLE IF EXISTS ITAdmins;
DROP TABLE IF EXISTS Courts;
DROP TABLE IF EXISTS Witnesses;
DROP TABLE IF EXISTS Jurors;
DROP TABLE IF EXISTS UserApplication;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Notifications;

-- Create tables for the system

-- Actor Table
CREATE TABLE Users (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(255) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL,
    Role ENUM('Judge', 'Clerk', 'Lawyer', 'Court Administrator', 'Plaintiff', 'Defendant', 'Witness', 'Bailiff', 'Juror', 'Court Reporter', 'Probation Officer', 'IT Admin', 'Prosecutor') NOT NULL,
    Email VARCHAR(255) UNIQUE NOT NULL,
    PhoneNumber VARCHAR(20),
	Activate BOOLEAN DEFAULT FALSE
);

CREATE TABLE Courts (
    CourtID INT AUTO_INCREMENT PRIMARY KEY,
    CourtName VARCHAR(255) NOT NULL,
    CourtType ENUM('Civil', 'Criminal', 'Family', 'Other') NOT NULL,
    Address VARCHAR(255),
    PhoneNumber VARCHAR(20),
    Email VARCHAR(255)
);

CREATE TABLE Judges (
    JudgeID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    DateOfBirth DATE NOT NULL,
    Gender ENUM('Male', 'Female', 'Other') NOT NULL,
    AppointmentDate DATE,
    CourtID INT,
    Email VARCHAR(255),
    PhoneNumber VARCHAR(20),
    UserID INT,
    FOREIGN KEY (CourtID) REFERENCES Courts(CourtID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    UNIQUE (Email)
);

CREATE TABLE Clerks (
    ClerkID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    DateOfBirth DATE NOT NULL,
    Gender ENUM('Male', 'Female', 'Other') NOT NULL,
    HireDate DATE,
    CourtID INT,
    Email VARCHAR(255),
    PhoneNumber VARCHAR(20),
    UserID INT,
    FOREIGN KEY (CourtID) REFERENCES Courts(CourtID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    UNIQUE (Email)
);

CREATE TABLE BarAssociations (
    BarAssociationID INT AUTO_INCREMENT PRIMARY KEY,
    AssociationName VARCHAR(255) NOT NULL,
    Address VARCHAR(255),
    PhoneNumber VARCHAR(20),
    Email VARCHAR(255)
);

CREATE TABLE Lawyers (
    LawyerID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    LicenseNumber VARCHAR(255) NOT NULL,
    DateOfBirth DATE NOT NULL,
    Gender ENUM('Male', 'Female', 'Other') NOT NULL,
    BarAssociationID INT,
    Email VARCHAR(255),
    PhoneNumber VARCHAR(20),
    UserID INT,
    FOREIGN KEY (BarAssociationID) REFERENCES BarAssociations(BarAssociationID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    UNIQUE (Email)
);

CREATE TABLE CourtAdministrators (
    AdminID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    DateOfBirth DATE NOT NULL,
    Gender ENUM('Male', 'Female', 'Other') NOT NULL,
    HireDate DATE,
    CourtID INT,
    Email VARCHAR(255),
    PhoneNumber VARCHAR(20),
    UserID INT,
    FOREIGN KEY (CourtID) REFERENCES Courts(CourtID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    UNIQUE (Email)
);

CREATE TABLE Plaintiffs (
    PlaintiffID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    DateOfBirth DATE NOT NULL,
    Gender ENUM('Male', 'Female', 'Other') NOT NULL,
    Address VARCHAR(255),
    PhoneNumber VARCHAR(20),
    Email VARCHAR(255),
    UserID INT,
    UNIQUE (Email),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

CREATE TABLE Defendants (
    DefendantID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    DateOfBirth DATE NOT NULL,
    Gender ENUM('Male', 'Female', 'Other') NOT NULL,
    Address VARCHAR(255),
    PhoneNumber VARCHAR(20),
    Email VARCHAR(255),
    UserID INT,
    UNIQUE (Email),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

CREATE TABLE Witnesses (
    WitnessID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    DateOfBirth DATE NOT NULL,
    Gender ENUM('Male', 'Female', 'Other') NOT NULL,
    Address VARCHAR(255),
    PhoneNumber VARCHAR(20),
    Email VARCHAR(255),
    UserID INT,
    UNIQUE (Email),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

CREATE TABLE Bailiffs (
    BailiffID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    DateOfBirth DATE NOT NULL,
    Gender ENUM('Male', 'Female', 'Other') NOT NULL,
    HireDate DATE,
    CourtID INT,
    Email VARCHAR(255),
    PhoneNumber VARCHAR(20),
    UserID INT,
    FOREIGN KEY (CourtID) REFERENCES Courts(CourtID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    UNIQUE (Email)
);

CREATE TABLE Jurors (
    JurorID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    DateOfBirth DATE NOT NULL,
    Gender ENUM('Male', 'Female', 'Other') NOT NULL,
    Address VARCHAR(255),
    PhoneNumber VARCHAR(20),
    Email VARCHAR(255),
    UserID INT,
    UNIQUE (Email),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

CREATE TABLE CourtReporters (
    ReporterID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    DateOfBirth DATE NOT NULL,
    Gender ENUM('Male', 'Female', 'Other') NOT NULL,
    HireDate DATE,
    CourtID INT,
    Email VARCHAR(255),
    PhoneNumber VARCHAR(20),
    UserID INT,
    FOREIGN KEY (CourtID) REFERENCES Courts(CourtID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    UNIQUE (Email)
);

CREATE TABLE ProbationOfficers (
    OfficerID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    DateOfBirth DATE NOT NULL,
    Gender ENUM('Male', 'Female', 'Other') NOT NULL,
    HireDate DATE,
    CourtID INT,
    Email VARCHAR(255),
    PhoneNumber VARCHAR(20),
    UserID INT,
    FOREIGN KEY (CourtID) REFERENCES Courts(CourtID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    UNIQUE (Email)
);

CREATE TABLE ITAdmins (
    AdminID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    DateOfBirth DATE NOT NULL,
    Gender ENUM('Male', 'Female', 'Other') NOT NULL,
    HireDate DATE,
    Email VARCHAR(255),
    PhoneNumber VARCHAR(20),
    UserID INT,
    UNIQUE (Email),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

CREATE TABLE Prosecutors (
    ProsecutorID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    LicenseNumber VARCHAR(255) NOT NULL,
    DateOfBirth DATE NOT NULL,
    Gender ENUM('Male', 'Female', 'Other') NOT NULL,
    BarAssociationID INT,
    Email VARCHAR(255),
    PhoneNumber VARCHAR(20),
    UserID INT,
    FOREIGN KEY (BarAssociationID) REFERENCES BarAssociations(BarAssociationID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    UNIQUE (Email)
);


CREATE TABLE UserApplication (
    ApplicationID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT NOT NULL,
    ApplicationStatus INT DEFAULT 0,
    SubmissionDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE
);


CREATE TABLE Cases (
    CaseID INT AUTO_INCREMENT PRIMARY KEY,
    CaseTitle VARCHAR(255) NOT NULL,
    CaseType VARCHAR(100),
    CaseStatus VARCHAR(100),
    FilingDate DATE,
    CourtDate DATE,
    PlaintiffID INT  ,
    DefendantID INT  ,	
    FOREIGN KEY (PlaintiffID) REFERENCES Plaintiffs(PlaintiffID),
    FOREIGN KEY (DefendantID) REFERENCES Defendants(DefendantID)
);

CREATE TABLE CaseFiles (
    FileID INT AUTO_INCREMENT PRIMARY KEY,
    CaseID INT,
    FileName VARCHAR(255) NOT NULL,
    FileHash VARCHAR(255) NOT NULL,
    UploadDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (CaseID) REFERENCES Cases(CaseID)
);


CREATE TABLE Notifications (
    NotificationID INT AUTO_INCREMENT PRIMARY KEY, -- Unique ID for each notification
    CaseID INT,                                   -- Associated case ID
    RecipientsID JSON,                            -- List of recipient IDs (stored as JSON)
    RecipientsType JSON,                          -- List of recipient types (stored as JSON)
    SenderID INT,                                 -- Sender ID
    SenderType VARCHAR(255),                      -- Sender type
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Timestamp when the notification is created
);





-- Select statements
SELECT * FROM Users;
SELECT * FROM Cases;
SELECT * FROM CaseFiles;
select * from UserApplication;
SELECT * FROM Courts;
SELECT * FROM Judges;
SELECT * FROM Clerks;
SELECT * FROM Lawyers;
SELECT * FROM BarAssociations;
SELECT * FROM CourtAdministrators;
SELECT * FROM Plaintiffs;
SELECT * FROM Defendants;
SELECT * FROM Bailiffs;
SELECT * FROM CourtReporters;
SELECT * FROM ProbationOfficers;
SELECT * FROM ITAdmins;
SELECT * FROM Prosecutors;



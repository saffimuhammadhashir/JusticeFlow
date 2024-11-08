-- Insert Bar Association (id 1 for City Bar Association)
INSERT INTO BarAssociations (AssociationName, Address, PhoneNumber, Email)
VALUES 
('City Bar Association', '789 Legal Ave, Cityville', '555-2001', 'citybar@law.com');

-- Insert Users
-- (Assuming UserIDs will auto-increment)
INSERT INTO Users (Username, Password, Role, Email, PhoneNumber)
VALUES 
('johnsmith', 'password123', 'Judge', 'johnsmith@court.com', '555-0101'),
('janedoe', 'password456', 'Clerk', 'janedoe@court.com', '555-0102'),
('alicejones', 'password789', 'Lawyer', 'alicejones@lawfirm.com', '555-0103'),
('bobmartin', 'password000', 'Plaintiff', 'bobmartin@plaintiff.com', '555-0104');

-- Insert Courts
-- (Assuming CourtIDs will auto-increment)
INSERT INTO Courts (CourtName, CourtType, Address, PhoneNumber, Email)
VALUES 
('Central Criminal Court', 'Criminal', '123 Court St, Cityville', '555-1001', 'central@court.com'),
('Family Court', 'Family', '456 Family Rd, Townsville', '555-1002', 'family@court.com');

-- Insert Judges
-- (Assuming JudgeIDs will auto-increment and CourtID references the previously inserted Courts)
INSERT INTO Judges (FirstName, LastName, DateOfBirth, Gender, AppointmentDate, CourtID, Email, PhoneNumber)
VALUES 
('John', 'Smith', '1980-03-15', 'Male', '2010-01-01', 1, 'johnsmith@court.com', '555-0101'),
('Jane', 'Doe', '1985-07-20', 'Female', '2015-05-12', 2, 'janedoe@court.com', '555-0102');

-- Insert Clerks
-- (Assuming ClerkIDs will auto-increment and CourtID references the previously inserted Courts)
INSERT INTO Clerks (FirstName, LastName, DateOfBirth, Gender, HireDate, CourtID, Email, PhoneNumber)
VALUES 
('Alice', 'Jones', '1990-02-10', 'Female', '2020-06-15', 1, 'alicejones@court.com', '555-0103'),
('Bob', 'Martin', '1992-09-25', 'Male', '2021-03-22', 2, 'bobmartin@court.com', '555-0104');

-- Insert Lawyers
-- (Assuming LawyerIDs will auto-increment and BarAssociationID references the previously inserted BarAssociation)
INSERT INTO Lawyers (FirstName, LastName, LicenseNumber, DateOfBirth, Gender, BarAssociationID, Email, PhoneNumber)
VALUES 
('Alice', 'Jones', 'L12345', '1988-06-01', 'Female', 1, 'alicejones@lawfirm.com', '555-0103');

-- Insert Court Administrators
-- (Assuming AdminIDs will auto-increment and CourtID references the previously inserted Courts)
INSERT INTO CourtAdministrators (FirstName, LastName, DateOfBirth, Gender, HireDate, CourtID, Email, PhoneNumber)
VALUES 
('Eve', 'Taylor', '1979-05-05', 'Female', '2018-08-12', 1, 'evetaylor@court.com', '555-0105');

-- Insert Plaintiffs
-- (Assuming PlaintiffIDs will auto-increment)
INSERT INTO Plaintiffs (FirstName, LastName, DateOfBirth, Gender, Address, PhoneNumber, Email)
VALUES 
('John', 'Doe', '1990-05-15', 'Male', '789 Plaintiff Rd, Cityville', '555-0106', 'johndoe@plaintiff.com'),
('Emily', 'Clark', '1987-03-12', 'Female', '101 Plaintiff Rd, Cityville', '555-0107', 'emilyclark@plaintiff.com'),
('Mike', 'Jackson', '1992-11-30', 'Male', '202 Plaintiff Rd, Cityville', '555-0108', 'mikejackson@plaintiff.com'),
('Sarah', 'White', '1991-01-20', 'Female', '303 Plaintiff Rd, Cityville', '555-0109', 'sarahwhite@plaintiff.com'),
('David', 'Lee', '1995-08-22', 'Male', '404 Plaintiff Rd, Cityville', '555-0110', 'davidlee@plaintiff.com');

-- Insert Defendants
-- (Assuming DefendantIDs will auto-increment)
INSERT INTO Defendants (FirstName, LastName, DateOfBirth, Gender, Address, PhoneNumber, Email)
VALUES 
('Mary', 'Smith', '1992-04-10', 'Female', '456 Defendant St, Townsville', '555-0107', 'marysmith@defendant.com'),
('James', 'Brown', '1985-01-18', 'Male', '789 Defendant St, Cityville', '555-0108', 'jamesbrown@defendant.com'),
('Olivia', 'Williams', '1990-07-09', 'Female', '101 Defendant St, Cityville', '555-0109', 'oliviawilliams@defendant.com'),
('Lucas', 'Davis', '1987-09-30', 'Male', '202 Defendant St, Cityville', '555-0110', 'lucasdavis@defendant.com'),
('Sophia', 'Taylor', '1989-04-12', 'Female', '303 Defendant St, Cityville', '555-0111', 'sophiataylor@defendant.com');

-- Insert Bailiffs
-- (Assuming BailiffIDs will auto-increment and CourtID references the previously inserted Courts)
INSERT INTO Bailiffs (FirstName, LastName, DateOfBirth, Gender, HireDate, CourtID, Email, PhoneNumber)
VALUES 
('Boris', 'Graham', '1985-11-23', 'Male', '2015-02-12', 1, 'borisgraham@court.com', '555-0108');

-- Insert Court Reporters
-- (Assuming ReporterIDs will auto-increment and CourtID references the previously inserted Courts)
INSERT INTO CourtReporters (FirstName, LastName, DateOfBirth, Gender, HireDate, CourtID, Email, PhoneNumber)
VALUES 
('Susan', 'Adams', '1983-02-11', 'Female', '2012-05-10', 1, 'susanadams@court.com', '555-0109');

-- Insert Probation Officers
-- (Assuming OfficerIDs will auto-increment and CourtID references the previously inserted Courts)
INSERT INTO ProbationOfficers (FirstName, LastName, DateOfBirth, Gender, HireDate, CourtID, Email, PhoneNumber)
VALUES 
('Carl', 'Thompson', '1977-12-09', 'Male', '2010-03-11', 2, 'carlthompson@court.com', '555-0110');

-- Insert IT Admins
-- (Assuming AdminIDs will auto-increment)
INSERT INTO ITAdmins (FirstName, LastName, DateOfBirth, Gender, HireDate, Email, PhoneNumber)
VALUES 
('Peter', 'Williams', '1980-09-10', 'Male', '2016-07-14', 'peterwilliams@it.com', '555-0111');

-- Insert Prosecutors
-- (Assuming ProsecutorIDs will auto-increment and BarAssociationID references the previously inserted BarAssociation)
INSERT INTO Prosecutors (FirstName, LastName, LicenseNumber, DateOfBirth, Gender, BarAssociationID, Email, PhoneNumber)
VALUES 
('Tom', 'Harris', 'P54321', '1979-06-30', 'Male', 1, 'tomharris@prosecutor.com', '555-0112');

-- Insert Cases
-- (Assuming CaseIDs will auto-increment and PlaintiffID and DefendantID are consistent with previously inserted Plaintiffs and Defendants)
INSERT INTO Cases (CaseTitle, CaseType, CaseStatus, FilingDate, CourtDate, PlaintiffID, DefendantID)
VALUES 
('Smith vs. Johnson', 'Civil', 'Open', '2024-10-15', '2024-11-20', 1, 5),
('Taylor vs. Brown', 'Criminal', 'Pending', '2024-09-25', '2024-11-25', 2, 4),
('White vs. Green', 'Civil', 'Closed', '2024-07-10', '2024-09-15', 3, 3),
('Miller vs. Davis', 'Criminal', 'Open', '2024-08-05', '2024-12-10', 4, 2),
('Lopez vs. Wilson', 'Family', 'Pending', '2024-06-30', '2024-12-15', 5, 1);

INSERT INTO CaseFiles (CaseID, FileName, FileHash)
VALUES 
(1, 'document1.pdf', 'a1b2c3d4e5f67890'),
(1, 'evidence.jpg', '9f8e7d6c5b4a3210');

INSERT INTO CaseFiles (CaseID, FileName, FileHash)
VALUES 
(2, 'witness_statement.docx', 'f4d6e7a2b8c901d3');


USE sda_project;
-- dummy data
INSERT INTO BarAssociations (AssociationName, Address, PhoneNumber, Email)
VALUES 
('City Bar Association', '789 Legal Ave, Cityville', '555-2001', 'citybar@law.com');

INSERT INTO Users (Username, Password, Role, Email, PhoneNumber)
VALUES 
('johnsmith', 'password123', 'Judge', 'johnsmith@court.com', '555-0101'),
('janedoe', 'password456', 'Clerk', 'janedoe@court.com', '555-0102'),
('alicejones', 'password789', 'Lawyer', 'alicejones@lawfirm.com', '555-0103'),
('bobmartin', 'password000', 'Plaintiff', 'bobmartin@plaintiff.com', '555-0104');

INSERT INTO Courts (CourtName, CourtType, Address, PhoneNumber, Email)
VALUES 
('Central Criminal Court', 'Criminal', '123 Court St, Cityville', '555-1001', 'central@court.com'),
('Family Court', 'Family', '456 Family Rd, Townsville', '555-1002', 'family@court.com');

-- Assuming CourtID 1 is assigned to 'Central Criminal Court' and CourtID 2 to 'Family Court'

INSERT INTO Judges (FirstName, LastName, DateOfBirth, Gender, AppointmentDate, CourtID, Email, PhoneNumber)
VALUES 
('John', 'Smith', '1980-03-15', 'Male', '2010-01-01', 1, 'johnsmith@court.com', '555-0101'),
('Jane', 'Doe', '1985-07-20', 'Female', '2015-05-12', 2, 'janedoe@court.com', '555-0102');

INSERT INTO Clerks (FirstName, LastName, DateOfBirth, Gender, HireDate, CourtID, Email, PhoneNumber)
VALUES 
('Alice', 'Jones', '1990-02-10', 'Female', '2020-06-15', 1, 'alicejones@court.com', '555-0103'),
('Bob', 'Martin', '1992-09-25', 'Male', '2021-03-22', 2, 'bobmartin@court.com', '555-0104');

INSERT INTO Lawyers (FirstName, LastName, LicenseNumber, DateOfBirth, Gender, BarAssociationID, Email, PhoneNumber)
VALUES 
('Alice', 'Jones', 'L12345', '1988-06-01', 'Female', 1, 'alicejones@lawfirm.com', '555-0103');

INSERT INTO CourtAdministrators (FirstName, LastName, DateOfBirth, Gender, HireDate, CourtID, Email, PhoneNumber)
VALUES 
('Eve', 'Taylor', '1979-05-05', 'Female', '2018-08-12', 1, 'evetaylor@court.com', '555-0105');

INSERT INTO Plaintiffs (FirstName, LastName, DateOfBirth, Gender, Address, PhoneNumber, Email)
VALUES 
('John', 'Doe', '1990-05-15', 'Male', '789 Plaintiff Rd, Cityville', '555-0106', 'johndoe@plaintiff.com');

INSERT INTO Defendants (FirstName, LastName, DateOfBirth, Gender, Address, PhoneNumber, Email)
VALUES 
('Mary', 'Smith', '1992-04-10', 'Female', '456 Defendant St, Townsville', '555-0107', 'marysmith@defendant.com');

INSERT INTO Bailiffs (FirstName, LastName, DateOfBirth, Gender, HireDate, CourtID, Email, PhoneNumber)
VALUES 
('Boris', 'Graham', '1985-11-23', 'Male', '2015-02-12', 1, 'borisgraham@court.com', '555-0108');

INSERT INTO CourtReporters (FirstName, LastName, DateOfBirth, Gender, HireDate, CourtID, Email, PhoneNumber)
VALUES 
('Susan', 'Adams', '1983-02-11', 'Female', '2012-05-10', 1, 'susanadams@court.com', '555-0109');

INSERT INTO ProbationOfficers (FirstName, LastName, DateOfBirth, Gender, HireDate, CourtID, Email, PhoneNumber)
VALUES 
('Carl', 'Thompson', '1977-12-09', 'Male', '2010-03-11', 2, 'carlthompson@court.com', '555-0110');

INSERT INTO ITAdmins (FirstName, LastName, DateOfBirth, Gender, HireDate, Email, PhoneNumber)
VALUES 
('Peter', 'Williams', '1980-09-10', 'Male', '2016-07-14', 'peterwilliams@it.com', '555-0111');

INSERT INTO Prosecutors (FirstName, LastName, LicenseNumber, DateOfBirth, Gender, BarAssociationID, Email, PhoneNumber)
VALUES 
('Tom', 'Harris', 'P54321', '1979-06-30', 'Male', 1, 'tomharris@prosecutor.com', '555-0112');



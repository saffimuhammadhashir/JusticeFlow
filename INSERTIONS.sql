-- Insert Bar Association (id 1 for City Bar Association)
INSERT INTO BarAssociations (AssociationName, Address, PhoneNumber, Email)
VALUES 
('City Bar Association', '789 Legal Ave, Cityville', '555-2001', 'citybar@law.com');

-- Insert Users
-- (Assuming UserIDs will auto-increment)

-- Insert Courts
-- (Assuming CourtIDs will auto-increment)
INSERT INTO Courts (CourtName, CourtType, Address, PhoneNumber, Email)
VALUES 
('Central Criminal Court', 'Criminal', '123 Court St, Cityville', '555-1001', 'central@court.com'),
('Family Court', 'Family', '456 Family Rd, Townsville', '555-1002', 'family@court.com');

-- Inserting dummy data into Users table for all roles
INSERT INTO Users (Username, Password, Role, Email, PhoneNumber, Activate) VALUES
-- Judges
('judge1', 'password1', 'Judge', 'judge1@example.com', '123-456-7890', TRUE),
('judge2', 'password2', 'Judge', 'judge2@example.com', '234-567-8901', TRUE),
('judge3', 'password3', 'Judge', 'judge3@example.com', '345-678-9012', TRUE),
('judge4', 'password4', 'Judge', 'judge4@example.com', '456-789-0123', TRUE),
('judge5', 'password5', 'Judge', 'judge5@example.com', '567-890-1234', TRUE),

-- Clerks
('clerk1', 'password1', 'Clerk', 'clerk1@example.com', '234-567-8901', TRUE),
('clerk2', 'password2', 'Clerk', 'clerk2@example.com', '345-678-9012', TRUE),
('clerk3', 'password3', 'Clerk', 'clerk3@example.com', '456-789-0123', TRUE),
('clerk4', 'password4', 'Clerk', 'clerk4@example.com', '567-890-1234', TRUE),
('clerk5', 'password5', 'Clerk', 'clerk5@example.com', '678-901-2345', TRUE),

-- Lawyers
('lawyer1', 'password1', 'Lawyer', 'lawyer1@example.com', '345-678-9012', TRUE),
('lawyer2', 'password2', 'Lawyer', 'lawyer2@example.com', '456-789-0123', TRUE),
('lawyer3', 'password3', 'Lawyer', 'lawyer3@example.com', '567-890-1234', TRUE),
('lawyer4', 'password4', 'Lawyer', 'lawyer4@example.com', '678-901-2345', TRUE),
('lawyer5', 'password5', 'Lawyer', 'lawyer5@example.com', '789-012-3456', TRUE),

-- Court Administrators
('admin1', 'password1', 'Court Administrator', 'admin1@example.com', '456-789-0123', TRUE),
('admin2', 'password2', 'Court Administrator', 'admin2@example.com', '567-890-1234', TRUE),
('admin3', 'password3', 'Court Administrator', 'admin3@example.com', '678-901-2345', TRUE),
('admin4', 'password4', 'Court Administrator', 'admin4@example.com', '789-012-3456', TRUE),
('admin5', 'password5', 'Court Administrator', 'admin5@example.com', '890-123-4567', TRUE),

-- Plaintiffs
('plaintiff1', 'password1', 'Plaintiff', 'plaintiff1@example.com', '567-890-1234', TRUE),
('plaintiff2', 'password2', 'Plaintiff', 'plaintiff2@example.com', '678-901-2345', TRUE),
('plaintiff3', 'password3', 'Plaintiff', 'plaintiff3@example.com', '789-012-3456', TRUE),
('plaintiff4', 'password4', 'Plaintiff', 'plaintiff4@example.com', '890-123-4567', TRUE),
('plaintiff5', 'password5', 'Plaintiff', 'plaintiff5@example.com', '901-234-5678', TRUE),

-- Defendants
('defendant1', 'password1', 'Defendant', 'defendant1@example.com', '678-901-2345', TRUE),
('defendant2', 'password2', 'Defendant', 'defendant2@example.com', '789-012-3456', TRUE),
('defendant3', 'password3', 'Defendant', 'defendant3@example.com', '890-123-4567', TRUE),
('defendant4', 'password4', 'Defendant', 'defendant4@example.com', '901-234-5678', TRUE),
('defendant5', 'password5', 'Defendant', 'defendant5@example.com', '012-345-6789', TRUE),

-- Witnesses
('witness1', 'password1', 'Witness', 'witness1@example.com', '789-012-3456', TRUE),
('witness2', 'password2', 'Witness', 'witness2@example.com', '890-123-4567', TRUE),
('witness3', 'password3', 'Witness', 'witness3@example.com', '901-234-5678', TRUE),
('witness4', 'password4', 'Witness', 'witness4@example.com', '012-345-6789', TRUE),
('witness5', 'password5', 'Witness', 'witness5@example.com', '123-456-7890', TRUE),

-- Bailiffs
('bailiff1', 'password1', 'Bailiff', 'bailiff1@example.com', '234-567-8901', TRUE),
('bailiff2', 'password2', 'Bailiff', 'bailiff2@example.com', '345-678-9012', TRUE),
('bailiff3', 'password3', 'Bailiff', 'bailiff3@example.com', '456-789-0123', TRUE),
('bailiff4', 'password4', 'Bailiff', 'bailiff4@example.com', '567-890-1234', TRUE),
('bailiff5', 'password5', 'Bailiff', 'bailiff5@example.com', '678-901-2345', TRUE),

-- Jurors
('juror1', 'password1', 'Juror', 'juror1@example.com', '345-678-9012', TRUE),
('juror2', 'password2', 'Juror', 'juror2@example.com', '456-789-0123', TRUE),
('juror3', 'password3', 'Juror', 'juror3@example.com', '567-890-1234', TRUE),
('juror4', 'password4', 'Juror', 'juror4@example.com', '678-901-2345', TRUE),
('juror5', 'password5', 'Juror', 'juror5@example.com', '789-012-3456', TRUE),

-- Court Reporters
('reporter1', 'password1', 'Court Reporter', 'reporter1@example.com', '234-567-8901', TRUE),
('reporter2', 'password2', 'Court Reporter', 'reporter2@example.com', '345-678-9012', TRUE),
('reporter3', 'password3', 'Court Reporter', 'reporter3@example.com', '456-789-0123', TRUE),
('reporter4', 'password4', 'Court Reporter', 'reporter4@example.com', '567-890-1234', TRUE),
('reporter5', 'password5', 'Court Reporter', 'reporter5@example.com', '678-901-2345', TRUE),

-- Probation Officers
('officer1', 'password1', 'Probation Officer', 'officer1@example.com', '789-012-3456', TRUE),
('officer2', 'password2', 'Probation Officer', 'officer2@example.com', '890-123-4567', TRUE),
('officer3', 'password3', 'Probation Officer', 'officer3@example.com', '901-234-5678', TRUE),
('officer4', 'password4', 'Probation Officer', 'officer4@example.com', '012-345-6789', TRUE),
('officer5', 'password5', 'Probation Officer', 'officer5@example.com', '123-456-7890', TRUE),

-- IT Admins
('admin1IT', 'password1', 'IT Admin', 'admin1it@example.com', '234-567-8901', TRUE),
('admin2IT', 'password2', 'IT Admin', 'admin2it@example.com', '345-678-9012', TRUE),
('admin3IT', 'password3', 'IT Admin', 'admin3it@example.com', '456-789-0123', TRUE),
('admin4IT', 'password4', 'IT Admin', 'admin4it@example.com', '567-890-1234', TRUE),
('admin5IT', 'password5', 'IT Admin', 'admin5it@example.com', '678-901-2345', TRUE),

-- Prosecutors
('prosecutor1', 'password1', 'Prosecutor', 'prosecutor1@example.com', '345-678-9012', TRUE),
('prosecutor2', 'password2', 'Prosecutor', 'prosecutor2@example.com', '456-789-0123', TRUE),
('prosecutor3', 'password3', 'Prosecutor', 'prosecutor3@example.com', '567-890-1234', TRUE),
('prosecutor4', 'password4', 'Prosecutor', 'prosecutor4@example.com', '678-901-2345', TRUE),
('prosecutor5', 'password5', 'Prosecutor', 'prosecutor5@example.com', '789-012-3456', TRUE);


-- Inserting dummy data into Courts table
INSERT INTO Courts (CourtName, CourtType, Address, PhoneNumber, Email) VALUES
('Court A', 'Criminal', '123 Criminal St, City', '555-111-2222', 'courtA@example.com'),
('Court B', 'Civil', '456 Civil Ave, City', '555-222-3333', 'courtB@example.com'),
('Court C', 'Family', '789 Family Rd, City', '555-333-4444', 'courtC@example.com'),
('Court D', 'Criminal', '101 Criminal Blvd, City', '555-444-5555', 'courtD@example.com'),
('Court E', 'Other', '202 Other Way, City', '555-555-6666', 'courtE@example.com');

-- Inserting dummy data into Judges table (relating UserID from Users and CourtID from Courts)
INSERT INTO Judges (FirstName, LastName, DateOfBirth, Gender, AppointmentDate, CourtID, Email, PhoneNumber, UserID) VALUES
('John', 'Doe', '1980-01-01', 'Male', '2010-01-01', 1, 'john.doe@example.com', '123-456-7890', 1),
('Jane', 'Smith', '1985-02-01', 'Female', '2015-06-15', 2, 'jane.smith@example.com', '234-567-8901', 2),
('Jim', 'Brown', '1975-03-01', 'Male', '2005-03-12', 3, 'jim.brown@example.com', '345-678-9012', 3),
('Mary', 'Jones', '1982-04-01', 'Female', '2012-09-01', 4, 'mary.jones@example.com', '456-789-0123', 4),
('Tom', 'Wilson', '1990-05-01', 'Male', '2020-11-20', 5, 'tom.wilson@example.com', '567-890-1234', 5);

-- Inserting dummy data into Clerks table (relating UserID from Users and CourtID from Courts)
INSERT INTO Clerks (FirstName, LastName, DateOfBirth, Gender, HireDate, CourtID, Email, PhoneNumber, UserID) VALUES
('Alice', 'Taylor', '1990-06-10', 'Female', '2020-01-15', 1, 'alice.taylor@example.com', '234-567-8902', 6),
('Bob', 'Miller', '1988-07-20', 'Male', '2019-04-22', 2, 'bob.miller@example.com', '345-678-9013', 7),
('Charlie', 'Johnson', '1992-08-25', 'Male', '2021-02-28', 3, 'charlie.johnson@example.com', '456-789-0124', 8),
('Diana', 'Lee', '1984-09-15', 'Female', '2018-10-05', 4, 'diana.lee@example.com', '567-890-1235', 9),
('Eve', 'King', '1995-10-30', 'Female', '2022-03-17', 5, 'eve.king@example.com', '678-901-2345', 10);

-- Inserting dummy data into Lawyers table (relating UserID from Users and BarAssociationID from BarAssociations)
INSERT INTO Lawyers (FirstName, LastName, LicenseNumber, DateOfBirth, Gender, BarAssociationID, Email, PhoneNumber, UserID) VALUES
('Nancy', 'Adams', 'L12345', '1980-02-10', 'Female', 1, 'nancy.adams@example.com', '123-456-7891', 11),
('George', 'Clark', 'L67890', '1985-03-05', 'Male', 1, 'george.clark@example.com', '234-567-8903', 12),
('Emily', 'Evans', 'L11223', '1990-04-15', 'Female', 1, 'emily.evans@example.com', '345-678-9014', 13),
('Steven', 'Scott', 'L33445', '1975-05-25', 'Male', 1, 'steven.scott@example.com', '456-789-0125', 14),
('Megan', 'Harris', 'L55667', '1988-06-30', 'Female', 1, 'megan.harris@example.com', '567-890-1236', 15);

-- Inserting dummy data into CourtAdministrators table (relating UserID from Users and CourtID from Courts)
INSERT INTO CourtAdministrators (FirstName, LastName, DateOfBirth, Gender, HireDate, CourtID, Email, PhoneNumber, UserID) VALUES
('Jack', 'Green', '1982-07-07', 'Male', '2016-01-11', 1, 'jack.green@example.com', '678-901-2346', 16),
('Holly', 'White', '1990-08-18', 'Female', '2017-04-02', 2, 'holly.white@example.com', '789-012-3456', 17),
('Mark', 'Black', '1984-09-19', 'Male', '2018-05-03', 3, 'mark.black@example.com', '890-123-4567', 18),
('Nina', 'Blue', '1992-10-10', 'Female', '2021-06-10', 4, 'nina.blue@example.com', '901-234-5678', 19),
('Oscar', 'Red', '1987-11-15', 'Male', '2019-12-01', 5, 'oscar.red@example.com', '012-345-6789', 20);

-- Inserting dummy data into Plaintiffs table (relating UserID from Users)
INSERT INTO Plaintiffs (FirstName, LastName, DateOfBirth, Gender, Address, PhoneNumber, Email, UserID) VALUES
('David', 'Gomez', '1970-12-12', 'Male', '123 Plaintiff St', '234-567-8907', 'david.gomez@example.com', 21),
('Sophie', 'Martinez', '1983-02-25', 'Female', '456 Plaintiff Blvd', '345-678-9018', 'sophie.martinez@example.com', 22),
('Liam', 'Rodriguez', '1992-06-18', 'Male', '789 Plaintiff Rd', '456-789-0126', 'liam.rodriguez@example.com', 23),
('Olivia', 'Lee', '1986-09-29', 'Female', '101 Plaintiff Ave', '567-890-1237', 'olivia.lee@example.com', 24),
('Noah', 'Hernandez', '1990-10-22', 'Male', '202 Plaintiff Dr', '678-901-2347', 'noah.hernandez@example.com', 25);

-- Inserting dummy data into Defendants table (relating UserID from Users)
INSERT INTO Defendants (FirstName, LastName, DateOfBirth, Gender, Address, PhoneNumber, Email, UserID) VALUES
('John', 'Miller', '1985-11-01', 'Male', '123 Defendant St', '234-567-8908', 'john.miller@example.com', 26),
('Eva', 'Wright', '1992-12-10', 'Female', '456 Defendant Blvd', '345-678-9019', 'eva.wright@example.com', 27),
('Max', 'Roberts', '1980-05-15', 'Male', '789 Defendant Rd', '456-789-0127', 'max.roberts@example.com', 28),
('Sophia', 'Martinez', '1988-04-18', 'Female', '101 Defendant Ave', '567-890-1238', 'sophia.martinez@example.com', 29),
('Lucas', 'Garcia', '1991-07-25', 'Male', '202 Defendant Dr', '678-901-2348', 'lucas.garcia@example.com', 30);

-- Inserting dummy data into UserApplication table (relating UserID from Users)
INSERT INTO UserApplication (UserID, ApplicationStatus) VALUES
(1, 1),
(2, 0),
(3, 1),
(4, 2),
(5, 0);

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


































-- Insert 100 Users
INSERT INTO Users (Username, Password, Role, Email, PhoneNumber, Activate) 
VALUES 
-- Judges
('judge', '123', 'Judge', 'judge.john@example.com', '1234567890', 1),
('judge_jane', 'password123', 'Judge', 'judge.jane@example.com', '2234567891', 1),
('judge_mark', 'password123', 'Judge', 'judge.mark@example.com', '3234567892', 1),
('judge_anna', 'password123', 'Judge', 'judge.anna@example.com', '4234567893', 1),
('judge_emily', 'password123', 'Judge', 'judge.emily@example.com', '5234567894', 1),

-- Lawyers
('lawyer_jane', 'password123', 'Lawyer', 'lawyer.jane@example.com', '1234567891', 1),
('lawyer_jack', 'password123', 'Lawyer', 'lawyer.jack@example.com', '2234567895', 1),
('lawyer_henry', 'password123', 'Lawyer', 'lawyer.henry@example.com', '3234567896', 1),
('lawyer_clara', 'password123', 'Lawyer', 'lawyer.clara@example.com', '4234567897', 1),
('lawyer', '123', 'Lawyer', 'lawyer.oliver@example.com', '5234567898', 1),

-- Administrators
('admin', '123', 'Court Administrator', 'admin.mary@example.com', '1234567892', 1),
('admin_luke', 'password123', 'Court Administrator', 'admin.luke@example.com', '2234567899', 1),
('admin_emma', 'password123', 'Court Administrator', 'admin.emma@example.com', '3234567800', 1),
('admin_sophia', 'password123', 'Court Administrator', 'admin.sophia@example.com', '4234567801', 1),
('admin_noah', 'password123', 'Court Administrator', 'admin.noah@example.com', '5234567802', 1),

-- Clients
('client', '123', 'Client', 'client.mark@example.com', '1234567893', 1),
('client_susan', 'password123', 'Client', 'client.susan@example.com', '1234567894', 1),
('client_harry', 'password123', 'Client', 'client.harry@example.com', '2234567895', 1),
('client_ron', 'password123', 'Client', 'client.ron@example.com', '3234567896', 1),
('client_hermione', 'password123', 'Client', 'client.hermione@example.com', '4234567897', 1),

-- Witnesses
('witness', '123', 'Witness', 'witness.tom@example.com', '1234567895', 1),
('witness_lisa', 'password123', 'Witness', 'witness.lisa@example.com', '2234567898', 1),
('witness_harry', 'password123', 'Witness', 'witness.harry@example.com', '3234567899', 1),
('witness_ron', 'password123', 'Witness', 'witness.ron@example.com', '4234567890', 1),
('witness_hermione', 'password123', 'Witness', 'witness.hermione@example.com', '5234567891', 1),

-- Registrars
('registrar', '123', 'Registrar', 'registrar.anna@example.com', '1234567896', 1),
('registrar_john', 'password123', 'Registrar', 'registrar.john@example.com', '2234567892', 1),
('registrar_jane', 'password123', 'Registrar', 'registrar.jane@example.com', '3234567893', 1),
('registrar_paul', 'password123', 'Registrar', 'registrar.paul@example.com', '4234567894', 1),
('registrar_lucy', 'password123', 'Registrar', 'registrar.lucy@example.com', '5234567895', 1),

-- Jurors
('juror', '123', 'Juror', 'juror.henry@example.com', '1234567897', 1),
('juror_clara', 'password123', 'Juror', 'juror.clara@example.com', '2234567893', 1),
('juror_james', 'password123', 'Juror', 'juror.james@example.com', '3234567894', 1),
('juror_linda', 'password123', 'Juror', 'juror.linda@example.com', '4234567895', 1),
('juror_ethan', 'password123', 'Juror', 'juror.ethan@example.com', '5234567896', 1),

-- Probation Officers
('officer', '123', 'Probation Officer', 'officer.emma@example.com', '1234567898', 1),
('officer_oliver', 'password123', 'Probation Officer', 'officer.oliver@example.com', '2234567894', 1),
('officer_sophia', 'password123', 'Probation Officer', 'officer.sophia@example.com', '3234567895', 1),
('officer_jack', 'password123', 'Probation Officer', 'officer.jack@example.com', '4234567896', 1),
('officer_ava', 'password123', 'Probation Officer', 'officer.ava@example.com', '5234567897', 1),

-- IT Admins
('itadmin', '123', 'IT Admin', 'itadmin.jack@example.com', '1234567899', 1),
('itadmin_mia', 'password123', 'IT Admin', 'itadmin.mia@example.com', '2234567895', 1),
('itadmin_luke', 'password123', 'IT Admin', 'itadmin.luke@example.com', '3234567896', 1),
('itadmin_emily', 'password123', 'IT Admin', 'itadmin.emily@example.com', '4234567897', 1),
('itadmin_noah', 'password123', 'IT Admin', 'itadmin.noah@example.com', '5234567898', 1);

-- Repeat similar entries for the remaining 50 users, changing names, emails, and phone numbers as needed.


-- Insert into Courts
-- Insert into Courts
INSERT INTO Courts (CourtName, CourtType, Address, PhoneNumber, Email) 
VALUES 
('High Court', 'Civil', '123 Main St', '1111111111', 'highcourt@example.com'),
('District Court', 'Criminal', '456 Oak St', '2222222222', 'districtcourt@example.com'),
('Family Court', 'Family', '789 Pine St', '3333333333', 'familycourt@example.com'),
('Civil Court North', 'Civil', '321 Elm St', '4444444444', 'civilcourtnorth@example.com'),
('Criminal Court South', 'Criminal', '654 Maple St', '5555555555', 'criminalcourtsouth@example.com'),
('Juvenile Court', 'Other', '987 Birch St', '6666666666', 'juvenilecourt@example.com'),
('Small Claims Court', 'Other', '159 Spruce St', '7777777777', 'smallclaimscourt@example.com'),
('Appeals Court', 'Civil', '753 Cedar St', '8888888888', 'appealscourt@example.com'),
('Municipal Court', 'Criminal', '357 Redwood St', '9999999999', 'municipalcourt@example.com'),
('Special Tribunal Court', 'Other', '951 Fir St', '1010101010', 'tribunalcourt@example.com');


-- Insert into Judges
INSERT INTO Judges (FirstName, LastName, DateOfBirth, Gender, AppointmentDate, CourtID, Email, PhoneNumber, UserID) 
VALUES 
('John', 'Doe', '1970-01-01', 'Male', '2005-01-01', 1, 'judge.john@example.com', '1234567890', 1),
('Anna', 'Smith', '1980-02-10', 'Female', '2010-02-01', 2, 'judge.anna@example.com', '1234567890', 2),
('Tom', 'Brown', '1965-07-20', 'Male', '2000-05-15', 3, 'judge.tom@example.com', '2234567890', 3),
('Sara', 'Johnson', '1975-03-11', 'Female', '2015-09-12', 4, 'judge.sara@example.com', '3234567890', 4),
('Mike', 'Davis', '1969-06-15', 'Male', '1998-08-22', 5, 'judge.mike@example.com', '4234567890', 5);

-- Insert into BarAssociations
INSERT INTO BarAssociations (AssociationName, Address, PhoneNumber, Email) 
VALUES 
('City Bar Association', '12 City Rd', '6234500001', 'citybar@example.com'),
('State Bar Association', '34 State St', '7234500002', 'statebar@example.com'),
('National Bar Association', '56 National Blvd', '8234500003', 'nationalbar@example.com'),
('Regional Bar Association', '78 Regional Ave', '9234500004', 'regionalbar@example.com'),
('Local Bar Association', '90 Local St', '1023450005', 'localbar@example.com');

-- Insert into Lawyers
INSERT INTO Lawyers (FirstName, LastName, LicenseNumber, DateOfBirth, Gender, BarAssociationID, Email, PhoneNumber, UserID) 
VALUES 
('Jane', 'Smith', 'ABC123', '1985-05-15', 'Female', 1, 'lawyer.jane@example.com', '1234567891', 6),
('Bob', 'Taylor', 'XYZ456', '1982-06-14', 'Male', 2, 'lawyer.bob@example.com', '2234500006', 7),
('Alice', 'Walker', 'DEF789', '1988-02-25', 'Female', 3, 'lawyer.alice@example.com', '3234500007', 8),
('Charlie', 'Johnson', 'GHI101', '1990-12-01', 'Male', 4, 'lawyer.charlie@example.com', '4234500008', 9),
('Eve', 'Adams', 'JKL202', '1992-03-17', 'Female', 5, 'lawyer.eve@example.com', '5234500009', 10);

-- Insert into CourtAdministrators
INSERT INTO CourtAdministrators (FirstName, LastName, DateOfBirth, Gender, HireDate, CourtID, Email, PhoneNumber, UserID) 
VALUES 
('Mary', 'Johnson', '1980-03-22', 'Female', '2015-06-01', 1, 'admin.mary@example.com', '1234567892', 11),
('Clark', 'Kent', '1980-08-09', 'Male', '2012-04-12', 2, 'admin.clark@example.com', '2234500010', 12),
('Bruce', 'Wayne', '1975-10-10', 'Male', '2005-03-15', 3, 'admin.bruce@example.com', '3234500011', 13),
('Steve', 'Rogers', '1990-04-25', 'Male', '2018-11-01', 4, 'admin.steve@example.com', '4234500012', 14),
('Natasha', 'Romanoff', '1987-12-17', 'Female', '2017-08-23', 5, 'admin.natasha@example.com', '5234500013', 15);

-- Insert into Clients
INSERT INTO Clients (FirstName, LastName, DateOfBirth, Gender, Address, PhoneNumber, Email, UserID) 
VALUES 
('Mark', 'Taylor', '1990-07-12', 'Male', '999 Hill St', '1234567893', 'client.mark@example.com', 16),
('Susan', 'Lee', '1992-09-25', 'Female', '888 Valley St', '1234567894', 'client.susan@example.com', 17),
('Lucy', 'Martin', '1993-11-11', 'Female', '777 Beach Ave', '2234500020', 'client.lucy@example.com', 18),
('David', 'Clark', '1985-04-04', 'Male', '666 Wood St', '3234500021', 'client.david@example.com', 19),
('Emma', 'Jones', '1995-06-06', 'Female', '555 Elm St', '4234500022', 'client.emma@example.com', 20);

-- Insert into Witnesses
INSERT INTO Witnesses (FirstName, LastName, DateOfBirth, Gender, Address, PhoneNumber, Email, UserID) 
VALUES 
('Tom', 'Brown', '1995-11-11', 'Male', '123 Witness Lane', '6234567890', 'witness.tom@example.com', 21),
('Lisa', 'Miller', '1992-05-20', 'Female', '45 Witness Ave', '7234567891', 'witness.lisa@example.com', 22),
('Harry', 'Potter', '1993-07-31', 'Male', '12 Privet Dr', '8234567892', 'witness.harry@example.com', 23),
('Ron', 'Weasley', '1994-03-01', 'Male', '78 Burrow Ln', '9234567893', 'witness.ron@example.com', 24),
('Hermione', 'Granger', '1995-09-19', 'Female', '101 Magic Rd', '1023456789', 'witness.hermione@example.com', 25);

-- Insert into Registrar
INSERT INTO Registrar (FirstName, LastName, DateOfBirth, Gender, HireDate, CourtID, Email, PhoneNumber, UserID) 
VALUES 
('Anna', 'Black', '1987-04-30', 'Female', '2018-04-01', 1, 'registrar.anna@example.com', '7234567890', 26),
('John', 'Doe', '1985-02-14', 'Male', '2015-02-10', 2, 'registrar.john@example.com', '8234567891', 27),
('Jane', 'White', '1990-08-08', 'Female', '2020-06-15', 3, 'registrar.jane@example.com', '9234567892', 28),
('Paul', 'Green', '1992-12-25', 'Male', '2021-01-01', 4, 'registrar.paul@example.com', '1023456789', 29),
('Lucy', 'Gray', '1989-07-16', 'Female', '2019-09-05', 5, 'registrar.lucy@example.com', '1123456789', 30);

-- Insert into Jurors
INSERT INTO Jurors (FirstName, LastName, DateOfBirth, Gender, Address, PhoneNumber, Email, UserID) 
VALUES 
('Henry', 'White', '1993-12-05', 'Male', 'Juror Lane 45', '8234567890', 'juror.henry@example.com', 31),
('Clara', 'Kent', '1995-01-10', 'Female', '12 Kent Ave', '9234567891', 'juror.clara@example.com', 32),
('James', 'Brown', '1990-03-18', 'Male', '23 Brown Blvd', '1023456782', 'juror.james@example.com', 33),
('Linda', 'Taylor', '1992-11-30', 'Female', '56 Taylor Rd', '1123456783', 'juror.linda@example.com', 34),
('Ethan', 'Hunt', '1988-05-20', 'Male', '78 Hunt St', '1223456784', 'juror.ethan@example.com', 35);

-- Insert into ProbationOfficers
INSERT INTO ProbationOfficers (FirstName, LastName, DateOfBirth, Gender, HireDate, CourtID, Email, PhoneNumber, UserID) 
VALUES 
('Emma', 'Green', '1988-08-20', 'Female', '2017-10-15', 2, 'officer.emma@example.com', '9234567890', 36),
('Oliver', 'Stone', '1990-09-10', 'Male', '2018-05-20', 3, 'officer.oliver@example.com', '1023456781', 37),
('Sophia', 'Hill', '1992-02-05', 'Female', '2019-07-15', 4, 'officer.sophia@example.com', '1123456782', 38),
('Jack', 'Carter', '1985-11-11', 'Male', '2016-03-10', 5, 'officer.jack@example.com', '1223456783', 39),
('Ava', 'Walker', '1991-04-04', 'Female', '2020-08-01', 1, 'officer.ava@example.com', '1323456784', 40);

-- Insert into ITAdmins
INSERT INTO ITAdmins (FirstName, LastName, DateOfBirth, Gender, HireDate, Email, PhoneNumber, UserID) 
VALUES 
('Jack', 'Blue', '1982-02-19', 'Male', '2012-12-05', 'itadmin.jack@example.com', '1023456789', 41),
('Mia', 'Black', '1989-09-09', 'Female', '2018-06-10', 'itadmin.mia@example.com', '1123456780', 42),
('Luke', 'Gray', '1985-01-25', 'Male', '2015-03-20', 'itadmin.luke@example.com', '1223456781', 43),
('Emily', 'White', '1990-03-15', 'Female', '2017-07-30', 'itadmin.emily@example.com', '1323456782', 44),
('Noah', 'Brown', '1992-05-22', 'Male', '2019-09-15', 'itadmin.noah@example.com', '1423456783', 45);

-- Insert into UserApplication
INSERT INTO UserApplication (UserID, ApplicationStatus) 
VALUES 
(1, 1),
(2, 0),
(3, 1),
(4, 0),
(5, 1),
(6, 0),
(7, 1),
(8, 0),
(9, 1),
(10, 0);



-- Insert into Cases
INSERT INTO Cases (CaseTitle, CaseType, CaseStatus, FilingDate, CourtDate, PlaintiffID, DefendantID, CaseState)
VALUES
('Case 1: John Doe vs Jane Doe', 'Civil', 'Open', '2024-01-01', '2024-02-01', 1, 5, 'Pending'),
('Case 2: Company X vs Individual Y', 'Criminal', 'Closed', '2023-12-10', '2024-01-20', 2, 4, 'Filed'),
('Case 3: ABC Corp vs XYZ Ltd', 'Civil', 'Pending', '2024-01-15', '2024-03-01', 3, 1, 'Pending'),
('Case 4: Jane Smith vs Mike Lee', 'Family', 'Open', '2024-02-01', '2024-04-05', 4, 2, 'Filed'),
('Case 5: City vs Citizen A', 'Criminal', 'Closed', '2024-03-01', '2024-04-10', 5, 3, 'Filed'),
('Case 6: Corporation A vs Individual B', 'Civil', 'Open', '2024-01-10', '2024-03-15', 1, 5, 'Pending'),
('Case 7: Mary Lou vs John Green', 'Family', 'Pending', '2024-02-20', '2024-05-01', 2, 4, 'Pending'),
('Case 8: John White vs State', 'Criminal', 'Open', '2024-03-05', '2024-06-01', 3, 1, 'Pending'),
('Case 9: Sarah Black vs William Gray', 'Civil', 'Closed', '2024-04-01', '2024-07-01', 4, 2, 'Filed'),
('Case 10: XYZ Ltd vs ABC Ltd', 'Criminal', 'Pending', '2024-05-01', '2024-08-10', 5, 3, 'Pending');



-- Insert into CaseFiles
INSERT INTO CaseFiles (CaseID, FileName, FileHash, status)
VALUES
(1, 'file_1.pdf', 'hash_1', 1),
(2, 'file_2.pdf', 'hash_2', 1),
(3, 'file_3.pdf', 'hash_3', 1),
(4, 'file_4.pdf', 'hash_4', 1),
(5, 'file_5.pdf', 'hash_5', 1),
(6, 'file_6.pdf', 'hash_6', 1),
(7, 'file_7.pdf', 'hash_7', 1),
(8, 'file_8.pdf', 'hash_8', 1),
(9, 'file_9.pdf', 'hash_9', 1),
(10, 'file_10.pdf', 'hash_10', 1);





-- INSERT INTO WitnessTable (CaseId, WitnessID)
-- VALUES 
--     (11, 1),
--     (11, 2),
--     (11, 3),
--     (11, 4),
--     (11, 5);



select * From WitnessTable;
select * from slots;
SELECT * FROM Users;
SELECT * FROM Courts;
SELECT * FROM Judges;
SELECT * FROM BarAssociations;
SELECT * FROM Lawyers;
SELECT * FROM CourtAdministrators;
SELECT * FROM Clients;
SELECT * FROM Witnesses;
SELECT * FROM Registrar;
SELECT * FROM Jurors;
SELECT * FROM ProbationOfficers;
SELECT * FROM ITAdmins;
SELECT * FROM UserApplication;
SELECT * FROM Cases;
SELECT * FROM CaseFiles;
SELECT * FROM Notifications;


INSERT INTO Notifications (CaseID, RecipientsID, SenderID, SenderType, Notification, CreatedAt)
VALUES
    (14, 16, 11, 'Admin', 'Notification 1: Case details have been updated.', '2024-11-22 10:00:00'),
    (14, 16, 11, 'Admin', 'Notification 2: New hearing date has been scheduled.', '2024-11-22 12:00:00'),
    (14, 16, 11, 'Admin', 'Notification 3: Your case summary has been uploaded.', '2024-11-22 15:00:00'),
    (14, 16, 11, 'Admin', 'Notification 4: Important documents are pending review.', '2024-11-22 17:00:00');




update cases set CaseStatus='Pending' where CaseID>=10;

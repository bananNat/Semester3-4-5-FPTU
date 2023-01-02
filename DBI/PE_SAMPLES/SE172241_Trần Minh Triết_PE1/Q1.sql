CREATE TABLE Professor
(
	ProfessorID int not null primary key,
	ProfessorName Varchar(20)
)

CREATE TABLE Course
(
	CourseID int not null primary key,
	CourseName Varchar(20)
)

CREATE TABLE student
(
	StudentID int not null primary key,
	DOB datetime,
	Name Varchar(20),
	Gender Varchar(10)
)
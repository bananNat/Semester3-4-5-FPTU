create table Departments
(
	DeptID varchar(15) primary key,
	Name nvarchar(60),
)

create table Offices
(
	OfficeNumber int primary key,
	Address nvarchar(30),
	Phone varchar(15),
	DeptID varchar(15) foreign key references Departments(DeptID)
)

create table Employees
(
	EmployeeID int primary key,
	FullName nvarchar(50),
	OfficeNumber int foreign key references Offices(OfficeNumber)
)

create table WorkFor
(
	"From" date,
	Salary float,
	"To" date,
	EmployeeID int foreign key references Employees(EmployeeID),
	DeptID varchar(15) foreign key references Departments(DeptID),
	primary key("From",EmployeeID,DeptID)
)




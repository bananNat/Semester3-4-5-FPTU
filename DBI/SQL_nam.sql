create table office
(
	officeNumber int primary key,
	location nvarchar(30),
	managed_employeeNumber int
)

create table employee
(
	employeeNumber int primary key,
	employeeName nvarchar(30),
	assigned_officeNumber int foreign key references office(officeNumber)
)

Alter table office add foreign key (managed_employeeNumber) references employee(employeeNumber)

create table property
(
	propertyID varchar(10) primary key,
	zipCode varchar(10),
	address nvarchar(30),
	city nvarchar(15),
	state nvarchar(15),
	officeNumber int foreign key references office(officeNumber)
)

create table owner
(
	ownerID varchar(10) primary key,
	ownerName nvarchar(30)
)

create table owned_by
(
	percentOwned float,
	propertyID varchar(10) foreign key references property(PropertyID),
	ownerID varchar(10) foreign key references owner(ownerID),
	primary key(percentOwned, propertyID, ownerID)

)
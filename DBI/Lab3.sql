create database Lab3

create table Employee (
	FName varchar(15) not null,
	MInit char(1),
	LName varchar(15) not null,
	SSN char(9) not null,
	BDate smalldatetime null,
	Address varchar(30),
	Sex char(1),
	Salary decimal(18,2),
	SuperSSN char(9),
	DNo int not null,
	HireDate smalldatetime null,
	constraint PK_Employee primary key (SSN),
	constraint FK_Employee_Employee foreign key (SuperSSN) references Employee (SSN),
)create table Department (
	DName varchar(15) not null,
	DNumber int not null,
	MgrSSN char(9) not null,
	MgrStartDate smalldatetime,
	nbrEmployees int,
	constraint PK_Department primary key (DNumber),
	constraint FK_Department_Employee foreign key (MgrSSN) references Employee (SSN)
	on delete cascade on update cascade
)alter table Employee
add constraint FK_Employee_Department foreign key (DNo) references Department (DNumber)

create table Project (
	PName varchar(15) not null,
	PNumber int not null,
	PLocation varchar(15),
	DNumber int not null,
	constraint PK_Project primary key (PNumber),
	constraint FK_Project_Department foreign key (DNumber) references Department (DNumber)
)

create table DeptLocations (
	DNumber int not null,
	DLocation varchar(15) not null,
	constraint PK_Dept_Locations primary key (DNumber,DLocation),
	constraint FK_Dept_Locations_Department foreign key (DNumber) references Department (DNumber)
)
create table Dependent (
	ESSN char(9) not null,
	DependentName varchar(15) not null,
	Sex char(1),
	BDate smalldatetime null,
	Relationship varchar(8),
	constraint PK_Dependent primary key (ESSN,DependentName),
	constraint FK_Dependent_Employee foreign key (ESSN) references Employee (SSN)
)
create table WorksOn (
	ESSN char(9) not null,
	PNo int not null,
	hours decimal(18,1) not null,
	constraint PK_WorksOn primary key (ESSN,PNo),
	constraint FK_WorksOn_Employee foreign key (ESSN) references Employee (SSN),
	constraint FK_WorksOn_Project foreign key (PNo) references Project (PNumber)
)-------------Q1-------------alter table Employee
	add constraint employee_Age18
	check ( dateadd(year,18,BDate) <= getdate() )-------------Q2-------------create trigger supervisorAge
on Employee
after insert, update
as
if exists (
select *
from Inserted I,
Employee E
where ( I.SuperSSN = E.SSN and I.BDate < E.BDate )
or ( E.SuperSSN = I.SSN and E.BDate < I.BDate ) )
begin
	raiserror( 'Constraint Violation:
	The age of an employee must be less than
	the age of his/her supervisor', 1, 1)
	rollback
end-------------Q3-------------create trigger supervisorSalary
on Employee
after insert, update
as
if exists (
select *
from Inserted I,
Employee E
where ( I.SuperSSN = E.SSN and I.Salary > E.Salary )
or ( E.SuperSSN = I.SSN and E.Salary > I.Salary ) )
begin
	raiserror('Constraint Violation:
	The salary of an employee cannot be greater than
	the salary of his/her supervisor', 1, 1)
	rollback
end-------------Q4-------------alter table Employee
add constraint UN_Employee_SSN_DNo
unique( SSN, DNO )
alter table Department
add constraint FK_Employee_SSN_DNo
foreign key( MgrSSN, DNumber )
references Employee( SSN, DNo )-------------Q5-------------alter table Project
add constraint FK_Project_DeptLocations
foreign key( DNumber, PLocation )
references DeptLocations( DNumber, DLocation )
-------------Q6-------------alter table Employee
add constraint HireDate_BDate
check( HireDate > BDate )-------------Q7-------------create trigger hireSuperv
on Employee
after insert, update
as
if exists (
	select *
		from Inserted I,
			Employee E
	where ( I.SuperSSN = E.SSN and datediff(year, E.HireDate, I.HireDate) < 1 )
		or ( E.SuperSSN = I.SSN and datediff(year, I.HireDate, E.HireDate) < 1 )
)
begin
	raiserror('Constraint Violation:
	A supervisor must be hired at least 1 year before
	every employee s/he supervises', 1, 1)
	rollback
end
-------------Q8-------------create trigger DeptNbrEmp_Employee_InsUpdDel_Derive
on Employee
after insert, update, delete
as
begin
	update Department set NbrEmployees = (
		select count(*) from Employee E where E.DNo = Department.DNumber )
	where Department.DNumber in (
		select distinct I.DNo from Inserted I ) or Department.DNumber in (
		select distinct D.DNo from Deleted D )end-------------Q9-------------create trigger empNbrProj
on WorksOn
after insert, update
as
if exists (
select *
from WorksOn W
group by W.ESSN
having count(*) > 4 )
begin
	raiserror('Constraint Violation: An employee works at
	most in 4 projects', 1, 1)
	rollback
end-------------Q10-------------create trigger workson_30_50
on WorksOn
after insert, update, delete
as
if exists ( select *
from WorksOn
group by ESSN
having ( sum(Hours) < 30 )
or ( sum(Hours) > 50 ) )
begin
raiserror('Constraint Violation: An employee works at
least 30 h/week and at most 50 h/week on all its projects', 1, 1)
rollback
end-------------Q11-------------create trigger worksonLess10h
on WorksOn
after insert, update
as
if exists ( select *
from WorksOn
where Hours < 10
group by PNo
having count(*) > 2 )
begin
	raiserror('Constraint Violation: A project can have at
	most 2 employees working on the project less than 10 hours', 1, 1)
	rollbackend-------------Q12-------------create trigger worksonLess5h_WorksOn
on WorksOn
after insert, update
as
if exists ( select *
from Inserted
where Hours < 5
and ESSN not in (
select MgrSSN
from Department
where MgrSSN is not null ) )begin	raiserror('Constraint Violation: Only department managers
	can work less than 5 hours on a project', 1, 1)
	rollbackend
create trigger worksonLess5h_Department
on Department
after update, delete
as
if exists ( select *
from Deleted
where MgrSSN not in (
select MgrSSN
from Department )
and MgrSSN in (
select ESSN
from WorksOn
where Hours < 5 ) )
begin
	raiserror('Constraint Violation: Only department managers
	can work less than 5 hours on a project', 1, 1)
	rollback
end-------------Q13-------------create trigger workson10h_WorksOn
on WorksOn
after insert, update
as
if exists ( select *
from Inserted
where Hours < 10
and ESSN not in (
select SuperSSN
from Employee
where SuperSSN is not null ) )
begin
raiserror('Constraint Violation: Employees that are not supervisors
must work at least 10 hours on every project they work', 1, 1)
rollback
end

create trigger workson10h_Employee
on Employee
after update, delete
as
if exists ( select *
from Deleted
where SuperSSN not in (
select SuperSSN
from Employee
where SuperSSN is not null )
and SuperSSN in (
select ESSN
from WorksOn
where Hours < 10 ) )
begin
	raiserror('Constraint Violation: Employees that are not supervisors
	must work at least 10 hours on every project they work', 1, 1)
	rollbackend-------------Q14-------------create trigger mgrProj_Department
on Department
after insert, update
as
if exists ( select *
from ( Inserted I join Project P on I.DNumber = P.DNumber )
left outer join WorksOn on MgrSSN = ESSN and PNumber = PNo
where Hours is null
or Hours < 5 )
begin
raiserror('Constraint Violation: A manager must work at least 5 hours
on all projects controlled by his/her department', 1, 1)
rollback
endcreate trigger mgrProj_Project
on Project
after insert, update
as
if exists ( select *
from ( Project P join Department D on D.DNumber = P.DNumber )
left outer join WorksOn on MgrSSN = ESSN and PNumber = PNo
where P.PNumber in ( select PNumber
from Inserted )
and ( Hours is null
or Hours < 5 ) )beginraiserror('Constraint Violation: A manager must work at least 5 hours
on all projects controlled by his/her department', 1, 1)
rollbackendcreate trigger mgrProj_WorksOn
on WorksOn
after update, delete
as
if exists ( select *
from ( Department D join Project P on D.DNumber=P.DNumber)
left outer join WorksOn on MgrSSN = ESSN and PNumber = PNo
where D.MgrSSN in ( select ESSN
from Deleted )
and ( Hours is null
or Hours < 5 ) )beginraiserror('Constraint Violation: A manager must work at least 5 hours
on all projects controlled by his/her department', 1, 1)
rollbackend-------------Q15-------------create trigger derived_Employee_SuperSSN_Department
on Department
after insert, update
as
if update(MgrSSN)
begin
update Employee
set SuperSSN = (
select case when SSN != D.MgrSSN
then D.MgrSSN
when SSN = D.MgrSSN and DNo != 1
then ( select MgrSSN
from Department
where DNumber = 1 )
else
null
end
from Department D
where DNo = D.DNumber )
-- if the department manager changes all employees of the department
-- must be updated
where ( DNo in (
select DNumber
from Inserted ) )
-- if the manager of department 1 changes, all department managers
-- must be updated
or ( 1 in (
select DNumber
from Inserted )
and SSN in (
select MgrSSN
from Department ) )endcreate trigger derived_Employee_SuperSSN_Employee
on Employee
after insert, update
as
if update(DNo)
begin
update Employee
set SuperSSN = (
select case when SSN != MgrSSN
then D.MgrSSN
when SSN = MgrSSN and I.DNo != 1
then ( select MgrSSN
from Department
where DNumber = 1 )
else
null
end
from Inserted I,
Department D
where SSN = I.SSN
and I.DNo = D.DNumber )
where SSN in (
select SSN
from Inserted )end
-------------Q16-------------create trigger noncyclic_subordinates
on Employee
after insert, update
as
begin
create table #Supervision (
SSN char(9),
SuperSSN char(9)
primary key (SSN,SuperSSN) )
insert into #Supervision
select SSN, SuperSSN
from Employee
where SuperSSN is not null
while @@rowcount != 0 -- while previous operation affected some rows
begin
if exists ( select *
from #Supervision
where SSN = SuperSSN )
begin
raiserror('Constraint Violation: The supervision
relationship is cyclic', 1, 1)
rollback
end
insert into #Supervision
select distinct S1.SSN, S2.SuperSSN
from #Supervision S1 join #Supervision S2
on S1.SuperSSN = S2.SSN
where not exists (
select *
from #Supervision S
where S.SSN = S1.SSN
and S.SuperSSN = S2.SuperSSN )end
end
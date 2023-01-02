select * from Employees
select * from Departments

select EmployeeID, count(ManagerID) from Employees e
select ManagerID , count(EmployeeID) as NumberOfSubordinates from Employees group by ManagerID

select e1.EmployeeID, e2.NumberOfSubordinates from Employees e1 left join
(select ManagerID , count(EmployeeID) as NumberOfSubordinates from Employees group by ManagerID)
as e2 on e1.EmployeeID = e2.ManagerID order by NumberOfSubordinates desc

select * from Employees e
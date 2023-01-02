select e.EmployeeID, e.FirstName, e.LastName, e.HireDate, e.JobID, j.JobTitle, e.DepartmentID, e.DepartmentID, d.DepartmentName from Employees e inner join Jobs j on e.JobID = j.JobID
inner join Departments d on d.DepartmentID = e.DepartmentID
where year(HireDate) = 2005 and JobTitle = 'Stock Clerk'
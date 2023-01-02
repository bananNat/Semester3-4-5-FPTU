create trigger Tr2 on Departments after Update
as
begin
	select i.DepartmentID, i.DepartmentName, d.ManagerID as OldManagerID, i.ManagerID as NewManagerID from inserted i inner join deleted d on i.DepartmentID = d.DepartmentID
end
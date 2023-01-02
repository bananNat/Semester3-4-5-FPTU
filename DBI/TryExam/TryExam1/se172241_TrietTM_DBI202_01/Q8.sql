create proc proc2 @fromDate date, @toDate date, @numberOfEmployees int output
as
begin
	select @numberOfEmployees = 
	(select count(*) from (select * from Employees where HireDate between @fromDate and @toDate) as F1)
end
select * from Employees where Salary >= 4000 and Salary <= 10000
and Commission_pct > 0 and (FirstName like '%E%' or
FirstName like '%e%') order by HireDate DESC
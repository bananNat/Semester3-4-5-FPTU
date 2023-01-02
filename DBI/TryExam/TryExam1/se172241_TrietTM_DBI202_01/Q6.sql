select * from Countries where CountryID in (select l.CountryID from Departments d right join Locations l on d.LocationID = l.LocationID group by CountryID
having count(DepartmentID) = 0)
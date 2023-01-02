delete from Locations where LocationID in(select l.LocationID 
from Departments d right join Locations l on d.LocationID = l.LocationID 
group by l.LocationID, d.LocationID
having count(DepartmentID) = 0)
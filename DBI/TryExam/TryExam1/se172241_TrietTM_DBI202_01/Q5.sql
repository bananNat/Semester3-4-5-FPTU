select j.JobID, j.JobTitle, count(e.JobID) from Jobs j inner join Employees e on j.JobID = e.JobID
group by j.JobID, j.JobTitle, e.JobID order by count(e.JobID) desc
select c.* from Customer c inner join Orders O on c.ID = O.CustomerID
where c.CustomerName like 'B%' and year(O.OrderDate) = 2017 and month(O.OrderDate) = 12
order by Segment DESC, CustomerName ASC
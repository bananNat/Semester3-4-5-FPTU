select * from(select top 5 * from Product order by UnitPrice DESC) as s1
union
select * from(select top 5 * from Product order by UnitPrice ASC) as s2

order by UnitPrice desc
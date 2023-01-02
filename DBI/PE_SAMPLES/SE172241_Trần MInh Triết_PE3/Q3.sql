select c.name, count(*) as [Number of films] from category c inner join film_category f on c.category_id = f.category_id
group by c.name, c.category_id order by [Number of films]
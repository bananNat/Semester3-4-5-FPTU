create proc NumberOfFilm @catName varchar(25), @count int output
as
begin
	
	select @count = count(*) from category c right join film_category f on c.category_id = f.category_id
	group by c.category_id, c.name having c.name = @catName
	
end

declare @count int 
exec NumberOfFilm 'Comedy', @count output
select @count

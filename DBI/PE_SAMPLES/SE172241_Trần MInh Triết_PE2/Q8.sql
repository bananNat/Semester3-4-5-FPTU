create proc CountProduct @OrderID nvarchar(255), @NbProducts int output
as
begin
	declare @count int
	select count(*) from OrderDetails group by OrderID having OrderID = @OrderID
	set @NbProducts = @count
end

declare @t int
exec CountProduct 'CA-2014-100391', @t output 
print @t

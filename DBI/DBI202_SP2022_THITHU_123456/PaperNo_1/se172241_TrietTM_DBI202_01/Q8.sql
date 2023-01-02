create proc proc_product_quantity @productID int, @totalQuantity int output
as
begin
	select @totalQuantity = (select sum(Quantity) from ProductInventory group by ProductID having ProductID = @productID)
end

declare @x int
exec proc_product_quantity 1, @x output
select @x as TotalQuantity
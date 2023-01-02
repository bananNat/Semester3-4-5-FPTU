insert into Category(CategoryName) values('Sports')
insert into SubCategory values('Tennis',(select c.ID from Category c where c.CategoryName = 'Sports'))
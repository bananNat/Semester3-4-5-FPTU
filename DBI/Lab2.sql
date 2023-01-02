SELECT CategoryName, Description FROM Categories ORDER BY CategoryName;

SELECT ContactName, CompanyName, ContactTitle, Phone FROM Customers ORDER BY Phone

SELECT UPPER(FirstName) AS FirstName, UPPER(LastName) AS Lastname, HireDate FROM Employees ORDER BY HireDate

SELECT TOP 10 OrderID, OrderDate, ShippedDate, CustomerID, Freight from Orders ORDER BY Freight DESC

SELECT LOWER(CustomerID) AS ID from Customers

SELECT CompanyName, Fax, Phone, Country, HomePage from Suppliers ORDER BY Country DESC, CompanyName

SELECT CompanyName, ContactName from Customers where City = 'Buenos Aires'

SELECT ProductName, UnitPrice, QuantityPerUnit FROM Products where Discontinued = 1;

SELECT ContactName, Address, City FROM Customers where Country NOT IN('Germany','Mexico','Spain');

SELECT OrderDate, ShippedDate, CustomerID, Freight FROM Orders WHERE OrderDate = '1996-05-21 ';

SELECT FirstName, LastName, Country FROM Employees where Country != 'United States'

SELECT EmployeeID, OrderID, CustomerID, RequiredDate, ShippedDate FROM Orders where ShippedDate > RequiredDate

SELECT City, CompanyName, ContactName FROM Customers where City like 'A%' OR City like 'B%'

SELECT OrderID FROM Orders where OrderID%2=0

SELECT * FROM Orders where Freight > 500

SELECT ProductName, UnitsInStock, UnitsOnOrder, ReorderLevel FROM Products where ReorderLevel != 0

SELECT CompanyName, ContactName FROM Customers Where fax is null

select FirstName, LastName from Employees where ReportsTo is null

select OrderID from Orders where OrderID%2=1

select CompanyName, ContactName, Fax from Customers where Fax is null Order by ContactName

select City, CompanyName, ContactName from Customers where City Like '%L%' order by ContactName

select FirstName, LastName, BirthDate from Employees WHERE BirthDate >= '1950-01-01' AND BirthDate < '1960-01-01';

select FirstName, LastName, year(BirthDate) from Employees

SELECT OrderID, count(OrderID) as NumberofOrders FROM Orders details GROUP BY OrderID ORDER BY NumberofOrders DESC


select s.SupplierID, p.ProductName, s.CompanyName from Suppliers s Join Products p ON s.SupplierID = p.SupplierID WHERE s.CompanyName IN ('Exotic Liquids','Specialty Biscuits, Ltd.','Escargots Nouveaux') ORDER BY s.SupplierID;

SELECT ShipPostalCode, OrderID, OrderDate, RequiredDate, ShippedDate,ShipAddress FROM Orders WHERE ShipPostalCode LIKE '98124%';

SELECT ContactName, ContactTitle, CompanyName FROM Customers WHERE ContactTitle NOT LIKE '%Sales%';

SELECT LastName, FirstName, City from Employees where City != 'Seattle'

SELECT CompanyName, ContactTitle, City, Country FROM Customers WHERE Country IN ('Mexico','Spain') AND City <> 'Madrid';

SELECT ContactName FROM Customers WHERE ContactName NOT LIKE '_A%';

SELECT round (avg (UnitPrice),0) AS AveragePrice, SUM(UnitsInStock) AS TotalStock, MAX(UnitsOnOrder) AS MaxOrder FROM Products;

SELECT s.SupplierID, s.CompanyName, c.CategoryName, p.ProductName, p.UnitPrice FROM Products p JOIN Suppliers s ON s.SupplierID = p.SupplierID JOIN Categories C ON c.CategoryID = p.CategoryID;

SELECT CustomerID, sum(Freight) FROM Orders GROUP BY CustomerID HAVING sum(Freight) > '200';

SELECT od.OrderID, c.ContactName,od.UnitPrice,od.Quantity,od.Discount FROM [Order Details] od JOIN Orders o ON od.OrderID = o.OrderID JOIN Customers c ON c.CustomerID = o.CustomerID WHERE od.Discount != '0';

SELECT a.EmployeeID, CONCAT (a.LastName, '' ,a.FirstName )as employee, CONCAT (b.LastName,'' , b.FirstName ) as manager FROM Employees a LEFT JOIN Employees b ON b.EmployeeID = a.ReportsTo ORDER BY a.EmployeeID

SELECT avg(UnitPrice) AS AveragePrice, min(UnitPrice)AS MinimumPrice, max(UnitPrice)AS MaximumPrice FROM Products

CREATE VIEW [CustomerInfo] AS
SELECT C.CustomerID, C.CompanyName, C.ContactName, C.ContactTitle, C.Address,
C.City,C.Country, C.Phone, O.OrderDate, O.RequiredDate, O.ShippedDate
FROM Customers C INNER JOIN Orders O ON C.CustomerID = O.CustomerID;

SELECT * FROM [CustomerInfo]

sp_rename 'dbo.[CustomerInfo]', 'CustomerDetails'
SELECT * FROM CustomerDetails

CREATE VIEW ProductDetails AS
SELECT P.ProductID, S.CompanyName, P.ProductName, C.CategoryName, 
C.Description, P.QuantityPerUnit, P.UnitPrice, P.UnitsInStock, P.UnitsOnOrder, P.ReorderLevel,
P.Discontinued FROM Products P INNER JOIN Suppliers S ON S.SupplierId = P.SupplierId INNER JOIN Categories C ON C.CategoryID = P.CategoryID

SELECT * FROM ProductDetails

DROP VIEW dbo.customerDetails;
SELECT * FROM CustomerDetails

SELECT SUBSTRING(Description, 1, 5) AS ShortInfo FROM Categories;

SELECT S.CompanyName, P.ProductName FROM 
Products P INNER JOIN Categories C ON C.CategoryID = P.CategoryID
INNER JOIN Suppliers S ON S.SupplierID = P.SupplierID WHERE C.CategoryName = 'Seafood';

SELECT CompanyName, ProductName FROM Products P JOIN Categories C ON C.CategoryID = P.CategoryID INNER JOIN Suppliers S ON S.SupplierID = P.SupplierID

SELECT * FROM Categories
SELECT * FROM Products
SELECT * FROM Suppliers
create table Warehouses
(
	Code Integer primary key not null,
	Location nvarchar(25) not null,
	Capacity Integer not null
);

create table Boxes
(
	Code nvarchar(25) primary key not null,
	Contents nvarchar(25) not null,
	Value real not null,
	Warehouse Integer not null references Warehouses(Code),
)

INSERT INTO Warehouses(Code,Location,Capacity) VALUES(1,'Chicago',3); INSERT INTO Warehouses(Code,Location,Capacity) VALUES(2,'Chicago',4); INSERT INTO Warehouses(Code,Location,Capacity) VALUES(3,'New York',7); INSERT INTO Warehouses(Code,Location,Capacity) VALUES(4,'Los Angeles',2); INSERT INTO Warehouses(Code,Location,Capacity) VALUES(5,'San Francisco',8); INSERT INTO Boxes(Code,Contents,Value,Warehouse) VALUES('0MN7','Rocks',180,3); INSERT INTO Boxes(Code,Contents,Value,Warehouse) VALUES('4H8P','Rocks',250,1); INSERT INTO Boxes(Code,Contents,Value,Warehouse) VALUES('4RT3','Scissors',190,4); INSERT INTO Boxes(Code,Contents,Value,Warehouse) VALUES('7G3H','Rocks',200,1); INSERT INTO Boxes(Code,Contents,Value,Warehouse) VALUES('8JN6','Papers',75,1); INSERT INTO Boxes(Code,Contents,Value,Warehouse) VALUES('8Y6U','Papers',50,3); INSERT INTO Boxes(Code,Contents,Value,Warehouse) VALUES('9J6F','Papers',175,2); INSERT INTO Boxes(Code,Contents,Value,Warehouse) VALUES('LL08','Rocks',140,4); INSERT INTO Boxes(Code,Contents,Value,Warehouse)
VALUES('P0H6','Scissors',125,1); INSERT INTO Boxes(Code,Contents,Value,Warehouse) VALUES('P2T6','Scissors',150,2); INSERT INTO Boxes(Code,Contents,Value,Warehouse) VALUES('TU55','Papers',90,5);

SELECT * FROM Warehouses

SELECT * FROM Boxes WHERE Value > 150;

SELECT DISTINCT Contents FROM Boxes;
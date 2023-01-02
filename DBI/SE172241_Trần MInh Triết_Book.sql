create database book

create table Student
(
	studentId nchar(10) primary key,
	name nchar(10),
	surname nchar(10),
	birthdate nchar(10),
	gender nchar(10),
	class nchar(10),
	point nchar(10)
)

create table Authors
(
	authorId nchar(10) primary key,
	name nchar(10),
	surname nchar(10),
)

create table Types
(
	typeId nchar(10) primary key,
	name nchar(10)
)

create table Books
(
	bookId nchar(10) primary key,
	name nchar(10),
	pagecount nchar(10),
	point nchar(10),
	authorId nchar(10),
	typeId nchar(10),
	foreign key (authorId) references Authors(authorId),
	foreign key (typeId) references Types(typeId)
)



create table Borrows
(
	studentId nchar(10),
	bookId nchar(10),
	borrowId nchar(10) primary key,
	takenDate nchar(10),
	broughtDate nchar(10),
	foreign key (studentId) references Student(studentId),
	foreign key (bookId) references Books(bookId)

)
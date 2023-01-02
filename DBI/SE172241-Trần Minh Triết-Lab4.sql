create database Lab4

use Lab4


create table Movie
(
	mID int primary key not null,
	title varchar(100) not null,
	year int,
	director nvarchar(100)
)

create table Reviewer
(
	rID int primary key not null,
	name nvarchar(100)
)

create table Rating
(
	rID int foreign key references Reviewer(rID),
	mID int foreign key references Movie(mID),
	stars int,
	ratingDate Date
)

Insert into Movie values(101, 'Gone with the Wind', 1939, 'Victor Fleming')
Insert into Movie values(102, 'Star Wars', 1977, 'George Lucas')
Insert into Movie values(103, 'The Sound of Music', 1965, 'Robert Wise')
Insert into Movie values(104, 'E.T.', 1982, 'Steven Spielberg')
Insert into Movie values(105, 'Titanic', 1997, 'James Cameron')
Insert into Movie values(106, 'Snow White', 1937, null)
Insert into Movie values(107, 'Avatar', 2009, 'James Cameron')
Insert into Movie values(108, 'Raiders of the Lost Ark', 1981, 'Steven Spielberg')

Insert into Reviewer values(201, 'Sarah Martinez');
Insert into Reviewer values(202, 'Daniel Lewis');
Insert into Reviewer values(203, 'Brittany Harris');
Insert into Reviewer values(204, 'Mike Anderson');
Insert into Reviewer values(205, 'Chris Jackson');
Insert into Reviewer values(206, 'Elizabeth Thomas');
Insert into Reviewer values(207, 'James Cameron');
Insert into Reviewer values(208, 'Ashley White');

Insert into Rating values(201, 101, 2, '2011-01-22');
Insert into Rating values(201, 101, 4, '2011-01-27');
Insert into Rating values(202, 106, 4, null);
Insert into Rating values(203, 103, 2, '2011-01-20');
Insert into Rating values(203, 108, 4, '2011-01-12');
Insert into Rating values(203, 108, 2, '2011-01-30');
Insert into Rating values(204, 101, 3, '2011-01-09');
Insert into Rating values(205, 103, 3, '2011-01-27');
Insert into Rating values(205, 104, 2, '2011-01-22');
Insert into Rating values(205, 108, 4, null);
Insert into Rating values(206, 107, 3, '2011-01-15');
Insert into Rating values(206, 106, 5, '2011-01-19');
Insert into Rating values(207, 107, 5, '2011-01-20');
Insert into Rating values(208, 104, 3, '2011-01-02');

--Question 1
SELECT Movie.title
FROM Movie
WHERE  Movie.Director = 'Steven Spielberg';

--Question 2
SELECT DISTINCT Movie.year
FROM Rating, Movie
WHERE Rating.stars < 6 AND Rating.stars > 3
AND Movie.mID = Rating.mID
ORDER BY Movie.year;

--Question 3
SELECT DISTINCT Movie.title
FROM Movie, Rating
WHERE Movie.mID NOT IN(
SELECT Movie.mID
FROM Movie,Rating
WHERE Movie.mID = Rating.mID);

--Question 4
SELECT Reviewer.name
FROM Reviewer, Rating
WHERE ratingDate IS NULL
AND Reviewer.rID = Rating.rID;

--Question 5
SELECT DISTINCT Reviewer.name, Movie.title, Rating.stars, Rating.ratingDate
FROM Movie, Reviewer, Rating
WHERE Reviewer.rID = Rating.rID AND Rating.mID = Movie.mID 
ORDER BY Reviewer.name, Movie.title, Rating.stars

--Question 6
SELECT Reviewer.name, Movie.title
FROM Rating R1, Rating R2, Reviewer, Movie
WHERE R1.rID= R2.rID
AND R1.RatingDate < R2.RatingDate
AND R1.mID = R2.mID
AND R1.stars < R2.stars
AND R2.mID = movie.mID
AND R2.rID = reviewer.rID ;

--Question 7
SELECT Movie.title, MAX(Rating.stars)
FROM  Movie, Rating
WHERE Movie.mID = Rating.mID
GROUP BY Movie.mID
ORDER BY Movie.title;

--Question 8
SELECT Movie.title, Rating.stars
FROM Movie, Rating
WHERE Movie.mID = Rating.mID
GROUP BY Movie.mID
ORDER BY Max(Rating.stars) - MIN(Rating.stars) DESC, Movie.title

--Question 9
select max(a1) - min(a1) from (
select avg(av1) a1 from (select avg(stars) av1 from rating r join movie m on r.mid=m.mid where m.year < 1980 group by r.mid)
union
select avg(av2) a1 from
(select avg(stars) av2 from rating r join movie m on r.mid=m.mid where m.year > 1980
group by r.mid));

--Question 10
select distinct name
from Rating
join Movie on Movie.mID = Rating.mID join Reviewer on Reviewer.rID = Rating.rID where title = 'Gone with the Wind';

--Question 11
select distinct name, title, stars
from Rating
join Movie on Movie.mID = Rating.mID join Reviewer on Reviewer.rID = Rating.rID where name = director;

--Question 12
(select distinct name
from Reviewer
union
select distinct title
from Movie) ORDER BY name;

--Question 13
select title from Movie
where mID not in
(select mID from Rating where rID in
(select rID from Reviewer
where name = 'Chris Jackson'))

--Question 14
select distinct Re1.name, Re2.name
from Rating R1, Rating R2, Reviewer Re1, Reviewer Re2
where R1.mID = R2.mID and R1.rID = Re1.rID and R2.rID = Re2.rID and Re1.name < Re2.name
order by Re1.name, Re2.name;

--Question 15
select name, title, stars
from Rating
join Movie on Movie.mID = Rating.mID join Reviewer on Reviewer.rID = Rating.rID
where stars <= (select min(stars) from Rating);

--Question 16
select title, avg(stars) as avg
from Rating
join Movie on Movie.mID = Rating.mID join Reviewer on Reviewer.rID = Rating.rID group by title
order by avg desc, title;

--Question 17
select name
from Rating
join Movie on Movie.mID = Rating.mID join Reviewer on Reviewer.rID = Rating.rID group by name
having count(name) >= 3;

--Question 18
select title, director from Movie
where director in
( select director
from movie
group by director
having count(*) > 1)
order by director, title;

--Question 19
select title, avg(stars) as avg from Rating
join Movie on Movie.mID = Rating.mID join Reviewer on Reviewer.rID = Rating.rID group by title

--Question 20
select title, avg(stars) as avg from Rating
join Movie on Movie.mID = Rating.mID join Reviewer on Reviewer.rID = Rating.rID group by title

--Question 21
select director, title, max(stars) from Rating
join Movie on Movie.mID = Rating.mID join Reviewer on Reviewer.rID = Rating.rID
where director is not null group by director;
--Question 22
insert into Reviewer (rID, name)
values ('209', 'Roger Ebert');

--Question 24
update Movie
set year = year + 25
where mID in
(select mID from
(select mID, avg(stars) as avg1
from Movie join Rating on Movie.mID = Rating.mID join Reviewer on Reviewer.rID = Rating.rID
where avg1 >= 4)

--Question 25
delete from rating
where mID in (select mID from movie where year < 1970 or year > 2000)
and stars < 4;
create database Excercise_1

create table s
(
	sid VARCHAR(10) primary key,
	sname NVARCHAR(10),
	rating NVARCHAR(10),
	age INT
)

create table b
(
	bid VARCHAR(10) primary key,
	bname NVARCHAR(10),
	color NVARCHAR(10)
)

create table r
(
	sid VARCHAR(10),
	bid VARCHAR(10),
	date VARCHAR(10),
	foreign key (bid) references b(bid),
	foreign key (sid) references s(sid)
)

select color from s,b,r where r.sid = s.sid AND r.bid = b.bid AND sname = 'Albert'

(select sid from s where rating >= 8) UNION (select sid from r where bid = 103)

SELECT sname FROM s WHERE sid NOT IN (SELECT sid FROM r,s WHERE r.sid=s.sid AND sname LIKE '%storm%') ORDER BY s.sname

SELECT sid FROM s WHERE age>20 AND sid NOT IN (SELECT sid FROM r, b WHERE r.bid=b.bid AND bname LIKE '%thunder%')

SELECT sname FROM s, r r1, r r2 WHERE s.sid = r1.sid AND s.sid=r2.sid AND r1.bid<>r2.bid

SELECT sname FROM s WHERE NOT EXISTS (SELECT * FROM b WHERE NOT EXISTS (SELECT * FROM r WHERE r.sid=s.sid AND r.bid=b.bid))

SELECT sname FROM s WHERE NOT EXISTS (SELECT * FROM b WHERE bname LIKE 'typhoon%' AND NOT EXISTS (SELECT * FROM r WHERE r.sid=s.sid AND r.bid=b.bid))SELECT s1.sid FROM s s1, s s2 WHERE s1.rating > s2.rating AND s2.sname='Bob'SELECT sid FROM s WHERE rating > all (SELECT rating FROM s s2 WHERE s2.sname='Bob')SELECT sid FROM s s1 WHERE s1.rating >= all (SELECT rating FROM s)SELECT s1.sname, s1.age FROM s s1 WHERE NOT EXISTS (SELECT * FROM s s2 WHERE s2.age > s1.age)SELECT s1.sname FROM s s1 WHERE NOT EXISTS (SELECT * FROM b, r, s s2 WHERE s2.sid=r.sid AND r.bid=b.bid AND s2.rating<s1.rating AND NOT EXISTS (SELECT * FROM r r2 WHERE s1.sid=r2.sid AND r2.bid=b.bid))SELECT rating, AVG(age) FROM s GROUP BY ratingSELECT b.bid, AVG(age) FROM b, r, s WHERE b.bid=r.bid AND r.sid=s.sid GROUP BY b.bid HAVING 5<=COUNT(DISTINCT r.sid)SELECT b.bid, AVG(age) FROM b, r, s WHERE s.age >= 40 AND b.bid = r.bid AND s.sid = r.sid GROUP BY b.bid HAVING 5 <= COUNT(DISTINCT s.sid)SELECT bid, AVG(s.age) FROM r, s (SELECT b.bid FROM b, r r2, s s2 WHERE b.bid = r2.bid AND s2.sid=r2.sid AND age >= 40 GROUP BY bid HAVING 5<=COUNT(DISTINCT r2.sid)) b1 WHERE b1.bid=r.bid AND r.sid=s.sid GROUP BY b1.bid
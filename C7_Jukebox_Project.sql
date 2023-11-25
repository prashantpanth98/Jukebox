create database Jukebox;
use Jukebox;

create table songs
(
songid int primary key,
songname varchar(30) not null,
artistname varchar(30) not null,
songgenre varchar(20) not null,
album varchar(30) not null,
songduration varchar(25) not null
);
select * from songs;

insert into songs values(1,'Saiyyan','Kailash Kher','Sufi','Kailasa','5:10');
insert into songs values(2,'Faasle','Shrey Singhal','Romantic','Faasle','4:35');
insert into songs values(3,'Darbadar','KK','Sad','Killer','4:27');


insert into songs values(4,'Amplifier','Imran Khan','Dj','Unforgettable','3:50');
insert into songs values(5,'Manchala','Shafqat Amanat Ali','Love','Hasee Toh Phasee','3:47');
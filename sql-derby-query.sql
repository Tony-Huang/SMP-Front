select * from app.stations

select * from app.monitors

select * from app.shifts;

select * from APP.STATIONDATA

create table app.test001(id BIGINT GENERATED BY DEFAULT AS IDENTITY, 
                     name VARCHAR(50) NOT NULL, 
                     status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE'
                     )
                     
insert into app.test001 (name) values('mytest1')

select  * from app.test001;



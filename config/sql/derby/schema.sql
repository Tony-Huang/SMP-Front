-- ***************************************************************************
-- HaiNa Confidential
-- 
-- OCO Source Materials
-- 
-- HaiNa Products: Spindle Monitoring Platform 
--
-- (C) Copyright HaiNa. 2015
-- 
-- The source code for this program is not published or otherwise divested of
-- its trade secrets, irrespective of what has been deposited with the China
-- Copyright Office.
-- ***************************************************************************

---tables
CREATE TABLE STATIONS
    (id BIGINT GENERATED BY DEFAULT AS IDENTITY, name VARCHAR(50) NOT NULL, description VARCHAR(50), status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    UNIQUE (name),
    PRIMARY KEY (id) );
    
CREATE TABLE MONITORS
    (id BIGINT GENERATED BY DEFAULT AS IDENTITY, name VARCHAR(50) NOT NULL, description VARCHAR(50)  DEFAULT 'WireLess', stationId BIGINT, model VARCHAR(20), status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE', protocol VARCHAR(50) , ip VARCHAR(50), port BIGINT, 
    FOREIGN KEY(stationId) REFERENCES STATIONS(id) ON DELETE CASCADE,
    UNIQUE (name, stationId),
    PRIMARY KEY (id) );
    
CREATE TABLE SPINDLES
   (id BIGINT GENERATED BY DEFAULT AS IDENTITY, name VARCHAR(50) , description VARCHAR(50), stationId BIGINT,
   FOREIGN KEY(stationId) REFERENCES STATIONS(id) ON DELETE CASCADE,
   PRIMARY KEY (id) );
   
CREATE TABLE SHIFTS
   (id BIGINT GENERATED BY DEFAULT AS IDENTITY, name VARCHAR(50) NOT NULL,  description VARCHAR(50),
   UNIQUE (name),
   PRIMARY KEY (id) );
   
CREATE TABLE STATIONDATA
   (id BIGINT GENERATED BY DEFAULT AS IDENTITY, stationId BIGINT, shiftId BIGINT, brokenSpindles INT, instantBrokenHeads INT, emptySpindles INT,creepSpindles INT,twist FLOAT, draftTimes FLOAT, powerKW FLOAT, eneryKWH FLOAT, productionEfficiency FLOAT, frontRollerSpeed FLOAT, brokenEndsPer1000Spindles FLOAT,  avgSpindleSpeed FLOAT, 
   realTimeProduction FLOAT, grossProductionByShift FLOAT, createdOn TIMESTAMP DEFAULT current_timestamp ,startTime TIME, endTime TIME, doffTime TIMESTAMP , warning VARCHAR(128), valid BOOLEAN DEFAULT true, doffBorkenEnds INT, accumulatedBrokenEnds INT, stationStatus VARCHAR(20), isBeforeDoff BOOLEAN , attr1 VARCHAR(128), attr2 DATE, attr3 TIME, attr4 INT, attr5 BOOLEAN,
    FOREIGN KEY(stationId) REFERENCES STATIONS(id) ON DELETE CASCADE,
    FOREIGN KEY(shiftId) REFERENCES SHIFTS(id),
    PRIMARY KEY (id) );
    
CREATE TABLE SPINDLEDATA
   (id BIGINT GENERATED BY DEFAULT AS IDENTITY, spindleId BIGINT,  spindleSpeed INT, status VARCHAR(20),
    FOREIGN KEY(spindleId) REFERENCES SPINDLES(id) ,
    PRIMARY KEY (id) );
    
CREATE TABLE  CRAFTPARAMS
   (id BIGINT GENERATED BY DEFAULT AS IDENTITY, stationId BIGINT, materialParam INT, categoryParam INT, spindleAmount INT, frontRollerDiameter FLOAT,midRollerDiameter FLOAT,backRollerDiameter FLOAT,interSensorRange FLOAT, standardSpeed INT, standardTwist FLOAT,
    FOREIGN KEY(stationId) REFERENCES STATIONS(id) ,
    PRIMARY KEY (id) 
    );
    
CREATE TABLE CODEMAPPINGS
   (id BIGINT GENERATED BY DEFAULT AS IDENTITY, codeNO INT, name VARCHAR(20), description VARCHAR(20),
     PRIMARY KEY (id) );
     
CREATE TABLE ROLES
   (id INT GENERATED BY DEFAULT AS IDENTITY,  name_CN VARCHAR(30), name_EN VARCHAR(30), description_CN VARCHAR(30),description_EN VARCHAR(30),
    UNIQUE (name_EN), 
    PRIMARY KEY (id) );
     
     
CREATE TABLE USERS
   (id INT GENERATED BY DEFAULT AS IDENTITY,  name VARCHAR(30), passwd VARCHAR(128), roleId INT,
     UNIQUE (name)
     FOREIGN KEY(roleId) REFERENCES ROLES(id) ,
     PRIMARY KEY (id) );   
     
CREATE TABLE VERSION
    (number VARCHAR(64), createdOn timestamp);
    
INSERT into VERSION values('{current.version}', current_timestamp);
insert into roles ( name_CN, name_EN, description_CN, description_EN ) values  ( '管理员' ,'Admin', '具有所有权限','have all privileges.') ;
insert into roles ( name_CN, name_EN, description_CN, description_EN ) values  ( '操作员' ,'Operators', '具有查看权限','have view privileges.') ;
---insert into users (name, passwd, roleId)  values ('admin','admin')

--- index
CREATE INDEX station_name on STATIONS (name);
CREATE INDEX monitor_name on MONITORS (name);
CREATE INDEX spindle_name on SPINDLES (name);
CREATE INDEX shift_name   on SHIFTS   (name);
CREATE INDEX codemap_name on CODEMAPPINGS (name);

CREATE INDEX monitor_station on MONITORS (stationId);
CREATE INDEX spindle_station on SPINDLES (stationId);
CREATE INDEX stdata_station    on STATIONDATA (stationId);
CREATE INDEX stdata_shift    on STATIONDATA (shiftId);
CREATE INDEX spdata_spindle  on SPINDLEDATA (spindleId);
CREATE INDEX spdata_stationdata  on SPINDLEDATA (stationDataId);
CREATE INDEX craftparam_station on CRAFTPARAMS (stationId);


 

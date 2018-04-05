DROP DATABASE IF EXISTS Starters;
CREATE DATABASE Starters;
USE Starters;
CREATE TABLE meetings (
id LONG NULL,
meetingname VARCHAR(45) NULL ,
meetingdate VARCHAR(45) NULL ,
meetingtime INT NULL ,
roomnumber INT NULL ,
occupantcount INT NULL ,
conferencenumber VARCHAR(45) NULL) ;
COMMIT;


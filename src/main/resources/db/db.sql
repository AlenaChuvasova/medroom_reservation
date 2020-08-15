CREATE SCHEMA `medroom_reservation`;

CREATE TABLE `medroom_reservation`.`room`
(
  `roomId`      INT                          NOT NULL AUTO_INCREMENT,
  `roomNumber`  INT                          NOT NULL,
  `roomType`    ENUM ('ULTRASOUND', 'OPHTHALMIC', 'DENTAL',
    'PROCEDURAL', 'OPERATING', 'CONSULTING') NOT NULL,
  `isAvailable` TINYINT,
  PRIMARY KEY (`roomId`),
  UNIQUE INDEX `roomNumber_UNIQUE` (`roomNumber` ASC) VISIBLE
);

CREATE TABLE `medroom_reservation`.`employee`
(
  `employeeId` INT                                              NOT NULL AUTO_INCREMENT,
  `name`       VARCHAR(45)                                      NOT NULL,
  `surname`    VARCHAR(45)                                      NOT NULL,
  `position`   ENUM ('DOCTOR', 'NURSE', 'ASSISTANT', 'CLEANER') NOT NULL,
  `isFree`     BOOLEAN,
  PRIMARY KEY (`employeeId`)
);

CREATE TABLE `medroom_reservation`.`reservation`
(
  `reservationId`    INT          NOT NULL AUTO_INCREMENT,
  `manipulationName` ENUM ('EXAMINATION', 'OPERATION',
    'TESTS', 'CONSULTATION')      NOT NULL,
  `description`      VARCHAR(500) NOT NULL,
  `startTime`        TIMESTAMP(6) NOT NULL,
  `endTime`          TIMESTAMP(6) NOT NULL,
  `isActive`         BOOLEAN,
  `employeeId`       INT          NOT NULL,
  `roomId`           INT          NOT NULL,
  PRIMARY KEY (`reservationId`),
  FOREIGN KEY (employeeId) REFERENCES employee (employeeId),
  FOREIGN KEY (roomId) REFERENCES room (roomId)
);

INSERT into employee(name, surname, position, isFree)
VALUES ('Petr', 'Ivanov', 'DOCTOR', true);
INSERT into employee(name, surname, position, isFree)
VALUES ('Helga', 'Bern', 'DOCTOR', true);
INSERT into employee(name, surname, position, isFree)
VALUES ('Anna', 'Dobrova', 'NURSE', true);
INSERT into employee(name, surname, position, isFree)
VALUES ('Nick', 'Valuev', 'ASSISTANT', true);
INSERT into employee(name, surname, position, isFree)
VALUES ('Den', 'Orlov', 'CLEANER', true);

INSERT into room(roomNumber, roomType, isAvailable)
VALUES (101, 'ULTRASOUND', true);
INSERT into room(roomNumber, roomType, isAvailable)
VALUES (102, 'PROCEDURAL', true);
INSERT into room(roomNumber, roomType, isAvailable)
VALUES (202, 'CONSULTING', true);
INSERT into room(roomNumber, roomType, isAvailable)
VALUES (201, 'OPERATING', true);
INSERT into room(roomNumber, roomType, isAvailable)
VALUES (301, 'OPHTHALMIC', true);
INSERT into room(roomNumber, roomType, isAvailable)
VALUES (501, 'DENTAL', true);

SELECT employeeId, CONCAT(name, ' ', surname) as fullname FROM employee where isFree = true;
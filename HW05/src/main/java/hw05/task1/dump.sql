CREATE DATABASE `cursor`;

USE `cursor`;

CREATE TABLE `shops` (
     `s_id` int unsigned NOT NULL AUTO_INCREMENT,
     `s_name` varchar(100) NOT NULL,
     `s_city` varchar(50) NOT NULL,
     `s_address` varchar(100) NOT NULL,
     `s_has_site` tinyint unsigned NOT NULL DEFAULT '0',
     `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
     `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
     PRIMARY KEY (`s_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `shops` (`s_id`,`s_name`,`s_city`,`s_address`,`s_has_site`,`created_at`,`updated_at`)
VALUES
    (1,'Tagopia','Gweru','88596 Caliangt Court',1,'2022-10-20 10:10:30','0000-00-00 00:00:00'),
    (2,'Layo','Wróblew','84 Harbort Road',0,'2022-10-20 10:10:30','0000-00-00 00:00:00'),
    (3,'Quatz','Citalahab','7335 Springs Crossing',1,'2022-10-20 10:10:30','0000-00-00 00:00:00'),
    (4,'Dynava','Jince','9377 Harbort Avenue',1,'2022-10-20 10:10:30','0000-00-00 00:00:00'),
    (5,'Miboo','Fonte da Vaca','27820 Farwell Point',0,'2022-10-20 10:10:30','0000-00-00 00:00:00')
;

CREATE TABLE `employees` (
     `e_id` int unsigned NOT NULL AUTO_INCREMENT,
     `f_shop_id` int unsigned NOT NULL,
     `e_email` varchar(50) NOT NULL,
     `e_first_name` varchar(50) NOT NULL,
     `e_last_name` varchar(50) NOT NULL,
     `e_gender` varchar(10) NOT NULL,
     `e_age` tinyint unsigned NOT NULL,
     `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
     `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
     PRIMARY KEY (`e_id`),
     KEY `employees_ibfk_1` (`f_shop_id`),
     CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`f_shop_id`) REFERENCES `shops` (`s_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `employees` (`e_id`,`f_shop_id`,`e_email`,`e_first_name`,`e_last_name`,`e_gender`,`e_age`,`created_at`,`updated_at`)
VALUES
    (1,1,'gloffel0@mozilla.org','Gilemette','Loffel','female',51,'2022-11-12 23:48:55','0000-00-00 00:00:00'),
    (2,2,'lclaige1@purevolume.com','Lisha','Claige','female',23,'2022-11-12 23:48:55','0000-00-00 00:00:00'),
    (3,3,'jellings2@webs.com','Jodee','Ellings','female',33,'2022-11-12 23:48:55','0000-00-00 00:00:00'),
    (4,4,'bbenduhn3@shinystat.com','Buckie','Benduhn','male',43,'2022-11-12 23:48:55','0000-00-00 00:00:00'),
    (5,5,'shazeman4@buzzfeed.com','Sophi','Hazeman','female',25,'2022-11-12 23:48:55','0000-00-00 00:00:00'),
    (6,1,'evanshin5@ca.gov','Eberhard','Vanshin','male',36,'2022-11-12 23:48:55','0000-00-00 00:00:00'),
    (7,2,'ttofts6@bloomberg.com','Tait','Tofts','male',27,'2022-11-12 23:48:55','0000-00-00 00:00:00'),
    (8,3,'shaddock7@abc.net.au','Sidonnie','Haddock','female',18,'2022-11-12 23:48:55','0000-00-00 00:00:00'),
    (9,4,'lstennes8@twitter.com','Lennie','Stennes','male',29,'2022-11-12 23:48:55','0000-00-00 00:00:00'),
    (10,5,'ayouens9@bandcamp.com','Aldon','Youens','male',40,'2022-11-12 23:48:55','0000-00-00 00:00:00'),
    (11,3,'agaylorda@sourceforge.net','Aurlie','Gaylord','female',41,'2022-11-12 23:48:55','0000-00-00 00:00:00'),
    (12,2,'mrusseb@statcounter.com','Matthaeus','Russe','male',22,'2022-11-12 23:48:55','0000-00-00 00:00:00'),
    (13,4,'sbrometc@washingtonpost.com','Saunders','Bromet','male',23,'2022-11-12 23:48:55','0000-00-00 00:00:00'),
    (14,3,'fvanderspohrd@gov.uk','Forster','Van der Spohr','male',34,'2022-11-12 23:48:55','0000-00-00 00:00:00'),
    (15,1,'ksouche@buzzfeed.com','Keith','Souch','male',35,'2022-11-12 23:48:55','0000-00-00 00:00:00'),
    (16,5,'sjosupeitf@geocities.com','Shae','Josupeit','male',26,'2022-11-12 23:48:55','0000-00-00 00:00:00'),
    (17,5,'shaddyg@deviantart.com','Sibella','Haddy','female',27,'2022-11-12 23:48:55','0000-00-00 00:00:00'),
    (18,4,'apatsallh@dagondesign.com','Ardene','Patsall','female',18,'2022-11-12 23:48:55','0000-00-00 00:00:00'),
    (19,2,'wjeroki@indiatimes.com','Wynn','Jerok','male',19,'2022-11-12 23:48:55','0000-00-00 00:00:00'),
    (20,5,'hweatherellj@china.com.cn','Herbie','Weatherell','female',20,'2022-11-12 23:48:55','0000-00-00 00:00:00')
;

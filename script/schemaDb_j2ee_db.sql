--
-- Database: `j2ee_db`
--

CREATE DATABASE IF NOT EXISTS `j2ee_db`;
USE `j2ee_db`;


-- ENTITIES

--
-- Struttura della tabella `user`
--

CREATE TABLE IF NOT EXISTS `user` (
	`mail` varchar(130) ,
	`name` varchar(130) ,
	`password` varchar(130)  NOT NULL,
	`phone` numeric ,
	`username` varchar(130)  NOT NULL,
	
	`_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT 

);


-- Security

ALTER TABLE `user` MODIFY COLUMN `password` varchar(128)  NOT NULL;

INSERT INTO `j2ee_db`.`user` (`username`, `password`, `_id`) VALUES ('admin', '62f264d7ad826f02a8af714c0a54b197935b717656b80461686d450f7b3abde4c553541515de2052b9af70f710f0cd8a1a2d3f4d60aa72608d71a63a9a93c0f5', 1);

CREATE TABLE IF NOT EXISTS `roles` (
	`role` varchar(30) ,
	
	-- RELAZIONI

	`_user` int(11)  NOT NULL REFERENCES user(_id),
	`_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT 

);
INSERT INTO `j2ee_db`.`roles` (`role`, `_user`, `_id`) VALUES ('ADMIN', '1', 1);


--
-- Struttura della tabella `adherent`
--

CREATE TABLE IF NOT EXISTS `adherent` (
	`email` varchar(130) ,
	`name` varchar(130) ,
	`phone` numeric ,
	
	`_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT 

);


--
-- Struttura della tabella `livre`
--

CREATE TABLE IF NOT EXISTS `livre` (
	`auteur` varchar(130) ,
	`titre` varchar(130) ,
	
	`_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT 

);


--
-- Struttura della tabella `pret`
--

CREATE TABLE IF NOT EXISTS `pret` (
	`date` date ,
	`date_retour` date ,
	`id_adherent` numeric ,
	`id_livre` numeric ,
	
	`_id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT 

);





-- relation 1:m adherent_pret pret - adherent
ALTER TABLE `pret` ADD COLUMN `adherent_pret` int(11)  REFERENCES adherent(_id);

-- relation 1:m livre_pret pret - livre
ALTER TABLE `pret` ADD COLUMN `livre_pret` int(11)  REFERENCES livre(_id);



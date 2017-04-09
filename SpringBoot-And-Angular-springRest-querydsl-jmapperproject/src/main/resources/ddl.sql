create database if not exists elsewedy;

create table if not exists `elsewedy`.`customer`(
`id` int(11) not null auto_increment,
`first_name` varchar(50) not null,
`last_name` varchar(50) NOT NULL,
`email` varchar(50) not null,
`mobile_number` varchar(11) not NULL,
`address` varchar(100) not null,
`creation_date` datetime,
primary key(`id`),
CONSTRAINT `email_UNIQUE` UNIQUE(`email`)
);


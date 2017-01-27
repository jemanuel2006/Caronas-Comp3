create database bancocaronascomp3;
use bancocaronascomp3;

create table usuario(
id int not null auto_increment,
nome varchar(500) not null,
email varchar(500) not null,
telefone varchar(500) not null,
primary key (id)
);
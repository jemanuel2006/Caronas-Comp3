create database bancocaronascomp3;
use bancocaronascomp3;

create table usuario(
id int not null auto_increment,
nome varchar(500) not null,
email varchar(500) not null,
telefone varchar(500) not null,
primary key (id)
);

create table grupo(
id int not null auto_increment,
nome varchar(500) not null,
descricao varchar(500) not null,
regras varchar(5000) not null,
limite int not null,
primary key (id)
);

create table usuario_grupo(
id int not null auto_increment,
grupo_id int not null,
usuario_id int not null,
primary key (id),
foreign key (grupo_id) REFERENCES grupo(id),
foreign key (usuario_id) REFERENCES usuario(id)
);

create table motorista(
id int not null,
primary key (id)
);

create table veiculo(
id int not null auto_increment,
modelo varchar(500) not null,
placa varchar(500) not null,
cor varchar(500) not null,
usuario_id int not null,
primary key (id),
foreign key (usuario_id) REFERENCES motorista(id)
);
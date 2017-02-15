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

create table logradouro(
id int not null auto_increment,
cep varchar(500) not null,
cidade varchar(500) not null,
estado varchar(500) not null,
distrito varchar(500) not null,
endereco varchar(1000) not null,
numero int not null,
primary key (id)
);

create table origem(
id int not null,
primary key (id)
);

create table destino(
id int not null,
primary key (id)
);

create table carona(
id int not null auto_increment,
dia date not null,
hora_saida date not null,
motoristaId int not null,
veiculoId int not null,
origemId int not null,
destinoId int not null,
estadoCarona int not null,
primary key (id),
foreign key (motoristaId) REFERENCES motorista(id),
foreign key (veiculoId) REFERENCES veiculo(id),
foreign key (origemId) REFERENCES origem(id),
foreign key (destinoId) REFERENCES destino(id)
);

create table parada(
id int not null auto_increment,
caronaId int not null,
logradouroId int not null,
usuarioId int not null,
primary key (id),
foreign key (caronaId) REFERENCES carona(id),
foreign key (logradouroId) REFERENCES logradouro(id),
foreign key (usuarioId) REFERENCES usuario(id)
);

create table avaliacao(
id int not null auto_increment,
usuarioId int not null,
estrelas int not null,
primary key (id),
foreign key (usuarioId) REFERENCES usuario(id)
);

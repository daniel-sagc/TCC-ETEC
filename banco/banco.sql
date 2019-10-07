

create database banco

CREATE TABLE Usuario(
nome varchar(50) not null primary key,
email varchar(40),
dataNascimento varchar(20),
tipo varchar(20),
useru varchar(10),
senha varchar(20))



CREATE TABLE Dispositivos(
nPatrimonio int not null primary key,
tipo varchar(20),
modelo varchar(20),
locall varchar(20),
especifi varchar(200))


CREATE TABLE Material(
codigo int not null identity primary key,
categoriaM varchar(20),
quantidade int,
tipoM varchar(20),
especificacao varchar(100))



CREATE TABLE Loginu(
codigo int not null identity primary key,
useru varchar(10),
senha varchar(10))






CREATE TABLE Pedido(
codigo int not null identity primary key,
nome varchar(15),
data date,
especificacao varchar(50),
tipo varchar(15),
quantidade int,
codigoU int,
foreign key (codigoU) REFERENCES Loginu(codigo))





/***************************************/

select LoginU.useru
      from Pedido 
     inner join Loginu
on Pedido.codigou =Loginu.codigo








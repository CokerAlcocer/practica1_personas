
Create database dbpersona;
use dbpersona;

create table persona (
idPerson tinyint primary key auto_increment not null,
nombre varchar(50),
aPaterno varchar(50),
aMaterno varchar(50),
edad tinyint,
sexo varchar(20),
telefono varchar(15),
direccion varchar(255),
fechaNacimiento date,
estadoCivil varcher(20),
correo varchar(60),
trabajo boolean,
contrasena varchar(20),
estado boolean
)

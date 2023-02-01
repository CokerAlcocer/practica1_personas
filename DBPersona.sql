CREATE DATABASE dbpersona;
USE dbpersona;

CREATE TABLE persona (
    id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    a_paterno VARCHAR(50) NOT NULL,
    a_materno VARCHAR(50),
    edad TINYINT NOT NULL,
    sexo VARCHAR(20) NOT NULL,
    telefono VARCHAR(15) UNIQUE KEY NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    fecha_nacimiento DATE,
    estado_civil VARCHAR(20) NOT NULL,
    correo VARCHAR(100) UNIQUE KEY NOT NULL,
    contrasena VARCHAR(20) NOT NULL,
    estado boolean DEFAULT TRUE
);

CREATE TABLE tipo_operacion(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(20) UNIQUE KEY NOT NULL
);

CREATE TABLE json_persona(
    id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    datos JSON,
    id_persona BIGINT NOT NULL, -- ID DE LA PERSONA A LA QUE PERTENECE ESE JSON
    FOREIGN KEY (id_persona) REFERENCES persona(id)
);

CREATE TABLE operacion(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_tipo_operacion INT NOT NULL,
    dato_viejo JSON,
    dato_nuevo JSON NOT NULL,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_tipo_operacion) REFERENCES tipo_operacion(id)
);

INSERT INTO tipo_operacion(nombre) VALUES ('INSERT');
INSERT INTO tipo_operacion(nombre) VALUES ('UPDATE');
INSERT INTO tipo_operacion(nombre) VALUES ('DELETE');
INSERT INTO tipo_operacion(nombre) VALUES ('LOGIN');
INSERT INTO tipo_operacion(nombre) VALUES ('LOGOUT');
INSERT INTO tipo_operacion(nombre) VALUES ('FIND_ALL');
INSERT INTO tipo_operacion(nombre) VALUES ('FIND_BY');

DROP TRIGGER IF EXISTS operation_insert;
DELIMITER //
CREATE TRIGGER operation_insert AFTER INSERT ON json_persona FOR EACH ROW
BEGIN
    INSERT INTO operacion(id_tipo_operacion, dato_viejo, dato_nuevo) VALUES(1, '{}', new.datos);
END //
DELIMITER ;

DROP TRIGGER IF EXISTS operation_update;
DELIMITER //
CREATE TRIGGER operation_update AFTER UPDATE ON json_persona FOR EACH ROW
BEGIN
    INSERT INTO operacion(id_tipo_operacion, dato_viejo, dato_nuevo) VALUES(2, old.datos, new.datos);
END //
DELIMITER ;

DROP TRIGGER IF EXISTS operation_delete;
DELIMITER //
CREATE TRIGGER operation_delete AFTER DELETE ON json_persona FOR EACH ROW
BEGIN
    INSERT INTO operacion(id_tipo_operacion, dato_viejo, dato_nuevo) VALUES(3, old.datos, '{}');
END //
DELIMITER ;



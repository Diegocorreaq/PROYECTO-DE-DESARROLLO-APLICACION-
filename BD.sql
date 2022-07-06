create database Playstation_STORE;
USE Playstation_STORE; 


DROP TABLE IF EXISTS tb_tipo_juegos;
CREATE TABLE tb_tipo_juegos (
  cod_tipo int NOT NULL AUTO_INCREMENT,
  nom_tipo varchar(50) DEFAULT NULL,
  PRIMARY KEY (cod_tipo)
) ;

INSERT INTO tb_tipo_juegos VALUES 
(1,'Playstation 4'),
(2,'Playstation 5'),
(3,'Playstation 3'),
(4,'Playstation 2')
;

DROP TABLE IF EXISTS tb_Juegos;
CREATE TABLE tb_Juegos(
  cod_juegos int NOT NULL AUTO_INCREMENT,
  nom_juegos varchar(50) DEFAULT NULL,
  des_juegos varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  sto_juegos int DEFAULT NULL,
  pre_juegos double DEFAULT NULL,
  fec_fab_juegos date DEFAULT NULL,
  cod_tipo int DEFAULT NULL,
  PRIMARY KEY (cod_juegos),
FOREIGN KEY (cod_tipo) REFERENCES tb_tipo_juegos (cod_tipo)
);

INSERT INTO tb_Juegos VALUES 
(1,'Crash Team Racing Nitro-Fueled','Crash vuelve al volante Prepárate para desmelenarte con Crash™ Team Racing Nitro-Fueled. Es la experiencia auténtica de CTR y mucho más; completamente remasterizada y a tope de revoluciones',20,5,'2022-01-01',2),
(2,'Tekke7','es un videojuego de lucha desarrollado y distribuido por Namco Bandai Games. El juego es la séptima entrega principal de la saga Tekken y el primero en utilizar el motor gráfico Unreal Engine.',5,22,'2021-03-01',3),
(3,'Mortal Kombat 11','es un videojuego de lucha desarrollado por NetherRealm Studios y publicado por Warner Bros. Interactive Entertainment',20,22,'2021-03-01',1),
(4,'Need for Speed Heat','es un videojuego de carreras desarrollado por Ghost Games y publicado por Electronic Arts para Microsoft Windows',20,5,'2021-03-10',1),
(5,'Crash Bandicoot','es el nombre de una serie de videojuegos protagonizado por el personaje del mismo nombre',25,25.6,'2025-06-24',2);

UNLOCK TABLES;
--
-- Table structure for table `tb_enlace`
--

DROP TABLE IF EXISTS tb_enlace;

CREATE TABLE tb_enlace (
  idenlace int(11) NOT NULL AUTO_INCREMENT,
  descripcion varchar(45) DEFAULT NULL,
  ruta varchar(45) DEFAULT NULL,
  PRIMARY KEY (idenlace)
) ;

--
-- Dumping data for table `tb_enlace`
--

INSERT INTO tb_enlace VALUES (1,'Juegos','/juegos/'),(2,'Usuario','/UsuarioPorRoles/'),(3,'Cliente','/Cliente/'),(4,'Boleta','/ventas/');

--
-- Table structure for table `tb_rol`
--

DROP TABLE IF EXISTS tb_rol;

CREATE TABLE tb_rol (
  idrol int(11) NOT NULL AUTO_INCREMENT,
  descripcion varchar(45) DEFAULT NULL,
  PRIMARY KEY (idrol)
);

--
-- Dumping data for table `tb_rol`
--
INSERT INTO tb_rol VALUES (1,'Administrador'),(2,'Caja'),(3,'Contabilidad'),(4,'Pediente de Rol'),(5,'Jefe de Tienda');


--
-- Table structure for table `tb_rol_enlace`
--

DROP TABLE IF EXISTS tb_rol_enlace;

CREATE TABLE tb_rol_enlace (
  idrol int(11) NOT NULL,
  idenlace int(11) NOT NULL,
  PRIMARY KEY (idrol,idenlace),
  KEY fk25 (idenlace),
  CONSTRAINT fk24 FOREIGN KEY (idrol) REFERENCES tb_rol (idrol),
  CONSTRAINT fk25 FOREIGN KEY (idenlace) REFERENCES tb_enlace (idenlace)
);

--
-- Dumping data for table `tb_rol_enlace`
--
INSERT INTO tb_rol_enlace VALUES (1,1),(1,2),(1,3),(1,4),(2,3),(2,4),(3,1),(3,3),(5,1),(5,3),(5,4);


DROP TABLE IF EXISTS tb_usuario;
CREATE TABLE tb_usuario (
  cod_usu int(11) NOT NULL auto_increment,
  login varchar(45) DEFAULT NULL,
  password varchar(200) DEFAULT NULL,
  idrol int(11) DEFAULT NULL,
  nombre VARCHAR(45),
  apellido VARCHAR(45),
  PRIMARY KEY (cod_usu),
  KEY fk23 (idrol),
  CONSTRAINT fk23 FOREIGN KEY (idrol) REFERENCES tb_rol (idrol)
);
--
-- Dumping data for table `tb_usuario`
--
INSERT INTO tb_usuario VALUES (null,'diego','$2a$10$d4W10yYoDssOewZWLOpeU.dzttGkMWaJQtPfDt.OVTbd4Kr4yhXsy',1,'Diego','Correa');

DROP TABLE IF EXISTS tb_cliente;

CREATE TABLE tb_cliente (
  cod_cli int NOT NULL AUTO_INCREMENT,
  nom_cli varchar(30) DEFAULT NULL,
  pat_cli varchar(30) DEFAULT NULL,
  mat_cli varchar(30) DEFAULT NULL,
  sex_cli varchar(15) DEFAULT NULL,
  dni_cli int DEFAULT NULL,
  fec_nac_cli date DEFAULT NULL,
  cel_cli int DEFAULT NULL,
  dir_cli varchar(50) DEFAULT NULL,
  PRIMARY KEY (cod_cli)
);

--
-- Dumping data for table `tb_cliente`
--
INSERT INTO `playstation_store`.`tb_cliente` (`cod_cli`, `nom_cli`, `pat_cli`, `mat_cli`, `sex_cli`, `dni_cli`, `fec_nac_cli`, `cel_cli`) VALUES ('2', 'Diego', 'Vidal', 'Sanchez', 'Femenino', '74805926', '2011-11-22', '984123698');
INSERT INTO `playstation_store`.`tb_cliente` (`cod_cli`, `nom_cli`, `pat_cli`, `mat_cli`, `sex_cli`, `dni_cli`, `fec_nac_cli`, `cel_cli`) VALUES ('3', 'Santiago', 'Sanchez', 'Gonzales', 'Masculino', '12345678', '2010-11-22', '984123698');
INSERT INTO `playstation_store`.`tb_cliente` (`cod_cli`, `nom_cli`, `pat_cli`, `mat_cli`, `sex_cli`, `dni_cli`, `fec_nac_cli`, `cel_cli`) VALUES ('4', 'Mario', 'Arquimedes', 'Ayala', 'Masculino', '25874136', '2010-11-22', '984123698');
INSERT INTO `playstation_store`.`tb_cliente` (`cod_cli`, `nom_cli`, `pat_cli`, `mat_cli`, `sex_cli`, `dni_cli`, `fec_nac_cli`, `cel_cli`) VALUES ('5', 'Junior', 'Rodriguez', 'Yanez', 'Masculino', '25874963', '2010-11-22', '984123698');



DROP TABLE IF EXISTS tb_boleta;

CREATE TABLE tb_boleta (
  num_bol int NOT NULL AUTO_INCREMENT,
  cod_cli int DEFAULT NULL,
  cod_usu int DEFAULT NULL,
  fec_emi_bol date DEFAULT NULL,
  monto_bol decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (num_bol),
  KEY fk4 (cod_usu),
  KEY fk5 (cod_cli),
  CONSTRAINT fk4 FOREIGN KEY (cod_usu) REFERENCES tb_usuario (cod_usu),
  CONSTRAINT fk5 FOREIGN KEY (cod_cli) REFERENCES tb_cliente (cod_cli)
);
--
-- Dumping data for table `tb_boleta`
--



DROP TABLE IF EXISTS tb_Juegos_has_boleta;

CREATE TABLE tb_Juegos_has_boleta (
  num_bol int NOT NULL,
  cod_juegos int NOT NULL,
  pre double DEFAULT NULL,
  KEY fk6 (num_bol),
  KEY fk7 (cod_juegos),
  CONSTRAINT fk6 FOREIGN KEY (num_bol) REFERENCES tb_boleta (num_bol),
  CONSTRAINT fk7 FOREIGN KEY (cod_juegos) REFERENCES tb_juegos (cod_juegos)
);

--
-- Dumping data for table `tb_medicamento_has_boleta`
--


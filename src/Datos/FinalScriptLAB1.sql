-- CONEXION
--Tablespace de datos
CREATE TABLESPACE UNIVERSIDAD
DATAFILE
  'C:\oraclexe\app\oracle\oradata\XE\UNIVERSIDAD_1.dbf'
SIZE 512M
ONLINE;

--Tablespace temporal
CREATE TEMPORARY TABLESPACE UNIVERSIDAD_TEMP
TEMPFILE
'C:\oraclexe\app\oracle\oradata\XE\UNIVERSIDAD_TEMP.dbf'
SIZE 128M
AUTOEXTEND ON;

CREATE USER UNV IDENTIFIED BY root123
       DEFAULT TABLESPACE UNIVERSIDAD
       TEMPORARY TABLESPACE UNIVERSIDAD_TEMP;
GRANT RESOURCE, CONNECT TO UNV;
GRANT CREATE SESSION TO UNV;
GRANT ALL PRIVILEGES TO UNV;
-- -----------------------------------------------------------

--Tablas

-- CARRERAS
CREATE TABLE unv.carrera (
  codigo VARCHAR(3) NOT NULL,
  nombre VARCHAR(100),
  titulo VARCHAR(100),
  CONSTRAINT pkcarrera PRIMARY KEY(codigo)
);
-- --------------------------------------------
-- CURSOS
CREATE TABLE unv.curso (
  codigo VARCHAR(7) NOT NULL,
  nombre VARCHAR(100),
  creditos NUMBER(5),
  horas_semanales NUMBER(5),
  carrera VARCHAR(3) NOT NULL,
  ciclo NUMBER(1) NOT NULL,
  anno VARCHAR2(4),
  CONSTRAINT pkcurso PRIMARY KEY(codigo)
);
-- --------------------------------------------
-- ALUMNOS
CREATE TABLE unv.alumno (
  id VARCHAR(10) NOT NULL,
  nombre VARCHAR(50),
  telefono VARCHAR(12),
  email VARCHAR(100),
  fecha_nacimiento VARCHAR(10),
  carrera VARCHAR(3) NOT NULL,
  CONSTRAINT pkalumno PRIMARY KEY (id)
);
-- --------------------------------------------
-- PROFESORES
CREATE TABLE unv.profesor (
  id VARCHAR(10) NOT NULL,
  nombre VARCHAR(50),
  telefono VARCHAR(12),
  email VARCHAR(100),
  CONSTRAINT pkprofesor PRIMARY KEY (id)
);
-- --------------------------------------------
-- GRUPOS
CREATE TABLE unv.grupo (
  codigo VARCHAR(7) NOT NULL,
  ciclo NUMBER(1) NOT NULL,
  curso VARCHAR(7) NOT NULL,
  numero_grupo NUMBER(2),
  horario VARCHAR(50),
  profesor VARCHAR(10),
  alumno VARCHAR(10),
  CONSTRAINT pkgrupo PRIMARY KEY (codigo)
);
-- --------------------------------------------
-- CICLOS
CREATE TABLE unv.ciclo (
  ciclo NUMBER(1) NOT NULL,
  anno VARCHAR2(4),
  fecha_inicio DATE,
  fecha_finalizacion DATE,
  CONSTRAINT pkciclo PRIMARY KEY (ciclo)
);
-- --------------------------------------------
-- NOTAS
CREATE TABLE unv.notas (
  nota FLOAT,
  curso VARCHAR(7) NOT NULL,
  alumno VARCHAR(10) NOT NULL
);
-- --------------------------------------------
-- USUARIOS
CREATE table usuario(
    id VARCHAR(10) NOT NULL,
    nombre VARCHAR(50),
    nombreUsuario VARCHAR(30),
    contrasena VARCHAR(10),
    rol VARCHAR(4),
    CONSTRAINT pkusuario PRIMARY KEY (id)
);
-- --------------------------------------------
-- ROLES
CREATE TABLE unv.roles (
  codigo VARCHAR(4) NOT NULL,
  nombre VARCHAR(50),
  CONSTRAINT pkrol PRIMARY KEY (codigo)
);
-- --------------------------------------------
-- MATRICULADOR
CREATE TABLE unv.matriculador (
  id VARCHAR(10) NOT NULL,
  nombre VARCHAR(50),
  CONSTRAINT pkmatriculador PRIMARY KEY (id)
);
-- --------------------------------------------
-- ADMINISTRADOR
CREATE TABLE unv.administrador (
  id VARCHAR(10) NOT NULL,
  nombre VARCHAR(50),
  CONSTRAINT pkadministrador PRIMARY KEY (id)
);
-- --------------------------------------------

-- PROCEDIMIENTOS ALMACENADOS SP
-- --------------------------------------------
-- ALUMNOS
create or replace PROCEDURE insertarAlumnos(
  id IN alumno.id%TYPE,
  nombre IN alumno.nombre%TYPE,
  telefono IN alumno.telefono%TYPE,
  email IN alumno.email%TYPE,
  fecha_nacimiento IN alumno.fecha_nacimiento%TYPE,
  carrera IN alumno.carrera%TYPE
)
AS
BEGIN
	INSERT INTO alumno VALUES(id,nombre,telefono,email,fecha_nacimiento,carrera);
END;
--
create or replace PROCEDURE modificarAlumnos (
  idin IN alumno.id%TYPE,
  nombrein IN alumno.nombre%TYPE,
  telefonoin IN alumno.telefono%TYPE,
  emailin IN alumno.email%TYPE,
  fecha_nacimientoin IN alumno.fecha_nacimiento%TYPE,
  carrerain IN alumno.carrera%TYPE
)
AS
BEGIN
  UPDATE alumno SET
    nombre = nombrein,
    telefono = telefonoin,
    email = emailin,
    fecha_nacimiento = fecha_nacimientoin,
    carrera = carrerain
  WHERE id = idin;
END;
--
create or replace PROCEDURE eliminarAlumnos(idin IN alumno.id%TYPE)
AS
BEGIN
    DELETE FROM alumno WHERE id = idin;
END;
-- --------------------------------------------
-- CARRERAS
create or replace PROCEDURE insertarCarreras(
  codigo IN carrera.codigo%TYPE,
  nombre IN carrera.nombre%TYPE,
  titulo IN carrera.titulo%TYPE
)
AS
BEGIN
	INSERT INTO carrera VALUES(codigo,nombre,titulo);
END;
--
create or replace PROCEDURE modificarCarreras (
  cod_carrerain IN carrera.codigo%TYPE,
  nombre_carrerain IN carrera.nombre%TYPE,
  tituloin IN carrera.titulo%TYPE
)
AS
BEGIN
  UPDATE carrera SET
    nombre = nombre_carrerain,
    titulo = tituloin
  WHERE codigo = cod_carrerain;
END;
--
create or replace PROCEDURE eliminarCarreras(cod_carrerain IN carrera.codigo%TYPE)
AS
BEGIN
    DELETE FROM carrera WHERE codigo = cod_carrerain;
END;
-- --------------------------------------------
-- CURSOS
create or replace PROCEDURE insertarCursos(
  codigo IN curso.codigo%TYPE,
  nombre IN curso.nombre%TYPE,
  creditos IN curso.creditos%TYPE,
  horas_semanales IN curso.horas_semanales%TYPE,
  carrera IN curso.carrera%TYPE,
  ciclo IN curso.ciclo%TYPE,
  anno IN curso.anno%TYPE
)
AS
BEGIN
	INSERT INTO curso VALUES(codigo,nombre,creditos,horas_semanales,carrera,ciclo,anno);
END;
--
create or replace PROCEDURE modificarCursos (
  cod_cursoin IN curso.codigo%TYPE,
  nombre_cursoin IN curso.nombre%TYPE,
  creditosin IN curso.creditos%TYPE,
  horas_semanalesin IN curso.horas_semanales%TYPE,
  cod_carrerain IN curso.carrera%TYPE,
  cicloin IN curso.ciclo%TYPE,
  annoin IN curso.anno%TYPE
)
AS
BEGIN
  UPDATE curso SET
    nombre = nombre_cursoin,
    creditos = creditosin,
    horas_semanales = horas_semanalesin,
    carrera = cod_carrerain,
    ciclo = cicloin,
    anno = annoin
  WHERE codigo = cod_cursoin;
END;
--
create or replace PROCEDURE eliminarCursos(cod_cursoin IN curso.codigo%TYPE)
AS
BEGIN
    DELETE FROM curso WHERE codigo = cod_cursoin;
END;
-- --------------------------------------------
-- PROFESORES
create or replace PROCEDURE insertarProfesores(
  id IN profesor.id%TYPE,
  nombre IN profesor.nombre%TYPE,
  telefono IN profesor.telefono%TYPE,
  email IN profesor.email%TYPE
)
AS
BEGIN
	INSERT INTO profesor VALUES(id,nombre,telefono,email);
END;
--
create or replace PROCEDURE modificarProfesores (
  idin IN profesor.id%TYPE,
  nombrein IN profesor.nombre%TYPE,
  telefonoin IN profesor.telefono%TYPE,
  emailin IN profesor.email%TYPE
)
AS
BEGIN
  UPDATE profesor SET
    nombre = nombrein,
    telefono = telefonoin,
    email = emailin
  WHERE id = idin;
END;
--
create or replace PROCEDURE eliminarProfesores(idin IN profesor.id%TYPE)
AS
BEGIN
    DELETE FROM profesor WHERE id = idin;
END;
-- --------------------------------------------
-- CICLOS
create or replace PROCEDURE insertarCiclos(
  ciclo IN ciclo.ciclo%TYPE,
  anno IN ciclo.anno%TYPE,
  fecha_inicio IN ciclo.fecha_inicio%TYPE,
  fecha_finalizacion IN ciclo.fecha_finalizacion%TYPE
)
AS
BEGIN
	INSERT INTO ciclo VALUES(ciclo,anno,fecha_inicio,fecha_finalizacion);
END;
--
create or replace PROCEDURE modificarCiclos (
  cicloin IN ciclo.ciclo%TYPE,
  annoin IN ciclo.anno%TYPE,
  fecha_inicioin IN ciclo.fecha_inicio%TYPE,
  fecha_finalizacionin IN ciclo.fecha_finalizacion%TYPE
)
AS
BEGIN
  UPDATE ciclo SET
    anno = annoin,
    fecha_inicio = fecha_inicioin,
    fecha_finalizacion = fecha_finalizacionin
  WHERE ciclo = cicloin;
END;
--
create or replace PROCEDURE eliminarCiclos(cicloin IN ciclo.ciclo%TYPE)
AS
BEGIN
    DELETE FROM ciclo WHERE ciclo = cicloin;
END;
-- --------------------------------------------
-- GRUPOS
create or replace PROCEDURE insertarGrupos(
  codigo IN grupo.codigo%TYPE,
  ciclo IN grupo.ciclo%TYPE,
  curso IN grupo.curso%TYPE,
  numero_grupo IN grupo.numero_grupo%TYPE,
  horario IN grupo.horario%TYPE,
  profesor IN grupo.profesor%TYPE,
  alumno IN grupo.alumno%TYPE
)
AS
BEGIN
	INSERT INTO grupo VALUES(codigo,ciclo,curso,numero_grupo,horario,profesor,alumno);
END;
--
create or replace PROCEDURE modificarGrupos (
  codigoin IN grupo.codigo%TYPE,
  cicloin IN grupo.ciclo%TYPE,
  cursoin IN grupo.curso%TYPE,
  numero_grupoin IN grupo.numero_grupo%TYPE,
  horarioin IN grupo.horario%TYPE,
  profesorin IN grupo.profesor%TYPE,
  alumnoin IN grupo.alumno%TYPE
)
AS
BEGIN
  UPDATE grupo SET
    ciclo = cicloin,
    curso = cursoin,
    numero_grupo = numero_grupoin,
    horario = horarioin,
    profesor = profesorin,
    alumno = alumnoin
  WHERE codigo = codigoin;
END;
--
create or replace PROCEDURE eliminarGrupos(codigoin IN grupo.codigo%TYPE)
AS
BEGIN
    DELETE FROM grupo WHERE codigo = codigoin;
END;
-- --------------------------------------------
-- MATRICULADORES
create or replace PROCEDURE insertarMatriculadores (
  id IN matriculador.id%TYPE,
  nombre IN matriculador.nombre%TYPE
)
AS
BEGIN
	INSERT INTO matriculador VALUES(id,nombre);
END;
--
create or replace PROCEDURE modificarMatriculadores (
  idin IN matriculador.id%TYPE,
  nombrein IN matriculador.nombre%TYPE
)
AS
BEGIN
  UPDATE matriculador SET
    nombre = nombrein
  WHERE id = idin;
END;
--
create or replace PROCEDURE eliminarMatriculadores(idin IN matriculador.id%TYPE)
AS
BEGIN
    DELETE FROM matriculador WHERE id = idin;
END;
-- --------------------------------------------
-- ROLES
create or replace PROCEDURE insertarRoles (
  rol IN roles.codigo%TYPE,
  nombre IN roles.nombre%TYPE
)
AS
BEGIN
	INSERT INTO roles VALUES(rol,nombre);
END;
--
create or replace PROCEDURE modificarRoles (
  rolin IN roles.codigo%TYPE,
  nombrein IN roles.nombre%TYPE
)
AS
BEGIN
  UPDATE roles SET
    nombre = nombrein
  WHERE codigo = rolin;
END;
--
create or replace PROCEDURE eliminarRoles(valor IN roles.codigo%TYPE)
AS
BEGIN
    DELETE FROM roles WHERE codigo = valor;
END;
-- --------------------------------------------
-- NOTAS
create or replace PROCEDURE insertarNotas (
  nota IN notas.nota%TYPE,
  curso IN notas.curso%TYPE,
  alumno IN notas.alumno%TYPE
)
AS
BEGIN
	INSERT INTO notas VALUES(nota,curso,alumno);
END;
--
create or replace PROCEDURE modificarNotas (
  notain IN notas.nota%TYPE,
  cursoin IN notas.curso%TYPE,
  alumnoin IN notas.alumno%TYPE
)
AS
BEGIN
  UPDATE notas SET
    nota = notain
  WHERE curso = cursoin AND alumno = alumnoin;
END;
-- --------------------------------------------
-- ADMINISTRADORES
create or replace PROCEDURE insertarAdministradores (
  id IN administrador.id%TYPE,
  nombre IN administrador.nombre%TYPE
)
AS
BEGIN
	INSERT INTO administrador VALUES(id,nombre);
END;
--
create or replace PROCEDURE modificarAdministradores (
  idin IN administrador.id%TYPE,
  nombrein IN administrador.nombre%TYPE
)
AS
BEGIN
  UPDATE administrador SET
    nombre = nombrein
  WHERE id = idin;
END;
--
create or replace PROCEDURE eliminarAdministradores (idin IN administrador.id%TYPE)
AS
BEGIN
    DELETE FROM administrador WHERE id = idin;
END;
-- --------------------------------------------
-- USUARIOS
create or replace PROCEDURE insertarUsuarios(
  id IN usuario.id%TYPE,
  nombre IN usuario.nombre%TYPE,
  nombreUsuario IN usuario.nombreUsuario%TYPE,
  contrasena IN usuario.contrasena%TYPE,
  rol IN usuario.rol%TYPE
)
as
BEGIN
 INSERT INTO usuario VALUES(id,nombre,nombreUsuario,contrasena,rol);
END;
--
create or replace PROCEDURE modificarUsuarios(
  idin IN usuario.id%TYPE,
  nombrein IN usuario.nombre%TYPE,
  nombreUsuarioin IN usuario.nombreUsuario%TYPE,
  contrasenain IN usuario.contrasena%TYPE,
  rolin IN usuario.rol%TYPE
)
AS
BEGIN
 UPDATE usuario SET
    nombre = nombrein,
    nombreUsuario = nombreUsuarioin,
    contrasena = contrasenain,
    rol = rolin
  WHERE id = idin;
END;
--
create or replace PROCEDURE eliminarUsuarios(idin IN usuario.id%TYPE)
AS
BEGIN
 DELETE usuario WHERE id = idin;
END;
-- --------------------------------------------

-- Funciones
CREATE OR REPLACE PACKAGE types
AS
     TYPE ref_cursor IS REF CURSOR;
END;

-- ALUMNOS
create or replace FUNCTION listarAlumnos
RETURN Types.ref_cursor
AS
    alumno_cursor types.ref_cursor;
BEGIN
  OPEN alumno_cursor FOR
       SELECT id,nombre,telefono,email,fecha_nacimiento,carrera FROM alumno;
  RETURN alumno_cursor;
END;
--
create or replace FUNCTION buscarAlumnos(valor IN VARCHAR2)
RETURN Types.ref_cursor
AS
    alumno_cursor types.ref_cursor;
BEGIN
  OPEN alumno_cursor FOR
       SELECT id,nombre,telefono,email,fecha_nacimiento,carrera FROM alumno
       WHERE id = valor OR nombre = valor OR carrera = valor;
  RETURN alumno_cursor;
END;
--
create or replace FUNCTION buscarAlumnosById(valor IN VARCHAR2)
RETURN Types.ref_cursor
AS
    alumno_cursor types.ref_cursor;
BEGIN
  OPEN alumno_cursor FOR
       SELECT id,nombre,telefono,email,fecha_nacimiento,carrera FROM alumno
       WHERE id = valor;
  RETURN alumno_cursor;
END;
-- --------------------------------------------
-- CARRERAS
create or replace FUNCTION listarCarreras
RETURN Types.ref_cursor
AS
    carrera_cursor types.ref_cursor;
BEGIN
  OPEN carrera_cursor FOR
       SELECT codigo,nombre,titulo FROM carrera;
  RETURN carrera_cursor;
END;
--
create or replace FUNCTION buscarCarreras(valor IN carrera.codigo%TYPE)
RETURN Types.ref_cursor
AS
    carrera_cursor types.ref_cursor;
BEGIN
  OPEN carrera_cursor FOR
       SELECT codigo,nombre,titulo FROM carrera
       WHERE codigo = valor OR nombre = valor;
  RETURN carrera_cursor;
END;
--
create or replace FUNCTION buscarCarrerasById(valor IN carrera.codigo%TYPE)
RETURN Types.ref_cursor
AS
    carrera_cursor types.ref_cursor;
BEGIN
  OPEN carrera_cursor FOR
       SELECT codigo,nombre,titulo FROM carrera
       WHERE codigo = valor;
  RETURN carrera_cursor;
END;
--
create or replace FUNCTION mostrarCarreras
RETURN Types.ref_cursor
AS
    carrera_cursor types.ref_cursor;
BEGIN
  OPEN carrera_cursor FOR
       SELECT codigo,nombre FROM carrera;
  RETURN carrera_cursor;
END;
-- --------------------------------------------
-- CICLOS
create or replace FUNCTION listarCiclos
RETURN Types.ref_cursor
AS
    ciclo_cursor types.ref_cursor;
BEGIN
  OPEN ciclo_cursor FOR
       SELECT ciclo,anno,fecha_inicio,fecha_finalizacion FROM ciclo;
  RETURN ciclo_cursor;
END;
--
create or replace FUNCTION buscarCiclos(ciclobuscar IN ciclo.ciclo%TYPE)
RETURN Types.ref_cursor
AS
    ciclo_cursor types.ref_cursor;
BEGIN
  OPEN ciclo_cursor FOR
       SELECT ciclo,anno,fecha_inicio,fecha_finalizacion FROM ciclo
       WHERE ciclo = ciclobuscar;
  RETURN ciclo_cursor;
END;
-- --------------------------------------------
-- CURSOS
create or replace FUNCTION listarCursos
RETURN Types.ref_cursor
AS
    curso_cursor types.ref_cursor;
BEGIN
  OPEN curso_cursor FOR
       SELECT curso.CODIGO,curso.NOMBRE,creditos,horas_semanales,carrera,ciclo,anno FROM curso;
  RETURN curso_cursor;
END;
--
create or replace FUNCTION buscarCursos(palabra IN VARCHAR2)
RETURN Types.ref_cursor
AS
    curso_cursor types.ref_cursor;
BEGIN
  OPEN curso_cursor FOR
       SELECT curso.CODIGO,curso.NOMBRE,creditos,horas_semanales,carrera,ciclo,anno FROM curso
       WHERE curso.CODIGO = palabra or curso.NOMBRE = palabra or CARRERA = palabra;
  RETURN curso_cursor;
END;
--
create or replace FUNCTION buscarCursosPorCarrera(palabra IN VARCHAR2)
RETURN Types.ref_cursor
AS
    curso_cursor types.ref_cursor;
BEGIN
  OPEN curso_cursor FOR
       SELECT curso.CODIGO,curso.NOMBRE,creditos,horas_semanales,carrera,ciclo,anno FROM curso
       WHERE carrera = palabra;
  RETURN curso_cursor;
END;
--
create or replace FUNCTION buscarCursosById(palabra IN VARCHAR2)
RETURN Types.ref_cursor
AS
    curso_cursor types.ref_cursor;
BEGIN
  OPEN curso_cursor FOR
       SELECT curso.CODIGO,curso.NOMBRE,creditos,horas_semanales,carrera,ciclo,anno FROM curso
       WHERE curso.CODIGO = palabra;
  RETURN curso_cursor;
END;
-- --------------------------------------------
-- GRUPOS
create or replace FUNCTION listarGrupos
RETURN Types.ref_cursor
AS
    grupo_cursor types.ref_cursor;
BEGIN
  OPEN grupo_cursor FOR
       SELECT codigo,ciclo,curso,numero_grupo,horario,profesor,alumno FROM grupo;
  RETURN grupo_cursor;
END;
--
create or replace FUNCTION buscarGrupos(codigobuscar IN grupo.codigo%TYPE)
RETURN Types.ref_cursor
AS
    grupo_cursor types.ref_cursor;
BEGIN
  OPEN grupo_cursor FOR
       SELECT codigo,ciclo,curso,numero_grupo,horario,profesor,alumno FROM grupo
       WHERE codigo = codigobuscar;
  RETURN grupo_cursor;
END;
-- --------------------------------------------
-- MATRICULADORES
create or replace FUNCTION listarMatriculadores
RETURN Types.ref_cursor
AS
    matric_cursor types.ref_cursor;
BEGIN
  OPEN matric_cursor FOR
       SELECT id,nombre FROM matriculador;
  RETURN matric_cursor;
END;
--
create or replace FUNCTION buscarMatriculadores (idbuscar IN matriculador.id%TYPE)
RETURN Types.ref_cursor
AS
    matric_cursor types.ref_cursor;
BEGIN
  OPEN matric_cursor FOR
       SELECT id,nombre FROM matriculador
       WHERE id = idbuscar;
  RETURN matric_cursor;
END;
-- --------------------------------------------
-- NOTAS
create or replace FUNCTION listarNotas
RETURN Types.ref_cursor
AS
    notas_cursor types.ref_cursor;
BEGIN
  OPEN notas_cursor FOR
       SELECT curso,alumno,nota FROM notas;
  RETURN notas_cursor;
END;
--
create or replace FUNCTION buscarNotas(cursobuscar IN notas.curso%TYPE)
RETURN Types.ref_cursor
AS
    notas_cursor types.ref_cursor;
BEGIN
  OPEN notas_cursor FOR
       SELECT curso,alumno,nota FROM notas
       WHERE curso = cursobuscar;
  RETURN notas_cursor;
END;
-- --------------------------------------------
-- PROFESORES
create or replace FUNCTION listarProfesores
RETURN Types.ref_cursor
AS
    profesor_cursor types.ref_cursor;
BEGIN
  OPEN profesor_cursor FOR
       SELECT id,nombre,telefono,email FROM profesor;
  RETURN profesor_cursor;
END;
--
create or replace FUNCTION buscarProfesores(idbuscar IN VARCHAR2)
RETURN Types.ref_cursor
AS
    profesor_cursor types.ref_cursor;
BEGIN
  OPEN profesor_cursor FOR
       SELECT id,nombre,telefono,email FROM profesor
       WHERE id = idbuscar OR nombre = idbuscar;
  RETURN profesor_cursor;
END;
--
create or replace FUNCTION buscarProfesoresById(idbuscar IN VARCHAR2)
RETURN Types.ref_cursor
AS
    profesor_cursor types.ref_cursor;
BEGIN
  OPEN profesor_cursor FOR
       SELECT id,nombre,telefono,email FROM profesor
       WHERE id = idbuscar;
  RETURN profesor_cursor;
END;
-- --------------------------------------------
-- ROLES
create or replace FUNCTION listarRoles
RETURN Types.ref_cursor
AS
    rol_cursor types.ref_cursor;
BEGIN
  OPEN rol_cursor FOR
       SELECT codigo,nombre FROM roles;
  RETURN rol_cursor;
END;
--
create or replace FUNCTION buscarRoles(codigobuscar IN roles.codigo%TYPE)
RETURN Types.ref_cursor
AS
    rol_cursor types.ref_cursor;
BEGIN
  OPEN rol_cursor FOR
       SELECT codigo,nombre FROM roles
       WHERE codigo = codigobuscar;
  RETURN rol_cursor;
END;
-- --------------------------------------------
-- USUARIOS
create or replace FUNCTION listarUsuarios
RETURN Types.ref_cursor
AS
    usuario_cursor types.ref_cursor;
BEGIN
  OPEN usuario_cursor FOR
       SELECT id,nombre,nombreUsuario,contrasena,rol FROM usuario ;
RETURN usuario_cursor;
END;
--
create or replace FUNCTION buscarUsuarios(idin IN usuario.id%TYPE)
RETURN Types.ref_cursor
AS
    usuario_cursor types.ref_cursor;
BEGIN
  OPEN usuario_cursor FOR
       SELECT id,nombre,nombreUsuario,contrasena,rol FROM usuario WHERE id = idin;
RETURN usuario_cursor;
END;
-- ---------------------------------
-- ADMINISTRADORES
create or replace FUNCTION buscarAdministradores (idbuscar IN administrador.id%TYPE)
RETURN Types.ref_cursor
AS
    admi_cursor types.ref_cursor;
BEGIN
  OPEN admi_cursor FOR
       SELECT id,nombre FROM administrador
       WHERE id = idbuscar;
  RETURN admi_cursor;
END;
--
create or replace FUNCTION listarAdministradores
RETURN Types.ref_cursor
AS
    admi_cursor types.ref_cursor;
BEGIN
  OPEN admi_cursor FOR
       SELECT id,nombre FROM administrador;
  RETURN admi_cursor;
END;

--RELACIONES ENTRE TABLAS
ALTER TABLE UNV.CURSO ADD CONSTRAINT FK_CARRERA FOREIGN KEY (CARRERA) REFERENCES UNV.CARRERA (CODIGO);
ALTER TABLE UNV.CURSO ADD CONSTRAINT FK_CICLO FOREIGN KEY (CICLO) REFERENCES UNV.CICLO (CICLO);

ALTER TABLE UNV.GRUPO ADD CONSTRAINT FK_CICLO_G FOREIGN KEY (CICLO) REFERENCES UNV.CICLO (CICLO);
ALTER TABLE UNV.GRUPO ADD CONSTRAINT FK_CURSO FOREIGN KEY (CURSO) REFERENCES UNV.CURSO (CODIGO);

ALTER TABLE UNV.NOTAS ADD CONSTRAINT FK_CURSO_N FOREIGN KEY (CURSO) REFERENCES UNV.CURSO (CODIGO);
ALTER TABLE UNV.NOTAS ADD CONSTRAINT FK_ALUMNO_N FOREIGN KEY (ALUMNO) REFERENCES UNV.ALUMNO (ID);

ALTER TABLE UNV.ALUMNO ADD CONSTRAINT FK_CARRERA_A FOREIGN KEY (CARRERA) REFERENCES UNV.CARRERA (CODIGO);


-- DATOS QUEMADOS
INSERT INTO ROLES(CODIGO,NOMBRE) VALUES ('PROF','PROFESOR');
INSERT INTO ROLES(CODIGO,NOMBRE) VALUES ('ALUM','ALUMNO');
INSERT INTO ROLES(CODIGO,NOMBRE) VALUES ('MATR','MATRICULADOR');
INSERT INTO ROLES(CODIGO,NOMBRE) VALUES ('ADMI','ADMINISTRADOR');

INSERT INTO UNV.CICLO(CICLO,ANNO,FECHA_INICIO,FECHA_FINALIZACION) VALUES('1','2020','11/02/2020','16/06/2020');
INSERT INTO UNV.CICLO(CICLO,ANNO,FECHA_INICIO,FECHA_FINALIZACION) VALUES('2','2020','12/07/2020','18/11/2020');

insert into usuario(ID,NOMBRE,NOMBREUSUARIO,CONTRASENA,ROL) VALUES ('309650489','Katherine Ramirez','kramirez','123k','ALUM');
insert into usuario(ID,NOMBRE,NOMBREUSUARIO,CONTRASENA,ROL) VALUES ('502150489','Carol Vargas','cvargas','123c','ALUM');
insert into usuario(ID,NOMBRE,NOMBREUSUARIO,CONTRASENA,ROL) VALUES ('204560154','Pepe','pp','123p','PROF');
insert into usuario(ID,NOMBRE,NOMBREUSUARIO,CONTRASENA,ROL) VALUES ('204560147','Juan Pablo Zamora','jzamora','123j','ADMI');
insert into usuario(ID,NOMBRE,NOMBREUSUARIO,CONTRASENA,ROL) VALUES ('301590265','Marcela Solis','msolis','123m','ADMI');
insert into usuario(ID,NOMBRE,NOMBREUSUARIO,CONTRASENA,ROL) VALUES ('222222','Bernardo Torres','btorres','123b','MATR');

-- CARRERAS
insert into carrera(codigo,nombre,titulo) values ('EIF','Informatica','Diploma / Bachillerato');
insert into carrera(codigo,nombre,titulo) values ('ADM','Administracion','Diploma');
insert into carrera(codigo,nombre,titulo) values ('AGR','Ingenieria Agricola','Bachillerato');
insert into carrera(codigo,nombre,titulo) values ('RRI','Relaciones Internacioales','Bachillerato / Maestria');
insert into carrera(codigo,nombre,titulo) values ('ILE','Ingles','Diploma');
-- ALUMNOS
insert into alumno(id,nombre,telefono,email,fecha_nacimiento,carrera) values('207650489','Maria Perez','89457512','maria@gmail.com','1993-08-12','EIF');
insert into alumno(id,nombre,telefono,email,fecha_nacimiento,carrera) values('207860123','Oscar Alvarado','85451274','oscar@gmail.com','1991-01-10','EIF');
insert into alumno(id,nombre,telefono,email,fecha_nacimiento,carrera) values('307650489','Denisse Mora','82154745','denisse@gmail.com','1987-09-12','ADM');
insert into alumno(id,nombre,telefono,email,fecha_nacimiento,carrera) values('407650489','Marjorie Molina','88446633','marjo@gmail.com','1996-03-24','RRI');
insert into alumno(id,nombre,telefono,email,fecha_nacimiento,carrera) values('409650489','Daniel Fernandez','89740213','dany@gmail.com','1990-10-10','AGR');
insert into alumno(id,nombre,telefono,email,fecha_nacimiento,carrera) values('507650489','Odilie Vargas','81479858','odel@gmail.com','1997-12-12','AGR');
-- CURSOS
insert into curso(codigo,nombre,creditos,horas_semanales,carrera,ciclo,anno) values('EIF200','Fundamentos de Infromatica',3,10,'EIF',1,'I');
insert into curso(codigo,nombre,creditos,horas_semanales,carrera,ciclo,anno) values('EIF412','Programacion I',4,11,'EIF',2,'II');
insert into curso(codigo,nombre,creditos,horas_semanales,carrera,ciclo,anno) values('ADM400','Recursos Humanos',4,12,'ADM',1,'I');
insert into curso(codigo,nombre,creditos,horas_semanales,carrera,ciclo,anno) values('ADM405','Administracion Emp',5,15,'ADM',2,'III');
insert into curso(codigo,nombre,creditos,horas_semanales,carrera,ciclo,anno) values('AGR123','Biologia I Lab',3,12,'AGR',2,'I');
insert into curso(codigo,nombre,creditos,horas_semanales,carrera,ciclo,anno) values('AGR144','Quimica Basica',4,10,'AGR',1,'II');
insert into curso(codigo,nombre,creditos,horas_semanales,carrera,ciclo,anno) values('RRI300','Comercio',4,12,'RRI',1,'I');
insert into curso(codigo,nombre,creditos,horas_semanales,carrera,ciclo,anno) values('ILE400','Gramatica I',4,12,'ILE',1,'I');
-- PROFESORES
insert into profesor(id,nombre,telefono,email) values('204580125','Juan Morera','89467582','juan@gmail.com');
insert into profesor(id,nombre,telefono,email) values('203560025','Alicia Bogantes','88741523','alicia@gmail.com');
insert into profesor(id,nombre,telefono,email) values('302150144','Jose Alvarado','85412355','jose@gmail.com');
insert into profesor(id,nombre,telefono,email) values('11254896','Lucia Vargas','8844557788','luc@gmail.com');
insert into profesor(id,nombre,telefono,email) values('404870254','Bernardo Araya','89997445','bernard@gmail.com');

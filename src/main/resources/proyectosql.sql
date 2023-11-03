/*Se crea la base de datos */
drop schema if exists proyecto;
/*drop user if exists proyecto_estetica;*/
CREATE SCHEMA proyecto ;

create user 'proyecto_estetica'@'%' identified by 'la_Clave1234';

/*Se asignan los prvilegios  */
grant all privileges on proyecto.* to 'proyecto_estetica'@'%';
flush privileges;


CREATE TABLE proyecto.servicio (
  id_servicio INT NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(30) NOT NULL,
  ruta_imagen VARCHAR(1024),
  nombre VARCHAR(30) NOT NULL,
  precio DOUBLE,
  activo BOOL,
  PRIMARY KEY (id_servicio)
);
CREATE TABLE proyecto.ofertas (
    id_oferta INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    ruta_imagen VARCHAR(1024),
    descuento DOUBLE NOT NULL,
    activo BOOL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL
);
CREATE TABLE proyecto.cita (
    id_cita INT AUTO_INCREMENT PRIMARY KEY,
	nombre INT NOT NULL,
    id_servicio VARCHAR(255),
    fecha_hora DATETIME NOT NULL,
    correo_electronico VARCHAR(255),
    numero_telefono VARCHAR(20), 
    activo BOOL
);


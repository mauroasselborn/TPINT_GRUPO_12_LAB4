
-- Creación de base de datos
CREATE DATABASE IF NOT EXISTS BancoGrupo12;
USE BancoGrupo12;

-- Tabla provincias
CREATE TABLE provincias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- Tabla localidades
CREATE TABLE localidades (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    id_provincia INT NOT NULL,
    FOREIGN KEY (id_provincia) REFERENCES provincias(id)
);

-- Tabla tipo_usuario
CREATE TABLE tipo_usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL
);

-- Tabla nacionalidades
CREATE TABLE nacionalidades (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(100) NOT NULL
);

-- Tabla clientes
CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(20) UNIQUE NOT NULL,
    cuil VARCHAR(20) UNIQUE NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    sexo VARCHAR(10) NOT NULL,
    id_nacionalidad INT NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    id_localidad INT NOT NULL,
    id_provincia INT NOT NULL,
    correo_electronico VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    activo TINYINT(1) DEFAULT 1,
    FOREIGN KEY (id_nacionalidad) REFERENCES nacionalidades(id),
    FOREIGN KEY (id_localidad) REFERENCES localidades(id),
    FOREIGN KEY (id_provincia) REFERENCES provincias(id)
);

-- Tabla usuarios
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT UNIQUE NOT NULL,
    id_tipo_usuario INT NOT NULL,
    nombre_usuario VARCHAR(50) NOT NULL,
    contrasena VARCHAR(100) NOT NULL,
    activo TINYINT(1) DEFAULT 1,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id),
    FOREIGN KEY (id_tipo_usuario) REFERENCES tipo_usuario(id)
);

-- Tabla tipo_movimiento
CREATE TABLE tipo_movimiento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL
);


-- Tabla tipo_cuenta
CREATE TABLE tipo_cuenta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL
);


-- Tabla cuentas
CREATE TABLE cuentas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    numero_cuenta VARCHAR(20) UNIQUE NOT NULL,
    cbu VARCHAR(30) UNIQUE NOT NULL,
    id_tipo_cuenta INT NOT NULL,
    FOREIGN KEY (id_tipo_cuenta) REFERENCES tipo_cuenta(id),
    fecha_creacion DATE NOT NULL,
    saldo DECIMAL(10,2) NOT NULL DEFAULT 10000.00,
    activo TINYINT(1) DEFAULT 1,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id)
);

-- Tabla movimientos
CREATE TABLE movimientos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL,
    detalle TEXT NOT NULL,
    id_tipo_movimiento INT NOT NULL,
    importe DECIMAL(10,2) NOT NULL,
    tipo VARCHAR(10) NOT NULL, -- ingreso o egreso
    id_cuenta INT NOT NULL,
    FOREIGN KEY (id_tipo_movimiento) REFERENCES tipo_movimiento(id),
    FOREIGN KEY (id_cuenta) REFERENCES cuentas(id)
);

-- Tabla estado_prestamo
CREATE TABLE estado_prestamo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL
);

-- Tabla prestamos
CREATE TABLE prestamos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    id_cuenta INT NOT NULL,
    fecha DATE NOT NULL,
    importe_pedido DECIMAL(10,2) NOT NULL,
    cantidad_cuotas INT NOT NULL,
    importe_cuota DECIMAL(10,2) NOT NULL,
    cuotas_pendientes INT NOT NULL,
    id_estado INT NOT NULL,
    activo TINYINT(1) DEFAULT 1,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id),
    FOREIGN KEY (id_cuenta) REFERENCES cuentas(id),
    FOREIGN KEY (id_estado) REFERENCES estado_prestamo(id)
);

-- Tabla cuotas
CREATE TABLE cuotas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_prestamo INT NOT NULL,
    nro_cuota INT NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    fecha_pago DATE DEFAULT NULL,
    FOREIGN KEY (id_prestamo) REFERENCES prestamos(id)
);

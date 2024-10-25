DROP DATABASE IF EXISTS escuela;
CREATE DATABASE escuela;
USE escuela;

-- Tabla de estudiantes
CREATE TABLE estudiante (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL
);

-- Insertar datos en la tabla de estudiantes
INSERT INTO estudiante (nombre, email) VALUES
('Juan Pérez', 'juan.perez@example.com'),
('Ana García', 'ana.garcia@example.com'),
('Carlos Ruiz', 'carlos.ruiz@example.com');

-- Tabla de cursos
CREATE TABLE curso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT
);

-- Insertar datos en la tabla de cursos
INSERT INTO curso (nombre, descripcion) VALUES
('Matemáticas', 'Curso de matemáticas avanzadas'),
('Historia', 'Historia mundial'),
('Programación', 'Fundamentos de la programación en Java');

-- Tabla de inscripciones (relación entre estudiantes y cursos)
CREATE TABLE inscripcion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id INT,
    curso_id INT,
    FOREIGN KEY (estudiante_id) REFERENCES estudiante(id),
    FOREIGN KEY (curso_id) REFERENCES curso(id)
);

-- Insertar datos en la tabla de inscripciones
INSERT INTO inscripcion (estudiante_id, curso_id) VALUES
(1, 1),  -- Juan Pérez inscrito en Matemáticas
(2, 2),  -- Ana García inscrita en Historia
(3, 3),  -- Carlos Ruiz inscrito en Programación
(1, 3);  -- Juan Pérez también inscrito en Programación

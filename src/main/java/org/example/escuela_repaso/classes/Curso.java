package org.example.escuela_repaso.classes;

import java.util.Objects;

public class Curso {
    private int id_curso;
    private String nombre_curso;
    private String descripcion;

    public Curso(String nombre_curso, String descripcion) {
        this.nombre_curso = nombre_curso;
        this.descripcion = descripcion;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public String getNombre_curso() {
        return nombre_curso;
    }

    public void setNombre_curso(String nombre_curso) {
        this.nombre_curso = nombre_curso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return nombre_curso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return id_curso == curso.id_curso;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id_curso);
    }
}

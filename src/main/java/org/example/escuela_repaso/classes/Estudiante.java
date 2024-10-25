package org.example.escuela_repaso.classes;

public class Estudiante {
    private int id_est;
    private String nombre_est;
    private String email;
    private String cursos;

    public Estudiante(String nombre_est, String email) {
        this.nombre_est = nombre_est;
        this.email = email;
    }

    public Estudiante(String nombre_est, String email, String cursos) {
        this.nombre_est = nombre_est;
        this.email = email;
        this.cursos = cursos;
    }

    public int getId_est() {
        return id_est;
    }

    public void setId_est(int id_est) {
        this.id_est = id_est;
    }

    public String getNombre_est() {
        return nombre_est;
    }

    public void setNombre_est(String nombre_est) {
        this.nombre_est = nombre_est;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCursos() {
        return cursos;
    }

    public void setCursos(String cursos) {
        this.cursos = cursos;
    }

    @Override
    public String toString() {
        return nombre_est;
    }

    public String toStringCurso() {
        return cursos;
    }
}

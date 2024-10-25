package org.example.escuela_repaso.classes;

public class Inscripcion {
    private int id;
    int id_estudiante;
    int id_curso;

    public Inscripcion(Estudiante id_estudiante, Curso id_curso) {
        this.id_estudiante = id_estudiante.getId_est();
        this.id_curso = id_curso.getId_curso();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }
}

package org.example.escuela_repaso.DAO;

import org.example.escuela_repaso.classes.Curso;
import org.example.escuela_repaso.classes.Estudiante;
import org.example.escuela_repaso.util.Alerta;
import org.example.escuela_repaso.util.Conectar;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {
    public List<Estudiante> obtenerEstudiantes(){
        List<Estudiante> lista_estudiantes = new ArrayList<>();
        String sql = "SELECT e.*, GROUP_CONCAT(c.nombre SEPARATOR ', ') AS cursos " +
                "FROM Estudiante e LEFT JOIN Inscripcion i ON e.id = i.estudiante_id " +
                "LEFT JOIN curso c ON i.curso_id = c.id GROUP BY e.id, e.nombre, e.email;";
        Estudiante estudiante;
        try{
            Connection connection = Conectar.conectar();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){ //si hay datos a mostrar
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String email = resultSet.getString("email");
                String curso = resultSet.getString("cursos");
                estudiante = new Estudiante(nombre, email, curso);
                estudiante.setId_est(id);
                lista_estudiantes.add(estudiante);
            }
        } catch (SQLException e) {
            Alerta.mensajeError(e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return lista_estudiantes;
    }

    public List<Curso> obtenerCursosInscritos(int id_estudiante) {
        List<Curso> cursos_inscritos = new ArrayList<>();
        String sql = "SELECT c.* FROM Curso c JOIN Inscripcion i ON c.id = i.curso_id WHERE i.estudiante_id = ?";
        Curso curso;
        try{
            Connection connection = Conectar.conectar();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id_estudiante);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                curso = new Curso(nombre, descripcion);
                curso.setId_curso(id);
                cursos_inscritos.add(curso);
            }

        } catch (SQLException e) {
            Alerta.mensajeError(e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return cursos_inscritos;
    }

    public void insertar(Estudiante estudiante) {
        String sql = "INSERT INTO Estudiante (nombre, email) VALUES (?,?)";
        try{
            Connection connection = Conectar.conectar();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, estudiante.getNombre_est());
            statement.setString(2, estudiante.getEmail());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int idGenerado = resultSet.getInt(1);
                estudiante.setId_est(idGenerado);
            }
        } catch (SQLException e) {
            Alerta.mensajeError(e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
package org.example.escuela_repaso.DAO;

import org.example.escuela_repaso.classes.Curso;
import org.example.escuela_repaso.util.Alerta;
import org.example.escuela_repaso.util.Conectar;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {
    public List<Curso> obtenerCursos(){
        List<Curso> lista_cursos = new ArrayList<>();
        String sql = "SELECT * FROM Curso";
        Curso curso;
        try{
            Connection connection = Conectar.conectar();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){ //si hay datos a mostrar
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String descripcion = resultSet.getString("descripcion");
                curso = new Curso(nombre, descripcion);
                curso.setId_curso(id);
                lista_cursos.add(curso);
            }
        } catch (SQLException e) {
            Alerta.mensajeError(e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return lista_cursos;
    }

    public void insertar(Curso curso) {
        String sql = "INSERT INTO Curso (nombre, descripcion) VALUES (?,?)";
        try{
            Connection connection = Conectar.conectar();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, curso.getNombre_curso());
            statement.setString(2, curso.getDescripcion());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int idGenerado = resultSet.getInt(1);
                curso.setId_curso(idGenerado);
            }
        } catch (SQLException e) {
            Alerta.mensajeError(e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

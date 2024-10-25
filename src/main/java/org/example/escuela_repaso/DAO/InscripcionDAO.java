package org.example.escuela_repaso.DAO;

import org.example.escuela_repaso.classes.Inscripcion;
import org.example.escuela_repaso.util.Alerta;
import org.example.escuela_repaso.util.Conectar;

import java.io.IOException;
import java.sql.*;

public class InscripcionDAO {
    public void insertar(Inscripcion inscripcion) {
        String sql = "INSERT INTO Inscripcion (estudiante_id, curso_id) VALUES (?,?)";
        try{
            Connection connection = Conectar.conectar();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, inscripcion.getId_estudiante());
            statement.setInt(2, inscripcion.getId_curso());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int idGenerado = resultSet.getInt(1);
                inscripcion.setId(idGenerado);
            }
        } catch (SQLException e) {
            Alerta.mensajeError(e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

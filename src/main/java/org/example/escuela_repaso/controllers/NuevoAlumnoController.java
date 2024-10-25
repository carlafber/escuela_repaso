package org.example.escuela_repaso.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.escuela_repaso.DAO.EstudianteDAO;
import org.example.escuela_repaso.EscuelaApplication;
import org.example.escuela_repaso.classes.Estudiante;
import org.example.escuela_repaso.util.Alerta;

import java.io.IOException;

public class NuevoAlumnoController {

    @FXML
    private Button bt_volver;

    @FXML
    private Button bt_crear;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_nombre;

    Estudiante estudiante;

    private EstudianteDAO estudianteDAO = new EstudianteDAO();

    @FXML
    void OnVolverClick(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(EscuelaApplication.class.getResource("inicio.fxml"));
            Parent root = fxmlLoader.load();
            InicioController controller = fxmlLoader.getController();

            Scene scene = new Scene(root);
            Stage stage = (Stage) bt_volver.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            Alerta.mensajeError(e.getMessage());
        }
    }

    @FXML
    void OnCrearClick(ActionEvent event) {
        if(txt_nombre.getText().isEmpty() || txt_email.getText().isEmpty()){
            Alerta.mensajeError("Complete todos los campos.");
        } else {
            String nombre = txt_nombre.getText();
            String email = txt_email.getText();
            estudiante = new Estudiante(nombre, email);
            estudianteDAO.insertar(estudiante);

            Alerta.mensajeInfo("ÉXITO", "Alumno creado correctamente.");

            txt_nombre.clear();
            txt_email.clear();
        }
    }
}
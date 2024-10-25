package org.example.escuela_repaso.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.escuela_repaso.*;
import org.example.escuela_repaso.DAO.EstudianteDAO;
import org.example.escuela_repaso.classes.Curso;
import org.example.escuela_repaso.classes.Estudiante;
import org.example.escuela_repaso.util.Alerta;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InicioController implements Initializable {

    @FXML
    private Button bt_inscribir;

    @FXML
    private Button bt_nuevoalumno;

    @FXML
    private Button bt_nuevocurso;

    @FXML
    private Button bt_salir;

    @FXML
    private TableColumn<Curso, String> tc_curso;

    @FXML
    private TableColumn<Estudiante, String> tc_email;

    @FXML
    private TableColumn<Estudiante, String> tc_nombre;

    @FXML
    private TableView<Estudiante> tv_alumnos;

    private EstudianteDAO estudianteDAO  = new EstudianteDAO();

    List<Estudiante> estudiantes;


    @FXML
    void OnInscribirClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(EscuelaApplication.class.getResource("inscripcion.fxml"));
            Parent root = fxmlLoader.load();
            InscripcionController controller = fxmlLoader.getController();

            Scene scene = new Scene(root);
            Stage stage = (Stage) bt_inscribir.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            Alerta.mensajeError(e.getMessage());
        }
    }

    @FXML
    void OnNuevoAlumnoClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(EscuelaApplication.class.getResource("nuevo-alumno.fxml"));
            Parent root = fxmlLoader.load();
            NuevoAlumnoController controller = fxmlLoader.getController();

            Scene scene = new Scene(root);
            Stage stage = (Stage) bt_nuevoalumno.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            Alerta.mensajeError(e.getMessage());
        }
    }

    @FXML
    void OnNuevoCursoClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(EscuelaApplication.class.getResource("nuevo-curso.fxml"));
            Parent root = fxmlLoader.load();
            NuevoCursoController controller = fxmlLoader.getController();

            Scene scene = new Scene(root);
            Stage stage = (Stage) bt_nuevocurso.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            Alerta.mensajeError(e.getMessage());
        }
    }

    @FXML
    void OnSalirClick(ActionEvent event) {
        Stage stage = (Stage) bt_salir.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tc_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre_est"));
        tc_curso.setCellValueFactory(new PropertyValueFactory<>("cursos"));
        tc_email.setCellValueFactory(new PropertyValueFactory<>("email"));

        cargarEstudiantesCurso();
    }

    public void cargarEstudiantesCurso(){
        estudiantes = estudianteDAO.obtenerEstudiantes();
        tv_alumnos.getItems().setAll(estudiantes);
    }
}

package org.example.escuela_repaso.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.escuela_repaso.DAO.CursoDAO;
import org.example.escuela_repaso.DAO.EstudianteDAO;
import org.example.escuela_repaso.DAO.InscripcionDAO;
import org.example.escuela_repaso.EscuelaApplication;
import org.example.escuela_repaso.classes.Curso;
import org.example.escuela_repaso.classes.Estudiante;
import org.example.escuela_repaso.classes.Inscripcion;
import org.example.escuela_repaso.util.Alerta;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InscripcionController implements Initializable {

    @FXML
    private Button bt_volver;

    @FXML
    private Button bt_inscribir;

    @FXML
    private ComboBox<Curso> cb_cursos;

    @FXML
    private ListView<Estudiante> lv_alumnos;

    private CursoDAO cursoDAO = new CursoDAO();

    private EstudianteDAO estudianteDAO = new EstudianteDAO();

    private InscripcionDAO inscripcionDAO = new InscripcionDAO();

    List<Estudiante> estudiantes;

    List<Curso> cursos;

    Estudiante estudiante_seleccionado;

    Inscripcion inscripcion;

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
    void OnInscribirClick(ActionEvent event) {
        if(estudiante_seleccionado == null || cb_cursos.getItems().isEmpty()){
            Alerta.mensajeError("Selecciona un alumno.");
        } else {
            inscripcion = new Inscripcion(estudiante_seleccionado, cb_cursos.getSelectionModel().getSelectedItem());
            inscripcionDAO.insertar(inscripcion);

            Alerta.mensajeInfo("ÉXITO", "Inscripción realizada correctamente.");
            cargarCursos();
        }
    }

    @FXML
    void onAlumnoClick(MouseEvent event) {
        estudiante_seleccionado = lv_alumnos.getSelectionModel().getSelectedItem();
        if(estudiante_seleccionado != null){
            cargarCursos();
        }
    }

    @FXML
    void onCursosClick(MouseEvent event) {
        if(cb_cursos.getItems().isEmpty()){
            if(estudiante_seleccionado != null){
                Alerta.mensajeError("Este alumno está inscrito en los cursos disponibles.");
            } else {
                Alerta.mensajeError("Seleccione un alumno.");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lv_alumnos.getItems().clear();
        cargarEstudiantes();
    }

    public void cargarEstudiantes(){
        estudiantes = estudianteDAO.obtenerEstudiantes();
        lv_alumnos.setItems(FXCollections.observableList(estudiantes));
    }

    public void cargarCursos(){
        List<Curso> cursos_inscritos = estudianteDAO.obtenerCursosInscritos(estudiante_seleccionado.getId_est());
        cursos = cursoDAO.obtenerCursos();
        List<Curso> cursos_disponibles = new ArrayList<>(cursos);
        cursos_disponibles.removeAll(cursos_inscritos);

        cb_cursos.setItems(FXCollections.observableArrayList(cursos_disponibles));
    }
}
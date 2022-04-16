package org.edyslex.controllers.students;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.edyslex.Main;
import org.edyslex.models.Student;
import org.hibernate.Transaction;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class StudentsController extends BaseController implements Initializable {

    @FXML
    private TableView<Student> studentTableView;
    @FXML
    private TableColumn<Student, String> firstNameColumn;
    @FXML
    private TableColumn<Student, String> lastNameColumn;
    @FXML
    private TableColumn<Student, Integer> ageColumn;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        studentTableView.setPlaceholder(new Label(""));

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));

        ageColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(
                        Period.between(cellData.getValue().getDateOfBirth(), LocalDate.now()).getYears())
                        .asObject());

        studentTableView.setItems(FXCollections.observableArrayList(
                Main.session.createQuery(
                        "from Student ORDER BY lastName, firstName, dateOfBirth DESC", Student.class).list()));
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("scenes/menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void switchToAddStudent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("scenes/students/addStudent.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void switchToViewStudent(ActionEvent event) throws IOException {
        Student student = studentTableView.getSelectionModel().getSelectedItem();
        if(student != null){
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
                    .getResource("scenes/students/viewStudent.fxml"));
            Parent root = loader.load();

            ViewStudentController controller = loader.getController();
            controller.initializeFields(student);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    public void switchToModifyStudent(ActionEvent event) throws IOException {
        Student student = studentTableView.getSelectionModel().getSelectedItem();
        if(student != null){
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
                    .getResource("scenes/students/modifyStudent.fxml"));
            Parent root = loader.load();

            ModifyStudentController controller = loader.getController();
            controller.initializeFields(student);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    public void deleteStudent(){
        Student student = studentTableView.getSelectionModel().getSelectedItem();
        if(student != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add("css/style.css");
            ((Button) dialogPane.lookupButton(ButtonType.CANCEL)).setText("Anulare");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setTitle("Ștergere elev");
            alert.setContentText("Doriți să ștergeți elevul?");
            alert.setHeaderText("Confirmare");
            if(alert.showAndWait().get() == ButtonType.OK) {
                Transaction transaction = null;
                try {
                    transaction = Main.session.getTransaction();
                    transaction.begin();
                    Main.session.delete(student);
                    transaction.commit();
                } catch (Exception e){
                    if (transaction != null) {
                        transaction.rollback();
                    }
                }
                studentTableView.setItems(FXCollections.observableArrayList(
                        Main.session.createQuery(
                                "from Student ORDER BY lastName, firstName, dateOfBirth DESC", Student.class).list()));
            }
        }
    }

    public void switchToReports(ActionEvent event) throws IOException {
        Student student = studentTableView.getSelectionModel().getSelectedItem();
        if(student != null){
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
                    .getResource("scenes/students/reports.fxml"));
            Parent root = loader.load();

            ReportsController controller = loader.getController();
            controller.initializeFields(student);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }



}

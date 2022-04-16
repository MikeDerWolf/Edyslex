package org.edyslex.controllers.students;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.edyslex.Main;
import org.edyslex.models.Report;
import org.edyslex.models.Student;
import org.hibernate.Transaction;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ReportsController extends BaseController {

    @FXML
    private TableView<Report> reportTableView;
    @FXML
    private TableColumn<Report, String> reportNoColumn;
    @FXML
    private TableColumn<Report, String> reportDateColumn;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Student student;

    public void initializeFields(Student student){
        this.student = student;
        reportTableView.setPlaceholder(new Label(""));

        reportNoColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty("Raport #" +
                        cellData.getValue().getId().toString()));

        reportDateColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getDateOfReport().format(dateFormatter)));

        reportTableView.setItems(FXCollections.observableArrayList(
                Main.session.createQuery(
                        "from Report r where r.student.id = :studentId ORDER BY dateOfReport DESC, id",
                        Report.class).setParameter("studentId", this.student.getId()).list()));
    }

    public void switchToAddReport(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
                .getResource("scenes/students/addReport.fxml"));
        Parent root = loader.load();

        AddReportController controller = loader.getController();
        controller.initializeFields(this.student);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void switchToViewReport(ActionEvent event) throws IOException {
        Report report = reportTableView.getSelectionModel().getSelectedItem();
        if(report != null){
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
                    .getResource("scenes/students/viewReport.fxml"));
            Parent root = loader.load();

            ViewReportController controller = loader.getController();
            controller.initializeFields(this.student, report);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    public void switchToModifyReport(ActionEvent event) throws IOException {
        Report report = reportTableView.getSelectionModel().getSelectedItem();
        if(report != null){
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
                    .getResource("scenes/students/modifyReport.fxml"));
            Parent root = loader.load();

            ModifyReportController controller = loader.getController();
            controller.initializeFields(this.student, report);

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    public void deleteReport(){
        Report report = reportTableView.getSelectionModel().getSelectedItem();
        if(report != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add("css/style.css");
            ((Button) dialogPane.lookupButton(ButtonType.CANCEL)).setText("Anulare");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setTitle("Ștergere raport");
            alert.setContentText("Doriți să ștergeți raportul?");
            alert.setHeaderText("Confirmare");
            if(alert.showAndWait().get() == ButtonType.OK) {
                Transaction transaction = null;
                try {
                    transaction = Main.session.getTransaction();
                    transaction.begin();
                    Main.session.delete(report);
                    transaction.commit();
                } catch (Exception e){
                    if (transaction != null) {
                        transaction.rollback();
                    }
                }
                reportTableView.setItems(FXCollections.observableArrayList(
                        Main.session.createQuery(
                                "from Report r where r.student.id = :studentId ORDER BY dateOfReport DESC, id",
                                Report.class).setParameter("studentId", this.student.getId()).list()));
            }
        }
    }

    public void switchToStudents(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("scenes/students/students.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


}

package org.edyslex.controllers.students;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.edyslex.Main;
import org.edyslex.controllers.BaseController;
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

    @FXML
    private TextField searchBar;

    private ObservableList<Report> reportObservableList;

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

        reportObservableList = FXCollections.observableArrayList(
                Main.getSession().createQuery(
                        "from Report r where r.student.id = :studentId ORDER BY dateOfReport DESC, id",
                        Report.class).setParameter("studentId", this.student.getId()).list());
        reportTableView.setItems(reportObservableList);
        applySearch(reportObservableList);
    }

    public void applySearch(ObservableList<Report> reportObservableList){
        FilteredList<Report> filteredReports = new FilteredList<>(reportObservableList, p -> true);

        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredReports.setPredicate(Report -> {

                if (newValue == null || newValue.isEmpty() || newValue.isBlank()){
                    return true;
                }

                String keyword = newValue.toLowerCase();

                if (("Raport #" + Report.getId().toString()).toLowerCase().contains(keyword)){
                    return true;
                } else if (Report.getDateOfReport().format(dateFormatter).toLowerCase().contains(keyword)){
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<Report> sortedReports = new SortedList<>(filteredReports);
        sortedReports.comparatorProperty().bind(reportTableView.comparatorProperty());
        reportTableView.setItems(sortedReports);
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
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(Main.getPrimaryStage());
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
                    transaction = Main.getSession().getTransaction();
                    transaction.begin();
                    Main.getSession().delete(report);
                    transaction.commit();
                } catch (Exception e){
                    if (transaction != null) {
                        transaction.rollback();
                    }
                }
                reportObservableList = FXCollections.observableArrayList(
                        Main.getSession().createQuery(
                                "from Report r where r.student.id = :studentId ORDER BY dateOfReport DESC, id",
                                Report.class).setParameter("studentId", this.student.getId()).list());
                reportTableView.setItems(reportObservableList);
                applySearch(reportObservableList);
            }
        }
    }

    public void switchToStudents(ActionEvent event) throws IOException {
        switchScene(event, "scenes/students/students.fxml");
    }

}

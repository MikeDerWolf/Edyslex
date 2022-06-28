package org.edyslex.controllers.students;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import org.edyslex.Main;
import org.edyslex.controllers.BaseController;
import org.edyslex.models.Report;
import org.edyslex.models.Student;
import org.edyslex.utils.CustomAlert;
import org.hibernate.Transaction;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.util.ResourceBundle;

public class ModifyReportController extends BaseController implements Initializable {

    @FXML
    private DatePicker dateOfReportDatePicker;
    @FXML
    private TextArea prioritiesTextArea;
    @FXML
    private TextArea difficultiesTextArea;
    @FXML
    private TextArea progressTextArea;
    @FXML
    private TextArea observationsTextArea;

    private Student student;

    private Report report;

    public void initializeFields(Student student, Report report){
        this.student = student;
        this.report = report;

        dateOfReportDatePicker.setValue(report.getDateOfReport());
        prioritiesTextArea.setText(report.getPriorities());
        difficultiesTextArea.setText(report.getDifficulties());
        progressTextArea.setText(report.getProgress());

        if(report.getObservations() != null){
            observationsTextArea.setText(report.getObservations());
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        dateOfReportDatePicker.setConverter(new StringConverter<LocalDate>() {
            final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

    }

    public void modifyReport(ActionEvent event){
        String priorities, difficulties, progress, observations, dateString;
        LocalDate dateOfReport;
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("dd/MM/yyyy")
                .parseDefaulting(ChronoField.ERA, 1)
                .toFormatter()
                .withResolverStyle(ResolverStyle.STRICT)
                .withChronology(IsoChronology.INSTANCE);

        //check report date
        dateString = dateOfReportDatePicker.getEditor().getText();
        if(dateString.isEmpty()){
            CustomAlert.createAlert(Alert.AlertType.ERROR, "DATE INVALIDE",
                    "Data raportului lipsește!");
            return;
        }
        try {
            dateOfReport = LocalDate.parse(dateString, formatter);
            if(dateOfReport.isBefore(this.student.getDateOfEntry())){
                throw new BeforeDateException("Raportul nu poate fi creat \nînainte de intrarea în program!");
            }
        } catch(BeforeDateException e){
            CustomAlert.createAlert(Alert.AlertType.ERROR, "DATE INVALIDE",
                    e.getMessage());
            return;
        } catch(Exception e){
            CustomAlert.createAlert(Alert.AlertType.ERROR, "DATE INVALIDE",
                    "Data raportului este invalidă!");
            return;
        }

        //check priorities criteria
        priorities = prioritiesTextArea.getText();
        if(priorities.isEmpty()){
            CustomAlert.createAlert(Alert.AlertType.ERROR, "DATE INVALIDE",
                    "Secțiunea 'Priorități' nu este completată!");
            return;
        }
        if(priorities.length() > 5000){
            CustomAlert.createAlert(Alert.AlertType.ERROR, "DATE INVALIDE",
                    "Lungimea maximă a secțiunii 'Priorități' \neste 5000 de caractere!");
            return;
        }

        //check difficulties criteria
        difficulties = difficultiesTextArea.getText();
        if(difficulties.isEmpty()){
            CustomAlert.createAlert(Alert.AlertType.ERROR, "DATE INVALIDE",
                    "Secțiunea 'Dificultăți întâmpinate' \nnu este completată!");
            return;
        }
        if(difficulties.length() > 5000){
            CustomAlert.createAlert(Alert.AlertType.ERROR, "DATE INVALIDE",
                    "Lungimea maximă a secțiunii \n'Dificultăți întâmpinate' este 5000 de caractere!");
            return;
        }

        //check progress criteria
        progress = progressTextArea.getText();
        if(progress.isEmpty()){
            CustomAlert.createAlert(Alert.AlertType.ERROR, "DATE INVALIDE",
                    "Secțiunea 'Progrese' nu este completată!");
            return;
        }
        if(progress.length() > 5000){
            CustomAlert.createAlert(Alert.AlertType.ERROR, "DATE INVALIDE",
                    "Lungimea maximă a secțiunii 'Progrese' \neste 5000 de caractere!");
            return;
        }

        //check observations criteria
        observations = observationsTextArea.getText();
        if(observations.isEmpty()){
            observations = null;
        } else if (observations.length() > 5000){
            CustomAlert.createAlert(Alert.AlertType.ERROR, "DATE INVALIDE",
                    "Lungimea maximă a secțiunii 'Observații' \neste 5000 de caractere!");
            return;
        }

        report.setDateOfReport(dateOfReport);
        report.setPriorities(priorities);
        report.setDifficulties(difficulties);
        report.setProgress(progress);
        report.setObservations(observations);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add("css/style.css");
        ((Button) dialogPane.lookupButton(ButtonType.CANCEL)).setText("Anulare");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(Main.getPrimaryStage());
        alert.setTitle("Modificare raport");
        alert.setContentText("Doriți să modificați datele raportului?");
        alert.setHeaderText("Confirmare");
        if(alert.showAndWait().get() == ButtonType.OK) {
            Transaction transaction = null;
            try {
                transaction = Main.getSession().getTransaction();
                transaction.begin();
                Main.getSession().update(report);
                transaction.commit();
                switchToReports(event);
            } catch (Exception e){
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }

    }

    public void switchToReports(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
                .getResource("scenes/students/reports.fxml"));
        Parent root = loader.load();

        ReportsController controller = loader.getController();
        controller.initializeFields(this.student);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}

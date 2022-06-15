package org.edyslex.controllers.students;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import org.edyslex.Main;
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
import java.util.List;
import java.util.ResourceBundle;

class BeforeDateException extends Exception {
    public BeforeDateException(String errorMessage) {
        super(errorMessage);
    }
}

public class AddReportController extends BaseController implements Initializable {

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

    public void initializeFields(Student student){
        this.student = student;
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

    public void addReport(ActionEvent event){
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

        Report report = new Report(dateOfReport, priorities, difficulties, progress, observations);

        List<Report> reports = this.student.getReports();
        reports.add(report);
        this.student.setReports(reports);

        report.setStudent(this.student);

        Transaction transaction = null;
        try{
            transaction = Main.getSession().getTransaction();
            transaction.begin();
            Main.getSession().save(report);
            transaction.commit();
            switchToReports(event);
            CustomAlert.createAlert(Alert.AlertType.INFORMATION,
                    "Adăugare efectuată cu succes!", "Raportul a fost salvat!");
        } catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
                System.out.println(e);
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

package org.edyslex.controllers.students;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.edyslex.models.Report;
import org.edyslex.models.Student;
import org.edyslex.utils.CustomAlert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ViewReportController extends BaseController {

    @FXML
    private Label titleLabel;
    @FXML
    private TextArea reportDataTextArea;

    private Student student;

    private Report report;

    private StringBuilder labelData = new StringBuilder();

    private StringBuilder reportData = new StringBuilder();

    private final DateTimeFormatter dateFormatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final DateTimeFormatter dateFormatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public void initializeFields(Student student, Report report){
        this.student = student;
        this.report = report;

        labelData.append("RAPORT #").append(report.getId().toString())
                .append(" - ").append(report.getDateOfReport().format(dateFormatter1));

        titleLabel.setText(labelData.toString());

        reportData.append("NUME ȘI PRENUME: ")
                .append(student.getLastName()).append(" ")
                .append(student.getFirstName()).append("\n\n");

        reportData.append("DATA NAȘTERII: ")
                .append(student.getDateOfBirth().format(dateFormatter1)).append("\n\n");

        reportData.append("CLASA: ")
                .append(student.getSchoolClass()).append("\n\n");


        reportData.append("PRIORITĂȚI: ")
                .append("\n\n");
        String priorities = report.getPriorities();
        String[] prioritiesLines = priorities.split("\n");
        for(String line: prioritiesLines){
            if(!line.startsWith("\t")){
                reportData.append("\t");
            }
            reportData.append(line).append("\n");
        }
        reportData.append("\n");

        reportData.append("DIFICULTĂȚI ÎNTÂMPINATE: ")
                .append("\n\n");
        String difficulties = report.getDifficulties();
        String[] difficultiesLines = difficulties.split("\n");
        for(String line: difficultiesLines){
            if(!line.startsWith("\t")){
                reportData.append("\t");
            }
            reportData.append(line).append("\n");
        }
        reportData.append("\n");

        reportData.append("PROGRESE: ")
                .append("\n\n");
        String progress = report.getProgress();
        String[] progressLines = progress.split("\n");
        for(String line: progressLines){
            if(!line.startsWith("\t")){
                reportData.append("\t");
            }
            reportData.append(line).append("\n");
        }

        String observations = report.getObservations();
        if(observations != null){
            reportData.append("\n");

            reportData.append("OBSERVAȚII: ")
                    .append("\n\n");
            String[] observationsLines = observations.split("\n");
            for(String line: observationsLines){
                if(!line.startsWith("\t")){
                    reportData.append("\t");
                }
                reportData.append(line).append("\n");
            }
        }

        reportDataTextArea.setText(reportData.toString());

    }

    public void saveReport(ActionEvent event) {
        String filename = new StringBuilder().append("raport_").append(report.getId().toString()).append("_")
                .append(student.getLastName()).append("_").append(student.getFirstName()).append("_")
                .append(report.getDateOfReport().format(dateFormatter2)).append(".pdf").toString();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName(filename);
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF files (*.pdf)", ".pdf"));
        File selectedFile = fileChooser.showSaveDialog(stage);
        if(selectedFile != null) {
            try {
                final String FONT = "fonts/cmunrm.ttf";
                Font font = FontFactory.getFont(FONT,BaseFont.IDENTITY_H, true, 12);
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(selectedFile));
                document.open();

                Paragraph title = new Paragraph();
                title.setFont(font);
                title.add(labelData.toString() + "\nPRIVIND MONITORIZAREA" +
                        "\nPROGRAMULUI DE INTERVENȚIE LOGOPEDICĂ\n\n");
                title.setAlignment(Element.ALIGN_CENTER);

                Paragraph content = new Paragraph();
                content.setFont(font);
                content.add(reportData.toString().replace("\t","        "));

                document.add(title);
                document.add(content);

                document.close();
            }
            catch (Exception e) {
                CustomAlert.createAlert(Alert.AlertType.ERROR, "EXPORT EȘUAT",
                        "Raportul nu a putut fi exportat!");
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

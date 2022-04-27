package org.edyslex.controllers.students;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.edyslex.models.Student;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ViewStudentController extends BaseController {

    @FXML
    private TextArea studentDataTextArea;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void initializeFields(Student student){
        StringBuilder studentData = new StringBuilder();

        studentData.append("NUME ȘI PRENUME: ")
                .append(student.getLastName()).append(" ")
                .append(student.getFirstName()).append("\n\n");

        studentData.append("DATA NAȘTERII: ")
                .append(student.getDateOfBirth().format(dateFormatter)).append("\n\n");

        studentData.append("SEX: ");
        if(student.getGender().equals("M")){
            studentData.append("Masculin").append("\n\n");
        } else{
            studentData.append("Feminin").append("\n\n");
        }

        studentData.append("CLASA: ")
                .append(student.getSchoolClass()).append("\n\n");

        studentData.append("DATA INTRĂRII ÎN PROGRAM: ")
                .append(student.getDateOfEntry().format(dateFormatter)).append("\n\n");

        studentData.append("DATA IEȘIRII DIN PROGRAM: ");
        if(student.getDateOfExit() != null){
            studentData.append(student.getDateOfExit().format(dateFormatter)).append("\n\n");
        } else{
            studentData.append("-").append("\n\n");
        }

        studentData.append("DIAGNOSTIC MEDICAL: ")
                .append("\n\n");
        String medicalDiagnosis = student.getMedicalDiagnosis();
        String[] medicalDiagnosisLines = medicalDiagnosis.split("\n");
        for(String line: medicalDiagnosisLines){
            if(!line.startsWith("\t")){
                studentData.append("\t");
            }
            studentData.append(line).append("\n");
        }
        studentData.append("\n");

        studentData.append("DIAGNOSTIC LOGOPEDIC: ")
                .append("\n\n");
        String speechDiagnosis = student.getSpeechDiagnosis();
        String[] speechDiagnosisLines = speechDiagnosis.split("\n");
        for(String line: speechDiagnosisLines){
            if(!line.startsWith("\t")){
                studentData.append("\t");
            }
            studentData.append(line).append("\n");
        }

        studentDataTextArea.setText(studentData.toString());

    }

    public void switchToStudents(ActionEvent event) throws IOException {
        switchScene(event, "scenes/students/students.fxml");
    }

}

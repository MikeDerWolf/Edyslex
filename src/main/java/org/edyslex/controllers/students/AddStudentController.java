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
import org.edyslex.models.Student;
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

public class AddStudentController extends BaseController implements Initializable {

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private DatePicker dateOfEntryDatePicker;
    @FXML
    private DatePicker dateOfExitDatePicker;
    @FXML
    private ComboBox<String> classComboBox;
    @FXML
    private ComboBox<String> genderComboBox;
    @FXML
    private TextArea medicalDiagnosisTextArea;
    @FXML
    private TextArea speechDiagnosisTextArea;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        birthDatePicker.setConverter(new StringConverter<LocalDate>() {
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

        dateOfEntryDatePicker.setConverter(new StringConverter<LocalDate>() {
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

        dateOfExitDatePicker.setConverter(new StringConverter<LocalDate>() {
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

        classComboBox.getItems().add("Fără clasă");
        for(int i=0; i <= 12; i++){
            classComboBox.getItems().add(Integer.toString(i));
        }
        classComboBox.getSelectionModel().selectFirst();

        genderComboBox.getItems().addAll("M", "F");

    }

    public void createAlert(Alert.AlertType alertType, String headerText, String content){
        Alert alert = new Alert(alertType);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add("css/style.css");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setHeaderText(headerText);
        alert.setContentText(content);
        alert.show();
    }

    public void addStudent(ActionEvent event){
        String firstName, lastName, medicalDiagnosis, speechDiagnosis, gender, dateString;
        Integer schoolClass;
        LocalDate birthDate, dateOfEntry, dateOfExit;
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("dd/MM/yyyy")
                .parseDefaulting(ChronoField.ERA, 1)
                .toFormatter()
                .withResolverStyle(ResolverStyle.STRICT)
                .withChronology(IsoChronology.INSTANCE);


        //check last name criteria
        lastName = lastNameTextField.getText();
        if(lastName.isEmpty()){
            createAlert(Alert.AlertType.ERROR, "DATE INVALIDE",
                    "Numele elevului lipsește!");
            return;
        }
        if(lastName.length() > 45){
            createAlert(Alert.AlertType.ERROR, "DATE INVALIDE",
                    "Lungimea maximă a numelui \neste 45 de caractere!");
            return;
        }

        //check first name criteria
        firstName = firstNameTextField.getText();
        if(firstName.isEmpty()){
            createAlert(Alert.AlertType.ERROR, "DATE INVALIDE",
                    "Prenumele elevului lipsește!");
            return;
        }
        if(firstName.length() > 45){
            createAlert(Alert.AlertType.ERROR, "DATE INVALIDE",
                    "Lungimea maximă a prenumelui \neste 45 de caractere!");
            return;
        }

        //check birth date
        dateString = birthDatePicker.getEditor().getText();
        if(dateString.isEmpty()){
            createAlert(Alert.AlertType.ERROR, "DATE INVALIDE",
                    "Data nașterii lipsește!");
            return;
        }
        try {
            birthDate = LocalDate.parse(dateString, formatter);
            if(birthDate.isAfter(LocalDate.now())){
                throw new Exception();
            }
        } catch(Exception e){
            createAlert(Alert.AlertType.ERROR, "DATE INVALIDE",
                    "Data nașterii este invalidă!");
            return;
        }

        //convert class to suitable type
        if(classComboBox.getValue().equals("Fără clasă")){
            schoolClass = null;
        } else{
            schoolClass = Integer.parseInt(classComboBox.getValue());
        }

        //check gender
        gender = genderComboBox.getValue();
        if(gender == null){
            createAlert(Alert.AlertType.ERROR, "DATE INVALIDE",
                    "Sexul elevului lipsește!");
            return;
        }

        //check program entry date
        dateString = dateOfEntryDatePicker.getEditor().getText();
        if(dateString.isEmpty()){
            createAlert(Alert.AlertType.ERROR, "DATE INVALIDE",
                    "Data intrării în program lipsește!");
            return;
        }
        try {
            dateOfEntry = LocalDate.parse(dateString, formatter);
            if(dateOfEntry.isBefore(birthDate)){
                throw new Exception();
            }
        } catch(Exception e){
            createAlert(Alert.AlertType.ERROR, "DATE INVALIDE",
                    "Data intrării în program este invalidă!");
            return;
        }

        //check program exit date
        dateString = dateOfExitDatePicker.getEditor().getText();
        if(dateString.isEmpty()){
            dateOfExit = null;
        }else{
            try {
                dateOfExit = LocalDate.parse(dateString, formatter);
                if(dateOfExit.isBefore(dateOfEntry)){
                    throw new Exception();
                }
            } catch(Exception e){
                createAlert(Alert.AlertType.ERROR, "DATE INVALIDE",
                        "Data ieșirii din program este invalidă!");
                return;
            }
        }

        //check medical diagnosis
        medicalDiagnosis = medicalDiagnosisTextArea.getText();
        if(medicalDiagnosis.isEmpty()){
            createAlert(Alert.AlertType.ERROR, "DATE INVALIDE",
                    "Diagnosticul medical lipsește!");
            return;
        }
        if(medicalDiagnosis.length() > 5000){
            createAlert(Alert.AlertType.ERROR, "DATE INVALIDE",
                    "Lungimea maximă a diagnosticului medical \neste 5000 de caractere!");
            return;
        }

        //check speech diagnosis
        speechDiagnosis = speechDiagnosisTextArea.getText();
        if(speechDiagnosis.isEmpty()){
            createAlert(Alert.AlertType.ERROR, "DATE INVALIDE",
                    "Diagnosticul logopedic lipsește!");
            return;
        }
        if(speechDiagnosis.length() > 5000){
            createAlert(Alert.AlertType.ERROR, "DATE INVALIDE",
                    "Lungimea maximă a diagnosticului logopedic \neste 5000 de caractere!");
            return;
        }

        Student student = new Student(firstName, lastName, birthDate, schoolClass, gender,
                medicalDiagnosis, speechDiagnosis, dateOfEntry,
                dateOfExit);
        Transaction transaction = null;
        try{
            transaction = Main.session.getTransaction();
            transaction.begin();
            Main.session.save(student);
            transaction.commit();
            switchToStudents(event);
            createAlert(Alert.AlertType.INFORMATION,
                    "Adăugare efectuată cu succes!", "Elevul a fost salvat!");
        } catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
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

package org.edyslex.controllers.students;


import javafx.event.ActionEvent;
import org.edyslex.controllers.BaseController;

import java.io.IOException;

public class MenuController extends BaseController {

    public void switchToStudents(ActionEvent event) throws IOException {
        switchScene(event, "scenes/students/students.fxml");
    }

    public void switchToExercises(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercises.fxml");
    }

}

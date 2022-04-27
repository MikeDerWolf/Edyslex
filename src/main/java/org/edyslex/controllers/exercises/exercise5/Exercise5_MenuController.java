package org.edyslex.controllers.exercises.exercise5;

import javafx.event.ActionEvent;
import org.edyslex.controllers.students.BaseController;

import java.io.IOException;

public class Exercise5_MenuController extends BaseController {

    public void switchToExercises(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercises.fxml");
    }

    public void switchToExercise5_1(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise5/exercise5_1.fxml");
    }

}

package org.edyslex.controllers.exercises.exercise3;

import javafx.event.ActionEvent;
import org.edyslex.controllers.students.BaseController;

import java.io.IOException;

public class Exercise3_MenuController extends BaseController {

    public void switchToExercises(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercises.fxml");
    }

    public void switchToExercise3_1(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise3/exercise3_1.fxml");
    }

}

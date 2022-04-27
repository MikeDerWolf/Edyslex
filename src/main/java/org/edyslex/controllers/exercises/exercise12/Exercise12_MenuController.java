package org.edyslex.controllers.exercises.exercise12;

import javafx.event.ActionEvent;
import org.edyslex.controllers.students.BaseController;

import java.io.IOException;

public class Exercise12_MenuController extends BaseController {

    public void switchToExercises(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercises.fxml");
    }

    public void switchToExercise12_1(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise12/exercise12_1.fxml");
    }
}

package org.edyslex.controllers.exercises.exercise1;

import javafx.event.ActionEvent;
import org.edyslex.controllers.students.BaseController;

import java.io.IOException;

public class Exercise1_MenuController extends BaseController {

    public void switchToExercises(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercises.fxml");
    }

    public void switchToExercise1_1(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise1/exercise1_1.fxml");
    }

}

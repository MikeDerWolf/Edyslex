package org.edyslex.controllers.exercises.exercise9;

import javafx.event.ActionEvent;
import org.edyslex.controllers.students.BaseController;

import java.io.IOException;

public class Exercise9_MenuController extends BaseController {

    public void switchToExercises(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercises.fxml");
    }

    public void switchToExercise9_1(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise9/exercise9_1.fxml");
    }

}

package org.edyslex.controllers.exercises.exercise7;

import javafx.event.ActionEvent;
import org.edyslex.controllers.students.BaseController;

import java.io.IOException;

public class Exercise7_MenuController extends BaseController {

    public void switchToExercises(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercises.fxml");
    }

    public void switchToExercise7_1(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise7/exercise7_1.fxml");
    }

}

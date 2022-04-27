package org.edyslex.controllers.exercises.exercise8;

import javafx.event.ActionEvent;
import org.edyslex.controllers.students.BaseController;

import java.io.IOException;

public class Exercise8_MenuController extends BaseController {

    public void switchToExercises(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercises.fxml");
    }

    public void switchToExercise8_1(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise8/exercise8_1.fxml");
    }

}

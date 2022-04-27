package org.edyslex.controllers.exercises.exercise10;

import javafx.event.ActionEvent;
import org.edyslex.controllers.students.BaseController;

import java.io.IOException;

public class Exercise10_MenuController extends BaseController {

    public void switchToExercises(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercises.fxml");
    }

    public void switchToExercise10_1(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise10/exercise10_1.fxml");
    }

}

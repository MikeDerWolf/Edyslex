package org.edyslex.controllers.exercises.exercise2;

import javafx.event.ActionEvent;
import org.edyslex.controllers.students.BaseController;

import java.io.IOException;

public class Exercise2_MenuController extends BaseController {

    public void switchToExercises(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercises.fxml");
    }

    public void switchToExercise2_1(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise2/exercise2_1.fxml");
    }

}

package org.edyslex.controllers.exercises.exercise3;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.edyslex.controllers.students.BaseController;

import java.io.IOException;

public class Exercise3_MenuController extends BaseController {

    public void switchToExercises(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("scenes/exercises/exercises.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void switchToExercise3_1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
                getClass().getClassLoader().getResource("scenes/exercises/exercise3/exercise3_1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}

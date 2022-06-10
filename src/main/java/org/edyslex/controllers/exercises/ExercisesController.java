package org.edyslex.controllers.exercises;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import org.edyslex.controllers.students.BaseController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ExercisesController extends BaseController implements Initializable {

    @FXML
    private Button menuBtn1, menuBtn2, menuBtn3, menuBtn4, menuBtn5, menuBtn6, menuBtn7,
            menuBtn8, menuBtn9, menuBtn10, menuBtn11, menuBtn12, menuBtn13,
            menuBtn1_1, menuBtn1_2, menuBtn1_3, menuBtn1_4, menuBtn1_5, menuBtn1_6,
            menuBtn2_1, menuBtn2_2, menuBtn2_3, menuBtn2_4, menuBtn2_5,
            menuBtn3_1, menuBtn3_2, menuBtn3_3, menuBtn3_4, menuBtn3_5,
            menuBtn3_6, menuBtn3_7, menuBtn3_8, menuBtn3_9, menuBtn3_10,
            menuBtn4_1,
            menuBtn5_1, menuBtn5_2,
            menuBtn6_1, menuBtn6_2, menuBtn6_3, menuBtn6_4, menuBtn6_5, menuBtn6_6,
            menuBtn7_1, menuBtn7_2, menuBtn7_3, menuBtn7_4,
            menuBtn8_1, menuBtn8_2,
            menuBtn9_1, menuBtn9_2, menuBtn9_3, menuBtn9_4, menuBtn9_5, menuBtn9_6,
            menuBtn10_1, menuBtn10_2, menuBtn10_3, menuBtn10_4, menuBtn10_5,
            menuBtn11_1, menuBtn11_2, menuBtn11_3, menuBtn11_4, menuBtn11_5, menuBtn11_6,
            menuBtn12_1, menuBtn12_2,
            menuBtn13_1, menuBtn13_2;


    @FXML
    private Pane pane0, pane1, pane2, pane3, pane4, pane5, pane6,
            pane7, pane8, pane9, pane10, pane11, pane12, pane13;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        pane0.toFront();
    }

    public void openSubmenu(ActionEvent event) throws IOException {
        Node button = (Node) event.getSource();
        if(button == menuBtn1){
            pane1.toFront();
        } else if (button == menuBtn2) {
            pane2.toFront();
        } else if (button == menuBtn3) {
            pane3.toFront();
        } else if (button == menuBtn4) {
            pane4.toFront();
        } else if (button == menuBtn5) {
            pane5.toFront();
        } else if (button == menuBtn6) {
            pane6.toFront();
        } else if (button == menuBtn7) {
            pane7.toFront();
        } else if (button == menuBtn8) {
            pane8.toFront();
        } else if (button == menuBtn9) {
            pane9.toFront();
        } else if (button == menuBtn10) {
            pane10.toFront();
        } else if (button == menuBtn11) {
            pane11.toFront();
        } else if (button == menuBtn12) {
            pane12.toFront();
        } else if (button == menuBtn13) {
            pane13.toFront();
        }
    }

    public void switchToExercise1(ActionEvent event) throws IOException {
        String scenePath = null;
        Node button = (Node) event.getSource();
        if(button == menuBtn1_1){
            scenePath = "scenes/exercises/exercise1/exercise1_1.fxml";
        }
        if (scenePath != null){
            switchScene(event, scenePath);
        }
    }

    public void switchToExercise2(ActionEvent event) throws IOException {
        String scenePath = null;
        Node button = (Node) event.getSource();
        if(button == menuBtn2_1){
            scenePath = "scenes/exercises/exercise2/exercise2_1.fxml";
        }
        if (scenePath != null){
            switchScene(event, scenePath);
        }
    }

    public void switchToExercise3(ActionEvent event) throws IOException {
        String scenePath = null;
        Node button = (Node) event.getSource();
        if(button == menuBtn3_1){
            scenePath = "scenes/exercises/exercise3/exercise3_1.fxml";
        }
        if (scenePath != null){
            switchScene(event, scenePath);
        }
    }

    public void switchToExercise4(ActionEvent event) throws IOException {
        String scenePath = null;
        Node button = (Node) event.getSource();
        if(button == menuBtn4_1){
            scenePath = "scenes/exercises/exercise4/exercise4_1.fxml";
        }
        if (scenePath != null){
            switchScene(event, scenePath);
        }
    }

    public void switchToExercise5(ActionEvent event) throws IOException {
        String scenePath = null;
        Node button = (Node) event.getSource();
        if(button == menuBtn5_1){
            scenePath = "scenes/exercises/exercise5/exercise5_1.fxml";
        }
        if (scenePath != null){
            switchScene(event, scenePath);
        }
    }

    public void switchToExercise6(ActionEvent event) throws IOException {
        String scenePath = null;
        Node button = (Node) event.getSource();
        if(button == menuBtn6_1){
            scenePath = "scenes/exercises/exercise5/exercise5_1.fxml";
        }
        if (scenePath != null){
            switchScene(event, scenePath);
        }
    }

    public void switchToExercise7(ActionEvent event) throws IOException {
        String scenePath = null;
        Node button = (Node) event.getSource();
        if(button == menuBtn7_1){
            scenePath = "scenes/exercises/exercise7/exercise7_1.fxml";
        }
        if (scenePath != null){
            switchScene(event, scenePath);
        }
    }

    public void switchToExercise8(ActionEvent event) throws IOException {
        String scenePath = null;
        Node button = (Node) event.getSource();
        if(button == menuBtn8_1){
            scenePath = "scenes/exercises/exercise8/exercise8_1.fxml";
        }
        if (scenePath != null){
            switchScene(event, scenePath);
        }
    }

    public void switchToExercise9(ActionEvent event) throws IOException {
        String scenePath = null;
        Node button = (Node) event.getSource();
        if(button == menuBtn9_1){
            scenePath = "scenes/exercises/exercise9/exercise9_1.fxml";
        }
        if (scenePath != null){
            switchScene(event, scenePath);
        }
    }

    public void switchToExercise10(ActionEvent event) throws IOException {
        String scenePath = null;
        Node button = (Node) event.getSource();
        if(button == menuBtn10_1){
            scenePath = "scenes/exercises/exercise10/exercise10_1.fxml";
        }
        if (scenePath != null){
            switchScene(event, scenePath);
        }
    }

    public void switchToExercise11(ActionEvent event) throws IOException {
        String scenePath = null;
        Node button = (Node) event.getSource();
        if(button == menuBtn11_1){
            scenePath = "scenes/exercises/exercise11/exercise11_1.fxml";
        }
        if (scenePath != null){
            switchScene(event, scenePath);
        }
    }

    public void switchToExercise12(ActionEvent event) throws IOException {
        String scenePath = null;
        Node button = (Node) event.getSource();
        if(button == menuBtn12_1){
            scenePath = "scenes/exercises/exercise12/exercise12_1.fxml";
        }
        if (scenePath != null){
            switchScene(event, scenePath);
        }
    }

    public void switchToExercise13(ActionEvent event) throws IOException {
        String scenePath = null;
        Node button = (Node) event.getSource();
        if(button == menuBtn13_1){
            scenePath = "scenes/exercises/exercise13/exercise13_1.fxml";
        }
        if (scenePath != null){
            switchScene(event, scenePath);
        }
    }

}

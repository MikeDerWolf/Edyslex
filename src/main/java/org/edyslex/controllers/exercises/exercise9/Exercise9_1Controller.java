package org.edyslex.controllers.exercises.exercise9;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import org.edyslex.controllers.exercises.ExerciseBaseController;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Exercise9_1Controller extends ExerciseBaseController implements Initializable {

    @FXML
    private Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9,
            lbl10, lbl11, lbl12, lbl13, lbl14, lbl15, lbl16, lbl17, lbl18;
    @FXML
    private Label ansLbl1, ansLbl2, ansLbl3, ansLbl4, ansLbl5, ansLbl6, ansLbl7, ansLbl8, ansLbl9,
            ansLbl10, ansLbl11, ansLbl12, ansLbl13, ansLbl14, ansLbl15, ansLbl16, ansLbl17, ansLbl18;

    private List<String> tags = new ArrayList<String>(Arrays.asList(
            "te-ren", "nea", "la-ma", "sac", "ac", "peș-te", "teasc", "leac", "deal",
            "pe-re-te", "a-ra-ma", "cre-ve-te", "rea", "se-te", "cas-tan", "tea-tru", "fan-fa-ra", "ve-de"));

    private List<String> wordsE = new ArrayList<String>(Arrays.asList(
            "te-ren", "peș-te", "pe-re-te", "cre-ve-te", "se-te", "ve-de"));

    private List<String> wordsEA = new ArrayList<String>(Arrays.asList(
            "nea", "teasc", "leac", "deal", "rea", "tea-tru"));

    private List<String> wordsA = new ArrayList<String>(Arrays.asList(
            "la-ma", "sac", "ac", "a-ra-ma", "cas-tan", "fan-fa-ra"));

    private HashMap<Label, List<String>> labelsMap = new HashMap<Label, List<String>>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        super.initialize();

        labelsMap.put(lbl1, wordsE);
        labelsMap.put(lbl2, wordsE);
        labelsMap.put(lbl3, wordsE);
        labelsMap.put(lbl4, wordsE);
        labelsMap.put(lbl5, wordsE);
        labelsMap.put(lbl6, wordsE);
        labelsMap.put(lbl7, wordsEA);
        labelsMap.put(lbl8, wordsEA);
        labelsMap.put(lbl9, wordsEA);
        labelsMap.put(lbl10, wordsEA);
        labelsMap.put(lbl11, wordsEA);
        labelsMap.put(lbl12, wordsEA);
        labelsMap.put(lbl13, wordsA);
        labelsMap.put(lbl14, wordsA);
        labelsMap.put(lbl15, wordsA);
        labelsMap.put(lbl16, wordsA);
        labelsMap.put(lbl17, wordsA);
        labelsMap.put(lbl18, wordsA);


        List<Label> labels = new ArrayList<Label>();
        labels.add(lbl1);
        labels.add(lbl2);
        labels.add(lbl3);
        labels.add(lbl4);
        labels.add(lbl5);
        labels.add(lbl6);
        labels.add(lbl7);
        labels.add(lbl8);
        labels.add(lbl9);
        labels.add(lbl10);
        labels.add(lbl11);
        labels.add(lbl12);
        labels.add(lbl13);
        labels.add(lbl14);
        labels.add(lbl15);
        labels.add(lbl16);
        labels.add(lbl17);
        labels.add(lbl18);

        List<Label> answerLabels = new ArrayList<Label>();
        answerLabels.add(ansLbl1);
        answerLabels.add(ansLbl2);
        answerLabels.add(ansLbl3);
        answerLabels.add(ansLbl4);
        answerLabels.add(ansLbl5);
        answerLabels.add(ansLbl6);
        answerLabels.add(ansLbl7);
        answerLabels.add(ansLbl8);
        answerLabels.add(ansLbl9);
        answerLabels.add(ansLbl10);
        answerLabels.add(ansLbl11);
        answerLabels.add(ansLbl12);
        answerLabels.add(ansLbl13);
        answerLabels.add(ansLbl14);
        answerLabels.add(ansLbl15);
        answerLabels.add(ansLbl16);
        answerLabels.add(ansLbl17);
        answerLabels.add(ansLbl18);

        Collections.shuffle(tags);

        int i = 0;
        for(Label lbl: answerLabels) {

            lbl.setText(tags.get(i));
            i++;

            lbl.setOnMousePressed(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    currentLabel = lbl;
                    lbl.setMouseTransparent(true);
                    labelOffsetX = lbl.getLayoutX() - event.getSceneX();
                    labelOffsetY = lbl.getLayoutY() - event.getSceneY();
                    event.setDragDetect(true);
                    labelContent = lbl.getText();
                    initialX = lbl.getLayoutX();
                    initialY = lbl.getLayoutY();
                }
            });

            lbl.setOnMouseReleased(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    lbl.setMouseTransparent(false);
                    lbl.setLayoutX(initialX);
                    lbl.setLayoutY(initialY);
                }
            });

            lbl.setOnMouseDragged(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    lbl.setLayoutX(event.getSceneX() + labelOffsetX);
                    lbl.setLayoutY(event.getSceneY() + labelOffsetY);
                    event.setDragDetect(false);
                }
            });

            lbl.setOnDragDetected(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    lbl.startFullDrag();
                }
            });
        }

        for(Label lbl: labels) {

            lbl.setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {
                public void handle(MouseDragEvent event) {
                    if(labelsMap.get(lbl).contains(labelContent)){
                        lbl.setText(labelContent);
                        lbl.setDisable(true);
                        lbl.setOpacity(1);
                        currentLabel.setVisible(false);
                        displayFeedback(rightAlert);
                    } else{
                        currentLabel.setMouseTransparent(false);
                        currentLabel.setLayoutX(initialX);
                        currentLabel.setLayoutY(initialY);
                        displayFeedback(wrongAlert);
                    }

                }
            });

            lbl.setOnMouseDragExited(new EventHandler<MouseDragEvent>() {
                public void handle(MouseDragEvent event) {
                }
            });
        }

    }

    public void switchToExercise9(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise9/exercise9_menu.fxml");
    }

    public void reset(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise9/exercise9_1.fxml");
    }

}

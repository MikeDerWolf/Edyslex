package org.edyslex.controllers.exercises.exercise10;

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


public class Exercise10_1Controller extends ExerciseBaseController implements Initializable {

    @FXML
    private Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8;
    @FXML
    private Label ansLbl1, ansLbl2, ansLbl3, ansLbl4, ansLbl5, ansLbl6, ansLbl7, ansLbl8;

    private List<String> tags = new ArrayList<String>(Arrays.asList(
            "pen-tru că fa-ce co-zo-nac.", "pen-tru că a spart ceș-cu-ța.", "pen-tru că es-te bolnav.",
            "pen-tru că es-te proas-pă-tă.", "pen-tru că es-te ră-gu-șit.", "pen-tru că s-a ju-cat pi-si-ca cu el.",
            "pen-tru că îi plac bas-me-le.", "pen-tru că a căl-cat în no-roi."));

    private HashMap<Label, String> labelsMap = new HashMap<Label, String>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        super.initialize();

        labelsMap.put(lbl1, "pen-tru că fa-ce co-zo-nac.");
        labelsMap.put(lbl2, "pen-tru că a spart ceș-cu-ța.");
        labelsMap.put(lbl3, "pen-tru că es-te bolnav.");
        labelsMap.put(lbl4, "pen-tru că es-te proas-pă-tă.");
        labelsMap.put(lbl5, "pen-tru că es-te ră-gu-șit.");
        labelsMap.put(lbl6, "pen-tru că s-a ju-cat pi-si-ca cu el.");
        labelsMap.put(lbl7, "pen-tru că îi plac bas-me-le.");
        labelsMap.put(lbl8, "pen-tru că a căl-cat în no-roi.");


        List<Label> labels = new ArrayList<Label>();
        labels.add(lbl1);
        labels.add(lbl2);
        labels.add(lbl3);
        labels.add(lbl4);
        labels.add(lbl5);
        labels.add(lbl6);
        labels.add(lbl7);
        labels.add(lbl8);

        List<Label> answerLabels = new ArrayList<Label>();
        answerLabels.add(ansLbl1);
        answerLabels.add(ansLbl2);
        answerLabels.add(ansLbl3);
        answerLabels.add(ansLbl4);
        answerLabels.add(ansLbl5);
        answerLabels.add(ansLbl6);
        answerLabels.add(ansLbl7);
        answerLabels.add(ansLbl8);

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
                    if(labelContent.equals(labelsMap.get(lbl))){
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

    public void switchToExercise10(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise10/exercise10_menu.fxml");
    }

    public void reset(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise10/exercise10_1.fxml");
    }

}

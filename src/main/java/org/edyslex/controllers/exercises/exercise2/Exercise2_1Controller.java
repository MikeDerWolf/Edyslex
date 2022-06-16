package org.edyslex.controllers.exercises.exercise2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.Pane;
import org.edyslex.controllers.exercises.ExerciseBaseController;
import org.edyslex.utils.AudioPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Exercise2_1Controller extends ExerciseBaseController implements Initializable {

    @FXML
    private Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9, lbl10, lbl11, lbl12,
            lbl13, lbl14, lbl15, lbl16, lbl17, lbl18, lbl19, lbl20, lbl21, lbl22, lbl23, lbl24;
    @FXML
    private Label ansLbl1, ansLbl2, ansLbl3, ansLbl4, ansLbl5, ansLbl6, ansLbl7, ansLbl8,
            ansLbl9, ansLbl10, ansLbl11, ansLbl12, ansLbl13, ansLbl14, ansLbl15, ansLbl16,
            ansLbl17, ansLbl18, ansLbl19, ansLbl20, ansLbl21, ansLbl22, ansLbl23, ansLbl24;

    private List<String> words = new ArrayList<String>(Arrays.asList(
            "fată", "supă", "vase", "file", "pere", "casă", "case", "vile",
            "focă", "mură", "sare", "mure"));

    private List<String> tags = new ArrayList<String>(Arrays.asList(
            "fa", "tă", "su", "pă", "va", "se", "fi", "le",
            "pe", "re", "ca", "să", "ca", "se", "vi", "le",
            "fo", "că", "mu", "ră", "sa", "re", "mu", "re"));

    private List<String> leftSideTags = new ArrayList<String>(Arrays.asList(
            "fa", "su", "va", "fi", "pe", "ca", "ca", "vi", "fo", "mu", "sa", "mu"));

    private List<String> rightSideTags = new ArrayList<String>(Arrays.asList(
            "tă", "pă", "se", "le", "re", "să", "se", "le", "că", "ră", "re", "re"));

    private HashMap<Label, Label> completedBy = new HashMap<Label, Label>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        super.initialize();

        List<Label> labels = new ArrayList<Label>();
        labels.add(lbl1); labels.add(lbl2); labels.add(lbl3); labels.add(lbl4);
        labels.add(lbl5); labels.add(lbl6); labels.add(lbl7); labels.add(lbl8);
        labels.add(lbl9); labels.add(lbl10); labels.add(lbl11); labels.add(lbl12);
        labels.add(lbl13); labels.add(lbl14); labels.add(lbl15); labels.add(lbl16);
        labels.add(lbl17); labels.add(lbl18); labels.add(lbl19); labels.add(lbl20);
        labels.add(lbl21); labels.add(lbl22); labels.add(lbl23); labels.add(lbl24);

        List<Label> answerLabels = new ArrayList<Label>();
        answerLabels.add(ansLbl1); answerLabels.add(ansLbl2); answerLabels.add(ansLbl3); answerLabels.add(ansLbl4);
        answerLabels.add(ansLbl5); answerLabels.add(ansLbl6); answerLabels.add(ansLbl7); answerLabels.add(ansLbl8);
        answerLabels.add(ansLbl9); answerLabels.add(ansLbl10); answerLabels.add(ansLbl11); answerLabels.add(ansLbl12);
        answerLabels.add(ansLbl13); answerLabels.add(ansLbl14); answerLabels.add(ansLbl15); answerLabels.add(ansLbl16);
        answerLabels.add(ansLbl17); answerLabels.add(ansLbl18); answerLabels.add(ansLbl19); answerLabels.add(ansLbl20);
        answerLabels.add(ansLbl21); answerLabels.add(ansLbl22); answerLabels.add(ansLbl23); answerLabels.add(ansLbl24);

        Collections.shuffle(tags);

        int i = 0;
        for(Label lbl: answerLabels) {
            lbl.setText(tags.get(i));
            i++;
            dragEventsSingleAnswer(lbl);
        }

        for(i=0; i<labels.size(); i++) {

            int finalI = i;
            labels.get(i).setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {
                public void handle(MouseDragEvent event) {

                    if((finalI % 2 == 0 &&
                            leftSideTags.contains(
                                    currentLabel.getText()
                            )) ||
                            (finalI % 2 == 1 &&
                                    rightSideTags.contains(
                                            currentLabel.getText()
                                    )))
                    {
                        labels.get(finalI).setText(labelContent);
                        labels.get(finalI).setDisable(true);
                        labels.get(finalI).setOpacity(1);

                        completedBy.put(labels.get(finalI), currentLabel);

                        currentLabel.setVisible(false);
                        currentLabel.setLayoutX(initialX);
                        currentLabel.setLayoutY(initialY);
                    }
                }
            });

            labels.get(i).setOnMouseDragExited(new EventHandler<MouseDragEvent>() {
                public void handle(MouseDragEvent event) {
                    Parent pane = labels.get(finalI).getParent();
                    if(noOfCompletedChildren((Pane) pane) == 2) {
                        Label leftChild = (Label) ((Pane) pane)
                                .getChildren().get(0);
                        Label rightChild = (Label) ((Pane) pane)
                                .getChildren().get(1);
                        String word =  leftChild.getText() +
                                rightChild.getText();

                        if(words.contains(word)){
                            displayFeedback(rightAlert);
                        } else{

                            leftChild.setDisable(false);
                            rightChild.setDisable(false);
                            leftChild.setText("");
                            rightChild.setText("");

                            completedBy.get(leftChild).setVisible(true);
                            completedBy.get(rightChild).setVisible(true);
                            completedBy.remove(leftChild);
                            completedBy.remove(rightChild);

                            displayFeedback(wrongAlert);
                        }
                    }
                }
            });

        }

    }

    public void playRequirement() throws Exception{
        AudioPlayer.play("src/main/resources/audio/ex2.wav");
    }

    public void reset(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise2/exercise2_1.fxml");
    }

}

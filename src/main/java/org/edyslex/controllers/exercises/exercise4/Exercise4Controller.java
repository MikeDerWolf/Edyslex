package org.edyslex.controllers.exercises.exercise4;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.edyslex.controllers.exercises.ExerciseBaseController;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Exercise4Controller extends ExerciseBaseController implements Initializable {

    @FXML
    private Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9, lbl10, lbl11, lbl12,
            lbl13, lbl14, lbl15, lbl16, lbl17, lbl18, lbl19, lbl20;
    @FXML
    private Label ansLbl1, ansLbl2, ansLbl3, ansLbl4, ansLbl5, ansLbl6, ansLbl7, ansLbl8,
            ansLbl9, ansLbl10, ansLbl11, ansLbl12, ansLbl13, ansLbl14, ansLbl15, ansLbl16,
            ansLbl17, ansLbl18, ansLbl19, ansLbl20;

    private List<String> sentences = new ArrayList<String>(Arrays.asList(
            "Mie-rea al-bi-nei es-te dul-ce.", "Bo-tul câi-ne-lui es-te ne-gru.", "Pa-na cor-bu-lui es-te nea-gră.",
            "Cio-cul ber-zei es-te lung.", "Su-pa bu-ni-cii es-te gus-toa-să.", "Creas-ta co-co-șu-lui es-te ro-și-e.",
            "Mă-rul bă-ia-tu-lui es-te gus-tos.", "U-re-chea mă-ga-ru-lui es-te lun-gă.",
            "Pă-lă-ri-a bu-ni-cu-lui es-te ve-che.", "Pu-ful bo-bo-ce-lu-lui es-te moa-le."));

    private List<String> tags = new ArrayList<String>(Arrays.asList(
            "Mie-rea al-bi-nei", "es-te dul-ce.", "Bo-tul câi-ne-lui", "es-te ne-gru.", "Pa-na cor-bu-lui",
            "es-te nea-gră.", "Cio-cul ber-zei", "es-te lung.", "Su-pa bu-ni-cii", "es-te gus-toa-să.",
            "Creas-ta co-co-șu-lui", "es-te ro-și-e.", "Mă-rul bă-ia-tu-lui", "es-te gus-tos.", "U-re-chea mă-ga-ru-lui",
            "es-te lun-gă.", "Pă-lă-ri-a bu-ni-cu-lui", "es-te ve-che.", "Pu-ful bo-bo-ce-lu-lui", "es-te moa-le."));

    private List<String> leftSideTags = new ArrayList<String>(Arrays.asList(
            "Mie-rea al-bi-nei", "Bo-tul câi-ne-lui", "Pa-na cor-bu-lui", "Cio-cul ber-zei", "Su-pa bu-ni-cii",
            "Creas-ta co-co-șu-lui", "Mă-rul bă-ia-tu-lui", "U-re-chea mă-ga-ru-lui", "Pă-lă-ri-a bu-ni-cu-lui",
            "Pu-ful bo-bo-ce-lu-lui"));

    private List<String> rightSideTags = new ArrayList<String>(Arrays.asList(
            "es-te dul-ce.", "es-te ne-gru.", "es-te nea-gră.", "es-te lung.", "es-te gus-toa-să.", "es-te ro-și-e.",
            "es-te gus-tos.", "es-te lun-gă.", "es-te ve-che.", "es-te moa-le."));

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

        List<Label> answerLabels = new ArrayList<Label>();
        answerLabels.add(ansLbl1); answerLabels.add(ansLbl2); answerLabels.add(ansLbl3); answerLabels.add(ansLbl4);
        answerLabels.add(ansLbl5); answerLabels.add(ansLbl6); answerLabels.add(ansLbl7); answerLabels.add(ansLbl8);
        answerLabels.add(ansLbl9); answerLabels.add(ansLbl10); answerLabels.add(ansLbl11); answerLabels.add(ansLbl12);
        answerLabels.add(ansLbl13); answerLabels.add(ansLbl14); answerLabels.add(ansLbl15); answerLabels.add(ansLbl16);
        answerLabels.add(ansLbl17); answerLabels.add(ansLbl18); answerLabels.add(ansLbl19); answerLabels.add(ansLbl20);

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

        for(i=0; i<labels.size(); i++) {

            int finalI = i;
            labels.get(i).setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {
                public void handle(MouseDragEvent event) {
                    Parent pane = labels.get(finalI).getParent();

                    if((finalI % 2 == 0 && inLeftSide(currentLabel)) ||
                            (finalI % 2 == 1 && inRightSide(currentLabel))){
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
                        Label leftChild = (Label) ((Pane) pane).getChildren().get(0);
                        Label rightChild = (Label) ((Pane) pane).getChildren().get(1);
                        String sentence =  leftChild.getText() + " " + rightChild.getText();

                        if(sentences.contains(sentence)){
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

    public boolean inLeftSide(Label lbl){
        return leftSideTags.contains(lbl.getText());
    }

    public boolean inRightSide(Label lbl){
        return rightSideTags.contains(lbl.getText());
    }

    public int noOfCompletedChildren(Pane pane){
        int no = 0;
        for(Node child: pane.getChildren()){
            Label label = (Label) child;
            if(!label.getText().isEmpty()){
                no++;
            }
        }
        return no;
    }

    public void displayFeedback(Alert alert){
        PauseTransition delay = new PauseTransition(Duration.seconds(1.4));
        delay.setOnFinished(e -> alert.close());

        alert.show();
        delay.play();

    }

    public void switchToExercises(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
                getClass().getClassLoader().getResource("scenes/exercises/exercises.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("scenes/menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void reset(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
                getClass().getClassLoader().getResource("scenes/exercises/exercise4/exercise4.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}

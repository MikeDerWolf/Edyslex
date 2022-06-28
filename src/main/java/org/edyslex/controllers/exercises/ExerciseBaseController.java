package org.edyslex.controllers.exercises;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.edyslex.Main;
import org.edyslex.controllers.BaseController;
import org.edyslex.utils.AudioPlayer;

import java.io.IOException;
import java.util.*;

public class ExerciseBaseController extends BaseController {

    protected Alert rightAlert = new Alert(Alert.AlertType.NONE);
    protected Alert wrongAlert = new Alert(Alert.AlertType.NONE);
    protected Image rightSymbolImage = new Image("images/happyFace.png");
    protected Image wrongSymbolImage = new Image("images/sadFace.png");
    protected ImageView rightImageView = new ImageView(rightSymbolImage);
    protected ImageView wrongImageView = new ImageView(wrongSymbolImage);

    protected Double labelOffsetX;
    protected Double labelOffsetY;
    protected Double initialX;
    protected Double initialY;
    protected String labelContent;
    protected Label currentLabel;

    public void initialize(){
        rightAlert.initStyle(StageStyle.UNDECORATED);
        wrongAlert.initStyle(StageStyle.UNDECORATED);
        rightAlert.initModality(Modality.APPLICATION_MODAL);
        wrongAlert.initModality(Modality.APPLICATION_MODAL);
        rightAlert.initOwner(Main.getPrimaryStage());
        wrongAlert.initOwner(Main.getPrimaryStage());

        rightImageView.setFitWidth(170);
        rightImageView.setFitHeight(170);
        rightImageView.setLayoutX(5);
        rightImageView.setLayoutY(5);
        wrongImageView.setFitWidth(170);
        wrongImageView.setFitHeight(170);
        wrongImageView.setLayoutX(5);
        wrongImageView.setLayoutY(5);

        DialogPane dialogPaneRight = rightAlert.getDialogPane();
        dialogPaneRight.getStylesheets().add("css/style.css");
        dialogPaneRight.getChildren().clear();
        dialogPaneRight.getChildren().add(rightImageView);
        dialogPaneRight.getButtonTypes().add(ButtonType.CLOSE);
        dialogPaneRight.setPrefWidth(180);
        dialogPaneRight.setPrefHeight(180);

        DialogPane dialogPaneWrong = wrongAlert.getDialogPane();
        dialogPaneWrong.getStylesheets().add("css/style.css");
        dialogPaneWrong.getChildren().clear();
        dialogPaneWrong.getChildren().add(wrongImageView);
        dialogPaneWrong.getButtonTypes().add(ButtonType.CLOSE);
        dialogPaneWrong.setPrefWidth(180);
        dialogPaneWrong.setPrefHeight(180);
    }

    public void back(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercises.fxml");
    }

    public void displayFeedback(Alert alert){
        if(alert == rightAlert){
            AudioPlayer.play("src/main/resources/audio/right_answer.wav");
        } else if(alert == wrongAlert){
            AudioPlayer.play("src/main/resources/audio/wrong_answer.wav");
        }

        PauseTransition delay = new PauseTransition(Duration.seconds(1.4));
        delay.setOnFinished(e -> alert.close());

        alert.show();
        delay.play();

    }

    public void centerImage(ImageView imageView) {
        Image img = imageView.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = imageView.getFitWidth() / img.getWidth();
            double ratioY = imageView.getFitHeight() / img.getHeight();

            double reduceCoeff = Math.min(ratioX, ratioY);

            w = img.getWidth() * reduceCoeff;
            h = img.getHeight() * reduceCoeff;

            imageView.setX((imageView.getFitWidth() - w) / 2);
            imageView.setY((imageView.getFitHeight() - h) / 2);

        }
    }

    public void dragEventsSingleAnswer(Label lbl){
        lbl.addEventFilter(MouseEvent.ANY, ev -> {
            if (ev.getButton() == MouseButton.SECONDARY) {
                ev.consume();
            }
        });

        lbl.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                currentLabel = lbl;
                lbl.toFront();
                lbl.setMouseTransparent(true);
                initialX = lbl.getLayoutX();
                initialY = lbl.getLayoutY();
                labelOffsetX = initialX - event.getSceneX();
                labelOffsetY = initialY - event.getSceneY();
                event.setDragDetect(true);
                labelContent = lbl.getText();
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

    public void dragEventsSingleSlot(Label lbl, HashMap<Label, ?> mapper){
        lbl.addEventFilter(MouseEvent.ANY, ev -> {
            if (ev.getButton() == MouseButton.SECONDARY) {
                ev.consume();
            }
        });

        lbl.setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {
            public void handle(MouseDragEvent event) {
                Boolean condition = null;
                var value = mapper.get(lbl);
                if(value instanceof List<?>){
                    condition = ((List<?>) value).contains(labelContent);
                } else {
                    condition = value.equals(labelContent);
                }
                if(condition){
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
}

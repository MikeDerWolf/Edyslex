package org.edyslex.controllers.exercises;

import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.edyslex.controllers.students.BaseController;

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

    public void displayFeedback(Alert alert){
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
}

package org.edyslex.controllers.exercises.exercise12;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.edyslex.controllers.students.BaseController;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Exercise12_1Controller extends BaseController implements Initializable {

    @FXML
    public Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8,
            lbl9, lbl10, lbl11, lbl12, lbl13, lbl14, lbl15, lbl16;
    public Label ansLbl1, ansLbl2, ansLbl3, ansLbl4, ansLbl5, ansLbl6,
            ansLbl7, ansLbl8, ansLbl9, ansLbl10, ansLbl11, ansLbl12, ansLbl13, ansLbl14, ansLbl15, ansLbl16;

    private List<String> tags = new ArrayList<String>(Arrays.asList(
            "ma-mei", "al-bi-nei", "mă-rii", "gi-ra-fei", "u-șii", "ca-sei", "fur-ni-cii", "ber-zei",
            "pi-si-cii", "va-cii", "ghe-tu-ței", "fus-tei", "lăm-pii", "rân-du-ni-cii", "bu-ni-cii", "gă-i-nii"));

    private HashMap<Label, String> labelsMap = new HashMap<Label, String>();

    private Alert rightAlert = new Alert(Alert.AlertType.NONE);
    private Alert wrongAlert = new Alert(Alert.AlertType.NONE);
    private Image rightSymbolImage = new Image("images/happyFace.png");
    private Image wrongSymbolImage = new Image("images/sadFace.png");
    private ImageView rightImageView = new ImageView(rightSymbolImage);
    private ImageView wrongImageView = new ImageView(wrongSymbolImage);

    private Double labelOffsetX;
    private Double labelOffsetY;
    private Double initialX;
    private Double initialY;
    private String labelContent;
    private Label currentLabel;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
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


        labelsMap.put(lbl1, "ca-sei");
        labelsMap.put(lbl2, "al-bi-nei");
        labelsMap.put(lbl3, "mă-rii");
        labelsMap.put(lbl4, "fur-ni-cii");
        labelsMap.put(lbl5, "lăm-pii");
        labelsMap.put(lbl6, "pi-si-cii");
        labelsMap.put(lbl7, "ghe-tu-ței");
        labelsMap.put(lbl8, "gă-i-nii");
        labelsMap.put(lbl9, "ma-mei");
        labelsMap.put(lbl10, "rân-du-ni-cii");
        labelsMap.put(lbl11, "bu-ni-cii");
        labelsMap.put(lbl12, "gi-ra-fei");
        labelsMap.put(lbl13, "u-șii");
        labelsMap.put(lbl14, "ber-zei");
        labelsMap.put(lbl15, "fus-tei");
        labelsMap.put(lbl16, "va-cii");


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

    public void displayFeedback(Alert alert){
        PauseTransition delay = new PauseTransition(Duration.seconds(1.4));
        delay.setOnFinished(e -> alert.close());

        alert.show();
        delay.play();

    }

    public void switchToExercise12(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
                getClass().getClassLoader().getResource("scenes/exercises/exercise12/exercise12_menu.fxml"));
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
                getClass().getClassLoader().getResource("scenes/exercises/exercise12/exercise12_1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}

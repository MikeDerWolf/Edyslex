package org.edyslex.controllers.exercises.exercise7;

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


public class Exercise7_1Controller extends BaseController implements Initializable {

    @FXML
    public ImageView img1, img2, img3, img4, img5, img6;
    public Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6;
    public Label ansLbl1, ansLbl2, ansLbl3, ansLbl4, ansLbl5, ansLbl6;

    private List<String> tags = new ArrayList<String>(Arrays.asList(
            "Da-na ce-re lap-te.", "Ro-di-ca ri-di-că la-da.", "Ca-lul du-ce sa-cul.",
            "Pi-si-ca ce-re lap-te.", "Dan du-ce sa-cul.", "To-ma ri-di-că ra-mu-ra."));

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


        labelsMap.put(lbl1, "To-ma ri-di-că ra-mu-ra.");
        labelsMap.put(lbl2, "Ro-di-ca ri-di-că la-da.");
        labelsMap.put(lbl3, "Pi-si-ca ce-re lap-te.");
        labelsMap.put(lbl4, "Da-na ce-re lap-te.");
        labelsMap.put(lbl5, "Dan du-ce sa-cul.");
        labelsMap.put(lbl6, "Ca-lul du-ce sa-cul.");


        List<ImageView> imageViews = new ArrayList<ImageView>();
        imageViews.add(img1);
        imageViews.add(img2);
        imageViews.add(img3);
        imageViews.add(img4);
        imageViews.add(img5);
        imageViews.add(img6);

        for(int i=0; i<imageViews.size(); i++){
            centerImage(imageViews.get(i));
        }

        List<Label> imageLabels = new ArrayList<Label>();
        imageLabels.add(lbl1);
        imageLabels.add(lbl2);
        imageLabels.add(lbl3);
        imageLabels.add(lbl4);
        imageLabels.add(lbl5);
        imageLabels.add(lbl6);

        List<Label> answerLabels = new ArrayList<Label>();
        answerLabels.add(ansLbl1);
        answerLabels.add(ansLbl2);
        answerLabels.add(ansLbl3);
        answerLabels.add(ansLbl4);
        answerLabels.add(ansLbl5);
        answerLabels.add(ansLbl6);

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

        for(Label lbl: imageLabels) {

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

    public void centerImage(ImageView imageView) {
        Image img = imageView.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = imageView.getFitWidth() / img.getWidth();
            double ratioY = imageView.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            imageView.setX((imageView.getFitWidth() - w) / 2);
            imageView.setY((imageView.getFitHeight() - h) / 2);

        }
    }

    public void switchToExercise7(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
                getClass().getClassLoader().getResource("scenes/exercises/exercise7/exercise7_menu.fxml"));
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
                getClass().getClassLoader().getResource("scenes/exercises/exercise7/exercise7_1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}

package org.edyslex.controllers.exercises.exercise8;

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


public class Exercise8_1Controller extends BaseController implements Initializable {

    @FXML
    public ImageView img1, img2, img3, img4, img5;
    public Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9, lbl10;
    public Label ansLbl1, ansLbl2, ansLbl3, ansLbl4, ansLbl5, ansLbl6, ansLbl7, ansLbl8, ansLbl9, ansLbl10;

    private List<String> tags = new ArrayList<String>(Arrays.asList(
            "Pa-vel se du-ce în ex-cur-si-e.", "Pe că-ra-re ve-de un cerb.", "Ta-ta să-deș-te un co-pac.",
            "Co-ri-na îl va u-da.", "Ni-cu-șor plân-ge.", "A-re o ra-nă la cot.",
            "Chi-vu es-te pri-mul cum-pă-ră-tor.", "Ce-re chi-fle proas-pe-te.", "Lui Dan îi pla-ce să co-lo-re-ze.",
            "Ce-re o car-te de co-lo-rat."));

    private HashMap<Label, List<String>> labelsMap = new HashMap<Label, List<String>>();

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


        labelsMap.put(lbl1, Arrays.asList("Pa-vel se du-ce în ex-cur-si-e.", "Pe că-ra-re ve-de un cerb."));
        labelsMap.put(lbl2, Arrays.asList("Pa-vel se du-ce în ex-cur-si-e.", "Pe că-ra-re ve-de un cerb."));
        labelsMap.put(lbl3, Arrays.asList("Ta-ta să-deș-te un co-pac.", "Co-ri-na îl va u-da."));
        labelsMap.put(lbl4, Arrays.asList("Ta-ta să-deș-te un co-pac.", "Co-ri-na îl va u-da."));
        labelsMap.put(lbl5, Arrays.asList("Ni-cu-șor plân-ge.", "A-re o ra-nă la cot."));
        labelsMap.put(lbl6, Arrays.asList("Ni-cu-șor plân-ge.", "A-re o ra-nă la cot."));
        labelsMap.put(lbl7, Arrays.asList("Chi-vu es-te pri-mul cum-pă-ră-tor.", "Ce-re chi-fle proas-pe-te."));
        labelsMap.put(lbl8, Arrays.asList("Chi-vu es-te pri-mul cum-pă-ră-tor.", "Ce-re chi-fle proas-pe-te."));
        labelsMap.put(lbl9, Arrays.asList("Lui Dan îi pla-ce să co-lo-re-ze.", "Ce-re o car-te de co-lo-rat."));
        labelsMap.put(lbl10, Arrays.asList("Lui Dan îi pla-ce să co-lo-re-ze.", "Ce-re o car-te de co-lo-rat."));


        List<ImageView> imageViews = new ArrayList<ImageView>();
        imageViews.add(img1);
        imageViews.add(img2);
        imageViews.add(img3);
        imageViews.add(img4);
        imageViews.add(img5);


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
        imageLabels.add(lbl7);
        imageLabels.add(lbl8);
        imageLabels.add(lbl9);
        imageLabels.add(lbl10);

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

    public void switchToExercise8(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(
                getClass().getClassLoader().getResource("scenes/exercises/exercise8/exercise8_menu.fxml"));
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
                getClass().getClassLoader().getResource("scenes/exercises/exercise8/exercise8_1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}

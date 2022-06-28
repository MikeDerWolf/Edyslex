package org.edyslex.controllers.exercises.exercise6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.edyslex.controllers.exercises.ExerciseBaseController;
import org.edyslex.utils.AudioPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Exercise6_4Controller extends ExerciseBaseController implements Initializable {

    @FXML
    private Button checkBtn1, checkBtn2, checkBtn3, checkBtn4, checkBtn5, checkBtn6, checkBtn7, checkBtn8, checkBtn9;

    @FXML
    private ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9;

    @FXML
    private Pane txtPane1, txtPane2, txtPane3, txtPane4, txtPane5, txtPane6, txtPane7, txtPane8, txtPane9;

    private final Integer numberOfImages = 9;

    private List<Integer> counters = new ArrayList<Integer>(Collections.nCopies(numberOfImages, 0));;

    private List<List<String>> syllables = Arrays.asList(
            Arrays.asList("jo", "ben"),
            Arrays.asList("nu", "făr"),
            Arrays.asList("pa", "vaj"),
            Arrays.asList("ba", "raj"),
            Arrays.asList("co", "joc"),
            Arrays.asList("ba", "zin"),
            Arrays.asList("ză", "vor"),
            Arrays.asList("ja", "lon"),
            Arrays.asList("ga", "raj")
        );

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        super.initialize();

        List<ImageView> imageViews = new ArrayList<ImageView>();
        imageViews.add(img1);
        imageViews.add(img2);
        imageViews.add(img3);
        imageViews.add(img4);
        imageViews.add(img5);
        imageViews.add(img6);
        imageViews.add(img7);
        imageViews.add(img8);
        imageViews.add(img9);

        for(ImageView imageView: imageViews){
            centerImage(imageView);
        }
    }

    public void check(ActionEvent event) throws IOException {
        String word = "";
        String fullWord = null;
        Pane targetPane = null;
        Integer index = null;
        Node button = (Node) event.getSource();

        if(button == checkBtn1){
            targetPane = txtPane1;
            fullWord = "joben";
            index = 0;
        } else if (button == checkBtn2) {
            targetPane = txtPane2;
            fullWord = "nufăr";
            index = 1;
        } else if (button == checkBtn3) {
            targetPane = txtPane3;
            fullWord = "pavaj";
            index = 2;
        } else if (button == checkBtn4) {
            targetPane = txtPane4;
            fullWord = "baraj";
            index = 3;
        } else if (button == checkBtn5) {
            targetPane = txtPane5;
            fullWord = "cojoc";
            index = 4;
        } else if (button == checkBtn6) {
            targetPane = txtPane6;
            fullWord = "bazin";
            index = 5;
        } else if (button == checkBtn7) {
            targetPane = txtPane7;
            fullWord = "zăvor";
            index = 6;
        } else if (button == checkBtn8) {
            targetPane = txtPane8;
            fullWord = "jalon";
            index = 7;
        } else if (button == checkBtn9) {
            targetPane = txtPane9;
            fullWord = "garaj";
            index = 8;
        }

        if(targetPane == null || fullWord == null){
            return;
        }

        List<Node> textFields = targetPane.getChildren();

        for(Node textField: textFields){
            word += ((TextField) textField).getText();
        }

        if(word.equals("")){
            return;
        }

        if(word.equals(fullWord)){
            button.setDisable(true);
            for(Node textField: textFields){
                ((TextField) textField).setEditable(false);
            }
            displayFeedback(rightAlert);
        } else {
            Integer counterValue = counters.get(index);

            displayFeedback(wrongAlert);

            if(counterValue >= 2){
                List<String> answerSyllables = syllables.get(index);
                for(int i=0; i<textFields.size(); i++){
                    ((TextField) textFields.get(i))
                            .setText(answerSyllables.get(i));
                    ((TextField) textFields.get(i))
                            .setEditable(false);
                }
                button.setDisable(true);

            }
            counters.set(index, counterValue + 1);
        }
    }

    public void playRequirement() throws Exception{
        AudioPlayer.play("src/main/resources/audio/ex6.wav");
    }

    public void reset(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise6/exercise6_4.fxml");
    }

}

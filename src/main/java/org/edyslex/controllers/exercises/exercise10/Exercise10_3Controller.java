package org.edyslex.controllers.exercises.exercise10;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.edyslex.controllers.exercises.ExerciseBaseController;
import org.edyslex.utils.AudioPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Exercise10_3Controller extends ExerciseBaseController implements Initializable {

    @FXML
    private Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8;
    @FXML
    private Label ansLbl1, ansLbl2, ansLbl3, ansLbl4, ansLbl5, ansLbl6, ansLbl7, ansLbl8;

    private List<String> tags = new ArrayList<String>(Arrays.asList(
            "și a des-cu-iat u-șa.", "și es-te ta-re mân-dru.", "și l-a dat ma-mei.",
            "și a ci-tit zi-a-rul.", "și a a-dus un pa-chet.",
            "și a che-mat chel-ne-rul.", "și a-prin-de lu-mâ-na-rea.", "și se an-tre-nea-ză zil-nic."));

    private HashMap<Label, String> labelsMap = new HashMap<Label, String>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        super.initialize();

        labelsMap.put(lbl1, "și a des-cu-iat u-șa.");
        labelsMap.put(lbl2, "și es-te ta-re mân-dru.");
        labelsMap.put(lbl3, "și l-a dat ma-mei.");
        labelsMap.put(lbl4, "și a ci-tit zi-a-rul.");
        labelsMap.put(lbl5, "și a a-dus un pa-chet.");
        labelsMap.put(lbl6, "și a che-mat chel-ne-rul.");
        labelsMap.put(lbl7, "și a-prin-de lu-mâ-na-rea.");
        labelsMap.put(lbl8, "și se an-tre-nea-ză zil-nic.");


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
            dragEventsSingleAnswer(lbl);
        }

        for(Label lbl: labels) {
            dragEventsSingleSlot(lbl, labelsMap);
        }

    }

    public void playRequirement() throws Exception{
        AudioPlayer.play("src/main/resources/audio/ex10.wav");
    }

    public void reset(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise10/exercise10_3.fxml");
    }

}

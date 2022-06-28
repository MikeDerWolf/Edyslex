package org.edyslex.controllers.exercises.exercise9;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.edyslex.controllers.exercises.ExerciseBaseController;
import org.edyslex.utils.AudioPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Exercise9_2Controller extends ExerciseBaseController implements Initializable {

    @FXML
    private Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9,
            lbl10, lbl11, lbl12, lbl13, lbl14, lbl15, lbl16, lbl17, lbl18;
    @FXML
    private Label ansLbl1, ansLbl2, ansLbl3, ansLbl4, ansLbl5, ansLbl6, ansLbl7, ansLbl8, ansLbl9,
            ansLbl10, ansLbl11, ansLbl12, ansLbl13, ansLbl14, ansLbl15, ansLbl16, ansLbl17, ansLbl18;

    private List<String> tags = new ArrayList<String>(Arrays.asList(
            "ni-sip", "crin", "pi-sic", "zid", "zil-nic", "vi-ni-lin", "iar-bă", "so-ia", "bă-iat",
            "ia-urt", "to-iag", "iar-nă", "ba-na-na", "pa-har", "mac", "da-ta", "fa-zan", "var"));

    private List<String> wordsI = new ArrayList<String>(Arrays.asList(
            "ni-sip", "crin", "pi-sic", "zid", "zil-nic", "vi-ni-lin"));

    private List<String> wordsIA = new ArrayList<String>(Arrays.asList(
            "iar-bă", "so-ia", "bă-iat", "ia-urt", "to-iag", "iar-nă"));

    private List<String> wordsA = new ArrayList<String>(Arrays.asList(
            "ba-na-na", "pa-har", "mac", "da-ta", "fa-zan", "var"));

    private HashMap<Label, List<String>> labelsMap = new HashMap<Label, List<String>>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        super.initialize();

        labelsMap.put(lbl1, wordsI);
        labelsMap.put(lbl2, wordsI);
        labelsMap.put(lbl3, wordsI);
        labelsMap.put(lbl4, wordsI);
        labelsMap.put(lbl5, wordsI);
        labelsMap.put(lbl6, wordsI);
        labelsMap.put(lbl7, wordsIA);
        labelsMap.put(lbl8, wordsIA);
        labelsMap.put(lbl9, wordsIA);
        labelsMap.put(lbl10, wordsIA);
        labelsMap.put(lbl11, wordsIA);
        labelsMap.put(lbl12, wordsIA);
        labelsMap.put(lbl13, wordsA);
        labelsMap.put(lbl14, wordsA);
        labelsMap.put(lbl15, wordsA);
        labelsMap.put(lbl16, wordsA);
        labelsMap.put(lbl17, wordsA);
        labelsMap.put(lbl18, wordsA);


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
        labels.add(lbl17);
        labels.add(lbl18);

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
        answerLabels.add(ansLbl17);
        answerLabels.add(ansLbl18);

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
        AudioPlayer.play("src/main/resources/audio/ex9.wav");
    }

    public void reset(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise9/exercise9_2.fxml");
    }

}

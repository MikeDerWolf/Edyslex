package org.edyslex.controllers.exercises.exercise11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.edyslex.controllers.exercises.ExerciseBaseController;
import org.edyslex.utils.AudioPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Exercise11_5Controller extends ExerciseBaseController implements Initializable {

    @FXML
    private Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9,
            lbl10, lbl11, lbl12, lbl13, lbl14, lbl15, lbl16, lbl17, lbl18;
    @FXML
    private Label ansLbl1, ansLbl2, ansLbl3, ansLbl4, ansLbl5, ansLbl6, ansLbl7, ansLbl8, ansLbl9,
            ansLbl10, ansLbl11, ansLbl12, ansLbl13, ansLbl14, ansLbl15, ansLbl16, ansLbl17, ansLbl18;

    private List<String> tags = new ArrayList<String>(Arrays.asList(
            "mă-lai", "unt", "za-hăr", "car-ne", "u-lei", "o-rez", "trom-pe-tă", "xi-lo-fon", "pi-an", "chi-ta-ră",
            "to-bă", "vi-oa-ră", "mar-ga-re-tă", "la-lea", "zam-bi-lă", "tran-da-fir", "cri-zan-te-mă", "ghi-o-cel"));

    private List<String> wordsColumn1 = new ArrayList<String>(Arrays.asList(
            "mă-lai", "unt", "za-hăr", "car-ne", "u-lei", "o-rez"));

    private List<String> wordsColumn2 = new ArrayList<String>(Arrays.asList(
            "trom-pe-tă", "xi-lo-fon", "pi-an", "chi-ta-ră", "to-bă", "vi-oa-ră"));

    private List<String> wordsColumn3 = new ArrayList<String>(Arrays.asList(
            "mar-ga-re-tă", "la-lea", "zam-bi-lă", "tran-da-fir", "cri-zan-te-mă", "ghi-o-cel"));

    private HashMap<Label, List<String>> labelsMap = new HashMap<Label, List<String>>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        super.initialize();

        labelsMap.put(lbl1, wordsColumn1);
        labelsMap.put(lbl2, wordsColumn1);
        labelsMap.put(lbl3, wordsColumn1);
        labelsMap.put(lbl4, wordsColumn1);
        labelsMap.put(lbl5, wordsColumn1);
        labelsMap.put(lbl6, wordsColumn1);
        labelsMap.put(lbl7, wordsColumn2);
        labelsMap.put(lbl8, wordsColumn2);
        labelsMap.put(lbl9, wordsColumn2);
        labelsMap.put(lbl10, wordsColumn2);
        labelsMap.put(lbl11, wordsColumn2);
        labelsMap.put(lbl12, wordsColumn2);
        labelsMap.put(lbl13, wordsColumn3);
        labelsMap.put(lbl14, wordsColumn3);
        labelsMap.put(lbl15, wordsColumn3);
        labelsMap.put(lbl16, wordsColumn3);
        labelsMap.put(lbl17, wordsColumn3);
        labelsMap.put(lbl18, wordsColumn3);


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
        AudioPlayer.play("src/main/resources/audio/ex11.wav");
    }

    public void reset(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise11/exercise11_5.fxml");
    }

}

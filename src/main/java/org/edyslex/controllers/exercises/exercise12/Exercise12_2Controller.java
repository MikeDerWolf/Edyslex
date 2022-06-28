package org.edyslex.controllers.exercises.exercise12;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.edyslex.controllers.exercises.ExerciseBaseController;
import org.edyslex.utils.AudioPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Exercise12_2Controller extends ExerciseBaseController implements Initializable {

    @FXML
    private Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9;
    @FXML
    private Label ansLbl1, ansLbl2, ansLbl3, ansLbl4, ansLbl5, ansLbl6,
            ansLbl7, ansLbl8, ansLbl9;

    private List<String> tags = new ArrayList<String>(Arrays.asList(
            "ta-tă-lui", "bu-ni-cu-lui", "bu-ni-cii", "fra-te-lui", "ma-mei", "pă-pu-șii", "că-țe-lu-lui",
            "be-be-lu-șu-lui", "pri-e-te-nei"));

    private HashMap<Label, String> labelsMap = new HashMap<Label, String>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        super.initialize();

        labelsMap.put(lbl1, "ta-tă-lui");
        labelsMap.put(lbl2, "bu-ni-cu-lui");
        labelsMap.put(lbl3, "bu-ni-cii");
        labelsMap.put(lbl4, "fra-te-lui");
        labelsMap.put(lbl5, "ma-mei");
        labelsMap.put(lbl6, "pă-pu-șii");
        labelsMap.put(lbl7, "că-țe-lu-lui");
        labelsMap.put(lbl8, "be-be-lu-șu-lui");
        labelsMap.put(lbl9, "pri-e-te-nei");


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
        AudioPlayer.play("src/main/resources/audio/ex12.wav");
    }

    public void reset(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise12/exercise12_2.fxml");
    }

}

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


public class Exercise10_4Controller extends ExerciseBaseController implements Initializable {

    @FXML
    private Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8;
    @FXML
    private Label ansLbl1, ansLbl2, ansLbl3, ansLbl4, ansLbl5, ansLbl6, ansLbl7, ansLbl8;

    private List<String> tags = new ArrayList<String>(Arrays.asList(
            "de-și ma-ma a pus borș în ea.", "de-și ză-pa-da s-a to-pit.", "de-și ma-ma l-a cu-sut ieri.",
            "de-și a-re cio-cul ră-nit.", "de-și bu-fo-nii vor lip-si.",
            "de-și a spă-lat-o.", "de-și bu-ni-ca a mă-tu-rat.", "de-și a-re a-ca-să u-nul vechi."));

    private HashMap<Label, String> labelsMap = new HashMap<Label, String>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        super.initialize();

        labelsMap.put(lbl1, "de-și ma-ma a pus borș în ea.");
        labelsMap.put(lbl2, "de-și ză-pa-da s-a to-pit.");
        labelsMap.put(lbl3, "de-și ma-ma l-a cu-sut ieri.");
        labelsMap.put(lbl4, "de-și a-re cio-cul ră-nit.");
        labelsMap.put(lbl5, "de-și bu-fo-nii vor lip-si.");
        labelsMap.put(lbl6, "de-și a spă-lat-o.");
        labelsMap.put(lbl7, "de-și bu-ni-ca a mă-tu-rat.");
        labelsMap.put(lbl8, "de-și a-re a-ca-să u-nul vechi.");


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
        switchScene(event, "scenes/exercises/exercise10/exercise10_4.fxml");
    }

}

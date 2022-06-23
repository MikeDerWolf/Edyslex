package org.edyslex.controllers.exercises.exercise5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.edyslex.controllers.exercises.ExerciseBaseController;
import org.edyslex.utils.AudioPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Exercise5_2Controller extends ExerciseBaseController implements Initializable {

    @FXML
    private Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9, lbl10, lbl11, lbl12;
    @FXML
    private Label ansLbl1, ansLbl2, ansLbl3, ansLbl4, ansLbl5, ansLbl6,
            ansLbl7, ansLbl8, ansLbl9, ansLbl10, ansLbl11, ansLbl12;

    private List<String> tags = new ArrayList<String>(Arrays.asList(
            "cân-te-cel", "bas-to-naș", "mij-lo-cel", "bul-gă-raș", "bos-tă-nel", "pal-to-naș",
            "cer-ce-luș", "ser-tă-raș", "bu-jo-rel", "năs-tu-raș", "car-to-naș", "șer-pi-șor"));

    private HashMap<Label, String> labelsMap = new HashMap<Label, String>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        super.initialize();

        labelsMap.put(lbl1, "cân-te-cel");
        labelsMap.put(lbl2, "bas-to-naș");
        labelsMap.put(lbl3, "mij-lo-cel");
        labelsMap.put(lbl4, "bul-gă-raș");
        labelsMap.put(lbl5, "bos-tă-nel");
        labelsMap.put(lbl6, "pal-to-naș");
        labelsMap.put(lbl7, "cer-ce-luș");
        labelsMap.put(lbl8, "ser-tă-raș");
        labelsMap.put(lbl9, "bu-jo-rel");
        labelsMap.put(lbl10, "năs-tu-raș");
        labelsMap.put(lbl11, "car-to-naș");
        labelsMap.put(lbl12, "șer-pi-șor");


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
        AudioPlayer.play("src/main/resources/audio/ex5.wav");
    }

    public void reset(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise5/exercise5_2.fxml");
    }

}

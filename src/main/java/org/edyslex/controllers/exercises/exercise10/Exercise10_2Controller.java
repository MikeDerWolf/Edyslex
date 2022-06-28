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


public class Exercise10_2Controller extends ExerciseBaseController implements Initializable {

    @FXML
    private Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8;
    @FXML
    private Label ansLbl1, ansLbl2, ansLbl3, ansLbl4, ansLbl5, ansLbl6, ansLbl7, ansLbl8;

    private List<String> tags = new ArrayList<String>(Arrays.asList(
            "ca să fa-că sa-la-tă.", "ca să fa-că pla-jă.", "ca să cum-pe-re ci-re-șe.",
            "ca să par-ti-ci-pe la ex-po-zi-ți-e.", "ca să cum-păr bi-le-te.",
            "ca să pre-gă-tim ghi-veci de le-gu-me.", "ca să plan-tez muș-ca-ta.", "ca să fa-că ghir-lan-de."));

    private HashMap<Label, String> labelsMap = new HashMap<Label, String>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        super.initialize();

        labelsMap.put(lbl1, "ca să fa-că sa-la-tă.");
        labelsMap.put(lbl2, "ca să fa-că pla-jă.");
        labelsMap.put(lbl3, "ca să cum-pe-re ci-re-șe.");
        labelsMap.put(lbl4, "ca să par-ti-ci-pe la ex-po-zi-ți-e.");
        labelsMap.put(lbl5, "ca să cum-păr bi-le-te.");
        labelsMap.put(lbl6, "ca să pre-gă-tim ghi-veci de le-gu-me.");
        labelsMap.put(lbl7, "ca să plan-tez muș-ca-ta.");
        labelsMap.put(lbl8, "ca să fa-că ghir-lan-de.");


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
        switchScene(event, "scenes/exercises/exercise10/exercise10_2.fxml");
    }

}

package org.edyslex.controllers.exercises.exercise12;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.edyslex.controllers.exercises.ExerciseBaseController;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Exercise12_1Controller extends ExerciseBaseController implements Initializable {

    @FXML
    private Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8,
            lbl9, lbl10, lbl11, lbl12, lbl13, lbl14, lbl15, lbl16;
    @FXML
    private Label ansLbl1, ansLbl2, ansLbl3, ansLbl4, ansLbl5, ansLbl6,
            ansLbl7, ansLbl8, ansLbl9, ansLbl10, ansLbl11, ansLbl12, ansLbl13, ansLbl14, ansLbl15, ansLbl16;

    private List<String> tags = new ArrayList<String>(Arrays.asList(
            "ma-mei", "al-bi-nei", "mă-rii", "gi-ra-fei", "u-șii", "ca-sei", "fur-ni-cii", "ber-zei",
            "pi-si-cii", "va-cii", "ghe-tu-ței", "fus-tei", "lăm-pii", "rân-du-ni-cii", "bu-ni-cii", "gă-i-nii"));

    private HashMap<Label, String> labelsMap = new HashMap<Label, String>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        super.initialize();

        labelsMap.put(lbl1, "ca-sei");
        labelsMap.put(lbl2, "al-bi-nei");
        labelsMap.put(lbl3, "mă-rii");
        labelsMap.put(lbl4, "fur-ni-cii");
        labelsMap.put(lbl5, "lăm-pii");
        labelsMap.put(lbl6, "pi-si-cii");
        labelsMap.put(lbl7, "ghe-tu-ței");
        labelsMap.put(lbl8, "gă-i-nii");
        labelsMap.put(lbl9, "ma-mei");
        labelsMap.put(lbl10, "rân-du-ni-cii");
        labelsMap.put(lbl11, "bu-ni-cii");
        labelsMap.put(lbl12, "gi-ra-fei");
        labelsMap.put(lbl13, "u-șii");
        labelsMap.put(lbl14, "ber-zei");
        labelsMap.put(lbl15, "fus-tei");
        labelsMap.put(lbl16, "va-cii");


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

    public void switchToExercise12(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise12/exercise12_menu.fxml");
    }

    public void reset(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise12/exercise12_1.fxml");
    }

}

package org.edyslex.controllers.exercises.exercise13;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.edyslex.controllers.exercises.ExerciseBaseController;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Exercise13_1Controller extends ExerciseBaseController implements Initializable {

    @FXML
    private Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8,
            lbl9, lbl10, lbl11, lbl12;
    @FXML
    private Label ansLbl1, ansLbl2, ansLbl3, ansLbl4, ansLbl5, ansLbl6,
            ansLbl7, ansLbl8, ansLbl9, ansLbl10, ansLbl11, ansLbl12;

    private List<String> tags = new ArrayList<String>(Arrays.asList(
            "lin-ge", "prin-de", "ca-u-tă", "a-du-ce", "cum-pă-ră", "hră-neș-te",
            "a-run-că", "mă-tu-ră", "re-pa-ră", "cu-ră-ță", "spu-ne", "tra-ge"));

    private HashMap<Label, String> labelsMap = new HashMap<Label, String>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        super.initialize();

        labelsMap.put(lbl1, "lin-ge");
        labelsMap.put(lbl2, "prin-de");
        labelsMap.put(lbl3, "ca-u-tă");
        labelsMap.put(lbl4, "a-du-ce");
        labelsMap.put(lbl5, "cum-pă-ră");
        labelsMap.put(lbl6, "hră-neș-te");
        labelsMap.put(lbl7, "a-run-că");
        labelsMap.put(lbl8, "mă-tu-ră");
        labelsMap.put(lbl9, "re-pa-ră");
        labelsMap.put(lbl10, "cu-ră-ță");
        labelsMap.put(lbl11, "spu-ne");
        labelsMap.put(lbl12, "tra-ge");


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

    public void back(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise13/exercise13_menu.fxml");
    }

    public void reset(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise13/exercise13_1.fxml");
    }

}

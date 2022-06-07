package org.edyslex.controllers.exercises.exercise10;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.edyslex.controllers.exercises.ExerciseBaseController;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Exercise10_1Controller extends ExerciseBaseController implements Initializable {

    @FXML
    private Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8;
    @FXML
    private Label ansLbl1, ansLbl2, ansLbl3, ansLbl4, ansLbl5, ansLbl6, ansLbl7, ansLbl8;

    private List<String> tags = new ArrayList<String>(Arrays.asList(
            "pen-tru că fa-ce co-zo-nac.", "pen-tru că a spart ceș-cu-ța.", "pen-tru că es-te bol-nav.",
            "pen-tru că es-te proas-pă-tă.", "pen-tru că es-te ră-gu-șit.", "pen-tru că s-a ju-cat pi-si-ca cu el.",
            "pen-tru că îi plac bas-me-le.", "pen-tru că a căl-cat în no-roi."));

    private HashMap<Label, String> labelsMap = new HashMap<Label, String>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        super.initialize();

        labelsMap.put(lbl1, "pen-tru că fa-ce co-zo-nac.");
        labelsMap.put(lbl2, "pen-tru că a spart ceș-cu-ța.");
        labelsMap.put(lbl3, "pen-tru că es-te bol-nav.");
        labelsMap.put(lbl4, "pen-tru că es-te proas-pă-tă.");
        labelsMap.put(lbl5, "pen-tru că es-te ră-gu-șit.");
        labelsMap.put(lbl6, "pen-tru că s-a ju-cat pi-si-ca cu el.");
        labelsMap.put(lbl7, "pen-tru că îi plac bas-me-le.");
        labelsMap.put(lbl8, "pen-tru că a căl-cat în no-roi.");


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

    public void back(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise10/exercise10_menu.fxml");
    }

    public void reset(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise10/exercise10_1.fxml");
    }

}

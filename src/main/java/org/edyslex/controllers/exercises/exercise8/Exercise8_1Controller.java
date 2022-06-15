package org.edyslex.controllers.exercises.exercise8;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.edyslex.controllers.exercises.ExerciseBaseController;
import org.edyslex.utils.AudioPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Exercise8_1Controller extends ExerciseBaseController implements Initializable {

    @FXML
    private ImageView img1, img2, img3, img4, img5;
    @FXML
    private Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9, lbl10;
    @FXML
    private Label ansLbl1, ansLbl2, ansLbl3, ansLbl4, ansLbl5, ansLbl6, ansLbl7, ansLbl8, ansLbl9, ansLbl10;

    private List<String> tags = new ArrayList<String>(Arrays.asList(
            "Pa-vel se du-ce în ex-cur-si-e.", "Pe că-ra-re ve-de un cerb.", "Ta-ta să-deș-te un co-pac.",
            "Co-ri-na îl va u-da.", "Ni-cu-șor plân-ge.", "A-re o ra-nă la cot.",
            "Chi-vu es-te pri-mul cum-pă-ră-tor.", "Ce-re chi-fle proas-pe-te.", "Lui Dan îi pla-ce să co-lo-re-ze.",
            "Ce-re o car-te de co-lo-rat."));

    private HashMap<Label, List<String>> labelsMap = new HashMap<Label, List<String>>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        super.initialize();

        labelsMap.put(lbl1, Arrays.asList("Pa-vel se du-ce în ex-cur-si-e.", "Pe că-ra-re ve-de un cerb."));
        labelsMap.put(lbl2, Arrays.asList("Pa-vel se du-ce în ex-cur-si-e.", "Pe că-ra-re ve-de un cerb."));
        labelsMap.put(lbl3, Arrays.asList("Ta-ta să-deș-te un co-pac.", "Co-ri-na îl va u-da."));
        labelsMap.put(lbl4, Arrays.asList("Ta-ta să-deș-te un co-pac.", "Co-ri-na îl va u-da."));
        labelsMap.put(lbl5, Arrays.asList("Ni-cu-șor plân-ge.", "A-re o ra-nă la cot."));
        labelsMap.put(lbl6, Arrays.asList("Ni-cu-șor plân-ge.", "A-re o ra-nă la cot."));
        labelsMap.put(lbl7, Arrays.asList("Chi-vu es-te pri-mul cum-pă-ră-tor.", "Ce-re chi-fle proas-pe-te."));
        labelsMap.put(lbl8, Arrays.asList("Chi-vu es-te pri-mul cum-pă-ră-tor.", "Ce-re chi-fle proas-pe-te."));
        labelsMap.put(lbl9, Arrays.asList("Lui Dan îi pla-ce să co-lo-re-ze.", "Ce-re o car-te de co-lo-rat."));
        labelsMap.put(lbl10, Arrays.asList("Lui Dan îi pla-ce să co-lo-re-ze.", "Ce-re o car-te de co-lo-rat."));


        List<ImageView> imageViews = new ArrayList<ImageView>();
        imageViews.add(img1);
        imageViews.add(img2);
        imageViews.add(img3);
        imageViews.add(img4);
        imageViews.add(img5);


        for(ImageView imageView: imageViews){
            centerImage(imageView);
        }

        List<Label> imageLabels = new ArrayList<Label>();
        imageLabels.add(lbl1);
        imageLabels.add(lbl2);
        imageLabels.add(lbl3);
        imageLabels.add(lbl4);
        imageLabels.add(lbl5);
        imageLabels.add(lbl6);
        imageLabels.add(lbl7);
        imageLabels.add(lbl8);
        imageLabels.add(lbl9);
        imageLabels.add(lbl10);

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

        Collections.shuffle(tags);

        int i = 0;
        for(Label lbl: answerLabels) {
            lbl.setText(tags.get(i));
            i++;
            dragEventsSingleAnswer(lbl);
        }

        for(Label lbl: imageLabels) {
            dragEventsSingleSlot(lbl, labelsMap);
        }

    }

    public void playRequirement() throws Exception{
        AudioPlayer.play("src/main/resources/audio/ex8.wav");
    }

    public void reset(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise8/exercise8_1.fxml");
    }

}

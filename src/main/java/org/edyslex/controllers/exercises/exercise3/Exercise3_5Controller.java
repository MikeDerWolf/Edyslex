package org.edyslex.controllers.exercises.exercise3;

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


public class Exercise3_5Controller extends ExerciseBaseController implements Initializable {

    @FXML
    private ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9, img10;
    @FXML
    private Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9, lbl10;
    @FXML
    private Label ansLbl1, ansLbl2, ansLbl3, ansLbl4, ansLbl5, ansLbl6,
            ansLbl7, ansLbl8, ansLbl9, ansLbl10;

    private List<String> tags = new ArrayList<String>(Arrays.asList(
            "pi-lă", "ca-lă", "si-tă", "cu-tă", "le-să", "ca-să", "cu-pă", "la-mă", "ra-mă", "pa-tă"));

    private HashMap<Label, String> labelsMap = new HashMap<Label, String>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        super.initialize();

        labelsMap.put(lbl1, "pi-lă");
        labelsMap.put(lbl2, "ca-lă");
        labelsMap.put(lbl3, "si-tă");
        labelsMap.put(lbl4, "cu-tă");
        labelsMap.put(lbl5, "le-să");
        labelsMap.put(lbl6, "ca-să");
        labelsMap.put(lbl7, "cu-pă");
        labelsMap.put(lbl8, "la-mă");
        labelsMap.put(lbl9, "ra-mă");
        labelsMap.put(lbl10, "pa-tă");

        List<ImageView> imageViews = new ArrayList<ImageView>();
        imageViews.add(img1);
        imageViews.add(img2);
        imageViews.add(img3);
        imageViews.add(img4);
        imageViews.add(img5);
        imageViews.add(img6);
        imageViews.add(img7);
        imageViews.add(img8);
        imageViews.add(img9);
        imageViews.add(img10);

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
        AudioPlayer.play("src/main/resources/audio/ex3.wav");
    }

    public void reset(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise3/exercise3_5.fxml");
    }

}

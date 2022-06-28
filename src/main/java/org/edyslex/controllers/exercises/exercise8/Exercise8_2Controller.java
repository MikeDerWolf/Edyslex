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


public class Exercise8_2Controller extends ExerciseBaseController implements Initializable {

    @FXML
    private ImageView img1, img2, img3;
    @FXML
    private Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9;
    @FXML
    private Label ansLbl1, ansLbl2, ansLbl3, ansLbl4, ansLbl5, ansLbl6, ansLbl7, ansLbl8, ansLbl9;

    private List<String> tags = new ArrayList<String>(Arrays.asList(
            "A-fa-ră es-te ză-pa-dă ma-re.", "A pri-mit mul-te ca-do-uri.", "A-re o le-să ro-și-e.",
            "Ti-tus ca-u-tă sa-ni-a în du-lap.", "Ma-ma a copt un tort.", "A-zo-rel es-te fe-ri-cit.",
            "Se du-ce la der-de-luș.", "Ol-ga du-ce că-țe-lul la plim-ba-re.", "Ma-ra îm-pli-neș-te cinci ani."));

    private HashMap<Label, List<String>> labelsMap = new HashMap<Label, List<String>>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        super.initialize();

        List<String> sentences1 = Arrays.asList(
                "A pri-mit mul-te ca-do-uri.", "Ma-ma a copt un tort.", "Ma-ra îm-pli-neș-te cinci ani.");

        List<String> sentences2 = Arrays.asList(
                "A-re o le-să ro-și-e.", "A-zo-rel es-te fe-ri-cit.", "Ol-ga du-ce că-țe-lul la plim-ba-re.");

        List<String> sentences3 = Arrays.asList(
                "A-fa-ră es-te ză-pa-dă ma-re.", "Ti-tus ca-u-tă sa-ni-a în du-lap.", "Se du-ce la der-de-luș.");


        labelsMap.put(lbl1, sentences1);
        labelsMap.put(lbl2, sentences1);
        labelsMap.put(lbl3, sentences1);
        labelsMap.put(lbl4, sentences2);
        labelsMap.put(lbl5, sentences2);
        labelsMap.put(lbl6, sentences2);
        labelsMap.put(lbl7, sentences3);
        labelsMap.put(lbl8, sentences3);
        labelsMap.put(lbl9, sentences3);


        List<ImageView> imageViews = new ArrayList<ImageView>();
        imageViews.add(img1);
        imageViews.add(img2);
        imageViews.add(img3);


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

        for(Label lbl: imageLabels) {
            dragEventsSingleSlot(lbl, labelsMap);
        }

    }

    public void playRequirement() throws Exception{
        AudioPlayer.play("src/main/resources/audio/ex8.wav");
    }

    public void reset(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise8/exercise8_2.fxml");
    }

}

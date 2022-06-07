package org.edyslex.controllers.exercises.exercise7;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.edyslex.controllers.exercises.ExerciseBaseController;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Exercise7_1Controller extends ExerciseBaseController implements Initializable {

    @FXML
    private ImageView img1, img2, img3, img4, img5, img6;
    @FXML
    private Label lbl1, lbl2, lbl3, lbl4, lbl5, lbl6;
    @FXML
    private Label ansLbl1, ansLbl2, ansLbl3, ansLbl4, ansLbl5, ansLbl6;

    private List<String> tags = new ArrayList<String>(Arrays.asList(
            "Da-na ce-re lap-te.", "Ro-di-ca ri-di-că la-da.", "Ca-lul du-ce sa-cul.",
            "Pi-si-ca ce-re lap-te.", "Dan du-ce sa-cul.", "To-ma ri-di-că ra-mu-ra."));

    private HashMap<Label, String> labelsMap = new HashMap<Label, String>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        super.initialize();

        labelsMap.put(lbl1, "To-ma ri-di-că ra-mu-ra.");
        labelsMap.put(lbl2, "Ro-di-ca ri-di-că la-da.");
        labelsMap.put(lbl3, "Pi-si-ca ce-re lap-te.");
        labelsMap.put(lbl4, "Da-na ce-re lap-te.");
        labelsMap.put(lbl5, "Dan du-ce sa-cul.");
        labelsMap.put(lbl6, "Ca-lul du-ce sa-cul.");


        List<ImageView> imageViews = new ArrayList<ImageView>();
        imageViews.add(img1);
        imageViews.add(img2);
        imageViews.add(img3);
        imageViews.add(img4);
        imageViews.add(img5);
        imageViews.add(img6);

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

        List<Label> answerLabels = new ArrayList<Label>();
        answerLabels.add(ansLbl1);
        answerLabels.add(ansLbl2);
        answerLabels.add(ansLbl3);
        answerLabels.add(ansLbl4);
        answerLabels.add(ansLbl5);
        answerLabels.add(ansLbl6);

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

    public void back(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise7/exercise7_menu.fxml");
    }

    public void reset(ActionEvent event) throws IOException {
        switchScene(event, "scenes/exercises/exercise7/exercise7_1.fxml");
    }

}

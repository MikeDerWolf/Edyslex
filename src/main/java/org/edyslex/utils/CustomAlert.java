package org.edyslex.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import org.edyslex.Main;

public class CustomAlert {

    public static void createAlert(Alert.AlertType alertType, String headerText, String content){
        Alert alert = new Alert(alertType);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(Main.getPrimaryStage());
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add("css/style.css");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setHeaderText(headerText);
        alert.setContentText(content);
        alert.show();
    }
}

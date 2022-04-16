package org.edyslex.controllers.students;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BaseController {

    protected double xOffset = 0;
    protected double yOffset = 0;
    protected Stage stage;

    @FXML
    protected Pane stageBar;

    public void getOffset(MouseEvent event){
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    public void setOffset(MouseEvent event){
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }

    public void closeWindow(ActionEvent event){
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void minimizeWindow(ActionEvent event){
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
}

package org.edyslex.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.edyslex.utils.AudioPlayer;

import java.io.IOException;

public class BaseController {

    protected double xOffset = 0;
    protected double yOffset = 0;
    protected Stage stage;

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

    public void switchToMenu(ActionEvent event) throws IOException {
        switchScene(event, "scenes/menu.fxml");
    }

    public void switchScene(ActionEvent event, String filename) throws IOException {
        AudioPlayer.stop();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(filename));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

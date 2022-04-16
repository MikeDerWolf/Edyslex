package org.edyslex;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.edyslex.utils.HibernateUtil;
import org.hibernate.Session;

public class Main extends Application {

    public static Session session;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("scenes/menu.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Edyslex");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        session = HibernateUtil.getSessionFactory().openSession();
        launch(args);
        HibernateUtil.shutdown();
    }
}

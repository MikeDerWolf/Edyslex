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

    private static Session session;
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Main.setPrimaryStage(primaryStage);
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("scenes/menu.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Edyslex");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        Main.setSession(HibernateUtil.getSessionFactory().openSession());
        launch(args);
        HibernateUtil.shutdown();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        Main.primaryStage = primaryStage;
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        Main.session = session;
    }
}

package dev.netho.jupiter;

import dev.netho.jupiter.controller.Home;
import dev.netho.jupiter.utils.MysqlBridge;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Main extends Application {

    MysqlBridge mysqlBridge = MysqlBridge.getInstance();

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(loadTela("/dev/netho/fxml/home.fxml", o-> new Home()));
        stage.setTitle("Jupiter");
        stage.setScene(scene);
        stage.show();
    }

    public static Parent loadTela(String fxml, Callback controller){
        Parent root = null;
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(fxml));
            loader.setControllerFactory(controller);

            root = loader.load();

        }catch (Exception e){
            System.out.println("Problema no arquivo fxml. Está correto?? "+fxml);
            e.printStackTrace();
            System.exit(0);
        }
        return root;
    }

    public static void main(String[] args) {
        launch();
    }
}
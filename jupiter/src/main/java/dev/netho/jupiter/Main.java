package dev.netho.jupiter;

import dev.netho.jupiter.controller.Home;
import dev.netho.jupiter.daos.AuthJDBC;
import dev.netho.jupiter.daos.DiaryJDBC;
import dev.netho.jupiter.daos.PatientJDBC;
import dev.netho.jupiter.daos.PsychologistJDBC;
import dev.netho.jupiter.daos.interfaces.AuthDAO;
import dev.netho.jupiter.daos.interfaces.DiaryDAO;
import dev.netho.jupiter.daos.interfaces.PatientDAO;
import dev.netho.jupiter.daos.interfaces.PsychologistDAO;
import dev.netho.jupiter.repositories.DiaryRepository;
import dev.netho.jupiter.repositories.PatientRepository;
import dev.netho.jupiter.repositories.PsychologistRepository;
import dev.netho.jupiter.services.AuthService;
import dev.netho.jupiter.utils.MysqlBridge;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Main extends Application {

    MysqlBridge mysqlBridge = MysqlBridge.getInstance();

    AuthDAO authDAO = new AuthJDBC(mysqlBridge);
    PsychologistDAO psychologistDAO = new PsychologistJDBC(mysqlBridge);
    PatientDAO patientDAO = new PatientJDBC(mysqlBridge);
    DiaryDAO diaryDAO = new DiaryJDBC(mysqlBridge);

    PsychologistRepository psychologistRepository = new PsychologistRepository(psychologistDAO, patientDAO, diaryDAO);
    PatientRepository patientRepository = new PatientRepository(patientDAO, diaryDAO);
    DiaryRepository diaryRepository = new DiaryRepository(diaryDAO);

    AuthService authService = new AuthService(authDAO, patientRepository, psychologistRepository);

    @Override
    public void start(Stage stage) {

        //Disable Javafx text antialiasing to get font smoother
        System.setProperty("prism.lcdtext", "false");

        Scene scene = new Scene(loadScreen("/dev/netho/fxml/home.fxml", o-> new Home(authService, patientRepository, psychologistRepository, diaryRepository)));
        stage.setTitle("Jupiter");
        stage.setScene(scene);
        stage.setHeight(720);
        stage.setWidth(1280);
        stage.show();


    }

    public static Parent loadScreen(String fxml, Callback controller){
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
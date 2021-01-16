package JavaProje;

import JavaProje.Database.Database;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @FXML
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("views/KullaniciGiris.fxml"));
        primaryStage.setTitle("Kullanıcı Giriş");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    @Override
    public void init() throws Exception {
        super.init();

        if(!Database.getInstance().veritabaniniAc()){
            Platform.exit();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Database.getInstance().veritabaniniKapat();
    }

    public static void main(String[] args) {
        launch(args);

    }
}

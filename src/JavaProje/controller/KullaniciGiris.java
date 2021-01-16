package JavaProje.controller;

import JavaProje.Database.Database;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class KullaniciGiris{
    @FXML
    private JFXTextField isim;

    @FXML
    private JFXPasswordField password;


    @FXML
    public void Giris(javafx.scene.input.MouseEvent mouseEvent)throws IOException {
        String ad = isim.getText();
        String sifre = password.getText();
        if (Database.getInstance().Giris(ad, sifre)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/JavaProje/views/AnaSayfa.fxml"));
            Parent root = loader.load();
            Node node = (Node) mouseEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setTitle("Stok Takip Otomasyonu");
            stage.setScene(new Scene(root));
            AnaSayfa anaSayfa = loader.getController();
            anaSayfa.urunBilgi();
        } else {
            Info.InfoBox("Kullanici Adi veya Sifre yanlis","Failed");
        }
    }
}




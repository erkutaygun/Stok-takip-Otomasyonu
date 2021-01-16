package JavaProje.controller;

import JavaProje.Database.Database;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

import javafx.stage.Stage;

import java.io.IOException;

public class KullaniciEkle {
    @FXML
    public JFXButton exit;

    @FXML
    private JFXPasswordField sifre;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXTextField isim;

    @FXML
    public void cikis(javafx.scene.input.MouseEvent event) throws IOException {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();

    }

    public void uyeol(javafx.scene.input.MouseEvent event) throws IOException {
        String ad = isim.getText();
        String email = mail.getText();
        String password = sifre.getText();
        if(ad.isEmpty() || email.isEmpty() || password.isEmpty()){
            Info.InfoBox("Kullanıcı Eklenemedi", "Failed");
        }else{
            Database.getInstance().KulaniciEkle(ad,email,password);
            Info.InfoBox("Kullanıcı Eklendi","Başarılı");
        }
    }
}

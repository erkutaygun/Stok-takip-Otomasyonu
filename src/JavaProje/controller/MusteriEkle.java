package JavaProje.controller;

import JavaProje.Database.Database;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class MusteriEkle {
    @FXML
    private JFXTextField ad;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXTextField sifre;
    @FXML
    private JFXButton exit;

    public void ekle(javafx.scene.input.MouseEvent event) throws IOException{
        String m_ad = ad.getText();
        String m_mail = mail.getText();
        String m_sifre = sifre.getText();

        if(m_ad.isEmpty() || m_mail.isEmpty() || m_sifre.isEmpty()){
            Info.InfoBox("Müşteri Eklenmedi","failed");
        }else{
            Database.getInstance().musteriEkle(m_ad,m_mail,m_sifre);
            Info.InfoBox("Müşteri Eklendi","başarılı");
        }
    }
    public void cikis(javafx.scene.input.MouseEvent event) throws IOException{
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
}

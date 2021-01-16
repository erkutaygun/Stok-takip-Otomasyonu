package JavaProje.controller;

import JavaProje.Database.Database;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MusteriGuncelle {
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField ad;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXPasswordField sifre;
    @FXML
    private JFXButton exit;

    @FXML
    public void guncelle(MouseEvent event) throws IOException {
       String m_id = id.getText();
       String m_ad = ad.getText();
       String m_mail = mail.getText();
       String m_sifre = sifre.getText();

       if(m_id.isEmpty() || m_ad.isEmpty() || m_mail.isEmpty() || m_sifre.isEmpty()){
           Info.InfoBox("müşteri güncellenemedi","failed");
       }else {

           Database.getInstance().musteriGuncelle(Integer.parseInt(m_id),m_ad,m_mail,m_sifre);
           Info.InfoBox("Müşteri güncellendi","başarılı");
       }
    }
    @FXML
    public void cikis(MouseEvent event)throws IOException{
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
}

package JavaProje.controller;

import JavaProje.Database.Database;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class UrunEkle {

    @FXML
    private JFXTextField kod;
    @FXML
    private JFXTextField ad;
    @FXML
    private JFXTextField adet;
    @FXML
    private JFXTextField depo_ad;
    @FXML
    private JFXTextField yer;
    @FXML
    private JFXButton exit;
    @FXML
    public void Urunekle(javafx.scene.input.MouseEvent event) throws IOException{
        String u_kod = kod.getText();
        String u_ad = ad.getText();
        String s_adet = adet.getText();
        String d_ad = depo_ad.getText();
        String d_yer = yer.getText();
        if(u_kod.isEmpty() || u_ad.isEmpty() || s_adet.isEmpty() || d_ad.isEmpty() || d_yer.isEmpty()){
            Info.InfoBox("ürün eklenmedi","failed");
        }else{
            Database.getInstance().urunEkle(u_kod,u_ad);
            Database.getInstance().stokEKle(s_adet);
            Database.getInstance().DepoEkle(d_ad,d_yer);
            Info.InfoBox("ürün eklendi","başarılı");
        }
    }
    @FXML
    public void cikis(javafx.scene.input.MouseEvent event)throws IOException{
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
}

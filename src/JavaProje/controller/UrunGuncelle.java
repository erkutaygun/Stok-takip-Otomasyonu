package JavaProje.controller;

import JavaProje.Database.Database;
import JavaProje.model.Depo;
import JavaProje.model.Stok;
import JavaProje.model.Urun;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class UrunGuncelle{
    int u_id;
    String u_kod;
    String u_ad;
    String s_adet;
    String d_ad;
    String d_yer;
    @FXML
    private JFXTextField urun_id;
    @FXML
    private JFXTextField urun_kod;
    @FXML
    private JFXTextField urun_ad;
    @FXML
    private JFXTextField stok_adet;
    @FXML
    private JFXTextField depo_ad;
    @FXML
    private JFXTextField depo_yer;
    @FXML
    private JFXButton cikis;

    @FXML
    public void guncelle(javafx.scene.input.MouseEvent event)throws IOException {
        u_id = Integer.parseInt(urun_id.getText());
        u_ad = urun_kod.getText();
        u_kod = urun_ad.getText();
        s_adet = stok_adet.getText();
        d_ad = depo_ad.getText();
        d_yer = depo_yer.getText();
        if (u_kod.isEmpty() || u_ad.isEmpty() || s_adet.isEmpty() || d_ad.isEmpty() || d_yer.isEmpty()) {
            Info.InfoBox("Ürün güncellenmedi ", "failed");
        } else {
            Info.InfoBox("Ürün güncellendi", "başarılı");
            Task<Boolean> task = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    return Database.getInstance().urunGuncelle(u_id,u_ad,u_kod);
                }
            };
            Urun urun = new Urun();
            task.setOnSucceeded(workerStateEvent -> urun.setUrunAdi(u_kod));
            task.setOnSucceeded(workerStateEvent -> urun.setUrunKodu(u_ad));
            new Thread(task).start();
            Task<Boolean> task1 = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    return Database.getInstance().stokGuncelle(u_id,s_adet);
                }
            };
            Stok stok = new Stok();
            task1.setOnSucceeded(workerStateEvent -> stok.setStokAdet(s_adet));
            new Thread(task1).start();
            Task<Boolean> task2 = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    return Database.getInstance().depoGuncelle(u_id,d_ad,d_yer);
                }
            };
            Depo depo = new Depo();
            task2.setOnSucceeded(workerStateEvent -> depo.setDepoAdi(d_ad));
            task2.setOnSucceeded(workerStateEvent -> depo.setDepoAdresi(d_yer));
            new Thread(task2).start();
        }
    }
    public void exit(javafx.scene.input.MouseEvent event)throws IOException{
        Stage stage = (Stage) cikis.getScene().getWindow();
        stage.close();
    }
}
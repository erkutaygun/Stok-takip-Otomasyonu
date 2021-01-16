package JavaProje.controller;

import JavaProje.Database.Database;
import JavaProje.model.Kullanici;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class KullaniciEkrani {

    @FXML
    private JFXButton Add;
    @FXML
    private JFXButton Del;
    @FXML
    private JFXButton exit;
    @FXML
    private JFXButton Ref;
    @FXML
    private JFXTextField id_deger;
    @FXML
    private TableView tablo;

    @FXML
    public void Sign(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/JavaProje/views/KullaniciEkle.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Üye ol");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void Del(MouseEvent event) throws IOException {
        Kullanici secilenKullanici = (Kullanici) tablo.getSelectionModel().getSelectedItem();
        if (secilenKullanici == null) {
            Info.InfoBox("Kullanıcı Seçilmedi", "failed");
        } else {
            Database.getInstance().kullaniciSil(secilenKullanici.getKullaniciID());
            Info.InfoBox("Kullanici Silindi", "basarili");
        }
    }

    @FXML
    public void kullanicilar() {
        Task<ObservableList<Kullanici>> task = new kullanicilar();
        tablo.itemsProperty().bind(task.valueProperty());
        new Thread(task).start();

    }

    @FXML
    public void Reflesh(javafx.scene.input.MouseEvent event) throws IOException {
        tablo.getItems().clear();
        tablo.getItems().addAll(Database.getInstance().kullanicilar());
    }

    @FXML
    public void cikis(javafx.scene.input.MouseEvent event) throws IOException {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

}
class kullanicilar extends Task {
    @Override
    protected ObservableList<Kullanici> call() throws Exception {
        return FXCollections.observableArrayList(Database.getInstance().kullanicilar());
    }
}

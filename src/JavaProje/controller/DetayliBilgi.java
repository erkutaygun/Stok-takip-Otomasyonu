package JavaProje.controller;

import JavaProje.Database.Database;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class DetayliBilgi extends TumBilgiler{
    @FXML
    private TableView tablostok;
    @FXML
    private TableView tablodepo;
    @FXML
    private JFXButton exit;
    @FXML
    public void detay(){
        tablostok.itemsProperty().bind(task1.valueProperty());
        TumBilgiler.stok();
        tablodepo.itemsProperty().bind(task2.valueProperty());
        TumBilgiler.depo();
    }
    @FXML
    public void cikis(javafx.scene.input.MouseEvent event)throws IOException{
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void yenile(javafx.scene.input.MouseEvent event)throws IOException{
        tablostok.getItems().clear();
        tablostok.getItems().addAll(Database.getInstance().stokGetir());
        tablodepo.getItems().clear();
        tablodepo.getItems().addAll(Database.getInstance().depolar());
    }
}

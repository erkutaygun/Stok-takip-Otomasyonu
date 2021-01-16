package JavaProje.controller;

import JavaProje.Database.Database;
import JavaProje.model.Musteri;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class MusteriEkrani {
    @FXML
    private TableView tablo;
    @FXML
    private JFXTextField search;
    @FXML
    private JFXButton exit;
    @FXML
    public void musteriBilgi(){
        FilteredList<Musteri> filteredList = new FilteredList<>(FXCollections.observableArrayList(Database.getInstance().musteriler()), p -> true);
        search.textProperty().addListener((observable,oldValue,newValue)->{
            filteredList.setPredicate(musteri -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(musteri.getMusteriAdi().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }else if(musteri.getMusteriMail().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }else if(musteri.getMusteriSifre().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                return false;
            });
        });
        SortedList<Musteri> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tablo.comparatorProperty());
        tablo.setItems(sortedList);
    }
    @FXML
    public void musteriekle(javafx.scene.input.MouseEvent event)throws IOException{
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/JavaProje/views/MusteriEKle.fxml"));
        stage.setTitle("Müşteri Ekle");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void musterisil(MouseEvent event) throws IOException {
        Musteri secilenmusteri = (Musteri) tablo.getSelectionModel().getSelectedItem();
        if (secilenmusteri == null) {
            Info.InfoBox("Müşteri Silinmedi","failed");
        } else {
            Database.getInstance().musteriSil(secilenmusteri.getMusteriID());
            Info.InfoBox("Müşteri Silindi", "basarili");
        }
    }
    @FXML
    public void musteriguncelle(MouseEvent event) throws IOException{
        Stage stage =new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/JavaProje/views/MusteriGuncelle.fxml"));
        stage.setTitle("Müşteri Güncelle");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void yenile(MouseEvent event) throws IOException{
        musteriBilgi();
    }
    @FXML
    public void cikis(MouseEvent event) throws  IOException{
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
}

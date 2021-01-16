package JavaProje.controller;

import JavaProje.Database.Database;

import JavaProje.model.Urun;
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
import javafx.stage.Stage;
import java.io.IOException;

public class AnaSayfa{
    @FXML
    private JFXButton exit;
    @FXML
    private TableView TabloUrun;
    @FXML
    private JFXTextField search;
    @FXML
    public void urunBilgi() {
        FilteredList<Urun> filteredList = new FilteredList<>(FXCollections.observableArrayList(Database.getInstance().urunler()), p -> true);
        search.textProperty().addListener((observable,oldValue,newValue)->{
            filteredList.setPredicate(urun -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(urun.getUrunAdi().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }else if(urun.getUrunKodu().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                return false;
            });
        });
        SortedList<Urun> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(TabloUrun.comparatorProperty());
        TabloUrun.setItems(sortedList);
    }


    @FXML
    public void kullaniciBilgi(javafx.scene.input.MouseEvent event) throws IOException{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/JavaProje/views/KullaniciEkrani.fxml"));
        Parent root = loader.load();
        KullaniciEkrani kullaniciEkrani = loader.getController();
        kullaniciEkrani.kullanicilar();
        stage.setTitle("Kullanıcı Giriş");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public  void DetayliBilgi(javafx.scene.input.MouseEvent event) throws IOException{
                Stage stage =new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/JavaProje/views/DetayliBilgi.fxml"));
                Parent root1 = loader.load();
                stage.setTitle("Detaylı Bilgi");
                Scene scene = new Scene(root1);
                stage.setScene(scene);
                stage.show();
                DetayliBilgi detayliBilgi = loader.getController();
                detayliBilgi.detay();
            }
    @FXML
    public void urunSil(javafx.scene.input.MouseEvent event)throws IOException{
      Urun secilenurun = (Urun) TabloUrun.getSelectionModel().getSelectedItem();
      if(secilenurun == null){
          Info.InfoBox("ürün seçilmedi","failed");
          return;
      }else{
          Database.getInstance().urunSıl(secilenurun.getUrunID());
          Database.getInstance().stokSil(secilenurun.getUrunID());
          Database.getInstance().depoSil(secilenurun.getUrunID());
          Info.InfoBox("ürün silindi","başarılı");
          TabloUrun.refresh();

      }
    }
    @FXML
    public void urunEkle(javafx.scene.input.MouseEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/JavaProje/views/UrunEkle.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Ürün ekle");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        TabloUrun.refresh();
    }
    @FXML
    public void urunGuncelle(javafx.scene.input.MouseEvent event)throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("/JavaProje/views/UrunGuncelle.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Ürün Guncelle");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    @FXML
    public void musteri(javafx.scene.input.MouseEvent event)throws IOException{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/JavaProje/views/MusteriEkrani.fxml"));
        Parent root = loader.load();
        stage.setTitle("Müşteri Ekranı");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        MusteriEkrani musteriEkrani = loader.getController();
        musteriEkrani.musteriBilgi();
    }

    @FXML
    public void yenile(javafx.scene.input.MouseEvent event)throws IOException{
        urunBilgi();
    }
    @FXML
    public void cikis(javafx.scene.input.MouseEvent event)throws IOException{
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
}
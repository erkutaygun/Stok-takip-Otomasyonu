package JavaProje.Database;

import JavaProje.model.*;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    private Connection connection;
    public static final String DB_NAME = "StokTakip.db";

    private Database() {
    }

    private static Database instance = new Database();

    public static Database getInstance() {
        return instance;
    }


    public boolean veritabaniniAc() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public void veritabaniniKapat() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean Giris(String isim, String sifre) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String sorgu = "SELECT * FROM kullanici WHERE  kullaniciAdi = ? AND kullaniciSifre = ?";
        try {
            preparedStatement = connection.prepareStatement(sorgu);
            preparedStatement.setString(1, isim);
            preparedStatement.setString(2, sifre);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean KulaniciEkle(String yeni_isim, String yeni_mail, String yeni_sifre) {
        PreparedStatement statement;
        String sorgu = "INSERT INTO kullanici (kullaniciAdi,kullaniciMail,kullaniciSifre) VALUES(?,?,?)";
        try {
            statement = connection.prepareStatement(sorgu);
            statement.setString(1, yeni_isim);
            statement.setString(2, yeni_mail);
            statement.setString(3, yeni_sifre);
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Kullanici> kullanicilar() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM kullanici ORDER BY kullaniciAdi ASC")) {
            ArrayList<Kullanici> tumkKullanicilar = new ArrayList<>();
            while (resultSet.next()) {
                Kullanici kullanici = new Kullanici();
                kullanici.setKullaniciID(resultSet.getInt("kullaniciID"));
                kullanici.setKullaniciAdi(resultSet.getString("kullaniciAdi"));
                kullanici.setKullaniciMail(resultSet.getString("kullaniciMail"));
                tumkKullanicilar.add(kullanici);
            }
            return tumkKullanicilar;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean kullaniciSil(int id) {
        String sorgu = "DELETE FROM kullanici WHERE kullaniciID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sorgu)) {
            preparedStatement.setInt(1, id);
            id = preparedStatement.executeUpdate();
            return id == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<Urun> urunler(){
        try (Statement statement = connection.createStatement();
             ResultSet sonuc = statement.executeQuery("SELECT * FROM urun ORDER BY urunAdi ASC ")) {
            ArrayList<Urun> tumUrunler = new ArrayList<>();
            while (sonuc.next()) {
                Urun urun = new Urun();
                urun.setUrunID(sonuc.getInt("urunID"));
                urun.setUrunAdi(sonuc.getString("urunAdi"));
                urun.setUrunKodu(sonuc.getString("urunKod"));
                tumUrunler.add(urun);
            }
            return tumUrunler;

        } catch (SQLException e) {
            System.out.println("Sorgu basarisiz" + e.getMessage());
            return null;
        }
    }

    public ArrayList<Stok> stokGetir() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM stok ORDER BY urunID ASC ");
            ArrayList<Stok> stoklar = new ArrayList<>();
            while (resultSet.next()) {
                Stok stok = new Stok();
                stok.setStokID(resultSet.getInt("stokID"));
                stok.setStokAdet(resultSet.getString("stokAdet"));
                stok.setUrunID(resultSet.getInt("urunID"));
                stoklar.add(stok);
            }
            return stoklar;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<Depo> depolar() { ;
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM depo ORDER BY urunID ASC ");
            ArrayList<Depo> tumDepolar = new ArrayList<>();
            while(resultSet.next()){
                Depo depo = new Depo();
                depo.setDepoID(resultSet.getInt("depoID"));
                depo.setDepoAdi(resultSet.getString("depoAdi"));
                depo.setDepoAdresi(resultSet.getString("depoAdresi"));
                depo.setUrunID(resultSet.getInt("urunID"));
                tumDepolar.add(depo);
            }
            return tumDepolar;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public boolean urunEkle(String yeni_kod,String yeni_ad){
        PreparedStatement preparedStatement;
        String sorgu = "INSERT INTO urun (urunAdi,urunKod) VALUES (?,?)";
        try{
            preparedStatement = connection.prepareStatement(sorgu);
            preparedStatement.setString(1,yeni_ad);
            preparedStatement.setString(2,yeni_kod);
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean stokEKle(String yeni_adet) {
        int sonuc = 0;
        PreparedStatement preparedStatement;
        String sorgu = "INSERT INTO stok (stokAdet,urunID) VALUES (?,?)";
        try (Statement statement =connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT MAX(urunID) FROM urun");
            if(resultSet.next()) {
                sonuc += resultSet.getInt(1);

                preparedStatement = connection.prepareStatement(sorgu);
                preparedStatement.setString(1, yeni_adet);
                preparedStatement.setInt(2, sonuc);
                preparedStatement.executeUpdate();
            }
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean DepoEkle(String isim,String yer){
        int sonuc = 0;
        PreparedStatement preparedStatement;
        String sorgu = "INSERT INTO depo (depoAdi,depoAdresi,urunID) VALUES (?,?,?)";
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT MAX(urunID) FROM urun");
            if(resultSet.next()){
                sonuc += resultSet.getInt(1);
                preparedStatement = connection.prepareStatement(sorgu);
                preparedStatement.setString(1,isim);
                preparedStatement.setString(2,yer);
                preparedStatement.setInt(3,sonuc);
                preparedStatement.executeUpdate();
            }
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean urunSıl(int id){
        String sorgu = "DELETE FROM urun WHERE urunID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sorgu)) {
            preparedStatement.setInt(1, id);
            id = preparedStatement.executeUpdate();
            return id == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean stokSil(int id){
        String sorgu = "DELETE FROM stok WHERE urunID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sorgu)) {
            preparedStatement.setInt(1, id);
            id = preparedStatement.executeUpdate();
            return id == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean depoSil(int id){
        String sorgu = "DELETE FROM depo WHERE urunID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sorgu)) {
            preparedStatement.setInt(1, id);
            id = preparedStatement.executeUpdate();
            return id == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean urunGuncelle(int id, String yeniAd,String yenikod) {
        String sorgu = "UPDATE urun SET urunAdi=?,urunKod=? WHERE urunID=? ";
        try(PreparedStatement statement = connection.prepareStatement(sorgu)){
            statement.setString(1, yenikod);
            statement.setString(2,yeniAd);
            statement.setInt(3, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean stokGuncelle(int id,String yeniStok) {
        String sorgu = "UPDATE stok SET stokAdet=?  WHERE urunID=? ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sorgu)){
          preparedStatement.setString(1,yeniStok);
          preparedStatement.setInt(2,id);
          preparedStatement.executeUpdate();
          return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean depoGuncelle(int id, String yeniAd,String yeniYer) {
        String sorgu = "UPDATE depo SET depoAdi=?,depoAdresi=? WHERE urunID=? ";
        try(PreparedStatement statement = connection.prepareStatement(sorgu)){
            statement.setString(1, yeniAd);
            statement.setString(2,yeniYer);
            statement.setInt(3, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<Musteri> musteriler() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM musteri ORDER BY musteriAdi ASC")) {
            ArrayList<Musteri> tumMusteriler = new ArrayList<>();
            while (resultSet.next()) {
                Musteri musteri = new Musteri();
                musteri.setMusteriID(resultSet.getInt("musteriID"));
                musteri.setMusteriAdi(resultSet.getString("musteriAdi"));
                musteri.setMusteriMail(resultSet.getString("musteriMail"));
                musteri.setMusteriSifre(resultSet.getString("musteriSifre"));
                tumMusteriler.add(musteri);
            }
            return tumMusteriler;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean musteriEkle(String yeni_isim, String yeni_mail, String yeni_sifre) {
        PreparedStatement statement;
        String sorgu = "INSERT INTO musteri (musteriAdi,musteriMail,musteriSifre) VALUES(?,?,?)";
        try {
            statement = connection.prepareStatement(sorgu);
            statement.setString(1, yeni_isim);
            statement.setString(2, yeni_mail);
            statement.setString(3, yeni_sifre);
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean musteriSil(int id) {
        String sorgu = "DELETE FROM musteri WHERE musteriID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sorgu)) {
            preparedStatement.setInt(1, id);
            id = preparedStatement.executeUpdate();
            return id == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean musteriGuncelle(int id,String yeni_ad,String yeni_mail,String yeni_sifre){
        String sorgu = "UPDATE musteri SET musteriAdi=?,musteriMail=?,musteriSifre=? WHERE musteriID=?";
        try(PreparedStatement preparedStatement=connection.prepareStatement(sorgu)){
            preparedStatement.setString(1,yeni_ad);
            preparedStatement.setString(2,yeni_mail);
            preparedStatement.setString(3,yeni_sifre);
            preparedStatement.setInt(4,id);
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
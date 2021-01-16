package JavaProje.model;

public class Kullanici {
    private int kullaniciID;
    private String kullaniciAdi;
    private String kullaniciSifre;
    private String kullaniciMail;

    public int getKullaniciID() {
        return kullaniciID;
    }

    public void setKullaniciID(int kullaniciID) {
        this.kullaniciID = kullaniciID;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getKullaniciSifre() {
        return kullaniciSifre;
    }

    public void setKullaniciSifre(String kullaniciSifre) {
        this.kullaniciSifre = kullaniciSifre;
    }

    public String getKullaniciMail() {
        return kullaniciMail;
    }

    public void setKullaniciMail(String kullaniciMail) {
        this.kullaniciMail = kullaniciMail;
    }
}
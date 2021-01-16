package JavaProje.model;

public class Depo {
    private int depoID;
    private String depoAdi;
    private String depoAdresi;
    private int urunID;

    public int getDepoID() {
        return depoID;
    }

    public void setDepoID(int depoID) {
        this.depoID = depoID;
    }

    public String getDepoAdi() {
        return depoAdi;
    }

    public void setDepoAdi(String depoAdi) {
        this.depoAdi = depoAdi;
    }

    public String getDepoAdresi() {
        return depoAdresi;
    }

    public void setDepoAdresi(String depoAdresi) {
        this.depoAdresi = depoAdresi;
    }

    public int getUrunID() {
        return urunID;
    }

    public void setUrunID(int urunID) {
        this.urunID = urunID;
    }
}

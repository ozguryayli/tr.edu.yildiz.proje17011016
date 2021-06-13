package tr.edu.yildiz.proje17011016;

import java.io.Serializable;

public class Kiyafet implements Serializable {
    private String tur;
    private String renk;
    private String desen;
    private String tarih;
    private String fiyat;
    private String image;


    public Kiyafet(){

    }

    public Kiyafet(String tur, String renk, String desen, String tarih, String fiyat, String image) {
        this.tur = tur;
        this.renk = renk;
        this.desen = desen;
        this.tarih = tarih;
        this.fiyat = fiyat;
        this.image = image;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public String getRenk() {
        return renk;
    }

    public void setRenk(String renk) {
        this.renk = renk;
    }

    public String getDesen() {
        return desen;
    }

    public void setDesen(String desen) {
        this.desen = desen;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getFiyat() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

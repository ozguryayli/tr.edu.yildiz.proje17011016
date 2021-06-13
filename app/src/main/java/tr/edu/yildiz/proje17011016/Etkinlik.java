package tr.edu.yildiz.proje17011016;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Etkinlik implements Serializable {
    private String isim;
    private String tur;
    private String tarih;
    private String lokasyon;
    private String basUstu;
    private String surat;
    private String ust;
    private String alt;
    private String ayak;


    public Etkinlik(){

    }

    public Etkinlik(String isim, String tur, String tarih, String lokasyon, String basUstu, String surat, String ust, String alt, String ayak) {
        this.isim = isim;
        this.tur = tur;
        this.tarih = tarih;
        this.lokasyon = lokasyon;
        this.basUstu = basUstu;
        this.surat = surat;
        this.ust = ust;
        this.alt = alt;
        this.ayak = ayak;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getLokasyon() {
        return lokasyon;
    }

    public void setLokasyon(String lokasyon) {
        this.lokasyon = lokasyon;
    }

    public String getBasUstu() {
        return basUstu;
    }

    public void setBasUstu(String basUstu) {
        this.basUstu = basUstu;
    }

    public String getSurat() {
        return surat;
    }

    public void setSurat(String surat) {
        this.surat = surat;
    }

    public String getUst() {
        return ust;
    }

    public void setUst(String ust) {
        this.ust = ust;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getAyak() {
        return ayak;
    }

    public void setAyak(String ayak) {
        this.ayak = ayak;
    }
}

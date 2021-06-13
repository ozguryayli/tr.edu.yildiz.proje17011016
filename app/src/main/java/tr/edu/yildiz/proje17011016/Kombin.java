package tr.edu.yildiz.proje17011016;

import java.io.Serializable;

public class Kombin implements Serializable {
    private String imageBasUstu;
    private String imageSurat;
    private String imageUst;
    private String imageAlt;
    private String imageAyak;


    public Kombin(String imageBasUstu, String imageSurat, String imageUst, String imageAlt, String imageAyak) {
        this.imageBasUstu = imageBasUstu;
        this.imageSurat = imageSurat;
        this.imageUst = imageUst;
        this.imageAlt = imageAlt;
        this.imageAyak = imageAyak;
    }

    public String getImageBasUstu() {
        return imageBasUstu;
    }

    public void setImageBasUstu(String imageBasUstu) {
        this.imageBasUstu = imageBasUstu;
    }

    public String getImageSurat() {
        return imageSurat;
    }

    public void setImageSurat(String imageSurat) {
        this.imageSurat = imageSurat;
    }

    public String getImageUst() {
        return imageUst;
    }

    public void setImageUst(String imageUst) {
        this.imageUst = imageUst;
    }

    public String getImageAlt() {
        return imageAlt;
    }

    public void setImageAlt(String imageAlt) {
        this.imageAlt = imageAlt;
    }

    public String getImageAyak() {
        return imageAyak;
    }

    public void setImageAyak(String imageAyak) {
        this.imageAyak = imageAyak;
    }
}

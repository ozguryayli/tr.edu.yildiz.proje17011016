package tr.edu.yildiz.proje17011016;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cekmece implements Serializable {
    private List<Kiyafet> kiyafetList = new ArrayList<Kiyafet>();

    public Cekmece(){

    }
    public Cekmece(List<Kiyafet> kiyafetList) {
        this.kiyafetList = kiyafetList;
    }

    public List<Kiyafet> getkiyafetList() {
        return kiyafetList;
    }

    public void setkiyafetList(List<Kiyafet> kiyafetList) {
        this.kiyafetList = kiyafetList;
    }
}

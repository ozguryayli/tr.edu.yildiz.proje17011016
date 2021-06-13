package tr.edu.yildiz.proje17011016;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button cekmeceButonu;
    Button kabinButonu;
    Button kombinlerButonu;
    public static List<Cekmece> cekmeceList = new ArrayList<Cekmece>();
    public static List<Kombin> kombinList = new ArrayList<Kombin>();
    public static List<Etkinlik> etkinlikList = new ArrayList<Etkinlik>();
    public static int cekmeceNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cekmeceNo = 0;
        cekmeceButonu = findViewById(R.id.buttonCekmece);
        kabinButonu = findViewById(R.id.buttonKabin);
        kombinlerButonu = findViewById(R.id.buttonKombinler);


        MainActivity.cekmeceList.clear();
        try {

            File f = new File(getFilesDir() + "/cekmeceler.dat");

            try {
                FileInputStream fileIn = new FileInputStream(f);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);

                boolean Read = true;
                try {
                    while (Read) {
                        Cekmece cekmece = (Cekmece) objectIn.readObject();
                        MainActivity.cekmeceList.add(cekmece);
                        //      System.out.println("main okuma kısmı"+person.getEmail());
                        //              objectIn = new ObjectInputStream(fileIn);
                    }

                } catch (EOFException e) {
                    Read = false;
                }
                objectIn.close();
                fileIn.close();


            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
        catch (Exception e){
            Log.v("Error Serialization",e.getMessage());
            e.printStackTrace();
        }


        MainActivity.etkinlikList.clear();
        try {

            File f1 = new File(getFilesDir() + "/etkinlikler.dat");

            try {
                FileInputStream fileIn1 = new FileInputStream(f1);
                ObjectInputStream objectIn1 = new ObjectInputStream(fileIn1);

                boolean Read = true;
                try {
                    while (Read) {
                        Etkinlik etkinlik = (Etkinlik) objectIn1.readObject();
                        MainActivity.etkinlikList.add(etkinlik);
                        //      System.out.println("main okuma kısmı"+person.getEmail());
                        //              objectIn = new ObjectInputStream(fileIn);
                    }

                } catch (EOFException e) {
                    Read = false;
                }
                objectIn1.close();
                fileIn1.close();


            } catch (Exception ex) {
                ex.printStackTrace();

            }


        }catch (Exception e){
            Log.v("Error Serialization",e.getMessage());
            e.printStackTrace();
        }

        MainActivity.kombinList.clear();
        try {

            File f2 = new File(getFilesDir() + "/kombinler.dat");

            try {
                FileInputStream fileIn2 = new FileInputStream(f2);
                ObjectInputStream objectIn2 = new ObjectInputStream(fileIn2);

                boolean Read = true;
                try {
                    while (Read) {
                        Kombin kombin = (Kombin) objectIn2.readObject();
                        MainActivity.kombinList.add(kombin);
                        //      System.out.println("main okuma kısmı"+person.getEmail());
                        //              objectIn = new ObjectInputStream(fileIn);
                    }

                } catch (EOFException e) {
                    Read = false;
                }
                objectIn2.close();
                fileIn2.close();


            } catch (Exception ex) {
                ex.printStackTrace();

            }


        }catch (Exception e){
            Log.v("Error Serialization",e.getMessage());
            e.printStackTrace();
        }



    }

    public void openCekmece(View view){
        Intent intent = new Intent(this, CekmeceActivity.class);
        startActivity(intent);
    }
    public void openKabin(View view){
        Intent intent = new Intent(this, KombinGorActivity.class);
        startActivity(intent);
    }
    public void openKombinleriListele(View view){
        Intent intent = new Intent(this, KombinleriListeleActivity.class);
        startActivity(intent);
    }
    public void openEtkinlikEkle(View view){
        Intent intent = new Intent(this, EtkinlikEkleActivity.class);
        startActivity(intent);
    }
    public void openEtkinlikler(View view){
        Intent intent = new Intent(this, EtkinlikActivity.class);
        startActivity(intent);
    }


}
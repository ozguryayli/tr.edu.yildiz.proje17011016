package tr.edu.yildiz.proje17011016;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class KiyafetActivity extends AppCompatActivity {

    Button ekle;
    RecyclerView recyclerView;
    public RecyclerKiyafetAdapter recyclerAdapter;
    int icekmece;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        icekmece=intent.getIntExtra("icekmece",0);
        setContentView(R.layout.activity_kiyafet);
        ekle = findViewById(R.id.button8);
        recyclerView = findViewById(R.id.recyclerview2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerKiyafetAdapter(MainActivity.cekmeceList.get(icekmece).getkiyafetList().size(),icekmece);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();
    }

    public void ekle(View view){
        Intent intent = new Intent(view.getContext(), KiyafetEkleActivity.class);
        intent.putExtra("icekmece",icekmece);
        finish();
        startActivity(intent);

    }

    @Override
    protected void onStop() {
        super.onStop();
        //   System.out.println("Sign Up Act. on stop girdi");

        FileOutputStream outStream = null;

        try {
            File f = new File(getFilesDir() + "/cekmeceler.dat");
            f.delete();
            File file =new File(getFilesDir() + "/cekmeceler.dat");
            outStream = new FileOutputStream(file, true);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
            for(Cekmece i : MainActivity.cekmeceList){

                objectOutStream.writeObject(i);
                //        System.out.println("sign up yazma k??sm??"+i.getEmail());

            }
            objectOutStream.flush();
            objectOutStream.close();
            outStream.close();
        }
        catch (Exception e){
            Log.v("Error Serialization",e.getMessage());
            e.printStackTrace();
        }

        ////////////////////

        FileOutputStream outStream1 = null;

        try {
            File f1 = new File(getFilesDir() + "/etkinlikler.dat");
            f1.delete();
            File file =new File(getFilesDir() + "/etkinlikler.dat");
            outStream1 = new FileOutputStream(file, true);
            ObjectOutputStream objectOutStream1 = new ObjectOutputStream(outStream1);
            for(Etkinlik i : MainActivity.etkinlikList){

                objectOutStream1.writeObject(i);
                //        System.out.println("sign up yazma k??sm??"+i.getEmail());

            }
            objectOutStream1.flush();
            objectOutStream1.close();
            outStream1.close();
        }
        catch (Exception e){
            Log.v("Error Serialization",e.getMessage());
            e.printStackTrace();
        }

        /////////////////////

        FileOutputStream outStream2 = null;

        try {
            File f2 = new File(getFilesDir() + "/kombinler.dat");
            f2.delete();
            File file =new File(getFilesDir() + "/kombinler.dat");
            outStream2 = new FileOutputStream(file, true);
            ObjectOutputStream objectOutStream2 = new ObjectOutputStream(outStream2);
            for(Kombin i : MainActivity.kombinList){

                objectOutStream2.writeObject(i);
                //        System.out.println("sign up yazma k??sm??"+i.getEmail());

            }
            objectOutStream2.flush();
            objectOutStream2.close();
            outStream2.close();
        }
        catch (Exception e){
            Log.v("Error Serialization",e.getMessage());
            e.printStackTrace();
        }

    }
}
package tr.edu.yildiz.proje17011016;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class CekmeceActivity extends AppCompatActivity {

    Button ekle;
    RecyclerView recyclerView;
    public RecyclerCekmeceAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cekmece);

        ekle = findViewById(R.id.button7);
        recyclerView = findViewById(R.id.recyclerview2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerCekmeceAdapter(MainActivity.cekmeceList.size());
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.notifyDataSetChanged();



    }
    public void ekle(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Onay");
        builder.setMessage("Yeni çekmece eklemek istediğinizden emin misiniz?");

        builder.setPositiveButton("| Evet |", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                Cekmece cekmece = new Cekmece();
                MainActivity.cekmeceList.add(cekmece);
                finish();
                Intent intent = new Intent(view.getContext(), CekmeceActivity.class);
                startActivity(intent);
                Toast toast = Toast.makeText(getApplicationContext(), "Yeni çekmece eklendi.", Toast.LENGTH_SHORT);
                toast.show();


            }
        });

        builder.setNegativeButton("| Hayır |", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

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
        //        System.out.println("sign up yazma kısmı"+i.getEmail());

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
                //        System.out.println("sign up yazma kısmı"+i.getEmail());

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
                //        System.out.println("sign up yazma kısmı"+i.getEmail());

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
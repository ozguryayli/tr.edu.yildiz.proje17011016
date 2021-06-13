package tr.edu.yildiz.proje17011016;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class EtkinlikGuncelleActivity extends AppCompatActivity {
    TextView isim;
    TextView tur;
    TextView tarih;
    TextView lokasyon;
    ImageView basUstu;
    ImageView surat;
    ImageView ust;
    ImageView alt;
    ImageView ayak;
    Button kiyafet;
    Button guncelle;
    public static int ietkinlik;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etkinlik_guncelle);

        Intent intent = getIntent();
        ietkinlik=intent.getIntExtra("ietkinlik",0);


        isim = findViewById(R.id.editText1);
        tur = findViewById(R.id.editText2);
        tarih = findViewById(R.id.editText3);
        lokasyon = findViewById(R.id.editText4);
        basUstu = findViewById(R.id.imageView13);
        surat = findViewById(R.id.imageView14);
        ust = findViewById(R.id.imageView15);
        alt = findViewById(R.id.imageView16);
        ayak = findViewById(R.id.imageView17);
        guncelle = findViewById(R.id.button2);

        isim.setText(MainActivity.etkinlikList.get(ietkinlik).getIsim());
        tur.setText(MainActivity.etkinlikList.get(ietkinlik).getTur());
        tarih.setText(MainActivity.etkinlikList.get(ietkinlik).getTarih());
        lokasyon.setText(MainActivity.etkinlikList.get(ietkinlik).getLokasyon());
        byte[] decodedString1 = Base64.decode(MainActivity.etkinlikList.get(ietkinlik).getBasUstu(), Base64.DEFAULT);
        Bitmap decodedByte1 = BitmapFactory.decodeByteArray(decodedString1, 0, decodedString1.length);
        basUstu.setImageBitmap(decodedByte1);
        byte[] decodedString2 = Base64.decode(MainActivity.etkinlikList.get(ietkinlik).getSurat(), Base64.DEFAULT);
        Bitmap decodedByte2 = BitmapFactory.decodeByteArray(decodedString2, 0, decodedString2.length);
        surat.setImageBitmap(decodedByte2);
        byte[] decodedString3 = Base64.decode(MainActivity.etkinlikList.get(ietkinlik).getUst(), Base64.DEFAULT);
        Bitmap decodedByte3 = BitmapFactory.decodeByteArray(decodedString3, 0, decodedString3.length);
        ust.setImageBitmap(decodedByte3);
        byte[] decodedString4 = Base64.decode(MainActivity.etkinlikList.get(ietkinlik).getAlt(), Base64.DEFAULT);
        Bitmap decodedByte4 = BitmapFactory.decodeByteArray(decodedString4, 0, decodedString4.length);
        alt.setImageBitmap(decodedByte4);
        byte[] decodedString5 = Base64.decode(MainActivity.etkinlikList.get(ietkinlik).getAyak(), Base64.DEFAULT);
        Bitmap decodedByte5 = BitmapFactory.decodeByteArray(decodedString5, 0, decodedString5.length);
        ayak.setImageBitmap(decodedByte5);

    }

    public void kombin1(View view){
        Intent intent = new Intent(this, CekmeceKombinActivity.class);
        intent.putExtra("parca",11);
        startActivity(intent);
        finish();
    }
    public void kombin2(View view){
        Intent intent = new Intent(this, CekmeceKombinActivity.class);
        intent.putExtra("parca",12);
        startActivity(intent);
        finish();

    }
    public void kombin3(View view){
        Intent intent = new Intent(this, CekmeceKombinActivity.class);
        intent.putExtra("parca",13);
        startActivity(intent);
        finish();

    }
    public void kombin4(View view){
        Intent intent = new Intent(this, CekmeceKombinActivity.class);
        intent.putExtra("parca",14);
        startActivity(intent);
        finish();

    }
    public void kombin5(View view){
        Intent intent = new Intent(this, CekmeceKombinActivity.class);
        intent.putExtra("parca",15);
        startActivity(intent);
        finish();

    }

    public void guncelle(View view){


        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Onay");
        builder.setMessage("Bu etkinliği güncellemek istediğinizden emin misiniz?");

        builder.setPositiveButton("| Evet |", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {


                Etkinlik etkinlik = new Etkinlik();


                etkinlik.setIsim(isim.getText().toString());
                etkinlik.setTur(tur.getText().toString());
                etkinlik.setTarih(tarih.getText().toString());
                etkinlik.setLokasyon(lokasyon.getText().toString());
                etkinlik.setBasUstu(MainActivity.etkinlikList.get(ietkinlik).getBasUstu());
                etkinlik.setSurat(MainActivity.etkinlikList.get(ietkinlik).getSurat());
                etkinlik.setUst(MainActivity.etkinlikList.get(ietkinlik).getUst());
                etkinlik.setAlt(MainActivity.etkinlikList.get(ietkinlik).getAlt());
                etkinlik.setAyak(MainActivity.etkinlikList.get(ietkinlik).getAyak());

                MainActivity.etkinlikList.set(ietkinlik,etkinlik);
                Intent intent = new Intent(view.getContext(),EtkinlikActivity.class);

                finish();
                startActivity(intent);
                Toast toast = Toast.makeText(getApplicationContext(), "Etkinlik güncellendi.", Toast.LENGTH_SHORT);
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
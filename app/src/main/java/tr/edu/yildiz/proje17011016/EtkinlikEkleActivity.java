package tr.edu.yildiz.proje17011016;

import android.annotation.SuppressLint;
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

public class EtkinlikEkleActivity extends AppCompatActivity {
    TextView isim;
    TextView tur;
    TextView tarih;
    TextView lokasyon;
    ImageView basUstu;
    ImageView surat;
    ImageView ust;
    ImageView alt;
    ImageView ayak;
    Button kaydet;

    public static Etkinlik etkinlik = new Etkinlik();
    public static Kombin kombin=new Kombin(" "," "," "," "," ");
    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etkinlik_ekle);
        isim = findViewById(R.id.editTextEisim2);
        tur = findViewById(R.id.editTextEtur2);
        tarih = findViewById(R.id.editTextEtarih2);
        lokasyon = findViewById(R.id.editTextElokasyon2);
        kaydet = findViewById(R.id.button4);
        basUstu = findViewById(R.id.imageView4);
        surat = findViewById(R.id.imageView12);
        ust = findViewById(R.id.imageView11);
        alt = findViewById(R.id.imageView9);
        ayak = findViewById(R.id.imageView10);



        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap photo = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.kiyafet);
        photo.compress(Bitmap.CompressFormat.PNG,50,stream);
        byte[] byteArray = stream.toByteArray();
        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
        Kombin k = new Kombin(encoded,encoded,encoded,encoded,encoded);
        if(EtkinlikEkleActivity.kombin.getImageBasUstu().equals(" ")){
            EtkinlikEkleActivity.kombin.setImageBasUstu(encoded);
        }
        if(EtkinlikEkleActivity.kombin.getImageSurat().equals(" ")){
            EtkinlikEkleActivity.kombin.setImageSurat(encoded);
        }
        if(EtkinlikEkleActivity.kombin.getImageUst().equals(" ")){
            EtkinlikEkleActivity.kombin.setImageUst(encoded);
        }
        if(EtkinlikEkleActivity.kombin.getImageAlt().equals(" ")){
            EtkinlikEkleActivity.kombin.setImageAlt(encoded);
        }
        if(EtkinlikEkleActivity.kombin.getImageAyak().equals(" ")){
            EtkinlikEkleActivity.kombin.setImageAyak(encoded);
        }




        byte[] decodedString = Base64.decode(EtkinlikEkleActivity.kombin.getImageBasUstu(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        basUstu.setImageBitmap(decodedByte);
        byte[] decodedString2 = Base64.decode(EtkinlikEkleActivity.kombin.getImageSurat(), Base64.DEFAULT);
        Bitmap decodedByte2 = BitmapFactory.decodeByteArray(decodedString2, 0, decodedString2.length);
        surat.setImageBitmap(decodedByte2);
        byte[] decodedString3 = Base64.decode(EtkinlikEkleActivity.kombin.getImageUst(), Base64.DEFAULT);
        Bitmap decodedByte3 = BitmapFactory.decodeByteArray(decodedString3, 0, decodedString3.length);
        ust.setImageBitmap(decodedByte3);
        byte[] decodedString4 = Base64.decode(EtkinlikEkleActivity.kombin.getImageAlt(), Base64.DEFAULT);
        Bitmap decodedByte4 = BitmapFactory.decodeByteArray(decodedString4, 0, decodedString4.length);
        alt.setImageBitmap(decodedByte4);
        byte[] decodedString5 = Base64.decode(EtkinlikEkleActivity.kombin.getImageAyak(), Base64.DEFAULT);
        Bitmap decodedByte5 = BitmapFactory.decodeByteArray(decodedString5, 0, decodedString5.length);
        ayak.setImageBitmap(decodedByte5);
    }
    public void kombin1(View view){
        Intent intent = new Intent(this, CekmeceKombinActivity.class);
        intent.putExtra("parca",6);
        startActivity(intent);
        finish();
    }
    public void kombin2(View view){
        Intent intent = new Intent(this, CekmeceKombinActivity.class);
        intent.putExtra("parca",7);
        startActivity(intent);
        finish();

    }
    public void kombin3(View view){
        Intent intent = new Intent(this, CekmeceKombinActivity.class);
        intent.putExtra("parca",8);
        startActivity(intent);
        finish();

    }
    public void kombin4(View view){
        Intent intent = new Intent(this, CekmeceKombinActivity.class);
        intent.putExtra("parca",9);
        startActivity(intent);
        finish();

    }
    public void kombin5(View view){
        Intent intent = new Intent(this, CekmeceKombinActivity.class);
        intent.putExtra("parca",10);
        startActivity(intent);
        finish();

    }

    public void etkinlikKayit(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Onay");
        builder.setMessage("Bu ürünü eklemek istediğinizden emin misiniz?");

        builder.setPositiveButton("| Evet |", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                Etkinlik etkinlik = new Etkinlik();
                etkinlik.setIsim(isim.getText().toString());
                etkinlik.setTur(tur.getText().toString());
                etkinlik.setTarih(tarih.getText().toString());
                etkinlik.setLokasyon(lokasyon.getText().toString());
                etkinlik.setBasUstu(EtkinlikEkleActivity.kombin.getImageBasUstu());
                etkinlik.setSurat(EtkinlikEkleActivity.kombin.getImageSurat());
                etkinlik.setUst(EtkinlikEkleActivity.kombin.getImageUst());
                etkinlik.setAlt(EtkinlikEkleActivity.kombin.getImageAlt());
                etkinlik.setAyak(EtkinlikEkleActivity.kombin.getImageAyak());


                MainActivity.etkinlikList.add(etkinlik);

                Intent intent = new Intent(view.getContext(),EtkinlikActivity.class);
                finish();
                startActivity(intent);
                Toast toast = Toast.makeText(getApplicationContext(), "Yeni etkinlik eklendi.", Toast.LENGTH_SHORT);
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
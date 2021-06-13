package tr.edu.yildiz.proje17011016;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class KombinGorActivity extends AppCompatActivity {

    ImageView basUstu;
    ImageView surat;
    ImageView ust;
    ImageView alt;
    ImageView ayak;
    Button ekle;
    Button paylas;
    Bitmap ekranGoruntusu;

    public static Kombin kombin=new Kombin(" "," "," "," "," ");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kombin_gor);
        basUstu = findViewById(R.id.imageViewBasUstu);
        surat = findViewById(R.id.imageViewSurat);
        ust = findViewById(R.id.imageViewUst);
        alt = findViewById(R.id.imageViewAlt);
        ayak = findViewById(R.id.imageViewAyak);
        ekle = findViewById(R.id.buttonKombinEkle);
        paylas = findViewById(R.id.button10);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap photo = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.kiyafet);
        photo.compress(Bitmap.CompressFormat.PNG,50,stream);
        byte[] byteArray = stream.toByteArray();
        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
        Kombin k = new Kombin(encoded,encoded,encoded,encoded,encoded);
        if(kombin.getImageBasUstu().equals(" ")){
            kombin.setImageBasUstu(encoded);
        }
        if(kombin.getImageSurat().equals(" ")){
            kombin.setImageSurat(encoded);
        }
        if(kombin.getImageUst().equals(" ")){
            kombin.setImageUst(encoded);
        }
        if(kombin.getImageAlt().equals(" ")){
            kombin.setImageAlt(encoded);
        }
        if(kombin.getImageAyak().equals(" ")){
            kombin.setImageAyak(encoded);
        }




        byte[] decodedString = Base64.decode(kombin.getImageBasUstu(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        basUstu.setImageBitmap(decodedByte);
        byte[] decodedString2 = Base64.decode(kombin.getImageSurat(), Base64.DEFAULT);
        Bitmap decodedByte2 = BitmapFactory.decodeByteArray(decodedString2, 0, decodedString2.length);
        surat.setImageBitmap(decodedByte2);
        byte[] decodedString3 = Base64.decode(kombin.getImageUst(), Base64.DEFAULT);
        Bitmap decodedByte3 = BitmapFactory.decodeByteArray(decodedString3, 0, decodedString3.length);
        ust.setImageBitmap(decodedByte3);
        byte[] decodedString4 = Base64.decode(kombin.getImageAlt(), Base64.DEFAULT);
        Bitmap decodedByte4 = BitmapFactory.decodeByteArray(decodedString4, 0, decodedString4.length);
        alt.setImageBitmap(decodedByte4);
        byte[] decodedString5 = Base64.decode(kombin.getImageAyak(), Base64.DEFAULT);
        Bitmap decodedByte5 = BitmapFactory.decodeByteArray(decodedString5, 0, decodedString5.length);
        ayak.setImageBitmap(decodedByte5);


    }

    public void kombin1(View view){
        Intent intent = new Intent(this, CekmeceKombinActivity.class);
        intent.putExtra("parca",1);
        startActivity(intent);
        finish();
    }
    public void kombin2(View view){
        Intent intent = new Intent(this, CekmeceKombinActivity.class);
        intent.putExtra("parca",2);
        startActivity(intent);
        finish();

    }
    public void kombin3(View view){
        Intent intent = new Intent(this, CekmeceKombinActivity.class);
        intent.putExtra("parca",3);
        startActivity(intent);
        finish();

    }
    public void kombin4(View view){
        Intent intent = new Intent(this, CekmeceKombinActivity.class);
        intent.putExtra("parca",4);
        startActivity(intent);
        finish();

    }
    public void kombin5(View view){
        Intent intent = new Intent(this, CekmeceKombinActivity.class);
        intent.putExtra("parca",5);
        startActivity(intent);
        finish();

    }
    public void kombinKaydet(View view){





        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

        builder.setTitle("Onay");
        builder.setMessage("Bu kombinin kaydetmek istediğinizden emin misiniz?");

        builder.setPositiveButton("| Evet |", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                MainActivity.kombinList.add(kombin);
                Toast toast = Toast.makeText(view.getContext(), "Kombin kaydedildi.", Toast.LENGTH_SHORT);
                toast.show();
                finish();


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


    public  void kombiniPaylas(View view) {


        ekranGoruntusu=takescreenshotOfRootView(view);
        Uri imageInternalUri;

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        File f =  new File(getExternalCacheDir()+"/"+getResources().getString(R.string.app_name)+".png");

        Intent shareIntent;



        try {
            FileOutputStream outputStream = new FileOutputStream(f);
            ekranGoruntusu.compress(Bitmap.CompressFormat.PNG,100,outputStream);

            outputStream.flush();
            outputStream.close();
            shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("image/*");
            shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
            shareIntent.putExtra(Intent.EXTRA_TEXT,"Merhaba, kombinim nasıl olmuş? :)");
            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


        }catch (Exception e){
            throw new RuntimeException(e);
        }
        startActivity(Intent.createChooser(shareIntent,"Kombini paylaş..."));


    }



    public  Bitmap takescreenshot(View v) {
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache(true);
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        return b;
    }

    public Bitmap takescreenshotOfRootView(View v) {
        return takescreenshot(v.getRootView());
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
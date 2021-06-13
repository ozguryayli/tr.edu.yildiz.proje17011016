package tr.edu.yildiz.proje17011016;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class KiyafetEkleActivity extends AppCompatActivity {

    EditText textTur;
    EditText textRenk;
    EditText textDesen;
    EditText textTarih;
    EditText textFiyat;
    ImageView imageView;
    Button button;
    Bitmap selectedImage;
    int icekmece;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiyafet_ekle);
        Intent intent = getIntent();
        icekmece=intent.getIntExtra("icekmece",0);

        textTur = findViewById(R.id.editTextTur);
        textRenk = findViewById(R.id.editTextRenk);
        textDesen = findViewById(R.id.editTextDesen);
        textTarih = findViewById(R.id.editTextTarih);
        textFiyat = findViewById(R.id.editTextFiyat);
        imageView = findViewById(R.id.imageView2);
        button = findViewById(R.id.buttonEkle2);
    }


    public void selectedImage(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }
        else{
            Intent intentGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intentGallery,2);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intentGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentGallery,2);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2 && resultCode == RESULT_OK && data != null){
            Uri dataOfImage = data.getData();
            try {

                if(Build.VERSION.SDK_INT >= 28){
                    ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(),dataOfImage);
                    selectedImage = ImageDecoder.decodeBitmap(source);
                    imageView.setImageBitmap(selectedImage);
                }
                else{
                    selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(),dataOfImage);
                    imageView.setImageBitmap(selectedImage);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
    public void kiyafetEkle(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Onay");
        builder.setMessage("Bu ürünü eklemek istediğinizden emin misiniz?");

        builder.setPositiveButton("| Evet |", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {


                Kiyafet kiyafet =new Kiyafet();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                if(selectedImage!= null){
                    selectedImage.compress(Bitmap.CompressFormat.PNG, 50, stream);
                }
                else{
                    Bitmap photo = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.kiyafet);
                    photo.compress(Bitmap.CompressFormat.PNG,50,stream);
                }
                byte[] byteArray = stream.toByteArray();
                String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                kiyafet.setImage(encoded);

                kiyafet.setTur(textTur.getText().toString());
                kiyafet.setDesen(textDesen.getText().toString());
                kiyafet.setFiyat(textFiyat.getText().toString());
                kiyafet.setRenk(textRenk.getText().toString());
                kiyafet.setTarih(textTarih.getText().toString());

                MainActivity.cekmeceList.get(icekmece).getkiyafetList().add(kiyafet);
                Intent intent = new Intent(view.getContext(),KiyafetActivity.class);
                intent.putExtra("icekmece",icekmece);
                finish();
                startActivity(intent);
                Toast toast = Toast.makeText(getApplicationContext(), "Yeni kıyafet eklendi.", Toast.LENGTH_SHORT);
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
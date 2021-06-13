package tr.edu.yildiz.proje17011016;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RecyclerEtkinlikAdapter extends RecyclerView.Adapter<RecyclerEtkinlikAdapter.Eholder> {
    public int size;

    public RecyclerEtkinlikAdapter(int size) {
        this.size = size;
    }

    @NonNull
    @Override
    public Eholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_etkinlik_row, viewGroup, false);
        return new Eholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Eholder eholder, int i) {
        int a=i;
        eholder.isim.setText(MainActivity.etkinlikList.get(a).getIsim());
        eholder.tur.setText(MainActivity.etkinlikList.get(a).getTur());
        eholder.tarih.setText(MainActivity.etkinlikList.get(a).getTarih());
        eholder.lokasyon.setText(MainActivity.etkinlikList.get(a).getLokasyon());

        byte[] decodedString1 = Base64.decode(MainActivity.etkinlikList.get(a).getBasUstu(), Base64.DEFAULT);
        Bitmap decodedByte1 = BitmapFactory.decodeByteArray(decodedString1, 0, decodedString1.length);
        eholder.basUstu.setImageBitmap(decodedByte1);
        byte[] decodedString2 = Base64.decode(MainActivity.etkinlikList.get(a).getSurat(), Base64.DEFAULT);
        Bitmap decodedByte2 = BitmapFactory.decodeByteArray(decodedString2, 0, decodedString2.length);
        eholder.surat.setImageBitmap(decodedByte2);
        byte[] decodedString3 = Base64.decode(MainActivity.etkinlikList.get(a).getUst(), Base64.DEFAULT);
        Bitmap decodedByte3 = BitmapFactory.decodeByteArray(decodedString3, 0, decodedString3.length);
        eholder.ust.setImageBitmap(decodedByte3);
        byte[] decodedString4 = Base64.decode(MainActivity.etkinlikList.get(a).getAlt(), Base64.DEFAULT);
        Bitmap decodedByte4 = BitmapFactory.decodeByteArray(decodedString4, 0, decodedString4.length);
        eholder.alt.setImageBitmap(decodedByte4);
        byte[] decodedString5 = Base64.decode(MainActivity.etkinlikList.get(a).getAyak(), Base64.DEFAULT);
        Bitmap decodedByte5 = BitmapFactory.decodeByteArray(decodedString5, 0, decodedString5.length);
        eholder.ayak.setImageBitmap(decodedByte5);

        eholder.sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                builder.setTitle("Onay");
                builder.setMessage("Bu etkinliği silmek istediğinizden emin misiniz?");

                builder.setPositiveButton("| Evet |", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        MainActivity.etkinlikList.remove(a);

                        ((EtkinlikActivity)v.getContext()).finish();
                        Intent intent = new Intent(v.getContext(), EtkinlikActivity.class);
                        v.getContext().startActivity(intent);
                        Toast toast = Toast.makeText(v.getContext(), "Etkinlik silindi..", Toast.LENGTH_SHORT);
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
        });

        eholder.guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EtkinlikActivity)v.getContext()).finish();
                Intent intent = new Intent(v.getContext(), EtkinlikGuncelleActivity.class);
                intent.putExtra("ietkinlik",a);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return size;
    }

    public class Eholder extends RecyclerView.ViewHolder{
        TextView isim;
        TextView tur;
        TextView tarih;
        TextView lokasyon;
        ImageView basUstu;
        ImageView surat;
        ImageView ust;
        ImageView alt;
        ImageView ayak;
        Button sil;
        Button guncelle;



        public Eholder(@NonNull View itemView) {
            super(itemView);

            isim = itemView.findViewById(R.id.textView22);
            tur = itemView.findViewById(R.id.textView21);
            tarih = itemView.findViewById(R.id.textView20);
            lokasyon = itemView.findViewById(R.id.textView19);
            sil = itemView.findViewById(R.id.button5);
            guncelle = itemView.findViewById(R.id.button6);
            basUstu = itemView.findViewById(R.id.imageView);
            surat = itemView.findViewById(R.id.imageView5);
            ust = itemView.findViewById(R.id.imageView6);
            alt = itemView.findViewById(R.id.imageView7);
            ayak = itemView.findViewById(R.id.imageView8);




        }
    }
}

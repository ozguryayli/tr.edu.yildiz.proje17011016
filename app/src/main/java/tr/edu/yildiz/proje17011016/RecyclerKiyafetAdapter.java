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

public class RecyclerKiyafetAdapter extends RecyclerView.Adapter<RecyclerKiyafetAdapter.ViewHolder> {

    public int size;
    public int icekmece;

    public RecyclerKiyafetAdapter(int size, int icekmece) {

        this.size = size;
        this.icekmece = icekmece;

    }

    @NonNull
    @Override
    public RecyclerKiyafetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_kiyafet_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerKiyafetAdapter.ViewHolder viewHolder, int i) {
        int a=i;

        viewHolder.textTur.setText(MainActivity.cekmeceList.get(icekmece).getkiyafetList().get(a).getTur());
        viewHolder.textFiyat.setText(MainActivity.cekmeceList.get(icekmece).getkiyafetList().get(a).getFiyat());
        viewHolder.textTarih.setText(MainActivity.cekmeceList.get(icekmece).getkiyafetList().get(a).getTarih());
        viewHolder.textDesen.setText(MainActivity.cekmeceList.get(icekmece).getkiyafetList().get(a).getDesen());
        viewHolder.textRenk.setText(MainActivity.cekmeceList.get(icekmece).getkiyafetList().get(a).getRenk());



        byte[] decodedString = Base64.decode(MainActivity.cekmeceList.get(icekmece).getkiyafetList().get(a).getImage(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        viewHolder.imageKiyafet.setImageBitmap(decodedByte);



        viewHolder.buttonGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((KiyafetActivity)v.getContext()).finish();
                Intent intent = new Intent(v.getContext(), KiyafetGuncelleActivity.class);
                intent.putExtra("icekmece",icekmece);
                intent.putExtra("ikiyafet",a);
                v.getContext().startActivity(intent);

            }
        });

        viewHolder.buttonSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                builder.setTitle("Onay");
                builder.setMessage("Bu ürünü silmek istediğinizden emin misiniz?");

                builder.setPositiveButton("| Evet |", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        MainActivity.cekmeceList.get(icekmece).getkiyafetList().remove(a);



                        ((KiyafetActivity)v.getContext()).finish();
                        Intent intent = new Intent(v.getContext(), KiyafetActivity.class);
                        intent.putExtra("icekmece",icekmece);
                        v.getContext().startActivity(intent);
                        Toast toast = Toast.makeText(v.getContext(), "Kıyafet silindi..", Toast.LENGTH_SHORT);
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



    }

    @Override
    public int getItemCount() {
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTur;
        TextView textRenk;
        TextView textDesen;
        TextView textTarih;
        TextView textFiyat;
        Button buttonSil;

        Button buttonGuncelle;
        ImageView imageKiyafet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTur = itemView.findViewById(R.id.textTur);
            textRenk = itemView.findViewById(R.id.textRenk);
            textDesen = itemView.findViewById(R.id.textDesen);
            textTarih = itemView.findViewById(R.id.textTarih);
            textFiyat = itemView.findViewById(R.id.textFiyat);
            buttonSil = itemView.findViewById(R.id.buttonKiyafetSil);

            buttonGuncelle = itemView.findViewById(R.id.buttonGuncelle);
            imageKiyafet = itemView.findViewById(R.id.imageKiyafet);
        }
    }
}
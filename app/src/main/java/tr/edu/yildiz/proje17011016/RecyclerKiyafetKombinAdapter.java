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
import android.widget.ImageView;
import android.widget.Toast;

import static tr.edu.yildiz.proje17011016.EtkinlikGuncelleActivity.ietkinlik;

public class RecyclerKiyafetKombinAdapter extends RecyclerView.Adapter<RecyclerKiyafetKombinAdapter.MyHolder> {
    public int size;
    public int icekmece;
    public int parca;

    public RecyclerKiyafetKombinAdapter(int size, int icekmece, int parca) {
        this.size = size;
        this.icekmece = icekmece;
        this.parca = parca;
    }

    @NonNull
    @Override
    public RecyclerKiyafetKombinAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_kiyafet_kombin_row, viewGroup,false);
        return new RecyclerKiyafetKombinAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerKiyafetKombinAdapter.MyHolder myholder, int i) {
        int a=i;
        byte[] decodedString = Base64.decode(MainActivity.cekmeceList.get(icekmece).getkiyafetList().get(a).getImage(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        myholder.image.setImageBitmap(decodedByte);

        myholder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                builder.setTitle("Onay");
                builder.setMessage("Seçtiğiniz kıyafet kombine eklensin mi?");

                builder.setPositiveButton("| Evet |", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        if(parca==1){
                            KombinGorActivity.kombin.setImageBasUstu(MainActivity.cekmeceList.get(icekmece).getkiyafetList().get(a).getImage());
                        }
                        else if(parca==2){
                            KombinGorActivity.kombin.setImageSurat(MainActivity.cekmeceList.get(icekmece).getkiyafetList().get(a).getImage());
                        }
                        else if(parca==3){
                            KombinGorActivity.kombin.setImageUst(MainActivity.cekmeceList.get(icekmece).getkiyafetList().get(a).getImage());
                        }
                        else if(parca==4){
                            KombinGorActivity.kombin.setImageAlt(MainActivity.cekmeceList.get(icekmece).getkiyafetList().get(a).getImage());
                        }
                        else if(parca==5){
                            KombinGorActivity.kombin.setImageAyak(MainActivity.cekmeceList.get(icekmece).getkiyafetList().get(a).getImage());
                        }
                        else if(parca==6){
                            EtkinlikEkleActivity.kombin.setImageBasUstu(MainActivity.cekmeceList.get(icekmece).getkiyafetList().get(a).getImage());
                        }
                        else if(parca==7){
                            EtkinlikEkleActivity.kombin.setImageSurat(MainActivity.cekmeceList.get(icekmece).getkiyafetList().get(a).getImage());
                        }
                        else if(parca==8){
                            EtkinlikEkleActivity.kombin.setImageUst(MainActivity.cekmeceList.get(icekmece).getkiyafetList().get(a).getImage());
                        }
                        else if(parca==9){
                            EtkinlikEkleActivity.kombin.setImageAlt(MainActivity.cekmeceList.get(icekmece).getkiyafetList().get(a).getImage());
                        }
                        else if(parca==10){
                            EtkinlikEkleActivity.kombin.setImageAyak(MainActivity.cekmeceList.get(icekmece).getkiyafetList().get(a).getImage());
                        }
                        else if(parca==11){
                            MainActivity.etkinlikList.get(ietkinlik).setBasUstu(MainActivity.cekmeceList.get(icekmece).getkiyafetList().get(a).getImage());
                        }
                        else if(parca==12){
                            MainActivity.etkinlikList.get(ietkinlik).setSurat(MainActivity.cekmeceList.get(icekmece).getkiyafetList().get(a).getImage());
                        }
                        else if(parca==13){
                            MainActivity.etkinlikList.get(ietkinlik).setUst(MainActivity.cekmeceList.get(icekmece).getkiyafetList().get(a).getImage());
                        }
                        else if(parca==14){
                            MainActivity.etkinlikList.get(ietkinlik).setAlt(MainActivity.cekmeceList.get(icekmece).getkiyafetList().get(a).getImage());
                        }
                        else if(parca==15){
                            MainActivity.etkinlikList.get(ietkinlik).setAyak(MainActivity.cekmeceList.get(icekmece).getkiyafetList().get(a).getImage());
                        }




                        if(parca==1 || parca==2 || parca==3 || parca==4 || parca==5){
                            Intent intent = new Intent(v.getContext(), KombinGorActivity.class);
                            v.getContext().startActivity(intent);
                            Toast toast = Toast.makeText(v.getContext(), "Kıyafet kombine eklendi.", Toast.LENGTH_SHORT);
                            toast.show();
                            ((KiyafetKombinActivity)v.getContext()).finish();
                        }
                        else if(parca==6 || parca==7 || parca==8 || parca==9 || parca==10){
                            Intent intent = new Intent(v.getContext(), EtkinlikEkleActivity.class);
                            v.getContext().startActivity(intent);
                            Toast toast = Toast.makeText(v.getContext(), "Kıyafet etkinliğe eklendi.", Toast.LENGTH_SHORT);
                            toast.show();
                            ((KiyafetKombinActivity)v.getContext()).finish();
                        }
                        else{
                            Intent intent = new Intent(v.getContext(), EtkinlikGuncelleActivity.class);
                            intent.putExtra("ietkinlik", ietkinlik);
                            v.getContext().startActivity(intent);
                            Toast toast = Toast.makeText(v.getContext(), "Kıyafet etkinlikte güncellendi.", Toast.LENGTH_SHORT);
                            toast.show();
                            ((KiyafetKombinActivity)v.getContext()).finish();
                        }

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

    public class MyHolder extends RecyclerView.ViewHolder{
        ImageView image;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageViewKombin);

        }
    }
}

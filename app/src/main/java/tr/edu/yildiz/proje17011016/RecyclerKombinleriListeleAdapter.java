package tr.edu.yildiz.proje17011016;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class RecyclerKombinleriListeleAdapter extends RecyclerView.Adapter<RecyclerKombinleriListeleAdapter.KombinlerHolder> {

    public int size;

    public RecyclerKombinleriListeleAdapter(int size) {
        this.size = size;
    }

    @NonNull
    @Override
    public RecyclerKombinleriListeleAdapter.KombinlerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_kombinleri_listele_row, viewGroup,false);
        return new RecyclerKombinleriListeleAdapter.KombinlerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerKombinleriListeleAdapter.KombinlerHolder kombinlerHolder, int i) {
        int a=i;
        byte[] decodedString1 = Base64.decode(MainActivity.kombinList.get(a).getImageBasUstu(), Base64.DEFAULT);
        Bitmap decodedByte1 = BitmapFactory.decodeByteArray(decodedString1, 0, decodedString1.length);
        kombinlerHolder.image1.setImageBitmap(decodedByte1);
        byte[] decodedString2 = Base64.decode(MainActivity.kombinList.get(a).getImageSurat(), Base64.DEFAULT);
        Bitmap decodedByte2 = BitmapFactory.decodeByteArray(decodedString2, 0, decodedString2.length);
        kombinlerHolder.image2.setImageBitmap(decodedByte2);
        byte[] decodedString3 = Base64.decode(MainActivity.kombinList.get(a).getImageUst(), Base64.DEFAULT);
        Bitmap decodedByte3 = BitmapFactory.decodeByteArray(decodedString3, 0, decodedString3.length);
        kombinlerHolder.image3.setImageBitmap(decodedByte3);
        byte[] decodedString4 = Base64.decode(MainActivity.kombinList.get(a).getImageAlt(), Base64.DEFAULT);
        Bitmap decodedByte4 = BitmapFactory.decodeByteArray(decodedString4, 0, decodedString4.length);
        kombinlerHolder.image4.setImageBitmap(decodedByte4);
        byte[] decodedString5 = Base64.decode(MainActivity.kombinList.get(a).getImageAyak(), Base64.DEFAULT);
        Bitmap decodedByte5 = BitmapFactory.decodeByteArray(decodedString5, 0, decodedString5.length);
        kombinlerHolder.image5.setImageBitmap(decodedByte5);

        kombinlerHolder.silButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                builder.setTitle("Onay");
                builder.setMessage("Seçtiğiniz kombini silmek istediğinizden emin misiniz?");

                builder.setPositiveButton("| Evet |", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        MainActivity.kombinList.remove(a);
                        ((KombinleriListeleActivity)v.getContext()).finish();
                        Intent intent = new Intent(v.getContext(), KombinleriListeleActivity.class);
                        v.getContext().startActivity(intent);
                        Toast toast = Toast.makeText(v.getContext(), "Kombin silindi.", Toast.LENGTH_SHORT);
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

    public class KombinlerHolder extends RecyclerView.ViewHolder{
        ImageView image1;
        ImageView image2;
        ImageView image3;
        ImageView image4;
        ImageView image5;
        Button silButton;
        Button paylas;

        public KombinlerHolder(@NonNull View itemView) {
            super(itemView);
            image1=itemView.findViewById(R.id.imageViewK1);
            image2=itemView.findViewById(R.id.imageViewK2);
            image3=itemView.findViewById(R.id.imageViewK3);
            image4=itemView.findViewById(R.id.imageViewK4);
            image5=itemView.findViewById(R.id.imageViewK5);
            silButton=itemView.findViewById(R.id.buttonKombinSil);


        }
    }
}

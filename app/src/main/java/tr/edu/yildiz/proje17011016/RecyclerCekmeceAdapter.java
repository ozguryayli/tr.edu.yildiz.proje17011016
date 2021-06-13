package tr.edu.yildiz.proje17011016;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RecyclerCekmeceAdapter extends RecyclerView.Adapter<RecyclerCekmeceAdapter.MyViewHolder> {

    public int size;

    public RecyclerCekmeceAdapter(int size) {
        this.size = size;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_cekmece_row, viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        int a=i;
        myViewHolder.cekmeceAdi.setText("Çekmece No : " + String.valueOf(a) );


        myViewHolder.ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), KiyafetActivity.class);
                intent.putExtra("icekmece",a);
                v.getContext().startActivity(intent);

            }
        });

        myViewHolder.sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                builder.setTitle("Onay");
                builder.setMessage(String.valueOf(a) + " nolu çekmeceyi silmek istediğinizden emin misiniz?");

                builder.setPositiveButton("| Evet |", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {


                        MainActivity.cekmeceList.remove(a);
                        ((CekmeceActivity)v.getContext()).finish();
                        Intent intent = new Intent(v.getContext(), CekmeceActivity.class);
                        v.getContext().startActivity(intent);
                        Toast toast = Toast.makeText(v.getContext(), "Çekmece silindi.", Toast.LENGTH_SHORT);
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


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView cekmeceAdi;
        Button sil;

        Button ac;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cekmeceAdi = itemView.findViewById(R.id.textCekmeceAdi);
            sil = itemView.findViewById(R.id.buttonSil);

            ac = itemView.findViewById(R.id.buttonAc);
        }
    }
}





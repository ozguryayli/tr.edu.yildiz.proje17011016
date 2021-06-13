package tr.edu.yildiz.proje17011016;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class RecyclerCekmeceKombinAdapter extends RecyclerView.Adapter<RecyclerCekmeceKombinAdapter.Holder> {
    
    public int size;
    public int parca;

    public RecyclerCekmeceKombinAdapter(int size, int parca) {
        this.size = size;
        this.parca = parca;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_cekmece_kombin_row, viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerCekmeceKombinAdapter.Holder holder, int i) {
        int a=i;
        holder.no.setText("Çekmece No : " + String.valueOf(a));
        
        holder.ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CekmeceKombinActivity)v.getContext()).finish();
                Intent intent = new Intent(v.getContext(), KiyafetKombinActivity.class);
                intent.putExtra("icekmece",a);
                intent.putExtra("parca",parca);
                v.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return size;
    }
    
    public class Holder extends RecyclerView.ViewHolder{
        TextView no;
        Button ac;


        public Holder(@NonNull View itemView) {
            super(itemView);
            ac = itemView.findViewById(R.id.buttonAcc);
            no = itemView.findViewById(R.id.textViewNo);
        }
    }
}

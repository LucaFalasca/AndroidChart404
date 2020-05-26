package it.qzeroq.androidchart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import it.qzeroq.androidchart.R;

public class InsertDataAdapter extends RecyclerView.Adapter<InsertDataAdapter.Holder> implements View.OnClickListener {

    private Context context;
    private int c;
    private List<Holder> holders;

    public InsertDataAdapter(Context context){
        this.context = context;
        c = 1;
        holders = new ArrayList<>();
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_view_insert_data, parent, false);
        constraintLayout.setOnClickListener(this);

        return new Holder(constraintLayout);
    }


    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holders.add(holder);
    }


    @Override
    public int getItemCount() {
        return c;
    }


    public void addCard(){
        c++;
        notifyItemInserted(c);
    }


    @Override
    public void onClick(View v) {
        int position = ((RecyclerView) v.getParent()).getChildAdapterPosition(v);
    }

    public String[] getNames(){
        String[] values = new String[c];
        for(int i = 0; i < c; i++){
            values[i] = holders.get(i).etNameLine.getText().toString();
        }
        return values;
    }

    public String[] getXAxis(){
        String[] values = new String[c];
        for(int i = 0; i < c; i++){
            values[i] = holders.get(i).etXAxis.getText().toString();
        }
        return values;
    }

    public String[] getYAxis(){
        String[] values = new String[c];
        for(int i = 0; i < c; i++){
            values[i] = holders.get(i).etYAxis.getText().toString();
        }
        return values;
    }

    static class Holder extends RecyclerView.ViewHolder {

        final TextView tvNameLine, tvXAxis, tvYAxis;
        final EditText etNameLine, etXAxis, etYAxis;

        Holder(@NonNull View itemView) {
            super(itemView);
            tvNameLine = itemView.findViewById(R.id.tvNameLine);
            tvXAxis = itemView.findViewById(R.id.tvXAxis);
            tvYAxis = itemView.findViewById(R.id.tvYAxis);
            etNameLine = itemView.findViewById(R.id.etNameLine);
            etXAxis = itemView.findViewById(R.id.etXAxis);
            etYAxis = itemView.findViewById(R.id.etYAxis);

        }
    }
}

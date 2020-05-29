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

public class PieInsertDataAdapter extends RecyclerView.Adapter<PieInsertDataAdapter.Holder> implements View.OnClickListener {
    private Context context;
    private int c;
    private List<PieInsertDataAdapter.Holder> holders;

    public PieInsertDataAdapter(Context context) {
        this.context = context;
        c = 1;
        holders = new ArrayList<>();
    }

    @NonNull
    @Override
    public PieInsertDataAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_view_pie_insert_data, parent, false);
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
            values[i] = holders.get(i).etSliceName.getText().toString();
        }
        return values;
    }

    public String[] getvaluesSlice(){
        String[] values = new String[c];
        for(int i = 0; i < c; i++){
            values[i] = holders.get(i).etSliveValue.getText().toString();
        }
        return values;
    }

    static class Holder extends RecyclerView.ViewHolder{

        final TextView tvSliceName, tvSliceValue;
        final EditText etSliceName, etSliveValue;


        Holder(@NonNull View itemView) {
            super(itemView);

            tvSliceName = itemView.findViewById(R.id.tvSliceName);
            tvSliceValue = itemView.findViewById(R.id.tvValueSlice);
            etSliceName = itemView.findViewById(R.id.etSliceName);
            etSliveValue = itemView.findViewById(R.id.etValueSlice);

        }

    }
}

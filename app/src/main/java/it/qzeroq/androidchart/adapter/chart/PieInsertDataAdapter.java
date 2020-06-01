package it.qzeroq.androidchart.adapter.chart;

import android.annotation.SuppressLint;
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

public class PieInsertDataAdapter extends RecyclerView.Adapter<PieInsertDataAdapter.Holder>{
    private int c;
    private List<Holder> holders;

    public PieInsertDataAdapter() {
        c = 1;
        holders = new ArrayList<>();
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating the layout for insertion of the PieChart data
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_view_pie_insert_data, parent, false);

        return new Holder(constraintLayout);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holders.add(holder);
        holder.etSliceName.setText(holder.etSliceName.getHint() + " " + (position + 1));
    }


    @Override
    public int getItemCount() {
        return c;
    }


    public void addCard() {
        //item counter increment and adding a CardView for the insertion of PieChart data
        c++;
        notifyItemInserted(c);
    }


    public String[] getNames() {
        //getting slice names from holders and putting them into an array of strings
        String[] values = new String[c];
        for(int i = 0; i < c; i++) {
            values[i] = holders.get(i).etSliceName.getText().toString();
        }
        return values;
    }


    public String[] getvaluesSlice() {
        //getting slices values and putting them into an array of strings
        String[] values = new String[c];
        for(int i = 0; i < c; i++) {
            values[i] = holders.get(i).etSliveValue.getText().toString();
        }
        return values;
    }


    static class Holder extends RecyclerView.ViewHolder {

        final TextView tvSliceName, tvSliceValue;
        final EditText etSliceName, etSliveValue;


        Holder(@NonNull View itemView) {
            super(itemView);
            //attaching views by their id
            tvSliceName = itemView.findViewById(R.id.tvSliceName);
            tvSliceValue = itemView.findViewById(R.id.tvValueSlice);
            etSliceName = itemView.findViewById(R.id.etSliceName);
            etSliveValue = itemView.findViewById(R.id.etValueSlice);
        }
    }
}

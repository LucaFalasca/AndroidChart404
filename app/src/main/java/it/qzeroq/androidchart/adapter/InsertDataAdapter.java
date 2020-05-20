package it.qzeroq.androidchart.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.qzeroq.androidchart.R;
import it.qzeroq.androidchart.activities.InsertDataActivity;

public class InsertDataAdapter extends RecyclerView.Adapter<ChartAdapter.Holder> implements View.OnClickListener {

    private Context context;
    private int c;

    public InsertDataAdapter(Context context){
        this.context = context;
        c = 1;
    }

    @NonNull
    @Override
    public ChartAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_insert_data, parent, false);
        constraintLayout.setOnClickListener(this);
        return new ChartAdapter.Holder(constraintLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull ChartAdapter.Holder holder, int position) {

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

    static class Holder extends RecyclerView.ViewHolder {

        Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

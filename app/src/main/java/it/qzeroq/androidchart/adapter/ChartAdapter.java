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
import it.qzeroq.androidchart.activities.LineChartActivity;

public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.Holder> implements View.OnClickListener {

    private Context context;
    private List<String> chartNameList;

    public ChartAdapter(Context context, List<String> chartNameList){
        this.chartNameList = chartNameList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_chart_selection, parent, false);
        constraintLayout.setOnClickListener(this);
        return new Holder(constraintLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.tvChart.setText(chartNameList.get(position));
    }

    @Override
    public int getItemCount() {
        return chartNameList.size();
    }

    @Override
    public void onClick(View v) {
        int position = ((RecyclerView) v.getParent()).getChildAdapterPosition(v);
        Intent intent = null;
        switch(position){
            case 0:
                intent = new Intent(context, InsertDataActivity.class);
                break;
            case 1:
                break;
        }
        context.startActivity(intent);
    }

    static class Holder extends RecyclerView.ViewHolder {

        private TextView tvChart;

        Holder(@NonNull View itemView) {
            super(itemView);
            tvChart = itemView.findViewById(R.id.tvChart);
        }
    }
}

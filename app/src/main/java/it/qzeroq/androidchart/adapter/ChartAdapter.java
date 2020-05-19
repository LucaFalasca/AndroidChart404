package it.qzeroq.androidchart.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.qzeroq.androidchart.R;

public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.Holder> {

    private List<String> chartNameList;

    public ChartAdapter(List<String> chartNameList){
        this.chartNameList = chartNameList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_chart_selection, parent, false);
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

    class Holder extends RecyclerView.ViewHolder {

        private TextView tvChart;

        Holder(@NonNull View itemView) {
            super(itemView);
            tvChart = itemView.findViewById(R.id.tvChart);
        }
    }
}

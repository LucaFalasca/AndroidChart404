package it.qzeroq.androidchart.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.qzeroq.androidchart.R;
import it.qzeroq.androidchart.activities.InsertDataActivity;

public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.Holder> implements View.OnClickListener {

    private Context context;
    private List<String> chartNameList;


    public ChartAdapter(Context context, List<String> chartNameList) {
        this.chartNameList = chartNameList;
        this.context = context;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating the layout for choosing the chart
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.
                from(parent.getContext()).inflate(R.layout.card_view_chart_selection, parent, false);
        constraintLayout.setOnClickListener(this);

        return new Holder(constraintLayout);
    }


    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        //filling the RecyclerView with name and image of the chart on the basis of the position
        String name = chartNameList.get(position);
        holder.tvChart.setText(name);
        if (context.getResources().getString(R.string.tv_LineChart_text).equals(name)) {
            holder.ivChart.setImageResource(R.drawable.line_chart);
        } else if (context.getResources().getString(R.string.tv_BarChart_text).equals(name)) {
            holder.ivChart.setImageResource(R.drawable.bar_chart);
        } else if (context.getResources().getString(R.string.tv_PieChart_text).equals(name)) {
            holder.ivChart.setImageResource(R.drawable.pie_chart);
        } else if (context.getResources().getString(R.string.tv_BubbleChart_text).equals(name)) {
            holder.ivChart.setImageResource(R.drawable.bubble_chart);
            holder.ivChart.setEnabled(false);
            holder.tvChart.setEnabled(false);
        } else if (context.getResources().getString(R.string.tv_ScatterChart_text).equals(name)) {
            holder.ivChart.setImageResource(R.drawable.scatter_chart);
            holder.ivChart.setEnabled(false);
            holder.tvChart.setEnabled(false);
        } else if (context.getResources().getString(R.string.tv_CandleStickChart_text).equals(name)) {
            holder.ivChart.setImageResource(R.drawable.candle_stick_chart);
            holder.ivChart.setEnabled(false);
            holder.tvChart.setEnabled(false);

        }
    }


    @Override
    public int getItemCount() {
        return chartNameList.size();
    }


    @Override
    public void onClick(View v) {
        //creating the intent and putting into it the position of the chart in the RecyclerView
        int position = ((RecyclerView) v.getParent()).getChildAdapterPosition(v);
        switch (position){
            case 0:
            case 1:
            case 2:
                Intent intent = new Intent(context, InsertDataActivity.class);
                intent.putExtra("value", position);

                //starting the activity of the clicked chart with the created intent
                context.startActivity(intent);
                break;
        }

    }


    static class Holder extends RecyclerView.ViewHolder {
        final TextView tvChart;
        final ImageView ivChart;

        Holder(@NonNull View itemView) {
            super(itemView);
            //attaching the views by their id
            tvChart = itemView.findViewById(R.id.tvChart);
            ivChart = itemView.findViewById(R.id.ivChart);
        }
    }
}

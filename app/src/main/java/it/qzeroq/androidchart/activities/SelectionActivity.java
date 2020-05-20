package it.qzeroq.androidchart.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import it.qzeroq.androidchart.R;
import it.qzeroq.androidchart.adapter.ChartAdapter;

public class SelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        Holder holder = new Holder(this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        holder.rvSelectionChart.setLayoutManager(layoutManager);

        List<String> chartNameList = new ArrayList();
        chartNameList.add(getResources().getString(R.string.tv_chart_text));
        chartNameList.add("prova2");
        chartNameList.add("prova3");

        ChartAdapter adapter = new ChartAdapter(this, chartNameList);
        holder.rvSelectionChart.setAdapter(adapter);
    }

    class Holder{

        private RecyclerView rvSelectionChart;

        Holder(Context context){
            rvSelectionChart = findViewById(R.id.rvSelectionChart);

        }

    }
}

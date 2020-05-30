package it.qzeroq.androidchart.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import it.qzeroq.androidchart.R;
import it.qzeroq.androidchart.adapter.*;

public class SelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        //list of chart names used to fill the RecyclerView
        ArrayList<String> chartNameList = new ArrayList<>();
        chartNameList.add(getResources().getString(R.string.tv_LineChart_text));
        chartNameList.add(getResources().getString(R.string.tv_BarChart_text));
        chartNameList.add(getResources().getString(R.string.tv_PieChart_text));
        chartNameList.add(getResources().getString(R.string.tv_ScatterChart_text));
        chartNameList.add(getResources().getString(R.string.tv_CandleStickChart_text));
        chartNameList.add(getResources().getString(R.string.tv_BubbleChart_text));

        new Holder(chartNameList);
    }


    class Holder {
        Holder(List<String> chartNameList) {
            RecyclerView rvSelectionChart = findViewById(R.id.rvSelectionChart);

            //setting GridLayoutManager for the RecyclerView of choosing chart
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(SelectionActivity.this, 2);
            rvSelectionChart.setLayoutManager(layoutManager);

            //setting ChartAdapter for the RecyclerView of choosing chart
            ChartAdapter adapter = new ChartAdapter(SelectionActivity.this, chartNameList);
            rvSelectionChart.setAdapter(adapter);
        }

    }
}

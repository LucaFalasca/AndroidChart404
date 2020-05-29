package it.qzeroq.androidchart.activities.chart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import it.qzeroq.androidchart.R;

public class PieChartActivity extends AppCompatActivity {

    Holder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        holder = new Holder();

        Intent intentData = getIntent();

        ArrayList<Integer> color;
        color = addColor();

        String[] names = intentData.getStringArrayExtra("names");
        String[] values = intentData.getStringArrayExtra("values");
        int n = intentData.getIntExtra("n", 0);

        List<PieEntry> entries = new ArrayList<>();
        for(int i = 0; i < n; i++){
            assert values[i] != null;
            assert names[i] != null;
            entries.add(new PieEntry(Float.parseFloat(values[i]), names[i]));

        }

        PieDataSet set = new PieDataSet(entries, "");
        set.setColors(color);

        PieData data = new PieData(set);
        holder.pieChart.setData(data);
        holder.pieChart.invalidate();
    }

    ArrayList<Integer> addColor(){
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getColor(R.color.Color1));
        colors.add(getColor(R.color.colorPrimary));
        colors.add(getColor(R.color.Color2));
        colors.add(getColor(R.color.Color3));
        colors.add(getColor(R.color.Color4));
        colors.add(getColor(R.color.Color5));


        return colors;
    }

    class Holder{
        private PieChart pieChart;
        Holder(){
            pieChart = findViewById(R.id.pieChart);
            pieChart.setHoleColor(getColor(R.color.background));
            pieChart.setUsePercentValues(true);
            pieChart.getDescription().setEnabled(false);
            Legend legend = pieChart.getLegend();
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            legend.setOrientation(Legend.LegendOrientation.VERTICAL);
            legend.setFormSize(15);
            legend.setTextSize(15);


        }
    }
}

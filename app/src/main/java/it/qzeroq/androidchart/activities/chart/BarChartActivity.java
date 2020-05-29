package it.qzeroq.androidchart.activities.chart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.qzeroq.androidchart.R;

public class BarChartActivity extends AppCompatActivity{

    Holder holder;
    Random rnd;
    // ------- INSERIRE GESTIONE ERRORI DI INSERIMENTO DATI DA PARTE DELL'UTENTE --------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        holder = new Holder();
        rnd = new Random();

        /*
        Intent intentData = getIntent();
        String[] names = intentData.getStringArrayExtra("names");
        String[] xAxises = intentData.getStringArrayExtra("xAxises");
        String[] yAxises = intentData.getStringArrayExtra("yAxises");
        int numberOfFunction = intentData.getIntExtra("n", 1);
        */

        String[] names = {"a"};
        String[] values = {"2 3 5 7 1 23"};
        int numberOfFunction = 1;

        BarData barData = new BarData();

        for(int i = 0; i < numberOfFunction; i++){
            //Rappresenta una singola barra sul grafico
            BarDataSet set = (BarDataSet) createDataSet(names[i], formatData(values[i]));

            //vengono al barData
            barData.addDataSet(set);
        }

        holder.barChart.setData(barData);

        PersonalizeChart(holder.barChart);

        holder.barChart.invalidate();
    }

    private List<Integer> formatData(String values) {
        List<Integer> list = new ArrayList<>();

        //controlli
        while(values.endsWith(" ")){
            values = values.substring(0, values.length() - 1);
        }
        values.replace(",", "");


        String[] strings = values.split(" ");
        for(int i = 0; i < strings.length; i++){
            list.add(Integer.valueOf(strings[i]));
        }

        return list;
    }

    private DataSet createDataSet(String name, List<Integer> values) {
        int numberOfEntry = values.size();

        List<BarEntry> entries = new ArrayList<>();
        for(int i = 0; i < numberOfEntry; i++){
            entries.add(new BarEntry(i, values.get(i)));
        }

        BarDataSet set = new BarDataSet(entries, name);

        //Personalizzazione del data set
        PersonalizeDataSet(set);

        return set;
    }

    private void PersonalizeDataSet(DataSet set) {
        set.setColor(Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
        set.setValueTextColor(Color.WHITE);
    }

    private void PersonalizeChart(BarChart chart) {
        chart.getXAxis().setTextColor(Color.WHITE);
        chart.getAxisLeft().setTextColor(Color.WHITE);
        chart.getAxisRight().setTextColor(Color.WHITE);
        chart.getLegend().setTextColor(Color.WHITE);

        chart.setFitBars(true);
        XAxis xAxis = chart.getXAxis();

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return "ciao";
            }
        });

        chart.getAxisLeft().setAxisMinimum(0);
        chart.getXAxis().setAxisMinimum(-0.5f);


    }

    class Holder{

        private BarChart barChart;

        Holder(){
            barChart = findViewById(R.id.barChart);
        }
    }
}

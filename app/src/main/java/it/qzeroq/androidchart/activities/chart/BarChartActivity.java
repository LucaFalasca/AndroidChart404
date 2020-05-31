package it.qzeroq.androidchart.activities.chart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import it.qzeroq.androidchart.R;

public class BarChartActivity extends AppCompatActivity {

    Holder holder;
    Random rnd;
    // ------- INSERIRE GESTIONE ERRORI DI INSERIMENTO DATI DA PARTE DELL'UTENTE --------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bar_chart);

        holder = new Holder();
        rnd = new Random();

        //getting the intent and its data
        Intent intentData = getIntent();
        String[] groups = intentData.getStringArrayExtra("groups");
        String[] xAxises = intentData.getStringArrayExtra("xAxises");
        String[] yAxises = intentData.getStringArrayExtra("yAxises");
        int numberOfGroups = intentData.getIntExtra("n", 1);

        BarData barData = new BarData();

        //creating the groups list of the BarChart
        for(int i = 0; i < numberOfGroups; i++) {
            assert groups != null;
            assert yAxises != null;

            //each set is a bar on the BarChart
            BarDataSet set = (BarDataSet) createDataSet(groups[i], formatDataToInteger(yAxises[i]));

            //each set is added to the BarData
            barData.addDataSet(set);
        }

        barData.setBarWidth(0.45f);
        barData.groupBars(0, 0.06f, 0.02f);

        //adding data to the BarChart
        holder.barChart.setData(barData);
        holder.barChart.invalidate();

        //adding labels to the x-axis
        assert xAxises != null;
        AddLabels(holder.barChart, xAxises[0]);

        //customization of the BarChart
        PersonalizeChart(holder.barChart);

        holder.barChart.invalidate();
    }


    private void AddLabels(BarChart barChart, String name) {
        final List<String> labels = formatDataToString(name);

        //setting ValueFormatter for the x-axis
        barChart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return labels.get((int) value);
            }
        });
    }


    private List<String> formatDataToString(String values){
        List<String> list = new ArrayList<>();

        //data format controls
        while(values.endsWith(" ")){
            values = values.substring(0, values.length() - 1);
        }
        values = values.replace(",", " ");

        //adding single strings to a list of strings
        String[] strings = values.split(" ");
        Collections.addAll(list, strings);

        return list;
    }


    private List<Float> formatDataToInteger(String values) {
        List<Float> list = new ArrayList<>();

        //data format controls
        while(values.endsWith(" ")){
            values = values.substring(0, values.length() - 1);
        }
        values = values.replace(",", " ");

        //conversion of data from String to Float
        String[] strings = values.split(" ");
        for (String string : strings) {
            list.add(Float.valueOf(string));
        }

        return list;
    }


    private DataSet createDataSet(String name, List<Float> values) {
        int numberOfEntry = values.size();

        //creation of BarEntry and list of BarEntry
        List<BarEntry> entries = new ArrayList<>();
        for(int i = 0; i < numberOfEntry; i++){
            entries.add(new BarEntry(i, values.get(i)));
        }

        //dataset creation
        BarDataSet set = new BarDataSet(entries, name);

        //customization of the DataSet
        PersonalizeDataSet(set);

        return set;
    }


    private void PersonalizeDataSet(DataSet set) {
        set.setColor(Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
        set.setValueTextColor(getColor(R.color.white));
    }


    private void PersonalizeChart(BarChart chart) {
        //setting colors
        chart.getXAxis().setTextColor(getColor(R.color.white));
        chart.getAxisLeft().setTextColor(getColor(R.color.white));
        chart.getAxisRight().setTextColor(getColor(R.color.white));
        chart.getLegend().setTextColor(getColor(R.color.white));

        //fitting the bars in order to properlyPAh s show them
        chart.setFitBars(true);

        //customization of x-axis
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);

        //setting the axises range
        chart.getAxisLeft().setAxisMinimum(0);
        chart.getXAxis().setAxisMinimum(-0.5f);
        chart.getXAxis().setAxisMaximum(chart.getXAxis().getAxisMaximum());

        //disabling description of the chart
        chart.getDescription().setEnabled(false);
    }


    class Holder {
        private BarChart barChart;

        Holder() {
            //attaching the BarChart by its id
            barChart = findViewById(R.id.barChart);
        }
    }
}

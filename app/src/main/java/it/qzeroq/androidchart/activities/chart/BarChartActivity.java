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
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import it.qzeroq.androidchart.R;

public class BarChartActivity extends AppCompatActivity {

    Holder holder;
    Random rnd;
    int numberOfGroups;
    float barWidth , groupSpace, barSpace;
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
        numberOfGroups = intentData.getIntExtra("n", 1);

        BarData barData = new BarData();

        //creating the groups list of the BarChart
        for(int i = 0; i < numberOfGroups; i++) {
            assert groups != null;
            assert yAxises != null;

            //each set is a bar on the BarChart
            BarDataSet set = (BarDataSet) createDataSet(groups[i], formatDataToFloat(yAxises[i]));

            //each set is added to the BarData
            barData.addDataSet(set);
        }

        //customization of BarData
        PersonalizeDataChart(barData);

        //adding data to the BarChart
        holder.barChart.setData(barData);
        holder.barChart.invalidate();



        //customization of the BarChart
        PersonalizeChart(holder.barChart);

        //adding labels to the x-axis
        assert xAxises != null;
        AddLabels(holder.barChart, xAxises[0]);

        holder.barChart.invalidate();
    }




    private void AddLabels(BarChart barChart, String name) {
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(formatDataToString(name)));
    }

    private String formatString(String string){
        while(string.contains("  ")){
            string = string.replace("  ", " ");
        }
        string = string.replace(" ", ",");
        return string;
    }

    private List<String> formatDataToString(String values){
        List<String> list = new ArrayList<>();

        //data format controls
        values = formatString(values);

        //adding single strings to a list of strings
        String[] strings = values.split(",");
        Collections.addAll(list, strings);

        return list;
    }


    private List<Float> formatDataToFloat(String values) {
        List<Float> list = new ArrayList<>();

        //data format controls
        values = formatString(values);

        //conversion of data from String to Float
        String[] strings = values.split(",");
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
        set.setValueTextColor(getColor(R.color.colorTextChart));
    }

    private void PersonalizeDataChart(BarData barData) {
        groupSpace = 0.1f;
        barWidth = 0.35f;
        barSpace = 0.1f;
        barData.setBarWidth(barWidth);

        if(numberOfGroups > 1)
            barData.groupBars(0, groupSpace, barSpace);
        else{
            //boh
        }

    }

    private void PersonalizeChart(BarChart chart) {
        //setting colors
        chart.getXAxis().setTextColor(getColor(R.color.colorTextChart));
        chart.getAxisLeft().setTextColor(getColor(R.color.colorTextChart));
        chart.getAxisRight().setTextColor(getColor(R.color.colorTextChart));
        chart.getLegend().setTextColor(getColor(R.color.colorTextChart));

        //fitting the bars in order to properlyPAh s show them
        chart.setFitBars(true);

        //customization of x-axis
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);

        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(true);
        chart.getAxisLeft().setGranularity(1f);
        chart.getAxisRight().setDrawLabels(false);

        //setting the axises range
        chart.getAxisLeft().setAxisMinimum(0);
        chart.getXAxis().setAxisMinimum(0);
        int numberOfEntry = chart.getBarData().getDataSets().get(0).getEntryCount();
        chart.getXAxis().setAxisMaximum(numberOfEntry);


        //disabling description of the chart
        chart.getDescription().setEnabled(false);

        chart.setFitBars(true);
    }


    class Holder {
        private BarChart barChart;

        Holder() {
            //attaching the BarChart by its id
            barChart = findViewById(R.id.barChart);
        }
    }
}

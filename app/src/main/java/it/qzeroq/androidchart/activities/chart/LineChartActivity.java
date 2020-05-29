package it.qzeroq.androidchart.activities.chart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.qzeroq.androidchart.R;
import it.qzeroq.androidchart.data.LineChartData;

public class LineChartActivity extends AppCompatActivity {

    Holder holder;
    Random rnd;
    // ------- INSERIRE GESTIONE ERRORI DI INSERIMENTO DATI DA PARTE DELL'UTENTE --------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        holder = new Holder();
        rnd = new Random();

        Intent intentData = getIntent();
        String[] names = intentData.getStringArrayExtra("names");
        String[] xAxises = intentData.getStringArrayExtra("xAxises");
        String[] yAxises = intentData.getStringArrayExtra("yAxises");
        int numberOfFunction = intentData.getIntExtra("n", 1);

        //Lista delle funzioni da inserire nel grafico
        List<ILineDataSet> setList = new ArrayList<>();

        for(int i = 0; i < numberOfFunction; i++){
            //Rappresenta una singola funzione sul grafico
            LineDataSet set = createDataSet(names[i], formatData(xAxises[i]), formatData(yAxises[i]));

            //vengono aggiunti in una lista
            setList.add(set);
        }

        LineData lineData = new LineData(setList);

        holder.lineChart.setData(lineData);
        holder.lineChart.invalidate();

        PersonalizeChart(holder.lineChart);
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

    private LineDataSet createDataSet(String name, List<Integer> xAxise, List<Integer> yAxise) {
        int numberOfEntry = Math.min(xAxise.size(), yAxise.size());

        List<Entry> entries = new ArrayList<>();
        for(int i = 0; i < numberOfEntry; i++){
            entries.add(new Entry(xAxise.get(i), yAxise.get(i)));
        }

        LineDataSet set = new LineDataSet(entries, name);

        //Personalizzazione del data set
        PersonalizeDataSet(set);

        return set;
    }

    private void PersonalizeDataSet(LineDataSet set) {
        set.setColor(Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
    }

    private void PersonalizeChart(LineChart chart) {
        chart.animateX(1000, Easing.Linear);
    }
    class Holder{

        private LineChart lineChart;
//        private List<LineChartData> dataList;


        Holder(){
            lineChart = findViewById(R.id.lineChart);
            lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            /*dataList = new ArrayList<>();
            for(int i = 0; i < 10; i++){
                dataList.add(new LineChartData(i + 1, i + 2));
            }*/
        }


        LineChart getLineChart() {
            return lineChart;
        }


       /* List<LineChartData> getDataList() {
            return dataList;
        }*/
    }
}

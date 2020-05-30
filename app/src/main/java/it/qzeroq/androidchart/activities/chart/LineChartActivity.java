package it.qzeroq.androidchart.activities.chart;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.qzeroq.androidchart.R;

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
            assert names != null;
            assert yAxises != null;
            assert xAxises != null;

            //Rappresenta una singola funzione sul grafico
            LineDataSet set = (LineDataSet) createDataSet(names[i], formatData(xAxises[i]), formatData(yAxises[i]));

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
        values = values.replace(",", "");


        String[] strings = values.split(" ");
        for (String string : strings) {
            list.add(Integer.valueOf(string));
        }

        return list;
    }

    private DataSet createDataSet(String name, List<Integer> xAxise, List<Integer> yAxise) {
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

    private void PersonalizeDataSet(DataSet set) {
        set.setColor(Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
        set.setValueTextColor(Color.WHITE);
    }

    private void PersonalizeChart(LineChart chart) {
        chart.animateX(1000, Easing.Linear);
        chart.getXAxis().setTextColor(R.color.white);
        chart.getAxisLeft().setTextColor(R.color.white);
        chart.getAxisRight().setTextColor(R.color.white);
        chart.getLegend().setTextColor(R.color.white);
        chart.getDescription().setEnabled(false);
    }

    class Holder{

        private LineChart lineChart;

        Holder(){
            lineChart = findViewById(R.id.lineChart);
            lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        }
    }
}

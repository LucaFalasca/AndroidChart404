package it.qzeroq.androidchart.activities.chart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import it.qzeroq.androidchart.R;
import it.qzeroq.androidchart.data.LineChartData;

public class LineChartActivity extends AppCompatActivity {

    Holder holder;

    // ------- INSERIRE GESTIONE ERRORI DI INSERIMENTO DATI DA PARTE DELL'UTENTE --------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        holder = new Holder();

        Intent intentData = getIntent();
        String[] names = intentData.getStringArrayExtra("names");
        String[] xAxises = intentData.getStringArrayExtra("xAxises");
        String[] yAxises = intentData.getStringArrayExtra("yAxises");
        int numberOfFunction = intentData.getIntExtra("n", 1);

        List<String[]> x = formatData(xAxises, numberOfFunction);
        List<String[]> y = formatData(yAxises, numberOfFunction);

        //Creazione lista di Entry
        List<Entry> entries = new ArrayList<>();
        for(int k = 0; k < x.size(); k++) {
            for (int i = 0; i < x.get(k).length; i++) {
                entries.add(new Entry(Float.parseFloat(x.get(k)[i]), Float.parseFloat(y.get(k)[i])));
            }
        }


        List<ILineDataSet> lineDataSets = new ArrayList<>();
        for(int i = 0; i < x.size(); i++) {
            lineDataSets.add(new LineDataSet(entries, names[i]));
        }

        LineData lineData = new LineData(lineDataSets);

        holder.getLineChart().setData(lineData);

        holder.getLineChart().invalidate();

        holder.lineChart.animateX(1000, Easing.Linear);
    }

    private List<String[]> formatData(String[] values, int n) {
        List<String[]> stringList = new ArrayList<>();
        for(int i = 0; i < n; i++){
            stringList.add(values[i].replace(",", " ").replace("  ", " ").split(" "));
        }
        return stringList;
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

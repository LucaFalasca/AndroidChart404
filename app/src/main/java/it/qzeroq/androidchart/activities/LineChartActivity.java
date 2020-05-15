package it.qzeroq.androidchart.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.qzeroq.androidchart.R;
import it.qzeroq.androidchart.data.LineChartData;

public class LineChartActivity extends AppCompatActivity {

    Holder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        holder = new Holder();



        //Creazione lista di Entry
        List<Entry> entries = new ArrayList<>();
        for(int i = 0; i < holder.getDataList().size(); i++){
            LineChartData data = holder.getDataList().get(i);
            entries.add(new Entry(data.getX(), data.getY()));
        }

        LineDataSet lineDataSet = new LineDataSet(entries, "Data1");

        LineData lineData = new LineData(lineDataSet);

        holder.getLineChart().setData(lineData);
        holder.getLineChart().invalidate();
    }

    class Holder{

        private LineChart lineChart;
        private List<LineChartData> dataList;

        public Holder(){
            lineChart = findViewById(R.id.lineChart);
            lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            dataList = new ArrayList<>();
            for(int i = 0; i < 10; i++){
                dataList.add(new LineChartData(i + 1, i + 2));
            }
        }

        public LineChart getLineChart() {
            return lineChart;
        }

        public List<LineChartData> getDataList() {
            return dataList;
        }
    }
}

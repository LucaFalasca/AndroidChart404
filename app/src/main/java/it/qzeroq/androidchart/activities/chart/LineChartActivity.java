package it.qzeroq.androidchart.activities.chart;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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

        //getting the intent and its data
        Intent intentData = getIntent();
        String[] names = intentData.getStringArrayExtra("names");
        String[] xAxises = intentData.getStringArrayExtra("xAxises");
        String[] yAxises = intentData.getStringArrayExtra("yAxises");
        int numberOfFunction = intentData.getIntExtra("n", 1);

        //creating the functions list of the LineChart
        List<ILineDataSet> setList = new ArrayList<>();
        for(int i = 0; i < numberOfFunction; i++) {
            assert names != null;
            assert yAxises != null;
            assert xAxises != null;

            //each set is a function on the LineChart
            LineDataSet set = (LineDataSet) createDataSet(names[i], formatDataToFloat(xAxises[i]), formatDataToFloat(yAxises[i]));

            //each set is added to the same list
            setList.add(set);
        }

        LineData lineData = new LineData(setList);

        //adding data to the LineChart
        holder.lineChart.setData(lineData);
        holder.lineChart.invalidate();

        //customization of the LineChart
        PersonalizeChart(holder.lineChart);

        holder.lineChart.invalidate();
    }


    private String formatString(String string) {
        //elimination of any multiple space between one data and another
        while(string.contains("  ")) {
            string = string.replace("  ", " ");
        }
        string = string.replace(" ", ",");
        return string;
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


    private DataSet createDataSet(String name, List<Float> xAxis, List<Float> yAxis) {
        int numberOfEntry = Math.min(xAxis.size(), yAxis.size());

        //creation of Entry and list of Entry
        List<Entry> entries = new ArrayList<>();
        for(int i = 0; i < numberOfEntry; i++){
            entries.add(new Entry(xAxis.get(i), yAxis.get(i)));
        }

        //dataset creation
        LineDataSet set = new LineDataSet(entries, name);

        //customization of the DataSet
        PersonalizeDataSet(set);

        return set;
    }


    private void PersonalizeDataSet(DataSet set) {
        set.setColor(Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
        set.setValueTextColor(getColor(R.color.colorTextChart));
    }


    private void PersonalizeChart(LineChart chart) {
        //setting animation on the chart
        chart.animateX(1000, Easing.Linear);

        //customization of x-axis position
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

        //setting colors
        chart.getXAxis().setTextColor(getColor(R.color.colorTextChart));
        chart.getAxisLeft().setTextColor(getColor(R.color.colorTextChart));
        chart.getAxisRight().setTextColor(getColor(R.color.colorTextChart));
        chart.getLegend().setTextColor(getColor(R.color.colorTextChart));

        //disabling description of the chart
        chart.getDescription().setEnabled(false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            holder.lineChart.saveToGallery(getResources().getString(R.string.tv_PieChart_text));
            Toast.makeText(LineChartActivity.this, LineChartActivity.this.getResources().getText(R.string.toast_saved), Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(LineChartActivity.this, LineChartActivity.this.getResources().getText(R.string.toast_no_permission), Toast.LENGTH_LONG).show();
        }
    }

    class Holder implements View.OnClickListener{
        private LineChart lineChart;
        final Button btnSave;
        Holder() {
            //attaching the LineChart by its id
            lineChart = findViewById(R.id.lineChart);

            btnSave = findViewById(R.id.btnSave);

            btnSave.setOnClickListener(this);

            }


            @Override
            public void onClick(View v) {
                int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 0;
                if(ContextCompat.checkSelfPermission(LineChartActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(LineChartActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);
                }
                else {
                    lineChart.saveToGallery(getResources().getString(R.string.tv_LineChart_text));
                    Toast.makeText(LineChartActivity.this, LineChartActivity.this.getResources().getText(R.string.toast_saved), Toast.LENGTH_LONG).show();
                }

            }
        }
    }


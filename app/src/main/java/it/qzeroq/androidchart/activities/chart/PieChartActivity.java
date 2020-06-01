package it.qzeroq.androidchart.activities.chart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import it.qzeroq.androidchart.R;

public class PieChartActivity extends AppCompatActivity {

    Holder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pie_chart);

        holder = new Holder();

        //getting the intent and its data
        Intent intentData = getIntent();
        String[] names = intentData.getStringArrayExtra("names");
        String[] values = intentData.getStringArrayExtra("values");
        int n = intentData.getIntExtra("n", 0);

        //creation of PieEntry and list of PieEntry
        List<PieEntry> entries = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            assert Objects.requireNonNull(values)[i] != null;
            assert Objects.requireNonNull(names)[i] != null;
            entries.add(new PieEntry(Float.parseFloat(values[i]), names[i]));
        }

        //dataset creation
        PieDataSet set = new PieDataSet(entries, "");

        //customization of the DataSet
        PersonalizeDataSet(set);


        PieData data = new PieData(set);

        //adding data to the PieChart
        holder.pieChart.setData(data);
        holder.pieChart.invalidate();

        //customization of the PieChart
        PersonalizeChart(holder.pieChart);

        holder.pieChart.invalidate();
    }


    protected ArrayList<Integer> addColor() {
        //creating the list of colors used in the chart
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getColor(R.color.Color1));
        colors.add(getColor(R.color.colorPrimary));
        colors.add(getColor(R.color.Color2));
        colors.add(getColor(R.color.Color3));
        colors.add(getColor(R.color.Color4));
        colors.add(getColor(R.color.Color5));

        return colors;
    }


    private void PersonalizeDataSet(PieDataSet set) {
        set.setColors(addColor());
        set.setValueTextColor(getColor(R.color.colorTextChart));
        set.setValueTextSize(15);
    }


    private void PersonalizeChart(PieChart chart){
        chart.setHoleColor(getColor(R.color.background));
        chart.setUsePercentValues(true);

        //customization of the legend
        Legend legend = chart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setFormSize(getResources().getDimension(R.dimen.legend));
        legend.setTextSize(getResources().getDimension(R.dimen.legend_text));
        legend.setTextColor(getColor(R.color.colorTextChart));
        chart.getDescription().setEnabled(false);
    }


    class Holder implements View.OnClickListener{
        final PieChart pieChart;
        final Button btnSave;
        Holder() {
            //attaching the PieChart by its id
            pieChart = findViewById(R.id.pieChart);
            btnSave = findViewById(R.id.btnSave);

            btnSave.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 0;
            if(ContextCompat.checkSelfPermission(PieChartActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(PieChartActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);
            }
            else {
                pieChart.saveToGallery(getResources().getString(R.string.tv_PieChart_text));
                Toast.makeText(PieChartActivity.this, PieChartActivity.this.getResources().getText(R.string.toast_saved), Toast.LENGTH_LONG).show();
            }

        }
    }
}

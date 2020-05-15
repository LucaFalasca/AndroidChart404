package it.qzeroq.androidchart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import it.qzeroq.androidchart.R;
import it.qzeroq.androidchart.activities.LineChartActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, LineChartActivity.class);
        startActivity(intent);
    }
}

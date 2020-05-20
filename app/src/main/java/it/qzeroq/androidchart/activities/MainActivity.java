package it.qzeroq.androidchart.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import it.qzeroq.androidchart.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Holder holder = new Holder();
    }

    class Holder implements View.OnClickListener{

        private Button btnCreateChart;

        Holder(){
            btnCreateChart = findViewById(R.id.btnCreateChart);
            btnCreateChart.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, SelectionActivity.class);
            startActivity(intent);
        }
    }
}

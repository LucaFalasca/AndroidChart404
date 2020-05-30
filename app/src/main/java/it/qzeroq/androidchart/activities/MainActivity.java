package it.qzeroq.androidchart.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import it.qzeroq.androidchart.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Holder();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this, SelectionActivity.class);
                startActivity(intent);
                finish();
            }
        },1000);
    }

    class Holder {

        Holder(){
            ImageView ivInizio = findViewById(R.id.ivInizio);
            ivInizio.setImageDrawable(getDrawable(R.drawable.inizio));
       }
    }
}

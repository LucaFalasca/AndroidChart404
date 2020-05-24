package it.qzeroq.androidchart.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

import it.qzeroq.androidchart.R;
import it.qzeroq.androidchart.adapter.InsertDataAdapter;

public class InsertDataActivity extends AppCompatActivity {

    private InsertDataAdapter adapter;
    Holder holder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        holder = new Holder();
    }


    class Holder implements View.OnClickListener {

        private RecyclerView rvInsertLine;
        private Button btnGenerate, btnAddCard;

        Holder(){
            rvInsertLine = findViewById(R.id.rvInsertLine);
            btnGenerate = findViewById(R.id.btnGenerate);
            btnAddCard = findViewById(R.id.btnAddCard);

            btnGenerate.setOnClickListener(this);
            btnAddCard.setOnClickListener(this);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(InsertDataActivity.this){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            rvInsertLine.setLayoutManager(layoutManager);

            adapter = new InsertDataAdapter(InsertDataActivity.this);
            rvInsertLine.setAdapter(adapter);
        }


        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btnAddCard)
                adapter.addCard();
            else if (v.getId() == R.id.btnGenerate) {
                makeDataSet();
                //Intent i = new Intent(InsertDataActivity.this, LineChartActivity.class);
                //startActivity(i);
            }
        }
    }


    private void makeDataSet() {
        int items = adapter.getItemCount();
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < items; i++) {


            //entries.add(new Entry( Integer.parseInt(adapter.) ) );
        }
    }
}

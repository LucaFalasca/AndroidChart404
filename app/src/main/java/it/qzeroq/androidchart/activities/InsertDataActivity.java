package it.qzeroq.androidchart.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import it.qzeroq.androidchart.R;
import it.qzeroq.androidchart.activities.chart.LineChartActivity;
import it.qzeroq.androidchart.activities.chart.PieChartActivity;
import it.qzeroq.androidchart.adapter.InsertDataAdapter;
import it.qzeroq.androidchart.adapter.PieInsertDataAdapter;

public class InsertDataActivity extends AppCompatActivity {
    private int idGraph;
    private InsertDataAdapter adapter;
    private PieInsertDataAdapter pAdapter;
    Holder holder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);
        Intent intentData = getIntent();
        idGraph = intentData.getIntExtra("value",6);
        holder = new Holder(idGraph);
    }


    class Holder implements View.OnClickListener {

        private RecyclerView rvInsertLine;
        private Button btnGenerate, btnAddCard;

        Holder(int id){
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
            switch (id) {

                case 0:
                    adapter = new InsertDataAdapter(InsertDataActivity.this);
                    rvInsertLine.setAdapter(adapter);
                    break;
                case 2:
                    pAdapter = new PieInsertDataAdapter(InsertDataActivity.this);
                    rvInsertLine.setAdapter(pAdapter);
                    break;

            }

        }


        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btnAddCard) {
                switch (idGraph) {
                    case 0:
                        adapter.addCard();
                    case 2:
                        pAdapter.addCard();

                }

            }
            else if (v.getId() == R.id.btnGenerate) {
                //makeDataSet();
                Intent intent = null;
                switch (idGraph) {

                    case 0:
                        intent =new Intent(InsertDataActivity.this, LineChartActivity.class);
                        intent.putExtra("names", adapter.getNames());
                        intent.putExtra("xAxises", adapter.getXAxis());
                        intent.putExtra("yAxises", adapter.getYAxis());
                        intent.putExtra("n", adapter.getItemCount());

                    case 2:
                        intent =new Intent(InsertDataActivity.this, PieChartActivity.class);
                        intent.putExtra("names", pAdapter.getNames());
                        intent.putExtra("values", pAdapter.getvaluesSlice());
                        intent.putExtra("n", pAdapter.getItemCount());
                }
                startActivity(intent);
            }
        }
    }

/*
    private void makeDataSet() {
        int items = adapter.getItemCount();
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < items; i++) {


            //entries.add(new Entry( Integer.parseInt(adapter.) ) );
        }
    }*/
}

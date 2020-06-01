package it.qzeroq.androidchart.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import it.qzeroq.androidchart.R;
import it.qzeroq.androidchart.activities.chart.BarChartActivity;
import it.qzeroq.androidchart.activities.chart.LineChartActivity;
import it.qzeroq.androidchart.activities.chart.PieChartActivity;
import it.qzeroq.androidchart.adapter.chart.BarInsertDataAdapter;
import it.qzeroq.androidchart.adapter.chart.LineInsertDataAdapter;
import it.qzeroq.androidchart.adapter.chart.PieInsertDataAdapter;

public class InsertDataActivity extends AppCompatActivity {
    private int idGraph;
    private LineInsertDataAdapter adapter;
    private PieInsertDataAdapter pAdapter;
    private BarInsertDataAdapter bAdapter;
    Holder holder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        //getting the intent and its data
        Intent intentData = getIntent();
        idGraph = intentData.getIntExtra("value",6);

        holder = new Holder(idGraph);
    }


    class Holder implements View.OnClickListener {

        Holder(int id){
            //attaching the views by their id
            RecyclerView rvInsertLine = findViewById(R.id.rvInsertLine);
            Button btnGenerate = findViewById(R.id.btnGenerate);
            Button btnAddCard = findViewById(R.id.btnAddCard);

            //setting the OnClickListeners on the buttons
            btnGenerate.setOnClickListener(this);
            btnAddCard.setOnClickListener(this);

            //setting LinearLayoutManager for the RecyclerView of insertion chart data
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(InsertDataActivity.this){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            rvInsertLine.setLayoutManager(layoutManager);

            //setting adapter for the RecyclerView on the basis of the clicked chart
            switch (id) {
                case 0:
                    adapter = new LineInsertDataAdapter();
                    rvInsertLine.setAdapter(adapter);
                    break;
                case 1:
                    bAdapter = new BarInsertDataAdapter();
                    rvInsertLine.setAdapter(bAdapter);
                    break;
                case 2:
                    pAdapter = new PieInsertDataAdapter();
                    rvInsertLine.setAdapter(pAdapter);
                    break;
            }

        }


        @Override
        public void onClick(View v) {
            //adding the right CartView for insertion of the chart data on the basis of the previous clicked chart
            //if the button btnAddCard was clicked
            if (v.getId() == R.id.btnAddCard) {
                switch (idGraph) {
                    case 0:
                        adapter.addCard();
                        break;
                    case 1:
                        bAdapter.addCard();
                        break;
                    case 2:
                        pAdapter.addCard();
                        break;
                }
            }
            //creating the intent and putting into it the chart data on the basis of the previous clicked chart
            //if the button btnGenerate was clicked
            else if (v.getId() == R.id.btnGenerate) {
                Intent intent = null;
                switch (idGraph) {
                    case 0:
                        intent = new Intent(InsertDataActivity.this, LineChartActivity.class);
                        intent.putExtra("names", adapter.getNames());
                        intent.putExtra("xAxises", adapter.getXAxis());
                        intent.putExtra("yAxises", adapter.getYAxis());
                        intent.putExtra("n", adapter.getItemCount());
                        break;
                    case 1:
                        intent = new Intent(InsertDataActivity.this, BarChartActivity.class);
                        intent.putExtra("groups", bAdapter.getNames());
                        intent.putExtra("xAxises", bAdapter.getXAxis());
                        intent.putExtra("yAxises", bAdapter.getYAxis());
                        intent.putExtra("n", bAdapter.getItemCount());
                        break;
                    case 2:
                        intent = new Intent(InsertDataActivity.this, PieChartActivity.class);
                        intent.putExtra("names", pAdapter.getNames());
                        intent.putExtra("values", pAdapter.getvaluesSlice());
                        intent.putExtra("n", pAdapter.getItemCount());
                        break;
                }
                startActivity(intent);
            }
        }
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("id", idGraph);

        switch (idGraph) {
            case 0:
                outState.putInt("numCard", adapter.getItemCount());
                for (int i = 0; i < adapter.getItemCount(); i++) {
                    outState.putString("lineName", adapter.getLineName(i));
                    outState.putString("xAxis", adapter.getXAxis(i));
                    outState.putString("yAxis", adapter.getYAxis(i));
                }
                break;
            case 1:
                outState.putInt("numCard", bAdapter.getItemCount());
                for (int i = 0; i < bAdapter.getItemCount(); i++) {
                    outState.putString("groupName", bAdapter.getGroupName(i));
                    outState.putString("xAxis", bAdapter.getXAxis(i));
                    outState.putString("yAxis", bAdapter.getYAxis(i));
                }
                break;
            case 2:
                outState.putInt("numCard", pAdapter.getItemCount());
                for (int i = 0; i < pAdapter.getItemCount(); i++) {
                    outState.putString("sliceName", pAdapter.getSliceName(i));
                    outState.putString("value", pAdapter.getSliceValue(i));
                }
                break;
        }
    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        int id = savedInstanceState.getInt("id");
        int numCard;

        switch (id) {
            case 0:
                numCard = savedInstanceState.getInt("numCard");
                for (int i = 0; i < numCard; i++) {
                    adapter.addCard();
                    adapter.setLineName(i, savedInstanceState.getString("lineName"));
                    adapter.setXAxis(i, savedInstanceState.getString("xAxis"));
                    adapter.setYAxis(i, savedInstanceState.getString("yAxis"));
                }
                break;
            case 1:
                numCard = savedInstanceState.getInt("numCard");
                for (int i = 0; i < numCard; i++) {
                    bAdapter.addCard();
                    bAdapter.setGroupName(i, savedInstanceState.getString("groupName"));
                    bAdapter.setXAxis(i, savedInstanceState.getString("xAxis"));
                    bAdapter.setYAxis(i, savedInstanceState.getString("yAxis"));
                }
                break;
            case 2:
                numCard = savedInstanceState.getInt("numCard");
                for (int i = 0; i < numCard; i++) {
                    pAdapter.addCard();
                    pAdapter.setSliceName(i, savedInstanceState.getString("groupName"));
                    pAdapter.setSliceValue(i, savedInstanceState.getString("xAxis"));
                }
                break;
        }
    }
}

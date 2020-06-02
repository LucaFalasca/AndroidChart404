package it.qzeroq.androidchart.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private BarInsertDataAdapter bAdapter;
    private PieInsertDataAdapter pAdapter;

    Holder holder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        //getting the intent and its data
        Intent intentData = getIntent();
        idGraph = intentData.getIntExtra("value",6);

        holder = new Holder(idGraph, this);
    }


    class Holder implements View.OnClickListener {

        Context context;
        RecyclerView rvInsertLine;
        RecyclerView.LayoutManager layoutManager;

        Holder(int id, Context context){
            this.context = context;

            //attaching the views by their id
            rvInsertLine = findViewById(R.id.rvInsertLine);
            Button btnGenerate = findViewById(R.id.btnGenerate);
            Button btnAddCard = findViewById(R.id.btnAddCard);

            //setting the OnClickListeners on the buttons
            btnGenerate.setOnClickListener(this);
            btnAddCard.setOnClickListener(this);

            //setting LinearLayoutManager for the RecyclerView of insertion chart data
            layoutManager = new LinearLayoutManager(InsertDataActivity.this){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            rvInsertLine.setLayoutManager(layoutManager);

            //setting adapter for the RecyclerView on the basis of the clicked chart
            setAdapter(id);

        }

        private void setAdapter(int id){
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
                boolean b = true;
                switch (idGraph) {
                    case 0:
                        if(check(adapter.getXAxis()) && check(adapter.getYAxis())) {
                            intent = new Intent(InsertDataActivity.this, LineChartActivity.class);
                            intent.putExtra("names", adapter.getNames());
                            intent.putExtra("xAxises", adapter.getXAxis());
                            intent.putExtra("yAxises", adapter.getYAxis());
                            intent.putExtra("n", adapter.getItemCount());
                        }
                        else{
                            b = false;
                        }
                        break;
                    case 1:
                        if(check(bAdapter.getYAxis())) {
                            intent = new Intent(InsertDataActivity.this, BarChartActivity.class);
                            intent.putExtra("groups", bAdapter.getNames());
                            intent.putExtra("xAxises", bAdapter.getXAxis());
                            intent.putExtra("yAxises", bAdapter.getYAxis());
                            intent.putExtra("n", bAdapter.getItemCount());
                        }
                        else
                        {
                            b = false;
                        }
                        break;
                    case 2:
                        if(check(pAdapter.getvaluesSlice()) && check2(pAdapter.getvaluesSlice())){
                            intent = new Intent(InsertDataActivity.this, PieChartActivity.class);
                            intent.putExtra("names", pAdapter.getNames());
                            intent.putExtra("values", pAdapter.getvaluesSlice());
                            intent.putExtra("n", pAdapter.getItemCount());
                        }
                        else{
                            b = false;
                        }
                        break;
                }
                if(b) startActivity(intent);
                else Toast.makeText(context, context.getResources().getText(R.string.toast_text), Toast.LENGTH_LONG).show();
            }
        }

        private boolean check(String[] strings) {

            for(int i = 0; i < strings.length; i++){
                if(strings[i].length() == 0){
                    return false;
                }
                for(int j = 0; j < strings[i].length(); j++){
                    String a = strings[i].replace(",", "0").replace(".", "0").replace(" ", "0");
                    if(!Character.isDigit(a.charAt(j))){
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean check2(String[] strings) {
            for(int i = 0; i < strings.length; i++){
                if(strings[i].length() == 0){
                    return false;
                }
                for(int j = 0; j < strings[i].length() - 1; j++){
                    String a = strings[i];
                    while(a.startsWith(" ")){
                        a = a.substring(1);
                    }
                    if(!(Character.isDigit(a.charAt(j)) || a.charAt(j) == '.') && Character.isDigit(a.charAt(j + 1))){
                        return false;
                    }
                }
            }
            return true;
        }
    }
}

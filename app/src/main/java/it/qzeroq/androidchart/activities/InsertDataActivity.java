package it.qzeroq.androidchart.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import it.qzeroq.androidchart.R;
import it.qzeroq.androidchart.adapter.InsertDataAdapter;

public class InsertDataActivity extends AppCompatActivity {

    private InsertDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        Holder holder = new Holder();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        holder.rvInsertLine.setLayoutManager(layoutManager);

        adapter = new InsertDataAdapter(this);
        holder.rvInsertLine.setAdapter(adapter);
    }

    class Holder implements View.OnClickListener{

        private RecyclerView rvInsertLine;
        private Button btnAddCard;

        Holder(){
            rvInsertLine = findViewById(R.id.rvInsertLine);
            btnAddCard = findViewById(R.id.btnAddCard);
            btnAddCard.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            adapter.addCard();
        }
    }
}

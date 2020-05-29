package it.qzeroq.androidchart.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import it.qzeroq.androidchart.R;

public class InsertDataAdapter extends RecyclerView.Adapter<InsertDataAdapter.Holder> implements View.OnClickListener {

    private Context context;
    private int c;
    private List<Holder> holders;

    public InsertDataAdapter(Context context){
        this.context = context;
        c = 1;
        holders = new ArrayList<>();
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_view_insert_data, parent, false);
        constraintLayout.setOnClickListener(this);

        return new Holder(constraintLayout);
    }


    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holders.add(holder);
        holder.etNameLine.setText("Function " + (position + 1));
    }


    @Override
    public int getItemCount() {
        return c;
    }


    public void addCard(){
        c++;
        notifyItemInserted(c);
    }


    @Override
    public void onClick(View v) {
        int position = ((RecyclerView) v.getParent()).getChildAdapterPosition(v);
    }

    public String[] getNames(){
        String[] values = new String[c];
        for(int i = 0; i < c; i++){
            values[i] = holders.get(i).etNameLine.getText().toString();
        }
        return values;
    }

    public String[] getXAxis(){
        String[] values = new String[c];
        for(int i = 0; i < c; i++){
            values[i] = holders.get(i).etXAxis.getText().toString();
        }
        return values;
    }

    public String[] getYAxis(){
        String[] values = new String[c];
        for(int i = 0; i < c; i++){
            values[i] = holders.get(i).etYAxis.getText().toString();
        }
        return values;
    }

    static class Holder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener, TextWatcher {

        final TextView tvNameLine, tvXAxis, tvYAxis;
        final EditText etNameLine, etXAxis, etYAxis;
        CheckBox cbDefaultValues;

        Holder(@NonNull View itemView) {
            super(itemView);
            tvNameLine = itemView.findViewById(R.id.tvNameLine);
            tvXAxis = itemView.findViewById(R.id.tvSliceName);
            tvYAxis = itemView.findViewById(R.id.tvValueSlice);
            etNameLine = itemView.findViewById(R.id.etNameLine);
            etXAxis = itemView.findViewById(R.id.etSliceName);
            etYAxis = itemView.findViewById(R.id.etValueSlice);
            cbDefaultValues = itemView.findViewById(R.id.cbDefaultValues);
            cbDefaultValues.setOnCheckedChangeListener(this);
            etYAxis.addTextChangedListener(this);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked){
                int n = countNumbers(etYAxis.getText().toString());
                String numbers = "";
                for(int i = 0; i < n; i++){
                    numbers += (i + 1 + " ");
                }
                etXAxis.setText(numbers);
                etXAxis.setEnabled(false);
            }
            else{
                etXAxis.setText("");
                etXAxis.setEnabled(true);
            }
        }

        private int countNumbers(String text) {
            int n = 0;
            if(text.length() == 0){
                return 0;
            }
            if(Character.isDigit(text.charAt(0))){
                n++;
            }
            for(int i = 0; i < text.length(); i++){
                if(text.charAt(i) == ' '){
                    n++;
                }
            }
            if(text.endsWith(" ")){
                n--;
            }
            return n;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String string = s.toString();
            if(cbDefaultValues.isChecked()) {
                onCheckedChanged(cbDefaultValues, true);
            }
        }
    }
}

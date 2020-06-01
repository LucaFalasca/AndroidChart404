package it.qzeroq.androidchart.adapter.chart;

import android.annotation.SuppressLint;
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

public class BarInsertDataAdapter extends RecyclerView.Adapter<BarInsertDataAdapter.Holder>{
    private int c;
    private List<Holder> holders;

    public BarInsertDataAdapter(){
        c = 1;
        holders = new ArrayList<>();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating the layout for insertion of the BarChart data
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_view_bar_insert_data, parent, false);

        return new Holder(constraintLayout);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holders.add(holder);
        holder.etGroupName.setText(holder.etGroupName.getHint() + " " + (position + 1));
        if(position != 0){
            holder.etXAxis.setText(holders.get(0).etXAxis.getText());
            holder.etXAxis.setVisibility(View.GONE);
            holder.cbDefaultValues.setVisibility(View.GONE);
            holder.tvXAxis.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return c;
    }


    public void addCard() {
        //item counter increment and adding a CardView for the insertion of BarChart data
        c++;
        notifyItemInserted(c);
    }


    public String[] getNames() {
        //getting group names from holders and putting them into an array of strings
        String[] values = new String[c];
        for(int i = 0; i < c; i++){
            values[i] = holders.get(i).etGroupName.getText().toString();
        }
        return values;
    }


    public String[] getXAxis() {
        //getting x-values of the group and putting them into an array of strings
        String[] values = new String[c];
        for(int i = 0; i < c; i++){
            values[i] = holders.get(i).etXAxis.getText().toString();
        }
        return values;
    }


    public String[] getYAxis() {
        //getting y-values of the group and putting them into an array of strings
        String[] values = new String[c];
        for(int i = 0; i < c; i++){
            values[i] = holders.get(i).etYAxis.getText().toString();
        }
        return values;
    }


    public String getGroupName(int i) {
        return holders.get(i).etGroupName.getText().toString();
    }


    public String getXAxis(int i) {
        return holders.get(i).etXAxis.getText().toString();
    }


    public String getYAxis(int i) {
        return holders.get(i).etYAxis.getText().toString();
    }


    public void setGroupName(int i, String s) {
        holders.get(i).etGroupName.setText(s);
    }


    public void setXAxis(int i, String s) {
        holders.get(i).etXAxis.setText(s);
    }


    public void setYAxis(int i, String s) {
        holders.get(i).etYAxis.setText(s);
    }


    static class Holder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener, TextWatcher {

        final TextView tvGroupName, tvXAxis, tvYAxis;
        final EditText etGroupName, etXAxis, etYAxis;
        CheckBox cbDefaultValues;

        Holder(@NonNull View itemView) {
            super(itemView);
            //attaching views by their id
            tvGroupName = itemView.findViewById(R.id.tvGroupName);
            tvXAxis = itemView.findViewById(R.id.tvXAxis);
            tvYAxis = itemView.findViewById(R.id.tvYAxis);
            etGroupName = itemView.findViewById(R.id.etGroupName);
            etXAxis = itemView.findViewById(R.id.etXAxis);
            etYAxis = itemView.findViewById(R.id.etYAxis);
            cbDefaultValues = itemView.findViewById(R.id.cbDefaultValues);

            //setting of listeners
            cbDefaultValues.setOnCheckedChangeListener(this);
            etYAxis.addTextChangedListener(this);
        }


        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked) {
                //self-generated x-values in ascending order if the CompoundButton is checked
                int n = countNumbers(etYAxis.getText().toString());
                StringBuilder numbers = new StringBuilder();
                for(int i = 0; i < n; i++) {
                    numbers.append(i + 1).append(" ");
                }
                //setting the self-generated x-values and disabling changes on the EditText etXAxis
                etXAxis.setText(numbers.toString());

                etXAxis.setEnabled(false);
            }
            else {
                //enabling changes on the EditText etXAxis
                etXAxis.setText("");
                etXAxis.setEnabled(true);
            }
        }


        private int countNumbers(String text) {
            //count of how many data are entered for the y-axis
            int n = 0;
            if(text.length() == 0) {
                return 0;
            }
            if(Character.isDigit(text.charAt(0))) {
                n++;
            }
            for(int i = 0; i < text.length(); i++) {
                if(text.charAt(i) == ' ') {
                    n++;
                }
            }
            if(text.endsWith(" ")) {
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
            //check if the Button cbDefaultValues was changed
            if(cbDefaultValues.isChecked()) {
                onCheckedChanged(cbDefaultValues, true);
            }
        }
    }
}
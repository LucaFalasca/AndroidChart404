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

public class LineInsertDataAdapter extends RecyclerView.Adapter<LineInsertDataAdapter.Holder> {

    private int c;
    private List<Holder> holders;

    public LineInsertDataAdapter(){
        c = 1;
        holders = new ArrayList<>();
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating the layout for insertion of the LineChart data
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_view_line_insert_data, parent, false);

        return new Holder(constraintLayout);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holders.add(holder);
        holder.etLineName.setText(holder.etLineName.getHint() + " " + (position + 1));
    }


    @Override
    public int getItemCount() {
        return c;
    }


    public void addCard() {
        //item counter increment and adding a CardView for the insertion of LineChart data
        c++;
        notifyItemInserted(c);
    }

    public String[] getNames() {
        //getting function names from holders and putting them into an array of strings
        String[] values = new String[c];
        for(int i = 0; i < c; i++) {
            values[i] = holders.get(i).etLineName.getText().toString();
        }
        return values;
    }


    public String[] getXAxis() {
        //getting x-values of the function and putting them into an array of strings
        String[] values = new String[c];
        for(int i = 0; i < c; i++) {
            values[i] = holders.get(i).etXAxis.getText().toString();
        }
        return values;
    }


    public String[] getYAxis() {
        //getting y-values of the function and putting them into an array of strings
        String[] values = new String[c];
        for(int i = 0; i < c; i++) {
            values[i] = holders.get(i).etYAxis.getText().toString();
        }
        return values;
    }




    static class Holder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener, TextWatcher {

        final TextView tvLineName, tvXAxis, tvYAxis;
        final EditText etLineName, etXAxis, etYAxis;
        CheckBox cbDefaultValues;

        Holder(@NonNull View itemView) {
            super(itemView);
            //attaching views by their id
            tvLineName = itemView.findViewById(R.id.tvNameLine);
            tvXAxis = itemView.findViewById(R.id.tvXAxis);
            tvYAxis = itemView.findViewById(R.id.tvYAxis);
            etLineName = itemView.findViewById(R.id.etNameLine);
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

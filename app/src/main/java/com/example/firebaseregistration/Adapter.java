package com.example.firebaseregistration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseregistration.models.CbTestResults;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    Context context;
    List<CbTestResults> cbTestResults = new ArrayList<>();

    public Adapter(Context context, ArrayList<CbTestResults> cbTestResults){
        this.context =context;
        this.cbTestResults = cbTestResults;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv1.setText(String.valueOf(cbTestResults.get(position).getPlate()));
        holder.tv2.setText(String.valueOf(cbTestResults.get(position).getAnswer()));
        holder.tv3.setText(String.valueOf(cbTestResults.get(position).getCorrectAnswer()));
        if(cbTestResults.get(position).getResult() == 1) {
            holder.tv4.setText("Correct");
        }else if(cbTestResults.get(position).getResult() == 0){
            holder.tv4.setText("Incorrect");
        }
    }

    @Override
    public int getItemCount() {
        return cbTestResults.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv1 ,tv2 ,tv3 ,tv4;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.textView1);
            tv2 = itemView.findViewById(R.id.textView2);
            tv3 = itemView.findViewById(R.id.textView3);
            tv4 = itemView.findViewById(R.id.textView4);
        }
    }
}

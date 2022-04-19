package com.example.firebaseregistration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseregistration.models.VATestResults;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

public class VA_Adapter extends RecyclerView.Adapter<VA_Adapter.MyViewHolder> {
    Context context;
    List<VATestResults> vaTestResultsList = new ArrayList<>();

    public VA_Adapter(Context context, ArrayList<VATestResults> vaTestResults){
        this.context = context;
        this.vaTestResultsList = vaTestResults;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_layout2,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv1.setText(vaTestResultsList.get(position).getDate());
        holder.tv2.setText(vaTestResultsList.get(position).getCorrectAnswer());
        holder.tv3.setText(vaTestResultsList.get(position).getAnswer());
        if(vaTestResultsList.get(position).getResult() == 1){
            holder.tv4.setText(vaTestResultsList.get(position).getEyesiteResult());
        }else if(vaTestResultsList.get(position).getResult() == 0){
            holder.tv4.setText("Incorrect");
        }

        holder.tv5.setText(vaTestResultsList.get(position).getDate());
        holder.tv6.setText(vaTestResultsList.get(position).getCorrectAnswer());
        holder.tv7.setText(vaTestResultsList.get(position).getAnswer());
        if(vaTestResultsList.get(position).getResult() == 1){
            holder.tv8.setText(vaTestResultsList.get(position).getEyesiteResult());
        }else if(vaTestResultsList.get(position).getResult() == 0){
            holder.tv8.setText("Incorrect");
        }
    }

    @Override
    public int getItemCount() {
        return vaTestResultsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv1 ,tv2 ,tv3 ,tv4,tv5 ,tv6 ,tv7 ,tv8;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.dateid);
            tv2 = itemView.findViewById(R.id.correctAnswerid);
            tv3 = itemView.findViewById(R.id.answerGivenID);
            tv4 = itemView.findViewById(R.id.TextResult);
            tv5 = itemView.findViewById(R.id.dateid2);
            tv6 = itemView.findViewById(R.id.correctAnswerid2);
            tv7 = itemView.findViewById(R.id.answerGivenID2);
            tv8 = itemView.findViewById(R.id.editTextResult2);

        }
    }
}

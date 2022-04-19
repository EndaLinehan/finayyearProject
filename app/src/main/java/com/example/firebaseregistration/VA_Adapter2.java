package com.example.firebaseregistration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseregistration.models.DuoChromeTestResults;
import com.example.firebaseregistration.models.VATestResults;

import java.util.ArrayList;
import java.util.List;

public class VA_Adapter2 extends RecyclerView.Adapter<VA_Adapter2.MyViewHolder> {

    Context context;
    List<DuoChromeTestResults> duoTestResultsList = new ArrayList<>();

    public VA_Adapter2(Context context, ArrayList<DuoChromeTestResults> duoTestResultsList){
        this.context = context;
        this.duoTestResultsList = duoTestResultsList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_layout3,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv1.setText(duoTestResultsList.get(position).getDate());
        holder.tv2.setText(duoTestResultsList.get(position).getEyesite());


    }


    @Override
    public int getItemCount() {
        return duoTestResultsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv1 ,tv2 ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.textView3);
            tv2 = itemView.findViewById(R.id.textView4);
        }
    }
}


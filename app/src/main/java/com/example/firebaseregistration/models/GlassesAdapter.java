package com.example.firebaseregistration.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firebaseregistration.R;

import java.util.List;


public class GlassesAdapter extends RecyclerView.Adapter<GlassesAdapter.ViewHolder> {

    private Context context;
    List<Glasses> glasses;

    public GlassesAdapter(Context context, List<Glasses> glasses) {
        this.context = context;
        this.glasses = glasses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(glasses.get(position).getImg_url()).into(holder.popularGlasses);
        holder.name.setText(glasses.get(position).getName());
        holder.description.setText(glasses.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return glasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView popularGlasses;
        TextView name,description,image_url;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            popularGlasses = itemView.findViewById(R.id.pop_image);
            name = itemView.findViewById(R.id.product_name_pop);
            description = itemView.findViewById(R.id.product_description_pop);
        }
    }
}

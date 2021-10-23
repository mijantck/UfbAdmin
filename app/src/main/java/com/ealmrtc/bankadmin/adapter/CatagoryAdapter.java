package com.ealmrtc.bankadmin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ealmrtc.bankadmin.R;
import com.ealmrtc.bankadmin.modle.CategoryModle;

import java.util.List;

public class CatagoryAdapter extends RecyclerView.Adapter<CatagoryAdapter.ViewHolder>{

    List<CategoryModle> categoryModles;

    public CatagoryAdapter(List<CategoryModle> categoryModles) {
        this.categoryModles = categoryModles;
    }


    @NonNull
    @Override
    public CatagoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catagory_item, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatagoryAdapter.ViewHolder holder, int position) {
        CategoryModle categoryModle = categoryModles.get(position);

        holder.textView.setText(categoryModle.getCatName());
    }

    @Override
    public int getItemCount() {

        return categoryModles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.catagoryName);


        }
    }
}

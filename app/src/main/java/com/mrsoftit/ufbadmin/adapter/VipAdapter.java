package com.mrsoftit.ufbadmin.adapter;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mrsoftit.ufbadmin.R;
import com.mrsoftit.ufbadmin.activity.AddPackageActivity;
import com.mrsoftit.ufbadmin.modle.PackegModel;


import java.util.List;

public class VipAdapter extends RecyclerView.Adapter<VipAdapter.ViewHolder> {

    List<PackegModel> taskModles ;

    public VipAdapter(List<PackegModel> taskModles ) {
        this.taskModles = taskModles;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView vipImage;
        TextView vipName;
        TextView dailyTask;
        TextView dailyIncome;
        TextView usdt;
        TextView join;


        public ViewHolder(View view) {
            super(view);
            vipImage = view.findViewById(R.id.vipImage);
            vipName = view.findViewById(R.id.vipName);
            dailyTask = view.findViewById(R.id.dailyTask);
            dailyIncome = view.findViewById(R.id.dailyIncome);
            usdt =  view.findViewById(R.id.usdt);
            join = view.findViewById(R.id.join);


        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.vip_task_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        PackegModel vipModel = taskModles.get(position);
        viewHolder.vipName.setText("Pack Name : "+ vipModel.getVipName()+"");
        viewHolder.dailyTask.setText("Daily Task : "+ vipModel.getDailyTask());
        viewHolder.dailyIncome.setText("Daily Income : "+ vipModel.getDailyIncome());
        viewHolder.usdt.setText(vipModel.getUsdtAmount());

        Glide.with(viewHolder.itemView)
                .load(vipModel.getImageUrl())
                .fitCenter()
                .into(viewHolder.vipImage);


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddPackageActivity.class);
                intent.putExtra("pack",vipModel.getVipName());
                intent.putExtra("dailyTask",vipModel.getDailyTask());
                intent.putExtra("dailyIncome",vipModel.getDailyIncome());
                intent.putExtra("UsdtAmount",vipModel.getUsdtAmount());
                intent.putExtra("USDTType",vipModel.getUsdtType());
                intent.putExtra("USDTWallet",vipModel.getWalletAddress());
                intent.putExtra("vipImage",vipModel.getImageUrl());
                intent.putExtra("taskIncomes",vipModel.getTaskIcome());
                intent.putExtra("id",vipModel.getId());
                Toast.makeText(v.getContext(), ""+vipModel.getId(), Toast.LENGTH_SHORT).show();
                v.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return taskModles.size();
    }
}


package com.mrsoftit.ufbadmin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mrsoftit.ufbadmin.R;
import com.mrsoftit.ufbadmin.modle.VipPurchechModel;
import com.mrsoftit.ufbadmin.modle.WithdralModel;

import java.util.List;

public class WithdrallRequstAdapter extends RecyclerView.Adapter<WithdrallRequstAdapter.ExampleViewHolder> {
    private List<WithdralModel> mExampleList;
    private OnItemClickListener mListener;


    public interface OnItemClickListener {
        void onItemClick(int position);
        void onPendingClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView statusID;
        public TextView dateID;
        public TextView vipID;
        public TextView orderID;
        public TextView userLastFourDID;
        public TextView userName;
        public TextView userPhone;
        public TextView userWllet;

        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            statusID = itemView.findViewById(R.id.statusID);
            dateID = itemView.findViewById(R.id.dateID);
            vipID = itemView.findViewById(R.id.vipID);
            orderID = itemView.findViewById(R.id.orderID);
            userLastFourDID = itemView.findViewById(R.id.userLastFourDID);
            userName = itemView.findViewById(R.id.userName);
            userPhone = itemView.findViewById(R.id.userPhone);
            userWllet = itemView.findViewById(R.id.userWllet);




            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            statusID.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onPendingClick(position);
                        }
                    }
                }
            });
        }
    }
    public WithdrallRequstAdapter(List<WithdralModel> exampleList) {
        mExampleList = exampleList;
    }
    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.weathdrall_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }
    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        WithdralModel currentItem = mExampleList.get(position);
        Glide.with(holder.itemView)
                .load(currentItem.getUserImageUrl())
                .fitCenter()
                .into(holder.mImageView);


        holder.statusID.setText(currentItem.getStuts());
        holder.dateID.setText(currentItem.getDateTime());
        holder.vipID.setText(currentItem.getUserRecevceMethod());
        holder.orderID.setText(currentItem.getId());
        holder.userLastFourDID.setText(currentItem.getUserWithdroalCoin()+"");
        holder.userName.setText(currentItem.getUserName());
        holder.userPhone.setText(currentItem.getUserEmail());
        holder.userWllet.setText(currentItem.getUserUsdtInfo());
    }
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}

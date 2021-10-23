package com.ealmrtc.bankadmin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ealmrtc.bankadmin.R;
import com.ealmrtc.bankadmin.modle.VipPurchechModel;

import java.util.List;

public class VipPurchesAdapter extends RecyclerView.Adapter<VipPurchesAdapter.ExampleViewHolder> {
    private List<VipPurchechModel> mExampleList;

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
    public VipPurchesAdapter(List<VipPurchechModel> exampleList) {
        mExampleList = exampleList;
    }
    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }
    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        VipPurchechModel currentItem = mExampleList.get(position);
        Glide.with(holder.itemView)
                .load(currentItem.getImageUrlr())
                .fitCenter()
                .into(holder.mImageView);


        holder.statusID.setText(currentItem.getOderStatus());
        holder.dateID.setText(currentItem.getDate());
        holder.vipID.setText(currentItem.getOrderType());
        holder.orderID.setText(currentItem.getOrderId());
        holder.userLastFourDID.setText(currentItem.getUserAccount());
        holder.userName.setText(currentItem.getUserName());
        holder.userPhone.setText(currentItem.getUserPhone());
        holder.userWllet.setText(currentItem.getUserWallet());
    }
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}

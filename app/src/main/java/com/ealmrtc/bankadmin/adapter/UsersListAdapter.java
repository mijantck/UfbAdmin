package com.ealmrtc.bankadmin.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ealmrtc.bankadmin.R;
import com.ealmrtc.bankadmin.modle.UserModle;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.ViewHolder>{

    List<UserModle> categoryModles;
    Context context;

    public UsersListAdapter(Context context,List<UserModle> categoryModles) {
        this.categoryModles = categoryModles;
        this.context = context;
    }


    @NonNull
    @Override
    public UsersListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersListAdapter.ViewHolder holder, int position) {
        UserModle userModle = categoryModles.get(position);

        holder.userName.setText(userModle.getUserName());
        holder.userEmail.setText(userModle.getUserPhone());
        holder.userCoin.setText(userModle.getCoin()+"");
        holder.active.setText(userModle.getAccountType());

        Glide.with(context)
                .load(userModle.getUserImage())
                .centerCrop()
                .into(holder.userImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  showDialog((Activity) v.getContext(),userModle);
            }
        });
    }

    @Override
    public int getItemCount() {

        return categoryModles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView userName;
        TextView userEmail;
        TextView userCoin;
        TextView active;
        CircleImageView  userImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            userEmail = itemView.findViewById(R.id.userEmail);
            userCoin = itemView.findViewById(R.id.userCoin);
            active = itemView.findViewById(R.id.active);
            userImageView = itemView.findViewById(R.id.userImageView);





        }
    }



    public void showDialog(Activity activity,UserModle userModle) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.customview);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        CircleImageView userImage = dialog.findViewById(R.id.userImage);
        TextView nameD = dialog.findViewById(R.id.nameD);
        TextView emailD = dialog.findViewById(R.id.emailD);
        TextView coinlD = dialog.findViewById(R.id.coinlD);
        TextView activelD = dialog.findViewById(R.id.activelD);
        Glide.with(context)
                .load(userModle.getUserImage())
                .centerCrop()
                .into(userImage);

        nameD.setText(userModle.getUserName());
        emailD.setText(userModle.getUserPhone());
        coinlD.setText(userModle.getCoin()+"");
        activelD.setText(userModle.getActive());

        dialog.show();
    }
}

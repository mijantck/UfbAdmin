package com.mrsoftit.ufbadmin.adapter;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.mrsoftit.ufbadmin.R;
import com.mrsoftit.ufbadmin.activity.TaskActivity;
import com.mrsoftit.ufbadmin.modle.TaskModle;


import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    List<TaskModle> taskModles ;
    public TaskAdapter(List<TaskModle> taskModles ) {
        this.taskModles = taskModles;
    }

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView coinTask;
        TextView nameTask;
        TextView TaskType;
        TextView TaskId;
        TextView TaskTime;
        ImageView categotyImage;

        public ViewHolder(View view,final OnItemClickListener listener) {
            super(view);
            coinTask =  view.findViewById(R.id.coinTask);
            nameTask = view.findViewById(R.id.nameTask);
            TaskType =  view.findViewById(R.id.TaskType);
            TaskId =  view.findViewById(R.id.TaskId);
            TaskTime =  view.findViewById(R.id.TaskTime);
            categotyImage =  view.findViewById(R.id.categotyImage);


            view.setOnClickListener(new View.OnClickListener() {
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

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.youtube_task_item, viewGroup, false);
        return new ViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        TaskModle taskModle = taskModles.get(position);


        int roundVal= (int) Math.round(taskModle.getCoin());
        viewHolder.coinTask.setText(roundVal+"");
        viewHolder.nameTask.setText(taskModle.getName());
        viewHolder.TaskId.setText(taskModle.getId());
        viewHolder.TaskType.setText(taskModle.getTaskType());

        if (taskModle.getCategory().equals("YOUTUBE")){
            viewHolder.categotyImage.setImageResource(R.drawable.youtube_logo);
        } else if (taskModle.getCategory().equals("FACEBOOK")){
            viewHolder.categotyImage.setImageResource(R.drawable.ic_facebook_);
        }else if (taskModle.getCategory().equals("TIKTOK")){
            viewHolder.categotyImage.setImageResource(R.drawable.ic_tiktok_logo_);
        }else if (taskModle.getCategory().equals("INSTAGRAM")){
            viewHolder.categotyImage.setImageResource(R.drawable.ic_instagram_);
        }

//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(v.getContext(), TaskActivity.class);
//                intent.putExtra("url",taskModle.getUrl());
//                intent.putExtra("count",taskModle.getTime()+"");
//                intent.putExtra("taskId",taskModle.getId()+"");
//                intent.putExtra("coin",roundVal+"");
//                Log.d("kldjdf", "onClick: "+taskModle.getCoin());
//                intent.putExtra("name",taskModle.getName()+"");
//                intent.putExtra("taskType",taskModle.getTaskType()+"");
//                v.getContext().startActivity(intent);
//
//
//               /* Intent intent = new Intent(v.getContext(), PlayerActivity.class);
//                intent.putExtra("videoId", youtubeSetData1.getYoutubeVideId());
//                intent.putExtra("channalImageUrl", youtubeSetData1.getYoutubeChannellogoUrl());
//                intent.putExtra("channalName", youtubeSetData1.getYoutubeChannelName());
//                intent.putExtra("videoName", youtubeSetData1.getYoutubeVideoName());
//                intent.putExtra("detailsString", youtubeSetData1.getYoutubeDetails());
//                v.getContext().startActivity(intent);*/
//
//
//            }
//        });
    }
    @Override
    public int getItemCount() {
        return taskModles.size();
    }
}


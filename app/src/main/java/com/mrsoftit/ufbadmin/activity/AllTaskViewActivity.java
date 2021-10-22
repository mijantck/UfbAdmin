package com.mrsoftit.ufbadmin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.mrsoftit.ufbadmin.R;
import com.mrsoftit.ufbadmin.adapter.TaskAdapter;
import com.mrsoftit.ufbadmin.modle.TaskModle;

import java.util.ArrayList;
import java.util.List;

public class AllTaskViewActivity extends AppCompatActivity {

    TaskAdapter taskAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_task_view);

        RecyclerView taskRecycerview = findViewById(R.id.taskRecycerview);

        List<TaskModle> taskModles = new ArrayList<>();

        taskAdapter = new TaskAdapter(taskModles);
        taskRecycerview.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        taskRecycerview.setAdapter(taskAdapter);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("AllTask")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        for (DocumentSnapshot document : task.getResult()) {
                            TaskModle qst = document.toObject(TaskModle.class);
                            taskModles.add(qst);
                            taskAdapter.notifyDataSetChanged();
                        }


                    }

                });

        taskAdapter.setOnItemClickListener(new TaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                new AlertDialog.Builder(AllTaskViewActivity.this)
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                db.collection("AllTask")
                                        .document(taskModles.get(position).getId())
                                        .delete()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                taskModles.remove(position);
                                                taskAdapter.notifyItemRemoved(position);
                                                taskAdapter.notifyDataSetChanged();
                                            }
                                        });

                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }

        });
    }
}
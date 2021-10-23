package com.ealmrtc.bankadmin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.ealmrtc.bankadmin.R;
import com.ealmrtc.bankadmin.adapter.VipAdapter;
import com.ealmrtc.bankadmin.modle.PackegModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ViewAllVipActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();;
    List<PackegModel> vipModels = new ArrayList<>();
    RecyclerView taskRecyclerview;
    VipAdapter vipAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_vip);
        taskRecyclerview = findViewById(R.id.taskRecyclerview);
        vipAdapter = new VipAdapter(vipModels);
        taskRecyclerview.setLayoutManager(new GridLayoutManager(ViewAllVipActivity.this,1));
        taskRecyclerview.setAdapter(vipAdapter);



        db.collection("VIP")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot document : task.getResult()) {
                            PackegModel qst = document.toObject(PackegModel.class);
                            vipModels.add(qst);
                            Collections.sort(vipModels, new Comparator<PackegModel>() {
                                @Override
                                public int compare(PackegModel obj1, PackegModel obj2) {
                                    return obj1.getVipName().compareToIgnoreCase(obj2.getVipName());
                                }
                            });

                            vipAdapter.notifyDataSetChanged();
                            // showNoticeDialog(MainActivity.this,qst);
                        }





                    }

                });


    }
}
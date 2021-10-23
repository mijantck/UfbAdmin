package com.ealmrtc.bankadmin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.ealmrtc.bankadmin.R;
import com.ealmrtc.bankadmin.adapter.WithdrallRequstAdapter;
import com.ealmrtc.bankadmin.modle.WithdralModel;

import java.util.ArrayList;
import java.util.List;

public class PymentCompletActivity extends AppCompatActivity {


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<WithdralModel> vipPurchechModels = new ArrayList<>();


    private RecyclerView mRecyclerView;
    private WithdrallRequstAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pyment_complet);


        buildRecyclerView(PymentCompletActivity.this, vipPurchechModels);


    }


    @Override
    protected void onResume() {
        super.onResume();

        db.collection("allWithdral")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot document : task.getResult()) {
                            WithdralModel qst = document.toObject(WithdralModel.class);
                            if (!qst.getStuts().equals("pending"))

                                vipPurchechModels.add(qst);

                            mAdapter.notifyDataSetChanged();
                        }


                    }

                });

    }

    public void buildRecyclerView(Context context, List<WithdralModel> vipPurchechModels) {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context);
        mAdapter = new WithdrallRequstAdapter(vipPurchechModels);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new WithdrallRequstAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onPendingClick(int position) {

            }
        });
    }


}
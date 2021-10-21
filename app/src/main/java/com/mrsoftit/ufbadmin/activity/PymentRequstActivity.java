package com.mrsoftit.ufbadmin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.mrsoftit.ufbadmin.R;
import com.mrsoftit.ufbadmin.adapter.VipPurchesAdapter;
import com.mrsoftit.ufbadmin.adapter.WithdrallRequstAdapter;
import com.mrsoftit.ufbadmin.modle.VipPurchechModel;
import com.mrsoftit.ufbadmin.modle.WithdralModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PymentRequstActivity extends AppCompatActivity {


    FirebaseFirestore db =  FirebaseFirestore.getInstance(); ;

    List<WithdralModel> vipPurchechModels = new ArrayList<>();


    private RecyclerView mRecyclerView;
    private WithdrallRequstAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pyment_requst);

        buildRecyclerView(PymentRequstActivity.this,vipPurchechModels);


        db.collection("allWithdral")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot document : task.getResult()) {
                            WithdralModel qst = document.toObject(WithdralModel.class);
                            if (qst.getStuts().equals("pending"))
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

                ProgressDialog progress = new ProgressDialog(PymentRequstActivity.this);
                progress.setMessage("Loading...");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.show();


                Date c = Calendar.getInstance().getTime();
                System.out.println("Current time => " + c);
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                String formattedDate = df.format(c);
                String complet = formattedDate+" Complete";




                db.collection("allWithdral")
                        .document(vipPurchechModels.get(position).getId())
                        .update("stuts",complet)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                db.collection("users").document(vipPurchechModels.get(position).getUserid())
                                        .collection("WithdralHistoey").document(vipPurchechModels.get(position).getId())
                                        .update("stuts",complet)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {








                                                vipPurchechModels.remove(position);
                                                mAdapter.notifyItemRemoved(position);
                                                mAdapter.notifyDataSetChanged();

                                                progress.dismiss();
                                            }
                                        });

                            }
                        });

            }
        });
    }



}
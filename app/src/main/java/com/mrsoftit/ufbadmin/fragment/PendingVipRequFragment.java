package com.mrsoftit.ufbadmin.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.mrsoftit.ufbadmin.R;
import com.mrsoftit.ufbadmin.activity.PymentRequstActivity;
import com.mrsoftit.ufbadmin.adapter.VipPurchesAdapter;
import com.mrsoftit.ufbadmin.modle.UserModle;
import com.mrsoftit.ufbadmin.modle.VipPurchechModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PendingVipRequFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PendingVipRequFragment() {
        // Required empty public constructor
    }

    public static PendingVipRequFragment newInstance(String param1, String param2) {
        PendingVipRequFragment fragment = new PendingVipRequFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    FirebaseFirestore db =  FirebaseFirestore.getInstance(); ;

    List<VipPurchechModel> vipPurchechModels = new ArrayList<>();


    private RecyclerView mRecyclerView;
    private VipPurchesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pending_vip_requ, container, false);

        buildRecyclerView(view,vipPurchechModels);


        db.collection("buyVip")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot document : task.getResult()) {
                            VipPurchechModel qst = document.toObject(VipPurchechModel.class);
                            if (qst.getOderStatus().equals("pending"))
                            vipPurchechModels.add(qst);
                            mAdapter.notifyDataSetChanged();

                        }



                    }

                });




        return view;

    }

    public void buildRecyclerView(View view,List<VipPurchechModel> vipPurchechModels) {
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(view.getContext());
        mAdapter = new VipPurchesAdapter(vipPurchechModels);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new VipPurchesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
            @Override
            public void onPendingClick(int position) {

                ProgressDialog progress = new ProgressDialog(getContext());
                progress.setMessage("Loading...");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.show();

                Date c = Calendar.getInstance().getTime();
                System.out.println("Current time => " + c);
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                String formattedDate = df.format(c);
                String complet = formattedDate+" Complete";

                db.collection("users")
                        .document(vipPurchechModels.get(position).getUserId())
                        .update("accountType",vipPurchechModels.get(position).getOrderType())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                db.collection("buyVip")
                                        .document(vipPurchechModels.get(position).getId())
                                        .update("oderStatus",complet)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                db.collection("users")
                                                        .get()
                                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                for (DocumentSnapshot document : task.getResult()) {
                                                                    UserModle qst = document.toObject(UserModle.class);

                                                                    if (qst.getReferCode().equals(vipPurchechModels.get(position).getMyRefer())){

                                                                        Toast.makeText(getContext(), tenp(vipPurchechModels.get(position).getUsdtAmount())+"", Toast.LENGTH_SHORT).show();
                                                                        double coin = qst.getCoin() + tenp(vipPurchechModels.get(position).getUsdtAmount());
                                                                        db.collection("users").document(qst.getuId())
                                                                                .update("coin",coin)
                                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                    @Override
                                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                                        vipPurchechModels.remove(position);
                                                                                        mAdapter.notifyItemRemoved(position);
                                                                                        mAdapter.notifyDataSetChanged();
                                                                                    }
                                                                                });


                                                                        progress.dismiss();
                                                                    }else {
                                                                        vipPurchechModels.remove(position);
                                                                        mAdapter.notifyItemRemoved(position);
                                                                        mAdapter.notifyDataSetChanged();
                                                                        progress.dismiss();
                                                                        Toast.makeText(getContext(), "Refer user Not found", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }

                                                            }

                                                        });




                                            }
                                        });




                            }
                        });


            }
        });
    }


    public double tenp(String e) {

        Log.d("sdfsdfsdf", "tenp: ");
        double res = 0;
        if (!e.equals("")) {
            double amount = Double.parseDouble(e);
            res = (amount / 100.0f) * 18;
            Toast.makeText(getContext(), "" + res, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(), "Amount cannot be empty", Toast.LENGTH_SHORT).show();
        }
        return res;
    }

}
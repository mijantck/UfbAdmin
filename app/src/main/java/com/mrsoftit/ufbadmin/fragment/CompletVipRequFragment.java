package com.mrsoftit.ufbadmin.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.mrsoftit.ufbadmin.modle.VipPurchechModel;

import java.util.ArrayList;
import java.util.List;

public class CompletVipRequFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public CompletVipRequFragment() {
        // Required empty public constructor
    }
    public static CompletVipRequFragment newInstance(String param1, String param2) {
        CompletVipRequFragment fragment = new CompletVipRequFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_complet_vip_requ, container, false);

        db.collection("buyVip")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot document : task.getResult()) {
                            VipPurchechModel qst = document.toObject(VipPurchechModel.class);
                            if (!qst.getOderStatus().equals("pending"))
                                vipPurchechModels.add(qst);
                        }

                        buildRecyclerView(view,vipPurchechModels);

                    }

                });

        return  view;
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

            }
        });
    }
}
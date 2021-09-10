package com.mrsoftit.ufbadmin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mrsoftit.ufbadmin.R;
import com.mrsoftit.ufbadmin.adapter.UsersListAdapter;
import com.mrsoftit.ufbadmin.modle.UserModle;
import com.nordan.dialog.NordanLoadingDialog;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<UserModle> userModles = new ArrayList<>();

    UsersListAdapter usersListAdapter;

    RecyclerView userListRe;

    NordanLoadingDialog nordanLoadingDialog ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        userListRe = findViewById(R.id.userListRe);



        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            UserModle qst = document.toObject(UserModle.class);
                            userModles.add(qst);

                            Log.d("djhfkjsdh", "onComplete: "+qst.getUserEmail());

                        }

                        usersListAdapter = new UsersListAdapter(UserListActivity.this,userModles);
                        userListRe.setLayoutManager(new LinearLayoutManager(UserListActivity.this,LinearLayoutManager.VERTICAL,false));
                        userListRe.setAdapter(usersListAdapter);

                        usersListAdapter.notifyDataSetChanged();

                    }
                });

    }
}
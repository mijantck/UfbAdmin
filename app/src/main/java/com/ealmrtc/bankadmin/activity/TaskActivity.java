package com.ealmrtc.bankadmin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.ealmrtc.bankadmin.R;
import com.ealmrtc.bankadmin.modle.PackegModel;
import com.ealmrtc.bankadmin.modle.TaskModle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.content.ContentValues.TAG;

public class TaskActivity extends AppCompatActivity {

    List<String> options = new ArrayList<>();
    List<String> vip = new ArrayList<>();
    List<String> category = new ArrayList<>();
    TextInputEditText  videoUrl;
    TextInputEditText  taskName;
    TextInputEditText  tasktTime;
    TextInputEditText  taskCoin;
    TextView submitButton;


    Spinner spinnerVip;
    Spinner spinnerCategory;
    Spinner spinnerOptions;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String seletVip;
    String seletCategory;
    String seletOptions;
    long  timeD;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    List<PackegModel> vipModels = new ArrayList<>();


    LinearLayout allTaskView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        spinnerVip = findViewById(R.id.spinnerVip);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerOptions = findViewById(R.id.spinnerOptions);

        videoUrl = findViewById(R.id.videoUrl);
        taskName = findViewById(R.id.taskName);
        tasktTime = findViewById(R.id.tasktTime);
        taskCoin = findViewById(R.id.taskCoin);
        submitButton = findViewById(R.id.submitButton);
        allTaskView = findViewById(R.id.allTaskView);


        allTaskView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TaskActivity.this,AllTaskViewActivity.class));

            }
        });


        options.add("Select task");
        options.add("Like");
        options.add("Subscribe");
        options.add("View");
        options.add("All");

        category.add("Select category");
        category.add("YOUTUBE");
        category.add("FACEBOOK");
        category.add("TIKTOK");
        category.add("INSTAGRAM");
        vip.add("Select VIP");

        db.collection("VIP")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot document : task.getResult()) {
                            PackegModel qst = document.toObject(PackegModel.class);
                            vip.add(qst.getVipName());
                            // showNoticeDialog(MainActivity.this,qst);
                        }

                        spinnerVip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                seletVip = vip.get(position);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                });


        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                seletCategory = category.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                seletOptions = options.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter aavip = new ArrayAdapter(this,android.R.layout.simple_spinner_item,vip);
        aavip.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVip.setAdapter(aavip);

        ArrayAdapter aacategory = new ArrayAdapter(this,android.R.layout.simple_spinner_item,category);
        aacategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(aacategory);

        ArrayAdapter aaoptions = new ArrayAdapter(this,android.R.layout.simple_spinner_item,options);
        aaoptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOptions.setAdapter(aaoptions);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seletCategory.equals("") || seletCategory.equals("Select category")){
                    Toast.makeText(TaskActivity.this, "Select category", Toast.LENGTH_SHORT).show();
                    return;
                }
                String urlS =videoUrl.getText().toString();
                String taskN = taskName.getText().toString();
                String taskTime = tasktTime.getText().toString();
                String taskCo =taskCoin.getText().toString();
                String taskType =seletOptions;

                if (urlS == null){
                    Toast.makeText(TaskActivity.this, "FillUp all", Toast.LENGTH_SHORT).show();
                }
                if (taskN == null){
                    Toast.makeText(TaskActivity.this, "FillUp all", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (taskCo == null){
                    Toast.makeText(TaskActivity.this, "FillUp all", Toast.LENGTH_SHORT).show();
                    return;
                }

                ProgressDialog progress = new ProgressDialog(TaskActivity.this);
                progress.setMessage("Loading...");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.show();

               // timeD = TimeUnit.MINUTES.toMillis(Long.parseLong(taskTime));
                double co = Double.parseDouble(taskCo);
                Calendar date = Calendar.getInstance();
                long millisecondsDate = date.getTimeInMillis();

                TaskModle taskModle = new TaskModle(null,taskN,urlS,10000,co,"TASK",millisecondsDate,"VIP",seletCategory);
                db.collection("AllTask")
                        .add(taskModle)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                String id  = documentReference.getId();
                                db.collection("AllTask")
                                        .document(id).update("id",id)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                progress.dismiss();
                                                startActivity(new Intent(TaskActivity.this,AllTaskViewActivity.class));

                                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                            }
                                        });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });
            }
        });


    }

}
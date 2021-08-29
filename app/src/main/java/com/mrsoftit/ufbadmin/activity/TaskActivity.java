package com.mrsoftit.ufbadmin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mrsoftit.ufbadmin.R;
import com.mrsoftit.ufbadmin.modle.TaskModle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static android.content.ContentValues.TAG;

public class TaskActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    List<String> options = new ArrayList<>();
    TextInputEditText  videoUrl;
    TextInputEditText  taskName;
    TextInputEditText  tasktTime;
    TextInputEditText  taskCoin;
    TextView submitButton;


    Spinner spin;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String seletType;
    long  timeD;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        spin = findViewById(R.id.spinner);
        videoUrl = findViewById(R.id.videoUrl);
        taskName = findViewById(R.id.taskName);
        tasktTime = findViewById(R.id.tasktTime);
        taskCoin = findViewById(R.id.taskCoin);
        submitButton = findViewById(R.id.submitButton);


        options.add("Select Category");
        options.add("Like");
        options.add("Subscribe");
        options.add("View");
        options.add("All");
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,options);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlS =videoUrl.getText().toString();
                String taskN = taskName.getText().toString();
                String taskTime = tasktTime.getText().toString();
                String taskCo =taskCoin.getText().toString();
                String taskType =seletType;


                if (urlS == null || taskN == null || taskTime == null || taskCo == null || taskType == null){
                    Toast.makeText(TaskActivity.this, "FillUp all", Toast.LENGTH_SHORT).show();

                    return;
                }

              /*  if (taskTime.contains(".")){
                    String imageName = taskTime;
                    String replace = imageName.replace('.','~');
                    String[] split = replace.split("~");

                    System.out.println("Image name : " + split[0]);
                    System.out.println("Image extension : " + split[1]);

                    String  one = split[0];
                    String  tw = split[1];
                    long  onew = TimeUnit.MINUTES.toMillis(Long.parseLong(one));
                    long  twoooo = TimeUnit.SECONDS.toMillis(Long.parseLong(tw));
                    timeD = onew +twoooo;

                    Log.d("kdjflkjlkjklga", "onClick: "+onew +"\n"+twoooo);

                }else {

                }*/
                timeD = TimeUnit.MINUTES.toMillis(Long.parseLong(taskTime));


                double co = Double.parseDouble(taskCo);
                Calendar date = Calendar.getInstance();
                long millisecondsDate = date.getTimeInMillis();


                TaskModle taskModle = new TaskModle(null,taskN,urlS,timeD,co,taskType,millisecondsDate);

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        seletType = options.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
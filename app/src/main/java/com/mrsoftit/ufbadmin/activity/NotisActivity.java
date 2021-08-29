package com.mrsoftit.ufbadmin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.mrsoftit.ufbadmin.modle.Notis;

import static android.content.ContentValues.TAG;

public class NotisActivity extends AppCompatActivity {


    TextInputEditText notisName;
    TextInputEditText  DetailsId;
    TextView suvmit;

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notis);

        notisName = findViewById(R.id.notisName);
        DetailsId = findViewById(R.id.DetailsId);
        suvmit = findViewById(R.id.submitButton);

        suvmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String notisName1 =notisName.getText().toString();
                String DetailsId1 = DetailsId.getText().toString();

                if (notisName1 == null || DetailsId1 == null ){
                    Toast.makeText(NotisActivity.this, "FillUp all", Toast.LENGTH_SHORT).show();
                    return;
                }

                Notis  notis = new Notis(null,notisName1,DetailsId1);

                db.collection("Notis")
                        .add(notis)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                String id  = documentReference.getId();
                                db.collection("Notis")
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
}
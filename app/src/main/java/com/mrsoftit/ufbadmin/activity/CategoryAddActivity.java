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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mrsoftit.ufbadmin.R;
import com.mrsoftit.ufbadmin.modle.CategoryModle;

import static android.content.ContentValues.TAG;

public class CategoryAddActivity extends AppCompatActivity {



    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextInputEditText categoryName;
    TextView submitButton;
    //Database




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_add);

        categoryName = findViewById(R.id.categoryName);
        submitButton = findViewById(R.id.submitButton);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String catName = categoryName.getText().toString();
                if (catName.isEmpty()){
                    Toast.makeText(CategoryAddActivity.this, "Input is Empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                CategoryModle  categoryModle =  new CategoryModle(catName,null);

                db.collection("category")
                        .add(categoryModle)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                String id  = documentReference.getId();
                                db.collection("category")
                                        .document(id).update("catID",id)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                categoryName.setText("");
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
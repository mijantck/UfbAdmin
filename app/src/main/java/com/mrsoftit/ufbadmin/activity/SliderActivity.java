package com.mrsoftit.ufbadmin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.OnProgressListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mrsoftit.ufbadmin.R;
import com.mrsoftit.ufbadmin.modle.SliderModle;

import static android.content.ContentValues.TAG;
import static com.google.common.io.Files.getFileExtension;

public class SliderActivity extends AppCompatActivity {

    TextInputEditText videoUrl;
    TextInputEditText SliderName;
    ImageView imagePreview;
    TextView pickButton;
    TextView submitButton;

    StorageReference mStorageref;

    StorageReference filepath;;
    FirebaseStorage storage = FirebaseStorage.getInstance();

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Uri selectedImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        videoUrl = findViewById(R.id.videoUrl);
        SliderName = findViewById(R.id.SliderName);
        imagePreview = findViewById(R.id.imagePreview);
        pickButton = findViewById(R.id.pickButton);
        submitButton = findViewById(R.id.submitButton);


        mStorageref = FirebaseStorage.getInstance().getReference("Upload Photos");


        pickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);//one can be replaced with any action code
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadfile();


            }
        });
    }

    private void uploadfile() {

        String name = SliderName.getText().toString();
        String videoUrl1 = videoUrl.getText().toString();

        if (selectedImage !=null){
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();
            StorageReference filereference  = mStorageref.child(System.currentTimeMillis()+ "."+getFileExtension(String.valueOf(selectedImage)));

            filereference.putFile(selectedImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(SliderActivity.this, "Upload Successfully", Toast.LENGTH_SHORT).show();

                            filereference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Uri downloadUrl = uri;
                                    progressDialog.show();

                                    SliderModle sliderModle = new SliderModle(null,videoUrl1 ,name,downloadUrl+"");
                                    db.collection("Slider")
                                            .add(sliderModle)
                                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(DocumentReference documentReference) {
                                                    String id  = documentReference.getId();
                                                    db.collection("Slider")
                                                            .document(id).update("id",id)
                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    progressDialog.setCanceledOnTouchOutside(false);
                                                                    progressDialog.dismiss();
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
                                    Log.d("lsdkjfklsj", "onSuccess:  "+downloadUrl);

                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    })
                    .addOnProgressListener(new com.google.firebase.storage.OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double progress = (100.0*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                            progressDialog.setCanceledOnTouchOutside(false);
                            progressDialog.setMessage("Uploaded  " +(int)progress+"%");
                        }
                    });
        }else
            Toast.makeText(this, "Please Select a Image", Toast.LENGTH_SHORT).show();
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    selectedImage = imageReturnedIntent.getData();
                    imagePreview.setImageURI(selectedImage);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    selectedImage = imageReturnedIntent.getData();
                    imagePreview.setImageURI(selectedImage);
                }
                break;
        }
    }
}
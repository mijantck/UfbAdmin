package com.mrsoftit.ufbadmin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mrsoftit.ufbadmin.R;
import com.mrsoftit.ufbadmin.modle.PackegModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class AddPackageActivity extends AppCompatActivity {
    private static final int EXTERNAL_PERMISSION_CODE = 1234;
    FirebaseFirestore db = FirebaseFirestore.getInstance();;
    private ImageView selectImage;
    private Uri filePath = null;
    private final int PICK_IMAGE_REQUEST = 22;

    FirebaseStorage storage;
    StorageReference storageReference;
    ProgressDialog progressDialog;

    MaterialButton submitId;
    MaterialButton VipList;

    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_GALLARY = 2;
    Uri outPutfileUri;

    EditText VipName, DailyTask, DailyIncome, USDTAmout, USDTType, WalletAddress;
    String id = null;
    String packs;
    String dailyTask;
    String dailyIncome;
    String UsdtAmount;
    String USDTTypes;
    String USDTWallets;
    String vipImage;

    ProgressDialog  progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_package);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        WalletAddress = findViewById(R.id.WalletAddress);
        VipName = findViewById(R.id.VipName);
        DailyTask = findViewById(R.id.DailyTask);
        DailyIncome = findViewById(R.id.DailyIncome);
        USDTAmout = findViewById(R.id.USDTAmout);
        USDTType = findViewById(R.id.USDTType);
        selectImage = findViewById(R.id.selectImage);
        submitId = findViewById(R.id.submitId);
        VipList = findViewById(R.id.VipList);

        Intent intent = getIntent();

        if (intent != null) {
            packs = intent.getStringExtra("pack");
            dailyTask = intent.getStringExtra("dailyTask");
            dailyIncome = intent.getStringExtra("dailyIncome");
            UsdtAmount = intent.getStringExtra("UsdtAmount");
            USDTTypes = intent.getStringExtra("USDTType");
            USDTWallets = intent.getStringExtra("USDTWallet");
            vipImage = intent.getStringExtra("vipImage");
            id = intent.getStringExtra("id");

            WalletAddress.setText(USDTWallets);
            VipName.setText(packs);
            DailyTask.setText(dailyTask);
            DailyIncome.setText(dailyIncome);
            USDTAmout.setText(UsdtAmount);
            USDTType.setText(USDTTypes);

            Glide.with(getApplicationContext())
                    .load(vipImage)
                    .fitCenter()
                    .into(selectImage);

        }
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        VipList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AddPackageActivity.this, ViewAllVipActivity.class));


            }
        });
        Button galleryButton = (Button) findViewById(R.id.galleryID);
        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    //start image picker
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    // Start the Intent
                    startActivityForResult(galleryIntent, PICK_FROM_GALLARY);
                } else {
                    //ask permission
                    requestStoragePermission();
                }
            }
        });


        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    //start image picker
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    // Start the Intent
                    startActivityForResult(galleryIntent, PICK_FROM_GALLARY);
                } else {
                    //ask permission
                    requestStoragePermission();
                }
            }
        });


        submitId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String VipName1 = VipName.getText().toString();
                String DailyTask1 = DailyTask.getText().toString();
                String DailyIncome1 = DailyIncome.getText().toString();
                String USDTAmout1 = USDTAmout.getText().toString();
                String USDTType1 = USDTType.getText().toString();
                String WalletAddress1 = WalletAddress.getText().toString();

                if (VipName1.equals("")) {
                    Toast.makeText(AddPackageActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (DailyTask1.equals("")) {
                    Toast.makeText(AddPackageActivity.this, "Enter Daily Task", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (DailyIncome1.equals("")) {
                    Toast.makeText(AddPackageActivity.this, "Enter Daily Income", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (USDTType1.equals("")) {
                    Toast.makeText(AddPackageActivity.this, "Enter USDT Type ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (WalletAddress1.equals("")) {
                    Toast.makeText(AddPackageActivity.this, "Enter Wallet Address", Toast.LENGTH_SHORT).show();
                    return;
                }
                progress = new ProgressDialog(AddPackageActivity.this);
                progress.setMessage("Loading...");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.show();

                if (id != null && filePath == null ) {
                    uploadData(VipName.getText().toString(), DailyTask.getText().toString(), DailyIncome.getText().toString(), USDTAmout.getText().toString(), USDTType.getText().toString(), WalletAddress.getText().toString(), vipImage);

                } else {
                    if (filePath != null) {
                        uploadImage(VipName1, DailyTask1, DailyIncome1, USDTAmout1, USDTType1, WalletAddress1);
                    } else {
                        Toast.makeText(AddPackageActivity.this, "Input Image", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    //start image picker
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    // Start the Intent
                    startActivityForResult(galleryIntent, PICK_FROM_GALLARY);
                } else {
                    //ask permission
                    requestStoragePermission();
                }
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICK_FROM_CAMERA:
                Log.d("fsdfdsfds", "onActivityResult: ");
                if (resultCode == RESULT_OK) {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    selectImage.setImageBitmap(photo);
                    // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                    Uri tempUri = getImageUri(getApplicationContext(), photo);
                    outPutfileUri = tempUri;
                }
                break;

            case PICK_FROM_GALLARY:
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedImage = data.getData();
                    outPutfileUri = selectedImage;
                    filePath = data.getData();
                    selectImage.setImageURI(filePath);
                }
                break;
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private boolean checkPermission() {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        return currentAPIVersion < Build.VERSION_CODES.M || (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == EXTERNAL_PERMISSION_CODE) {
            if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                setResult(RESULT_CANCELED);
                finish();
            }
        }
    }


    public void uploadData(String VipName, String DailyTask, String DailyIncome, String USDTAmout, String USDTType, String WalletAddress, String Imageurl) {
        PackegModel packegModel = new PackegModel(id, USDTAmout, USDTType, WalletAddress, Imageurl, DailyTask, DailyIncome, VipName);
        if (id != null) {
            db.collection("VIP").document(id)
                    .set(packegModel)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            progress.dismiss();
                            startActivity(new Intent(AddPackageActivity.this,ViewAllVipActivity.class));
                            finish();
                        }
                    });
        } else {
            db.collection("VIP")
                    .add(packegModel)
                    .addOnCompleteListener(task -> {
                        String idd = task.getResult().getId();

                        db.collection("VIP")
                                .document(idd)
                                .update("id", idd)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        progress.dismiss();
                                        startActivity(new Intent(AddPackageActivity.this,ViewAllVipActivity.class));
                                        finish();

                                    }
                                });
                    });
        }

    }


    private void uploadImage(String VipName, String DailyTask, String DailyIncome, String USDTAmout, String USDTType, String WalletAddress) {
        if (outPutfileUri != null) {
            progressDialog.show();
            StorageReference ref = storageReference.child("image/" + UUID.randomUUID().toString());
            ref.putFile(outPutfileUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri downloadUrl) {
                                    //do something with downloadurl
                                    uploadData(VipName, DailyTask, DailyIncome, USDTAmout, USDTType, WalletAddress, downloadUrl.toString());
                                    progressDialog.dismiss();
                                }
                            });

                            Toast.makeText(AddPackageActivity.this, "Image Uploaded!!", Toast.LENGTH_SHORT).show();
                        }
                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Error, Image not uploaded
                            progressDialog.dismiss();

                            Toast.makeText(AddPackageActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        }
    }
}
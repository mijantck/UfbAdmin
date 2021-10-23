package com.ealmrtc.bankadmin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ealmrtc.bankadmin.R;

public class MainActivity extends AppCompatActivity {


    LinearLayout userInfo;
    LinearLayout task;
    LinearLayout videoAdd;
    LinearLayout categoryAdd;
    LinearLayout slideradd;
    LinearLayout adsSetting;
    LinearLayout pymentRequst;
    LinearLayout completPyamet;
    LinearLayout promoton;
    LinearLayout notisAdd;
    LinearLayout PackageRequeste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userInfo = findViewById(R.id.userInfo);
        task = findViewById(R.id.task);
        videoAdd = findViewById(R.id.videoAdd);
        categoryAdd = findViewById(R.id.categoryAdd);
        slideradd = findViewById(R.id.slideradd);
        adsSetting = findViewById(R.id.adsSetting);
        pymentRequst = findViewById(R.id.pymentRequst);
        completPyamet = findViewById(R.id.completPyamet);
        promoton = findViewById(R.id.promoton);
        notisAdd = findViewById(R.id.notisAdd);
        PackageRequeste = findViewById(R.id.PackageRequeste);



        PackageRequeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VipPurchechReActivity.class));
            }
        });
        userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UserListActivity.class));
            }
        });
        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TaskActivity.class));

            }
        });
        videoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VideoAddActivity.class));

            }
        });
        categoryAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddYouTubeChannalActivity.class));


            }
        });
        slideradd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SliderActivity.class));
            }
        });
        adsSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "adsSetting", Toast.LENGTH_SHORT).show();
            }
        });

        pymentRequst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,PymentRequstActivity.class));

            }
        });

        completPyamet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PymentCompletActivity.class));

            }
        });
        promoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "promoton", Toast.LENGTH_SHORT).show();
            }
        });
        notisAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NotisActivity.class));
            }
        });


        findViewById(R.id.socialLink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SocialLinkActivity.class));
            }
        });


        findViewById(R.id.pack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddPackageActivity.class));
            }
        });
    }
}
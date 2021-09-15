package com.mrsoftit.ufbadmin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mrsoftit.ufbadmin.R;

import static android.content.ContentValues.TAG;

public class SocialLinkActivity extends AppCompatActivity {



    FirebaseFirestore db = FirebaseFirestore.getInstance();

    TextInputEditText  TeamSupportEditeText;
    TextInputEditText  JoinTheCommunityEditeExt;
    TextInputEditText  HelpcentarEditeText;
    TextView HelpcentarButton;
    TextView JoinTheCommunityButton;
    TextView TeamSupporButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_link);

        TeamSupportEditeText = findViewById(R.id.TeamSupportEditeText);
        JoinTheCommunityEditeExt = findViewById(R.id.JoinTheCommunityEditeExt);
        HelpcentarEditeText = findViewById(R.id.HelpcentarEditeText);
        HelpcentarButton = findViewById(R.id.HelpcentarButton);
        JoinTheCommunityButton = findViewById(R.id.JoinTheCommunityButton);
        TeamSupporButton = findViewById(R.id.TeamSupporButton);

        HelpcentarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String HelpcentarEditeTextS = HelpcentarEditeText.getText().toString();
                if (HelpcentarEditeTextS.equals("")){
                    Toast.makeText(SocialLinkActivity.this, "Enter Help centar Link", Toast.LENGTH_SHORT).show();
                    return;
                }

                addSocialLink(HelpcentarEditeTextS,"helpCenter",HelpcentarEditeText);


            }
        });


        JoinTheCommunityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String HelpcentarEditeTextS = JoinTheCommunityEditeExt.getText().toString();
                if (HelpcentarEditeTextS.equals("")){
                    Toast.makeText(SocialLinkActivity.this, "Enter join The Community Link", Toast.LENGTH_SHORT).show();
                    return;
                }

                addSocialLink(HelpcentarEditeTextS,"joinTheCommunity",JoinTheCommunityEditeExt);


            }
        });


        TeamSupporButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String HelpcentarEditeTextS = TeamSupportEditeText.getText().toString();
                if (HelpcentarEditeTextS.equals("")){
                    Toast.makeText(SocialLinkActivity.this, "Enter Team Support  Link", Toast.LENGTH_SHORT).show();
                    return;
                }

                addSocialLink(HelpcentarEditeTextS,"teamSupport",TeamSupportEditeText);



            }
        });


    }


    public void addSocialLink(String link,String fildName,TextInputEditText empty){

        db.collection("Social")
                .document("kdsfjhJHjhdkjl").update(fildName,link)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        empty.setText("");
                    }
                });
    }
}
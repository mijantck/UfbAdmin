package com.mrsoftit.ufbadmin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mrsoftit.ufbadmin.R;
import com.mrsoftit.ufbadmin.modle.WelcomeLink;
import com.mrsoftit.ufbadmin.modle.YoutubeSetData;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.ContentValues.TAG;

public class AddYouTubeChannalActivity extends AppCompatActivity {


    TextInputEditText youtube_add_edite_text;
    TextInputEditText welcomeCoin;
    TextInputEditText textFoeUser;
    TextView youtube_link_add_text;

    String videoImageURL;
    String videoNameID;
    String videoID;
    String videoName;
    String channelImageUrl;
    String channelName;
    String channelID;
    String details;
    CircleImageView channel_logo;
    TextView channel_namae;
    TextView channel_id;
    TextView addVideoButton;

    int currentPosition = -1;
    String seleceVideoType = "views";

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference youtubeInfoAdd;

    LinearLayout videoInfoLayout;
    String videoCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_you_tube_channal);
        youtube_add_edite_text = findViewById(R.id.videoUrl);
        youtube_link_add_text = findViewById(R.id.submitButton);
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        channel_logo = findViewById(R.id.channel_logo);
        channel_namae = findViewById(R.id.channel_namae);
        channel_id = findViewById(R.id.channel_id);
        videoInfoLayout = findViewById(R.id.videoInfoLayout);
        addVideoButton = findViewById(R.id.addVideoButton);
        welcomeCoin = findViewById(R.id.welcomeCoin);
        textFoeUser = findViewById(R.id.textFoeUser);

        youtube_link_add_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = youtube_add_edite_text.getText().toString();

                if (url.isEmpty()){
                    Toast.makeText(AddYouTubeChannalActivity.this, "Enter Url", Toast.LENGTH_SHORT).show();
                    return;
                }
                videoImageNameId videoImageNameId = new videoImageNameId(url);
                try {
                    String fulUrl = videoImageNameId.execute().get();


                    if (fulUrl == null){


                    }else {
                        String[] parts = fulUrl.split("#");
                        if (parts.length  <4){
                            Toast.makeText(AddYouTubeChannalActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                            Log.d("sdf", "onClick: ");
                            return;
                        }else {
                            videoImageURL = parts[0];
                            videoNameID = parts[1];
                            videoID = parts[2];
                            channelID = parts[3];
                            details = parts[4];
                            Log.d("skjflksdjfkjsd", "onClick: "+channelID);
                        }

                        Toast.makeText(AddYouTubeChannalActivity.this, "out", Toast.LENGTH_SHORT).show();

                       ChannelIconName channelIconName = new ChannelIconName(channelID);
                        String currentId =  channelIconName.execute().get();

                        String[] channelNameIcon = currentId.split("#");
                        String channelImageUrl1 = channelNameIcon[0];
                        channelImageUrl = channelImageUrl1;

                        String name = channelNameIcon[1];
                        channelName = name;

                        videoInfoLayout.setVisibility(View.VISIBLE);



                        Picasso.get()
                                .load(channelImageUrl)
                                .into(channel_logo);
                        channel_namae.setText(channelName);
                        channel_id.setText(channelID);
                    }

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        addVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = youtube_add_edite_text.getText().toString();
                String welcomeCoin1 = welcomeCoin.getText().toString();
                String textFoeUser1 = textFoeUser.getText().toString();

                if (url.isEmpty()){
                    Toast.makeText(AddYouTubeChannalActivity.this, "Enter Url", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (welcomeCoin1.isEmpty()){
                    Toast.makeText(AddYouTubeChannalActivity.this, "Add coin", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (textFoeUser1.isEmpty()){
                    Toast.makeText(AddYouTubeChannalActivity.this, "Welcome text", Toast.LENGTH_SHORT).show();
                    return;
                }

                int coin = Integer.parseInt(welcomeCoin1);
                WelcomeLink welcomeLink = new WelcomeLink(url,channelName,channelImageUrl,channelID,textFoeUser1,coin);
                db.collection("WelcomeLink").document("LKHSLHJHDKSJH")
                        .set(welcomeLink)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                youtube_add_edite_text.setText("");
                                welcomeCoin.setText("");
                                textFoeUser.setText("");
                            }
                        });

            }
        });
    }

    private class videoImageNameId extends AsyncTask<Void, String, String> {
        String youtubeLink;

        public videoImageNameId(String youtubeLink) {
            this.youtubeLink = youtubeLink;
        }


        @Override
        protected String doInBackground(Void... voids) {
            String Videoname = null;
            String videoimageUrl = null;
            String channelid = null;
            String videID = null;
            String details  = null;

            try {
                Document doc  = Jsoup.connect(youtubeLink)
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                        .get();


                Elements metaOgTitle = doc.select("meta[property=og:title]");
                if (metaOgTitle!=null) {
                    Videoname = metaOgTitle.attr("content");
                    Log.d("Videoname", "doInBackground: "+Videoname);
                }
                Elements metaOgImage = doc.select("meta[property=og:image]");


                if (metaOgImage!=null) {
                    videoimageUrl = metaOgImage.attr("content");
                    Log.d("Videoname", "doInBackground: "+videoimageUrl);

                }
                Elements metaDetail = doc.select("meta[name=description]");

                if (metaDetail != null){
                    details = metaDetail.attr("content");
                    Log.d("kgklfdjkjgkd", "doInBackground: "+details);
                }

                Elements meImage = doc.select("meta[itemprop=channelId]");
                if (meImage!=null){
                    channelid = meImage.attr("content");
                    Log.d("Videoname", "doInBackground: "+channelid);
                }
                Elements videID1 = doc.select("meta[itemprop=videoId]");
                if (meImage!=null){
                    videID = videID1.attr("content");
                    Log.d("Videoname", "doInBackground: "+videID);
                }
                Log.d("Videoname", videoimageUrl+"\n"+Videoname+"\n"+videID+"\n"+channelid);

                return videoimageUrl+"#"+Videoname+"#"+videID+"#"+channelid+"#"+details;


            } catch (Exception e) {
                Log.d("youu", "doInBackground: "+e.getMessage());
                return e.getMessage();
            }
        }
    }

    private class ChannelIconName extends AsyncTask<Void, String, String> {

        String id;

        public ChannelIconName(String id) {
            this.id = id;

        }

        @Override
        protected String doInBackground(Void... voids) {
            String newVersion = null;
            String name = null;
            String imageUrlaaa = null;

            try {
                Document doc  = Jsoup.connect("https://www.youtube.com/channel/"+id)
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                        .get();


                Elements metaOgTitle = doc.select("meta[property=og:title]");
                if (metaOgTitle!=null) {
                    name = metaOgTitle.attr("content");
                }

                Elements metaOgImage = doc.select("meta[property=og:image]");

                Elements meImage = doc.select("meta[itemprop=channelId]");
                if (meImage!=null) {
                    imageUrlaaa = metaOgImage.attr("content");
                    Log.d("jsdfhjsdh", "doInBackground: "+imageUrlaaa);

                }
                return imageUrlaaa+"#"+name;
            } catch (Exception e) {
                return newVersion;
            }
        }
    }
}
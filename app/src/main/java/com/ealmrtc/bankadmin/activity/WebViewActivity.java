package com.ealmrtc.bankadmin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.widget.FrameLayout;

import com.ealmrtc.bankadmin.HTML5WebView;
import com.ealmrtc.bankadmin.R;

public class WebViewActivity extends AppCompatActivity {



    FrameLayout webViewEm;
    HTML5WebView mWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);



        mWebView = new HTML5WebView(this);
        webViewEm = findViewById(R.id.webViewEm);




      //  setVideoView(videoUrl1);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.OFF);
        mWebView.getSettings().setAllowFileAccess(true);
        webViewEm.removeView(mWebView.getLayout());

        webViewEm.addView(mWebView.getLayout());


    }

    private void setVideoView(String videoId) {

        mWebView.loadUrl(videoId);
//
//        if (websiteName.equals("daily motion")) {
//            mWebView.loadUrl("https://www.dailymotion.com/embed/video/" + videoId);
//        } else if (websiteName.equals("vimeo")) {
//            mWebView.loadUrl("http://player.vimeo.com/video/" + videoId + "?player_id=player&autoplay=1&title=0&byline=0&portrait=0&api=1&maxheight=480&maxwidth=800");
//        } else {
//            mWebView.loadUrl(videoId);
//        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mWebView.handleBack();
    }
}
package com.beshoykamal.movieappui.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import com.beshoykamal.movieappui.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import java.util.Vector;

public class Video_Player_Activity extends AppCompatActivity {

    private PlayerView playerView;
    private SimpleExoPlayer simpleExoPlayer;
    public static final String VIDEO_TEST_URL="https://www.youtube.com/watch?v=";
    String Key;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video__player_);

         Key = getIntent().getExtras().getString("key");
        methodhideActionBar();
//        methodExoOlayer();
//        methodSetfullScreen();

        methodWbView();
    }

    private void methodWbView() {
        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAppCacheEnabled(true);

        webView.setWebChromeClient(new WebChromeClient(){

        });
        String URL = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+Key+"\" frameborder=\"0\"allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
        webView.loadData(URL,"text/html","utf-8");
    }

    private void methodhideActionBar() {
        getSupportActionBar().hide();
    }

    private void methodSetfullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void methodExoOlayer() {
        playerView=findViewById(R.id.movie_exo_player);

        simpleExoPlayer= ExoPlayerFactory.newSimpleInstance(this);
        playerView.setPlayer(simpleExoPlayer);
        DataSource.Factory datasourcefactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this,"appname"));


        MediaSource videoSource = new ExtractorMediaSource.Factory(datasourcefactory).createMediaSource(Uri.parse(VIDEO_TEST_URL));
        simpleExoPlayer.prepare(videoSource);
        simpleExoPlayer.setPlayWhenReady(true);

    }


    //    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        simpleExoPlayer.release();
//    }
}

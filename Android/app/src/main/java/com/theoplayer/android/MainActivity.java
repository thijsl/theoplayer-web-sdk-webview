package com.theoplayer.android;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        WebView.setWebContentsDebuggingEnabled(true);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setGeolocationEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setBlockNetworkImage(false);
        webSettings.setBlockNetworkLoads(false);
        webSettings.setNeedInitialFocus(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        Boolean hideDefaultVideoPoster = true;
        if (hideDefaultVideoPoster) {
            // https://stackoverflow.com/questions/39875102/disable-default-ugly-poster-image-in-html5-video-tag-on-android
            webView.setWebChromeClient(new WebChromeClient() {
                @Override public Bitmap getDefaultVideoPoster() {
                    final Bitmap bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
                    Canvas canvas = new Canvas(bitmap);
                    // Use whatever color you want here. You could probably use a transparent color
                    canvas.drawARGB(255, 0, 0, 0);
                    return bitmap;
                }
            });
        }
        Boolean allowFullscreenVideoPlayback = false;
        if (allowFullscreenVideoPlayback) {
            // currently not demonstrated. Feel free to add, e.g. URL below:
            // https://stackoverflow.com/questions/15768837/playing-html5-video-on-fullscreen-in-android-webview
        }
        webView.loadUrl("file:///android_asset/app.html");
        Button clickButton = (Button) findViewById(R.id.button);
        clickButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.evaluateJavascript("reloadStream(true)", null);
            }
        });
    }

}
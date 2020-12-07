package com.covidtracker;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import static android.content.ContentValues.TAG;

//Rayan Treebhowon N01226282 RNA

public class WebView extends FragmentActivity {

    android.webkit.WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        webview = (android.webkit.WebView) findViewById(R.id.webView1);
        WebSettings webSetting = webview.getSettings();
        webview.setWebViewClient(new WebViewClient());
        webSetting.setBuiltInZoomControls(true);

        //define your website url here which you want to open in webview.
        webview.loadUrl("https://www.uefa.com/");
    }

    private class WebViewClient extends android.webkit.WebViewClient {
        // deprecated in API 24
        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
            //return super.shouldOverrideUrlLoading(view, url);
            final Uri uri = Uri.parse(url);
            return handleUri(uri);
        }

        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(android.webkit.WebView view, WebResourceRequest webResourceRequest) {
            final Uri uri = webResourceRequest.getUrl();
            return handleUri(uri);
        }

        private boolean handleUri(final Uri uri) {
            Log.i(TAG, "Uri =" + uri);
            final String host = uri.getHost();
            final String scheme = uri.getScheme();
            return false;

        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webview.canGoBack()) {
                        webview.goBack();
                    } else {
                        finish();
                    }
                    Toast.makeText(getApplicationContext(), "Returning to Previous Screen", Toast.LENGTH_LONG).show();
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }


}
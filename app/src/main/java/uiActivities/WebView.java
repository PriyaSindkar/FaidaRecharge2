package uiActivities;

/**
 * Created by Krishna on 13-12-2015.
 */


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebViewClient;

import faidarecharge.com.faidarecharge.R;


/**
 * Created by krishnakumar on 02-12-2015.
 */
public class WebView extends AppCompatActivity {
    private android.webkit.WebView wv1;
    ProgressDialog bp;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);



        String url = getIntent().getStringExtra("link");






        wv1=(android.webkit.WebView)findViewById(R.id.webView);
        wv1.setWebViewClient(new MyBrowser());


        wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);


        wv1.loadUrl(url);
    }



    private class MyBrowser extends WebViewClient {


        @Override
        public void onPageFinished(android.webkit.WebView view, String url) {
            super.onPageFinished(view, url);
            bp.dismiss();
        }


        @Override
        public void onPageStarted(android.webkit.WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            bp = new ProgressDialog(WebView.this);
            bp.setMessage("Connecting to "+ getIntent().getStringExtra("store")+" ...");
            bp.show();
        }


        @Override
        public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


}
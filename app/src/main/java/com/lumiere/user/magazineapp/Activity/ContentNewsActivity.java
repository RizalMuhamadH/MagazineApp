package com.lumiere.user.magazineapp.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.lumiere.user.magazineapp.R;

public class ContentNewsActivity extends AppCompatActivity implements View.OnClickListener{

    private WebView viewWeb;

    private ImageView imageBtnBack;
    private ImageView imageBtnRefresh;

    Intent intent;

    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_news);
        getSupportActionBar().hide();

        viewWeb = (WebView)findViewById(R.id.web_view_news);
        imageBtnBack = (ImageView)findViewById(R.id.img_btn_back_news);
        imageBtnRefresh = (ImageView)findViewById(R.id.img_btn_refresh);

        progress =(ProgressBar)findViewById(R.id.progressBar);

        progress.setMax(100);

        imageBtnBack.setOnClickListener(this);
        imageBtnRefresh.setOnClickListener(this);

        startWebView("http://ayobandung.com/");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_btn_back_news :
                intent = new Intent(this,CoverMagazineActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.img_btn_refresh :
                startWebView("http://ayobandung.com/");
                break;
        }
    }
    private void startWebView(String url){
        viewWeb.setWebViewClient(new WebViewClient(){
            int status = 0;
            ProgressDialog progressDialog;
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            //Show loader on url load
            public void onLoadResource (WebView view, String url) {
//                view.loadUrl(url);
                if (status == 0) {
//                    view.loadUrl(url);
                    // in standard case YourActivity.this
                    if (progressDialog == null){
                        progressDialog = new ProgressDialog(ContentNewsActivity.this);
                        progressDialog.setMessage("Loading...");
                        progressDialog.show();
                    }

                }
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            public void onPageFinished(WebView view, String url) {
                try{
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                        progressDialog = null;
                        status = 1;
                        progress.setIndeterminate(false);
                        progress.setVisibility(View.INVISIBLE);
                    }
                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }

        });
        viewWeb.getSettings().setJavaScriptEnabled(true);
        viewWeb.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if(viewWeb.canGoBack()) {
            viewWeb.goBack();
        } else {
            // Let the system handle the back button
            super.onBackPressed();
        }
    }


}

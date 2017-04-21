package com.lumiere.user.magazineapp.Activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lumiere.user.magazineapp.R;

public class DetailAnnounActivity extends AppCompatActivity {

    private ImageView btnBack;

    private TextView detailTxt;

    private WebView webView;

    private String content = "<p style=\"text-align:justify\"><h2>the quick brown fox jump over the lazy dog</h2><br><u>the quick brown fox jump over the lazy dog </u><br><i>the quick brown fox jump over the lazy dog</i><br> &#34;the  quick brown fox jump over the lazy dog&#34; the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over <b>the lazy dog the quick brown fox jump over the lazy dog</b> the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over <font color=\'#FF8000\'>the lazy dog the quick brown fox jump over the lazy dog</font> the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog the quick brown fox jump over the lazy dog</p>";

    private String content2 = "<p style=\"text-align:justify\"><span style=\"color:#800080\">the quick brown fox jump over the lazy dog</span>&nbsp;<span style=\"font-size:20px\">the quick brown fox jump over the lazy dog</span>&nbsp;the quick brown fox jump over the lazy dog&nbsp;the quick brown fox jump over the lazy dog&nbsp;the quick brown fox jump over the lazy dog&nbsp;the quick brown fox jump over the lazy dog&nbsp;the quick brown fox jump over the lazy dog&nbsp;the quick brown fox jump over the lazy dog&nbsp;the quick brown fox jump over the lazy dog\n" +
            "</p>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_announ);
        getSupportActionBar().hide();

        btnBack = (ImageView)findViewById(R.id.img_btn_back_detail);
        detailTxt = (TextView)findViewById(R.id.txt_detail_content);
        webView = (WebView)findViewById(R.id.web_view);


        showDetail(content);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailAnnounActivity.this,AnnouncementActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void showDetail(String content){
        webView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            detailTxt.setText(Html.fromHtml(content,Html.FROM_HTML_MODE_LEGACY));
        }else {
            detailTxt.setText(Html.fromHtml(content));
        }

    }
}

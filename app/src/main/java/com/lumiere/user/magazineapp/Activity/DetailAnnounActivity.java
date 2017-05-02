package com.lumiere.user.magazineapp.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.lumiere.user.magazineapp.R;

public class DetailAnnounActivity extends AppCompatActivity {

    private ImageView btnBack;

    private TextView detailTxt;

    private WebView webView;

    private String body = "<p class=\"title\">Dedikasi bank bjb Menjaga Kesehatan Karyawan</p><p class=\"quotes\"> \"Kami berusaha melakukan edukasi mengenai pola hidup sehat. Itu harus menjadi gaya hidupnya orang bank bjb,\" -Dadan Yonanda, Kepala Divisi Human Capital bank bjb</p><p> \"Kami berusaha melakukan edukasi mengenai pola hidup sehat. Itu harus menjadi gaya hidupnya orang bank bjb,\" -Dadan Yonanda, Kepala Divisi Human Capital bank bjb</p><div><img src=\"http://mobs.ayobandung.com/assets/1.jpg\" /></div><p class=\"caption\">Seminar human capital care “Pola Hidup Bersih dan Sehat</p><p> \"Kami berusaha melakukan edukasi mengenai pola hidup sehat. Itu harus menjadi gaya hidupnya orang bank bjb,\" -Dadan Yonanda, Kepala Divisi Human Capital bank bjb</p>";
    private String html = "<html>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_announ);
        getSupportActionBar().hide();

        btnBack = (ImageView)findViewById(R.id.img_btn_back_detail);
//        detailTxt = (TextView)findViewById(R.id.txt_detail_content);
        webView = (WebView)findViewById(R.id.web_view);
//        video = (VideoView)findViewById(R.id.video);

//        controller = new MediaController(this);
//        int loc = savedInstanceState.getInt("Loc");
//        video.setMediaController(controller);
//        video.requestFocus();
//        uriYoutube = Uri.parse(savedInstanceState.getString("https://www.youtube.com/watch?v=4qXOFkn-dt4"));
//        video.setVideoURI(uriYoutube);
//        video.seekTo(loc);
//        video.start();
        html += "<head>";
        html += "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">";
        html += "<link href=\"https://fonts.googleapis.com/css?family=Roboto:400,400i,700,700i\" rel=\"stylesheet\">";
        html += "<style>";
        html += "body{font-family:'Roboto',sans-serif;font-size:100%;} p{text-align:justify;color:black;}p.quotes{font-weight:bold;color:#115B80;}p.title{font-weight:bold;color:#115B80;font-size:130%;margin-bottom:12px;text-align:left;} p.caption{font-size:70%;color:#939598;text-align:justify;margin-bottom:5px;}div,img{width:100%;}footer{margin-bottom:-10px;}";
        html += "</style>";
        html += "</head>";
        html += "<body>";
        html += body;
//        html += "<footer><img src=\"http://mobs.ayobandung.com/assets/footer.png\" /></footer>";
        html += "</body>";
        html += "</html>";

        showDetail(html);
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
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
//            detailTxt.setText(Html.fromHtml(content,Html.FROM_HTML_MODE_LEGACY));
//        }else {
//            detailTxt.setText(Html.escapeHtml(content));
//        }

    }
}

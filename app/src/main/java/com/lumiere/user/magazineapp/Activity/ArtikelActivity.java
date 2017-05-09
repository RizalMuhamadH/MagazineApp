package com.lumiere.user.magazineapp.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.lumiere.user.magazineapp.R;
import com.lumiere.user.magazineapp.Utility.SessionManager;
import com.lumiere.user.magazineapp.Utility.TypefaceUtil;

public class ArtikelActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Intent intent;
    private SessionManager manager;

    private WebView webView;

    private NavigationView navigationView;


    private String body = "<p class=\"title\">Dedikasi bank bjb Menjaga Kesehatan Karyawan</p><p class=\"quotes\"> \"Kami berusaha melakukan edukasi mengenai pola hidup sehat. Itu harus menjadi gaya hidupnya orang bank bjb,\" -Dadan Yonanda, Kepala Divisi Human Capital bank bjb</p><p> \"Kami berusaha melakukan edukasi mengenai pola hidup sehat. Itu harus menjadi gaya hidupnya orang bank bjb,\" -Dadan Yonanda, Kepala Divisi Human Capital bank bjb</p><div><img src=\"http://mobs.ayobandung.com/assets/1.jpg\" /></div><p class=\"caption\">Seminar human capital care “Pola Hidup Bersih dan Sehat</p><p> \"Kami berusaha melakukan edukasi mengenai pola hidup sehat. Itu harus menjadi gaya hidupnya orang bank bjb,\" -Dadan Yonanda, Kepala Divisi Human Capital bank bjb</p>";
    private String body1 = "<div class=\"gallery\"><a target=\"_self\" href=\"http://mobs.ayobandung.com/assets/1.jpg\"><img class=\"tiles\" src=\"http://mobs.ayobandung.com/assets/1.jpg\" /></a></div><div class=\"gallery\"><a target=\"_self\" href=\"http://mobs.ayobandung.com/assets/2.jpg\"><img class=\"tiles\" src=\"http://mobs.ayobandung.com/assets/2.jpg\" /></a></div><div class=\"gallery\"><a target=\"_self\" href=\"http://mobs.ayobandung.com/assets/3.jpg\"><img class=\"tiles\" src=\"http://mobs.ayobandung.com/assets/3.jpg\" /></a></div><div class=\"gallery\"><a target=\"_self\" href=\"http://mobs.ayobandung.com/assets/4.jpg\"><img class=\"tiles\" src=\"http://mobs.ayobandung.com/assets/4.jpg\" /></a></div><div class=\"gallery\"><a target=\"_self\" href=\"http://mobs.ayobandung.com/assets/5.jpg\"><img class=\"tiles\" src=\"http://mobs.ayobandung.com/assets/5.jpg\" /></a></div>";
    private String html = "<html>";
    private String html1 = "<html>";
    private String html2 = "<html>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        manager = new SessionManager(getApplicationContext());

        webView = (WebView)findViewById(R.id.web_view_artikel);

        html += "<head>";
        html += "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">";
        html += "<link href=\"https://fonts.googleapis.com/css?family=Roboto:400,400i,700,700i\" rel=\"stylesheet\">";
        html += "<style>";
        html += "body{font-family:'Roboto',sans-serif;font-size:100%;} p{text-align:justify;color:black;}p.quotes{font-weight:bold;color:#115B80;}p.title{font-weight:bold;color:#115B80;font-size:130%;margin-bottom:12px;text-align:left;} p.caption{font-size:70%;color:#939598;text-align:justify;margin-bottom:5px;}div,img{width:100%;}footer{margin-bottom:-10px;}";
        html += "</style>";
        html += "</head>";
        html += "<body>";
        html += body;
        html += "<object width=\"420\" height=\"315\"\n" +
                "data=\"https://www.youtube.com/embed/XGSy3_Czz8k\">\n" +
                "</object>";
//        html += "<video src=\"https://www.youtube.com/embed/fizg7T_SdrU\" width=\"170\" height=\"85\" controls></video>";
//        html += "<footer><img src=\"http://mobs.ayobandung.com/assets/footer.png\" /></footer>";
        html += "</body>";
        html += "</html>";

        html1 += "<head>";
        html1 += "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">";
        html1 += "<link href=\"https://fonts.googleapis.com/css?family=Roboto:400,400i,700,700i\" rel=\"stylesheet\">";
        html1 += "<style>";
        html1 += "body{font-family:'Roboto',sans-serif;font-size:100%;} p{text-align:justify;color:black;}p.quotes{font-weight:bold;color:#115B80;}p.title{font-weight:bold;color:#115B80;font-size:130%;margin-bottom:12px;text-align:left;} p.caption{font-size:70%;color:#939598;text-align:justify;margin-bottom:5px;}div,img{width:100%;}div.gallery{margin: 3px;border: 1px solid #ccc;float:left;width: 90px;}div.gallery img {width: 100%;height: auto;}img.tiles{width: 200;height: 100;}";
        html1 += "</style>";
        html1 += "</head>";
        html1 += "<body>";
        html1 += body1;
        html1 += "</body>";
        html1 += "</html>";
        webView.setWebViewClient(new WebViewClient(){
            ProgressDialog progressDialog;
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.endsWith(".jpg") || url.endsWith(".JPG") || url.endsWith(".png") || url.endsWith(".PNG")){
                    ImageViewerActivity.start(ArtikelActivity.this,url,null);
                }
                return true;
            }
            //Show loader on url load
            public void onLoadResource (WebView view, String url) {
                if (progressDialog == null) {
                    // in standard case YourActivity.this
                    progressDialog = new ProgressDialog(ArtikelActivity.this);
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                }
            }
            public void onPageFinished(WebView view, String url) {
                try{
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                        progressDialog = null;
                    }
                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
//        webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
        webView.loadData(html, "text/html", "utf-8");

        hideItem();



//        webView.getSettings().setJavaScriptEnabled(true);
//
//        webView.loadUrl("http://www.google.com");
//        setContentView(webView);
//        LayoutInflater inflater = getLayoutInflater();
//        View v = inflater.inflate(R.layout.cover_layout,null,false);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(webView.canGoBack()) {
            webView.goBack();
        } else {
            // Let the system handle the back button
            super.onBackPressed();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.artikel, menu);
//        MenuItem item = menu.findItem(R.id.nav_14);
//        item.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            manager.logoutUserSession();
            Intent intent = new Intent(ArtikelActivity.this,LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        switch (item.getItemId()){
            case R.id.nav_home :
                intent = new Intent(ArtikelActivity.this, ContentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                break;
//            case R.id.nav_1 :
//                break;
            case R.id.nav_2 :
                break;
            case R.id.nav_3 :
                break;
            case R.id.nav_4 :
                break;
            case R.id.nav_5 :
                break;
            case R.id.nav_6 :
                break;
            case R.id.nav_7 :
                break;
            case R.id.nav_8 :
                break;
            case R.id.nav_9 :
                break;
            case R.id.nav_10 :
                break;
            case R.id.nav_11 :
                break;
            case R.id.nav_12 :
                break;
            case R.id.nav_13 :
                break;
            case R.id.nav_14 :
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void setContentView(View view) {
        TypefaceUtil fontChanger = new TypefaceUtil(getAssets(),"fonts/Roboto-Regular.ttf");
        fontChanger.replaceFonts((ViewGroup) this.findViewById(android.R.id.content));
        super.setContentView(view);
    }
    private void hideItem(){
//        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_14).setVisible(false);
    }
}

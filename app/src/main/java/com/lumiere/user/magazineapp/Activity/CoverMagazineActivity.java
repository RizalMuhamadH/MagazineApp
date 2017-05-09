package com.lumiere.user.magazineapp.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lumiere.user.magazineapp.Adapter.ViewPagerCoverAdapter;
import com.lumiere.user.magazineapp.Model.CoverModel;
import com.lumiere.user.magazineapp.R;
import com.lumiere.user.magazineapp.Utility.TypefaceUtil;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CoverMagazineActivity extends AppCompatActivity {
    private static String TAG = "Cover Magazine Page";

    private ArrayList<CoverModel> coverList;
    private CoverModel cover;

    private ViewPager viewPager;
    private ViewPagerCoverAdapter adapter;

    private TabLayout tabLayout;
    private FrameLayout layout;

    private Snackbar snackbar;

//    private Button next;
//    private Button prev;

    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover_magazine);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edisi");
//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager)findViewById(R.id.view_pager_cover);
        tabLayout = (TabLayout)findViewById(R.id.tabDots);

        layout = (FrameLayout)findViewById(R.id.layout_cover);
//        next = (Button)findViewById(R.id.btn_next);
//        prev = (Button)findViewById(R.id.btn_prev);

        coverList = new ArrayList<>();

        for (int i = 0; i<14;i++){

            cover  = new CoverModel("http://reviewtech.info/wp-content/uploads/2015/11/apple-vs-android.jpg",String.valueOf(i));
            coverList.add(cover);
        }

        adapter = new ViewPagerCoverAdapter(coverList,this);

        viewPager.setAdapter(adapter);
        viewPager.setClickable(true);

        tabLayout.setupWithViewPager(viewPager,true);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                addDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("Pager Count: ", String.valueOf(viewPager.getChildCount()));
//                viewPager.setCurrentItem(nextViewPager(1),true);
//            }
//        });
//
//        prev.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewPager.setCurrentItem(prevViewPager(1),true);
//            }
//        });

    }

//    private int nextViewPager(int i){
//        return viewPager.getCurrentItem()+i;
//    }
//
//    private int prevViewPager(int i){
//        return viewPager.getCurrentItem()-i;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
//                this.onBackPressed();
                intent = new Intent(CoverMagazineActivity.this,ContentActivity.class);
                startActivity(intent);
                finish();
                return true;
            default: return super.onOptionsItemSelected(item);

        }
    }
    public void goToCoverPage(){
        intent = new Intent(this, ArtikelActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivity(intent);
        finish();
    }

    @Override
    public void setContentView(View view) {
        TypefaceUtil fontChanger = new TypefaceUtil(getAssets(),"fonts/Roboto-Regular.ttf");
        fontChanger.replaceFonts((ViewGroup) this.findViewById(android.R.id.content));
        super.setContentView(view);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
    private void getCoverMagazine(final String id){
        StringRequest request = new StringRequest(Request.Method.POST, "http://mobs.ayobandung.com/index.php/user_registration",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Log.e(TAG + " Respone",jsonObject.toString());

//                            status = jsonObject.getString("status");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e(TAG + " Respone",response);
//                        dialog.dismiss();
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        dialog.dismiss();
//                        Log.e(TAG + " Error",error.toString());
                        snackbar = Snackbar.make(layout,"Connection failed",Snackbar.LENGTH_SHORT);
                        snackbar.show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("id_edition",id);
                return param;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}

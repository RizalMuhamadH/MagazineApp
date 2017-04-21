package com.lumiere.user.magazineapp.Activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lumiere.user.magazineapp.Adapter.ViewPagerCoverAdapter;
import com.lumiere.user.magazineapp.Model.CoverModel;
import com.lumiere.user.magazineapp.R;
import com.lumiere.user.magazineapp.Utility.TypefaceUtil;

import java.util.ArrayList;

public class CoverMagazineActivity extends AppCompatActivity {

    private ArrayList<CoverModel> coverList;
    private CoverModel cover;

    private ViewPager viewPager;
    private ViewPagerCoverAdapter adapter;

    private TabLayout tabLayout;

    private Button next;
    private Button prev;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover_magazine);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edisi");
//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager)findViewById(R.id.view_pager_cover);
        tabLayout = (TabLayout)findViewById(R.id.tabDots);
        next = (Button)findViewById(R.id.btn_next);
        prev = (Button)findViewById(R.id.btn_prev);

        coverList = new ArrayList<>();

        for (int i = 0; i<5;i++){
            cover  = new CoverModel(null,String.valueOf(i));
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

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Pager Count: ", String.valueOf(viewPager.getChildCount()));
                viewPager.setCurrentItem(nextViewPager(1),true);
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(prevViewPager(1),true);
            }
        });
    }

    private int nextViewPager(int i){
        return viewPager.getCurrentItem()+i;
    }

    private int prevViewPager(int i){
        return viewPager.getCurrentItem()-i;
    }

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
}

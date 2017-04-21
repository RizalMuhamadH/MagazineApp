package com.lumiere.user.magazineapp.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.lumiere.user.magazineapp.Adapter.ContentAdapter;
import com.lumiere.user.magazineapp.R;
import com.lumiere.user.magazineapp.Utility.TypefaceUtil;

import java.util.ArrayList;

public class ContentActivity extends AppCompatActivity {
    private GridView grid;
    private ContentAdapter adapter;

    private ImageView profile;
    private ImageView announ;

    private ArrayList<String> arrayList;

    private BroadcastReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setBackgroundDrawable(new ColorDrawable(Color.rgb(255,127,0)));
//        actionBar.setTitle("Library");
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_action_person);
//        actionBar.setDisplayShowCustomEnabled(true);

        getSupportActionBar().hide();

        profile = (ImageView)findViewById(R.id.btn_profile);
        announ = (ImageView)findViewById(R.id.btn_announ);
        grid = (GridView)findViewById(R.id.grid_content);
        arrayList = new ArrayList<>();

        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("6");
        arrayList.add("7");
        arrayList.add("8");
        arrayList.add("9");
        arrayList.add("10");

        adapter = new ContentAdapter(arrayList,this);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ContentActivity.this, CoverMagazineActivity.class);
                startActivity(intent);
                finish();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContentActivity.this,ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        announ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContentActivity.this,AnnouncementActivity.class);
                startActivity(intent);
                finish();
            }
        });

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e("onTokenRefresh: ",refreshedToken);

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                FirebaseMessaging.getInstance().subscribeToTopic("global");
                SharedPreferences pref = getApplicationContext().getSharedPreferences("firebase", 0);
                String refreshedToken = pref.getString("regID", null);
                Log.e("FCM: ", refreshedToken);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,new IntentFilter("registrationComplete"));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        super.onPause();
    }
    //    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case android.R.id.home :
//                Intent intent = new Intent(ContentActivity.this,ProfileActivity.class);
//                startActivity(intent);
//                finish();
//                return true;
//            default: return super.onOptionsItemSelected(item);
//        }
//    }

    @Override
    public void setContentView(View view) {
        TypefaceUtil fontChanger = new TypefaceUtil(getAssets(),"fonts/Roboto-Regular.ttf");
        fontChanger.replaceFonts((ViewGroup) this.findViewById(android.R.id.content));
        super.setContentView(view);
    }
}

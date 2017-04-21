package com.lumiere.user.magazineapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.lumiere.user.magazineapp.Adapter.AnnouncementAdapter;
import com.lumiere.user.magazineapp.Model.AnnouncementModel;
import com.lumiere.user.magazineapp.R;

import java.util.ArrayList;

public class AnnouncementActivity extends AppCompatActivity {

    private ImageView back;

    private ListView listView;

    private AnnouncementAdapter adapter;

    private AnnouncementModel model;

    private ArrayList<AnnouncementModel> modelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        getSupportActionBar().hide();

        listView = (ListView)findViewById(R.id.list_notify);
        back =(ImageView)findViewById(R.id.img_btn_back);

        modelArrayList = new ArrayList<>();

        for (int i = 0;i<=10;i++){
            model = new AnnouncementModel("Subject #"+i,"Date Received");
            modelArrayList.add(model);
        }

        adapter = new AnnouncementAdapter(modelArrayList,getApplicationContext());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AnnouncementActivity.this,DetailAnnounActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnnouncementActivity.this,ContentActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

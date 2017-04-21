package com.lumiere.user.magazineapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.lumiere.user.magazineapp.R;
import com.lumiere.user.magazineapp.Utility.SessionManager;
import com.lumiere.user.magazineapp.Utility.TypefaceUtil;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageHome;

    private Button logout;

    Intent intent;

    private SessionManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        imageHome = (ImageView)findViewById(R.id.btn_img_home);

        logout = (Button)findViewById(R.id.btn_logout);

        logout.setOnClickListener(this);

        imageHome.setOnClickListener(this);

        manager = new SessionManager(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_logout :
                manager.logoutUserSession();
                intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_img_home :
                intent = new Intent(ProfileActivity.this, ContentActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void setContentView(View view) {
        TypefaceUtil fontChanger = new TypefaceUtil(getAssets(),"fonts/Roboto-Regular.ttf");
        fontChanger.replaceFonts((ViewGroup) this.findViewById(android.R.id.content));
        super.setContentView(view);
    }
}

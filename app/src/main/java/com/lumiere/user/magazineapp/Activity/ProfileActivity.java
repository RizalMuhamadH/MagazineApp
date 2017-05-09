package com.lumiere.user.magazineapp.Activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lumiere.user.magazineapp.Model.Edition;
import com.lumiere.user.magazineapp.R;
import com.lumiere.user.magazineapp.Utility.SessionManager;
import com.lumiere.user.magazineapp.Utility.TypefaceUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = "Profile Page";
    private ImageView imageHome;

    private EditText username;
    private EditText email;

    private Button ubahPassword;
    private Button ubahNama;
    private Button logout;

    private Intent intent;

    private Snackbar snackbar;

    private LinearLayout layout;

    private SessionManager manager;

    private String mUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        imageHome = (ImageView)findViewById(R.id.btn_img_home);

        logout = (Button)findViewById(R.id.btn_logout);

        username = (EditText)findViewById(R.id.txt_profile_username);
        email = (EditText)findViewById(R.id.txt_profile_email);

        ubahPassword = (Button)findViewById(R.id.btn_ubah_password);
        ubahNama = (Button)findViewById(R.id.btn_ubah_nama);

        layout = (LinearLayout)findViewById(R.id.layout_profile);


        logout.setOnClickListener(this);
        ubahPassword.setOnClickListener(this);
        ubahNama.setOnClickListener(this);

        imageHome.setOnClickListener(this);

        manager = new SessionManager(getApplicationContext());

        mUsername = manager.getUsername();
    }

    private void changeName(final String username,final String nama){
        StringRequest request = new StringRequest(Request.Method.POST, "http://mobs.ayobandung.com/index.php/user_registration",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Log.e(TAG + " Respone",jsonObject.toString());
                            snackbar = Snackbar.make(layout,"Berhasil ubah nama",Snackbar.LENGTH_SHORT);
                            snackbar.show();
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
                param.put("nama",nama);
                param.put("username",username);
                return param;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_logout :
                manager.logoutUserSession();
                intent = new Intent(ProfileActivity.this, LoginActivity.class);
                break;
            case R.id.btn_img_home :
                intent = new Intent(ProfileActivity.this, ContentActivity.class);
                break;
            case R.id. btn_ubah_password :
                intent = new Intent(ProfileActivity.this,PasswordActivity.class);
                break;
        }
        startActivity(intent);
    }

    @Override
    public void setContentView(View view) {
        TypefaceUtil fontChanger = new TypefaceUtil(getAssets(),"fonts/Roboto-Regular.ttf");
        fontChanger.replaceFonts((ViewGroup) this.findViewById(android.R.id.content));
        super.setContentView(view);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

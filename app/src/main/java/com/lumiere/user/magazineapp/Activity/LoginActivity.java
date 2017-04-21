package com.lumiere.user.magazineapp.Activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lumiere.user.magazineapp.API.APIClients;
import com.lumiere.user.magazineapp.API.APIInterface;
import com.lumiere.user.magazineapp.Model.Respone;
import com.lumiere.user.magazineapp.Model.Users;
import com.lumiere.user.magazineapp.R;
import com.lumiere.user.magazineapp.Utility.SessionManager;
import com.lumiere.user.magazineapp.Utility.TypefaceUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText username;
    private EditText password;

    private Button login;

    private TextView register;
    private TextView forgotPassword;
    private TextView textViewUsername;
    private TextView textViewPassword;
    private TextView textViewAkun;

    private CheckBox checkBoxMasuk;

    private Intent intent;

    private String status = "succes";

    private SessionManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();


        username = (EditText)findViewById(R.id.txt_username);
        password = (EditText)findViewById(R.id.txt_password);

        login = (Button) findViewById(R.id.btn_sign_in);

        register = (TextView) findViewById(R.id.txt_register);
        forgotPassword = (TextView) findViewById(R.id.forgot_password);
        textViewUsername = (TextView)findViewById(R.id.text_view_username);
        textViewPassword = (TextView)findViewById(R.id.text_view_password);
        textViewAkun = (TextView)findViewById(R.id.text_view_akun);

        checkBoxMasuk = (CheckBox)findViewById(R.id.check_box_masuk);

        manager = new SessionManager(getApplicationContext());

        login.setOnClickListener(this);
        register.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);

//        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Regular.ttf");
//        Typeface custom_font_bold = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Bold.ttf");
//
//        username.setTypeface(custom_font);
//        password.setTypeface(custom_font);
//        login.setTypeface(custom_font_bold);
//        register.setTypeface(custom_font);
//        forgotPassword.setTypeface(custom_font);
//        textViewUsername.setTypeface(custom_font_bold);
//        textViewPassword.setTypeface(custom_font_bold);
//        textViewAkun.setTypeface(custom_font);
//        checkBoxMasuk.setTypeface(custom_font);
    }
    private void userSignin(){
        final String tmpUsername = username.getText().toString().trim();
        final String tmpPassword = password.getText().toString().trim();
        Users users = new Users();
        users.setUsername(tmpUsername);
        users.setPassword(tmpPassword);

        StringRequest request = new StringRequest(Request.Method.POST, "http://10.0.2.2/API/index.php",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Respone",response);
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Respone",error.toString());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("username",tmpUsername);
                param.put("password",tmpPassword);
                return param;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);

//        APIInterface apiInterface = APIClients.getClient().create(APIInterface.class);
//
//        Call<Respone> call = apiInterface.login(users);
//        call.enqueue(new Callback<Respone>() {
//            @Override
//            public void onResponse(Call<Respone> call, Response<Respone> response) {
//                Log.e("Respone",response.body().getRespone() );
//            }
//
//            @Override
//            public void onFailure(Call<Respone> call, Throwable t) {
//                Log.e("Respone",t.getMessage() + t.toString());
//            }
//        });

//        if (status == "succes"){
//            manager.createUserLoginSession(tmpUsername,tmpPassword);
//            intent = new Intent(LoginActivity.this,ContentActivity.class);
//            startActivity(intent);
//            finish();
//        }else{
//            Toast.makeText(this, "E-mail & password failed", Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sign_in :
                userSignin();
                break;
            case R.id.txt_register :
                intent = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
                break;
            case R.id.forgot_password :
                intent = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        if (manager.userLoginSession()){
            intent = new Intent(LoginActivity.this,ContentActivity.class);
            startActivity(intent);
            finish();
        }
        super.onResume();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        TypefaceUtil fontChanger = new TypefaceUtil(getAssets(),"fonts/Roboto-Regular.ttf");
        fontChanger.replaceFonts((ViewGroup) this.findViewById(android.R.id.content));
    }
}
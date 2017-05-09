package com.lumiere.user.magazineapp.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    final static String TAG = "Login Page";
    private EditText username;
    private EditText password;

    private Button login;

    private TextView register;
    private TextView forgotPassword;

    private RelativeLayout layout;

    private Snackbar snackbar;

    private Intent intent;

    private String status = "success";

    private String msg = "Login gagal";

    private SessionManager manager;

    private ProgressDialog dialog;

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

        layout = (RelativeLayout)findViewById(R.id.layout_login);

        manager = new SessionManager(getApplicationContext());

        login.setOnClickListener(this);
        register.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);

    }
    private void userSignin(){
        final String tmpUsername = username.getText().toString().trim();
        final String tmpPassword = password.getText().toString().trim();
        Users users = new Users();
        users.setUsername(tmpUsername);
        users.setPassword(tmpPassword);

//        dialog = new ProgressDialog(this);
//        dialog.setMessage("Proses...");
//        dialog.setCancelable(false);
//        dialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, "http://10.0.2.2/CodeIgniter-2.2.6/user_controller/userLogin",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.e(TAG, response);

                            status = jsonObject.getString("status");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("Respone",response);
//                        dialog.dismiss();
                    }

                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        dialog.dismiss();
                        snackbar = Snackbar.make(layout,"Connection failed",Snackbar.LENGTH_LONG);
                        snackbar.show();
//                        Log.e("Respone",error.toString());
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

        if (status == "success"){
            manager.createUserLoginSession(tmpUsername,tmpPassword);
            intent = new Intent(LoginActivity.this,ContentActivity.class);
            startActivity(intent);
            finish();
        }else{
            snackbar = Snackbar.make(layout,msg,Snackbar.LENGTH_LONG);
            snackbar.show();
//            Toast.makeText(this, "E-mail atau kata sandi salah", Toast.LENGTH_SHORT).show();
        }
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
        try {
            if (manager.userLoginSession()){
                intent = new Intent(LoginActivity.this,ContentActivity.class);
                startActivity(intent);
                finish();

            }
            super.onResume();
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }


    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        TypefaceUtil fontChanger = new TypefaceUtil(getAssets(),"fonts/Roboto-Regular.ttf");
        fontChanger.replaceFonts((ViewGroup) this.findViewById(android.R.id.content));
    }
}
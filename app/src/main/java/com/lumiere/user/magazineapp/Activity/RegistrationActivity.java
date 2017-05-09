package com.lumiere.user.magazineapp.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.DialogPreference;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lumiere.user.magazineapp.R;
import com.lumiere.user.magazineapp.Utility.TypefaceUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {
    private static final String TAG ="Registration_Page";

    private Snackbar snackbar;

    private EditText email;
    private EditText password;
    private EditText retypePassword;
    private EditText username;
    private EditText nama;

    private Button submit;

    private RelativeLayout layout;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_registration);

        username = (EditText)findViewById(R.id.txt_input_username);
        email = (EditText)findViewById(R.id.txt_input_email);
        password = (EditText)findViewById(R.id.txt_input_password);
        retypePassword = (EditText)findViewById(R.id.txt_input_retype_password);
        nama = (EditText)findViewById(R.id.txt_input_name);

        submit = (Button)findViewById(R.id.btn_sign_up);

        layout = (RelativeLayout)findViewById(R.id.layout_register);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Regular.ttf");
        Typeface custom_font_bold = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Bold.ttf");

        password.setTypeface(custom_font);
        retypePassword.setTypeface(custom_font);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 0;
                if (!validationPassword(password.getText().toString())){
                    password.setError("Kata sandi harus berisikan huruf besar, huruf kecil dan angka");
                }else{
                    count += 1;
                }
                if (!validationUsername(username.getText().toString())){
                    username.setError("Username harus memiliki 5 karakter atau lebih");
                }else{
                    count += 1;
                }
                if (!checkPassword(password.getText().toString(),retypePassword.getText().toString())){
                    retypePassword.setError("Kata sandi tidak cocok");
                }else {
                    count += 1;
                }

                if (!checkEmail(email.getText().toString())){
                    email.setError("Email tidak cocok");
                }else {
                    count += 1;
                }
                if (count == 4){
                    registrasi(username.getText().toString(),nama.getText().toString(),email.getText().toString(),password.getText().toString());
                }

            }
        });
    }

    /**
     * [a-zA-Z0-9._-]+@bankbjb.com
     */
    @Override
    public void setContentView(View view) {
        TypefaceUtil fontChanger = new TypefaceUtil(getAssets(),"fonts/Roboto-Regular.ttf");
        fontChanger.replaceFonts((ViewGroup) this.findViewById(android.R.id.content));
        super.setContentView(view);
    }
    private boolean checkPassword(String retypePassword, String password){
        if (password.equals(retypePassword)){
            return true;
        }else {
            return false;
        }
    }
    private boolean validationPassword(String password){
        if (password.length() >= 8){
            if (password.matches("[a-zA-Z]+[0-9]+") || password.matches("[0-9]+[a-zA-Z]+") || password.matches("[0-9]+[a-zA-Z]+[0-9]+")){
//                Toast.makeText(this, "Kata sandi harus berisikan huruf besar, huruf kecil dan angka", Toast.LENGTH_SHORT).show();
                return true;
            }else{
//                Toast.makeText(this, "gagal", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else {
            return false;
//            Toast.makeText(this, "Kata sandi minimal 8 digit", Toast.LENGTH_SHORT).show();
        }

    }
    private boolean validationUsername(String username){
        if (username.length() >= 5){
            return true;
        }else {
            return false;
        }
    }

    private boolean checkEmail(String email){
        if (email.endsWith("@bankbjb.com")){
            return true;
        }else {
            return false;
        }
    }
    private void registrasi(final String username,final String nama,final String email, final String password){
//        dialog = new ProgressDialog(this);
//        dialog.setMessage("Proses...");
//        dialog.setCancelable(false);
//        dialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, "http://mobs.ayobandung.com/index.php/user_registration",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Log.e(TAG + " Respone",jsonObject.toString());
                            String status = jsonObject.getString("status");
                            if (status == "success"){
                                String msg = "Registrasi berhasil, segera cek email anda untuk aktivasi";
                                alertDialog(msg);
                            }else {
                                String msg = jsonObject.getString("msg");
                                snackbar = Snackbar.make(layout,msg,Snackbar.LENGTH_LONG);
                                snackbar.setActionTextColor(Color.RED);
                                snackbar.show();
                            }
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
                        snackbar = Snackbar.make(layout,"Connection failed",Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("nama",nama);
                param.put("username",username);
                param.put("email",email);
                param.put("password",password);
                param.put("active","0");
                return param;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private void alertDialog(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

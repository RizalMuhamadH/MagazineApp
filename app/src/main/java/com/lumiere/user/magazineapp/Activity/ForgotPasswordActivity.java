package com.lumiere.user.magazineapp.Activity;

import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lumiere.user.magazineapp.R;
import com.lumiere.user.magazineapp.Utility.SessionManager;
import com.lumiere.user.magazineapp.Utility.TypefaceUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ForgotPasswordActivity extends AppCompatActivity {
    private static String TAG = "Forgot Password Page";

    private TextView numRandom;
    private EditText txtRandom;
    private Button btnForgotPassword;

    private RelativeLayout layout;

    private Snackbar snackbar;

    private SessionManager manager;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().hide();

        numRandom = (TextView)findViewById(R.id.num_random);

        txtRandom = (EditText)findViewById(R.id.txt_num_random);

        btnForgotPassword = (Button)findViewById(R.id.btn_forgot_password);

        layout = (RelativeLayout)findViewById(R.id.layout_forgot_password);

        manager = new SessionManager(getApplicationContext());


        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = manager.getUsername();
                String xCode = numRandom.getText().toString();
                String yCode = txtRandom.getText().toString();
                if (xCode.equals(yCode)){

                }else {
                    snackbar = Snackbar.make(layout,"Code tidak cocok",Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        });
        codeVerification();
    }

    @Override
    public void setContentView(View view) {
        TypefaceUtil fontChanger = new TypefaceUtil(getAssets(),"fonts/Roboto-Regular.ttf");
        fontChanger.replaceFonts((ViewGroup) this.findViewById(android.R.id.content));
        super.setContentView(view);
    }
    private void codeVerification(){
        Random random = new Random();
        int digit = random.nextInt(9999);

        if (String.valueOf(digit).length() == 4){
            numRandom.setText(String.valueOf(digit));
        }else {
            codeVerification();
        }
    }

    private void resetPassword(final String email){
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
                param.put("email",email);
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
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

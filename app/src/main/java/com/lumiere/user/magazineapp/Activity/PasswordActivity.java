package com.lumiere.user.magazineapp.Activity;

import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lumiere.user.magazineapp.R;
import com.lumiere.user.magazineapp.Utility.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PasswordActivity extends AppCompatActivity {
    private static String TAG = "Password Page";
    private LinearLayout layout;

    private Button password;

    private Snackbar snackbar;

    private SessionManager manager;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        getSupportActionBar().hide();

        manager = new SessionManager(getApplicationContext());

        username = manager.getUsername();

        layout = (LinearLayout)findViewById(R.id.layout_password);

        password = (Button)findViewById(R.id.btn_password);

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void changePassword(final String username, final String password){
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
                param.put("password",password);
                param.put("username",username);
                return param;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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
}

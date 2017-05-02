package com.lumiere.user.magazineapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lumiere.user.magazineapp.R;
import com.lumiere.user.magazineapp.Utility.TypefaceUtil;

import java.util.Random;

public class ForgotPasswordActivity extends AppCompatActivity {

    private TextView numRandom;
    private Button btnForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().hide();

        numRandom = (TextView)findViewById(R.id.num_random);
        btnForgotPassword = (Button)findViewById(R.id.btn_forgot_password);

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
}

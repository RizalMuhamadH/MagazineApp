package com.lumiere.user.magazineapp.Activity;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lumiere.user.magazineapp.R;
import com.lumiere.user.magazineapp.Utility.TypefaceUtil;

public class RegistrationActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private EditText retypePassword;
    private EditText username;
    private Button submit;

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

        submit = (Button)findViewById(R.id.btn_sign_up);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Regular.ttf");
        Typeface custom_font_bold = Typeface.createFromAsset(getAssets(),  "fonts/Roboto-Bold.ttf");

        password.setTypeface(custom_font);
        retypePassword.setTypeface(custom_font);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validationPassword(password.getText().toString())){
                    password.setError("Kata sandi harus berisikan huruf besar, huruf kecil dan angka");
                }
                if (!validationUsername(username.getText().toString())){
                    username.setError("Username harus memiliki 5 karakter atau lebih");
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
}

package com.lumiere.user.magazineapp.Utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;

/**
 * Created by user on 04/12/2017.
 */

public class SessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    Context context;

    int PRIVATE_MODE = 0;

    public static final String PREFERENCE_NAME = "session";

    public static final String USER_LOGIN = "UserLooggedIn";

    public static final String KEY_USERNAME = "username";

    public static final String KEY_EMAIL = "email";

    public static final String KEY_PASSWORD = "password";

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREFERENCE_NAME,PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createUserLoginSession(String username, String password){

        editor.putString(KEY_USERNAME,username);
        editor.putString(KEY_PASSWORD, password);
        editor.putBoolean(USER_LOGIN,true);

        editor.commit();
    }
    public boolean userLoginSession(){

        boolean session = pref.getBoolean(USER_LOGIN,false);

        return session;
    }

    public void logoutUserSession(){
        editor.clear();
        editor.commit();
    }
    public String getUsername(){
        String username = pref.getString(KEY_USERNAME,"");
        return username;
    }
}

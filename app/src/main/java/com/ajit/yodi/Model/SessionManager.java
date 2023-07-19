package com.ajit.yodi.Model;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences choice;
    SharedPreferences.Editor editor;
    Context context;

    public static  final String SESSION_USERSESSION = "userLoginSession";

    public static final String IS_LOGIN = "logged_in";
    public static final String KEY_PROBLEM = "choice";
    public static final String KEY_EMAIL = "gmail";
    public static final String KEY_MOBILE_NO = "mobile";
    public static final String KEY_FIRST_NAME = "first_name";
    public static final String KEY_LAST_NAME = "last_name";

    public SessionManager(Context mcontext){
        context=mcontext;
        choice = context.getSharedPreferences("selectedchoice",Context.MODE_PRIVATE);
        editor = choice.edit();
    }

    // Option Section //

    public void create(String choice){

        editor.putString(KEY_PROBLEM,choice);

        editor.commit();
    }

    public HashMap<String, String> GetUserChoice(){
        HashMap<String,String> data =new HashMap<String, String>();
        data.put(KEY_PROBLEM, choice.getString(KEY_PROBLEM, null));

        return data;
    }

    // Option Section //

    //*****************************************************************************

    // Profile Section //

    public void mobile_login(String mobile){

        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_MOBILE_NO,mobile);
        editor.commit();

    }

    public HashMap<String, String> GetMobileLoginData(){
        HashMap<String,String> data =new HashMap<String, String>();

        data.put(KEY_MOBILE_NO, choice.getString(KEY_MOBILE_NO, null));
        return data;

    }

    public boolean check_login(){
        if (choice.getBoolean(IS_LOGIN, false)){
            return true;
        }else {
            return false;
        }
    }


    public void logout_session(){
        editor.clear();
        editor.commit();
    }

    // Profile Section //


}

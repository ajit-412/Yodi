package com.ajit.yodi.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.ajit.yodi.Dashboard.Options;
import com.ajit.yodi.Login.LoginPage;
import com.ajit.yodi.Model.SessionManager;
import com.ajit.yodi.R;

import java.util.HashMap;

public class OpeningScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN=4000;
    String phone="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SessionManager sessionManager = new SessionManager(OpeningScreen.this);
                HashMap<String, String> userDetails = sessionManager.GetMobileLoginData();
                phone = userDetails.get(SessionManager.KEY_MOBILE_NO);
                String IS_login = userDetails.get(SessionManager.IS_LOGIN);
                if (phone == null){
                    Intent intent = new Intent(OpeningScreen.this, LoginPage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else{
                    Intent dashBoard = new Intent(OpeningScreen.this, Options.class);
                    dashBoard.putExtra("profile Values","+91 "+phone);
                    dashBoard.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(dashBoard);
                }
            }
        },SPLASH_SCREEN);

    }


}
package com.ajit.yodi.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ajit.yodi.Login.GmailLogin.GmailLogin;
import com.ajit.yodi.Login.MobileLogin.MobileNumberLogin;
import com.ajit.yodi.R;

public class LoginPage extends AppCompatActivity {

    TextView mobile_button,google_btn,admin_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);



        mobile_button=findViewById(R.id.mobile_button);
        google_btn = findViewById(R.id.google_btn);
//        admin_btn = findViewById(R.id.admin_login_btn);



        // OTP SECTION
        mobile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this, MobileNumberLogin.class);
                startActivity(intent);
            }
        });


        // GOOGLE SECTION
        google_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this, GmailLogin.class);
                startActivity(intent);
            }
        });


//        // ADMIN SECTION
//        admin_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginPage.this, AdminLogin.class);
//                startActivity(intent);
//            }
//        });

    }
}
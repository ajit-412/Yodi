package com.ajit.yodi.Login.GmailLogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ajit.yodi.R;

public class GmailMobileNo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmail_mobile_no);

        EditText mobile_no = findViewById(R.id.mb_no);
        TextView send_otp = findViewById(R.id.otp_btn);

        send_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mobile_no.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(GmailMobileNo.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), GmailMobileOTP.class);
                    intent.putExtra("mobile",mobile_no.getText().toString());
                    startActivity(intent);
                }
            }
        });

    }
}
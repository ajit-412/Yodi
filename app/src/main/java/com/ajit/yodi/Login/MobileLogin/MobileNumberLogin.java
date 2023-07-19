package com.ajit.yodi.Login.MobileLogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ajit.yodi.R;

public class MobileNumberLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_number_login);

        EditText mobile_no = findViewById(R.id.mobile_no);
        TextView send_otp = findViewById(R.id.get_otp_btn);

        send_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mobile_no.getText().toString().trim().isEmpty())
                {
                    if(mobile_no.getText().length() == 10 && !mobile_no.getText().toString().equals("0123456789") && !mobile_no.getText().toString().equals("1234567890")){
                        Intent intent = new Intent(getApplicationContext(), OtpLogin.class);
                        intent.putExtra("mobile_no",mobile_no.getText().toString());
                        startActivity(intent);
                    }else {
                        Toast.makeText(MobileNumberLogin.this, "Enter Valid Number !!", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(MobileNumberLogin.this, "Enter Mobile Number !!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}





//    Button get_otp;
//    EditText mobile_no;
//
//     [START declare_auth]
//    private FirebaseAuth mAuth;
//     [END declare_auth]
//
//    private String mVerificationId;
//    private PhoneAuthProvider.ForceResendingToken mResendToken;
//    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;



//        // Checking Mobile Number
//
//        get_otp = findViewById(R.id.get_otp_btn);
//        mobile_no = findViewById(R.id.mobile_no);
//
//        get_otp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(!mobile_no.getText().toString().trim().isEmpty())
//                {
//                    if((mobile_no.getText().toString().trim().length() == 10))
//                    {
//                        PhoneAuthOptions options =
//                                PhoneAuthOptions.newBuilder(mAuth)
//                                        .setPhoneNumber("91"+mobile_no.getText().toString())       // Phone number to verify
//                                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//                                        .setActivity(MobileNumberLogin.this)                 // Activity (for callback binding)
//                                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
//                                        .build();
//                        PhoneAuthProvider.verifyPhoneNumber(options);
//                    }else
//                    {
//                        Toast.makeText(MobileNumberLogin.this, "Please Enter Correct Mobile Number", Toast.LENGTH_SHORT).show();
//                    }
//                }else
//                {
//                    Toast.makeText(MobileNumberLogin.this,"Please Enter Mobile Number",Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//
//
//        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//
//            @Override
//            public void onVerificationCompleted(PhoneAuthCredential credential) {
//
//                Toast.makeText(MobileNumberLogin.this, "Sending OTP....", Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onVerificationFailed(FirebaseException e3) {
//
//                Toast.makeText(MobileNumberLogin.this, "Error Please Check your internet connection", Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onCodeSent(@NonNull String check_otp,
//                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
//
//                Intent intent = new Intent(getApplicationContext(),OtpLogin.class);
//                intent.putExtra("mobile_no",mobile_no.getText().toString());
//                intent.putExtra("check_otp",check_otp);
//                startActivity(intent);
//
//            }
//        };
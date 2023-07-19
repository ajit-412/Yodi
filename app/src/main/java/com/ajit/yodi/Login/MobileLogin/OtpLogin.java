package com.ajit.yodi.Login.MobileLogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ajit.yodi.Dashboard.Options;
import com.ajit.yodi.Model.SessionManager;
import com.ajit.yodi.R;
import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class OtpLogin extends AppCompatActivity {

    String entered_mobile;
    PinView entered_otp;
    TextView verify_otp,resend_otp;
    TextView entered_no;

    private String verificationotp, mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    //FireBase Initialisation of variables
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_login);

        mAuth = FirebaseAuth.getInstance();

        // taking mobile number from MobileNumberLogin activity
        entered_mobile = getIntent().getStringExtra("mobile_no");
        entered_no = findViewById(R.id.entered_mobile_no);
        entered_no.setText(String.format(
                "+91 %s", getIntent().getStringExtra("mobile_no"))
        );


        Toast.makeText(this, entered_mobile, Toast.LENGTH_SHORT).show();

        // otp blocks and buttons
        verify_otp = findViewById(R.id.verify_otp_btn);
        resend_otp = findViewById(R.id.resend_otp);
        entered_otp = findViewById(R.id.entered_otp);

        // for otp verification
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                String otp_code = phoneAuthCredential.getSmsCode();
                if (otp_code != null) {
                    verifycode(verificationotp, otp_code);
                }
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(OtpLogin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    // Invalid request
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    Toast.makeText(OtpLogin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(OtpLogin.this, e3.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                super.onCodeSent(s, token);
                verificationotp = s;
                mResendToken = token;
            }
        };

        sendverificationcodetouser(entered_mobile);

        verify_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = entered_otp.getText().toString();

                verifycode(verificationotp, code);
            }
        });


        resend_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resendVerificationCode(entered_mobile, mResendToken);
            }
        });


    }

    // for sending otp
    private void sendverificationcodetouser(String entered_mobile) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91" + entered_mobile)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(OtpLogin.this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    // [START resend_verification]
    private void resendVerificationCode(String phoneNumber, PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91" + phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(OtpLogin.this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .setForceResendingToken(token)     // ForceResendingToken from callbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    // [END resend_verification]


    private void verifycode(String verification_otp, String codebyuser) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verification_otp, codebyuser);
        signinusercredential(credential);
    }


    private void signinusercredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(OtpLogin.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Query check_mobile_no = FirebaseDatabase.getInstance().getReference("Users").orderByChild("mobileno").equalTo(entered_no.getText().toString());
                    check_mobile_no.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                Intent intent = new Intent(getApplicationContext(), Options.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
//                                Toast.makeText(OtpLogin.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                // Shared preferences //
                                SessionManager sessionManager = new SessionManager(OtpLogin.this);
                                String mb = entered_no.getText().toString();
                                sessionManager.mobile_login(mb);
                                // Shared preferences //
                            }else {
                                Intent intent = new Intent(getApplicationContext(), UserDetails.class);
                                intent.putExtra("mobile_no", entered_no.getText().toString());
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                } else {
                    Toast.makeText(OtpLogin.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
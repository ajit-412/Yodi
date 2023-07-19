package com.ajit.yodi.Login.GmailLogin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ajit.yodi.Dashboard.Options;
import com.ajit.yodi.Login.UserHelperClass;
import com.ajit.yodi.Model.SessionManager;
import com.ajit.yodi.R;
import com.chaos.view.PinView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.concurrent.TimeUnit;

public class GmailMobileOTP extends AppCompatActivity {

    String entered_mobile;
    PinView entered_otp;
    TextView verify_otp,entered_no,resend_otp;

    private String verificationotp;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference reference;
    StorageReference storage;

    String fn,ln,pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmail_mobile_o_t_p);

        mAuth = FirebaseAuth.getInstance();

        // taking mobile number from MobileNumberLogin activity
        entered_mobile = getIntent().getStringExtra("mobile");
        entered_no = findViewById(R.id.entered_mobile);
        entered_no.setText(String.format(
                "+91 %s",getIntent().getStringExtra("mobile"))
        );


//        Toast.makeText(this, entered_mobile, Toast.LENGTH_SHORT).show();

        // otp blocks and buttons
        verify_otp = findViewById(R.id.verify_btn);
        resend_otp = findViewById(R.id.resendOtp);
        entered_otp = findViewById(R.id.admin_otp);

        // for otp verification
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                String otp_code = phoneAuthCredential.getSmsCode();
                if(otp_code!=null)
                {
                    verifycode(verificationotp,otp_code);
                }
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(GmailMobileOTP.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Toast.makeText(GmailMobileOTP.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
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
                verifycode(verificationotp,code);


                // Getting Gmail Details
                mAuth=FirebaseAuth.getInstance();
                storage = FirebaseStorage.getInstance().getReference();

                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(GmailMobileOTP.this);
                if(account!=null){
                    String uname = account.getDisplayName();
                    String mail = account.getEmail();
                    pic = account.getPhotoUrl().toString();

                    String f_l= uname;
                    String user_name[] = (f_l.split(" "));

                    for(int i=0;i<=user_name.length;i++){
                        fn=user_name[0];
                        ln= user_name[1];
                    }

                    database = FirebaseDatabase.getInstance();
                    reference = database.getReference("Users");


                    //Get all the values
                    String mobileno = entered_no.getText().toString();
                    String fname = fn;
                    String lname = ln;
                    String email = mail;
                    String profile = pic;

                    UserHelperClass helperClass = new UserHelperClass(mobileno,fname,lname,email,profile);
                    reference.child(mobileno).setValue(helperClass);

//                    Toast.makeText(GmailMobileOTP.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                    // Shared preferences //
                    SessionManager sessionManager = new SessionManager(GmailMobileOTP.this);
                    sessionManager.mobile_login(mobileno);
                    // Shared preferences //
                }
                // Getting Gmail Details



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
                        .setActivity(GmailMobileOTP.this)                 // Activity (for callback binding)
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
                        .setActivity(GmailMobileOTP.this)                 // Activity (for callback binding)
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
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(GmailMobileOTP.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(), Options.class);
                    intent.putExtra("mobile_no", entered_no.getText().toString());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(GmailMobileOTP.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    // Profile Pic

    private void upload_profile_pic_firebase(Uri uri) {
        StorageReference fileRef = storage.child("ProfilePic/"+mAuth.getCurrentUser().getUid()).child(("Profile"+".jpg"));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(GmailMobileOTP.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(GmailMobileOTP.this, "Something Went Wrong !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
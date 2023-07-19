package com.ajit.yodi.Fragments.Settings;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ajit.yodi.Dashboard.MainActivity;
import com.ajit.yodi.Login.UserHelperClass;
import com.ajit.yodi.Model.SessionManager;
import com.ajit.yodi.R;
import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    TextView first_name,last_name,mobile_no,mail,fullname;
    CircleImageView prof_img;
    ImageButton img_picker;

    FirebaseDatabase data;
    DatabaseReference reference;
    StorageReference storage;
    FirebaseAuth mAuth;

    String profileimg;

    Uri uri;

    SwipeRefreshLayout img_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        data = FirebaseDatabase.getInstance();
        reference = data.getReference().child("Users");
        storage = FirebaseStorage.getInstance().getReference();

        first_name = findViewById(R.id.fname);
        last_name = findViewById(R.id.lname);
        mobile_no = findViewById(R.id.mnumber);
        mail = findViewById(R.id.gmail);
        prof_img = findViewById(R.id.prof_img);
        fullname = findViewById(R.id.full_name);
        img_picker = findViewById(R.id.edit_img_picker);
//
//
        img_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(Profile.this)
                        .cropSquare() //Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });
//
//
        SessionManager sessionManagerI = new SessionManager(this);
        HashMap<String, String> gm = sessionManagerI.GetMobileLoginData();

        String mb = gm.get(SessionManager.KEY_MOBILE_NO);
//        Toast.makeText(this, mb, Toast.LENGTH_SHORT).show();

        img_update = findViewById(R.id.updater);
        img_update.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(uri != null){
                    String fn = first_name.getText().toString();
                    String ln = last_name.getText().toString();
                    String gm = mail.getText().toString();
                    String mbno = mobile_no.getText().toString();

                    uploadToFirebase(uri);
                    Toast.makeText(Profile.this, "url detected", Toast.LENGTH_SHORT).show();
                    UserHelperClass helperClass = new UserHelperClass(mbno,fn,ln,gm,uri.toString());
                    reference.child(mb).setValue(helperClass);
                    Intent i_again = new Intent(getApplicationContext(), Profile.class);
                    i_again.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i_again);
                    Toast.makeText(Profile.this, "Profile Pic Updated Successfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Profile.this, "Please Select Image", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Query get_user_data = reference.child(mb);
        get_user_data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
//                    if(){
                        String fn = snapshot.child("firstname").getValue(String.class);
                        String ln = snapshot.child("lastname").getValue(String.class);
                        String mobile = snapshot.child("mobileno").getValue(String.class);
                        String email = snapshot.child("email").getValue(String.class);
                        profileimg = snapshot.child("profile_img").getValue(String.class);

                        first_name.setText(fn);
                        last_name.setText(ln);
                        mail.setText(email);
                        mobile_no.setText(mobile);
                        fullname.setText(fn+" "+ln);
                        Glide.with(getApplicationContext()).load(profileimg).into(prof_img);
//                    }
                }else {
                    Toast.makeText(Profile.this, "No Such User Exist!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
//
//
//
    }
//
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uri = data.getData();
        prof_img.setImageURI(uri);
        Toast.makeText(this, "Swipe Down To Update Profile Pic", Toast.LENGTH_SHORT).show();
    }
//
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!this.isDestroyed()) {
            Glide.with(Profile.this).pauseRequests();
        }
    }


    private void uploadToFirebase(Uri uri) {
        StorageReference fileRef = storage.child("ProfilePic/"+mAuth.getCurrentUser().getUid()).child(("Profile"+"."+getFileExtension(uri)));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Profile.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Profile.this, "Something Went Wrong !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri mUri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Profile.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}



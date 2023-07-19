package com.ajit.yodi.Login.MobileLogin;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ajit.yodi.Dashboard.Options;
import com.ajit.yodi.Login.UserHelperClass;
import com.ajit.yodi.R;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserDetails extends AppCompatActivity {

    EditText first_name,last_name,email;
    TextView register;
    ImageButton image_picker;
    CircleImageView profile_image;


    Uri img_uri;
    Boolean image_selected = false;

    StorageReference storage;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        email = findViewById(R.id.gmail);
        register = findViewById(R.id.register_btn);
        image_picker = findViewById(R.id.floating_img_picker);
        profile_image = findViewById(R.id.get_profile_image);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("Users");
                storage = FirebaseStorage.getInstance().getReference();
                mAuth = FirebaseAuth.getInstance();



                //Get all the values
                String mobileno = getIntent().getStringExtra("mobile_no");
                String fname = first_name.getText().toString();
                String lname = last_name.getText().toString();
                String mail = email.getText().toString();

                if(fname.isEmpty()){
                    Toast.makeText(UserDetails.this, "Enter First Name !!", Toast.LENGTH_SHORT).show();
                }else if(lname.isEmpty()){
                    Toast.makeText(UserDetails.this, "Enter Last Name !!", Toast.LENGTH_SHORT).show();
                }else if(mail.isEmpty()){
                    Toast.makeText(UserDetails.this, "Enter Email Id !!", Toast.LENGTH_SHORT).show();
                }else if(img_uri == null){
                    Toast.makeText(UserDetails.this, "Select Profile Picture", Toast.LENGTH_SHORT).show();
                }else {
                    upload_profile_pic_firebase(img_uri);
//                    Toast.makeText(UserDetails.this, "url detected", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(UserDetails.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UserDetails.this, Options.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                    UserHelperClass helperClass = new UserHelperClass(mobileno,fname,lname,mail,img_uri.toString());
                    reference.child(mobileno).setValue(helperClass);

                }
            }
        });

        image_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(UserDetails.this)
                        .cropSquare() //Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

            }
        });

    }


    private void upload_profile_pic_firebase(Uri uri) {
        StorageReference fileRef = storage.child("ProfilePic/"+mAuth.getCurrentUser().getUid()).child(("Profile"+"."+getFileExtension(uri)));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                Toast.makeText(UserDetails.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UserDetails.this, "Something Went Wrong !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri mUri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        img_uri = data.getData();
        profile_image.setImageURI(img_uri);
        image_selected = true;
    }

}
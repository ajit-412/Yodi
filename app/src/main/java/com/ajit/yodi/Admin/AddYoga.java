package com.ajit.yodi.Admin;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ajit.yodi.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddYoga extends AppCompatActivity {

    EditText yname,yreps;
    ImageView yimg;
    Button submit;
    Spinner dname;

    FirebaseDatabase database;
    DatabaseReference reference;
    StorageReference storage = FirebaseStorage.getInstance().getReference();

    Uri imguri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_data);

        dname =findViewById(R.id.disease_list);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.disease_name, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dname.setAdapter(adapter);


        yname =findViewById(R.id.yoga_name);
//        ytime =findViewById(R.id.yoga_time);
        yreps =findViewById(R.id.yoga_reps);
        yimg =findViewById(R.id.img);
        submit =findViewById(R.id.submit);




        yimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryintent = new Intent();
                galleryintent.setAction(Intent.ACTION_GET_CONTENT);
                galleryintent.setType("image/*");
                startActivityForResult(galleryintent,2);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(imguri != null){
                    uploadToFirebase(imguri);

                    Toast.makeText(AddYoga.this, "url detected", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(AddYoga.this, "Please Select Image", Toast.LENGTH_SHORT).show();
                }

                //Get all the values
//                String imageuri = imguri.toString();
//                Toast.makeText(AddYoga.this, "Uploaded Successfully!!", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2 && resultCode == RESULT_OK && data != null){
            imguri =data.getData();
            yimg.setImageURI(imguri);
            Toast.makeText(this, "Set Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadToFirebase(Uri uri) {
        StorageReference fileRef = storage.child("YogaImage").child(dname.getSelectedItem().toString()).child(System.currentTimeMillis() +"."+ getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        database = FirebaseDatabase.getInstance();
                        reference = database.getReference("Yoga");
                        String disn = dname.getSelectedItem().toString();
                        String yogn = yname.getText().toString();
                        String reps = yreps.getText().toString();

                        YHelper helper = new YHelper(disn,yogn,reps,uri.toString());
                        reference.child(disn).child(yogn).setValue(helper);
                        Toast.makeText(AddYoga.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddYoga.this, "Uploading Failed !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri mUri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }

}



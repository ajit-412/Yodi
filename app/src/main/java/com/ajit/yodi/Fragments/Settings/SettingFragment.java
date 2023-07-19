package com.ajit.yodi.Fragments.Settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ajit.yodi.Login.LoginPage;
import com.ajit.yodi.Model.SessionManager;
import com.ajit.yodi.R;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;


public class SettingFragment extends Fragment {

    public SettingFragment() {
        // Required empty public constructor
    }

    ImageView prof_logo,about_logo,privacy_policy_logo,version_logo,share_logo;
    TextView prof,about,fullname,privacy_policy,version,share;
    Button logout;
    CircleImageView user_img;

    FirebaseDatabase data;
    DatabaseReference reference;


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        data = FirebaseDatabase.getInstance();
        reference = data.getReference().child("Users");

        prof_logo = view.findViewById(R.id.profile_logo);
        prof = view.findViewById(R.id.profile);
        about_logo = view.findViewById(R.id.about_logo);
        about = view.findViewById(R.id.about_us);
        privacy_policy_logo = view.findViewById(R.id.privacy_policy_img);
        privacy_policy = view.findViewById(R.id.privacy_policy);
        version_logo = view.findViewById(R.id.version_logo);
        version = view.findViewById(R.id.version);
        share_logo = view.findViewById(R.id.share_img);
        share = view.findViewById(R.id.share);
        logout = view.findViewById(R.id.logout);
        fullname = view.findViewById(R.id.user_full_name);
        user_img = view.findViewById(R.id.profile_image);


        SessionManager sessionmanager = new SessionManager(getContext());
        HashMap<String, String> gm = sessionmanager.GetMobileLoginData();

        String mb = gm.get(SessionManager.KEY_MOBILE_NO);
//        Toast.makeText(getContext(), mb, Toast.LENGTH_SHORT).show();

        Query get_user_data = reference.child(mb);
        get_user_data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    if(isAdded()){
                        String fn = snapshot.child("firstname").getValue(String.class);
                        String ln = snapshot.child("lastname").getValue(String.class);
                        String profileimg = snapshot.child("profile_img").getValue(String.class);

                        fullname.setText(fn+" "+ln);
                        Glide.with(getContext()).load(profileimg).into(user_img);
                    }
                }else {
                    Toast.makeText(getContext(), "No Such User Exist!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        // Profile Section
        prof_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),Profile.class);
                startActivity(intent);
            }
        });

        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),Profile.class);
                startActivity(intent);
            }
        });
        // Profile Section

        // About Us Section
        about_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),AboutUs.class);
                startActivity(intent);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),AboutUs.class);
                startActivity(intent);
            }
        });
        // About Us Section

        // Privacy Policy Section
        privacy_policy_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.termsfeed.com/live/c4508377-b7ae-44a3-90e2-101d6c95fa68");
            }
        });

        privacy_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.termsfeed.com/live/c4508377-b7ae-44a3-90e2-101d6c95fa68");
            }
        });
        // Privacy Policy Section

        // Version Section
        version_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Version 1.0.1", Toast.LENGTH_SHORT).show();
            }
        });

        version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Version 1.0.1", Toast.LENGTH_SHORT).show();
            }
        });
        // Version Section

        // Share Section
        share_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareBody = "Download this Application noe :-https://www.redcrossblood.org/blood-donor-app.html=en";
                String shareSub = "Yodi App";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                startActivity(Intent.createChooser(shareIntent, "Share Using"));
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareBody = "Download this Application noe :- Yodi App";
                String shareSub = "https://www.redcrossblood.org/blood-donor-app.html=en";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                startActivity(Intent.createChooser(shareIntent, "Share Using"));
            }
        });
        // Share Section


        // Logout Button
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginPage.class);
                SessionManager sessionManager = new SessionManager(getContext());
                sessionManager.logout_session();
                startActivity(intent);
            }
        });
        // Logout Button


        return view;
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}
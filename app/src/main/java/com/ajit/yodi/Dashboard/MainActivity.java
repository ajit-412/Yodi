package com.ajit.yodi.Dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.ajit.yodi.Fragments.Diet.DietFragment;
import com.ajit.yodi.Fragments.Settings.SettingFragment;
import com.ajit.yodi.Fragments.Yoga.YogaFragment;
import com.ajit.yodi.R;
import com.gauravk.bubblenavigation.IBubbleNavigation;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

public class MainActivity extends AppCompatActivity {

    IBubbleNavigation bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, new YogaFragment());
        transaction.commit();


        bottomNavigationView = findViewById(R.id.top_navigation_constraint);

        bottomNavigationView.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (position){
                    case 0:
                        transaction.replace(R.id.container, new YogaFragment());
                        break;
                    case 1:
                        transaction.replace(R.id.container, new DietFragment());
                        break;
                    case 2:
                        transaction.replace(R.id.container, new SettingFragment());
                        break;
                }
                transaction.commit();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,Options.class);
        startActivity(intent);
    }
}





//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                switch (item.getItemId()){
//                    case R.id.yoga:
//                        transaction.replace(R.id.container, new YogaFragment());
//                        break;
//                    case R.id.diet:
//                        transaction.replace(R.id.container, new DietFragment());
////                        Toast.makeText(MainActivity.this, problem_name, Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.settings:
//                        transaction.replace(R.id.container, new SettingFragment());
//                        break;
//                }
//                transaction.commit();
//                return true;
//            }
//        });

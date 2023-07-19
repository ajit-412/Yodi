package com.ajit.yodi.Fragments.Yoga;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ajit.yodi.R;
import com.bumptech.glide.Glide;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class YogaView extends AppCompatActivity {

    String image_id;
    String name,reps;

    TextView title,timer,yreps;
    TextView working_btn,start,pause,finish;
    ImageView YogaI;

    CountDownTimer count;
    boolean timer_running;

    long duration = TimeUnit.MINUTES.toMillis( 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_view);

        timer=(TextView)findViewById(R.id.yoga_timer);
        title=(TextView)findViewById(R.id.yoga_list_title);
        yreps=(TextView)findViewById(R.id.yog_reps);
        YogaI = (ImageView)findViewById(R.id.yoga_img);
        working_btn=findViewById(R.id.working_btn);
        start=findViewById(R.id.btn_start);
        pause=findViewById(R.id.btn_pause);
        finish=findViewById(R.id.btn_finish);


        working_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start_timer();
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start_timer();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause_timer();
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Toast.makeText(YogaView.this, "Finish !!", Toast.LENGTH_SHORT).show();
            }
        });

        // Appending Data To Yoga View
        if(getIntent() != null){

            image_id = getIntent().getStringExtra("image_id");
            name =  getIntent().getStringExtra("name");
            reps =  getIntent().getStringExtra("reps");

            Glide.with(this).load(image_id).into(YogaI);
            title.setText(name);
            yreps.setText(reps);

        }
    }


    private void start_timer(){
        count = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                duration = l;
                update_count_down();
            }

            @Override
            public void onFinish() {
                Toast.makeText(YogaView.this, "Finish!!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }.start();
        timer_running = true;
        working_btn.setVisibility(View.INVISIBLE);
//        start.setVisibility(View.VISIBLE);
        pause.setVisibility(View.VISIBLE);
        finish.setVisibility(View.VISIBLE);
    }

    private void update_count_down() {
        int minutes = (int) (duration /1000) / 60;
        int seconds = (int) (duration /1000) % 60;
        String time_left_format = String.format(Locale.getDefault(), "%02d:%02d",minutes,seconds);
        timer.setText(time_left_format);
    }

    private void pause_timer(){
        count.cancel();
        timer_running = false;
        start.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}




//        new CountDownTimer(duration, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
////                int minutes = (int) (duration /1000) / 60;
////                int seconds = (int) (duration /1000) % 60;
////                minutes,seconds);
//                int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(1);
//                int seconds = (int) ((int) TimeUnit.MILLISECONDS.toSeconds(1)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(1)));
//                String sDuration = String.format(Locale.getDefault(), "%02d:%02d",minutes,seconds);
//                timer.setText(sDuration);
//            }
//
//            @Override
//            public void onFinish() {
//
//                Toast.makeText(MainActivity.this, "Finish!!", Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        }.start();

//        working_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                working_btn.setText("DONE");
//                long duration = TimeUnit.MINUTES.toMillis(1);
//
//
//                new CountDownTimer(duration, 1000) {
//                    @Override
//                    public void onTick(long millisUntilFinished) {
//                        String sDuration = String.format(Locale.ENGLISH, "%02d : %02d"
//                                            ,TimeUnit.MILLISECONDS.toMinutes(1)
//                                            ,TimeUnit.MILLISECONDS.toSeconds(1)-
//                                             TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(1)));
//                        timer.setText(sDuration);
//                    }
//
//                    @Override
//                    public void onFinish() {
//
//                        Toast.makeText(YogaView.this, "Finish!!", Toast.LENGTH_SHORT).show();
//                        finish();
//                    }
//                }.start();
//            }
//        });

//        working_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(!isrunning){
//                    working_btn.setText("DONE");
//                    new CountDownTimer(20000,1000){
//
//                        @Override
//                        public void onTick(long millisUntilFinished) {
//                            timer.setText(""+1/1000);
//                        }
//
//                        @Override
//                        public void onFinish() {
//                            Toast.makeText(YogaView.this, "Finish!!", Toast.LENGTH_SHORT).show();
//                            finish();
//                        }
//                    }.start();
//                }else{
//                    Toast.makeText(YogaView.this, "Finish!!", Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//                isrunning = !isrunning;
//            }
//        });

//        timer.setText("");











//***************************** Timer Code ****************************************//

//package com.ajit.timer;
//
//        import androidx.appcompat.app.AppCompatActivity;
//
//        import android.os.Bundle;
//        import android.os.CountDownTimer;
//        import android.view.View;
//        import android.widget.Button;
//        import android.widget.TextView;
//        import android.widget.Toast;
//
//        import java.util.Locale;
//        import java.util.concurrent.TimeUnit;
//
//public class MainActivity extends AppCompatActivity {
//
//    TextView timer;
//    Button start,pause,finish,starto;
//    CountDownTimer count;
//    boolean timer_running;
//    long duration = TimeUnit.MINUTES.toMillis(1);
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        timer = findViewById(R.id.timer);
//        start = findViewById(R.id.btn_start);
//        starto = findViewById(R.id.start_btn);
//        pause = findViewById(R.id.btn_pause);
//        finish = findViewById(R.id.btn_finish);
//
//
//        start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start_timer();
//            }
//        });
//
//        starto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start_timer();
//            }
//        });
//
//        pause.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                pause_timer();
//            }
//        });
//
//        finish.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//
//
//
//
////        new CountDownTimer(duration, 1000) {
////            @Override
////            public void onTick(long millisUntilFinished) {
//////                int minutes = (int) (duration /1000) / 60;
//////                int seconds = (int) (duration /1000) % 60;
//////                minutes,seconds);
////                int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(1);
////                int seconds = (int) ((int) TimeUnit.MILLISECONDS.toSeconds(1)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(1)));
////                String sDuration = String.format(Locale.getDefault(), "%02d:%02d",minutes,seconds);
////                timer.setText(sDuration);
////            }
////
////            @Override
////            public void onFinish() {
////
////                Toast.makeText(MainActivity.this, "Finish!!", Toast.LENGTH_SHORT).show();
////                finish();
////            }
////        }.start();
//
//    }
//
//    private void start_timer(){
//        count = new CountDownTimer(duration, 1000) {
//            @Override
//            public void onTick(long l) {
//                duration = l;
//                update_count_down();
//            }
//
//            @Override
//            public void onFinish() {
//                Toast.makeText(MainActivity.this, "Finish!!", Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        }.start();
//        timer_running = true;
//        start.setVisibility(View.INVISIBLE);
//        starto.setVisibility(View.VISIBLE);
//        pause.setVisibility(View.VISIBLE);
//        finish.setVisibility(View.VISIBLE);
//    }
//
//    private void update_count_down() {
//        int minutes = (int) (duration /1000) / 60;
//        int seconds = (int) (duration /1000) % 60;
//        String time_left_format = String.format(Locale.getDefault(), "%02d:%02d",minutes,seconds);
//        timer.setText(time_left_format);
//    }
//
//    private void pause_timer(){
//        count.cancel();
//        timer_running = false;
//
//    }
//
//}
package com.ajit.yodi.Dashboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajit.yodi.Adapters.OptionsAdapter;
import com.ajit.yodi.Model.POption;
import com.ajit.yodi.Model.SessionManager;
import com.ajit.yodi.R;

import java.util.ArrayList;
import java.util.List;

public class Options extends AppCompatActivity implements OptionsAdapter.OptionAdapterEvents {

    RecyclerView recyclerView;
    List<POption> titles;



    OptionsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_options);

        recyclerView = (RecyclerView)findViewById(R.id.myrecyclerView);
        titles = new ArrayList<>();


        adapter =  new OptionsAdapter(this,titles);

        titles.add(new POption(R.drawable.asthma,"Asthma"));
        titles.add(new POption(R.drawable.indigest,"Indigestion"));
        titles.add(new POption(R.drawable.migraine,"Migraine"));
        titles.add(new POption(R.drawable.thyroid,"Thyroid"));
        titles.add(new POption(R.drawable.depression,"Depression"));
        titles.add(new POption(R.drawable.diabetess,"Diabetes"));
        titles.add(new POption(R.drawable.liver_prob,"Liver Problem"));
        titles.add(new POption(R.drawable.lbp,"Low Blood Pressure"));
        titles.add(new POption(R.drawable.hbp,"High Blood Pressure"));
        titles.add(new POption(R.drawable.weight_loss,"Weight Loss"));



        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        
    }

    @Override
    public void onOptionClicked(POption option) {
        String op = option.getHead();

        SessionManager sessionManager = new SessionManager(Options.this);
        sessionManager.create(op);

        Intent intent =new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        return;
    }
}


//********************************************************************************************
//********************************************************************************************
//********************************************************************************************

//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//
//        transaction.replace(R.id.container, new YogaFragment());
//        transaction.commit();

//    String op = option.getHead();

// Intent intent =new Intent(this,MainActivity.class);
//        startActivity(intent);


//    SharedPreferences opc = getSharedPreferences("poption", MODE_PRIVATE);
//    SharedPreferences.Editor editor = opc.edit();
//
//        editor.putString("choice",op);
//        editor.apply();










//*************************************************************************************************
//*************************************************************************************************
//*************************************************************************************************
//*************************************************************************************************

//        Toast.makeText(this, option.getHead(), Toast.LENGTH_SHORT).show();
//        YogaFragment as =new YogaFragment();
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//
//        Bundle b = new Bundle();
//        switch (option.getHead()){
//            case "YogaFragment":
//                b.putString("op", option.getHead());
//
//                break;
//            case "Migraine":
//                b.putString("op",option.getHead());
//                break;
//        }

//        as.setArguments(b);
//        transaction.commit();

//********************************************************************************************
//        switch (holder.mytextView.getText().toString()){
//                    case "YogaFragment":
////                        intent.putExtra("asthma",holder.mytextView.getText().toString());
////                        context.startActivity(intent);
//
////                        Bundle data = new Bundle();
////                        data.putString("asthma", "YogaFragment");
////                        asthma.setArguments(data);
//                        break;
//                    case "Migraine":
////                        intent.putExtra("migraine",holder.mytextView.getText().toString());
////                        context.startActivity(intent);
////                        Bundle data_o = new Bundle();
////                        data_o.putString("migraine", "Migraine");
////                        asthma.setArguments(data_o);
//                        break;



//*******************************************************************************************

//        titles.add("YogaFragment");
//        titles.add("Migraine");
//        titles.add("Low Blood Pressure");
//        titles.add("Depression");
//        titles.add("Diabetes");
//        titles.add("Indigestion");
//        titles.add("Liver Problem");
//        titles.add("Weight Loss");
//        titles.add("Sexual Disorder");
//        titles.add("Lower Back Pain");

//        images.add(R.drawable.asthma);
//        images.add(R.drawable.migraine);
//        images.add(R.drawable.blood_pressure);
//        images.add(R.drawable.depression);
//        images.add(R.drawable.diabetes);
//        images.add(R.drawable.indigestion);
//        images.add(R.drawable.liver_problem);
//        images.add(R.drawable.weight_loss);
//        images.add(R.drawable.sexual_disorder);
//        images.add(R.drawable.lower_back_pain);
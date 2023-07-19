package com.ajit.yodi.Fragments.Yoga;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajit.yodi.Adapters.YogaListAdapter;
import com.ajit.yodi.Dashboard.Options;
import com.ajit.yodi.Model.SessionManager;
import com.ajit.yodi.Model.YogaModel;
import com.ajit.yodi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class YogaFragment extends Fragment {

    public YogaFragment() {
        // Required empty public constructor
    }

    ArrayList<YogaModel> YogaList;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    private YogaListAdapter adapter;
    private Context mcontext;
    TextView ptitle;
    private DatabaseReference reference;

    CountDownTimer count;
    boolean timer_running;
    long duration;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_yoga, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.yoga_list);
        reference = FirebaseDatabase.getInstance().getReference();
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        ptitle = view.findViewById(R.id.yoga_list_title);
        YogaList = new ArrayList<>();
        adapter = new YogaListAdapter(YogaList, getContext());
        recyclerView.setAdapter(adapter);

        GetdataFromFirebase();

        return view;
    }

    private void GetdataFromFirebase() {

        SessionManager sessionManager = new SessionManager(getContext());
        HashMap<String, String> sel_choice = sessionManager.GetUserChoice();

        String problem = sel_choice.get(SessionManager.KEY_PROBLEM);


        if (problem != null) {
            ptitle.setText(problem);
            Query query = reference.child("Yoga").child(problem);
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        YogaModel yoga = new YogaModel();
                        yoga.setImg_url(snapshot.child("imageuri").getValue().toString());
                        yoga.setName(snapshot.child("yname").getValue().toString());
                        yoga.setReps(snapshot.child("yreps").getValue().toString());

                        YogaList.add(yoga);
                    }
                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(mcontext, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Intent intent = new Intent(getContext(), Options.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

    }

}


//******************************************************************************************************************

//        initData();
//        Bundle data =getArguments();
//        String asthma =data.getString("op");

//        String migraine=data.getString("migraine");
//        if(asthma=="YogaFragment"){
//            YogaList.add(new YogaModel(R.drawable.exersice_1, "Ajit Singh"));
//            YogaList.add(new YogaModel(R.drawable.exersice_2, "pratyskah Singh"));
//            YogaList.add(new YogaModel(R.drawable.exersice_3, "Balaji Singh"));
//            YogaList.add(new YogaModel(R.drawable.exersice_4, "faewf Singh"));
//            YogaList.add(new YogaModel(R.drawable.exersice_5, "Ajay Singh"));
//        }else if(migraine=="Migraine"){
//            YogaList.add(new YogaModel(R.drawable.exersice_6, "Ajey Jhadav"));
//            YogaList.add(new YogaModel(R.drawable.exersice_7, "Ajit Singh"));
//            YogaList.add(new YogaModel(R.drawable.exersice_8, "pratyskah Singh"));
//            YogaList.add(new YogaModel(R.drawable.exersice_9, "Balaji Singh"));
//            YogaList.add(new YogaModel(R.drawable.exersice_10, "Ajay Singh"));
//        }
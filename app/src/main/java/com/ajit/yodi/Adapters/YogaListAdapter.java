package com.ajit.yodi.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajit.yodi.Fragments.Yoga.YogaView;
import com.ajit.yodi.Interface.ItemClickListener;
import com.ajit.yodi.Model.YogaModel;
import com.ajit.yodi.R;
import com.bumptech.glide.Glide;

import java.util.List;

class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ImageView image;
    public TextView text;
    public TextView reps;

    private ItemClickListener itemClickListener;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        image = (ImageView)itemView.findViewById(R.id.yoga_img);
        text = (TextView)itemView.findViewById(R.id.yoga_name);
        reps = (TextView)itemView.findViewById(R.id.y_reps);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {

        itemClickListener.onClick(view, getAdapterPosition());

    }
}

public class YogaListAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

//    private static final String Tag = "RecyclerView";
    private List <YogaModel> YogaList;
    private Context context;

    public YogaListAdapter(List<YogaModel> yogaList, Context context) {
        YogaList = yogaList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.yoga_list, parent, false);

        return  new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        Glide.with(context).load(YogaList.get(position).getImg_url()).into(holder.image);
        holder.text.setText(YogaList.get(position).getName());
        holder.reps.setText(YogaList.get(position).getReps());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(context, YogaView.class);
                intent.putExtra("image_id", YogaList.get(position).getImg_url());
                intent.putExtra("name", YogaList.get(position).getName());
                intent.putExtra("reps", YogaList.get(position).getReps());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return YogaList.size();
    }
}

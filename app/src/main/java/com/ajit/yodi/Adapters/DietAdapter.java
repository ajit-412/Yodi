package com.ajit.yodi.Adapters;


import static com.ajit.yodi.Model.DietModel.Diet_Donts_Layout;
import static com.ajit.yodi.Model.DietModel.Diet_Dos_Layout;
import static com.ajit.yodi.Model.DietModel.Donts_Head;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajit.yodi.Model.DietModel;
import com.ajit.yodi.R;

import java.util.List;


public class DietAdapter extends RecyclerView.Adapter {

    List<DietModel> dietlist;

    public DietAdapter(List<DietModel> dietlist) {
        this.dietlist = dietlist;
    }

    @Override
    public int getItemViewType(int position) {
        switch (dietlist.get(position).getViewType()){
            case 0:
                return Donts_Head;
            case 1:
                return Diet_Dos_Layout;
            case 2:
                return Diet_Donts_Layout;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType){
            case Donts_Head:
                View donts_head = LayoutInflater.from(parent.getContext()).inflate(R.layout.donts_head, parent, false);
                return new dontshead(donts_head);
            case Diet_Dos_Layout:
                View diet_do = LayoutInflater.from(parent.getContext()).inflate(R.layout.diet_dos, parent, false);
                return new dietdos(diet_do);
            case Diet_Donts_Layout:
                View diet_dont = LayoutInflater.from(parent.getContext()).inflate(R.layout.diet_donts, parent, false);
                return new dietdonts(diet_dont);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (dietlist.get(position).getViewType()){
            case Donts_Head:
                int donts_head_img = dietlist.get(position).getDont_head_img();
                String donts_head = dietlist.get(position).getDont_head();
                ((dontshead)holder).SET_DONTS_HEAD_DATA(donts_head_img,donts_head);

                break;
            case Diet_Dos_Layout:
                int dos_diet_img = dietlist.get(position).getDiet_img();
                String dos_diet_name = dietlist.get(position).getDiet_name();
                String dos_diet_desc = dietlist.get(position).getDiet_desc();
                ((dietdos)holder).SET_DOS_DATA(dos_diet_img,dos_diet_name,dos_diet_desc);

                break;
            case Diet_Donts_Layout:
                int donts_diet_img = dietlist.get(position).getDiet_img();
                String donts_diet_name = dietlist.get(position).getDiet_name();
                String donts_diet_desc = dietlist.get(position).getDiet_desc();
                ((dietdonts)holder).SET_DONTS_DATA(donts_diet_img,donts_diet_name,donts_diet_desc);

                break;
            default:
                return;
        }

    }

    @Override
    public int getItemCount() {
        return dietlist.size();
    }


    // DATA COLLECTOR

    class dietdos extends RecyclerView.ViewHolder{

        ImageView imagedos;
        TextView headdos;
        TextView subheaddos;

        public dietdos(@NonNull View itemView) {
            super(itemView);
            imagedos = itemView.findViewById(R.id.dos_img);
            headdos = itemView.findViewById(R.id.dos_head);
            subheaddos =itemView.findViewById(R.id.dos_subhead);
        }

        private void SET_DOS_DATA(int img,String head,String subhead){
            imagedos.setImageResource(img);
            headdos.setText(head);
            subheaddos.setText(subhead);
        }

    }


    class dietdonts extends RecyclerView.ViewHolder{

        ImageView imagedonts;
        TextView headdonts;
        TextView subheaddonts;

        public dietdonts(@NonNull View itemView) {
            super(itemView);
            imagedonts = itemView.findViewById(R.id.donts_img);
            headdonts = itemView.findViewById(R.id.donts_head);
            subheaddonts = itemView.findViewById(R.id.donts_subhead);
        }

        private void SET_DONTS_DATA(int img, String head, String subhead){
            imagedonts.setImageResource(img);
            headdonts.setText(head);
            subheaddonts.setText(subhead);
        }

    }


    class dontshead extends RecyclerView.ViewHolder{

        ImageView imagedonts;
        TextView dontstitle;

        public dontshead(@NonNull View itemView) {
            super(itemView);
            imagedonts = itemView.findViewById(R.id.donts_sign);
            dontstitle = itemView.findViewById(R.id.donts_title);
        }

        private void SET_DONTS_HEAD_DATA(int img,String head){
            imagedonts.setImageResource(img);
            dontstitle.setText(head);
        }

    }

}




//****************************************************************************************

//public class DietAdapter extends RecyclerView.Adapter<DietAdapter.MyViewHolder>{
//
//    Context context;
//    List<DietModel> dietlist;
//
//    public DietAdapter(Context context, List<DietModel> dietlist) {
//        this.context = context;
//        this.dietlist = dietlist;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.diet_dos, parent, false);
//        return new MyViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.text.setText(dietlist.get(position).getDiet_name());
//        holder.imageView.setImageResource(dietlist.get(position).getDiet_img());
//        holder.desc.setText(dietlist.get(position).getDiet_desc());
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return dietlist.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        ImageView imageView;
//        TextView text;
//        TextView desc;
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            imageView = itemView.findViewById(R.id.diet_img);
//            text = itemView.findViewById(R.id.diet_title);
//            desc = itemView.findViewById(R.id.diet_title);
//        }
//    }
//
//}



//*********************************************************************************************

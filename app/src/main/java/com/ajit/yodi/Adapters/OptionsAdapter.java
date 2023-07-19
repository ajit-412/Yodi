package com.ajit.yodi.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ajit.yodi.Model.POption;
import com.ajit.yodi.R;

import java.util.List;


public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.ViewHolder>{

    private List<POption> titles;
    private OptionAdapterEvents optionAdapterEvents;

    public OptionsAdapter(OptionAdapterEvents optionAdapterEvents,List<POption> titles) {
        this.optionAdapterEvents=optionAdapterEvents;
        this.titles=titles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.option_menu, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageview.setImageResource(titles.get(position).getImage());
        holder.title.setText(titles.get(position).getHead());
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        View view;
        TextView title;
        ImageView imageview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView.findViewById(R.id.cardView);
            title=itemView.findViewById(R.id.option_title);
            imageview=itemView.findViewById(R.id.option_img);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
//            Intent intent = new Intent(v.getContext(), MainActivity.class);
//            v.getContext().startActivity(intent);
            optionAdapterEvents.onOptionClicked(titles.get(position));
        }
    }

    public interface OptionAdapterEvents{
        void onOptionClicked(POption option);
    }

}







//*************************************************************************************************
//*************************************************************************************************
//*************************************************************************************************
//*************************************************************************************************
//*************************************************************************************************


//class Optionholder extends RecyclerView.ViewHolder implements View.OnClickListener{
//
//    public ImageView imageview;
//    public TextView textview;
//
//    private option_click opclick;
//
//    public Optionholder(@NonNull View itemView) {
//        super(itemView);
//        imageview = (ImageView)itemView.findViewById(R.id.option_img);
//        textview = (TextView)itemView.findViewById(R.id.option_title);
//
//        itemView.setOnClickListener(this);
//    }
//
//    public void setOpclick(option_click opclick) {
//        this.opclick = opclick;
//    }
//
//    @Override
//    public void onClick(View v) {
//        opclick.onClick(v,getAdapterPosition());
//    }
//}


//    extends RecyclerView.Adapters<Optionholder>
//
//    private Context context;
//    private List<POption> titles;
////    private List<POption> images;
//
//    public OptionsAdapter(Context context, List<POption> titles) {
//        this.context = context;
//        this.titles = titles;
////        this.images = images;
////        List<POption> images
//    }
//
//    @NonNull
//    @Override
//    public Optionholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View itemView = inflater.inflate(R.layout.option_menu, parent, false);
//
//        return new Optionholder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull Optionholder holder, int position) {
//        holder.imageview.setImageResource(titles.get(position).getImage());
//        holder.textview.setText(titles.get(position).getHead());
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentManager manager = getSupportFragmentManager();
//                FragmentTransaction transaction = manager.beginTransaction();
//                YogaFragment asthma =new YogaFragment();
//                Bundle b = new Bundle();
//                b.putString();
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return titles.size();
//    }





//*************************************************************************************************
//*************************************************************************************************
//*************************************************************************************************
//*************************************************************************************************



//     extends RecyclerView.Adapters<OptionsAdapter.MyViewHolder>

//    private Context context;
//    private List<String> titles;
//    private List<Integer> images;
//
//    public OptionsAdapter(Context context, List<String> titles, List<Integer> images){
//
//        this.context = context;
//        this.titles = titles;
//        this.images = images;
//
//    }
//
//
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.option_menu, parent, false);
//        return new MyViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//
//        holder.mytextView.setText(titles.get(position));
//        holder.myimageView.setImageResource(images.get(position));
//
//        holder.myimageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                Intent intent = new Intent(context, MainActivity.class);
//                switch (holder.mytextView.getText().toString()){
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
//                }
//                Toast.makeText(v.getContext(), "Clicked on : "+holder.mytextView.getText().toString(), Toast.LENGTH_SHORT).show();
//
//            }
//
//        });
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return titles.size();
//    }
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder {
//
//        ImageView myimageView;
//        TextView mytextView;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            myimageView = itemView.findViewById(R.id.option_img);
//            mytextView = itemView.findViewById(R.id.option_title);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    switch (getAdapterPosition()){
////                        case 0:
////                            Intent intent = new Intent(itemView.getContext(), UserDetails.class);
////
////                            break;
//                    }
//                    Toast.makeText(v.getContext(), "Clicked on : "+ getAdapterPosition(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//    }

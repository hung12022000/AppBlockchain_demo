package com.example.huydz;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class mainadapter extends RecyclerView.Adapter<mainadapter.MainViewHoder>{
    Context context;
    List<articles> cateList;


    public mainadapter(Context context, List<articles> cateList) {
        this.context = context;
        this.cateList = cateList;
    }

    @NonNull
    @Override
    public MainViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHoder(LayoutInflater.from(context).inflate(R.layout.item_recycle,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHoder holder, int position) {
        holder.textView.setText(cateList.get(position).getTitle());

        int categoryId = cateList.get(position).getId().intValue();

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context.getApplicationContext(), "Sang trang course có id : "+cateList.get(position).getId(),Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(view.getContext(), ItemActivity.class);
                intent.putExtra("data",cateList.get(position));
                view.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return (cateList!=null)?cateList.size():0;
    }

    public static final class  MainViewHoder extends RecyclerView.ViewHolder{
        TextView textView;


        public MainViewHoder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textView3);


        }


    }

}

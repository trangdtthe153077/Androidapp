package com.example.firebase.Adapter;

import static com.example.firebase.Model.Common.comicList;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import com.example.firebase.ChaptersActivity;
import com.example.firebase.Interface.IRecyclerItenClickListener;
import com.example.firebase.Model.Common;


public class MyComicAdapter {
    Context context;
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i){
        myViewHolder.setRecyclerItenClickListener(new IRecyclerItenClickListener() {
            @Override
            public void onClick(View view, int position) {
                Common.comicSelected = comicList.get(position);
                context.startActivity(new Intent(context, ChaptersActivity.class));
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        IRecyclerItenClickListener recyclerItenClickListener;

        public void setRecyclerItenClickListener(IRecyclerItenClickListener recyclerItenClickListener) {
            this.recyclerItenClickListener = recyclerItenClickListener;
        }
        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            recyclerItenClickListener.onClick(view,getAdapterPosition());
        }
    }
}

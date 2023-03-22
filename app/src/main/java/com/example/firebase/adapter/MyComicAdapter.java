package com.example.firebase.adapter

import android.support.v7.width.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

class MyComicAdapter extends RecyclerView.Adapter<MyComicAdapter.MyViewHolder> {
    public class MyViewHolde extends RecyclerView.ViewHolder {
        TextView comic_name;
        ImageView comic_image;

        public MyViewHolder(@NonNull View item View){
            super(itemView);

            comic_image = (ImageView) itemView.findViewById(R.id.pager_image);
            comic_name = (TextView) itemView.findViewById(R.id.comic_name);
        }
    }
}
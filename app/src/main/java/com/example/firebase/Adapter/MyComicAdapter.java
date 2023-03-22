package com.example.firebase.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.Model.Comic;
import com.example.firebase.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyComicAdapter extends RecyclerView.Adapter<MyComicAdapter.MyViewHolder> {

    Context context;
    List<Comic> comics;
    LayoutInflater inflater;

    public MyComicAdapter(Context context, List<Comic> comics) {
        this.context = context;
        this.comics = comics;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
View itemView = inflater.inflate(R.layout.comic_item,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(comics.get(position).Image).into(holder.comic_image);
        holder.comic_name.setText(comics.get(position).Name);
    }

    @Override
    public int getItemCount() {
        return comics.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView comic_name;
        ImageView comic_image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            comic_image = itemView.findViewById(R.id.image_comic);
            comic_name = itemView.findViewById(R.id.comic_name);
        }
    }


}

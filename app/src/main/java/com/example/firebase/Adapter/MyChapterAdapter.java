package com.example.firebase.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.Interface.IRecyclerItenClickListener;
import com.example.firebase.Model.Chapter;
import com.example.firebase.Model.Common;
import com.example.firebase.R;
import com.example.firebase.ViewComicActicity;

import java.util.List;

public class MyChapterAdapter extends RecyclerView.Adapter<MyChapterAdapter.MyViewHolder> {
    Context context;
    List<Chapter> chapterList;
    LayoutInflater inflater;

    public MyChapterAdapter(Context context, List<Chapter> chapterList) {
        this.context = context;
        this.chapterList = chapterList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.chapter_item,viewGroup,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        myViewHolder.txt_chapter_numb.setText(chapterList.get(i).Name);

        myViewHolder.setRecyclerItenClickListener(new IRecyclerItenClickListener() {
            @Override
            public void onClick(View view, int position) {
                Common.chapterSelected = chapterList.get(position);
                Common.chapterIndex = position;
                context.startActivity(new Intent(context,ViewComicActicity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_chapter_numb;
        IRecyclerItenClickListener recyclerItenClickListener;

        public void setRecyclerItenClickListener(IRecyclerItenClickListener recyclerItenClickListener) {
            this.recyclerItenClickListener = recyclerItenClickListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_chapter_numb = (TextView) itemView.findViewById(R.id.txt_chapter_numb);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            recyclerItenClickListener.onClick(view, getAdapterPosition());

        }
    }
}

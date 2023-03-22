package com.example.firebase.adapter;

import java.util.List;

public class MySliderAdapter extends SliderAdapter {
    private List<String> imageList;

    public MySliderAdapter(List<String> imageList){
        this.imageList = imageList;
    }

    public int getItemCount(){
        return imageList.size();
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder imageViewHolder){
        imageSlideViewHolder.bindImageSlide(imageList.get(position));
    }
}

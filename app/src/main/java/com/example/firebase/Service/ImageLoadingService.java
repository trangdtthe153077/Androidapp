package com.example.firebase.Service;

import android.widget.ImageView;

public interface ImageLoadingService {
    public void loadImage(String url, ImageView imageView);
    public void loadImage(int resource, ImageView imageView);
    public void loadImage(String url, int placeHolder, int errorDrawble, ImageView imageView);
}

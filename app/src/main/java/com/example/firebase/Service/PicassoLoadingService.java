package com.example.firebase.Service;

import android.widget.ImageView;

public class PicassoLoadingService implements ImageLoadingService {
    @Override
    public void loadImage(String url, ImageView imageView) {

    }

    @Override
    public void loadImage(int resource, ImageView imageView) {
        Picasso.get().load(resource).into(imageView);
    }

    @Override
    public void loadImage(String url, int placeHolder, int errorDrawble, ImageView imageView) {
        Picasso.get().load(url).placeholder(placeHolder).error(errorDrawble).into(imageView);
    }
}

package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.firebase.Adapter.MySliderAdapter;
import com.example.firebase.Interface.IBannerLoadDone;
import com.example.firebase.Service.PicassoLoadingService;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


import ss.com.bannerslider.Slider;

public class MainActivity extends AppCompatActivity {

    Slider slider;
    DatabaseReference banners;
    IBannerLoadDone bannerListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        banners= FirebaseDatabase.getInstance().getReference("Banners");
        slider = (Slider) findViewById(R.id.slider);
        Slider.init(new PicassoLoadingService());

        LoadBanner();
    }

    public void LoadBanner()
    {
        banners.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

List<String> bannerList= new ArrayList<>();
for(DataSnapshot bannerSnapshot:snapshot.getChildren())
{
    String image = bannerSnapshot.getValue(String.class);
    Log.i("a",image);
    bannerList.add(image);
}

                Log.d("a",bannerList.get(1));
                bannerListener.onBannerLoadDoneListener(bannerList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this,"", Toast.LENGTH_SHORT ).show();
            }
        });
    }

    public void onBannerLoadDoneListener(List<String>banners)
    {

slider.setAdapter(new MySliderAdapter(banners));
    }
}
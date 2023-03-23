package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebase.Adapter.MyComicAdapter;
import com.example.firebase.Adapter.MySliderAdapter;
import com.example.firebase.Common.Common;
import com.example.firebase.Interface.IBannerLoadDone;
import com.example.firebase.Interface.IComicLoadDone;
import com.example.firebase.Model.Comic;
import com.example.firebase.Model.Manga;
import com.example.firebase.Service.PicassoLoadingService;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


import ss.com.bannerslider.Slider;

public class MainActivity extends AppCompatActivity implements IComicLoadDone {

    Slider slider;
    DatabaseReference banners;
    DatabaseReference comics;
    IBannerLoadDone bannerListener;
    IComicLoadDone iComicLoadDone;
    RecyclerView recycler_comic;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        banners= FirebaseDatabase.getInstance("https://androidproject-da7c5-default-rtdb.firebaseio.com").getReference("Banner");
        comics= FirebaseDatabase.getInstance("https://androidproject-da7c5-default-rtdb.firebaseio.com").getReference("Comic");
        slider = (Slider) findViewById(R.id.slider);
        Slider.init(new PicassoLoadingService());
bannerListener=this::onBannerLoadDoneListener;
        iComicLoadDone=this::onComicLoadListener;
        LoadBanner();
        LoadComic();

        recycler_comic=(RecyclerView) findViewById(R.id.recycler_comic);
        recycler_comic.setHasFixedSize(true);
        recycler_comic.setLayoutManager(new GridLayoutManager(this,2));

    }

    public void  LoadComic()
    {
        List<Comic> comicList= new ArrayList<>();
        comics.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot bannerSnapshot:snapshot.getChildren())
                {
                    Comic comic = bannerSnapshot.getValue(Comic.class);
                    comicList.add(comic);
                }

                iComicLoadDone.onComicLoadListener(comicList );
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
    Log.i("sdfsdfsfd","moy con vit");
    Log.i("anh anh anh",image);
    bannerList.add(image);
}


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

    @Override
    public void onComicLoadListener(List<Comic> comicList) {
        Common.comicList=comicList;

        recycler_comic.setAdapter(new MyComicAdapter( getBaseContext(),comicList));

    }
}
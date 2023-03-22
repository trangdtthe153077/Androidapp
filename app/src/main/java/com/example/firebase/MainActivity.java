package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.slider.Slider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    DatabaseReference banners;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Slider slider;
        SwipeRefreshLayout swipeRefreshLayout;
        RecyclerView recycler_comic;
        TextView txt_comic;
        //database
        DatabaseReference banners, comics;

        //Listener
        IBannerLoadDone bannerLoadDone;
        IComicLoadDone comicLoadDone;


        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");

// Creating new user node, which returns the unique key value
// new user node would be /users/$userid/
        String userId = mDatabase.push().getKey();

// creating user object
        User user = new User("Ravi Tamada", "ravi@androidhive.info");

// pushing user to 'users' node using the userId
        mDatabase.child(userId).setValue(user);

        mDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);

                Log.d("a", "User name: " + user.getName() + ", email " + user.getEmail());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("a", "Failed to read value.", error.toException());
            }
        });

    }

    public void LoadBanner()
    {
        banners.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
List<String> bannerList= new ArrayList<>();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this,"", Toast.LENGTH_SHORT ).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main_2);

        //Init Database
        banners = FirebaseDatabase.getInstance().getReference("Banners");
        comics = FirebaseDatabase.getInstance().getReference("Comics");

        //Inir Listener
        bannerListener = this;
        comicListener = this;

        android.app.AlertDialog alertDialog;

        slider = (Slider) findViewById(R.id.slider);
        slider.init(new PicassoLoadingService());

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_to_refresh);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.color_textview,
                R.color.color_line
        );
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadBanner();
                loadComic();
            }
            private void loadComic() {
            }

            private void loadBanner() {
            }

        });

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                loadBanner();
                loadComic();
            }

            recycler_comic = (RecyclerView)findViewById(R.id.recycler_comic);
            recycler_comic.setHasFixedSize(true);
            recycler_comic.setLayoutManager(new GridLayout(this, 2));

            txt_comic = (TextView)findViewById(R.id.txt_comic);

            private void loadComic() {
                //show dialog
                android.app.AlertDialog alertDialog = new SpotsDialog.Builder().setContext(this)
                        .setCancelable(false)
                        .setMessage("Waiting...")
                        .build();

                if (!swipeRefreshLayout.isRefreshing())
                    alertDialog.show();

                comics.addListenerForSingleValueEvent(new ValueEventListener() {
                    List<Comic> comics_load = new ArrayList<>();
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot comicSnapShot:dataSnapshot.getChildren()){
                            Comic comic = comicSnapShot.getValue(Comic.class);
                            comics_load.add(comic);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public  void onBannerLoadDoneListener(List<String> banners){
        slider.setAdapter(new MySliderAdapter(banners));

    }

    @Override
    public  void onComicLoadDoneListener(List<String> comicList){
        Common.comicList = comicList;

        recycler_comic.setAdapter(new MyComicAdapter (getBaseContext(), comicList));
        txt_comic.setText(new StringBuilder("NEW COMIC (*)").append(comicList.size()).append(")"));

        if(!swipeRefreshLayout.isRefreshing()){
            alertDialog.dismiss();
        }
    }

}
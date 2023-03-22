package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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

}
package com.example.allinoneapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.Reclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(getBaseContext(), 2));
        int Icons[] = {R.drawable.youtube,
                R.drawable.facebook,
                R.drawable.instagram,
                R.drawable.snapchat_icon,
                R.drawable.messenger,
                R.drawable.spotify,
                R.drawable.twitter,
                R.drawable.linkedin};
        recyclerView.setAdapter(new SocialAdapter(Icons));


    }
}
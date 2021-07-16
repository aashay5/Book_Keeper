package com.example.bookkeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FavouriteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        ImageView btnfavback = findViewById(R.id.btnfavback);
        btnfavback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        RecyclerView favouriteRecView = findViewById(R.id.favouriteRecView);
        BookRecViewAdapter adapter = new BookRecViewAdapter(this,"favouriteBooks");
        favouriteRecView.setAdapter(adapter);
        favouriteRecView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setBooks(Utils.getFavouriteBooks());
    }
    public void onBackPressed() {
        Intent intent = new Intent(FavouriteActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
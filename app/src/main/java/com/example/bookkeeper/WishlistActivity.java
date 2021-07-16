package com.example.bookkeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class WishlistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        ImageView btnwishback = findViewById(R.id.btnwishback);
        btnwishback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        RecyclerView wishlistRecView = findViewById(R.id.wishlistRecView);
        BookRecViewAdapter adapter = new BookRecViewAdapter(this, "wantToRead");
        wishlistRecView.setAdapter(adapter);
        wishlistRecView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setBooks(Utils.getCurrentlyReading());
    }

    public void onBackPressed() {
        Intent intent = new Intent(WishlistActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
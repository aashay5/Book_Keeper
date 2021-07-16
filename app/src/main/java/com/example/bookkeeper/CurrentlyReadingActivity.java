package com.example.bookkeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CurrentlyReadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_reading);
        ImageView btnCurback = findViewById(R.id.btnCurback);
        btnCurback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        RecyclerView currentlyReadRecView = findViewById(R.id.currentlyReadRecView);
        BookRecViewAdapter adapter = new BookRecViewAdapter(this, "currentlyReading");
        currentlyReadRecView.setAdapter(adapter);
        currentlyReadRecView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setBooks(Utils.getCurrentlyReadingBooks());
    }

    public void onBackPressed() {
        Intent intent = new Intent(CurrentlyReadingActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
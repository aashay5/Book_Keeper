package com.example.bookkeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AlreadyReadActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_read);
        ImageView btnalreadback = findViewById(R.id.btnalreadback);
        btnalreadback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        RecyclerView alreadyRecView = findViewById(R.id.alreadyRecView);
        BookRecViewAdapter adapter = new BookRecViewAdapter(this,"alreadyRead");
        alreadyRecView.setAdapter(adapter);
        alreadyRecView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setBooks(Utils.getAlreadyReadBooks());
    }
    public void onBackPressed() {
        Intent intent = new Intent(AlreadyReadActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
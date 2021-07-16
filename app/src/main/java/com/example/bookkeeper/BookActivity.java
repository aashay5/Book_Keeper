package com.example.bookkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    private TextView booName, auth, noOfPages, longDesc;
    public static final String BOOK_ID_KEY="bookId";
    private Button btnCurrentlyReading;
    private Button btnAddToFavour;
    private ImageView bookImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        ImageView btnbooksActback = findViewById(R.id.btnbookActback);
        btnbooksActback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        initView();
        Intent intent=getIntent();
        if(null!=intent){
            int bookId=intent.getIntExtra(BOOK_ID_KEY,-1);
            if(bookId!=-1){
                Book incomingBook=Utils.getInstance().getBookById(bookId);
                if(null!=incomingBook){
                    setData(incomingBook);
                    handleAlreadyRead(incomingBook);
                    handleWantToReadBook(incomingBook);
                    handleCurrentlyReadingBook(incomingBook);
                    handleFavouriteBook(incomingBook);
                }
            }
        }
    }

    private void handleAlreadyRead(Book book){
        ArrayList<Book> alreadyReadBooks=Utils.getInstance().getCurrentlyReading();
        boolean existInAlreadyReadBooks=false;
        for(Book b: alreadyReadBooks){
            if(b.getId()==book.getId()) {
                existInAlreadyReadBooks = true;
            }
        }
        if(existInAlreadyReadBooks){
            btnCurrentlyReading.setEnabled(false);
        }else{
            btnCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance().addToAlreadyRead(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this,AlreadyReadActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    public void handleWantToReadBook(Book book){
        ArrayList<Book>wanttoreadbooks=Utils.getInstance().getCurrentlyReading();
        boolean existinwanttoreadbooks=false;
        for(Book b: wanttoreadbooks){
            if(b.getId()==book.getId()) {
                existinwanttoreadbooks = true;
            }
        }
        if(existinwanttoreadbooks){
            btnCurrentlyReading.setEnabled(false);
        }else{
            btnCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance().addToWantToRead(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this,WishlistActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    public void handleCurrentlyReadingBook(Book book){
        ArrayList<Book>currentlyReading=Utils.getInstance().getCurrentlyReading();
        boolean existInCurrentlyReading=false;
        for(Book b: currentlyReading){
            if(b.getId()==book.getId()) {
                existInCurrentlyReading = true;
            }
        }
        if(existInCurrentlyReading){
            btnCurrentlyReading.setEnabled(false);
        }else{
            btnCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance().addToCurrentlyReading(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this,CurrentlyReadingActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
    public void handleFavouriteBook(Book book){

        ArrayList<Book>favouriteBooks=Utils.getInstance().getFavouriteBooks();
        boolean existInFavouriteBook=false;
        for(Book b: favouriteBooks){
            if(b.getId()==book.getId()) {
                existInFavouriteBook = true;
            }
        }
        if(existInFavouriteBook){
            btnAddToFavour.setEnabled(false);
        }else{
            btnAddToFavour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance().addToFavouriteBooks(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this,FavouriteActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void setData(Book book){
        booName.setText(book.getName());
        auth.setText((book.getAuthor()));
        noOfPages.setText(book.getPages());
        longDesc.setText(book.getLongDes());
        Glide.with(this).asBitmap().load(book.getImageUrl()).into(bookImage);
    }
    private void initView(){
        booName=findViewById(R.id.booName);
        auth=findViewById(R.id.auth);
        noOfPages=findViewById(R.id.noOfPages);
        longDesc=findViewById(R.id.longDesc);

        Button btnReading = findViewById(R.id.btnReading);
        Button btnWantTo = findViewById(R.id.btnWantTo);
        btnCurrentlyReading =findViewById(R.id.btnAlreadyRead);
        btnAddToFavour=findViewById(R.id.btnAddToFavour);

        bookImage=findViewById(R.id.bookImage);
    }
}
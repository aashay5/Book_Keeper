package com.example.bookkeeper;

import java.util.ArrayList;

public class Utils {


    private static Utils instance;
    public static ArrayList<Book> allBooks;
    private  static ArrayList<Book>alreadyReadBooks;
    public static ArrayList<Book>wantToReadBooks;
    public static ArrayList<Book>currentlyReadingBooks;
    public static ArrayList<Book>favouriteBooks;

    public Utils() {
        if(null==allBooks){
            allBooks=new ArrayList<>();
            initData();
        }
        if(null==alreadyReadBooks){
            alreadyReadBooks=new ArrayList<>();
        }
        if(null==wantToReadBooks){
            wantToReadBooks=new ArrayList<>();
        }
        if ((null==currentlyReadingBooks)){
            currentlyReadingBooks=new ArrayList<>();
        }
        if(null==favouriteBooks){
            favouriteBooks=new ArrayList<>();
        }
    }
    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }
    private void initData(){
        allBooks.add(new Book(1,"The richest man in Babylon","George Clason", 144,"https://m.media-amazon.com/images/I/51pYZS7IWcL.jpg",
                "The story behind the success of a rich ancient city",""));
        allBooks.add(new Book(2,"The power of subconsious mind","Joseph Murphy", 312,"https://images-na.ssl-images-amazon.com/images/I/51QTTApN-XL._SX324_BO1,204,203,200_.jpg",
                "This book explains about the infinite power that resides in your brain",""));
        allBooks.add(new Book(3,"The art of public speaking","Dale Carnegie", 150,"https://kbimages1-a.akamaihd.net/e0dce6d4-c3a9-41a4-bb97-54a76b0b1a4a/1200/1200/False/the-art-of-public-speaking-70.jpg",
                "Improve yourself at speaking in public",""));
        allBooks.add(new Book(4,"How to influence people and win friends","Dale Carnegie", 150,"https://images-na.ssl-images-amazon.com/images/I/611OWa8x+WL.jpg",
                "Become excellent in driving conversation you way",""));
        allBooks.add(new Book(5,"Mastery","Robert Greene", 650,"https://images-na.ssl-images-amazon.com/images/I/71LRdEUOmNL.jpg",
                "The pathway to attain mastery",""));
    }


    public static Utils getInstance() {
        if (null == instance) {
            instance = new Utils();
        }
        return instance;
    }

    public static ArrayList<Book> getCurrentlyReading() {
        return wantToReadBooks;
    }

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getFavouriteBooks() {
        return favouriteBooks;
    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }
    public Book getBookById(int id){
        for(Book b:allBooks){
            if(b.getId() == id){
                return b;
            }
        }
        return null;
    }
    public boolean addToAlreadyRead(Book book){
        return alreadyReadBooks.add(book);
    }
    public boolean addToWantToRead(Book book){
        return wantToReadBooks.add(book);
    }
    public boolean addToCurrentlyReading(Book book){
        return currentlyReadingBooks.add(book);
    }
    public boolean removeFromAlreadyRead(Book book){
        return alreadyReadBooks.remove(book);
    }
    public boolean removeFromWantToRead(Book book){
        return wantToReadBooks.remove(book);
    }
    public boolean removeFromCurrentlyReading(Book book){
        return currentlyReadingBooks.remove(book);
    }
    public boolean removeFromFavouriteBooks(Book book){
        return favouriteBooks.remove(book);
    }
    public boolean addToFavouriteBooks(Book book){
        return favouriteBooks.add(book);
    }

}

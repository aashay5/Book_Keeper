package com.example.bookkeeper;

public class Book {
    private int id;
    private String name;
    private String author;
    private int pages;
    private String imageUrl;
    private String shortDes;
    private String longDes;
    private boolean isExpanded;

    public Book(int id, String name, String author, int pages, String imageUrl, String shortDes, String longDes) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.pages = pages;
        this.imageUrl = imageUrl;
        this.shortDes = shortDes;
        this.longDes = longDes;
        isExpanded=false;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getShortDes() {
        return shortDes;
    }

    public String getLongDes() {
        return longDes;
    }

}

package com.example.pract2.model;

public class Book {
    private int Id;
    private String Name;
    private String Author;
    private int Price;
    private boolean IsDelete;

    public Book(int id, String name, String author, int price, boolean isDelete) {
        Id = id;
        Name = name;
        Author = author;
        Price = price;
        IsDelete = isDelete;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getAuthor() {
        return Author;
    }

    public int getPrice() {
        return Price;
    }

    public boolean isDelete() {
        return IsDelete;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public void setDelete(boolean delete) {
        IsDelete = delete;
    }
}

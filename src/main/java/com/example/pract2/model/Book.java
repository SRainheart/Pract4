package com.example.pract2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Size (max = 15,  min = 3, message = "Имя должно быть больше или ровно 3 символа, но и не больше 15 символов")
    private String Name;
    @Size (min = 5, max = 25, message = "Автор должнен быть больше или ровно 5 символа, но и не больше 25 символов")
    private String Author;
    @Max(value = 10000, message = "Цена книги должна быть не больше 10 тыс. рублей")
    @Min(value = 10, message = "Цена книги должна быть не меньше 10 рублей")
    private int Price;
    private boolean IsDelete;

    public Long getId() {
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

    public void setId(Long id) {
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

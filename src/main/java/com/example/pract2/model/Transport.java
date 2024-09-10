package com.example.pract2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Size(max = 15, min = 3, message = "Имя должно быть больше или ровно 3 символа, но и не больше 15 символов")
    private String Name;
    @Size (max = 25, min = 3, message = "Тип должен быть больше или ровно 3 символа, но и не больше 25 символов")
    private String Type;
    private boolean IsDelete;
    public Long getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getType() {
        return Type;
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

    public void setType(String type) {
        Type = type;
    }

    public void setDelete(boolean delete) {
        IsDelete = delete;
    }
}

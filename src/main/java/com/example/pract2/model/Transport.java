package com.example.pract2.model;

public class Transport {
    private int Id;
    private String Name;
    private String Type;
    private boolean IsDelete;

    public Transport(int id, String name, String type, boolean isDelete) {
        Id = id;
        Name = name;
        Type = type;
        IsDelete = isDelete;
    }

    public int getId() {
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

    public void setId(int id) {
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

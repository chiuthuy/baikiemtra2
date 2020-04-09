package com.example.baikiemtra2;

import java.io.Serializable;

public class MonanModel implements Serializable {
    int id;
    String img;
    String ten;
    String gia;

    public MonanModel(int id, String img, String ten, String gia) {
        this.id = id;
        this.img = img;
        this.ten = ten;
        this.gia = gia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
}

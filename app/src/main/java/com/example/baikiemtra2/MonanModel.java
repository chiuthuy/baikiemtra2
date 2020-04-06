package com.example.baikiemtra2;

public class MonanModel {
    int img;
    String ten;
    String gia;

    public MonanModel(int img, String ten, String gia) {
        this.img = img;
        this.ten = ten;
        this.gia = gia;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
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

package com.pojo;

public class SanPham {

    private int maSP;
    private String tenSP;
    private float giaSP;
    private int soLuong;

    // Constructor đầy đủ
    public SanPham(int maSP, String tenSP, float giaSP, int soLuong) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaSP = giaSP;
        this.soLuong = soLuong;
    }

    // Constructor không tham số
    public SanPham() {
    }

    // Getter & Setter
    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public float getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(float giaSP) {
        this.giaSP = giaSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return this.tenSP; // hiển thị tên sản phẩm
    }
}

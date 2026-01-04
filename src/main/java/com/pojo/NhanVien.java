package com.pojo;

import java.util.Date;

public class NhanVien {

    private int maNV;
    private String hoten, phai;
    private Date ngaysinh;
    private String diachi;
    private float luong;
    private int phong;
    private boolean trangthai;

    public NhanVien(int maNV, String hoten, String phai, Date ngaysinh,
            String diachi, float luong, int phong, boolean trangthai) {
        this.maNV = maNV;
        this.hoten = hoten;
        this.phai = phai;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
        this.luong = luong;
        this.phong = phong;
        this.trangthai = trangthai;
    }

    public NhanVien() {
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getPhai() {
        return phai;
    }

    public void setPhai(String phai) {
        this.phai = phai;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public float getLuong() {
        return luong;
    }

    public void setLuong(float luong) {
        this.luong = luong;
    }

    public int getPhong() {
        return phong;
    }

    public void setPhong(int phong) {
        this.phong = phong;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }

    @Override
    public String toString() {
        return this.hoten; // hiển thị tên nhân viên
    }
}

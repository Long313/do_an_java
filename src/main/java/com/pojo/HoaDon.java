package com.pojo;

import java.util.ArrayList;
import java.util.Date;

public class HoaDon {

    private int maHD;
    private int maNV;
    private Date ngayLap;

    // Danh sách sản phẩm và số lượng
    private ArrayList<Integer> maSPList;
    private ArrayList<Integer> soLuongList;

    public HoaDon() {
        maSPList = new ArrayList<>();
        soLuongList = new ArrayList<>();
    }

    // ===== Getter & Setter =====
    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    // ===== Các method cho CTHD =====
    public void addCTHD(int maSP, int soLuong) {
        maSPList.add(maSP);
        soLuongList.add(soLuong);
    }

    public ArrayList<Integer> getMaSPList() {
        return maSPList;
    }

    public ArrayList<Integer> getSoLuongList() {
        return soLuongList;
    }

    public int getSoLuongCTHD() {
        return maSPList.size();
    }

    public void setMaSPList(ArrayList<Integer> maSPList) {
        this.maSPList = maSPList;
    }

    public void setSoLuongList(ArrayList<Integer> soLuongList) {
        this.soLuongList = soLuongList;
    }
}

package com.pojo;

public class PhongBan {
    private int maPHG;
    private String tenPHG;

    public PhongBan() {}

    public PhongBan(int maPHG, String tenPHG) {
        this.maPHG = maPHG;
        this.tenPHG = tenPHG;
    }

    public int getMaPHG() {
        return maPHG;
    }

    public void setMaPHG(int maPHG) {
        this.maPHG = maPHG;
    }

    public String getTenPHG() {
        return tenPHG;
    }

    public void setTenPHG(String tenPHG) {
        this.tenPHG = tenPHG;
    }
    
    @Override
    public String toString() {
        return tenPHG;   
    }
}


package com.dao;

import dataprovider.DataProvider;
import com.pojo.SanPham;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SanPhamDAO {

    // ================= SELECT ALL =================
    public static ArrayList<SanPham> layDanhSachSanPham() {
        ArrayList<SanPham> dsSP = new ArrayList<>();
        DataProvider provider = new DataProvider();

        try {
            provider.open();
            String sql = "SELECT * FROM \"SanPham\" ORDER BY \"MaSP\"";
            ResultSet rs = provider.executeQuery(sql);

            while (rs != null && rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getInt("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setGiaSP(rs.getFloat("GiaSP"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                dsSP.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            provider.close();
        }
        return dsSP;
    }

    // ================= INSERT =================
    public static boolean themSanPham(SanPham sp) {
        String sql = "INSERT INTO \"SanPham\" (\"TenSP\",\"GiaSP\",\"SoLuong\") "
                   + "VALUES (?,?,?) RETURNING \"MaSP\"";

        DataProvider provider = new DataProvider();

        try {
            provider.open();
            var ps = provider.getConnection().prepareStatement(sql);

            ps.setString(1, sp.getTenSP());
            ps.setFloat(2, sp.getGiaSP());
            ps.setInt(3, sp.getSoLuong());

            ResultSet rs = ps.executeQuery(); // RETURNING cần executeQuery
            if (rs.next()) {
                sp.setMaSP(rs.getInt(1)); // Lấy MaSP mới
                System.out.println("Thêm sản phẩm thành công, MaSP = " + sp.getMaSP());
                return true;
            }
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            provider.close();
        }
    }

    // ================= UPDATE =================
    public static boolean capNhatSanPham(SanPham sp) {
        String sql = String.format(
                "UPDATE \"SanPham\" SET "
                + "\"TenSP\"='%s', \"GiaSP\"=%f, \"SoLuong\"=%d "
                + "WHERE \"MaSP\"=%d",
                sp.getTenSP(),
                sp.getGiaSP(),
                sp.getSoLuong(),
                sp.getMaSP()
        );

        DataProvider provider = new DataProvider();
        try {
            provider.open();
            return provider.executeUpdate(sql) == 1;
        } finally {
            provider.close();
        }
    }

    // ================= DELETE =================
    public static boolean xoaSanPham(int maSP) {
        String sql = "DELETE FROM \"SanPham\" WHERE \"MaSP\"=" + maSP;
        DataProvider provider = new DataProvider();
        try {
            provider.open();
            return provider.executeUpdate(sql) == 1;
        } finally {
            provider.close();
        }
    }

    // ================= SELECT ONE =================
    public static SanPham laySanPham(int maSP) {
        DataProvider provider = new DataProvider();
        try {
            provider.open();
            String sql = "SELECT * FROM \"SanPham\" WHERE \"MaSP\"=" + maSP;
            ResultSet rs = provider.executeQuery(sql);

            if (rs != null && rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getInt("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setGiaSP(rs.getFloat("GiaSP"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                return sp;
            }
        } catch (Exception e) {
            throw new RuntimeException(e); // 🔥 Đẩy lỗi lên UI
        } finally {
            provider.close();
        }
        return null;
    }
}

package com.dao;

import dataprovider.SQLServerDataProvider;
import com.pojo.NhanVien;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NhanVienDAO {

    // ================= SELECT =================
    public static ArrayList<NhanVien> layDanhSachNhanVien() {
        ArrayList<NhanVien> dsNV = new ArrayList<>();
        SQLServerDataProvider provider = new SQLServerDataProvider();

        try {
            provider.open();
            String sql = "SELECT * FROM \"NhanVien\" ORDER BY \"MaNV\"";
            ResultSet rs = provider.executeQuery(sql);

            while (rs != null && rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getInt("MaNV"));
                nv.setHoten(rs.getString("HoTen"));
                nv.setPhai(rs.getString("Phai"));
                nv.setNgaysinh(rs.getDate("NgaySinh"));
                nv.setDiachi(rs.getString("DiaChi"));
                nv.setLuong(rs.getFloat("Luong"));
                nv.setPhong(rs.getInt("Phong"));
                nv.setTrangthai(rs.getBoolean("TrangThai"));
                dsNV.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            provider.close();
        }
        return dsNV;
    }

    // ================= INSERT =================
    public static boolean themNhanVien(NhanVien nv) {
        String sql = "INSERT INTO \"NhanVien\" "
                + "(\"HoTen\",\"Phai\",\"NgaySinh\",\"DiaChi\",\"Luong\",\"Phong\",\"TrangThai\") "
                + "VALUES (?,?,?,?,?,?,?) RETURNING \"MaNV\"";

        SQLServerDataProvider provider = new SQLServerDataProvider();

        try {
            provider.open();
            var ps = provider.getConnection().prepareStatement(sql);

            ps.setString(1, nv.getHoten());
            ps.setString(2, nv.getPhai());
            ps.setDate(3, new java.sql.Date(nv.getNgaysinh().getTime()));
            ps.setString(4, nv.getDiachi());
            ps.setFloat(5, nv.getLuong());
            ps.setInt(6, nv.getPhong());
            ps.setBoolean(7, nv.isTrangthai());

            ResultSet rs = ps.executeQuery(); // Chú ý dùng executeQuery vì có RETURNING
            if (rs.next()) {
                nv.setMaNV(rs.getInt(1)); // Lấy MaNV mới
                System.out.println("Thêm nhân viên thành công, MaNV = " + nv.getMaNV());
                return true;
            }
            return false;

        } catch (Exception e) {
            e.printStackTrace(); // quan trọng để debug
            return false;
        } finally {
            provider.close();
        }
    }

    // ================= UPDATE =================
    public static boolean capNhatNhanVien(NhanVien nv) {
        String sql = String.format(
                "UPDATE \"NhanVien\" SET "
                + "\"HoTen\"='%s',\"Phai\"='%s',\"NgaySinh\"='%s',"
                + "\"DiaChi\"='%s',\"Luong\"=%f,\"Phong\"=%d,\"TrangThai\"=%s "
                + "WHERE \"MaNV\"=%d",
                nv.getHoten(),
                nv.getPhai(),
                nv.getNgaysinh().toString(),
                nv.getDiachi(),
                nv.getLuong(),
                nv.getPhong(),
                nv.isTrangthai() ? "true" : "false",
                nv.getMaNV()
        );

        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            provider.open();
            return provider.executeUpdate(sql) == 1;
        } finally {
            provider.close();
        }
    }

    // ================= DELETE =================
    public static boolean xoaNhanVien(int maNV) {
        String sql = "DELETE FROM \"NhanVien\" WHERE \"MaNV\"=" + maNV;
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            provider.open();
            return provider.executeUpdate(sql) == 1;
        } finally {
            provider.close();
        }
    }

    // ================= SELECT ONE =================
    public static NhanVien layNhanVien(int maNV) {
        SQLServerDataProvider provider = new SQLServerDataProvider();
        try {
            provider.open();
            String sql = "SELECT * FROM \"NhanVien\" WHERE \"MaNV\"=" + maNV;
            ResultSet rs = provider.executeQuery(sql);

            if (rs != null && rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getInt("MaNV"));
                nv.setHoten(rs.getString("HoTen"));
                nv.setPhai(rs.getString("Phai"));
                nv.setNgaysinh(rs.getDate("NgaySinh"));
                nv.setDiachi(rs.getString("DiaChi"));
                nv.setLuong(rs.getFloat("Luong"));
                nv.setPhong(rs.getInt("Phong"));
                nv.setTrangthai(rs.getBoolean("TrangThai"));
                return nv;
            }
        } catch (Exception e) {
            throw new RuntimeException(e); // 🔥 ĐẨY LỖI LÊN UI
        } finally {
            provider.close();
        }
        return null;
    }
}

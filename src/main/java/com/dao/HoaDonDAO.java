package com.dao;

import dataprovider.DataProvider;
import com.pojo.HoaDon;

import java.sql.ResultSet;
import java.util.ArrayList;

public class HoaDonDAO {

    // ================= SELECT ALL =================
    public static ArrayList<HoaDon> layDanhSachHoaDon() {
        ArrayList<HoaDon> dsHD = new ArrayList<>();
        DataProvider provider = new DataProvider();

        try {
            provider.open();
            String sql = "SELECT hd.\"MaHD\", hd.\"MaNV\", hd.\"NgayLap\", cthd.\"MaSP\", cthd.\"SoLuong\" " +
                         "FROM \"HoaDon\" hd LEFT JOIN \"CTHD\" cthd ON hd.\"MaHD\" = cthd.\"MaHD\" " +
                         "ORDER BY hd.\"MaHD\"";
            ResultSet rs = provider.executeQuery(sql);

            HoaDon hd = null;
            int lastMaHD = -1;

            while (rs != null && rs.next()) {
                int maHD = rs.getInt("MaHD");
                if (maHD != lastMaHD) {
                    hd = new HoaDon();
                    hd.setMaHD(maHD);
                    hd.setMaNV(rs.getInt("MaNV"));
                    hd.setNgayLap(rs.getDate("NgayLap"));
                    dsHD.add(hd);
                    lastMaHD = maHD;
                }

                int maSP = rs.getInt("MaSP");
                int soLuong = rs.getInt("SoLuong");
                hd.addCTHD(maSP, soLuong);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            provider.close();
        }

        return dsHD;
    }

    // ================= INSERT =================
    public static boolean themHoaDon(HoaDon hd) {
        DataProvider provider = new DataProvider();
        try {
            provider.open();

            // Thêm vào bảng HoaDon
            String sqlHD = "INSERT INTO \"HoaDon\" (\"MaNV\",\"NgayLap\") VALUES (?, ?) RETURNING \"MaHD\"";
            var psHD = provider.getConnection().prepareStatement(sqlHD);
            psHD.setInt(1, hd.getMaNV());
            psHD.setDate(2, new java.sql.Date(hd.getNgayLap().getTime()));
            ResultSet rs = psHD.executeQuery();

            if (rs.next()) {
                int maHD = rs.getInt(1);
                hd.setMaHD(maHD);

                // Thêm tất cả sản phẩm vào CTHD
                String sqlCTHD = "INSERT INTO \"CTHD\" (\"MaHD\", \"MaSP\", \"SoLuong\") VALUES (?, ?, ?)";
                var psCT = provider.getConnection().prepareStatement(sqlCTHD);
                for (int i = 0; i < hd.getMaSPList().size(); i++) {
                    psCT.setInt(1, maHD);
                    psCT.setInt(2, hd.getMaSPList().get(i));
                    psCT.setInt(3, hd.getSoLuongList().get(i));
                    psCT.addBatch();
                }
                psCT.executeBatch();
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
    public static boolean capNhatHoaDon(HoaDon hd) {
        DataProvider provider = new DataProvider();
        try {
            provider.open();

            // Cập nhật bảng HoaDon
            String sqlHD = "UPDATE \"HoaDon\" SET \"MaNV\"=?, \"NgayLap\"=? WHERE \"MaHD\"=?";
            var psHD = provider.getConnection().prepareStatement(sqlHD);
            psHD.setInt(1, hd.getMaNV());
            psHD.setDate(2, new java.sql.Date(hd.getNgayLap().getTime()));
            psHD.setInt(3, hd.getMaHD());
            int result = psHD.executeUpdate();

            // Xóa CTHD cũ
            String sqlDel = "DELETE FROM \"CTHD\" WHERE \"MaHD\"=?";
            var psDel = provider.getConnection().prepareStatement(sqlDel);
            psDel.setInt(1, hd.getMaHD());
            psDel.executeUpdate();

            // Thêm CTHD mới
            String sqlCTHD = "INSERT INTO \"CTHD\" (\"MaHD\", \"MaSP\", \"SoLuong\") VALUES (?, ?, ?)";
            var psCT = provider.getConnection().prepareStatement(sqlCTHD);
            for (int i = 0; i < hd.getMaSPList().size(); i++) {
                psCT.setInt(1, hd.getMaHD());
                psCT.setInt(2, hd.getMaSPList().get(i));
                psCT.setInt(3, hd.getSoLuongList().get(i));
                psCT.addBatch();
            }
            psCT.executeBatch();

            return result == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            provider.close();
        }
    }

    // ================= DELETE =================
    public static boolean xoaHoaDon(int maHD) {
        DataProvider provider = new DataProvider();
        try {
            provider.open();
            String sql = "DELETE FROM \"HoaDon\" WHERE \"MaHD\"=?";
            var ps = provider.getConnection().prepareStatement(sql);
            ps.setInt(1, maHD);
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            provider.close();
        }
    }

    // ================= SELECT ONE =================
    public static HoaDon layHoaDon(int maHD) {
        DataProvider provider = new DataProvider();
        try {
            provider.open();
            String sql = "SELECT hd.\"MaHD\", hd.\"MaNV\", hd.\"NgayLap\", cthd.\"MaSP\", cthd.\"SoLuong\" " +
                         "FROM \"HoaDon\" hd LEFT JOIN \"CTHD\" cthd ON hd.\"MaHD\" = cthd.\"MaHD\" " +
                         "WHERE hd.\"MaHD\"=?";
            var ps = provider.getConnection().prepareStatement(sql);
            ps.setInt(1, maHD);
            ResultSet rs = ps.executeQuery();

            HoaDon hd = null;
            while (rs.next()) {
                if (hd == null) {
                    hd = new HoaDon();
                    hd.setMaHD(rs.getInt("MaHD"));
                    hd.setMaNV(rs.getInt("MaNV"));
                    hd.setNgayLap(rs.getDate("NgayLap"));
                }
                hd.addCTHD(rs.getInt("MaSP"), rs.getInt("SoLuong"));
            }
            return hd;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            provider.close();
        }
    }
}

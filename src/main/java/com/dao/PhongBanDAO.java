package com.dao;

import dataprovider.DataProvider;
import com.pojo.PhongBan;
import java.util.ArrayList;
import java.sql.ResultSet;

public class PhongBanDAO {

    public static ArrayList<PhongBan> layDanhSachPhongBan() {
        ArrayList<PhongBan> dsPB = new ArrayList<>();
        DataProvider provider = new DataProvider();
        try {
            String sql = "SELECT * FROM \"PhongBan\""; 
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            if (rs != null) { 
                while (rs.next()) {
                    PhongBan pb = new PhongBan();
                    pb.setMaPHG(rs.getInt("MaPHG"));
                    pb.setTenPHG(rs.getString("TenPHG"));
                    dsPB.add(pb);
                }
            }
            provider.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dsPB;
    }

    public static boolean themPhongBan(PhongBan pb) {
        String sql = String.format("INSERT INTO \"PhongBan\"(\"TenPHG\") VALUES('%s');", pb.getTenPHG());
        DataProvider provider = new DataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        provider.close();
        return n == 1;
    }

    public static boolean xoaPhongBan(String maPB) {
        String sql = "DELETE FROM \"PhongBan\" WHERE \"MaPHG\" = " + maPB;
        DataProvider provider = new DataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        provider.close();
        return n == 1;
    }

    public static boolean capNhatPhongBan(PhongBan pb) {
        String sql = String.format(
                "UPDATE \"PhongBan\" SET \"TenPHG\" = '%s' WHERE \"MaPHG\" = %d",
                pb.getTenPHG(), pb.getMaPHG()
        );
        DataProvider provider = new DataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        provider.close();
        return n == 1;
    }

    public static PhongBan layPhongBan(int maPB) {
        DataProvider provider = new DataProvider();
        try {
            provider.open();
            String sql = "SELECT * FROM \"PhongBan\" WHERE \"MaPHG\" = " + maPB;
            ResultSet rs = provider.executeQuery(sql);
            if (rs != null && rs.next()) {
                PhongBan pb = new PhongBan();
                pb.setMaPHG(rs.getInt("MaPHG"));
                pb.setTenPHG(rs.getString("TenPHG"));
                return pb;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            provider.close();
        }
        return null;
    }
}
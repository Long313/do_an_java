package com.dao;

import dataprovider.SQLServerDataProvider;
import com.pojo.PhongBan;
import java.util.ArrayList;
import java.sql.ResultSet;

public class PhongBanDAO {

    public static ArrayList<PhongBan> layDanhSachPhongBan() {
        ArrayList<PhongBan> dsPB = new ArrayList<>();
        SQLServerDataProvider provider = new SQLServerDataProvider();
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
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        provider.close();
        return n == 1;
    }

    public static boolean xoaPhongBan(String maPB) {
        String sql = "DELETE FROM \"PhongBan\" WHERE \"MaPHG\" = " + maPB;
        SQLServerDataProvider provider = new SQLServerDataProvider();
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
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int n = provider.executeUpdate(sql);
        provider.close();
        return n == 1;
    }

    public static PhongBan layPhongBan(int maPB) {
        SQLServerDataProvider provider = new SQLServerDataProvider();
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
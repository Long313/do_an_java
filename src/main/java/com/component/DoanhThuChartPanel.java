package com.component;

import com.dao.HoaDonDAO;
import com.dao.SanPhamDAO;
import com.pojo.HoaDon;
import com.pojo.SanPham;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;

public class DoanhThuChartPanel extends JPanel {

    public DoanhThuChartPanel() {
        setLayout(new GridLayout(2, 2, 10, 10));
        setOpaque(false);

        // QUAN TRỌNG
        setPreferredSize(new Dimension(900, 700));

        double[] doanhThu = tinhDoanhThuTheoThang();

        add(createBarChart(doanhThu));
        add(createLineChart(doanhThu));
        add(createPieChart(doanhThu));
        add(createAreaChart(doanhThu));
    }

    // ================== DATA ==================
    private double[] tinhDoanhThuTheoThang() {
        double[] data = new double[12];
        ArrayList<HoaDon> dsHD = HoaDonDAO.layDanhSachHoaDon();

        for (HoaDon hd : dsHD) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(hd.getNgayLap());
            int month = cal.get(Calendar.MONTH);

            double tong = 0;
            for (int i = 0; i < hd.getMaSPList().size(); i++) {
                SanPham sp = SanPhamDAO.laySanPham(hd.getMaSPList().get(i));
                tong += sp.getGiaSP() * hd.getSoLuongList().get(i);
            }
            data[month] += tong;
        }
        return data;
    }

    private String thang(int i) {
        return "T" + (i + 1);
    }

    // ================== CHARTS ==================
    private ChartPanel createBarChart(double[] data) {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        for (int i = 0; i < 12; i++) {
            ds.addValue(data[i], "Doanh thu", thang(i));
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Bar Chart - Doanh thu theo tháng",
                "Tháng", "Doanh thu", ds
        );
        return new ChartPanel(chart);
    }

    private ChartPanel createLineChart(double[] data) {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        for (int i = 0; i < 12; i++) {
            ds.addValue(data[i], "Doanh thu", thang(i));
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Line Chart - Xu hướng doanh thu",
                "Tháng", "Doanh thu", ds
        );
        return new ChartPanel(chart);
    }

    private ChartPanel createPieChart(double[] data) {
        DefaultPieDataset ds = new DefaultPieDataset();
        for (int i = 0; i < 12; i++) {
            ds.setValue(thang(i), data[i]);
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Pie Chart - Tỷ trọng doanh thu",
                ds, true, true, false
        );
        return new ChartPanel(chart);
    }

    private ChartPanel createAreaChart(double[] data) {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        double sum = 0;
        for (int i = 0; i < 12; i++) {
            sum += data[i];
            ds.addValue(sum, "Doanh thu", thang(i));
        }

        JFreeChart chart = ChartFactory.createAreaChart(
                "Area Chart - Doanh thu tích lũy",
                "Tháng", "Doanh thu", ds
        );
        return new ChartPanel(chart);
    }
}

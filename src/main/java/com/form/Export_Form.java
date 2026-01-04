package com.form;

import com.component.Form;
import com.dao.HoaDonDAO;
import com.dao.NhanVienDAO;
import com.dao.SanPhamDAO;
import com.pojo.HoaDon;
import com.pojo.NhanVien;
import com.pojo.SanPham;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Export_Form extends Form {

    private JLabel lblTitle;
    private JButton btnExportHoaDon;
    private JTable tblHoaDon;
    private JScrollPane jScrollPane;

    public Export_Form() {
        initComponents();
        loadData();
    }

    private void initComponents() {

        lblTitle = new JLabel("XUẤT DỮ LIỆU EXCEL", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(Color.WHITE);

        btnExportHoaDon = new JButton("Xuất danh sách Hóa đơn");
        btnExportHoaDon.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnExportHoaDon.setPreferredSize(new Dimension(250, 50));
        btnExportHoaDon.addActionListener(e -> exportHoaDon());

        tblHoaDon = new JTable();
        tblHoaDon.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Mã HD", "Nhân viên", "Ngày lập", "Thành tiền"}
        ));

        jScrollPane = new JScrollPane(tblHoaDon);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                        .addComponent(btnExportHoaDon, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane, GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(20)
                        .addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addGap(30)
                        .addComponent(btnExportHoaDon, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addGap(20)
                        .addComponent(jScrollPane, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                        .addGap(20)
        );
    }

    private void loadData() {
        ArrayList<HoaDon> dsHD = HoaDonDAO.layDanhSachHoaDon();
        DefaultTableModel dtm = (DefaultTableModel) tblHoaDon.getModel();
        dtm.setRowCount(0);

        for (HoaDon hd : dsHD) {
            double tongTien = tinhTongTien(hd);

            Object[] row = {
                    hd.getMaHD(),
                    NhanVienDAO.layNhanVien(hd.getMaNV()) != null
                            ? NhanVienDAO.layNhanVien(hd.getMaNV()).getHoten()
                            : "Unknown",
                    new SimpleDateFormat("yyyy-MM-dd").format(hd.getNgayLap()),
                    tongTien
            };
            dtm.addRow(row);
        }
    }

    private double tinhTongTien(HoaDon hd) {
        double tong = 0;
        ArrayList<Integer> spList = hd.getMaSPList();
        ArrayList<Integer> slList = hd.getSoLuongList();

        for (int i = 0; i < spList.size(); i++) {
            SanPham sp = SanPhamDAO.laySanPham(spList.get(i));
            if (sp != null) {
                tong += sp.getGiaSP() * slList.get(i);
            }
        }
        return tong;
    }

    private void exportHoaDon() {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Lưu file Excel");
            chooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xlsx"));
            chooser.setSelectedFile(new java.io.File("DanhSachHoaDon.xlsx"));

            if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;

            String path = chooser.getSelectedFile().getAbsolutePath();
            if (!path.endsWith(".xlsx")) path += ".xlsx";

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Danh sách Hóa đơn");

            // ===== HEADER STYLE =====
            CellStyle headerStyle = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12);
            headerStyle.setFont(headerFont);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);

            // ===== DATA STYLE =====
            CellStyle dataStyle = workbook.createCellStyle();
            dataStyle.setBorderBottom(BorderStyle.THIN);
            dataStyle.setBorderTop(BorderStyle.THIN);
            dataStyle.setBorderLeft(BorderStyle.THIN);
            dataStyle.setBorderRight(BorderStyle.THIN);

            CellStyle moneyStyle = workbook.createCellStyle();
            moneyStyle.cloneStyleFrom(dataStyle);
            moneyStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0"));

            String[] columns = {"Mã HD", "Nhân viên", "Ngày lập", "Thành tiền"};
            Row headerRow = sheet.createRow(0);

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerStyle);
            }

            int rowNum = 1;
            for (HoaDon hd : HoaDonDAO.layDanhSachHoaDon()) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(hd.getMaHD());
                row.createCell(1).setCellValue(
                        NhanVienDAO.layNhanVien(hd.getMaNV()) != null
                                ? NhanVienDAO.layNhanVien(hd.getMaNV()).getHoten()
                                : "Unknown"
                );
                row.createCell(2).setCellValue(
                        new SimpleDateFormat("yyyy-MM-dd").format(hd.getNgayLap())
                );

                Cell moneyCell = row.createCell(3);
                moneyCell.setCellValue(tinhTongTien(hd));
                moneyCell.setCellStyle(moneyStyle);
            }

            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
                sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 1000);
            }

            try (FileOutputStream fos = new FileOutputStream(path)) {
                workbook.write(fos);
            }
            workbook.close();

            JOptionPane.showMessageDialog(this, "Xuất Excel thành công!");
            Desktop.getDesktop().open(new java.io.File(path));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi xuất Excel: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

package com.component;

import com.dao.SanPhamDAO;
import com.pojo.SanPham;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class DanhSachSanPham extends JPanel {

    public DanhSachSanPham() {
        initComponents();
        this.setOpaque(false);
        loadData();
    }

    // ================= LOAD DATA =================
    private void loadData() {
        ArrayList<SanPham> dsSP = SanPhamDAO.layDanhSachSanPham();
        DefaultTableModel dtm = (DefaultTableModel) tblSanPham.getModel();
        dtm.setRowCount(0);

        for (SanPham sp : dsSP) {
            Vector<Object> v = new Vector<>();
            v.add(sp.getMaSP());
            v.add(sp.getTenSP());
            v.add(sp.getGiaSP());
            v.add(sp.getSoLuong());
            dtm.addRow(v);
        }
    }

    // ================= INIT COMPONENT =================
    private void initComponents() {
        txtMaSP = new JTextField();
        txtTenSP = new JTextField();
        txtGiaSP = new JTextField();
        txtSoLuong = new JTextField();

        btnThem = new JButton("Thêm");
        btnXoa = new JButton("Xóa");
        btnDoc = new JButton("Đọc");
        btnLuu = new JButton("Lưu");

        tblSanPham = new JTable();
        jScrollPane1 = new JScrollPane(tblSanPham);

        lblTitle = new JLabel("DANH SÁCH SẢN PHẨM", SwingConstants.CENTER);
        lblTitle.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);

        tblSanPham.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Mã SP", "Tên SP", "Giá SP", "Số Lượng"}
        ));

        btnThem.addActionListener(e -> themSanPham());
        btnXoa.addActionListener(e -> xoaSanPham());
        btnDoc.addActionListener(e -> docSanPham());
        btnLuu.addActionListener(e -> capNhatSanPham());

        JLabel[] labels = {
            new JLabel("Mã SP"), new JLabel("Tên SP"),
            new JLabel("Giá SP"), new JLabel("Số lượng")
        };
        for (JLabel lb : labels) {
            lb.setForeground(Color.WHITE);
        }

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(lblTitle)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(labels[0])
                                        .addComponent(labels[1])
                                        .addComponent(labels[2])
                                        .addComponent(labels[3]))
                                .addGap(20)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(txtMaSP, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTenSP, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtGiaSP, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtSoLuong, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                )
                        )
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(btnThem)
                                .addGap(20)
                                .addComponent(btnXoa)
                                .addGap(20)
                                .addComponent(btnDoc)
                                .addGap(20)
                                .addComponent(btnLuu)
                        )
                        .addComponent(jScrollPane1)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(lblTitle)
                        .addGap(30)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labels[0])
                                .addComponent(txtMaSP, 30, 30, 30))
                        .addGap(5)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labels[1])
                                .addComponent(txtTenSP, 30, 30, 30))
                        .addGap(5)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labels[2])
                                .addComponent(txtGiaSP, 30, 30, 30))
                        .addGap(5)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labels[3])
                                .addComponent(txtSoLuong, 30, 30, 30))
                        .addGap(30)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnThem)
                                .addComponent(btnXoa)
                                .addComponent(btnDoc)
                                .addComponent(btnLuu)
                        )
                        .addGap(20)
                        .addComponent(jScrollPane1, 200, 200, 200)
        );
    }

    // ================= CRUD =================
    private void themSanPham() {
        try {
            SanPham sp = layDuLieuForm();
            if (SanPhamDAO.themSanPham(sp)) {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
                loadData();
                xoaForm();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void xoaSanPham() {
        if (txtMaSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập Mã SP");
            return;
        }
        try {
            int maSP = Integer.parseInt(txtMaSP.getText());
            if (SanPhamDAO.xoaSanPham(maSP)) {
                JOptionPane.showMessageDialog(this, "Xóa thành công");
                loadData();
                xoaForm();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mã SP phải là số");
        }
    }

    private void docSanPham() {
        if (txtMaSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập Mã SP cần đọc");
            return;
        }
        try {
            int maSP = Integer.parseInt(txtMaSP.getText());
            SanPham sp = SanPhamDAO.laySanPham(maSP);
            if (sp != null) {
                hienThiForm(sp);
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mã SP phải là số");
        }
    }

    private void capNhatSanPham() {
        try {
            if (txtMaSP.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Chưa chọn sản phẩm để cập nhật");
            }

            SanPham sp = layDuLieuForm();
            sp.setMaSP(Integer.parseInt(txtMaSP.getText()));

            if (SanPhamDAO.capNhatSanPham(sp)) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                loadData();
                xoaForm();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ================= HELPER =================
    private SanPham layDuLieuForm() {
        if (txtTenSP.getText().trim().isEmpty() || txtGiaSP.getText().trim().isEmpty() || txtSoLuong.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Vui lòng nhập đầy đủ dữ liệu");
        }

        SanPham sp = new SanPham();
        sp.setTenSP(txtTenSP.getText());

        try {
            sp.setGiaSP(Float.parseFloat(txtGiaSP.getText().trim()));
        } catch (Exception e) {
            throw new IllegalArgumentException("Giá SP phải là số");
        }

        try {
            sp.setSoLuong(Integer.parseInt(txtSoLuong.getText().trim()));
        } catch (Exception e) {
            throw new IllegalArgumentException("Số lượng phải là số nguyên");
        }

        return sp;
    }

    private void hienThiForm(SanPham sp) {
        txtMaSP.setText(String.valueOf(sp.getMaSP()));
        txtTenSP.setText(sp.getTenSP());
        txtGiaSP.setText(String.valueOf(sp.getGiaSP()));
        txtSoLuong.setText(String.valueOf(sp.getSoLuong()));
    }

    private void xoaForm() {
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtGiaSP.setText("");
        txtSoLuong.setText("");
    }

    // ================= VARIABLES =================
    private JTextField txtMaSP, txtTenSP, txtGiaSP, txtSoLuong;
    private JButton btnThem, btnXoa, btnDoc, btnLuu;
    private JTable tblSanPham;
    private JScrollPane jScrollPane1;
    private JLabel lblTitle;
}
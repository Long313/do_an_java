package com.component;

import com.dao.NhanVienDAO;
import com.dao.PhongBanDAO;
import com.pojo.NhanVien;
import com.pojo.PhongBan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Vector;

public class DanhSachNhanVien extends JPanel {

    public DanhSachNhanVien() {
        initComponents();
        this.setOpaque(false);
        loadPhongBan();
        loadData();
    }

    // ================= LOAD DATA =================
    private void loadData() {
        ArrayList<NhanVien> dsNV = NhanVienDAO.layDanhSachNhanVien();
        DefaultTableModel dtm = (DefaultTableModel) tblNhanVien.getModel();
        dtm.setRowCount(0);

        for (NhanVien nv : dsNV) {
            Vector<Object> v = new Vector<>();
            v.add(nv.getMaNV());
            v.add(nv.getHoten());
            v.add(nv.getPhai());
            v.add(nv.getNgaysinh());
            v.add(nv.getDiachi());
            v.add(nv.getLuong());
            v.add(nv.getPhong());
            v.add(nv.isTrangthai());
            dtm.addRow(v);
        }
    }

    private void loadPhongBan() {
        ArrayList<PhongBan> dsPB = PhongBanDAO.layDanhSachPhongBan();
        DefaultComboBoxModel<PhongBan> model = new DefaultComboBoxModel<>();
        for (PhongBan pb : dsPB) {
            model.addElement(pb);
        }
        cboPhong.setModel(model);
    }

    // ================= INIT COMPONENT =================
    private void initComponents() {

        txtMaNV = new JTextField();
        txtHoTen = new JTextField();
        cboPhai = new JComboBox<>(new String[]{"Nam", "Nữ"});
        txtNgaySinh = new JTextField();
        txtDiaChi = new JTextField();
        txtLuong = new JTextField();
        cboPhong = new JComboBox<>();

        chkTrangThai = new JCheckBox("Đang làm");
        chkTrangThai.setForeground(java.awt.Color.WHITE);
        chkTrangThai.setOpaque(false);

        btnThem = new JButton("Thêm");
        btnXoa = new JButton("Xóa");
        btnDoc = new JButton("Đọc");
        btnLuu = new JButton("Lưu");

        tblNhanVien = new JTable();
        jScrollPane1 = new JScrollPane(tblNhanVien);

        lblTitle = new JLabel("DANH SÁCH NHÂN VIÊN", SwingConstants.CENTER);
        lblTitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 26));
        lblTitle.setForeground(java.awt.Color.WHITE);

        tblNhanVien.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Mã NV", "Họ Tên", "Phái", "Ngày Sinh",
                    "Địa Chỉ", "Lương", "Phòng", "Trạng Thái"
                }
        ));

        btnThem.addActionListener(e -> themNhanVien());
        btnXoa.addActionListener(e -> xoaNhanVien());
        btnDoc.addActionListener(e -> docNhanVien());
        btnLuu.addActionListener(e -> capNhatNhanVien());

        JLabel[] labels = {
            new JLabel("Mã NV"), new JLabel("Họ tên"),
            new JLabel("Phái"), new JLabel("Ngày sinh (yyyy-mm-dd)"),
            new JLabel("Địa chỉ"), new JLabel("Lương"),
            new JLabel("Phòng"), new JLabel("Trạng thái")
        };
        for (JLabel lb : labels) {
            lb.setForeground(java.awt.Color.WHITE);
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
                                        .addComponent(labels[3])
                                        .addComponent(labels[4])
                                        .addComponent(labels[5])
                                        .addComponent(labels[6])
                                        .addComponent(labels[7]))
                                .addGap(20)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(txtMaNV)
                                        .addComponent(txtHoTen)
                                        .addComponent(cboPhai)
                                        .addComponent(txtNgaySinh)
                                        .addComponent(txtDiaChi)
                                        .addComponent(txtLuong)
                                        .addComponent(cboPhong)
                                        .addComponent(chkTrangThai)))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(btnThem)
                                .addGap(20)
                                .addComponent(btnXoa)
                                .addGap(20)
                                .addComponent(btnDoc)
                                .addGap(20)
                                .addComponent(btnLuu))
                        .addComponent(jScrollPane1)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(lblTitle)
                        .addGap(10)
                        .addGroup(layout.createParallelGroup().addComponent(labels[0]).addComponent(txtMaNV))
                        .addGroup(layout.createParallelGroup().addComponent(labels[1]).addComponent(txtHoTen))
                        .addGroup(layout.createParallelGroup().addComponent(labels[2]).addComponent(cboPhai))
                        .addGroup(layout.createParallelGroup().addComponent(labels[3]).addComponent(txtNgaySinh))
                        .addGroup(layout.createParallelGroup().addComponent(labels[4]).addComponent(txtDiaChi))
                        .addGroup(layout.createParallelGroup().addComponent(labels[5]).addComponent(txtLuong))
                        .addGroup(layout.createParallelGroup().addComponent(labels[6]).addComponent(cboPhong))
                        .addGroup(layout.createParallelGroup().addComponent(labels[7]).addComponent(chkTrangThai))
                        .addGap(15)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(btnThem)
                                .addComponent(btnXoa)
                                .addComponent(btnDoc)
                                .addComponent(btnLuu))
                        .addGap(10)
                        .addComponent(jScrollPane1, 200, 200, 200)
        );
    }

    // ================= CRUD =================
    private void themNhanVien() {
        try {
            NhanVien nv = layDuLieuForm();
            if (NhanVienDAO.themNhanVien(nv)) {
                JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công");
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi dữ liệu: " + e.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // RẤT QUAN TRỌNG khi debug
        }
    }

    private void xoaNhanVien() {
        if (txtMaNV.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập Mã NV");
            return;
        }
        int maNV = Integer.parseInt(txtMaNV.getText());
        if (NhanVienDAO.xoaNhanVien(maNV)) {
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            loadData();
        }
    }

    private void docNhanVien() {
        if (txtMaNV.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập Mã NV cần đọc");
            return;
        }
        int maNV = Integer.parseInt(txtMaNV.getText());
        NhanVien nv = NhanVienDAO.layNhanVien(maNV);
        if (nv != null) {
            hienThiForm(nv);
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên");
        }
    }

    private void capNhatNhanVien() {
        try {
            if (txtMaNV.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Chưa chọn nhân viên để cập nhật");
            }

            NhanVien nv = layDuLieuForm();
            nv.setMaNV(Integer.parseInt(txtMaNV.getText())); // 🔥 QUAN TRỌNG

            if (NhanVienDAO.capNhatNhanVien(nv)) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                loadData();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ================= HELPER =================
    private NhanVien layDuLieuForm() {

        if (txtHoTen.getText().trim().isEmpty()
                || txtNgaySinh.getText().trim().isEmpty()
                || txtLuong.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Vui lòng nhập đầy đủ dữ liệu");
        }

        PhongBan pb = (PhongBan) cboPhong.getSelectedItem();
        if (pb == null) {
            throw new IllegalArgumentException("Vui lòng chọn phòng ban");
        }

        NhanVien nv = new NhanVien();
        nv.setHoten(txtHoTen.getText());
        nv.setPhai(cboPhai.getSelectedItem().toString());

        // ✅ CHECK FORMAT NGÀY SINH
        try {
            nv.setNgaysinh(java.sql.Date.valueOf(txtNgaySinh.getText().trim()));
        } catch (Exception e) {
            throw new IllegalArgumentException("Ngày sinh phải đúng định dạng yyyy-mm-dd");
        }

        // ✅ CHECK LƯƠNG
        try {
            nv.setLuong(Float.parseFloat(txtLuong.getText().trim()));
        } catch (Exception e) {
            throw new IllegalArgumentException("Lương phải là số");
        }

        nv.setDiachi(txtDiaChi.getText());
        nv.setPhong(pb.getMaPHG());
        nv.setTrangthai(chkTrangThai.isSelected());

        return nv;
    }

    private void hienThiForm(NhanVien nv) {
        txtMaNV.setText(String.valueOf(nv.getMaNV()));
        txtHoTen.setText(nv.getHoten());
        cboPhai.setSelectedItem(nv.getPhai());
        txtNgaySinh.setText(nv.getNgaysinh().toString());
        txtDiaChi.setText(nv.getDiachi());
        txtLuong.setText(String.valueOf(nv.getLuong()));
        chkTrangThai.setSelected(nv.isTrangthai());

        for (int i = 0; i < cboPhong.getItemCount(); i++) {
            if (cboPhong.getItemAt(i).getMaPHG() == nv.getPhong()) {
                cboPhong.setSelectedIndex(i);
                break;
            }
        }
    }

    // ================= VARIABLES =================
    private JTextField txtMaNV, txtHoTen, txtNgaySinh, txtDiaChi, txtLuong;
    private JComboBox<String> cboPhai;
    private JComboBox<PhongBan> cboPhong;
    private JCheckBox chkTrangThai;
    private JButton btnThem, btnXoa, btnDoc, btnLuu;
    private JTable tblNhanVien;
    private JScrollPane jScrollPane1;
    private JLabel lblTitle;
}

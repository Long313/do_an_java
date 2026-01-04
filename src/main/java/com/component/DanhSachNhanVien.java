package com.component;

import com.dao.NhanVienDAO;
import com.dao.PhongBanDAO;
import com.pojo.NhanVien;
import com.pojo.PhongBan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
            v.add(nv.isTrangthai() ? "Đang làm" : "Nghỉ làm");
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
        cboGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ"});
        txtDiaChi = new JTextField();
        txtLuong = new JTextField();
        cboPhong = new JComboBox<>();

        // ===== Custom DatePicker cho ngày sinh =====
        txtNgaySinh = new JTextField();
        txtNgaySinh.setEditable(false);
        btnDatePicker = new JButton("📅");
        btnDatePicker.addActionListener(e -> showDatePickerDialog());
        selectedDate = new Date();
        updateDateDisplay();

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
                    "Mã NV", "Họ Tên", "Giới Tính", "Ngày Sinh",
                    "Địa Chỉ", "Lương", "Phòng", "Trạng Thái"
                }
        ));

        btnThem.addActionListener(e -> themNhanVien());
        btnXoa.addActionListener(e -> xoaNhanVien());
        btnDoc.addActionListener(e -> docNhanVien());
        btnLuu.addActionListener(e -> capNhatNhanVien());

        JLabel[] labels = {
            new JLabel("Mã NV"), new JLabel("Họ tên"),
            new JLabel("Giới tính"), new JLabel("Ngày sinh"),
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
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(txtMaNV, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtHoTen, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cboGioiTinh, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtNgaySinh, 250, 250, 250)
                                                .addGap(5)
                                                .addComponent(btnDatePicker, 45, 45, 45))
                                        .addComponent(txtDiaChi, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtLuong, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cboPhong, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(chkTrangThai))
                        )
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
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labels[0])
                                .addComponent(txtMaNV, 30, 30, 30))
                        .addGap(5)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labels[1])
                                .addComponent(txtHoTen, 30, 30, 30))
                        .addGap(5)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labels[2])
                                .addComponent(cboGioiTinh, 30, 30, 30))
                        .addGap(5)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labels[3])
                                .addComponent(txtNgaySinh, 30, 30, 30)
                                .addComponent(btnDatePicker, 30, 30, 30))
                        .addGap(5)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labels[4])
                                .addComponent(txtDiaChi, 30, 30, 30))
                        .addGap(5)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labels[5])
                                .addComponent(txtLuong, 30, 30, 30))
                        .addGap(5)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labels[6])
                                .addComponent(cboPhong, 30, 30, 30))
                        .addGap(5)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(labels[7])
                                .addComponent(chkTrangThai))
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

    // ================= DATE PICKER DIALOG =================
    private void showDatePickerDialog() {
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Chọn Ngày", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(this);

        Calendar cal = Calendar.getInstance();
        cal.setTime(selectedDate);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Year
        JPanel yearPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        yearPanel.add(new JLabel("Năm:"));
        SpinnerNumberModel yearModel = new SpinnerNumberModel(cal.get(Calendar.YEAR), 1900, 2100, 1);
        JSpinner spinYear = new JSpinner(yearModel);
        yearPanel.add(spinYear);
        panel.add(yearPanel);

        // Month
        JPanel monthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        monthPanel.add(new JLabel("Tháng:"));
        SpinnerNumberModel monthModel = new SpinnerNumberModel(cal.get(Calendar.MONTH) + 1, 1, 12, 1);
        JSpinner spinMonth = new JSpinner(monthModel);
        monthPanel.add(spinMonth);
        panel.add(monthPanel);

        // Day
        JPanel dayPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        dayPanel.add(new JLabel("Ngày:"));
        SpinnerNumberModel dayModel = new SpinnerNumberModel(cal.get(Calendar.DAY_OF_MONTH), 1, 31, 1);
        JSpinner spinDay = new JSpinner(dayModel);
        dayPanel.add(spinDay);
        panel.add(dayPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        JButton okBtn = new JButton("OK");
        JButton cancelBtn = new JButton("Hủy");

        okBtn.addActionListener(e -> {
            try {
                int year = (Integer) spinYear.getValue();
                int month = (Integer) spinMonth.getValue() - 1;
                int day = (Integer) spinDay.getValue();
                cal.set(year, month, day);
                selectedDate = cal.getTime();
                updateDateDisplay();
                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Ngày không hợp lệ");
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(okBtn);
        buttonPanel.add(cancelBtn);
        panel.add(buttonPanel);

        dialog.add(panel);
        dialog.setSize(300, 200);
        dialog.setVisible(true);
    }

    private void updateDateDisplay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        txtNgaySinh.setText(sdf.format(selectedDate));
    }

    // ================= CRUD =================
    private void themNhanVien() {
        try {
            NhanVien nv = layDuLieuForm();
            if (NhanVienDAO.themNhanVien(nv)) {
                JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công");
                loadData();
                xoaForm();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi dữ liệu: " + e.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void xoaNhanVien() {
        if (txtMaNV.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập Mã NV");
            return;
        }
        try {
            int maNV = Integer.parseInt(txtMaNV.getText());
            if (NhanVienDAO.xoaNhanVien(maNV)) {
                JOptionPane.showMessageDialog(this, "Xóa thành công");
                loadData();
                xoaForm();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mã NV phải là số");
        }
    }

    private void docNhanVien() {
        if (txtMaNV.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập Mã NV cần đọc");
            return;
        }
        try {
            int maNV = Integer.parseInt(txtMaNV.getText());
            NhanVien nv = NhanVienDAO.layNhanVien(maNV);
            if (nv != null) {
                hienThiForm(nv);
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mã NV phải là số");
        }
    }

    private void capNhatNhanVien() {
        try {
            if (txtMaNV.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Chưa chọn nhân viên để cập nhật");
            }

            NhanVien nv = layDuLieuForm();
            nv.setMaNV(Integer.parseInt(txtMaNV.getText()));

            if (NhanVienDAO.capNhatNhanVien(nv)) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                loadData();
                xoaForm();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ================= HELPER =================
    private NhanVien layDuLieuForm() {

        if (txtHoTen.getText().trim().isEmpty()
                || txtLuong.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Vui lòng nhập đầy đủ dữ liệu");
        }

        if (txtNgaySinh.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Vui lòng chọn ngày sinh");
        }

        PhongBan pb = (PhongBan) cboPhong.getSelectedItem();
        if (pb == null) {
            throw new IllegalArgumentException("Vui lòng chọn phòng ban");
        }

        NhanVien nv = new NhanVien();
        nv.setHoten(txtHoTen.getText());
        nv.setPhai(cboGioiTinh.getSelectedItem().toString());
        nv.setNgaysinh(new java.sql.Date(selectedDate.getTime()));

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
        cboGioiTinh.setSelectedItem(nv.getPhai());
        selectedDate = nv.getNgaysinh();
        updateDateDisplay();
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

    private void xoaForm() {
        txtMaNV.setText("");
        txtHoTen.setText("");
        txtDiaChi.setText("");
        txtLuong.setText("");
        cboGioiTinh.setSelectedIndex(0);
        selectedDate = new Date();
        updateDateDisplay();
        chkTrangThai.setSelected(false);
        if (cboPhong.getItemCount() > 0) {
            cboPhong.setSelectedIndex(0);
        }
    }

    // ================= VARIABLES =================
    private JTextField txtMaNV, txtHoTen, txtDiaChi, txtLuong, txtNgaySinh;
    private JButton btnDatePicker;
    private Date selectedDate;
    private JComboBox<String> cboGioiTinh;
    private JComboBox<PhongBan> cboPhong;
    private JCheckBox chkTrangThai;
    private JButton btnThem, btnXoa, btnDoc, btnLuu;
    private JTable tblNhanVien;
    private JScrollPane jScrollPane1;
    private JLabel lblTitle;
}
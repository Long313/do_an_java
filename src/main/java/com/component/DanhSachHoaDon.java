package com.component;

import com.dao.HoaDonDAO;
import com.dao.NhanVienDAO;
import com.dao.SanPhamDAO;
import com.pojo.HoaDon;
import com.pojo.NhanVien;
import com.pojo.SanPham;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DanhSachHoaDon extends JPanel {

    public DanhSachHoaDon() {
        initComponents();
        this.setOpaque(false);
        loadNhanVien();
        loadSanPham();
        loadData();
    }

    // ================= LOAD DATA =================
    private void loadData() {
        ArrayList<HoaDon> dsHD = HoaDonDAO.layDanhSachHoaDon();
        DefaultTableModel dtm = (DefaultTableModel) tblHoaDon.getModel();
        dtm.setRowCount(0);

        for (HoaDon hd : dsHD) {
            double tongTien = 0;
            ArrayList<Integer> spList = hd.getMaSPList();
            ArrayList<Integer> slList = hd.getSoLuongList();
            for (int i = 0; i < spList.size(); i++) {
                SanPham sp = SanPhamDAO.laySanPham(spList.get(i));
                tongTien += sp.getGiaSP() * slList.get(i);
            }

            Object[] row = new Object[]{
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

    private void loadNhanVien() {
        ArrayList<NhanVien> dsNV = NhanVienDAO.layDanhSachNhanVien();
        DefaultComboBoxModel<NhanVien> modelNV = new DefaultComboBoxModel<>();
        for (NhanVien nv : dsNV) modelNV.addElement(nv);
        cboNhanVien.setModel(modelNV);

        cboNhanVien.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof NhanVien nv) setText(nv.getHoten());
                return this;
            }
        });
    }

    private void loadSanPham() {
        ArrayList<SanPham> dsSP = SanPhamDAO.layDanhSachSanPham();
        DefaultComboBoxModel<SanPham> modelSP = new DefaultComboBoxModel<>();
        for (SanPham sp : dsSP) modelSP.addElement(sp);
        this.modelSP = modelSP;

        TableColumn colSP = tblChiTiet.getColumnModel().getColumn(0);
        JComboBox<SanPham> cbo = new JComboBox<>(modelSP);
        colSP.setCellEditor(new DefaultCellEditor(cbo));

        colSP.setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (value instanceof SanPham sp) setText(sp.getTenSP());
                else setText("");
                return this;
            }
        });
    }

    // ================= INIT COMPONENT =================
    private void initComponents() {
        txtMaHD = new JTextField();
        cboNhanVien = new JComboBox<>();
        txtNgayLap = new JTextField();
        txtNgayLap.setEditable(false);

        lblTitle = new JLabel("DANH SÁCH HÓA ĐƠN", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);

        lblMaHD = new JLabel("Mã HD"); lblMaHD.setForeground(Color.WHITE);
        lblNhanVien = new JLabel("Nhân viên"); lblNhanVien.setForeground(Color.WHITE);
        lblNgayLap = new JLabel("Ngày lập"); lblNgayLap.setForeground(Color.WHITE);

        btnDatePicker = new JButton("📅");
        btnDatePicker.addActionListener(e -> showDatePickerDialog());
        ngayLapSelected = new Date();
        updateDateDisplay();

        btnThem = new JButton("Thêm");
        btnXoa = new JButton("Xóa");
        btnDoc = new JButton("Đọc");
        btnLuu = new JButton("Lưu");
        btnThemSP = new JButton("Thêm sản phẩm");

        tblHoaDon = new JTable(new DefaultTableModel(
                new Object[]{"Mã HD", "Nhân viên", "Ngày lập", "Thành tiền"}, 0
        ));
        jScrollPaneHD = new JScrollPane(tblHoaDon);

        tblChiTiet = new JTable(new DefaultTableModel(
                new Object[]{"Sản phẩm", "Số lượng", "Thành tiền"}, 0
        ));
        JScrollPane jScrollChiTiet = new JScrollPane(tblChiTiet);

        // Layout
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addComponent(lblTitle)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(lblMaHD)
                                .addComponent(lblNhanVien)
                                .addComponent(lblNgayLap)
                        )
                        .addGap(10)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(txtMaHD, 200, 200, 200)
                                .addComponent(cboNhanVien, 200, 200, 200)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtNgayLap, 150, 150, 150)
                                        .addComponent(btnDatePicker, 50, 50, 50)
                                )
                        )
                )
                .addGroup(layout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addGap(10)
                        .addComponent(btnXoa)
                        .addGap(10)
                        .addComponent(btnDoc)
                        .addGap(10)
                        .addComponent(btnLuu)
                        .addGap(10)
                        .addComponent(btnThemSP)
                )
                .addComponent(jScrollPaneHD, 400, 400, 400)
                .addComponent(jScrollChiTiet, 400, 400, 400)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(lblTitle)
                .addGap(5)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMaHD)
                        .addComponent(txtMaHD, 30, 30, 30)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNhanVien)
                        .addComponent(cboNhanVien, 30, 30, 30)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNgayLap)
                        .addComponent(txtNgayLap, 30, 30, 30)
                        .addComponent(btnDatePicker)
                )
                .addGap(5)
                .addGroup(layout.createParallelGroup()
                        .addComponent(btnThem)
                        .addComponent(btnXoa)
                        .addComponent(btnDoc)
                        .addComponent(btnLuu)
                        .addComponent(btnThemSP)
                )
                .addGap(5)
                .addComponent(jScrollPaneHD, 150, 150, 150)
                .addGap(5)
                .addComponent(jScrollChiTiet, 150, 150, 150)
        );

        // Actions
        btnThem.addActionListener(e -> themHoaDon());
        btnXoa.addActionListener(e -> xoaHoaDon());
        btnDoc.addActionListener(e -> docHoaDon());
        btnLuu.addActionListener(e -> capNhatHoaDon());
        btnThemSP.addActionListener(e -> {
            DefaultTableModel dtm = (DefaultTableModel) tblChiTiet.getModel();
            dtm.addRow(new Object[]{null, 1, 0});
        });

        tblHoaDon.getSelectionModel().addListSelectionListener(e -> {
            int row = tblHoaDon.getSelectedRow();
            if (row >= 0) docHoaDon();
        });

        // Tính thành tiền khi thay đổi số lượng
        tblChiTiet.getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE && e.getColumn() == 1) {
                int row = e.getFirstRow();
                DefaultTableModel dtm = (DefaultTableModel) tblChiTiet.getModel();
                Object spObj = dtm.getValueAt(row, 0);
                Object slObj = dtm.getValueAt(row, 1);
                if (spObj instanceof SanPham sp && slObj != null) {
                    try {
                        int soLuong = Integer.parseInt(slObj.toString());
                        double thanhTien = sp.getGiaSP() * soLuong;
                        dtm.setValueAt(thanhTien, row, 2);
                    } catch (NumberFormatException ex) {
                        dtm.setValueAt(0, row, 2);
                    }
                }
            }
        });
    }

    private void showDatePickerDialog() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(ngayLapSelected);

        JSpinner spinYear = new JSpinner(new SpinnerNumberModel(cal.get(Calendar.YEAR), 1900, 2100, 1));
        JSpinner spinMonth = new JSpinner(new SpinnerNumberModel(cal.get(Calendar.MONTH) + 1, 1, 12, 1));
        JSpinner spinDay = new JSpinner(new SpinnerNumberModel(cal.get(Calendar.DAY_OF_MONTH), 1, 31, 1));

        JPanel panel = new JPanel();
        panel.add(new JLabel("Năm"));
        panel.add(spinYear);
        panel.add(new JLabel("Tháng"));
        panel.add(spinMonth);
        panel.add(new JLabel("Ngày"));
        panel.add(spinDay);

        int result = JOptionPane.showConfirmDialog(this, panel, "Chọn ngày", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int year = (Integer) spinYear.getValue();
            int month = (Integer) spinMonth.getValue() - 1;
            int day = (Integer) spinDay.getValue();
            cal.set(year, month, day);
            ngayLapSelected = cal.getTime();
            updateDateDisplay();
        }
    }

    private void updateDateDisplay() {
        txtNgayLap.setText(new SimpleDateFormat("yyyy-MM-dd").format(ngayLapSelected));
    }

    // ================= VARIABLES =================
    private JTextField txtMaHD, txtNgayLap;
    private JComboBox<NhanVien> cboNhanVien;
    private JTable tblHoaDon, tblChiTiet;
    private JScrollPane jScrollPaneHD;
    private JButton btnDatePicker, btnThem, btnXoa, btnDoc, btnLuu, btnThemSP;
    private JLabel lblTitle, lblMaHD, lblNhanVien, lblNgayLap;
    private Date ngayLapSelected;
    private DefaultComboBoxModel<SanPham> modelSP;

    // ================= HELPER + CRUD =================
    private HoaDon layDuLieuForm() {
        HoaDon hd = new HoaDon();
        if (!txtMaHD.getText().trim().isEmpty()) hd.setMaHD(Integer.parseInt(txtMaHD.getText().trim()));

        NhanVien nv = (NhanVien) cboNhanVien.getSelectedItem();
        if (nv == null) throw new IllegalArgumentException("Chọn nhân viên");
        hd.setMaNV(nv.getMaNV());
        hd.setNgayLap(ngayLapSelected);

        ArrayList<Integer> spList = new ArrayList<>();
        ArrayList<Integer> slList = new ArrayList<>();
        DefaultTableModel dtm = (DefaultTableModel) tblChiTiet.getModel();
        for (int i = 0; i < dtm.getRowCount(); i++) {
            Object spObj = dtm.getValueAt(i, 0);
            Object slObj = dtm.getValueAt(i, 1);
            if (spObj instanceof SanPham sp && slObj != null) {
                spList.add(sp.getMaSP());
                slList.add(Integer.parseInt(slObj.toString()));
            }
        }

        if (spList.isEmpty()) throw new IllegalArgumentException("Hóa đơn phải có ít nhất 1 sản phẩm");
        hd.setMaSPList(spList);
        hd.setSoLuongList(slList);

        return hd;
    }

    private void xoaForm() {
        txtMaHD.setText("");
        cboNhanVien.setSelectedIndex(0);
        tblChiTiet.setModel(new DefaultTableModel(new Object[]{"Sản phẩm", "Số lượng", "Thành tiền"}, 0));
        ngayLapSelected = new Date();
        updateDateDisplay();
    }

    private void themHoaDon() {
        try {
            HoaDon hd = layDuLieuForm();
            if (HoaDonDAO.themHoaDon(hd)) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                loadData();
                xoaForm();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void xoaHoaDon() {
        int row = tblHoaDon.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn hóa đơn để xóa");
            return;
        }
        int maHD = (int) tblHoaDon.getValueAt(row, 0);
        if (HoaDonDAO.xoaHoaDon(maHD)) {
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            loadData();
            xoaForm();
        } else {
            JOptionPane.showMessageDialog(this, "Xóa thất bại");
        }
    }

    private void docHoaDon() {
        HoaDon hd = null;

        int row = tblHoaDon.getSelectedRow();
        if (row >= 0) hd = HoaDonDAO.layHoaDon((int) tblHoaDon.getValueAt(row, 0));
        else if (!txtMaHD.getText().trim().isEmpty()) {
            try {
                int maHD = Integer.parseInt(txtMaHD.getText().trim());
                hd = HoaDonDAO.layHoaDon(maHD);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Mã HD không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Chọn hóa đơn hoặc nhập Mã HD để đọc", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (hd == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        txtMaHD.setText(String.valueOf(hd.getMaHD()));
        NhanVien nv = NhanVienDAO.layNhanVien(hd.getMaNV());
        cboNhanVien.setSelectedItem(nv);
        ngayLapSelected = hd.getNgayLap();
        updateDateDisplay();

        DefaultTableModel dtm = (DefaultTableModel) tblChiTiet.getModel();
        dtm.setRowCount(0);
        ArrayList<Integer> spList = hd.getMaSPList();
        ArrayList<Integer> slList = hd.getSoLuongList();

        for (int i = 0; i < spList.size(); i++) {
            SanPham sp = SanPhamDAO.laySanPham(spList.get(i));
            int soLuong = slList.get(i);
            double thanhTien = sp.getGiaSP() * soLuong;
            dtm.addRow(new Object[]{sp, soLuong, thanhTien});
        }
    }

    private void capNhatHoaDon() {
        try {
            HoaDon hd = layDuLieuForm();
            if (HoaDonDAO.capNhatHoaDon(hd)) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                loadData();
                xoaForm();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}

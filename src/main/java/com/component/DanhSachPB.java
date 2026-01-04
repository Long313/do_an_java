package com.component;

import com.dao.PhongBanDAO;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.pojo.PhongBan;

public class DanhSachPB extends javax.swing.JPanel {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DanhSachPB.class.getName());

    public DanhSachPB() {
        initComponents();
        this.setOpaque(false);
        loadData();
    }

    private void loadData() {
        ArrayList<PhongBan> dsPB = PhongBanDAO.layDanhSachPhongBan();
        DefaultTableModel dtm = (DefaultTableModel) tblPhongBan.getModel();
        dtm.setRowCount(0);
        for (PhongBan pb : dsPB) {
            Vector<Object> vec = new Vector<>();
            vec.add(pb.getMaPHG());
            vec.add(pb.getTenPHG());
            dtm.addRow(vec);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        txtTenPB = new javax.swing.JTextField();
        txtMaPB = new javax.swing.JTextField();
        btnThemPhong = new javax.swing.JButton();
        btnDoc = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnXoaPhong = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhongBan = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        txtTenPB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenPBActionPerformed(evt);
            }
        });

        txtMaPB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaPBActionPerformed(evt);
            }
        });

        btnThemPhong.setText("Thêm phòng");
        btnThemPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemPhongActionPerformed(evt);
            }
        });

        btnXoaPhong.setText("Xóa phòng");
        btnXoaPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnDoc.setText("Đọc");
        btnDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocActionPerformed(evt);
            }
        });

        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        tblPhongBan.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null},
                    {null, null},
                    {null, null},
                    {null, null}
                },
                new String[]{
                    "Mã Phòng", "Tên Phòng"
                }
        ));
        jScrollPane1.setViewportView(tblPhongBan);

        jLabel1.setText("Mã Phòng");
        jLabel1.setForeground(java.awt.Color.WHITE);

        jLabel2.setText("Tên Phòng");
        jLabel2.setForeground(java.awt.Color.WHITE);

        jLabel3.setText("DANH SÁCH BỘ PHẬN");
        jLabel3.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 28));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setForeground(java.awt.Color.WHITE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        // TIÊU ĐỀ
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        // MÃ PHÒNG
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(25)
                                .addComponent(txtMaPB, 336, 336, 336))
                        // TÊN PHÒNG
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(25)
                                .addComponent(txtTenPB, 336, 336, 336))
                        // BUTTON
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(btnThemPhong)
                                .addGap(20)
                                .addComponent(btnXoaPhong)
                                .addGap(20)
                                .addComponent(btnDoc)
                                .addGap(20)
                                .addComponent(btnLuu))
                        // TABLE
                        .addComponent(jScrollPane1)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMaPB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                        .addGap(10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTenPB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                        .addGap(40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnThemPhong)
                                .addComponent(btnXoaPhong)
                                .addComponent(btnDoc)
                                .addComponent(btnLuu))
                        .addGap(30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap()
        );

    }// </editor-fold>                        

    private void txtTenPBActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void txtMaPBActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void btnThemPhongActionPerformed(java.awt.event.ActionEvent evt) {
        String tenPB = txtTenPB.getText();
        PhongBan pb = new PhongBan();
        pb.setTenPHG(tenPB);

        if (PhongBanDAO.themPhongBan(pb)) {
            JOptionPane.showMessageDialog(this, "Thêm phòng ban thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadData();
        }
    }

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {
        String strMaPB = txtMaPB.getText().trim();

        if (strMaPB.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng nhập mã phòng ban cần xóa",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            txtMaPB.requestFocus();
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc chắn muốn xóa phòng ban này?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        if (PhongBanDAO.xoaPhongBan(strMaPB)) {
            JOptionPane.showMessageDialog(this,
                    "Xóa phòng ban thành công!",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
            loadData();
            txtMaPB.setText("");
            txtTenPB.setText("");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Xóa thất bại!",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnDocActionPerformed(java.awt.event.ActionEvent evt) {
        String strMaPB = txtMaPB.getText().trim();
        if (strMaPB.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng nhập mã phòng ban",
                    "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            txtMaPB.requestFocus();
            return;
        }

        int maPB;
        try {
            maPB = Integer.parseInt(strMaPB);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Mã phòng ban phải là số",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            txtMaPB.requestFocus();
            return;
        }

        PhongBan pb = PhongBanDAO.layPhongBan(maPB);

        if (pb == null) {
            JOptionPane.showMessageDialog(this,
                    "Không tìm thấy phòng ban có mã: " + maPB,
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
            txtTenPB.setText("");
        } else {
            txtTenPB.setText(pb.getTenPHG());
        }
    }

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {
        int maPB = Integer.parseInt(txtMaPB.getText());
        String tenPB = txtTenPB.getText();
        PhongBan pb = new PhongBan(maPB, tenPB);
        if (PhongBanDAO.capNhatPhongBan(pb)) {
            JOptionPane.showMessageDialog(this, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadData();
        }
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnDoc;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnThemPhong;
    private javax.swing.JButton btnXoaPhong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPhongBan;
    private javax.swing.JTextField txtMaPB;
    private javax.swing.JTextField txtTenPB;
    // End of variables declaration                   
}
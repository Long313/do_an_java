package com.form;

import com.component.DanhSachNhanVien;
import com.component.Form;
import com.component.DanhSachPB;
import com.component.DanhSachSanPham;
import java.awt.BorderLayout;

public class Product_Form extends Form {

    public Product_Form() {
        initComponents();
        initDanhSachSP();
        this.setOpaque(false);
    }

    private void initDanhSachSP() {
        this.setLayout(new BorderLayout());
        DanhSachSanPham spPanel = new DanhSachSanPham();
        this.add(spPanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 753, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

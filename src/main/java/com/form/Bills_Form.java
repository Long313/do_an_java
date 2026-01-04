package com.form;

import com.component.DanhSachHoaDon;
import com.component.Form;
import java.awt.BorderLayout;

public class Bills_Form extends Form {

    public Bills_Form() {
        initComponents();
        initDanhSachHD();
        this.setOpaque(false);
    }

    private void initDanhSachHD() {
        this.setLayout(new BorderLayout());
        DanhSachHoaDon spPanel = new DanhSachHoaDon();
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

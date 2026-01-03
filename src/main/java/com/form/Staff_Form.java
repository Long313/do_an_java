package com.form;

import com.component.DanhSachNhanVien;
import com.component.Form;
import com.component.DanhSachPB;
import java.awt.BorderLayout;

public class Staff_Form extends Form {

     public Staff_Form() {
        initComponents();
        initDanhSachNV();
        this.setOpaque(false);
    }

    private void initDanhSachNV() {
        this.setLayout(new BorderLayout());
        DanhSachNhanVien nvPanel = new DanhSachNhanVien();
        this.add(nvPanel, BorderLayout.CENTER);
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

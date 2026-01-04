package com.form;

import com.component.DoanhThuChartPanel;
import com.component.Form;
import java.awt.BorderLayout;
//import com.component.DanhSachPB;
//import java.awt.BorderLayout;

public class Home_Form extends Form {

    public Home_Form() {
        initComponents();
        initChart();
    }

    private void initChart() {
        // Set layout cho form
        this.setLayout(new BorderLayout());

        // Tạo panel biểu đồ
        DoanhThuChartPanel chartPanel = new DoanhThuChartPanel();

        // Thêm vào form
        this.add(chartPanel, BorderLayout.CENTER);

        // Refresh
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

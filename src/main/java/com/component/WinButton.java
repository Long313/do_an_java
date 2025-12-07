package com.component;

import com.swing.PanelBackground;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;


public class WinButton extends javax.swing.JPanel {


   private final JButton btnMinimize = new JButton("_");
    private final JButton btnClose = new JButton("X");

    public WinButton() {
        setOpaque(false);
        add(btnMinimize);
        add(btnClose);
    }

    public void initEvent(JFrame frame, PanelBackground panel) {
        btnMinimize.addActionListener((ActionEvent e) -> frame.setState(JFrame.ICONIFIED));
        btnClose.addActionListener((ActionEvent e) -> System.exit(0));
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

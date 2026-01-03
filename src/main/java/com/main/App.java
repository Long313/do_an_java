package com.main;

import com.form.Login_Form;

public class App {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new Login_Form().setVisible(true);
        });
    }
}

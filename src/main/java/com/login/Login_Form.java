package com.form;

import com.main.Main;
import javax.swing.*;
import java.awt.*;

public class Login_Form extends JFrame {

    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin;

    public Login_Form() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        // ===== Frame =====
        setTitle("Login");
        setSize(500, 320);                     // màn hình lớn hơn
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== Font =====
        Font fontLabel  = new Font("Segoe UI", Font.PLAIN, 16);
        Font fontField  = new Font("Segoe UI", Font.PLAIN, 18);
        Font fontButton = new Font("Segoe UI", Font.BOLD, 16);

        // ===== Panel =====
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ===== Components =====
        JLabel lblUser = new JLabel("Username");
        lblUser.setFont(fontLabel);

        JLabel lblPass = new JLabel("Password");
        lblPass.setFont(fontLabel);

        txtUser = new JTextField();
        txtUser.setFont(fontField);
        txtUser.setPreferredSize(new Dimension(260, 42)); // 🔥 field to
        txtUser.setMargin(new Insets(5, 10, 5, 10));

        txtPass = new JPasswordField();
        txtPass.setFont(fontField);
        txtPass.setPreferredSize(new Dimension(260, 42)); // 🔥 field to
        txtPass.setMargin(new Insets(5, 10, 5, 10));

        btnLogin = new JButton("LOGIN");
        btnLogin.setFont(fontButton);
        btnLogin.setPreferredSize(new Dimension(160, 44));
        btnLogin.addActionListener(e -> login());

        // Enter = Login
        getRootPane().setDefaultButton(btnLogin);

        // ===== Layout =====
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelForm.add(lblUser, gbc);

        gbc.gridx = 1;
        panelForm.add(txtUser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelForm.add(lblPass, gbc);

        gbc.gridx = 1;
        panelForm.add(txtPass, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelForm.add(btnLogin, gbc);

        add(panelForm, BorderLayout.CENTER);
    }

    private void login() {
        String username = txtUser.getText();
        String password = new String(txtPass.getPassword());

        // ===== Demo login =====
        if ("admin".equals(username) && "123".equals(password)) {
            new Main().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Sai tài khoản hoặc mật khẩu",
                "Login Failed",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}

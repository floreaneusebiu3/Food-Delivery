package PresentationLayer;

import DataLayer.DeliveryService;
import DataLayer.Serialization;
import DataLayer.User;
import DataLayer.UserRole;
import com.sun.deploy.uitoolkit.impl.fx.ui.FXMessageDialog;
import sun.awt.FontDescriptor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginView {
    private JFrame loginFrame = new JFrame("Login");
    ;
    private JLabel titleLabelLogin;
    private JLabel titleLabelRegister;
    private JLabel usernameLabel;
    private JLabel photoLabel;
    private JLabel passwordLabel;
    private JButton loginButon;
    private JButton registerButton;
    private JButton registerButtonNow;
    private JTextField usernameTextFieldLogin;
    private JTextField usernameTextField;
    private JPasswordField passwordTextFieldLogin;
    private JPasswordField passwordTextField;
    private JTextField role ;
    JLabel roleLabel;
    private JButton backButton;


    public LoginView() {
        titleLabelLogin = new JLabel("LOGIN");
        photoLabel = new JLabel();
        usernameLabel = new JLabel("USERNAME:");
        passwordLabel = new JLabel("PASSWORD:");
        usernameTextFieldLogin = new JTextField();
        passwordTextFieldLogin = new JPasswordField();
        loginButon = new JButton("Login");
        registerButton = new JButton("Register");
        titleLabelRegister = new JLabel("REGISTER");
        usernameLabel = new JLabel("USERNAME:");
        passwordLabel = new JLabel("PASSWORD:");
        usernameTextField = new JTextField();     roleLabel = new JLabel("       ROLE:");
        passwordTextField = new JPasswordField();
        role = new JTextField();
        registerButtonNow = new JButton();
        registerButtonNow.setText("REGISTER CLIENT");
        backButton = new JButton("<-");
        paintLoginView();
    }


    public void paintLoginView() {
        LoginView this1 = this;

        loginFrame.setLayout(null);
        loginFrame.getContentPane().setBackground(Color.ORANGE);
        loginFrame.setBounds(600, 20, 700, 800);


        titleLabelLogin.setBounds(30, 50, 300, 300);
        titleLabelLogin.setFont(new Font("Verdana", Font.BOLD, 60));
        titleLabelLogin.setForeground(Color.black);


        photoLabel.setBounds(300, 50, 300, 250);
        ImageIcon icon = new ImageIcon("restaurant.png");
        photoLabel.setIcon(icon);



        usernameLabel.setBounds(50, 380, 180, 80);
        usernameLabel.setFont(new Font("Verdana", Font.BOLD, 25));
        usernameLabel.setForeground(Color.black);


        passwordLabel.setBounds(50, 430, 200, 80);
        passwordLabel.setFont(new Font("Verdana", Font.BOLD, 25));
        passwordLabel.setForeground(Color.black);


        usernameTextFieldLogin.setBounds(230, 400, 300, 40);
        usernameTextFieldLogin.setFont(new Font("Verdana", Font.TYPE1_FONT, 25));
        usernameTextFieldLogin.setForeground(Color.black);


        passwordTextFieldLogin.setBounds(230, 450, 300, 40);
        passwordTextFieldLogin.setFont(new Font("Verdana", Font.TYPE1_FONT, 25));
        passwordTextFieldLogin.setForeground(Color.black);

        loginButon.setBounds(120, 550, 150, 50);
        loginButon.setFont(new Font("Verdana", Font.BOLD, 25));
        loginButon.setForeground(Color.black);

        registerButton.setBounds(300, 550, 200, 50);
        registerButton.setFont(new Font("Verdana", Font.BOLD, 25));
        registerButton.setForeground(Color.black);

        //add buttons
        loginFrame.add(titleLabelLogin);
        loginFrame.add(photoLabel);
        loginFrame.add(usernameLabel);
        loginFrame.add(passwordLabel);
        loginFrame.add(passwordTextFieldLogin);
        loginFrame.add(usernameTextFieldLogin);
        loginFrame.add(loginButon);
        loginFrame.add(registerButton);
        loginFrame.setVisible(true);
    }


    public void paintRegister() {
        loginFrame.getContentPane().removeAll();
        loginFrame.pack();
        loginFrame.getContentPane().setBackground(Color.ORANGE);
        loginFrame.setBounds(600, 50, 470, 450);
        loginFrame.setLayout(null);

        titleLabelRegister.setBounds(150, 5, 200, 150);
        titleLabelRegister.setFont(new Font("Verdana", Font.BOLD, 30));
        titleLabelRegister.setForeground(Color.black);

        usernameLabel.setBounds(10, 150, 180, 40);
        usernameLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        usernameLabel.setForeground(Color.black);
        passwordLabel.setBounds(10, 210, 200, 40);
        passwordLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        passwordLabel.setForeground(Color.black);


        roleLabel.setForeground(Color.black);
        roleLabel.setBounds(30, 260, 150, 40);
        roleLabel.setFont(new Font("Verdana", Font.BOLD, 20));



        usernameTextField.setBounds(170, 150, 270, 40);
        usernameTextField.setFont(new Font("Verdana", Font.TYPE1_FONT, 20));
        usernameTextField.setForeground(Color.black);

        passwordTextField.setBounds(170, 210, 270, 40);
        passwordTextField.setFont(new Font("Verdana", Font.TYPE1_FONT, 20));
        passwordTextField.setForeground(Color.black);


        role.setBounds(170, 260, 270, 40);
        role.setFont(new Font("Verdana", Font.TYPE1_FONT, 20));
        role.setForeground(Color.black);


        registerButtonNow.setForeground(Color.black);
        registerButtonNow.setBounds(100, 330, 300, 40);
        registerButtonNow.setFont(new Font("Verdana", Font.BOLD, 20));
        registerButtonNow.setForeground(Color.black);

        backButton.setBounds(5, 330, 80, 40);
        backButton.setForeground(Color.BLACK);
        backButton.setFont(new Font("Verdana", Font.BOLD, 15));

        loginFrame.add(role);
        loginFrame.add(roleLabel);
        loginFrame.add(backButton);
        loginFrame.add(registerButtonNow);
        loginFrame.add(usernameLabel);
        loginFrame.add(usernameTextField);
        loginFrame.add(passwordLabel);
        loginFrame.add(passwordTextField);
        loginFrame.add(titleLabelRegister);
        loginFrame.setVisible(true);


    }

    public JFrame getLoginFrame() {
        return loginFrame;
    }

    public void setLoginFrame(JFrame loginFrame) {
        this.loginFrame = loginFrame;
    }

    public JLabel getUsernameLabel() {
        return usernameLabel;
    }

    public void setUsernameLabel(JLabel usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    public JLabel getPhotoLabel() {
        return photoLabel;
    }

    public void setPhotoLabel(JLabel photoLabel) {
        this.photoLabel = photoLabel;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public void setPasswordLabel(JLabel passwordLabel) {
        this.passwordLabel = passwordLabel;
    }

    public JButton getLoginButon() {
        return loginButon;
    }

    public void setLoginButon(JButton loginButon) {
        this.loginButon = loginButon;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public void setRegisterButton(JButton registerButton) {
        this.registerButton = registerButton;
    }

    public JTextField getUsernameTextFieldLogin() {
        return usernameTextFieldLogin;
    }

    public void setUsernameTextFieldLogin(JTextField usernameTextFieldLogin) {
        this.usernameTextFieldLogin = usernameTextFieldLogin;
    }

    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    public void setUsernameTextField(JTextField usernameTextField) {
        this.usernameTextField = usernameTextField;
    }

    public JPasswordField getPasswordTextFieldLogin() {
        return passwordTextFieldLogin;
    }

    public void setPasswordTextFieldLogin(JPasswordField passwordTextFieldLogin) {
        this.passwordTextFieldLogin = passwordTextFieldLogin;
    }

    public JPasswordField getPasswordTextField() {
        return passwordTextField;
    }

    public void setPasswordTextField(JPasswordField passwordTextField) {
        this.passwordTextField = passwordTextField;
    }

    public JButton getRegisterButtonNow() {
        return registerButtonNow;
    }

    public void setRegisterButtonNow(JButton registerButtonNow) {
        this.registerButtonNow = registerButtonNow;
    }

    public JTextField getRole() {
        return role;
    }

    public void setRole(JTextField role) {
        this.role = role;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }
}

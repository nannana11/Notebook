package Notebook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SigninFrame extends JFrame {

    JPanel root =new JPanel();
    JLabel welcomeText =new JLabel("欢迎创建新账号！");
    JTextField userNameText=new JTextField(90);
    JTextField passwordText= new JTextField(90);
    JTextField againPasswordText= new JTextField(90);
    JCheckBox loginAgreementCheck =new JCheckBox("同意用户使用协定");
    JButton signinButton=new JButton("注册");

    private Database database1;

    public SigninFrame(String title,Database database)
    {
        super(title);
        this.database1=database;

        this.setSize(400,300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        LoginLayout loginLayout=new LoginLayout();

        //添加
        this.add(root);
        root.setLayout(loginLayout);
        root.add(welcomeText);
        root.add(userNameText);
        root.add(passwordText);
        root.add(againPasswordText);
        root.add(loginAgreementCheck);
        root.add(signinButton);

        //设置文字
        userNameText.setText("请输入用户名");
        passwordText.setText("请输入密码");
        againPasswordText.setText("请再次确认你的密码");

        //设置按钮
        loginAgreementCheck.setSelected(false);
        signinButton.setEnabled(false);

        //监听器
        ActionListener agreementListener=new AgreementActionListener();
        loginAgreementCheck.addActionListener(agreementListener);
        ActionListener signinListener=new SigninActionListener();
        signinButton.addActionListener(signinListener);

        this.setVisible(true);
    }

    private class AgreementActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(loginAgreementCheck.isSelected()){
                signinButton.setEnabled(true);
            }
        }
    }

    public class SigninActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String newUserName=userNameText.getText();
            String newPassword=passwordText.getText();
            String newPasswordCheck=againPasswordText.getText();
            if(newPassword.equals(newPasswordCheck)){
                database1.addUser(newUserName,newPassword);
                SigninFrame.this.dispose();
            }else {
                new WrongFrame("两次密码不相同！","两次密码不相同！");
            }
        }
    }

    private class LoginLayout extends LayoutAdaptor
    {
        @Override
        public void addLayoutComponent(Component comp, Object constraints) {

        }

        @Override
        public void removeLayoutComponent(Component comp) {

        }

        @Override
        public void layoutContainer(Container parent) {
            int widthWindow=getWidth();
            int heightWindow=getHeight();
            welcomeText.setBounds((int)(65.0/150.0*heightWindow),(int)(10.0/100.0*heightWindow),(int)(10.0/15.0*widthWindow),(int)(10.0/100.0*heightWindow));
            userNameText.setBounds((int)(3.0/15.0*heightWindow),(int)(25.0/100.0*heightWindow),(int)(9.0/15.0*widthWindow),(int)(8.0/100.0*heightWindow));
            passwordText.setBounds((int)(3.0/15.0*heightWindow),(int)(35.0/100.0*heightWindow),(int)(9.0/15.0*widthWindow),(int)(8.0/100.0*heightWindow));
            loginAgreementCheck.setBounds((int)(3.0/15.0*heightWindow),(int)(55.0/100.0*heightWindow),(int)(5.0/15.0*widthWindow),(int)(6.0/100.0*heightWindow));
            signinButton.setBounds((int)(10.0/15.0*heightWindow),(int)(55.0/100.0*heightWindow),(int)(4.0/15.0*widthWindow),(int)(6.0/100.0*heightWindow));
            againPasswordText.setBounds((int)(3.0/15.0*heightWindow),(int)(45.0/100.0*heightWindow),(int)(9.0/15.0*widthWindow),(int)(8.0/100.0*heightWindow));
        }
    }
}

package Notebook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

    JPanel root =new JPanel();
    JLabel welcomeText =new JLabel("欢迎使用笔记本！");
    JTextField userNameText=new JTextField(90);
    JPasswordField passwordText= new JPasswordField(90);
    JLabel noAccountText= new JLabel("没有账号？创建一个！");
    JCheckBox loginAgreementCheck =new JCheckBox("同意用户使用协定");
    JCheckBox rememberPassword=new JCheckBox("记住密码");
    JButton loginButton=new JButton("登陆");
    JButton signinButton=new JButton("注册");

    public Database database=new Database();

    public LoginFrame(String title)
    {
        super(title);
        this.setSize(400,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        database.loadFileToProgram();

        LoginLayout loginLayout=new LoginLayout();

        //添加
        this.add(root);
        root.setLayout(loginLayout);
        root.add(welcomeText);
        root.add(userNameText);
        root.add(passwordText);
        root.add(loginAgreementCheck);
        root.add(rememberPassword);
        root.add(loginButton);
        root.add(signinButton);
        root.add(noAccountText);

        //设置文字
        userNameText.setText(database.lastLoginName);
        passwordText.setText(database.lastLoginPassword);

        //设置按钮
        loginAgreementCheck.setSelected(database.isSelectedAgreement);
        rememberPassword.setSelected(database.isSelectedRemember);
        loginButton.setEnabled(database.isSelectedAgreement);
        signinButton.setEnabled(database.isSelectedAgreement);

        //监听器
        ActionListener agreementListener=new AgreementActionListener();
        loginAgreementCheck.addActionListener(agreementListener);
        ActionListener loginListener=new LoginActionListener();
        loginButton.addActionListener(loginListener);
        ActionListener signinListener=new SigninActionListener();
        signinButton.addActionListener(signinListener);

        this.setVisible(true);
    }

    private class AgreementActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(loginAgreementCheck.isSelected()){
                loginButton.setEnabled(true);
                signinButton.setEnabled(true);
            }else {
                loginButton.setEnabled(false);
                signinButton.setEnabled(false);
            }
        }
    }

    private class LoginActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userName=userNameText.getText();
            String password=passwordText.getText();
            UserInformation userInformation=database.loginCheck(userName,password);
            if(userInformation==null){
                new WrongFrame("发生错误","密码错误或用户不存在！");
            }else {
                boolean agreement=loginAgreementCheck.isSelected();
                boolean remember=rememberPassword.isSelected();
                database.setLastLoginInformation(userName,password,agreement,remember);
                new NotebookFrame("笔记本",userInformation,database);
                LoginFrame.this.dispose();
            }
        }
    }

    public class SigninActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            new SigninFrame("注册页面",database);
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
            welcomeText.setBounds((int)(45.0/150.0*widthWindow),(int)(10.0/100.0*heightWindow),(int)(10.0/15.0*widthWindow),(int)(10.0/100.0*heightWindow));
            userNameText.setBounds((int)(2.0/15.0*widthWindow),(int)(25.0/100.0*heightWindow),(int)(11.0/15.0*widthWindow),(int)(8.0/100.0*heightWindow));
            passwordText.setBounds((int)(2.0/15.0*widthWindow),(int)(35.0/100.0*heightWindow),(int)(11.0/15.0*widthWindow),(int)(8.0/100.0*heightWindow));
            loginAgreementCheck.setBounds((int)(2.0/15.0*widthWindow),(int)(45.0/100.0*heightWindow),(int)(4.75/15.0*widthWindow),(int)(6.0/100.0*heightWindow));
            rememberPassword.setBounds((int)(6.75/15.0*widthWindow),(int)(45.0/100.0*heightWindow),(int)(3.0/15.0*widthWindow),(int)(6.0/100.0*heightWindow));
            loginButton.setBounds((int)(10.0/15.0*widthWindow),(int)(45.0/100.0*heightWindow),(int)(3.0/15.0*widthWindow),(int)(6.0/100.0*heightWindow));
            noAccountText.setBounds((int)(2.0/15.0*widthWindow),(int)(55.0/100.0*heightWindow),(int)(6.0/15.0*widthWindow),(int)(6.0/100.0*heightWindow));
            signinButton.setBounds((int)(10.0/15.0*widthWindow),(int)(55.0/100.0*heightWindow),(int)(3.0/15.0*widthWindow),(int)(6.0/100.0*heightWindow));

        }
    }
}

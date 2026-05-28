package Notebook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WrongFrame extends JFrame {

    JPanel root=new JPanel();
    JLabel label=new JLabel();
    JButton exitButton=new JButton("确定");

    public WrongFrame(String title, String text)
    {
        super(title);
        this.setSize(300,200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Layout layoutManager=new Layout();

        this.add(root);
        root.add(label);
        root.add(exitButton);
        root.setLayout(layoutManager);

        label.setText(text);

        ActionListener exitListener =new ExitActionListener();
        exitButton.addActionListener(exitListener);

        this.setVisible(true);
    }

    public class ExitActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            WrongFrame.this.dispose();
        }
    }

    public class Layout extends LayoutAdaptor{
        @Override
        public void addLayoutComponent(Component comp, Object constraints) {

        }

        @Override
        public void removeLayoutComponent(Component comp) {

        }

        @Override
        public void layoutContainer(Container parent) {
            int width=getWidth();
            int height=getHeight();
            label.setBounds((int)(0.35*width),(int)(0.2*height),(int)(0.4*width),(int)(0.2*height));
            exitButton.setBounds((int)(0.35*width),(int)(0.5*height),(int)(0.3*width),(int)(0.15*height));
        }
    }
}

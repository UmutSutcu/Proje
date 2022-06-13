package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiMain extends JPanel{
    private JFrame frame;
    public GuiMain(JFrame frame){
        this.frame = frame;
        setLayout(null);
        frame.setVisible(true);
        setBackground(Color.orange);


        JLabel lb_welcome = new JLabel("Welcome to the KOU BANK");
        lb_welcome.setFont( lb_welcome.getFont().deriveFont(20f) );
        lb_welcome.setBounds(280,50,400, 40);
        add(lb_welcome);

        JLabel lb_kategori = new JLabel("Kategorinizi Seçiniz : ");
        lb_kategori.setFont( lb_kategori.getFont().deriveFont(10f) );
        lb_kategori.setBounds(350,150,120, 40);
        add(lb_kategori);

        JButton btn_rep = new JButton("Rep");
        btn_rep.setFont( btn_rep.getFont().deriveFont(10f) );
        btn_rep.setBackground(Color.orange);
        btn_rep.setForeground(Color.black);
        btn_rep.setBounds(250,200,100, 40);
        add(btn_rep);

        JButton btn_cus = new JButton("Customer");
        btn_cus.setFont( btn_cus.getFont().deriveFont(10f) );
        btn_cus.setBackground( new Color(0, 204, 153) );
        btn_cus.setForeground(Color.black);
        btn_cus.setBounds(350,200,100, 40);
        add(btn_cus);

        JButton btn_man = new JButton("Manager");
        btn_man.setFont( btn_man.getFont().deriveFont(10f) );
        btn_man.setBackground( new Color(0, 204, 153) );
        btn_man.setForeground(Color.black);
        btn_man.setBounds(450,200,100, 40);
        add(btn_man);


        btn_rep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

             setVisible(false);
             frame.setContentPane(new GuiRepGiris(frame));
            }
        });
        btn_cus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                frame.setContentPane(new GuiCustomerGiris(frame));
            }
        });
        btn_man.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               setVisible(false);
                frame.setContentPane(new GuiManagerGiris(frame));


            }
        });


    }

    }

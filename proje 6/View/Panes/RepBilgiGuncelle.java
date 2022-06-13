package view.panes;

import service.RepService;
import view.GuiRep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RepBilgiGuncelle extends JPanel {

    private JFrame frame ;
    RepService repService = new RepService();

    public RepBilgiGuncelle(JFrame frame,String kisi){
        this.frame = frame ;
        setLayout(null);
        frame.setVisible(true);
        setBackground(Color.orange);



        JLabel lb_giris = new JLabel("Güncelle");
        lb_giris.setFont( lb_giris.getFont().deriveFont(25f) );
        lb_giris.setBounds(350,0,120, 40);
        add(lb_giris);

        JLabel lb_phone = new JLabel("phone no : ");
        lb_phone.setFont( lb_phone.getFont().deriveFont(10f) );
        lb_phone.setBounds(350,150,100, 40);
        add(lb_phone);

        JTextField tf_phone = new JTextField();
        tf_phone.setBounds(500, 150, 100, 25);
        add(tf_phone);

        JLabel lb_address = new JLabel("address : ");
        lb_address.setFont( lb_address.getFont().deriveFont(10f) );
        lb_address.setBounds(350,200,100, 40);
        add(lb_address);

        JTextField tf_address = new JTextField();
        tf_address.setBounds(500, 200, 100, 25);
        add(tf_address);

        JLabel lb_mail = new JLabel("mail : ");
        lb_mail.setFont( lb_mail.getFont().deriveFont(10f) );
        lb_mail.setBounds(350,250,100, 40);
        add(lb_mail);

        JTextField tf_mail = new JTextField();
        tf_mail.setBounds(500, 250, 100, 25);
        add(tf_mail);

        JLabel lb_password = new JLabel("password : ");
        lb_password.setFont( lb_password.getFont().deriveFont(10f) );
        lb_password.setBounds(350,300,100, 40);
        add(lb_password);
        JPasswordField tf_password = new JPasswordField();
        tf_password.setBounds(500, 300, 100, 25);
        add(tf_password);

        JLabel lb_firstName = new JLabel("first name : ");
        lb_firstName.setFont( lb_firstName.getFont().deriveFont(10f) );
        lb_firstName.setBounds(350,50,100, 40);
        add(lb_firstName);

        JTextField tf_firstName = new JTextField();
        tf_firstName.setBounds(500, 50, 100, 25);
        add(tf_firstName);

        JLabel lb_lastName = new JLabel("last name : ");
        lb_lastName.setFont( lb_lastName.getFont().deriveFont(10f) );
        lb_lastName.setBounds(350,100,100, 40);
        add(lb_lastName);

        JTextField tf_lastName = new JTextField();
        tf_lastName.setBounds(500, 100, 100, 25);
        add(tf_lastName);

        JButton btn_send = new JButton("Bilgileri Güncelle");
        btn_send.setFont( btn_send.getFont().deriveFont(10f) );
        btn_send.setBackground( new Color(0, 204, 153) );
        btn_send.setForeground(Color.black);
        btn_send.setBounds(570,350,130, 40);
        add(btn_send);

        btn_send.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String phone = tf_phone.getText();
                String address = tf_address.getText();
                String mail = tf_lastName.getText();
                String password = String.valueOf(tf_password.getPassword());
                String first_name = tf_firstName.getText();
                String last_name = tf_lastName.getText();

                repService.MusDuzenle(kisi,phone,address,mail,password,first_name,last_name);

                setVisible(false);
                frame.setContentPane(new GuiRep(frame));

            }
        });

        JButton btn_geri = new JButton("Geri Dön");
        btn_geri.setFont( btn_geri.getFont().deriveFont(10f) );
        btn_geri.setBackground( new Color(0, 204, 153) );
        btn_geri.setForeground(Color.black);
        btn_geri.setBounds(0,0,100, 40);
        add(btn_geri);

        btn_geri.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                frame.setContentPane(new GuiRep(frame));

            }
        });

    }
}

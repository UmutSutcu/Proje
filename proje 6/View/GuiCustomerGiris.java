package view;

import members.Customer;
import service.CustomerService;
import service.UserAuthenticationRegistery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiCustomerGiris extends JPanel {

    private JFrame frame;

    public GuiCustomerGiris(JFrame frame) {
        this.frame = frame;
        setLayout(null);
        frame.setVisible(true);
        setBackground(Color.orange);

        JLabel lb_welcome2 = new JLabel("KOU BANK");
        lb_welcome2.setFont(lb_welcome2.getFont().deriveFont(25f));
        lb_welcome2.setBounds(350, 50, 200, 40);
        add(lb_welcome2);

        JLabel lb_giris = new JLabel("Customer");
        lb_giris.setFont(lb_giris.getFont().deriveFont(15f));
        lb_giris.setBounds(350, 150, 200, 40);
        add(lb_giris);

        JLabel lb_repNo = new JLabel("TC No:");
        lb_repNo.setFont(lb_repNo.getFont().deriveFont(15f));
        lb_repNo.setBounds(200, 250, 120, 40);
        add(lb_repNo);

        JLabel lb_repPassword = new JLabel("Password:");
        lb_repPassword.setFont(lb_repPassword.getFont().deriveFont(15f));
        lb_repPassword.setBounds(200, 300, 120, 40);
        add(lb_repPassword);


        JTextField tf_tc = new JTextField();
        tf_tc.setBounds(350, 250, 180, 25);
        add(tf_tc);

        JPasswordField tf_Password = new JPasswordField();
        tf_Password.setBounds(350, 300, 180, 25);
        add(tf_Password);


        JButton btn_giris = new JButton("Giriş");
        btn_giris.setFont(btn_giris.getFont().deriveFont(10f));
        btn_giris.setBackground(new Color(0, 204, 153));
        btn_giris.setForeground(Color.black);
        btn_giris.setBounds(350, 400, 100, 40);
        add(btn_giris);


        btn_giris.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String tc = tf_tc.getText();
                String password = String.valueOf(tf_Password.getPassword());
                CustomerService customerService = new CustomerService();
                Customer customer = new Customer();
                try {
                    customer = customerService.login(tc, password);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Giriş yaparken hata oluştu, tekrar deneyin.",
                            "Giriş Hatası", JOptionPane.ERROR_MESSAGE);
                }
                if (customer == null) {
                    JOptionPane.showMessageDialog(frame, "Giriş bilgilerini kontrol ediniz.",
                            "Giriş Hatası", JOptionPane.ERROR_MESSAGE);
                } else {
                    setVisible(false);
                    frame.setContentPane(new GuiCustomer(frame,tc));
                    UserAuthenticationRegistery.REGISTERED_CUSTOMER = customer;
                }
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
                frame.setContentPane(new GuiMain(frame));

            }
        });
    }

}

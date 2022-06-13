package view.panes;


import members.Customer;
import service.CustomerService;
import view.GuiCustomer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MusteriDurum extends JPanel {



    private JFrame frame ;
    private String kisi ;
    public CustomerService customerService = new CustomerService();




    public MusteriDurum(JFrame frame,String kisi) throws SQLException {
        this.frame = frame;
        this.kisi = kisi;
        setLayout(null);
        frame.setVisible(true);
        setBackground(Color.orange);


        Customer customer= customerService.durumum(kisi);


        JLabel lb_giris = new JLabel("Müşteri Durum");
        lb_giris.setFont(lb_giris.getFont().deriveFont(25f));
        lb_giris.setBounds(350, 50, 200, 40);
        add(lb_giris);

        JLabel lb_balance = new JLabel("Bakiye");
        lb_balance.setFont(lb_balance.getFont().deriveFont(10f));
        lb_balance.setBounds(350, 150, 200, 40);
        add(lb_balance);

        JLabel lb_balancee = new JLabel(String.valueOf(customer.getBalance()));
        lb_balancee.setFont(lb_balancee.getFont().deriveFont(10f));
        lb_balancee.setBounds(350, 250, 200, 40);
        add(lb_balancee);

        JLabel lb_borc = new JLabel("Borç");
        lb_borc.setFont(lb_borc.getFont().deriveFont(10f));
        lb_borc.setBounds(450, 150, 200, 40);
        add(lb_borc);

        JLabel lb_borcc = new JLabel(String.valueOf(customer.getBorc()));
        lb_borcc.setFont(lb_borcc.getFont().deriveFont(10f));
        lb_borcc.setBounds(450, 250, 200, 40);
        add(lb_borcc);




        JButton btn_geri = new JButton("Geri");
        btn_geri.setFont(btn_geri.getFont().deriveFont(10f));
        btn_geri.setBackground(new Color(0, 204, 153));
        btn_geri.setForeground(Color.black);
        btn_geri.setBounds(0, 0, 130, 40);
        add(btn_geri);

        btn_geri.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                frame.setContentPane(new GuiCustomer(frame,kisi));

            }
        });


    }
}

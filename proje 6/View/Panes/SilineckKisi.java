package view.panes;

import service.RepService;
import view.GuiRep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SilineckKisi extends JPanel {
    private JFrame frame ;
RepService repService = new RepService();
    public SilineckKisi(JFrame frame){
        this.frame  =frame ;
        setLayout(null);
        frame.setVisible(true);
        setBackground(Color.orange);
        JLabel lb_giris = new JLabel("Kullanıcı Sil");
        lb_giris.setFont( lb_giris.getFont().deriveFont(25f) );
        lb_giris.setBounds(350,50,120, 40);
        add(lb_giris);

        JLabel lb_tc = new JLabel("Silinecek Kişi Tc : ");
        lb_tc.setFont( lb_tc.getFont().deriveFont(10f) );
        lb_tc.setBounds(350,250,100, 40);
        add(lb_tc);

        JTextField tf_tc = new JTextField();
        tf_tc.setBounds(500, 250, 100, 25);
        add(tf_tc);


        JButton btn_send = new JButton(" Sil.");
        btn_send.setFont( btn_send.getFont().deriveFont(10f) );
        btn_send.setBackground( new Color(0, 204, 153) );
        btn_send.setForeground(Color.black);
        btn_send.setBounds(370,350,130, 40);
        add(btn_send);

        btn_send.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String kisi = tf_tc.getText();
                repService.musSil(kisi);
                setVisible(false);
                frame.setContentPane(new GuiRep(frame));

            }
        });

        JButton btn_geri = new JButton("Geri");
        btn_geri.setFont(btn_geri.getFont().deriveFont(10f));
        btn_geri.setBackground(new Color(0, 204, 153));
        btn_geri.setForeground(Color.black);
        btn_geri.setBounds(0, 0, 130, 40);
        add(btn_geri);

        btn_geri.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                frame.setContentPane(new GuiRep(frame));

            }
        });

    }
}

package view.panes;

import service.ManagerService;
import view.GuiRep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RepYeniMusteri extends JPanel {

        private JFrame frame;
        ManagerService managerService = new ManagerService();

        public RepYeniMusteri(JFrame frame) {
            this.frame = frame;
            setLayout(null);
            frame.setVisible(true);
            setBackground(Color.orange);

            JLabel lb_giris = new JLabel("Kullanıcı Ekle");
            lb_giris.setFont(lb_giris.getFont().deriveFont(25f));
            lb_giris.setBounds(350, 50, 200, 40);
            add(lb_giris);

            JLabel lb_tc = new JLabel("Yeni Müşteri TC : ");
            lb_tc.setFont(lb_tc.getFont().deriveFont(10f));
            lb_tc.setBounds(350, 150, 120, 40);
            add(lb_tc);

            JTextField tf_tc = new JTextField();
            tf_tc.setBounds(450, 150, 120, 25);
            add(tf_tc);

            JLabel lb_password = new JLabel("Yeni Müşteri Password : ");
            lb_password.setFont(lb_password.getFont().deriveFont(10f));
            lb_password.setBounds(320, 250, 120, 40);
            add(lb_password);

            JPasswordField tf_password = new JPasswordField();
            tf_password.setBounds(450, 250, 120, 25);
            add(tf_password);


            JButton btn_send = new JButton("Ekle");
            btn_send.setFont(btn_send.getFont().deriveFont(10f));
            btn_send.setBackground(new Color(0, 204, 153));
            btn_send.setForeground(Color.black);
            btn_send.setBounds(370, 400, 130, 40);
            add(btn_send);

            btn_send.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    String password = String.valueOf(tf_password.getPassword());
                    String tc = tf_tc.getText();
                    managerService.musteriEkle(tc, password);
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

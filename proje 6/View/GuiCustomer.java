package view;

import service.CustomerService;
import view.panes.BilgiGuncelle;
import view.panes.MusteriDurum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class GuiCustomer extends JPanel {
private JFrame frame ;
private  String tc ;
public CustomerService customerService = new CustomerService() ;
    public GuiCustomer(JFrame frame, String tc){
        this.frame = frame;
        this.tc = tc ;
        setLayout(null);
        frame.setVisible(true);
        setBackground(Color.orange);

          JLabel lb_giris = new JLabel("İŞLEMLER");
        lb_giris.setFont( lb_giris.getFont().deriveFont(25f) );
        lb_giris.setBounds(350,50,120, 40);
        add(lb_giris);

        JButton btn_gonder = new JButton("Para Gönder");
        btn_gonder.setFont( btn_gonder.getFont().deriveFont(10f) );
        btn_gonder.setBackground( new Color(0, 204, 153) );
        btn_gonder.setForeground(Color.black);
        btn_gonder.setBounds(270,150,100, 40);
        add(btn_gonder);

        btn_gonder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

               String kime =  JOptionPane.showInputDialog(frame,"kime  : ",
                        "Para Gönder",JOptionPane.QUESTION_MESSAGE);

               int amount = Integer.parseInt(JOptionPane.showInputDialog(frame," kaç tl : ",
                        "Para Gönder",JOptionPane.QUESTION_MESSAGE));

                try {
                    customerService.paraGonder(tc,amount,kime);

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        JButton btn_silHesap = new JButton("Hesap Sil");
        btn_silHesap.setFont( btn_silHesap.getFont().deriveFont(10f) );
        btn_silHesap.setBackground( new Color(0, 204, 153) );
        btn_silHesap.setForeground(Color.black);
        btn_silHesap.setBounds(370,150,100, 40);
        add(btn_silHesap);

        btn_silHesap.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {

                customerService.hesapSil(tc);
            }
        });
        

        JButton btn_yeniHesap = new JButton("Yeni Hesap Aç");
        btn_yeniHesap.setFont( btn_yeniHesap.getFont().deriveFont(10f) );
        btn_yeniHesap.setBackground( new Color(0, 204, 153) );
        btn_yeniHesap.setForeground(Color.black);
        btn_yeniHesap.setBounds(470,150,100, 40);
        add(btn_yeniHesap);

        btn_yeniHesap.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,
                        "Yeni Hesap Açmak İçin Müşteri hizmetleriyle iletişime geçiniz.",
                        " Yeni Hesap Ekranı",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton btn_borcOde = new JButton("Borç Öde");
        btn_borcOde.setFont( btn_borcOde.getFont().deriveFont(10f) );
        btn_borcOde.setBackground( new Color(0, 204, 153) );
        btn_borcOde.setForeground(Color.black);
        btn_borcOde.setBounds(270,250,100, 40);
        add(btn_borcOde);

        btn_borcOde.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
               int odenecek = Integer.parseInt(JOptionPane.showInputDialog(frame,"Ödeme Girin : ",
                       "Borç Ödeme",JOptionPane.QUESTION_MESSAGE));
                try {
                    customerService.borcOde(tc,odenecek);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton btn_guncelle = new JButton("Bilgi Güncelle");
        btn_guncelle.setFont( btn_guncelle.getFont().deriveFont(10f) );
        btn_guncelle.setBackground( new Color(0, 204, 153) );
        btn_guncelle.setForeground(Color.black);
        btn_guncelle.setBounds(370,250,100, 40);
        add(btn_guncelle);

        btn_guncelle.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                frame.setContentPane(new BilgiGuncelle(frame,tc));
            }
        });

        JButton btn_krediCek = new JButton("Kredi Çek");
        btn_krediCek.setFont( btn_krediCek.getFont().deriveFont(10f) );
        btn_krediCek.setBackground( new Color(0, 204, 153) );
        btn_krediCek.setForeground(Color.black);
        btn_krediCek.setBounds(470,250,100, 40);
        add(btn_krediCek);

        btn_krediCek.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,
                        "Kredi Çekmek için Müşteri hizmetleriyle iletişime geçiniz.",
                        "Kredi Çekme Ekranı",JOptionPane.INFORMATION_MESSAGE);
            }
        });


        JButton btn_durum = new JButton("Durumum");
        btn_durum.setFont( btn_durum.getFont().deriveFont(10f) );
        btn_durum.setBackground( new Color(0, 204, 153) );
        btn_durum.setForeground(Color.black);
        btn_durum.setBounds(370,200,100, 40);
        add(btn_durum);

        btn_durum.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    frame.setContentPane(new MusteriDurum(frame,tc));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        JButton btn_geri = new JButton("Çıkış");
        btn_geri.setFont( btn_geri.getFont().deriveFont(10f) );
        btn_geri.setBackground( new Color(0, 204, 153) );
        btn_geri.setForeground(Color.black);
        btn_geri.setBounds(0,0,100, 40);
        add(btn_geri);

        btn_geri.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                frame.setContentPane(new GuiCustomerGiris(frame));
            }
        });

    }
}

package view;

import service.RepService;
import view.panes.MusteriDurumRep;
import view.panes.RepBilgiGuncelle;
import view.panes.RepYeniMusteri;
import view.panes.SilineckKisi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiRep extends JPanel {

    public RepService repService = new RepService();
    private JFrame frame ;
    public GuiRep(JFrame frame){
        this.frame = frame;
        setLayout(null);
        frame.setVisible(true);
        setBackground(Color.orange);

        JLabel lb_giris = new JLabel("İŞLEMLER");
        lb_giris.setFont( lb_giris.getFont().deriveFont(25f) );
        lb_giris.setBounds(350,50,120, 40);
        add(lb_giris);

        JButton btn_duzenle = new JButton("Müşteri Düzenle");
        btn_duzenle.setFont( btn_duzenle.getFont().deriveFont(10f) );
        btn_duzenle.setBackground( new Color(0, 204, 153) );
        btn_duzenle.setForeground(Color.black);
        btn_duzenle.setBounds(270,150,100, 40);
        add(btn_duzenle);

        btn_duzenle.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String kisi = JOptionPane.showInputDialog(frame,"Müşteri TC Girin: ",
                        "Müşteri Düzenle",JOptionPane.QUESTION_MESSAGE);

                setVisible(false);
                frame.setContentPane(new RepBilgiGuncelle(frame,kisi));

            }
        });

        JButton btn_musDurum = new JButton("Müşteri Durumu");
        btn_musDurum.setFont( btn_musDurum.getFont().deriveFont(10f) );
        btn_musDurum.setBackground( new Color(0, 204, 153) );
        btn_musDurum.setForeground(Color.black);
        btn_musDurum.setBounds(370,200,100, 40);
        add(btn_musDurum);

        btn_musDurum.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) throws RuntimeException {

                String kisi = JOptionPane.showInputDialog(frame,"Müşteri Tc: "
                        ,"Müşteri Durum",JOptionPane.QUESTION_MESSAGE);

                if (kisi!=null){
                    setVisible(false);
                    frame.setContentPane(new MusteriDurumRep(frame,kisi));
                }


            }
        });

        JButton btn_yeniHesap = new JButton("Müşteri Aç");
        btn_yeniHesap.setFont( btn_yeniHesap.getFont().deriveFont(10f) );
        btn_yeniHesap.setBackground( new Color(0, 204, 153) );
        btn_yeniHesap.setForeground(Color.black);
        btn_yeniHesap.setBounds(470,150,100, 40);
        add(btn_yeniHesap);

        btn_yeniHesap.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                frame.setContentPane(new RepYeniMusteri(frame));
            }
        });

        JButton btn_musSil = new JButton("Müşteri Sil");
        btn_musSil.setFont( btn_musSil.getFont().deriveFont(10f) );
        btn_musSil.setBackground( new Color(0, 204, 153) );
        btn_musSil.setForeground(Color.black);
        btn_musSil.setBounds(270,250,100, 40);
        add(btn_musSil);

        btn_musSil.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                frame.setContentPane(new SilineckKisi(frame));
            }
        });

        JButton btn_krediTalep = new JButton("Kredi Talep");
        btn_krediTalep.setFont( btn_krediTalep.getFont().deriveFont(10f) );
        btn_krediTalep.setBackground( new Color(0, 204, 153) );
        btn_krediTalep.setForeground(Color.black);
        btn_krediTalep.setBounds(470,250,100, 40);
        add(btn_krediTalep);

        btn_krediTalep.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String tc=  JOptionPane.showInputDialog(frame,"Kredi Çekecek Müşteri Tc:",
                        "Kredi Çekme Talep",JOptionPane.QUESTION_MESSAGE);
                float miktar = Float.parseFloat(JOptionPane.showInputDialog(frame,"Kredi Miktarı:  ",
                        "Kredi Çekme Talep",JOptionPane.QUESTION_MESSAGE));
                if (tc != null && miktar!=0){
                    repService.krediTalep(tc,miktar);
                }


            }
        });


       /* JButton btn_ozet = new JButton("Aylık Özet");
        btn_ozet.setFont( btn_ozet.getFont().deriveFont(10f) );
        btn_ozet.setBackground( new Color(0, 204, 153) );
        btn_ozet.setForeground(Color.black);
        btn_ozet.setBounds(270,350,100, 40);
        add(btn_ozet);

        btn_ozet.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel();

                panel.add(new JLabel("Aylık Harcama"));
                panel.add(new JLabel("ne diyon"));
                panel.add(new JLabel("Aylık Gelir"));


                String kisi = null;//UNUTMA GİRİŞ BİLGİLERİ YAPILIRKEN ALINAN VERİYİ KAYDET.
               JOptionPane.showConfirmDialog(null,
                       panel,"lütfen istenilen değerleri giriniz: ", JOptionPane.OK_CANCEL_OPTION);


            }
        });
        */


        JButton btn_geri = new JButton("Çıkış");
        btn_geri.setFont( btn_geri.getFont().deriveFont(10f) );
        btn_geri.setBackground( new Color(0, 204, 153) );
        btn_geri.setForeground(Color.black);
        btn_geri.setBounds(0,0,100, 40);
        add(btn_geri);

        btn_geri.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                frame.setContentPane(new GuiRepGiris(frame));
            }
        });
    }

}

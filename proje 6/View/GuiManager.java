package view;

import service.ManagerService;
import view.panes.YeniMusteri;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiManager extends JPanel {

    private JFrame frame ;
    ManagerService managerService = new ManagerService();
    public GuiManager(JFrame frame){
        this.frame = frame;
        setLayout(null);
        frame.setVisible(true);
        setBackground(Color.orange);

        JLabel lb_giris = new JLabel("İŞLEMLER");
        lb_giris.setFont( lb_giris.getFont().deriveFont(25f) );
        lb_giris.setBounds(350,50,120, 40);
        add(lb_giris);

       /* JButton btn_genel = new JButton("Son İşlemler");
        btn_genel.setFont( btn_genel.getFont().deriveFont(10f) );
        btn_genel.setBackground( new Color(0, 204, 153) );
        btn_genel.setForeground(Color.black);
        btn_genel.setBounds(270,150,100, 40);
        add(btn_genel);

        btn_genel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {
                    setVisible(false);
                    frame.setContentPane(new BankaGenelDurum(frame));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });*/

        JButton btn_yeniPara = new JButton("Yeni Para Birimi");
        btn_yeniPara.setFont( btn_yeniPara.getFont().deriveFont(10f) );
        btn_yeniPara.setBackground( new Color(0, 204, 153) );
        btn_yeniPara.setForeground(Color.black);
        btn_yeniPara.setBounds(270,150,100, 40);
        add(btn_yeniPara);

        btn_yeniPara.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String yeniKur ="";
                      yeniKur  = JOptionPane.showInputDialog(frame,"Yeni Kur İsmini Girin : ",
                        "Kur Ekleme",JOptionPane.QUESTION_MESSAGE);


              float  yeniOran= Float.parseFloat(JOptionPane.showInputDialog(frame,"Yeni Kur'un Değeri Girin : ",
                        "Kur Ekleme",JOptionPane.QUESTION_MESSAGE));

                managerService.kurEkle(yeniKur,yeniOran);
            }
        });

        JButton btn_kurGuncelle = new JButton("Kur Güncelle");
        btn_kurGuncelle.setFont( btn_kurGuncelle.getFont().deriveFont(10f) );
        btn_kurGuncelle.setBackground( new Color(0, 204, 153) );
        btn_kurGuncelle.setForeground(Color.black);
        btn_kurGuncelle.setBounds(470,150,100, 40);
        add(btn_kurGuncelle);

        btn_kurGuncelle.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String kurName = JOptionPane.showInputDialog(frame,"Güncellemek İstediğiniz Kur İsmini Girin : ",
                        "Kur Güncelle",JOptionPane.QUESTION_MESSAGE);

                float kurOran = Float.parseFloat(JOptionPane.showInputDialog(frame," Kur'un Yeni Değerini Girin : ",
                        "Kur Güncelle",JOptionPane.QUESTION_MESSAGE));
                managerService.kurGuncelle(kurName,kurOran);
            }
        });

        JButton btn_maas = new JButton("Maaş Belirle");
        btn_maas.setFont( btn_maas.getFont().deriveFont(10f) );
        btn_maas.setBackground( new Color(0, 204, 153) );
        btn_maas.setForeground(Color.black);
        btn_maas.setBounds(270,250,100, 40);
        add(btn_maas);
        btn_maas.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int yeniMaas = Integer.parseInt((JOptionPane.showInputDialog(frame,"Yeni Maaş Değeri Girin : ",
                        "Maaş Belirleme",JOptionPane.QUESTION_MESSAGE)));

                managerService.maasBelirle(yeniMaas);
            }
        });

        JButton btn_haram = new JButton("Faiz Oran");
        btn_haram.setFont( btn_haram.getFont().deriveFont(10f) );
        btn_haram.setBackground( new Color(0, 204, 153) );
        btn_haram.setForeground(Color.black);
        btn_haram.setBounds(370,200,100, 40);
        add(btn_haram);
        btn_haram.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            float faizOran = Float.parseFloat(JOptionPane.showInputDialog(frame,"Yeni Faiz Oranı Girin : ",
                        "Faiz Oranı",JOptionPane.QUESTION_MESSAGE));

            managerService.faizOran(faizOran);
            }
        });
        JButton btn_CEkle = new JButton("Müşteri Ekle");
        btn_CEkle.setFont( btn_CEkle.getFont().deriveFont(10f) );
        btn_CEkle.setBackground( new Color(0, 204, 153) );
        btn_CEkle.setForeground(Color.black);
        btn_CEkle.setBounds(470,250,100, 40);
        add(btn_CEkle);
        btn_CEkle.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                frame.setContentPane(new YeniMusteri(frame));
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
                frame.setContentPane(new GuiManagerGiris(frame));
            }
        });

    }
}

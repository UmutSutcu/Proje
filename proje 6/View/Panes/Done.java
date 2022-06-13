package view.panes;

import javax.swing.*;

public class Done {

    private JFrame frame ;
    public Done (JFrame frame){
      this.frame = frame ;
    }

    public Done(){

    }
    public void MoneySended(){
        JOptionPane.showMessageDialog(frame,"Başarılı bir şekilde gönderim yapıldı.");
    }

    public void DataUpdated(){

        JOptionPane.showMessageDialog(frame,"Bilgiler başarılı bir şekilde güncellendi.");
    }
    public void ProcessSuccess(){

        JOptionPane.showMessageDialog(frame,"İşlem başarılı !");
    }
}

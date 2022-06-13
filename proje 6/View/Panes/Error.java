package view.panes;

import javax.swing.*;

public class Error {
    private JFrame frame ;
    public Error(JFrame frame){

        this.frame = frame;
    }

    public Error() {

    }

    public void notDeleted(){
        JOptionPane.showMessageDialog(frame,"Hesap Silinemedi. " +
                "Lütfen Kontrol ediniz.Para veya borç olabilir.");
    }

    public void notSended(){
        JOptionPane.showMessageDialog(frame,"Para gönderilemedi - nedeni : Bakiye yetersiz.");
    }

    public void Error() {
        JOptionPane.showMessageDialog(frame, "İşlem Gerçekleşmedi");
    }
}

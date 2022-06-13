package view;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame arayuz = new JFrame("Bank Test");
        JPanel panel = new GuiMain(arayuz);
        arayuz.setContentPane(panel);
        arayuz.setSize(800,500);
        arayuz.setVisible(true);
        arayuz.setDefaultCloseOperation(arayuz.DISPOSE_ON_CLOSE);


    }


}

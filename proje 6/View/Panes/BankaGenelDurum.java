package view.panes;

import members.Databank;
import view.GuiManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BankaGenelDurum extends JPanel {

    private Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank",
                    "root","password");
            System.out.println("System successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    private  JFrame frame = new JFrame();
    private JTable table;

    public  BankaGenelDurum(JFrame frame) throws SQLException {
        this.frame = frame;
        setLayout(null);
        frame.setVisible(true);
        setBackground(Color.orange);
        //liste yap son işlemler

        DefaultTableModel model=new DefaultTableModel();
        model.addColumn("data_name");
        model.addColumn("data_oran");
        Databank databank = null;

        String query ="Select * From Databank";
        PreparedStatement preparedStatement= getConnection().prepareStatement(query);
        ResultSet resultSet =  preparedStatement.executeQuery();
        int row = 0 ;
        while (resultSet.next()){
            databank = new Databank();
            model.setValueAt(resultSet.getString("data_name"), row, 0);
            model.setValueAt(resultSet.getFloat("data_oran"), row, 1);
            //databank.setName(resultSet.getString("data_name"));
            //databank.setOran(resultSet.getFloat("data_oran"));
            table.setModel(model);
            add(table);
            row++;


        }





        JButton btn_geri = new JButton("Geri");
        btn_geri.setFont(btn_geri.getFont().deriveFont(10f));
        btn_geri.setBackground(new Color(0, 204, 153));
        btn_geri.setForeground(Color.black);
        btn_geri.setBounds(0, 0, 130, 40);
        add(btn_geri);

        btn_geri.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                frame.setContentPane(new GuiManager(frame));

            }
        });
    }




}

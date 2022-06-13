package Dao;

import members.Manager;
import view.panes.Done;

import java.sql.*;

public class ManagerDao {

Done done = new Done();
    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","password");
            System.out.println("System successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }


    public Manager getUserByTC(String tc) throws SQLException {
        Manager manager =  null;
        String query = "Select * from Manager where tc_no=?";
        PreparedStatement preparedStatement =
                getConnection().prepareStatement(query);
        preparedStatement.setString(1, tc);

        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            manager = new Manager();
            manager.setFirst_name(resultSet.getString("first_name"));
            manager.setPassword(resultSet.getString("password"));
        }
        return manager ;
    }



    public void kurGuncelle(String kurName,float kurOran) throws SQLException {
        String query = "Update DataBank Set data_oran=? where data_name=?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        preparedStatement.setFloat(1,kurOran);
        preparedStatement.setString(2,kurName);
        preparedStatement.executeUpdate();
        done.DataUpdated();
    }

    public void kurEkle(String yeniKur,float yeniOran) throws SQLException {
        if (yeniKur != "" && yeniOran !=0) {
            String query = "INSERT INTO DataBank(data_name,data_oran) VALUES (?,?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, yeniKur);
            preparedStatement.setFloat(2, yeniOran);
            preparedStatement.executeUpdate();
            done.ProcessSuccess();
        }
    }

    public void maasBelirle(int yeniMaas) throws SQLException {

        String query = "Update Representative Set maas=?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        preparedStatement.setInt(1,yeniMaas);
        preparedStatement.executeUpdate();
        done.DataUpdated();
    }

    public void faizOran(float faizOran) throws SQLException {

        String query = "Update DataBank Set  data_oran=? where data_name=?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        preparedStatement.setFloat(1,faizOran);
        preparedStatement.setString(2, "faizOran");
        preparedStatement.executeUpdate();
        done.DataUpdated();
    }

    public void musteriEkle(String tc,String password) throws SQLException {



            String query = "INSERT INTO Customer(tc_no,password) VALUES (?,?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, tc);
            preparedStatement.setString(2, password);
            int resultSet = preparedStatement.executeUpdate();
            if (resultSet != 0)
                done.ProcessSuccess();
        }



    public void BankaDurum() throws SQLException {

//listeleme query ve ekrana yazdırma.
        String query = "";
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);

        int resultSet = preparedStatement.executeUpdate();
        if (resultSet != 0)
            done.ProcessSuccess();
    }


    public void SonIslemler() throws SQLException {

//listeleme query ve ekrana yazdırma.
        String query = "";
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);

        int resultSet = preparedStatement.executeUpdate();
        if (resultSet != 0)
            done.ProcessSuccess();
    }


}

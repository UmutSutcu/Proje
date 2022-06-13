package Dao;

import members.Customer;
import members.Representative;
import view.panes.Done;
import view.panes.Error;

import java.sql.*;


public class RepresentativeDao {
Done done = new Done();
Error error = new Error();

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


    public Representative getUserByTC(String tc) throws SQLException {
        Representative rep =  null;
        String query = "Select * from Representative where tc_no=?";
        PreparedStatement preparedStatement =
                getConnection().prepareStatement(query);
        preparedStatement.setString(1, tc);

        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            rep = new Representative();
            rep.setFirst_name(resultSet.getString("first_name"));
            rep.setPassword(resultSet.getString("password"));
        }
        return rep;
    }
    public Customer musteriBalance(String kisi) throws SQLException {
        Customer customer =  null;
        String query = "Select * from Customer where tc_no=?";
        PreparedStatement preparedStatement =
                getConnection().prepareStatement(query);
        preparedStatement.setString(1, kisi);

        ResultSet resultSet = preparedStatement.executeQuery();
        int balance =0;
        int borc =0 ;
        if(resultSet.next()){
            customer = new Customer();
            customer.setBalance(resultSet.getInt("balance"));
            customer.setBorc(resultSet.getInt("borc"));
        }
        return customer;
    }
 CustomerDao customerDao = new CustomerDao();


    public void ozet(String kisi) throws SQLException {

       Customer customer = customerDao.getUserByTC(kisi);
       //arayüzde gösterilecek bilgiler...


    }




    public void krediTalep(String tc,float miktar) throws SQLException {
        Customer customer = musteriBalance(tc);
        float normalBorc= customer.getBorc();
        String query = "UPDATE Customer Set borc=? Where tc_no=?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        preparedStatement.setFloat(1, normalBorc+miktar);
        preparedStatement.setString(2,tc);
        int resultSet = preparedStatement.executeUpdate();
        if (resultSet!=0){
            done.ProcessSuccess();
    }
    }

    public void musDuzenle(String kisi,String phone_number,String address,String mail,
                           String password,String first_name,String last_name) throws SQLException {

            Customer customer = customerDao.getUserByTC(kisi);
            String query = "Update  Customer Set phone_number = ?,address= ?, " +
                    "mail=?,password=?, first_name=?, last_name=? Where tc_no=?";

            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
         preparedStatement.setString(5,first_name);
         preparedStatement.setString(6,last_name);
        preparedStatement.setString(1,phone_number);
        preparedStatement.setString(2,address);
        preparedStatement.setString(3,mail);
        preparedStatement.setString(4,password);
        preparedStatement.setString(7,kisi);
        preparedStatement.executeUpdate();
        done.DataUpdated();


    }


    public void yeniHesap(String tc,String password) throws SQLException {

        String query = "INSERT INTO Customer(tc_no,password) VALUES (?,?)";
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        preparedStatement.setString(1,tc);
        preparedStatement.setString(2, password);
        int resultSet = preparedStatement.executeUpdate();
        if (resultSet!=0)
            done.ProcessSuccess();

    }

    public void musSil(String tc) throws SQLException {
        Customer customer = musteriBalance(tc);
        int musteriHesapBakiye = customer.getBalance();
        if (musteriHesapBakiye>0){

            error.notDeleted();

        }else {
            String query = "Delete From Customer where tc_no = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, tc);
            preparedStatement.execute();


        }




    }
}

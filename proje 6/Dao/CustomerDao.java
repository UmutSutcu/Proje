package Dao;

import members.Customer;
import view.panes.Done;
import view.panes.Error;

import java.sql.*;

public class CustomerDao {
    Done done = new Done();
    Error error = new Error();


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

    public Customer getUserByTC(String tc) throws SQLException {
        Customer customer =  null;
        String query = "Select * from Customer where tc_no=?";
        PreparedStatement preparedStatement =
                getConnection().prepareStatement(query);
        preparedStatement.setString(1, tc);

        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            customer = new Customer();
            customer.setFirst_name(resultSet.getString("first_name"));
            customer.setPassword(resultSet.getString("password"));
        }
        return customer;
    }


    public int musteriVarMı(String kisi) throws SQLException {
        Customer customer = null ;
        String query = "Select * From Customer where tc_no=?";
        PreparedStatement preparedStatement =
                getConnection().prepareStatement(query);
        preparedStatement.setString(1,kisi);
        ResultSet resultSet =preparedStatement.executeQuery();
        if (resultSet.next()){
            return 1 ; // this means there is a customer
        }

        return 0 ; // this means there isn't.

    }
    public Customer musteriBalance(String kisi) throws SQLException {
        Customer customer =  null;
        String query = "Select * from Customer where tc_no=?";
        PreparedStatement preparedStatement =
                getConnection().prepareStatement(query);
        preparedStatement.setString(1, kisi);

        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            customer = new Customer();
              customer.setBalance(resultSet.getInt("balance"));
            customer.setBorc(resultSet.getInt("borc"));


        }
        return customer;
    }

    public void paraGonder(String tc, int amount,String kisi) throws SQLException {
        //kontrol fonksiyonu : kisiVarMı
       if (musteriVarMı(kisi)==0){
           // musteri bulunamadı hatası.
       }
       else {
           //para göndericek kadar parası var mı ? fonksiyon
          Customer customer = musteriBalance(kisi);
          int musteriHesapBakiye = customer.getBalance();
           Customer customerSender = musteriBalance(tc);
           int customerSenderNewBalance = customerSender.getBalance();

           if (customerSenderNewBalance<amount){
               //paranız yetmiyor, işlem devam etmemekte hatası.
               error.notSended();
           }else { //KENDİNE PARA GÖNDEREMEZSİN HATASI YAZ.
           //para gönderdikten sonra işlemi kayıt etme, databaseden de çekebiliriz.
           //para azaltma ve arttırma.(int olarak bir fonksiyondan select ile balance çek.) query2 ise update para giden.
           String query = "Update Customer Set balance = ? Where tc_no= ?";
           PreparedStatement preparedStatement = getConnection().prepareStatement(query);
           preparedStatement.setInt(1, musteriHesapBakiye+amount);
           preparedStatement.setString(2, kisi);
           preparedStatement.executeUpdate();

           String query2 = "Update Customer Set balance=? Where tc_no=?";
           preparedStatement = getConnection().prepareStatement(query2);
           preparedStatement.setInt(1,customerSenderNewBalance-amount);
           preparedStatement.setString(2,tc);
           preparedStatement.executeUpdate();


           done.MoneySended();

           }
       }
    }

    public void hesapSil(String kisi) throws SQLException {
        //kontrol fonksiyonu : kisiVarMı
        if (musteriVarMı(kisi)==0 ){
            // musteri bulunamadı hatası.
        }
        else {
            //para var mı varsa silemez
            //aynısını borç için de yap
            Customer customer = musteriBalance(kisi);
            int musteriHesapBakiye = customer.getBalance();
            if (musteriHesapBakiye>0){

                error.notDeleted();

            }else {
                String query = "Delete From Customer where tc_no = ?";
                PreparedStatement preparedStatement = getConnection().prepareStatement(query);
                preparedStatement.setString(1, kisi);
                preparedStatement.execute();


                }

            }

        }





    public void bilgiGuncelle(String kisi,String phone_number,String address,String mail,String password) throws SQLException {

        if (musteriVarMı(kisi)==0){
            // musteri bulunamadı hatası.
        }
        else {
            //para gönderdikten sonra işlemi kayıt etme, databaseden de çekebiliriz.
                //para azaltma ve arttırma.(int olarak bir fonksiyondan select ile balance çek.) query2 ise update para giden.
                String query = "Update Customer Set phone_number = ?,address= ?, mail=?,password=?  Where tc_no= ?";
                PreparedStatement preparedStatement = getConnection().prepareStatement(query);

            preparedStatement.setString(1,phone_number);
            preparedStatement.setString(2,address);
            preparedStatement.setString(3,mail);
            preparedStatement.setString(4,password);
            preparedStatement.setString(5,kisi);
              preparedStatement.executeUpdate();
              done.DataUpdated();
            }
        }


    public void borcOde(String kisi,int odenecek) throws SQLException {
        Customer customer = musteriBalance(kisi);
        int musteriBorc = customer.getBorc();
        int musteriBalance = customer.getBalance();
        if (musteriBorc>0){
            if (musteriBorc>=odenecek){

                String query ="Update Customer Set borc = ?, balance = ?  Where tc_no = ?";
                PreparedStatement preparedStatement = getConnection().prepareStatement(query);
                preparedStatement.setInt(1,musteriBorc-odenecek);
                preparedStatement.setInt(2,musteriBalance-odenecek);
                preparedStatement.setString(3,kisi);
                preparedStatement.executeUpdate();
                done.ProcessSuccess();
            }
        }

    }

    public void krediCek(int amount) {

        //kredi faizi, kredi faizi ile toplam borç kabul ediyor musun ve kredi alınması = query borc güncellenir.
    }

    public Customer durumum(String kisi) throws SQLException {
        Customer customer =  null;
        String query ="Select balance,borc From Customer Where tc_no=?";
        PreparedStatement preparedStatement = getConnection().prepareStatement(query) ;
        preparedStatement.setString(1,kisi);
       ResultSet resultSet = preparedStatement.executeQuery();

       if(resultSet.next()){
           customer = new Customer();
           customer.setBalance(resultSet.getInt("balance"));
           customer.setBorc(resultSet.getInt("borc"));



       }
       return  customer ;

    }
}

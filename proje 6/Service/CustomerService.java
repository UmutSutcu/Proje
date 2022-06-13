package service;

import Dao.CustomerDao;
import members.Customer;

import java.sql.SQLException;

public class CustomerService {
    private CustomerDao customerDao = new CustomerDao();
   public   CustomerService(){

    }


    public Customer login(String tc, String password) throws Exception{
        Customer user = customerDao.getUserByTC(tc);
        if(isUserValid(password, user)){
            return null;
        }
        return user;
    }
    private boolean isUserValid(String password, Customer user) {
        return user == null || !user.getPassword().equals(password);
    }

    public void paraGonder(String tc, int amount, String kisi) throws SQLException {
        customerDao.paraGonder( tc,amount,kisi);

    }


    public void  hesapSil(String kisi){
        try {
            customerDao.hesapSil(kisi);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void bilgiGuncelle(String kisi,String phone_number,String address,String mail,String password){
        try {
            customerDao.bilgiGuncelle( kisi, phone_number, address, mail, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void borcOde(String kisi,int odenecek) throws SQLException{

            customerDao.borcOde(kisi,odenecek);
    }

    public void krediCek(int amount){

       customerDao.krediCek(amount);
    }

    public Customer durumum(String kisi)  {

        try {

            Customer customer =new Customer();
            customer =customerDao.durumum(kisi);
            return customer;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }




}

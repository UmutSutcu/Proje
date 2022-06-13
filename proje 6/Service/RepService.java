package service;

import Dao.CustomerDao;
import Dao.RepresentativeDao;
import members.Customer;
import members.Representative;

import java.sql.SQLException;

public class RepService {
    private RepresentativeDao representativeDao = new RepresentativeDao() ;
    private CustomerDao customerDao = new CustomerDao() ;

    public Representative login(String tc, String password) throws Exception{
        Representative user = representativeDao.getUserByTC(tc);
        if(isUserValid(password, user)){
            return null;
        }
        return user;
    }
    private boolean isUserValid(String password, Representative user) {
        return user == null || !user.getPassword().equals(password);
    }


    public void MusDuzenle(String kisi,String phone_number,String address,String mail,String password,String first_name,String last_name) {
        try {
            representativeDao.musDuzenle(kisi,phone_number,address,mail,password,first_name,last_name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Customer musDurum(String kisi) {
        try {
            return customerDao.durumum(kisi);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void yeniHesap(String tc,String password) throws SQLException {
        representativeDao.yeniHesap(tc,password);
    }

    public void musSil(String tc) {
        try {
            representativeDao.musSil( tc);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void krediTalep(String tc,float miktar) {
        try {
            representativeDao.krediTalep(tc,miktar);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public void ozet(String kisi) {
        try {
            representativeDao.ozet(kisi);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

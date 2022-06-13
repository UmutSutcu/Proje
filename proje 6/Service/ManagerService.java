package service;

import Dao.ManagerDao;
import members.Manager;
import view.panes.Error;

import java.sql.SQLException;

public class ManagerService {

    private ManagerDao managerDao = new ManagerDao();
    Error error = new Error();
    public Manager login(String tc, String password) throws Exception{
        Manager manager = managerDao.getUserByTC(tc);
        if(isUserValid(password, manager)){
            return null;
        }
        return manager;
    }

    private boolean isUserValid(String password, Manager user) {
        return user == null || !user.getPassword().equals(password);
    }

    public void faizOran(float faizOran){
        try {
            managerDao.faizOran(faizOran);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void maasBelirle(int yeniMaas){
        try {
            managerDao.maasBelirle(yeniMaas);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void kurEkle(String yeniKur,float yeniOran){
        try {
            managerDao.kurEkle(yeniKur,yeniOran);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void kurGuncelle(String kurName,float kurOran){
        try {
            managerDao.kurGuncelle(kurName,kurOran);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void musteriEkle(String tc,String password){
        try {
            managerDao.musteriEkle(tc,password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void  BankaDurum(){
        try {
            managerDao.BankaDurum();

        } catch (SQLException e) {
             error.Error();
        }
    }

    public void  SonIslemler(){
        try {
            managerDao.SonIslemler();

        } catch (SQLException e) {
            error.Error();
        }
    }
}

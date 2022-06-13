package members;

public class Customer extends User{
    public  int idCustomer ;
    public int balance ;
    public int borc ;

    public Customer(){}
    public Customer(String first_name, String last_name, String tc_no, String phoneNumber, int balance,String address, String mail,String password,int borc) {
        super(first_name, last_name, tc_no, phoneNumber, address, mail,password);
        this.balance = balance ;
        this.borc = borc ;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getBalance() {

        return balance;
    }

    public void setBalance(int balance) {

        this.balance = balance;
    }

    public int getBorc() {

        return borc;
    }

    public void setBorc(int borc) {

        this.borc = borc;
    }



}

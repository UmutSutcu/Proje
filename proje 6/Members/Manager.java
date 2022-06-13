package members;

public class Manager extends  User{

    public  int idManager ;
    public int salary ;

    public Manager(String first_name, String last_name, String tc_no, String phoneNumber,
                   String address, String mail, int idManager, String password, int salary) {
        super(first_name, last_name, tc_no, phoneNumber, address, mail,password);
        this.idManager = idManager;
        this.salary = salary;
    }

    public Manager() {

    }

    public int getManagerNo() {
        return idManager;
    }

    public void setManagerNo(int idManager) {
        this.idManager = idManager;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }




}

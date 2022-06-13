package members;

public class Representative extends User{


    public  int idRepresentative ;
    public int salary ;

    public Representative(String first_name, String last_name, String tc_no,String phoneNumber,
                          String address, String mail, int idRepresentative , String password,int salary) {
        super(first_name, last_name, tc_no, phoneNumber, address, mail,password);
        this.idRepresentative = idRepresentative;
        this.salary = salary ;
    }

    public Representative() {

    }

    public int getRepNo() {
        return idRepresentative;
    }

    public void setRepNo(int idRepresentative ) {
        this.idRepresentative = idRepresentative ;}

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

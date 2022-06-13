package members;

public class User {
    String first_name ;
    String last_name ;
    String tc_no ;
    String phoneNumber ;
    String address ;
    String mail;

    String password;

    public User(String first_name, String last_name, String tc_no, String phoneNumber, String address, String mail, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.tc_no = tc_no;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.mail = mail;
        this.password = password;
    }

    public User() {
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getTc_no() {
        return tc_no;
    }

    public void setTc_no(String tc_no) {
        this.tc_no = tc_no;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }



}

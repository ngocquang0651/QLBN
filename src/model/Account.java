package model;

public class Account {
    private String userName;
    private String password;
//contructor
    public Account( String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    //get and set
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

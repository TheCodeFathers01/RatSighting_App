package edu.gatech.tcf.ratsighting_app.Model;

/**
 * Created by spmek on 10/2/2017.
 */

public class User {
    protected String username;
    protected String password;
    protected String email;
    protected UserType uT;

    public User(String username, String password, String email, UserType uT) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.uT = uT;
    }
    public User() {
        this.username = "hello";
        this.password = "world";
        this.email = "helloWorld@gmail.com";
        this.uT = UserType.USER;
    }

    public boolean validate(String uname, String pass) {
        return uname.equals(username) && pass.equals(password);
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public UserType getuT() {
        return uT;
    }
}

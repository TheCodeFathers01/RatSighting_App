package edu.gatech.tcf.ratsighting_app.Model;

import java.io.Serializable;

/**
 * Created by spmek on 10/2/2017.
 */

public class User implements Serializable {
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setuT(UserType uT) {
        this.uT = uT;
    }
}


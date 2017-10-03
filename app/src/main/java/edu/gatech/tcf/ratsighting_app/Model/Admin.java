package edu.gatech.tcf.ratsighting_app.Model;

/**
 * Created by spmek on 10/2/2017.
 */

public class Admin extends User {
    public Admin() {
        this.username = "hello";
        this.password = "world";
        this.email = "helloWorld@gmail.com";
        this.uT = UserType.ADMIN;
    }
    public Admin(String username, String password, String email, UserType uT) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.uT = uT;
    }
}

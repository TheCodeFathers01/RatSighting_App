package edu.gatech.tcf.ratsighting_app.Model;

import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

/**
 * Created by raghavbhat on 9/24/17.
 */

public class DefaultUser {

    protected String username;
    protected String password;
    protected String email;

    public DefaultUser(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public boolean validate(String uname, String pass) {
        return uname.equals(username) && pass.equals(password);
    }
}

package edu.gatech.tcf.ratsighting_app.Model;

import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

/**
 * Created by raghavbhat on 9/24/17.
 */

public class DefaultUser extends User{
    public DefaultUser(String username, String password, String email, UserType uT) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.uT = uT;
    }

}

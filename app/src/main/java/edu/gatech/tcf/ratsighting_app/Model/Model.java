package edu.gatech.tcf.ratsighting_app.Model;

import java.util.HashMap;

/**
 * Created by raghavbhat on 9/24/17.
 */

public class Model {
    public static HashMap<String, String> loginCreds = new HashMap<String, String>();

    public static Model myModel = new Model();

    private Model() {
        loginCreds.put("user", "pass");
    }


}

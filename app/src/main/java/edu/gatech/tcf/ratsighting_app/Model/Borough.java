package edu.gatech.tcf.ratsighting_app.Model;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public enum Borough implements Serializable{
    MANHATTAN("MANHATTAN"), STATENISLAND("STATEN ISLAND"), QUEENS("QUEENS"), BROOKLYN("BROOKLYN"), BRONX("BRONX"), OTHER("OTHER");

    String description;

    Borough(String string) {
        description = string;
    }

    public String getDescription() {
        return description;
    }

    private static Map<String, Borough> map = new HashMap<String, Borough>();

    static {
        for (Borough borough: Borough.values()) {
            map.put(borough.description, borough);
        }
    }

    public static Borough getBorough(String string) {
        return map.get(string);
    }
}

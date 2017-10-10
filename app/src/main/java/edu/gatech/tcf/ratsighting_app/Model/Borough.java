package edu.gatech.tcf.ratsighting_app.Model;

public enum Borough {
    MANHATTAN("MANHATTAN"), STATENISLAND("STATEN ISLAND"), QUEENS("QUEENS"), BROOKLYN("BROOKLYN"), BRONX("BRONX");

    String description;

    Borough(String string) {
        description = string;
    }

    public String getDescription() {
        return description;
    }
}

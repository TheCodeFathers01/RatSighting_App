package edu.gatech.tcf.ratsighting_app.Model;

import java.util.HashMap;
import java.util.Map;

public enum LocationType {

    FamilyDwelling("1-2 Family Dwelling"), FamilyApt("3+ Family Apartment Building"), FamilyMixedUse("3+ Family Mixed Use Building"), Commercial("Commercial Building"),
    Vacant("Vacant Lot"), Construction("Construction Site"), Hospital("Hospital"), Catch("Catch Basin/Sewer"), Other("Other (Explain Below)");

    String description;

    LocationType(String string) {
        description = string;
    }

    public String getDescription(){
        return description;
    }

    private static Map<String, LocationType> map = new HashMap<String, LocationType>();
    static {
        for (LocationType mLocationType : LocationType.values()) {
            map.put(mLocationType.description, mLocationType);
        }
    }

    public static LocationType getLocationType(String string) {
        return map.get(string);
    }
}

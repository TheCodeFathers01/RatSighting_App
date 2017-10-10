package edu.gatech.tcf.ratsighting_app.Model;

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
}

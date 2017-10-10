package edu.gatech.tcf.ratsighting_app.Model;

import java.util.Date;

public class RatSighting {

    private int key;
    private String mDate;
    private String coordinates;
    private LocationType mLocationType;
    private String zipCode;
    private String address;
    private String city;
    private Borough mBorough;

    public RatSighting(int key, String date, String coordinates, LocationType locationType, String zipCode, String address, String city, Borough borough) {
        this.key = key;
        mDate = date;
        this.coordinates = coordinates;
        mLocationType = locationType;
        this.zipCode = zipCode;
        this.address = address;
        this.city = city;
        mBorough = borough;
    }

    public int getKey() {
        return key;
    }

    public String getDate() {
        return mDate;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public LocationType getLocationType() {
        return mLocationType;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public Borough getBorough() {
        return mBorough;
    }
}

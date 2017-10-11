package edu.gatech.tcf.ratsighting_app.Model;

import java.io.Serializable;
import java.util.Date;

public class RatSighting implements Serializable {

    private int key;
    private String mDate;
    private String coordinates;
    private LocationType mLocationType;
    private String zipCode;
    private String address;
    private String city;
    private Borough mBorough;

    public void setKey(int key) {
        this.key = key;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public void setmLocationType(LocationType mLocationType) {
        this.mLocationType = mLocationType;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setmBorough(Borough mBorough) {
        this.mBorough = mBorough;
    }

    public RatSighting() {}

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

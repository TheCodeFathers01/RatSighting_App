package edu.gatech.tcf.ratsighting_app.Model;

import java.util.Date;

public class RatSighting {

    private int key;
    private Date mDate = new Date();
    private int lattitude;
    private int longitude;
    private LocationType mLocationType;
    private int zipCode;
    private String address;
    private String city;
    private Borough mBorough;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Date getDate() {
        return mDate;
    }

    public int getLattitude() {
        return lattitude;
    }

    public void setLattitude(int lattitude) {
        this.lattitude = lattitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public LocationType getLocationType() {
        return mLocationType;
    }

    public void setLocationType(LocationType locationType) {
        mLocationType = locationType;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Borough getBorough() {
        return mBorough;
    }

    public void setBorough(Borough borough) {
        mBorough = borough;
    }
}

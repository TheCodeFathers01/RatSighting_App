package edu.gatech.tcf.ratsighting_app.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.opencsv.CSVReader;
import java.io.FileReader;

public class CSVParser {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("server/saving-data/sightingData");

    void parseData(String filename) {
        try {
            FileReader fileReader = new FileReader(filename);
            CSVReader reader = new CSVReader(fileReader);
            int counter = 0;
            String[] nextLine;
            nextLine = reader.readNext();
            RatSighting tempSighting;
            while (nextLine != null) {
                if (counter != 1) {
                    int key;
                    try {
                        key = Integer.parseInt(nextLine[0]);
                    } catch (Exception e) {
                        key = -1;
                    }
                    String date = nextLine[1];
                    if(validate(date)) {
                        date = "No date available.";
                    }
                    String coordinates = nextLine[51];
                    if(validate(coordinates)) {
                        coordinates = "No coordinates available";
                    }
                    LocationType locationType;
                    try {
                        locationType = LocationType.getLocationType(nextLine[7].trim());
                    } catch (Exception e) {
                        locationType = LocationType.Other;
                    }
                    String zipcode = nextLine[8];
                    if (validate(zipcode)) {
                        zipcode = "00000";
                    }
                    String address = nextLine[9];
                    if (validate(address)) {
                        address = "No address available";
                    }
                    String city = nextLine[16];
                    if (validate(city)) {
                        city = "No city available";
                    }
                    Borough borough = Borough.getBorough(nextLine[23]);
                    if (borough == null) {
                        borough = Borough.OTHER;
                    }
                    tempSighting = new RatSighting(key, date, coordinates, locationType, zipcode, address, city, borough);
                }
            }
        } catch (Exception e) {

        }
    }

    private boolean validate(String string) {
        return string == null || string.isEmpty();
    }


}

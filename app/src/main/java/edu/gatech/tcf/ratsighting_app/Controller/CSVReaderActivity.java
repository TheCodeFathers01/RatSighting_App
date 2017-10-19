package edu.gatech.tcf.ratsighting_app.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import edu.gatech.tcf.ratsighting_app.Model.Borough;
import edu.gatech.tcf.ratsighting_app.Model.LocationType;
import edu.gatech.tcf.ratsighting_app.Model.RatSighting;
import edu.gatech.tcf.ratsighting_app.R;

public class CSVReaderActivity extends AppCompatActivity {

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference("server/saving-data/sightingData");
    private DatabaseReference sightingRef;
    FileReader fs;
    InputStreamReader is;
    com.opencsv.CSVReader reader;
    String[] nextLine;
    RatSighting tempSighting;
    ArrayList<RatSighting> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Counter", "Got here");
        setContentView(R.layout.activity_csvreader);
        ref.setValue(null);
        sightingRef = ref.child("Sighting");
        try {
            Log.d("Counter", "Got here");
            int counter = 1;
            is = new InputStreamReader(getAssets().open("Rat_Sightings.csv"));
            reader = new com.opencsv.CSVReader(is);
            nextLine = reader.readNext();
            while (nextLine != null) {
                    int key;
                    try {
                        key = Integer.parseInt(nextLine[0]);
                    } catch (Exception e) {
                        key = -1;
                    }
                    String date = nextLine[1];
                    if (validate(date)) {
                        date = "No date available.";
                    }
                    String coordinates = nextLine[51];
                    if (validate(coordinates)) {
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
                    sightingRef = ref.child("Sighting " + tempSighting.getKey());
                    sightingRef.setValue(tempSighting);
                    nextLine = reader.readNext();
                    counter++;
                    Log.d("Counter", "" + counter);
            }
            Log.d("Location" , list.get(0).getLocationType().getDescription());
        } catch (Exception e) {
            Log.d("IO", "Rat_Sightings.csv not found in assets");
        }
    }

    private boolean validate(String string) {
        return string == null || string.isEmpty();
    }
}

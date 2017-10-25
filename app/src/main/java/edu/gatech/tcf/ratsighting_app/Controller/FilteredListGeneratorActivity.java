package edu.gatech.tcf.ratsighting_app.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import edu.gatech.tcf.ratsighting_app.Model.RatSighting;
import edu.gatech.tcf.ratsighting_app.Model.SightingListContainer;
import edu.gatech.tcf.ratsighting_app.R;

public class FilteredListGeneratorActivity extends AppCompatActivity {

    private Button submitFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered_list_generator);
        submitFilter = (Button) findViewById(R.id.submitFilter);
        submitFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initialize();
            }
        });

    }

    private void initialize() {
        EditText startDay = (EditText) findViewById(R.id.startDay);
        EditText startMonth = (EditText) findViewById(R.id.startMonth);
        EditText startYear = (EditText) findViewById(R.id.startYear);
        EditText endDay = (EditText) findViewById(R.id.endDay);
        EditText endMonth = (EditText) findViewById(R.id.endMonth);
        EditText endYear = (EditText) findViewById(R.id.endYear);
        String[] sightingDate;
        int intStartDay;
        int intStartMonth;
        int intStartYear;
        int intEndDay;
        int intEndMonth;
        int intEndYear;
        try {
            intStartDay = Integer.parseInt(startDay.getText().toString());
            intStartMonth = Integer.parseInt(startMonth.getText().toString());
            intStartYear = Integer.parseInt(startYear.getText().toString());
            intEndDay = Integer.parseInt(endDay.getText().toString()); //these values are the ones entered by the user
            intEndMonth = Integer.parseInt(endMonth.getText().toString());
            intEndYear = Integer.parseInt(endYear.getText().toString());
        } catch (Exception e) {

        }
        SightingListContainer.filteredList = new ArrayList<>();
        for (RatSighting sighting : SightingListContainer.list) {
                if (sighting != null && sighting.getDate() != null) {
                    sightingDate = sighting.getDate().split("/");
                } else {
                    continue;
                }
                if (sightingDate.length < 3) {
                    Log.d("Nothing", "Nothing");
                    continue;
                }
                if (sightingDate[2].length() < 4) {
                    continue;
                }
                sightingDate[2] = sightingDate[2].substring(0, 3);
                Log.d("Day", sightingDate[2]);
                if (false) { //Logic for choosing which elements go in ;sightingDate[2] contains the year, sightingDate[1] contains the day, and sightingDate[0] contains the month
                    SightingListContainer.filteredList.add(sighting);
                }
        }
        Intent map = new Intent(this, MapsActivity.class);
        map.putExtra("Filtered", true);
        startActivity(map);
    }
}

package edu.gatech.tcf.ratsighting_app.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import edu.gatech.tcf.ratsighting_app.Model.RatSighting;
import edu.gatech.tcf.ratsighting_app.Model.SightingListContainer;
import edu.gatech.tcf.ratsighting_app.R;

public class FilteredListGeneratorActivity extends AppCompatActivity {

    private Button openFilteredMap;
    private Button openGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered_list_generator);
        openFilteredMap = (Button) findViewById(R.id.submitFilter);
        openFilteredMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchFilteredMap();
            }
        });
        openGraph = (Button) findViewById(R.id.graphLauncher);
        openGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchGraph();
            }
        });


    }

    /**
     * Adds rat sightings to the filtered list based on which ones fall in the user inputted date range
     */
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
            intStartDay = 0;
            intStartMonth = 0;
            intStartYear = 0;
            intEndDay = 0;
            intEndMonth = 0;
            intEndYear = 0;
        }


        Calendar startDateSDF = new GregorianCalendar(intStartYear, intStartMonth, intStartDay, 0, 0, 0);
        Calendar endDateSDF = new GregorianCalendar(intEndYear, intEndMonth, intEndDay, 0, 0, 0);

        //sets startMonth, startYear, endMonth, and endYear for graphing activity
        SightingListContainer.startMonth = intStartMonth;
        SightingListContainer.startYear = intStartYear;
        SightingListContainer.endMonth = intEndMonth;
        SightingListContainer.endYear = intEndYear;


        SightingListContainer.filteredList = new ArrayList<>();
        int counter = 0;
        for (RatSighting sighting : SightingListContainer.list) {
                if (sighting != null && sighting.getDate() != null) {
                    Log.d("Nothing", "Not Null");
                    sightingDate = sighting.getDate().split("/");
                } else {
                    Log.d("Nothing", "Was null");
                    continue;
                }
                if (sightingDate.length < 3) {
                    Log.d("Nothing", "sightingDate length < 3");
                    continue;
                }
                if (sightingDate[2].length() < 4) {
                    Log.d("Nothing", "Year sightingDate[2] < 4");
                    continue;
                }
                sightingDate[2] = sightingDate[2].substring(0, 4);
                Log.d("Day", sightingDate[2]);
                int sightingYear = Integer.parseInt(sightingDate[2]);
                int sightingDay = Integer.parseInt(sightingDate[1]);
                int sightingMonth = Integer.parseInt(sightingDate[0]);

                Log.d("Sighting", sightingYear + " " + sightingMonth + " " + sightingDay);
                Log.d("StartDate", intStartYear + " " + intStartMonth + " " + intStartDay);
                Log.d("EndDate", intEndYear + " " + intEndMonth + " " + intEndDay);

                Calendar sightingDateSDF = new GregorianCalendar(sightingYear, sightingMonth, sightingDay, 0, 0, 0);

                if (sightingDateSDF.compareTo(startDateSDF) >= 0 && sightingDateSDF.compareTo(endDateSDF) <= 0) {
                    Log.d("FilteredListItem", counter + "");
                    SightingListContainer.filteredList.add(sighting);
                    SightingListContainer.reports.add(sightingDateSDF);
                }
                counter++;
        }
        Log.d("Values", counter + "");

    }

    private void launchGraph() {
        initialize();
        Intent graph = new Intent(this, GraphActivity.class);
        startActivity(graph);

    }

    private void launchFilteredMap() {
        initialize();
        Intent map = new Intent(this, MapsActivity.class);
        map.putExtra("Filtered", true);
        startActivity(map);
    }
}

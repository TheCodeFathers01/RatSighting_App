package edu.gatech.tcf.ratsighting_app.Controller;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.HashMap;

import edu.gatech.tcf.ratsighting_app.Model.RatSighting;
import edu.gatech.tcf.ratsighting_app.Model.SightingListContainer;
import edu.gatech.tcf.ratsighting_app.R;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        GraphView graph = (GraphView) findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>();
        //broken
        int[] months = new int[12 * (SightingListContainer.endYear - SightingListContainer.startYear) + SightingListContainer.endMonth - SightingListContainer.startMonth];

        for (RatSighting sighting : SightingListContainer.filteredList) {
            String[] sightingDate = sighting.getDate().split("/");
            int sightingMonth = Integer.parseInt(sightingDate[0]);
            int sightingYear = Integer.parseInt(sightingDate[2]);

            int bucket = sightingMonth - SightingListContainer.startMonth + 12 * (sightingYear - SightingListContainer.startYear);
            months[bucket] = months[bucket] + 1;
        }

        for (int i = 0; i < months.length; i++) {
            Log.d("DataAdded", "i: " + i + " months[i]" + months[i]);
            series.appendData(new DataPoint(i, months[i]), true, months.length);
        }

        //use http://www.android-graphview.org/bar-chart/ for reference for Bar Graph
        //DataPoint should take format (Date, Sightings)
        //Date should take format (Month, Year)
        //Somehow get this data from FireBase and plot graph accordingly
        //Implement FilterGraphButton on PostLogin to pop up this graph from clicking button
        //Implement logic for filtering date range and having the appropriate graph pop up

        graph.addSeries(series);
    }
}
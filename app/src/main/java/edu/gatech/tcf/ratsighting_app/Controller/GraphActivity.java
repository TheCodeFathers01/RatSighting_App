package edu.gatech.tcf.ratsighting_app.Controller;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Calendar;
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

        for (Calendar sighting : SightingListContainer.reports) {
            Log.d("DataAdded", "gotHere with " + sighting);
            int bucket = sighting.get(Calendar.MONTH) - SightingListContainer.startMonth + 12 * (sighting.get(Calendar.YEAR) - SightingListContainer.startYear);
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
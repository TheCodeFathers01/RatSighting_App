package edu.gatech.tcf.ratsighting_app.Controller;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

        DataPoint[] dataPoints = new DataPoint[150];
        HashMap<String, Integer> dataPointers = new HashMap<>();
        int i = 0;
        for (RatSighting r : SightingListContainer.filteredList) {
            if(dataPointers.containsKey(r.getDate())) {
                Integer val = dataPointers.get(r.getDate());
                val = val + 1;
            } else {
                dataPointers.put(r.getDate(), 1);
            }
        }

        //use http://www.android-graphview.org/bar-chart/ for reference for Bar Graph
        //DataPoint should take format (Date, Sightings)
        //Date should take format (Month, Year)
        //Somehow get this data from FireBase and plot graph accordingly
        //Implement FilterGraphButton on PostLogin to pop up this graph from clicking button
        //Implement logic for filtering date range and having the appropriate graph pop up
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
        graph.addSeries(series);
    }
}

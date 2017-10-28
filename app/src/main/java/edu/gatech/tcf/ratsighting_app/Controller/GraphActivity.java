package edu.gatech.tcf.ratsighting_app.Controller;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import edu.gatech.tcf.ratsighting_app.R;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        GraphView graph = (GraphView) findViewById(R.id.graph);

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
             //use http://www.android-graphview.org/bar-chart/ for reference for Bar Graph
             //DataPoint should take format (Date, Sightings)
             //Date should take format (Month, Year)
             //Somehow get this data from FireBase and plot graph accordingly
             //Implement FilterGraphButton on PostLogin to pop up this graph from clicking button
             //Implement logic for filtering date range and having the appropriate graph pop up
        });
        graph.addSeries(series);
    }
}

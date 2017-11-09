package edu.gatech.tcf.ratsighting_app.Controller;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import edu.gatech.tcf.ratsighting_app.Model.RatSighting;
import edu.gatech.tcf.ratsighting_app.Model.SightingListContainer;
import edu.gatech.tcf.ratsighting_app.R;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        LineChart lineChart = (LineChart) findViewById(R.id.chart);

        //broken
        int[] months = new int[12];//new int[12 * (SightingListContainer.endYear - SightingListContainer.startYear) + SightingListContainer.endMonth - SightingListContainer.startMonth];

        //adds sightings to buckets
        for (Calendar sighting : SightingListContainer.reports) {
            Log.d("sightingTime", sighting.get(Calendar.MONTH) + "");
            int bucket = sighting.get(Calendar.MONTH);//sighting.get(Calendar.MONTH) - SightingListContainer.startMonth + 12 * (sighting.get(Calendar.YEAR) - SightingListContainer.startYear);
            months[bucket] = months[bucket] + 1;
        }


        List<Entry> entries = convertDataSetToEntry(months);

        LineDataSet dataset = new LineDataSet(entries, "Month");

        LineData data = new LineData(dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //

        dataset.setDrawFilled(true);

        lineChart.setData(data);
        lineChart.animateY(5000);

        lineChart.getDescription().setText("Average Calls per Month");



        /*
        GraphView graph = (GraphView) findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>();


        //adds buckets to graph
        for (int i = 0; i < months.length; i++) {
            Log.d("DataAdded", "i: " + i + " months[i]" + months[i]);
            series.appendData(new DataPoint(i, months[i]), false, 2*months.length);
        }

        final String[] monthArray = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    // show normal x values
                    return monthArray[(int) value];
                }
                return value + "";
            }
        });


        //use http://www.android-graphview.org/bar-chart/ for reference for Bar Graph
        //DataPoint should take format (Date, Sightings)
        //Date should take format (Month, Year)
        //Somehow get this data from FireBase and plot graph accordingly
        //Implement FilterGraphButton on PostLogin to pop up this graph from clicking button
        //Implement logic for filtering date range and having the appropriate graph pop up

        graph.addSeries(series);
        */
    }

    private List<Entry> convertDataSetToEntry(int[] months) {
        List<Entry> entries = new ArrayList<>();

        for (int i = 0; i < months.length; i++) {
            entries.add(new Entry(i, months[i]));
        }

        return entries;
    }
}
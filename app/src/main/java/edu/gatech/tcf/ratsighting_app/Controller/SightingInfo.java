package edu.gatech.tcf.ratsighting_app.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import edu.gatech.tcf.ratsighting_app.Model.RatSighting;
import edu.gatech.tcf.ratsighting_app.R;

public class SightingInfo extends AppCompatActivity {

    /**
     * This activity represents the information screen of each sighting
     *
     * @param savedInstanceState the container of the sighting object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sighting_info);
        Bundle bundle = getIntent().getExtras();
        RatSighting sighting = (RatSighting) bundle.getSerializable("Sighting");
        TextView text1 = (TextView) findViewById(R.id.textView);
        TextView text2 = (TextView) findViewById(R.id.textView2);
        TextView text3 = (TextView) findViewById(R.id.textView3);
        TextView text4 = (TextView) findViewById(R.id.textView4);
        TextView text5 = (TextView) findViewById(R.id.textView5);
        TextView text6 = (TextView) findViewById(R.id.textView6);
        TextView text7 = (TextView) findViewById(R.id.textView7);
        TextView text8 = (TextView) findViewById(R.id.textView8);
        text1.setText("Address: " + sighting.getAddress());
        text2.setText("City: " + sighting.getCity());
        text3.setText("Key: " + sighting.getKey());
        text4.setText("Location Type: " + sighting.getLocationType());
        text5.setText("ZipCode: " + sighting.getZipCode());
        text6.setText("Borough: " + sighting.getBorough());
        text7.setText("Coordinates: " + sighting.getCoordinates());
        text8.setText("Date: " + sighting.getDate());

    }
}

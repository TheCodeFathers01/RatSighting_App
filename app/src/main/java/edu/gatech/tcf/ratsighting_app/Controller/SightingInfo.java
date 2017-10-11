package edu.gatech.tcf.ratsighting_app.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import edu.gatech.tcf.ratsighting_app.Model.RatSighting;
import edu.gatech.tcf.ratsighting_app.R;

public class SightingInfo extends AppCompatActivity {

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
    }
}

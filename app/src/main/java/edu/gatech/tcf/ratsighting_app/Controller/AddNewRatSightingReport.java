package edu.gatech.tcf.ratsighting_app.Controller;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;

import edu.gatech.tcf.ratsighting_app.Model.Borough;
import edu.gatech.tcf.ratsighting_app.Model.LocationType;
import edu.gatech.tcf.ratsighting_app.Model.RatSighting;
import edu.gatech.tcf.ratsighting_app.Model.SightingListContainer;
import edu.gatech.tcf.ratsighting_app.R;

public class AddNewRatSightingReport extends AppCompatActivity implements View.OnClickListener{
    private EditText addressField;
    private EditText cityField;
    private Spinner locationTypeSpinner;
    private EditText zipCodeField;
    private Spinner boroughSpinner;
    private EditText coordinatesField;
    private EditText dateField;
    private Button submitButton;

    private RatSighting _sighting;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_rat_sighting_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //set up of RatSightingReport Widgets
        addressField = (EditText) findViewById(R.id.newReport_address);
        cityField = (EditText) findViewById(R.id.newReport_city);
        locationTypeSpinner = (Spinner) findViewById(R.id.newReport_LocationType);
        zipCodeField = (EditText) findViewById(R.id.newReport_ZipCode);
        boroughSpinner = (Spinner) findViewById(R.id.newReport_Borough);
        coordinatesField = (EditText) findViewById(R.id.newReport_Coordinates);
        dateField = (EditText) findViewById(R.id.newReport_Date);
        submitButton = (Button) findViewById(R.id.newReport_SubmitButton);

        /*
          Set up the adapter to display the allowable location types in the spinner
         */
        ArrayAdapter<LocationType> locationTypeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, LocationType.values());
        locationTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationTypeSpinner.setAdapter(locationTypeAdapter);

        /*
          Set up the adapter to display the allowable boroughs in the spinner
         */
        ArrayAdapter<LocationType> boroughAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Borough.values());
        boroughAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boroughSpinner.setAdapter(boroughAdapter);

        _sighting = new RatSighting();

        addressField.setText(_sighting.getAddress());
        cityField.setText(_sighting.getCity());
        zipCodeField.setText(_sighting.getZipCode());
        coordinatesField.setText(_sighting.getCoordinates());
        dateField.setText(_sighting.getDate());
    }

    @Override
    public void onClick(View v) {
        if(v == submitButton){
            onAddPressed(v);
        }
    }

    /**
     * Button handler for the submit button
     * @param view the button
     */
    public void onAddPressed(View view) {
        Log.d("Edit", "Add Rat Sighting Report");
        submitButton.setOnClickListener(this);
        SightingListContainer.list.add(_sighting);

        finish();
    }

}

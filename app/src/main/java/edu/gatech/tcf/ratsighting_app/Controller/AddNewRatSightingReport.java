package edu.gatech.tcf.ratsighting_app.Controller;

import android.content.Intent;
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
import android.text.TextUtils;
import android.widget.Toast;

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
    private Button cancelButton;

    private String addressText;
    private String cityText;
    private String zipCodeText;
    private String coordinatesText;
    private String dateText;

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
        cancelButton = (Button) findViewById(R.id.newReport_CancelButton);

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

        submitButton = (Button) findViewById(R.id.newReport_SubmitButton);
        submitButton.setOnClickListener(this);

        cancelButton = (Button) findViewById(R.id.newReport_CancelButton);
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == submitButton){
            onSubmitPressed(v);
        } else if (v == cancelButton) {
            onCancelPressed(v);
        }
    }

    /**
     * Button handler for the submit button
     * @param view the button
     */
    public void onSubmitPressed(View view) {
        addressText = addressField.getText().toString().trim();
        cityText = cityField.getText().toString().trim();
        zipCodeText = zipCodeField.getText().toString().trim();
        coordinatesText = coordinatesField.getText().toString().trim();
        dateText = dateField.getText().toString().trim();
        if(TextUtils.isEmpty(addressText) || TextUtils.isEmpty(cityText)
                || TextUtils.isEmpty(zipCodeText) || TextUtils.isEmpty(coordinatesText)
                || TextUtils.isEmpty(dateText)) {
            Toast.makeText(this, "One or more fields are missing.", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d("Edit", "Add Rat Sighting Report");
        _sighting.setAddress(addressText);
        _sighting.setCity(cityText);
        _sighting.setZipCode(zipCodeText);
        _sighting.setCoordinates(coordinatesText);
        _sighting.setDate(dateText);
        LocationType locationType = (LocationType) locationTypeSpinner.getSelectedItem();
        Borough borough = (Borough) boroughSpinner.getSelectedItem();
        _sighting.setLocationType(locationType);
        _sighting.setBorough(borough);
        _sighting.setKey(SightingListContainer.list.get(SightingListContainer.list.size() - 1).getKey() + 6);
        SightingListContainer.list.add(_sighting);

        finish();
    }

    /**
     * Button handler for the cancel button
     * @param view the button
     */
    public void onCancelPressed(View view) {
        Log.d("Edit", "Cancel Rat Sighting Report");
        goToPostLogin();

        finish();
    }

    /**
     * goes to the PostLogin
     */
    private void goToPostLogin() {
        Intent goHome = new Intent(this, PostLogin.class);
        startActivity(goHome);
    }
}

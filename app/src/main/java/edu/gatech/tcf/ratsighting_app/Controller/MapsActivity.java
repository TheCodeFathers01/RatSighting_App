package edu.gatech.tcf.ratsighting_app.Controller;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.gatech.tcf.ratsighting_app.Model.RatSighting;
import edu.gatech.tcf.ratsighting_app.Model.SightingListContainer;
import edu.gatech.tcf.ratsighting_app.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        MarkerOptions marker;
        String ratCoordinates;
        double lattitude = 40.7128;
        double longitude = 74.006;
        String latString;
        String lonString;
        String[] latLon;
        for (RatSighting sighting : SightingListContainer.list) {
            marker = new MarkerOptions();
            ratCoordinates = sighting.getCoordinates();
            if (ratCoordinates != null) {
                ratCoordinates = ratCoordinates.substring(1, ratCoordinates.length() - 2);
            } else {
                continue;
            }
            latLon = ratCoordinates.split(", ");
            try {
                latString = latLon[0];
                lonString = latLon[1];
                lattitude = Double.parseDouble(latString);
                longitude = Double.parseDouble(lonString);
            } catch (Exception e) {
                //decide what to do later
            }
            marker.position(new LatLng(lattitude,longitude)).title("Sighting " + sighting.getKey()).snippet(sighting.getAddress() + " " + sighting.getCity() + " " + sighting.getZipCode());

            googleMap.addMarker(marker);
        }
        googleMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}

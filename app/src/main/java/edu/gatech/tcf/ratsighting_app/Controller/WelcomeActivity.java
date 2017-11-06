package edu.gatech.tcf.ratsighting_app.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import edu.gatech.tcf.ratsighting_app.Model.LocationType;
import edu.gatech.tcf.ratsighting_app.Model.RatSighting;
import edu.gatech.tcf.ratsighting_app.Model.SightingListContainer;
import edu.gatech.tcf.ratsighting_app.Model.User;
import edu.gatech.tcf.ratsighting_app.Model.UserListContainer;
import edu.gatech.tcf.ratsighting_app.R;

public class WelcomeActivity extends AppCompatActivity {

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference("server/saving-data/sightingData");
    private DatabaseReference userRef = database.getReference("server/saving-data/userData");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button welcomeLoginButton = (Button) findViewById(R.id.welcomeLognButton);
        welcomeLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchLoginActivity();
            }
        });
        Button registrationButton = (Button) findViewById(R.id.registrationButton);
        registrationButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v) {
                launchRegistrationActivity();
            }
        });

        setSupportActionBar(toolbar);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                initList(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                initList(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                initUsers(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                initList(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void launchLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void launchRegistrationActivity() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    /**
     * Fills the model's list with 50 sightins from the database
     * @param dataSnapshot the snapshat of the database at that child
     */
    private void initList(DataSnapshot dataSnapshot) {
        Iterable<DataSnapshot> ds = dataSnapshot.getChildren();
        SightingListContainer.list = new ArrayList<RatSighting>();
        RatSighting newSighting;
        LocationType locationType;
        int counter = 0;
        for (DataSnapshot sighting : ds) {
            newSighting = new RatSighting();
            newSighting.setAddress(sighting.getValue(RatSighting.class).getAddress());
            newSighting.setCity(sighting.getValue(RatSighting.class).getCity());
            newSighting.setCoordinates(sighting.getValue(RatSighting.class).getCoordinates());
            newSighting.setBorough(sighting.getValue(RatSighting.class).getBorough());
            newSighting.setKey(sighting.getValue(RatSighting.class).getKey());
            Log.d("Key", "" + sighting.getValue(RatSighting.class).getKey());
            newSighting.setDate(sighting.getValue(RatSighting.class).getDate());
            newSighting.setZipCode(sighting.getValue(RatSighting.class).getZipCode());
            locationType = sighting.getValue(RatSighting.class).getLocationType();
            if (locationType != null) {
                newSighting.setLocationType(locationType);
            } else {
                newSighting.setLocationType(LocationType.Other);
            }
            if (newSighting.getKey() != -1) {
                SightingListContainer.list.add(newSighting);
            }
            if (counter > 500) {
                break;
            }
            counter++;
        }
    }

    private void initUsers(DataSnapshot dataSnapshot) {
        Iterable<DataSnapshot> ds = dataSnapshot.getChildren();
        UserListContainer.list = new ArrayList<User>();
        User newUser;
        LocationType locationType;
        int counter = 0;
        for (DataSnapshot user : ds) {
            newUser = new User();
            newUser.setEmail(user.getValue(User.class).getEmail());
            newUser.setuT(user.getValue(User.class).getuT());
            newUser.setUsername(user.getValue(User.class).getUsername());
            UserListContainer.list.add(newUser);
        }
    }
}

